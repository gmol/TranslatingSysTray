package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;

public interface DataSet {
	public String getNextTranslation() throws TranslatorEx;
	public String getPreviousTranslation() throws TranslatorEx;
	public ArrayList<String> getEntryLabels();
	public ArrayList<String> getEntryLabels(int pageIndex);
	int getTotalResultNumber();
	int getTotalPageNumber();
	int getCurrentEntryIndex();
	int getCurrentPageIndex();
}
