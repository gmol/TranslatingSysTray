/**
 * 
 */
package org.gmol.TranslatingSysTray.translator;

import java.util.List;

/**
 * @author stulka
 *
 */
public interface IDataset {
	//
    String getWord();
    //
    int getPageSize();
	//
	int getTotalPageNumber();
	//
	int getTotalResultNumber();
    //
	String getNextTranslation();
	//
    String getPreviousTranslation();
    //
    String getTranslation(String entryLabel);
    //
    List<String> getEntryLabels(int pageIndex);
}
