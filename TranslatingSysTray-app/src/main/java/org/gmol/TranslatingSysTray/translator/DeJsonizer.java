package org.gmol.TranslatingSysTray.translator;

import java.io.IOException;

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
	
	public static String dejsonSearch(JSONObject search) {
		try {
			int totalResultNumber = (Integer) search.get("resultNumber");
			System.out.println("Result Number: " + totalResultNumber);
			int pageNumber = (Integer) search.get("pageNumber");
			System.out.println("Page number:" + pageNumber);
			int currentPageIndex = (Integer) search.get("currentPageIndex");
			System.out.println("Page number:" + currentPageIndex);
			// loop array
			JSONArray results = (JSONArray) search.get("results");
			int resultNumber = results.length();
			for (int i = 0; i < results.length(); i++) {
				JSONObject o = (JSONObject) results.get(i);

				String entryLabel = (String) o.get("entryLabel");
				System.out.println("entryLabel: " + entryLabel);

				String entryId = (String) o.get("entryId");
				System.out.println("entryLabel: " + entryLabel);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}
	
	public static String dejsonEntry(JSONObject entry) {
		String entryContent = null;
		try {
			entryContent = (String)entry.get("entryContent");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entryContent;
	}

}
