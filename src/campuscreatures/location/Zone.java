package campuscreatures.location;


public class Zone {
	
	private String type = null; 
	private Location[] points = null;
	private int numPoints = 0;
	private double radius = 0;
	
	public Zone(Location center, double rad){
		type = "circle";
		points = new Location[1];
		points[0] = center; 
		numPoints = 1;
		radius = rad;
	}
	
	public Zone(Location alpha, Location beta, Location gamma, Location delta){
		type = "quadrilateral";
		points = new Location[4];
		points[0] = alpha;
		points[1] = beta;
		points[2] = gamma;
		points[3] = delta;
		numPoints = 4;
	}
	
	private double pointDistance(Location alpha, Location beta){
		//TODO distance formula	
	}
	
	
	public boolean inZone(){
		//TODO finish the distance formula and create method to check if a point
		//is in the zone
	}
	
}
