package com.geoloc.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.geoloc.model.GeoLocationData;
import com.geoloc.model.ViewModel;
import com.geoloc.model.SearchCriteria;

@Service("geoLocService")
public class GeoLocServiceImpl implements GeoLocService {
	
	private final DocumentBuilderFactory builderFactory =
	        DocumentBuilderFactory.newInstance();

	public java.util.List<ViewModel> findGeoLocationInfo(SearchCriteria criteria) {
		
		 String service = "http://api.neustar.biz/ipi/gpp/";
		 String version = "v1/";
		String method = "ipinfo/";
		 String apikey = "220.1.52fef83fe4b09f3f3f8cfabe.UMAybbH5i";
		String secret = "gfXBWWmH";
		String ip = null;
		Content content = null;
		try {
			if(criteria.getSearchString() == null) {
				 ip = InetAddress.getLocalHost().getHostAddress();
			}else {
				ip = criteria.getSearchString();
			}

			MessageDigest md = MessageDigest.getInstance("MD5");
			long timeInSeconds = (long) (System.currentTimeMillis() / 1000);
			String input = apikey + secret + timeInSeconds;
			md.update(input.getBytes());
			String sig = String.format("%032x", new BigInteger(1, md.digest()));

			String url = service + version + method + ip + "?apikey=" + apikey + "&sig=" + sig + "&format=xml";
			
			content = Request.Get(url).execute()
					.returnContent();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buildmodel(content.asString());
	}
	
	private List<ViewModel> buildmodel(String xml) {
		List<String> valueList = new ArrayList<String>(1);
		List<ViewModel> modelList = new ArrayList<ViewModel>(1);
		try {
			DocumentBuilder builder =  builderFactory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
			XPath xPath =  XPathFactory.newInstance().newXPath();
/*			
			if(null != document) {
			    NodeList nodeList = document.getChildNodes();
			    for (int i = 0;null!=nodeList && i < nodeList.getLength(); i++) {
			        Node node = nodeList.item(i);
			        if(node.getNodeType() == Node.ELEMENT_NODE)
			            System.out.println(nodeList.item(i).getNodeName() + " : " + nod.getFirstChild().getNodeValue()); 
			    }
			}*/
			
			
			Node country = (Node) xPath.compile("//country").evaluate(document, XPathConstants.NODE);
			if(country != null)
				valueList.add(country.getTextContent());
			
			Node state =  (Node) xPath.compile("//state").evaluate(document, XPathConstants.NODE);
			if(state != null)
				valueList.add(state.getTextContent());
			
			Node city =   (Node) xPath.compile("//city").evaluate(document, XPathConstants.NODE);
			if(city != null)
				valueList.add(city.getTextContent());
			
			Node postal =   (Node) xPath.compile("//postal_code").evaluate(document, XPathConstants.NODE);
			if(postal != null)
				valueList.add(postal.getTextContent());
			
			Node lat =   (Node) xPath.compile("//latitude").evaluate(document, XPathConstants.NODE);
			if(lat != null)
				valueList.add(lat.getTextContent());
			
			Node longitudde =   (Node) xPath.compile("//longitude").evaluate(document, XPathConstants.NODE);
			if(longitudde != null)
				valueList.add(longitudde.getTextContent());
			
		} catch (Exception e) {
			//log.error(e, entityXML, null);
		}
		ViewModel viewModel = new ViewModel(valueList);
		modelList.add(viewModel);
		return modelList;
	}
	
	
	public List<String> findColumnList() {
		List<String> columnList = new ArrayList<String>();
		columnList.add(GeoLocationData.FieldNames.COUNTRY);
		columnList.add(GeoLocationData.FieldNames.STATE);
		columnList.add(GeoLocationData.FieldNames.CITY);
		columnList.add(GeoLocationData.FieldNames.POSTAL);
		columnList.add(GeoLocationData.FieldNames.LAT);
		columnList.add(GeoLocationData.FieldNames.LONG);
		
		return columnList;
	}
}
