package com.geoloc.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A backing bean for the main hotel search form. Encapsulates the criteria
 * needed to perform a hotel search.
 */
@XmlRootElement
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private String searchString;

	@XmlAttribute
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
