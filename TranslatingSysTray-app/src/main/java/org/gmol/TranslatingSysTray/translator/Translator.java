package org.gmol.TranslatingSysTray.translator;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.gmol.TranslatingSysTray.App;
import org.json.JSONArray;
import org.json.JSONObject;
import fr.idm.sk.publish.api.client.light.SkPublishAPI;

public class Translator {

	private static final String BASEURL = "https://dictionary.cambridge.org";
	private static final String KEY = "";
	private SearchDataSet dataset = new SearchDataSet();

	public Translator() {
		// TODO Auto-generated constructor stub
	}

	public String translate(String word) {

		System.out.println("Hello World!");

		DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
		SkPublishAPI api = new SkPublishAPI(BASEURL + "/api/v1", KEY, httpClient);
		api.setRequestHandler(new SkPublishAPI.RequestHandler() {
			public void prepareGetRequest(HttpGet request) {
				System.out.println("*** Request: " + request.getURI());
			}
		});

		try {
			// System.out.println("*** Dictionaries ***");
			// JSONArray dictionaries = new JSONArray(api.getDictionaries());
			// System.out.println("*** JSONArray dictionaries:");
			// System.out.println(dictionaries);

			// JSONObject dict = dictionaries.getJSONObject(0);
			// System.out.println("*** dictionaries.getJSONObject(0):");
			// System.out.println(dict);
			// String dictCode = dict.getString("dictionaryCode");
			// System.out.println("*** dictCode: " + dictCode);

			String dictCode = "british";
			System.out.println("*** Search result");
			JSONObject results = new JSONObject(api.search(dictCode, word, 10, 1));
			Page page = DeJsonizer.dejsonSearch(results);
			dataset.addPage(page);
			
			// System.out.println(results);
			System.out.println("*** Get entry");
			String entryId = page.getEntry(0).getEntryLabel();
			JSONObject getEntryresults = new JSONObject(api.getEntry(dictCode, entryId, "html"));
			
		    return DeJsonizer.dejsonEntry(getEntryresults);
			// return DeJsonizer.dejsonEntry(getEntryresults);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return word;

	}
}
