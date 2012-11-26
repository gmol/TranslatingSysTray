package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
//mytodo check how it can be fixed
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.json.JSONException;
import org.json.JSONObject;
import fr.idm.sk.publish.api.client.light.SkPublishAPI;
import fr.idm.sk.publish.api.client.light.SkPublishAPIException;

public class Translator {

	private static final String BASEURL = "https://dictionary.cambridge.org";
	private static final int PAGESIZE = 10;
	private static final String DICTCODE = "british";

	private SearchDataSet dataset = new SearchDataSet();
	private int currentPageIndex = 1;
	private SkPublishAPI api;
	private String word = "";

	public Translator(String key) {
		DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
		api = new SkPublishAPI(BASEURL + "/api/v1", key, httpClient);
		api.setRequestHandler(new SkPublishAPI.RequestHandler() {
			public void prepareGetRequest(HttpGet request) {
				System.out.println("*** Request: " + request.getURI());
			}
		});
	}

	public void translate(String word) throws TranslatorEx {

		System.out.println("--------------------------translate(" + word + ")");

		try {
			System.out.println("*** Search result");
			currentPageIndex = 1;
			JSONObject results = new JSONObject(api.search(DICTCODE, word, PAGESIZE, currentPageIndex++));
			Page page = DeJsonizer.dejsonSearch(results);			
			dataset = new SearchDataSet();
			dataset.addPage(page);
			this.word = word;

			// // System.out.println(results);
			// System.out.println("*** Get entry");
			// // get entry from a page
			// String entryId = page.getEntry(0).getEntryId();
			// System.out.println("search for entry:" + entryId);
			//
			// JSONObject getEntryresults;
			// getEntryresults = new JSONObject(api.getEntry(DICTCODE, entryId,
			// "html"));
			//
			// translatedEntry = DeJsonizer.dejsonEntry(getEntryresults);
		} catch (SkPublishAPIException ex) {
			throw new TranslatorEx(ex);

		} catch (Throwable t) {
			t.printStackTrace();
			throw new TranslatorEx(t);
		}
	}

	public String getNextTranslation() throws TranslatorEx {
		System.out.println("Enter: getNextTranslation");
		Entry e = dataset.getNextEntry();		
		if (e == null) {
			System.out.println("Entry is NULL");
			if (dataset.areAllPagesFetched()) {
				System.out.println("all pages are fetch, return null");
				return null;
			} else {
				// mytodo fetch the next page
				System.out.println("*** Fetch the next page");
				try {
					if (word.isEmpty()) {
						return "ERROR: you are trying to translated an empty word";						
					}
					JSONObject results = new JSONObject(api.search(DICTCODE, word, PAGESIZE, currentPageIndex++));
					Page page = DeJsonizer.dejsonSearch(results);
					dataset.addPage(page);
					e = dataset.getNextEntry();
					if (e == null) {
						throw new TranslatorEx("A next page has been fetched but the next entry is null");
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
					throw new TranslatorEx(ex);
				}

			}
		}
		System.out.println("Print entry: " + e.toString());
		try {
			if (e.getTranslation().isEmpty()) {
				JSONObject getEntryresults = new JSONObject(api.getEntry(DICTCODE, e.getEntryId(), "html"));
				String translation = DeJsonizer.dejsonEntry(getEntryresults);
				System.out.println("translation: " + translation);
				e.setTranslation(translation);
				return translation;
			}
			return e.getTranslation();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new TranslatorEx(e1);
		}
	}

	public String getPreviousTranslation() throws TranslatorEx {
		Entry e = dataset.getPrevEntry();
		if (e == null) {
			return null;
		}
		try {
			if (e.getTranslation().isEmpty()) {
				JSONObject getEntryresults = new JSONObject(api.getEntry(DICTCODE, e.getEntryId(), "html"));
				String translation = DeJsonizer.dejsonEntry(getEntryresults);
				e.setTranslation(translation);
				return translation;
			}
			System.out.println("Print entry: " + e.toString());
			return e.getTranslation();
		} catch (JSONException e1) {
			e1.printStackTrace();
			throw new TranslatorEx(e1);
		} catch (SkPublishAPIException e1) {
			e1.printStackTrace();
			throw new TranslatorEx(e1);
		}
	}

	public ArrayList<String> getEntryLabels() {
		return dataset.getEntryLabels();
	}
}
