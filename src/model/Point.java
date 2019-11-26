package model;

public class Point {

	private String name;
	private double latitude;
	private double longitude;
	private int id;
	
	public Point(String name, double latitude, double longitude, int id) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.setId(id);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		String info = name + " ; Latitud: " +latitude + "  Longitud: "+ longitude;
		return info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
