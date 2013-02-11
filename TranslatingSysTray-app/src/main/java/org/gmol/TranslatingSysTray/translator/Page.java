package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

class Page {
	
	private static final Logger LOGGER = Logger.getLogger(Page.class);
	
	Page(int totalResultNumber, int totalPageNumber, int currentPageIndex,
			List<Entry> entries) {
		this.totalResultNumber = totalResultNumber;
		this.totalPageNumber = totalPageNumber;
		this.currentPageIndex = currentPageIndex;
		this.entries = entries;
	}

	//
	private int currentPageIndex;
	//
	private int totalResultNumber;
	//
	private int totalPageNumber;
	//
	private List<Entry> entries = new ArrayList<Entry>();

	int getEntryCount() {
		return entries.size();
	}

	Entry getEntry(int index) {
		LOGGER.debug("getEntry(" + index+ "), entries.size = " + entries.size());
		
		if (index < 0 || index >= entries.size()) {
			return null;
		}
		return entries.get(index);
	}

	int getTotalPageNumber() {
		return totalPageNumber;
	}

	int getCurrentPageIndex() {
		return currentPageIndex;
	}

	int getTotalResultNumber() {
		return totalResultNumber;
	}

	ArrayList<String> getEntryLabels() {
		ArrayList<String> labels = new ArrayList<String>();
		if (entries.size() > 0) {
			for (Entry e : entries) {
				labels.add(e.getEntryLabel());
			}			
		} 
		return labels;
	}

	List<Entry> getEntries() {
    	return entries;
    }
}
