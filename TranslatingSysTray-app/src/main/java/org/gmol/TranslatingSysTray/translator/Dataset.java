package org.gmol.TranslatingSysTray.translator;

import java.util.List;

public class Dataset implements IDataset{
	
	private Translator translator;
    private String word;
    
    private int pageSize;
    private int totalResultNumber;
    private int totalPageNumber;
    
    private int currentEntryIndex;
    private List<Entry> entries;

	// prevent Dataset to be created outside its package
	private Dataset() {
    }
    // can only be created by Translator	
	Dataset(Translator translator) {
		this.translator = translator;
	}

	@Override
    public String getWord() {
	    return word;
    }

	@Override
    public int getPageSize() {
	    return pageSize;
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
	
}
