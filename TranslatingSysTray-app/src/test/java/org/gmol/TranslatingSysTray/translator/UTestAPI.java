/**
 * 
 */
package org.gmol.TranslatingSysTray.translator;

import org.apache.http.client.HttpClient;

import fr.idm.sk.publish.api.client.light.SkPublishAPI;
import fr.idm.sk.publish.api.client.light.SkPublishAPIException;

/**
 * @author stulka
 *
 */
public class UTestAPI extends SkPublishAPI {

	/**
	 * 
	 */
	public UTestAPI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param baseUrl
	 * @param accessKey
	 */
	public UTestAPI(String baseUrl, String accessKey) {
		super(baseUrl, accessKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param baseUrl
	 * @param accessKey
	 * @param httpClient
	 */
	public UTestAPI(String baseUrl, String accessKey, HttpClient httpClient) {
		super(baseUrl, accessKey, httpClient);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getEntry(String dictionaryCode, String entryId, String format) throws SkPublishAPIException {
	    // TODO Auto-generated method stub
	    return super.getEntry(dictionaryCode, entryId, format);
	}
	
	@Override
	public String search(String dictionaryCode, String searchWord, Integer pageSize, Integer pageIndex) throws SkPublishAPIException {
	    // TODO Auto-generated method stub
	    return super.search(dictionaryCode, searchWord, pageSize, pageIndex);
	}

}
