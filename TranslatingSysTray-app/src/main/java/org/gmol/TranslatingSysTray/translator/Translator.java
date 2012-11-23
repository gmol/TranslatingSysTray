package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
	private String translatedEntry = "";
	private SkPublishAPI api;

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

		System.out.println("Hello World!");



		try {
			String dictCode = "british";
			System.out.println("*** Search result");
			JSONObject results = new JSONObject(api.search(dictCode, word, PAGESIZE, currentPageIndex));
			Page page = DeJsonizer.dejsonSearch(results);
			dataset.addPage(page);
			
			// System.out.println(results);
			System.out.println("*** Get entry");
			// get entry from a page
			String entryId = page.getEntry(0).getEntryId();			
			System.out.println("search for entry:" + entryId);
			
			JSONObject getEntryresults;
      		getEntryresults = new JSONObject(api.getEntry(dictCode, entryId, "html"));
			
		    translatedEntry = DeJsonizer.dejsonEntry(getEntryresults);
		} catch (SkPublishAPIException ex) {
			throw new TranslatorEx(ex);
			
		} catch (Throwable t) {
			t.printStackTrace();
			throw new TranslatorEx(t);			
		}
	}	
	
	String getNextTranslation() throws TranslatorEx {
		Entry e = dataset.getNextEntry();
		if (e == null) {
			return null;
		}
		try {
			if (e.getTranslation().isEmpty()) {
				JSONObject getEntryresults = new JSONObject(api.getEntry(
						DICTCODE, e.getEntryId(), "html"));
				String translation = DeJsonizer.dejsonEntry(getEntryresults);
				e.setTranslation(translation);
				return translation;
			}
			return e.getTranslation();
		} catch (JSONException e1) {
			e1.printStackTrace();
			throw new TranslatorEx(e1);
		} catch (SkPublishAPIException e1) {
			e1.printStackTrace();
			throw new TranslatorEx(e1);
		}		
	}
	
	String getPreviousTranslation() throws TranslatorEx {
		Entry e = dataset.getNextEntry();
		if (e == null) {
			return null;
		}
		try {
			if (e.getTranslation().isEmpty()) {
				JSONObject getEntryresults = new JSONObject(api.getEntry(
						DICTCODE, e.getEntryId(), "html"));
				String translation = DeJsonizer.dejsonEntry(getEntryresults);
				e.setTranslation(translation);
				return translation;
			}
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
