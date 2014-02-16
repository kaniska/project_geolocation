/**
 * 
 */
package com.geoloc.cli;

//IP Intelligence REST Web Service
//@author www.quova.com
//Copyright 2010 Quova, Inc.
//This example illustrates how to execute a web service request via HTTP GET. 08
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

public class GeoLocationClient {

	public static String service = "http://api.neustar.biz/ipi/gpp/";
	public static String version = "v1/";
	public static String method = "ipinfo/";
	public static String apikey = "220.1.52fef83fe4b09f3f3f8cfabe.UMAybbH5i";
	public static String secret = "gfXBWWmH";

	// Shared Secret
	public static void main(String[] args) throws Exception {
		//String ip = InetAddress.getLocalHost().getHostAddress();
		String ip = "74.125.239.137";

		MessageDigest md = MessageDigest.getInstance("MD5");
		long timeInSeconds = (long) (System.currentTimeMillis() / 1000);
		String input = apikey + secret + timeInSeconds;
		md.update(input.getBytes());
		String sig = String.format("%032x", new BigInteger(1, md.digest()));

		String url = service + version + method + ip + "?apikey=" + apikey + "&sig=" + sig + "&format=xml";
		System.out.println("URL=" + url);

	/*	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("apikey", apikey));
		urlParameters.add(new BasicNameValuePair("sig", sig));
		urlParameters.add(new BasicNameValuePair("format", "xml"));*/

		Content content = Request.Get(url).execute()
				.returnContent();
		System.out.println(content);
	}
}