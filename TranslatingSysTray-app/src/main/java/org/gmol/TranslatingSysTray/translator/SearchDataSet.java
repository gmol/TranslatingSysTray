package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

public class SearchDataSet extends DataSet {
	//
	int currentEntryIndex = 0;
	int total = 0;
	List<Page> pageList = new ArrayList<Page>();

	void addPage(Page page) {
		total += page.getEntryCount();
		pageList.add(page);
	}

	Entry getNextEntry() {
		if (total > 0) {
			if (currentEntryIndex < total) {
				int tmpTotal = 0;
				for (Page p : pageList) {
					tmpTotal += p.getEntryCount();
					if (currentEntryIndex < tmpTotal) {
						Entry e = p.getEntry(tmpTotal - currentEntryIndex);
						currentEntryIndex++;
						return e;
					}
				}
			}
		}
		return null;
	}
	
	Entry getPrevEntry() {
		if (total > 0) {
			if ((currentEntryIndex-1) >= 0 ) {
				currentEntryIndex--;
				int tmpTotal = 0;
				for (Page p : pageList) {
					tmpTotal += p.getEntryCount();
					if (currentEntryIndex < tmpTotal) {
						Entry e = p.getEntry(tmpTotal - currentEntryIndex);
						return e;
					}
				}
			}
		}
		return null;
	}
	
	/*
	 * Get entry labels for all pages
	 */
	ArrayList<String> getEntryLabels() {
		ArrayList<String> labels = new ArrayList<String>();
		for (Page page : pageList) {
			ArrayList<String> pageEntries = page.getEntryLabels();
			for (String label : pageEntries) {
				labels.add(label);				
			}			
		}	
		return labels;
	}	
}
