package org.gmol.TranslatingSysTray.translator;

import java.io.IOException;
import java.util.ArrayList;
import org.json.*;

/*
 {
    "resultNumber": 416,
    "results": [
       {
          "entryLabel": "go verb  MOVE/TRAVEL ",
          "entryUrl": "http://dictionary.cambridge.org/dictionary/british/go_1",
          "entryId": "go_1"
       },
       {
          "entryLabel": "go verb  LEAVE ",
          "entryUrl": "http://dictionary.cambridge.org/dictionary/british/go_2",
          "entryId": "go_2"
       },
   
   [....]
   
       {
          "entryLabel": "go verb  START ",
          "entryUrl": "http://dictionary.cambridge.org/dictionary/british/go_10",
          "entryId": "go_10"
       }
    ],
    "dictionaryCode": "british",
    "currentPageIndex": 1,
    "pageNumber": 42
 }
 */

/* entry response
 {
 "topics": [],
 "dictionaryCode": "british",
 "entryLabel": "go",
 "entryContent": "<header>",
 "entryUrl": "http://dictionary.cambridge.org/dictionary/british/go_2",
 "format": "html",
 "entryId": "go_2"
 }
 */

class DeJsonizer {

	public static Page dejsonSearch(JSONObject search) throws Exception {

		int totalResultNumber = (Integer) search.get("resultNumber");
		System.out.println("Total Result Number: " + totalResultNumber);
		int pageNumber = (Integer) search.get("pageNumber");
		System.out.println("Page number:" + pageNumber);
		int currentPageIndex = (Integer) search.get("currentPageIndex");
		System.out.println("Current Page index:" + currentPageIndex);
		// loop array
		JSONArray results = (JSONArray) search.get("results");
		int resultNumber = results.length();
		java.util.List<Entry> entries = new ArrayList<Entry>();
		for (int i = 0; i < results.length(); i++) {

			JSONObject o = (JSONObject) results.get(i);

			String entryLabel = (String) o.get("entryLabel");
			System.out.println("entryLabel: " + entryLabel);

			String entryId = (String) o.get("entryId");
			System.out.println("entryId: " + entryId);

			entries.add(new Entry(entryId, entryLabel));
		}
		return new Page(totalResultNumber, pageNumber, currentPageIndex, entries);
	}

	public static String dejsonEntry(JSONObject entry) throws JSONException {
		String entryContent = null;
		entryContent = (String) entry.get("entryContent");
		return entryContent;
	}

}
