package main.java.br.com.arida.ufc.mydbaas.util;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 21, 2013
 */
public class SendResquest {

	/**
	 * @param url - url to request
	 * @param params - parameters of form
	 * @param charset - encoding
	 * @return an HttpResponse object
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResponse postRequest(String url, List<NameValuePair> params, String charset) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		if (params != null) {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, charset);		
			httpPost.setEntity(formEntity);
		}				
		HttpResponse response = httpClient.execute(httpPost);
		return response;
	}
	
	public static String getJsonResult(HttpResponse response) throws IOException {
		HttpEntity resEntity = response.getEntity();
		resEntity = new BufferedHttpEntity(resEntity);
		String result = EntityUtils.toString(resEntity);
		EntityUtils.toString(resEntity);
		return result;
	}
}
