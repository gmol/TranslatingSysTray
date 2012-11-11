package org.gmol.TranslatingSysTray;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.idm.sk.publish.api.client.light.SkPublishAPI;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		for (String string : args) {
			// mytodo remove
			System.out.println("Arg:" + string);
		}
		if (args.length != 2) {
			System.err
					.println("need baseurl and accesskey in parameters, param no = "
							+ args.length);
			return;
		}

		DefaultHttpClient httpClient = new DefaultHttpClient(
				new ThreadSafeClientConnManager());
		SkPublishAPI api = new SkPublishAPI(args[0] + "/api/v1", args[1],
				httpClient);
		api.setRequestHandler(new SkPublishAPI.RequestHandler() {
			public void prepareGetRequest(HttpGet request) {
				System.out.println("*** Request: " + request.getURI());
			}
		});

		try {
			System.out.println("*** Dictionaries ***");
			JSONArray dictionaries = new JSONArray(api.getDictionaries());
			System.out.println("*** JSONArray dictionaries:");
			System.out.println(dictionaries);

			JSONObject dict = dictionaries.getJSONObject(0);
			System.out.println("*** dictionaries.getJSONObject(0):");
			System.out.println(dict);
			String dictCode = dict.getString("dictionaryCode");
			System.out.println("*** dictCode: " + dictCode);

			System.out.println("*** Search result");
			JSONObject results = new JSONObject(
					api.search(dictCode, "go", 1, 1));
			System.out.println(results);

			System.out.println("*** Get entry");
			JSONObject getEntryresults = new JSONObject(api.getEntry(dictCode,
					"go_1", "html"));
			System.out.println(getEntryresults);

			System.out.println("*** Spell checking");
			JSONObject spellResults = new JSONObject(api.didYouMean(dictCode,
					"dorg", 3));
			System.out.println(spellResults);
			System.out.println("*** Best matching");
			JSONObject bestMatch = new JSONObject(api.searchFirst(dictCode,
					"ca", "html"));
			System.out.println(bestMatch);

			System.out.println("*** Nearby Entries");
			JSONObject nearbyEntries = new JSONObject(api.getNearbyEntries(
					dictCode, bestMatch.getString("entryId"), 3));
			System.out.println(nearbyEntries);
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
}
