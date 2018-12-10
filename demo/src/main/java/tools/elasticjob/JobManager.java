package tools.elasticjob;

import java.util.Map;

public class JobManager {
	private Map<String, String> nameClassMap;

	public Map<String, String> getNameClassMap() {
		return nameClassMap;
	}

	public void setNameClassMap(Map<String, String> nameClassMap) {
		this.nameClassMap = nameClassMap;
	}
	
	public String getClass(String name) {
		return nameClassMap.get(name);
	}
}
