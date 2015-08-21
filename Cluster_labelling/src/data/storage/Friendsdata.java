
package data.storage;

import java.io.*;
import java.util.*;

public class Friendsdata {
	String current_location;
	String hometown;
	int numberofcolleges;
	ArrayList<String> colleges = new ArrayList<String>();
	public String getCurrent_location() {
		return current_location;
	}
	public void setCurrent_location(String current_location) {
		this.current_location = current_location;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public int getNumberofcolleges() {
		return numberofcolleges;
	}
	public void setNumberofcolleges(int numberofcolleges) {
		this.numberofcolleges = numberofcolleges;
	}
	public ArrayList<String> getColleges() {
		return colleges;
	}
	public void setColleges(ArrayList<String> colleges) {
		this.colleges = colleges;
	}
	
}
