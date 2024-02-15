package co.com.jcd.inventory.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseRest {
	
	private List<Map<String, String>> metadata = new ArrayList<>();

	public List<Map<String, String>> getMetadata() {
		return metadata;
	}

	public void setMetadata(String type, String code, String date) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("code", code);
		map.put("date", date);
		this.metadata.add(map);
	}
	
	

}
