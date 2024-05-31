package model;

import org.ektorp.support.CouchDbDocument;

public class Person extends CouchDbDocument {

	private static final long serialVersionUID = 1L;
	private String name;
	private long dob;
	private String image;

	public Person() {
	}

	public Person(String name, long dob, String image) {
		this.name = name;
		this.dob = dob;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDob() {
		return dob;
	}

	public void setDob(long dob) {
		this.dob = dob;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
