package org.gmol.TranslatingSysTray.translator;

import java.util.List;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager; //mydoto do sth about that
import org.apache.log4j.Logger;
import org.json.JSONObject;
import fr.idm.sk.publish.api.client.light.SkPublishAPI;
import fr.idm.sk.publish.api.client.light.SkPublishAPIException;
//import org.gmol.TranslatingSysTray.translator.UTestAPI;
import org.gmol.TranslatingSysTray.translator.TranslatorEx;

@SuppressWarnings("deprecation")
public class Translator implements ITranslator{

	private static final Logger LOGGER = Logger.getLogger(Translator.class);
	private static final String BASEURL = "https://dictionary.cambridge.org";
	private static final String DICTCODE = "british";
	private SkPublishAPI api;
	private int pageSize = 10;

	public Translator(String key) {
		DefaultHttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
		api = new SkPublishAPI(BASEURL + "/api/v1", key, httpClient);
		api.setRequestHandler(new SkPublishAPI.RequestHandler() {
			public void prepareGetRequest(HttpGet request) {
				LOGGER.debug("*** Request: " + request.getURI());
			}
		});
	}
	
	public String getEntryTranslation(final Entry e) throws TranslatorEx {
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
	
	public List<Entry> getEntries(IDataset dataset, int pageIndex) throws TranslatorEx {
		try {
			JSONObject results = new JSONObject(api.search(DICTCODE, dataset.getWord(), pageSize, pageIndex));
			Page page = DeJsonizer.dejsonSearch(results); 
			return page.getEntries();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new TranslatorEx(ex);
		}
	}
	
	public IDataset translate(String word) throws TranslatorEx {
		LOGGER.debug("translate(" + word + ")");

		try {
			int pageIndex = 1;
			JSONObject results = new JSONObject(api.search(DICTCODE, word, pageSize, pageIndex));
			Page page = DeJsonizer.dejsonSearch(results);
			IDataset dataset = new Dataset(this, word, pageSize, page);
			return dataset;
		} catch (SkPublishAPIException ex) {
			throw new TranslatorEx(ex);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new TranslatorEx(t);
		}
	}

}
