package campuscreatures.location;

/* Zones are either circles designated with a center and a radius
 * or they are convex quadrilaterals designated by 4 points.
 */

public class Zone {
	
	private Location barycenter = null;
	private Location[] points = null;
	private int numPoints = 0;
	private double radius = 0;
	String zoneName;
	
	public Zone(String name, Location center, double rad) {
		this.zoneName = name;
		numPoints = 1;
		points = new Location[numPoints];
		points[0] = center;
		barycenter = center;
		radius = rad;
	}
	
	public Zone(String name, Location center, Location outerpoint){
		this.zoneName = name;
		numPoints = 1;
		points = new Location[numPoints];
		points[0] = center;
		barycenter = center;
		radius = pointDistance(center, outerpoint);
	}

	public Zone(String name, Location alpha, Location beta, Location gamma, Location delta) {
		this.zoneName = name;
		numPoints = 4;
		Location temp[] = {alpha, beta, gamma, delta};
		points = temp;
//		setBarycenter(alpha, beta, gamma, delta);
//		Location temp[] = {alpha, beta, gamma, delta};
//		
//		for(int i =0; i<4; i++){
//			double latDiff = temp[i].getLatitude() - barycenter.getLatitude(); 
//			double lonDiff = temp[i].getLongitude() - barycenter.getLongitude();
//			
//			if((latDiff > 0 && lonDiff > 0)||(latDiff > 0 && lonDiff == 0)){
//				points[0] = temp[i];
//			}else if((latDiff < 0 && lonDiff > 0)||(latDiff == 0 && lonDiff > 0)){
//				points[1] = temp[i];
//			}else if((latDiff < 0 && lonDiff < 0)||(latDiff < 0 && lonDiff == 0)){
//				points[2] = temp[i];
//			}else{
//				points[3] = temp[i];
//			}
//			
//		}
	}
	
	
	public boolean inZone(Location point) {

		boolean inArea = false;

		if (numPoints == 1) {
			if (pointDistance(points[0], point) <= radius) {
				inArea = true;
			}
		} else if (numPoints == 4){
			double zoneArea = area(points[0],points[1],points[2],points[3]);
			double tri1 = area(point, points[0], points[1]);
			double tri2 = area(point, points[1], points[2]);
			double tri3 = area(point, points[2], points[3]);
			double tri4 = area(point, points[3], points[0]);
			
			if (zoneArea == (tri1+tri2+tri3+tri4)){
				inArea = true;
			} 
		}

		return inArea;
	}
	
	public boolean inZone(Location player, Location point1, Location point2, Location point3){
		return true;
	}
	
	public String getZoneName(){
		return zoneName;
	}
	
//	public String getName() {
//		return this.zoneName;
//	}
	
//	private void setBarycenter(Location alpha, Location beta, Location gamma, Location delta){
//		double lat = (alpha.getLatitude()+beta.getLatitude()+gamma.getLatitude()+delta.getLatitude())/4;
//		double lon = (alpha.getLongitude()+beta.getLongitude()+gamma.getLongitude()+delta.getLongitude())/4;
//		barycenter = new Location(lat, lon);
//	}

	private double pointDistance(Location alpha, Location beta) {
		double dx = (alpha.getLatitude() - beta.getLatitude());
		double dy = (alpha.getLongitude() - beta.getLongitude());
		return Math.sqrt((dx * dx) + (dy * dy));
	}

	// triangle
	private double area(Location alpha, Location beta, Location gamma) {
		double a, b, c;
		a = alpha.getLatitude() * (beta.getLongitude() - gamma.getLongitude());
		b = beta.getLatitude() * (gamma.getLongitude() - alpha.getLongitude());
		c = gamma.getLatitude() * (alpha.getLongitude() - beta.getLongitude());

		return ((a + b + c) / 2);
	}

	// quadrilateral
//	private double area(Location alpha, Location beta, Location gamma,
//			Location delta) {
//		return ((area(alpha, gamma, beta))+(area(alpha, gamma, delta)));
//	}
	
}