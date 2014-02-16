package com.geoloc.service;

import java.util.List;

import com.geoloc.model.ViewModel;
import com.geoloc.model.SearchCriteria;

public interface GeoLocService {
	/**
	 * 
	 * @param criteria
	 * @return
	 */
	List<ViewModel> findGeoLocationInfo(SearchCriteria criteria);

	List<String> findColumnList();
}