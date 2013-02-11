package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

public class Dataset implements IDataset{
	
	static final int PAGESIZE = 10;
	
	private Translator translator;
    
	private String word;
    
    private int pageSize;
    
    private int totalResultNumber;
    
    private int totalPageNumber;
    
    private int currentEntryIndex;
    
    private List<Entry> entries = new ArrayList<Entry>();

	// prevent Dataset to be created 
	private Dataset() {
    }
	
    // can only be created by Translator (within its package)
	Dataset(Translator translator, String word, List<Entry> entries) {		
		this.translator = translator;
		this.word = word;
		this.entries.addAll(entries);
	}

	@Override
    public String getWord() {
	    return word;
    }

	@Override
    public int getPageSize() {
	    return PAGESIZE;
    }

	@Override
    public int getTotalPageNumber() {
	    return totalPageNumber;
    }

	@Override
    public int getTotalResultNumber() {
	    return totalResultNumber;
    }

	@Override
    public String getNextTranslation() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getPreviousTranslation() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getTranslation(String entryLabel) {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public List<String> getEntryLabels(int pageIndex) {
	    // TODO Auto-generated method stub
	    return null;
    }
	
    public void addEntries(List<Entry> entries) {
		this.entries.addAll(entries);	    
    }
    
    void setTotalResultNumber(int totalResultNumber) {
    	this.totalResultNumber = totalResultNumber;
    }

	void setTotalPageNumber(int totalPageNumber) {
    	this.totalPageNumber = totalPageNumber;
    }
}
