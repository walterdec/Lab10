package it.polito.tdp.porto.model;

public class Paper {

	private int eprintid;
	private String title;
	private String issn;
	private String publication;
	private String type;
	private String types;

	public Paper(int eprintid, String title, String issn, String publication, String type, String types) {
		this.eprintid = eprintid;
		this.title = title;
		this.issn = issn;
		this.publication = publication;
		this.type = type;
		this.types = types;
	}

	public int getEprintid() {
		return eprintid;
	}

	public void setEprintid(int eprintid) {
		this.eprintid = eprintid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String pubblication) {
		this.publication = pubblication;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return ""+eprintid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eprintid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paper other = (Paper) obj;
		if (eprintid != other.eprintid)
			return false;
		return true;
	}
	
	

}
