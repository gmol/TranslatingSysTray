package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;

public class SearchDataSet extends DataSet {
	//
	int resultIndex = 0;

	List<Page> pageList = new ArrayList<Page>();

	void addPage(Page page) {
		pageList.add(page);
	}

	Entry getNextEntry() {
		int total = 0;
		for (Page page : pageList) {
			total += page.getEntryCount();
			if (resultIndex < total) {
				return page.getEntry(total - resultIndex);
			}
		}
		return null;
	}
}
