package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

class Page {
	
//	private static final Logger LOGGER = Logger.getLogger(Page.class);
	
	// 
	@SuppressWarnings("unused")
    private Page() {
		
	}
	
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

	int getTotalPageNumber() {
		return totalPageNumber;
	}

	int getCurrentPageIndex() {
		return currentPageIndex;
	}

	int getTotalResultNumber() {
		return totalResultNumber;
	}

	List<Entry> getEntries() {
    	return entries;
    }
}
