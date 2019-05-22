package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

public class Creator {
	
	private List<Author> autori = new ArrayList<Author>();
	private Paper paper;
	
	public Creator(Paper paper, Author author) {
		this.paper=paper;
		autori.add(author);
	}

	public List<Author> getAutori() {
		return autori;
	}

	public void setAutori(List<Author> autori) {
		this.autori = autori;
	}
	
	public void aggiungiAutore(Author a) {
		autori.add(a);
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	@Override
	public String toString() {
		return ""+paper;
	}
	
	
}
