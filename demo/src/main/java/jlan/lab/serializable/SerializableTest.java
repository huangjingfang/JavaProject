package jlan.lab.serializable;

import java.util.HashMap;

import com.google.gson.Gson;

public class SerializableTest {
	public static void main(String[] args) {
		Gson gson = new Gson();
		SerializableTest sTest = new SerializableTest();
		sTest.setName("kolgan");
		sTest.setPassword("123456");
		sTest.setNote("love yan");
		System.out.println(gson.toJson(sTest));
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("kolgan", "123456");
		System.out.println(gson.toJson(hashMap));
	}
	
	
	private String name;
	private String password;
	transient private String note;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
