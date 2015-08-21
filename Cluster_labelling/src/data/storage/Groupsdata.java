package data.storage;

import java.util.ArrayList;

public class Groupsdata {
	ArrayList<String> members = new ArrayList<String>();
	String description;
	public ArrayList<String> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
