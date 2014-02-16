package com.geoloc.model;

/**
 * @author KANISKA MANDAL
 *
 */
public class GeoLocationData {

	
	
	/**
	 * <?xml version="1.0" encoding="UTF-8"?>
	 * <ipinfo xmlns="http://data.quova.com/1" xmlns:xs="http://www.w3.org/2001/XMLSchema-instance">
	 * <ip_address>74.125.239.137</ip_address><ip_type>Mapped</ip_type>
	 * <Network><organization>google inc.</organization>
	 * <carrier>google inc.</carrier><asn>15169</asn><connection_type>ocx</connection_type>
	 * <line_speed>high</line_speed><ip_routing_type>fixed</ip_routing_type><Domain>
	 * <tld>net</tld><sld>1e100</sld></Domain></Network><Location>
	 * <continent>north america</continent><latitude>37.41916</latitude><longitude>-122.07541</longitude>
	 * <CountryData><country>united states</country><country_code>us</country_code><country_cf>99</country_cf></CountryData>
	 * <region>southwest</region><StateData><state>california</state><state_code>ca</state_code><state_cf>98</state_cf></StateData>
	 * <dma>807</dma><msa>41940</msa><CityData><city>mountain view</city><postal_code>94043</postal_code>
	 * <time_zone>-8</time_zone><area_code>650</area_code><city_cf>98</city_cf></CityData></Location></ipinfo>
	 * 
	 */

	
	public static class FieldNames {
		public static final String CITY = "City";
		public static final String STATE = "State";
		public static final String COUNTRY = "Country";
		public static final String POSTAL = "Postal_code";
		public static final String LAT = "latitude";
		public static final String LONG = "longitude";
		
	}
}
