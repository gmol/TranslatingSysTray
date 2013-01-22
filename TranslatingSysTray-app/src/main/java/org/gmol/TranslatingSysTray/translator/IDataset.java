/**
 * 
 */
package org.gmol.TranslatingSysTray.translator;

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
    java.util.List<String> getEntryLabels(int pageIndex);

}
