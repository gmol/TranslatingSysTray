package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

public class SearchDataSet extends DataSet {
	//
	int currentEntryIndex = 0;
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
	
	boolean areAllPagesFetched() {
		int lastFetchedPage = lastPageAdded.getCurrentPageIndex();
		return ((lastFetchedPage + 1) == lastPageAdded.getTotalPageNumber());
	}

	void addPage(Page page) {
		totalFetchedEntryCount += page.getEntryCount();
		lastPageAdded = page;
		pageList.add(page);
	}

	Entry getNextEntry() {
		if (totalFetchedEntryCount > 0) {
			if (currentEntryIndex < totalFetchedEntryCount) {
				int tmpTotal = 0;
				for (Page p : pageList) {
					tmpTotal += p.getEntryCount();
					if (currentEntryIndex < tmpTotal) {
						Entry e = p.getEntry(tmpTotal - currentEntryIndex);
						currentEntryIndex++;
						return e;
					}
				}
			}
		}
		return null;
	}
	
	Entry getPrevEntry() {
		if (totalFetchedEntryCount > 0) {
			if ((currentEntryIndex-1) >= 0 ) {
				currentEntryIndex--;
				int tmpTotal = 0;
				for (Page p : pageList) {
					tmpTotal += p.getEntryCount();
					if (currentEntryIndex < tmpTotal) {
						Entry e = p.getEntry(tmpTotal - currentEntryIndex);
						return e;
					}
				}
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
