package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";
		Map<Integer, Author> autori = this.getAllAutori();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return autori.get(id);
			}
			conn.close();
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public Map<Integer, Author> getAllAutori(){
		
		final String sql = "SELECT * FROM author";
		
		Map<Integer, Author> autori = new HashMap<Integer, Author>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autori.put(autore.getId(), autore);
			}
			conn.close();
			return autori;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
		public Map<Integer, Paper> getAllArticoli(){

			final String sql = "SELECT * FROM paper";
			Map<Integer, Paper> articoli = new HashMap<>();

			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
							rs.getString("publication"), rs.getString("type"), rs.getString("types"));
					articoli.put(paper.getEprintid(), paper);
				}
				conn.close();
				return articoli;

			} catch (SQLException e) {
				 e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
		
	}
		
		public Map<Integer, Creator> getAllCreators(){

			final String sql = "SELECT * FROM creator";
			Map<Integer, Paper> articoli = this.getAllArticoli();
			Map<Integer, Author> autori = this.getAllAutori();
			Map<Integer, Creator> creatori = new HashMap<Integer, Creator>();

			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					Author a = autori.get(rs.getInt("authorid"));
					Paper p = articoli.get(rs.getInt("eprintid"));
					Creator c = null;
					if(!creatori.containsKey(p.getEprintid())) {
						c = new Creator(p, a);
						creatori.put(p.getEprintid(), c);
					}
					else {
						c = creatori.get(p.getEprintid());
						c.aggiungiAutore(a);
					}
				}
				conn.close();
				return creatori;

			} catch (SQLException e) {
				 e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
		}
}