package campuscreatures.location;

public class Location {

	private double latitude;
	private double longitude;

	public Location(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}

	public void setLocation(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
