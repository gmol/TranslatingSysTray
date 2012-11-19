package org.gmol.TranslatingSysTray.translator;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.json.JSONObject;
import fr.idm.sk.publish.api.client.light.SkPublishAPI;

public class Translator {

	private static final String BASEURL = "https://dictionary.cambridge.org";
	private String KEY = null;
	private SearchDataSet dataset = new SearchDataSet();

	public Translator(String key) {
		KEY = key;
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
			String dictCode = "british";
			System.out.println("*** Search result");
			JSONObject results = new JSONObject(api.search(dictCode, word, 10, 1));
			Page page = DeJsonizer.dejsonSearch(results);
			dataset.addPage(page);
			
			// System.out.println(results);
			System.out.println("*** Get entry");
			// get entry from a page
			String entryId = page.getEntry(0).getEntryId();			
			System.out.println("search for entry:" + entryId);
			
			JSONObject getEntryresults;
//			try {
				getEntryresults = new JSONObject(api.getEntry(dictCode, entryId, "html"));
//			} catch (SkPublishAPIException e) {
//				// TODO Auto-generated catch block
//				System.out.println("ERROR: Translation of " + entryId + " not found!, ");
//				e.printStackTrace();
//			}
			
		    return DeJsonizer.dejsonEntry(getEntryresults);
			// return DeJsonizer.dejsonEntry(getEntryresults);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return word;

	}
}
