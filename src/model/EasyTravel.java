package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EasyTravel {

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
		return points;
	}
}
