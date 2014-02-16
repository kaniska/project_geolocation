package com.geoloc.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class ViewModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ModelIterator modelIterator;
	
    public ViewModel(List<String> columnData) {
    	this.modelIterator = new ModelIterator(columnData);
    }
    
    
public String getNextValue() {
	if(modelIterator.hasNext()){
		   return modelIterator.next().toString();
		}else{
			return "Unknown";
		}
	}
 
private class ModelIterator implements Iterator {

		private int index = 0; 
		private int limit = 0; 
		private List<String> valueList = null;

		public ModelIterator(List<String> columnData) {
			limit = columnData.size();
	    	this.valueList = columnData;
		}

		@Override
		public boolean hasNext() {
			return index < limit;
		}

		@Override
		public Object next() {
			return valueList.get(index++);
		}

		@Override
		public void remove() {
			// NO OP		
		}
		 
	 }

}
