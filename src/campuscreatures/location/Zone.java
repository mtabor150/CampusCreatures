package campuscreatures.location;

/* Zones are either circles designated with a center and a radius
 * or they are convex quadrilaterals designated by 4 points.
 */

public class Zone {

	private Location barycenter = null;
	private Location[] points = null;
	private int numPoints = 0;
	private double radius = 0;
	private String zoneName;

	public Zone(String name, Location center, double rad) {
		zoneName = name;
		numPoints = 1;
		points = new Location[numPoints];
		points[0] = center;
		barycenter = center;
		radius = rad;
	}

	public Zone(String name, Location center, Location outerpoint) {
		zoneName = name;
		numPoints = 1;
		points = new Location[numPoints];
		points[0] = center;
		barycenter = center;
		radius = pointDistance(center, outerpoint);
	}

	public Zone(String name, Location alpha, Location beta, Location gamma,
			Location delta) {
		zoneName = name;
		numPoints = 4;
		Location temp[] = { alpha, beta, gamma, delta };
		points = temp;
	}

	public double inZone(Location point) {
		if (numPoints == 1) {
			if (pointDistance(points[0], point) <= radius) {
				return 0.0;
			}
		} else if (numPoints == 4) {
			
			double tri1 = inZoneErrorMargin(point,points[0],points[1],points[2]);
			double tri2 = inZoneErrorMargin(point,points[1],points[2],points[3]);
			double tri3 = inZoneErrorMargin(point,points[2],points[3],points[0]);
			double tri4 = inZoneErrorMargin(point,points[3],points[0],points[1]);
			
			return Math.min(Math.min(tri1, tri2), Math.min(tri3, tri4));
		}
		return 2;
	}
	
	//triangle
	public double inZoneErrorMargin(Location player, Location alpha, Location beta,
			Location gamma) {
		double playerArea = area(player, alpha, beta)
				+ area(player, alpha, gamma) + area(player, beta, gamma);
		double zoneArea = area(alpha, beta, gamma);
		System.out.println("playerArea = " + playerArea);
		System.out.println("zoneArea = " + zoneArea);
		return withinError(playerArea,zoneArea);
	}
	
	private double withinError(double A, double B) {
		double error =  Math.abs((A-B)/((A+B)/2));
		System.out.println("error = " + error);
		return error;
	}

	public String getZoneName() {
		return zoneName;
	}

	private double pointDistance(Location alpha, Location beta) {
		double dx = (alpha.getLatitude() - beta.getLatitude());
		double dy = (alpha.getLongitude() - beta.getLongitude());
		return Math.sqrt((dx * dx) + (dy * dy));
	}

	// triangle
	private double area(Location alpha, Location beta, Location gamma) {
		double a = Math.abs(alpha.getLatitude()*beta.getLongitude()) - Math.abs(beta.getLatitude()*alpha.getLongitude());
		double b = Math.abs(beta.getLatitude()*gamma.getLongitude()) - Math.abs(gamma.getLatitude()*beta.getLongitude());
		double c = Math.abs(gamma.getLatitude()*alpha.getLongitude()) - Math.abs(alpha.getLatitude()*gamma.getLongitude());
		double area = (Math.abs((a + b + c)) / 2);
		System.out.println("area is = " + area);
		return area;
	}

}