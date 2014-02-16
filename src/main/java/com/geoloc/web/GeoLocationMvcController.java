package com.geoloc.web;

import java.net.InetAddress;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geoloc.model.ViewModel;
import com.geoloc.model.SearchCriteria;
import com.geoloc.service.GeoLocService;

@Controller
public class GeoLocationMvcController {

	@Inject
	private GeoLocService geoLocService;

	private Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm(Model model) {
		SearchCriteria searchCriteria = new SearchCriteria();
		List<ViewModel> entityList = null;
		try {
			searchCriteria.setSearchString(InetAddress.getLocalHost().getHostAddress());
			entityList = geoLocService
			  .findGeoLocationInfo(searchCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		List<String> columnLabelList = geoLocService.findColumnList();
		model.addAttribute("columnLabelList", columnLabelList);

		model.addAttribute("searchCriteria", searchCriteria); // adding in
																// model
		model.addAttribute("entityList", entityList);
		return "home";
	}

	@RequestMapping(value = "/ip", method = RequestMethod.GET)
	public String list(SearchCriteria searchCriteria, Model model) {
		
		  List<ViewModel> entityList = geoLocService.findGeoLocationInfo(searchCriteria);
		 

		List<String> columnLabelList = geoLocService.findColumnList();
		model.addAttribute("columnLabelList", columnLabelList);

		model.addAttribute("searchCriteria", searchCriteria);
		model.addAttribute("entityList", entityList);
		return "home"; // users/list
	}

}
