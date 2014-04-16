package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Dataset implements IDataset{
	
	private static final Logger LOGGER = Logger.getLogger(Dataset.class);
	
	private Translator translator;
    
	private String word;
    
    private int pageSize;
    
    private int totalResultNumber;
    
    private int totalPageNumber;
    
    private int currentEntryIndex;
    
    private List<Entry> entries = new ArrayList<Entry>();

	// prevent Dataset to be created 
	@SuppressWarnings("unused")
    private Dataset() {
    }
	
	// can only be created by Translator (within its package)
		Dataset(Translator translator, String word, int pageSize, Page page) {		
			this.translator = translator;		
			this.word = word;
			this.pageSize = pageSize;
			this.totalResultNumber = page.getTotalResultNumber();
			this.totalPageNumber = page.getTotalPageNumber();		
			this.entries.addAll(page.getEntries());			
			currentEntryIndex = page.getCurrentPageIndex();
		}

	@Override
    public String getWord() {
	    return word;
    }

	@Override
    public int getTotalResultNumber() {
	    return totalResultNumber;
    }	

	@Override
	public String getNextTranslation() throws DatasetEx {

		int currentPageIndex = getCurrentPageIndex();
		int nextIndex = currentEntryIndex + 1;
		LOGGER.debug("Current page index is : " + currentPageIndex + ", nextIndex is : " + nextIndex + " and entries size is : " + entries.size());
		
		if (entries.size() < 1) {
			throw new DatasetEx("There is not entries in the dataset");
		}

		try {
			Entry e = null;
			if (nextIndex > entries.size()) {				
				LOGGER.debug("Entries has not been downloaded yet");
			    LOGGER.debug("Check if Dataset is not on the last page");
				if (currentPageIndex < totalPageNumber) {
					LOGGER.debug("Fetch the next page");
					List<Entry> newEntries = translator.getEntries(this, currentPageIndex + 1);					
					if (newEntries.size() > 0) {
						entries.addAll(newEntries);
						e = entries.get(nextIndex);
						LOGGER.debug("Entry [" + e.getEntryId() + "] is new so translate it");
						String translation = translator.getEntryTranslation(e);
						e.setTranslation(translation);
					} else {
						String msg = "The fetched page returned 0 entries"; 
						LOGGER.warn(msg);
						throw new DatasetEx(msg);
					}
				} else {
					LOGGER.debug("Dataset is on the last page return last entry");
					e= entries.get(entries.size() - 1);
					nextIndex = currentEntryIndex;
				}				
			} else {
				LOGGER.debug("Entries from the downloaded page but check if the entry has been translated yet");
				e = entries.get(nextIndex - 1);				
				if (e.getTranslation().isEmpty()) {
					LOGGER.debug("Entry [" + e.getEntryId() + "] has not been translated yet. Translate");
					String translation = translator.getEntryTranslation(e);
					e.setTranslation(translation);
				} else {
					LOGGER.debug("Entry [" + e.getEntryId() + "] has already been translated");
				}
			
			}
			currentEntryIndex = nextIndex;
			return e.getTranslation();
			
		} catch (TranslatorEx e) {
			e.printStackTrace();
			throw new DatasetEx(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatasetEx(e);
		}
	}

	@Override
    public String getPreviousTranslation() throws DatasetEx {

		int currentPageIndex = getCurrentPageIndex();
		int prevIndex = (currentEntryIndex - 1) > 0 ? (currentEntryIndex - 1) : 1;
		LOGGER.debug("Current page index is : " + currentPageIndex + ", prevIndex is : " + prevIndex + " and entries size is : " + entries.size());
		if (entries.size() > 0) {
			LOGGER.debug("Entries from the downloaded page but check if the entry has been translated yet");
			try {
	            Entry e = entries.get(prevIndex - 1);
	            if (e.getTranslation().isEmpty()) {
		            LOGGER.debug("Entry [" + e.getEntryId() + "] has not been translated yet. Translate");
		            String translation = translator.getEntryTranslation(e);
		            e.setTranslation(translation);
		            currentEntryIndex = prevIndex;
		            return translation;
	            }
	            LOGGER.debug("Entry [" + e.getEntryId() + "] has already been translated");
	            currentEntryIndex = prevIndex;
	            return e.getTranslation();
			} catch (TranslatorEx e) {
				throw new DatasetEx(e);
            } catch (Exception e) {
            	throw new DatasetEx(e);
            }
	        
        } else {
        	throw new DatasetEx("There is no entries in the dataset");
        }
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
    
    private int getCurrentPageIndex() {
    	return ((currentEntryIndex/pageSize) + 1);
    }

	@Override
    public String getFirstTranslation() throws DatasetEx {
		LOGGER.debug("First Index is 0 and entries size is : " + entries.size());
		int firstIndex = 0;
		if (entries.size() > 0) {
			LOGGER.debug("Entries from the downloaded page but check if the entry has been translated yet");
			try {
	            Entry e = entries.get(firstIndex);
	            if (e.getTranslation().isEmpty()) {
		            LOGGER.debug("Entry [" + e.getEntryId() + "] has not been translated yet. Translate");
		            String translation = translator.getEntryTranslation(e);
		            e.setTranslation(translation);
		            return translation;
	            }
	            LOGGER.debug("Entry [" + e.getEntryId() + "] has already been translated");
	            return e.getTranslation();
			} catch (TranslatorEx e) {
				throw new DatasetEx(e);
            } catch (Exception e) {
            	throw new DatasetEx(e);
            }	        
        } else {
        	throw new DatasetEx("There is no entries in the dataset");
        }
    }
}
