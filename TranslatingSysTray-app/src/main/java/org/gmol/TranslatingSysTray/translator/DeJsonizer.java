package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import org.apache.log4j.Logger;
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
	
	private static final Logger LOGGER = Logger.getLogger(DeJsonizer.class);

	public static Page dejsonSearch(JSONObject search) throws Exception {
		LOGGER.debug("dejsonSearch");
		
		int totalResultNumber = (Integer) search.get("resultNumber");
		LOGGER.debug("Total Result Number: " + totalResultNumber);
		
		int totalPageNumber = (Integer) search.get("pageNumber");
		LOGGER.debug("Page number:" + totalPageNumber);
		
		int currentPageIndex = (Integer) search.get("currentPageIndex");
		LOGGER.debug("Current Page index:" + currentPageIndex);
		
		// loop array
		JSONArray results = (JSONArray) search.get("results");
		int resultNumber = results.length();
		LOGGER.debug("Number of results fetched: " + resultNumber);
		java.util.List<Entry> entries = new ArrayList<Entry>();
		for (int i = 0; i < results.length(); i++) {

			JSONObject o = (JSONObject) results.get(i);

			String entryLabel = (String) o.get("entryLabel");
			LOGGER.debug("entryLabel: " + entryLabel);

			String entryId = (String) o.get("entryId");
			LOGGER.debug("entryId: " + entryId);

			entries.add(new Entry(entryId, entryLabel));
		}
		return new Page(totalResultNumber, totalPageNumber, currentPageIndex, entries);
	}

	public static String dejsonEntry(JSONObject entry) throws JSONException {
		String entryContent = null;
		entryContent = (String) entry.get("entryContent");
		String header = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n";
		entryContent = header.concat(entryContent);
		return entryContent;
	}

}
