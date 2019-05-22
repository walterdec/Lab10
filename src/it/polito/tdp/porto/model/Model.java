package it.polito.tdp.porto.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	Graph<Author, DefaultEdge> grafo;
	PortoDAO dao;
	
	public Model() {
		dao = new PortoDAO();
	}
	
	public void generaGrafo() {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(grafo, dao.getAllAutori().values());
		
		for(Creator c : dao.getAllCreators().values()) {
			for(Author a1 : c.getAutori()) {
				for(Author a2 : c.getAutori()) {
					if(a1 != a2) {
						grafo.addEdge(a1, a2);
					}
				}
			}
		}
	}
	
	public List<Author> getNeighbors(Author a){
		if(grafo == null) {
			this.generaGrafo();
		}
		return Graphs.neighborListOf(grafo, a);
	}

	public List<Author> getCamminoMinimo(Author a1, Author a2) {
		if(grafo==null) {
			this.generaGrafo();
		}
		DijkstraShortestPath<Author, DefaultEdge> dijkstra = new DijkstraShortestPath<>(this.grafo);
		GraphPath<Author, DefaultEdge> path = dijkstra.getPath(a1, a2);
		return path.getVertexList();
	}
	
}
