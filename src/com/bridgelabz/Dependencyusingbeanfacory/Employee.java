package com.bridgelabz.springsexamples;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Employee {

	private int id;
	private String name;
	private Map<String, String> map;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee() {
		System.out.println("default constructor");
	}

	public Employee(int id) {
		this.id = id;
	}

	public Employee(String name) {
		this.name = name;
	}

	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Employee(int id, String name, Map<String, String> answers) {
		this.id = id;
		this.name = name;
		this.map = answers;
	}

	void show() {
		System.out.println(id + " - " + name);
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	void display() {
		System.out.println("dispaly is called");
	    Set<Entry<String, String>> set=map.entrySet();  
	    Iterator<Entry<String, String>> itr=set.iterator();  
	    while(itr.hasNext()){  
	        Entry<String,String> entry=itr.next();  
	        System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());  
	    }  
   }
}
