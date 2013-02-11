package org.gmol.TranslatingSysTray.translator;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager; //mydoto do sth about that
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import fr.idm.sk.publish.api.client.light.SkPublishAPI;
import fr.idm.sk.publish.api.client.light.SkPublishAPIException;
//import org.gmol.TranslatingSysTray.translator.UTestAPI;
import org.gmol.TranslatingSysTray.translator.TranslatorEx;

public class Translator implements ITranslator{

	private static final Logger LOGGER = Logger.getLogger(Translator.class);
	private static final String BASEURL = "https://dictionary.cambridge.org";
	private static final int PAGESIZE = 10;
	private static final String DICTCODE = "british";

	private SearchDataSet dataset = new SearchDataSet();
	private int currentPageIndex = 1;
	private SkPublishAPI api;
//	private String word = "";

	public Translator(String key) {
		DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
		api = new SkPublishAPI(BASEURL + "/api/v1", key, httpClient);
		api.setRequestHandler(new SkPublishAPI.RequestHandler() {
			public void prepareGetRequest(HttpGet request) {
				LOGGER.debug("*** Request: " + request.getURI());
			}
		});
	}
	
//	public Translator(String key, UTestAPI api) {
//		DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
//		api = api;
//		api.setRequestHandler(new SkPublishAPI.RequestHandler() {
//			public void prepareGetRequest(HttpGet request) {
//				LOGGER.debug("*** Request: " + request.getURI());
//			}
//		});
//	}

	public Dataset translate(String word) throws TranslatorEx {

		LOGGER.debug("---translate(" + word + ")");

		try {
			LOGGER.debug("*** Search result");
			currentPageIndex = 1;
			JSONObject results = new JSONObject(api.search(DICTCODE, word, PAGESIZE, currentPageIndex++));
			List<Entry> entries = DeJsonizer.dejsonSearch(results);
			dataset = new SearchDataSet();
			dataset.addPage(null);
//			this.word = word;

			// // LOGGER.debug(results);
			// LOGGER.debug("*** Get entry");
			// // get entry from a page
			// String entryId = page.getEntry(0).getEntryId();
			// LOGGER.debug("search for entry:" + entryId);
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
		return null;
	}

	
	public String getTranslation(final Entry e) throws TranslatorEx {
		LOGGER.debug(e.toString());
		try {
			JSONObject getEntryresults = new JSONObject(api.getEntry(DICTCODE, e.getEntryId(), "html"));
			String translation = DeJsonizer.dejsonEntry(getEntryresults);
			LOGGER.debug("Translation: " + translation);
			e.setTranslation(translation);  //TODO WTF?
			return translation;

		} catch (Exception e1) {
			e1.printStackTrace();
			throw new TranslatorEx(e1);
		}
	}
	
	public List<Entry> getPage(IDataset dataset, int pageIndex) throws TranslatorEx {
		try {
			JSONObject results = new JSONObject(api.search(DICTCODE, dataset.getWord(), PAGESIZE, pageIndex));
			return DeJsonizer.dejsonSearch(results);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			throw new TranslatorEx(ex);
		}
	}
	
//	public String getNextTranslation() throws TranslatorEx {
//		LOGGER.debug("Enter: getNextTranslation");
//		Entry e = dataset.getNextEntry();		
//		if (e == null) {
//			LOGGER.debug("Entry is NULL");
//			if (dataset.areAllPagesFetched()) {
//				LOGGER.debug("all pages are fetch, return null");
//				return null;
//			} else {
//				// mytodo fetch the next page
//				LOGGER.debug("*** Fetch the next page");
//				try {
//					if (word.isEmpty()) {
//						return "ERROR: you are trying to translated an empty word";						
//					}
//					JSONObject results = new JSONObject(api.search(DICTCODE, word, PAGESIZE, currentPageIndex++));
//					Page page = null;
//					dataset.addPage(page);
//					e = dataset.getNextEntry();
//					if (e == null) {
//						throw new TranslatorEx("A next page has been fetched but the next entry is null");
//					}
//				} catch (Exception ex) {
//					// TODO Auto-generated catch block
//					ex.printStackTrace();
//					throw new TranslatorEx(ex);
//				}
//
//			}
//		}
//		LOGGER.debug("Print entry: " + e.toString());
//		try {
//			if (e.getTranslation().isEmpty()) {
//				JSONObject getEntryresults = new JSONObject(api.getEntry(DICTCODE, e.getEntryId(), "html"));
//				String translation = DeJsonizer.dejsonEntry(getEntryresults);
//				LOGGER.debug("translation: " + translation);
//				e.setTranslation(translation);
//				return translation;
//			}
//			return e.getTranslation();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			throw new TranslatorEx(e1);
//		}
//	}

//	public String getPreviousTranslation() throws TranslatorEx {
//		Entry e = dataset.getPrevEntry();
//		if (e == null) {
//			return null;
//		}
//		try {
//			if (e.getTranslation().isEmpty()) {
//				JSONObject getEntryresults = new JSONObject(api.getEntry(DICTCODE, e.getEntryId(), "html"));
//				String translation = DeJsonizer.dejsonEntry(getEntryresults);
//				e.setTranslation(translation);
//				return translation;
//			}
//			LOGGER.debug("Print entry: " + e.toString());
//			return e.getTranslation();
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//			throw new TranslatorEx(e1);
//		} catch (SkPublishAPIException e1) {
//			e1.printStackTrace();
//			throw new TranslatorEx(e1);
//		}
//	}

	public ArrayList<String> getEntryLabels() {
		return dataset.getEntryLabels();
	}
	
	public IDataset translateee(String word) throws TranslatorEx {
		LOGGER.debug("---translate(" + word + ")");

		try {
			LOGGER.debug("*** Search result");
			currentPageIndex = 1;
			JSONObject results = new JSONObject(api.search(DICTCODE, word, PAGESIZE, currentPageIndex++));
			List<Entry> entries = DeJsonizer.dejsonSearch(results);
			IDataset dataset = new Dataset(this);
			// IDataset dataset = new Dataset(this, entries);
			// or
			// dataset.addEntries(entries)

		} catch (SkPublishAPIException ex) {
			throw new TranslatorEx(ex);

		} catch (Throwable t) {
			t.printStackTrace();
			throw new TranslatorEx(t);
		}
		return null;

	}

}
