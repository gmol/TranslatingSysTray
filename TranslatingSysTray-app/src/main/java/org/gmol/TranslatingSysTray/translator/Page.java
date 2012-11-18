package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

class Page {
	
	Page(int totalResultNumber, int totalPageNumber, int currentPageIndex, List<Entry> entries) {
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

}
