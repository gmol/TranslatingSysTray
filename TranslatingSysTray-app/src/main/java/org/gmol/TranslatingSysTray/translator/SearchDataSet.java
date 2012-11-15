package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

public class SearchDataSet extends DataSet {
	//
	int pageNumber;
	//
	int currentPageIndex;
	//
	int resultNumber;
	//
	List<SearchDataSet.Entry> entryList = new ArrayList<SearchDataSet.Entry>();
	//
	int totalResultNumber;
	//
	
	public void setTotalResultNumber(int totalResultNumber) {
		this.totalResultNumber = totalResultNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public void setResultNumber(int resultNumber) {
		this.resultNumber = resultNumber;
	}
	
	public int getTotalResultNumber() {
		return totalResultNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public int getResultNumber() {
		return resultNumber;
	}

	public List<SearchDataSet.Entry> getEntryList() {
		return entryList;
	}

	class Entry {
		String entryLabel;
		String entryId;
	}	

}
