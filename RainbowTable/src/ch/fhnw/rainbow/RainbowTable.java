package ch.fhnw.rainbow;

import java.util.HashMap;
import java.util.Map;

public class RainbowTable {
	//Rainbow Table with the endpassword as key and first password as value
	private Map<String, String> table;
	
	public RainbowTable(){
		table = new HashMap<String, String>();
	}
	
	public void addPair(String key, String value){
		table.put(key, value);
	}
	
	public String getStart(String key){
		return table.get(key);
	}
	
	public boolean containsKey(String key){
		return table.containsKey(key);
	}
	
	public String toString(){
		return table.toString();
	}

}
