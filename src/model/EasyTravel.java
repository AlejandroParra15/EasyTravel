package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import structures.AdjacencyListGraph;

public class EasyTravel {

	public static AdjacencyListGraph<Point> adjacencyListGraph;

	public ArrayList<Point> load(String path) {
		ArrayList<Point> points = new ArrayList<>();
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
			e.printStackTrace();
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
		adjacencyListGraph.addEdge(points.get(0), points.get(15), CalculateDistance(points.get(0).getLatitude(),
				points.get(0).getLongitude(), points.get(15).getLatitude(), points.get(15).getLongitude()));
		adjacencyListGraph.addEdge(points.get(0), points.get(6), CalculateDistance(points.get(0).getLatitude(),
				points.get(0).getLongitude(), points.get(6).getLatitude(), points.get(6).getLongitude()));
		adjacencyListGraph.addEdge(points.get(0), points.get(16), CalculateDistance(points.get(0).getLatitude(),
				points.get(0).getLongitude(), points.get(16).getLatitude(), points.get(16).getLongitude()));
		adjacencyListGraph.addEdge(points.get(7), points.get(14), CalculateDistance(points.get(7).getLatitude(),
				points.get(7).getLongitude(), points.get(14).getLatitude(), points.get(14).getLongitude()));
		adjacencyListGraph.addEdge(points.get(16), points.get(14), CalculateDistance(points.get(16).getLatitude(),
				points.get(16).getLongitude(), points.get(14).getLatitude(), points.get(14).getLongitude()));
		adjacencyListGraph.addEdge(points.get(17), points.get(12), CalculateDistance(points.get(17).getLatitude(),
				points.get(17).getLongitude(), points.get(12).getLatitude(), points.get(12).getLongitude()));
		adjacencyListGraph.addEdge(points.get(12), points.get(7), CalculateDistance(points.get(12).getLatitude(),
				points.get(12).getLongitude(), points.get(7).getLatitude(), points.get(7).getLongitude()));
		adjacencyListGraph.addEdge(points.get(17), points.get(9), CalculateDistance(points.get(17).getLatitude(),
				points.get(17).getLongitude(), points.get(9).getLatitude(), points.get(9).getLongitude()));
		adjacencyListGraph.addEdge(points.get(9), points.get(3), CalculateDistance(points.get(9).getLatitude(),
				points.get(9).getLongitude(), points.get(3).getLatitude(), points.get(3).getLongitude()));
		adjacencyListGraph.addEdge(points.get(17), points.get(6), CalculateDistance(points.get(17).getLatitude(),
				points.get(17).getLongitude(), points.get(6).getLatitude(), points.get(6).getLongitude()));
		adjacencyListGraph.addEdge(points.get(9), points.get(6), CalculateDistance(points.get(9).getLatitude(),
				points.get(9).getLongitude(), points.get(6).getLatitude(), points.get(6).getLongitude()));
		adjacencyListGraph.addEdge(points.get(3), points.get(6), CalculateDistance(points.get(3).getLatitude(),
				points.get(3).getLongitude(), points.get(6).getLatitude(), points.get(6).getLongitude()));
		adjacencyListGraph.addEdge(points.get(6), points.get(15), CalculateDistance(points.get(6).getLatitude(),
				points.get(6).getLongitude(), points.get(15).getLatitude(), points.get(15).getLongitude()));
		adjacencyListGraph.addEdge(points.get(15), points.get(12), CalculateDistance(points.get(15).getLatitude(),
				points.get(15).getLongitude(), points.get(12).getLatitude(), points.get(12).getLongitude()));
		adjacencyListGraph.addEdge(points.get(6), points.get(4), CalculateDistance(points.get(6).getLatitude(),
				points.get(6).getLongitude(), points.get(4).getLatitude(), points.get(4).getLongitude()));
		adjacencyListGraph.addEdge(points.get(15), points.get(4), CalculateDistance(points.get(15).getLatitude(),
				points.get(15).getLongitude(), points.get(4).getLatitude(), points.get(4).getLongitude()));
		adjacencyListGraph.addEdge(points.get(4), points.get(2), CalculateDistance(points.get(4).getLatitude(),
				points.get(4).getLongitude(), points.get(2).getLatitude(), points.get(2).getLongitude()));
		adjacencyListGraph.addEdge(points.get(2), points.get(13), CalculateDistance(points.get(2).getLatitude(),
				points.get(2).getLongitude(), points.get(13).getLatitude(), points.get(13).getLongitude()));
		adjacencyListGraph.addEdge(points.get(13), points.get(11), CalculateDistance(points.get(13).getLatitude(),
				points.get(13).getLongitude(), points.get(11).getLatitude(), points.get(11).getLongitude()));
		adjacencyListGraph.addEdge(points.get(11), points.get(10), CalculateDistance(points.get(11).getLatitude(),
				points.get(11).getLongitude(), points.get(10).getLatitude(), points.get(12).getLongitude()));
		adjacencyListGraph.addEdge(points.get(10), points.get(8), CalculateDistance(points.get(10).getLatitude(),
				points.get(10).getLongitude(), points.get(8).getLatitude(), points.get(8).getLongitude()));
		adjacencyListGraph.addEdge(points.get(8), points.get(1), CalculateDistance(points.get(8).getLatitude(),
				points.get(8).getLongitude(), points.get(1).getLatitude(), points.get(1).getLongitude()));
		adjacencyListGraph.addEdge(points.get(1), points.get(5), CalculateDistance(points.get(1).getLatitude(),
				points.get(1).getLongitude(), points.get(5).getLatitude(), points.get(5).getLongitude()));
		adjacencyListGraph.addEdge(points.get(5), points.get(4), CalculateDistance(points.get(5).getLatitude(),
				points.get(5).getLongitude(), points.get(4).getLatitude(), points.get(4).getLongitude()));
		adjacencyListGraph.addEdge(points.get(2), points.get(5), CalculateDistance(points.get(2).getLatitude(),
				points.get(2).getLongitude(), points.get(5).getLatitude(), points.get(5).getLongitude()));
		adjacencyListGraph.addEdge(points.get(13), points.get(5), CalculateDistance(points.get(13).getLatitude(),
				points.get(13).getLongitude(), points.get(5).getLatitude(), points.get(5).getLongitude()));
		adjacencyListGraph.addEdge(points.get(5), points.get(8), CalculateDistance(points.get(5).getLatitude(),
				points.get(5).getLongitude(), points.get(8).getLatitude(), points.get(8).getLongitude()));
		adjacencyListGraph.addEdge(points.get(13), points.get(8), CalculateDistance(points.get(13).getLatitude(),
				points.get(13).getLongitude(), points.get(8).getLatitude(), points.get(8).getLongitude()));
		adjacencyListGraph.addEdge(points.get(11), points.get(8), CalculateDistance(points.get(11).getLatitude(),
				points.get(11).getLongitude(), points.get(8).getLatitude(), points.get(8).getLongitude()));
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

}
