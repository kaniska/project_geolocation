/**
 * 
 */
package com.geoloc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.geoloc.model.SearchCriteria;
import com.geoloc.model.ViewModel;
import com.geoloc.service.GeoLocService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author KANISKA MANDAL
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/root-context.xml"})

public class GeoLocationTest {

	
	  @Autowired
	    protected GeoLocService service;

	    public GeoLocationTest() {
	    }

	    @Test
	    public void getGeoLocInfo() {
	    	
	    	SearchCriteria criteria = new SearchCriteria();
	    	criteria.setSearchString("74.125.239.33");
	        
	    	java.util.List<ViewModel> modelList = service.findGeoLocationInfo(criteria);
	    	
	        Assert.assertEquals(1, modelList.size());
	        
	        ViewModel datalist = modelList.get(0);
	        
	        Assert.assertEquals("united states", datalist.getNextValue());
	    }
}
