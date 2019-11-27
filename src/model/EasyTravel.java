package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import structures.AdjacencyListGraph;

public class EasyTravel {

	public static AdjacencyListGraph<Point> adjacencyListGraph;
	public static ArrayList<Point> points;

	public ArrayList<Point> load(String path) {
		points = new ArrayList<>();
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String sCurrentLine;
			int cont = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] parts = sCurrentLine.split(",");
				Point p = new Point(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), cont);
				points.add(p);
				cont++;
			}
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		addVertex(points);
		return points;
	}

	public void addVertex(ArrayList<Point> points) {
		adjacencyListGraph = new AdjacencyListGraph<>(false);
		for (int i = 0; i < points.size(); i++) {
			adjacencyListGraph.addVertex(points.get(i));
		}
		String all = "Cali-Jamundi,Cali-Candelaria,Cali-La Cumbre,Cali-El Cerrito,Candelaria-Florida,Jamundi-Florida,La Cumbre-El Cerrito"
				+ ",El Cerrito-Candelaria,La Cumbre-Dagua,Dagua-Buenaventura,La Cumbre-Darien,Dagua-Darien,Buenaventura-Darien,Darien-Buga,Buga-El Cerrito,Darien-Bugalagrande"
				+ ",Buga-Bugalagrande,Bugalagrande-Bolivar,Bolivar-El Dovio,El Dovio-El Cairo,El Cairo-El Aguila,El Aguila-Cartago,Cartago-Alcala,Alcala-Caicedonia,Caicedonia-Bugalagrande"
				+ ",Bolivar-Caicedonia,El Dovio-Caicedonia,Caicedonia-Cartago,El Dovio-Cartago,El Cairo-Cartago";
		String[] relations = all.split(",");
		for (int i = 0; i < relations.length; i++) {
			String[] divide = relations[i].split("-");
			int one = getID(divide[0]);
			int two = getID(divide[1]);
			int distance = CalculateDistance(points.get(one).getLatitude(), points.get(one).getLongitude(),points.get(two).getLatitude(), points.get(two).getLongitude());
			adjacencyListGraph.addEdge(points.get(one), points.get(two), distance);
		}
	}

	public int CalculateDistance(double startLat, double startLong, double endLat, double endLong) {
		double distance;
		/*
		 * This code has been adapted from the original in:
		 * https://github.com/jasonwinn/haversine/blob/master/Haversine.java
		 */

		int EARTH_RADIUS = 6371; // Approx Earth radius in KM

		double dLat = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		distance = EARTH_RADIUS * c;

		return (int) distance;
	}

	public double haversin(double val) {
		/*
		 * This code has been adapted from the original in:
		 * https://github.com/jasonwinn/haversine/blob/master/Haversine.java
		 */
		return Math.pow(Math.sin(val / 2), 2);
	}

	public AdjacencyListGraph<Point> getList() {
		return adjacencyListGraph;
	}

	public int getID(String name) {
		int id = -1;
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).getName().equals(name))
				id = points.get(i).getId();
		}
		return id;
	}

}
