package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SearchDataSet /*implements DataSet*/ {
	
	private static final Logger LOGGER = Logger.getLogger(SearchDataSet.class);
	//
	int currentEntryIndex = -1;
	int totalFetchedEntryCount = 0;
	List<Page> pageList = new ArrayList<Page>();
	Page lastPageAdded = null;

	int getCurrentEntryIndex() {
		return currentEntryIndex;
	}

	int getTotalPageNumber() {
		return lastPageAdded.getTotalPageNumber();
	}

	int getTotalResultNumber() {
		return lastPageAdded.getTotalResultNumber();
	}

	int getCurrentPageIndex() {
		return lastPageAdded.getCurrentPageIndex();
	}
	
	int fetchEntryCount() {
		return totalFetchedEntryCount;
	}
	

	boolean areAllPagesFetched() {
		LOGGER.debug("Enter: areAllPagesFetched");
		if (lastPageAdded == null)
			return false;
		int lastFetchedPage = lastPageAdded.getCurrentPageIndex();
		// mytodo remove
		if ((lastFetchedPage + 1) == lastPageAdded.getTotalPageNumber()) {
			LOGGER.debug("All pages fetched");
		} else {
			LOGGER.debug("Not all pages fetched");
		}
		return ((lastFetchedPage + 1) == lastPageAdded.getTotalPageNumber());
	}

	void addPage(Page page) {
		LOGGER.debug("addPage(Page page)");
		totalFetchedEntryCount += page.getEntryCount();
		LOGGER.debug("totalFetchedEntryCount=" + totalFetchedEntryCount);
		lastPageAdded = page;
		pageList.add(page);
	}

	Entry getNextEntry() {
		LOGGER.debug("getNextEntry: totalFetchedEntryCount="
				+ totalFetchedEntryCount);
		if (totalFetchedEntryCount > 0) {
			LOGGER.debug("currentEntryIndex=" + currentEntryIndex);
			currentEntryIndex++;
			return getEntry();
		}
		return null;
	}

	Entry getPrevEntry() {
		if (totalFetchedEntryCount > 0) {
			currentEntryIndex--;
			return getEntry();
		}
		return null;
	}

	private Entry getEntry() {
		// check && adjust index value
		if (currentEntryIndex < 0) {
			currentEntryIndex = 0;
		}
		// check && adjust index value
		if (currentEntryIndex >= totalFetchedEntryCount) {
			currentEntryIndex = totalFetchedEntryCount - 1;
		}

		int tmpTotal = 0;
		for (Page p : pageList) {
			tmpTotal += p.getEntryCount();
			if (currentEntryIndex < tmpTotal) {
				Entry e = p.getEntry(currentEntryIndex
						- (tmpTotal - p.getEntryCount()));
				return e;
			}
		}
		return null;
	}

	/*
	 * Get entry labels for all pages
	 */
	ArrayList<String> getEntryLabels() {
		ArrayList<String> labels = new ArrayList<String>();
		for (Page page : pageList) {
			ArrayList<String> pageEntries = page.getEntryLabels();
			for (String label : pageEntries) {
				labels.add(label);
			}
		}
		return labels;
	}
}
