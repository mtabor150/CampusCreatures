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

	public boolean inZone(Location point) {
		if (numPoints == 1) {
			if (pointDistance(points[0], point) <= radius) {
				return true;
			}
		} else if (numPoints == 4) {
			
			boolean tri1 = inZone(point,points[0],points[1],points[2]);
			boolean tri2 = inZone(point,points[1],points[2],points[3]);
			boolean tri3 = inZone(point,points[2],points[3],points[0]);
			boolean tri4 = inZone(point,points[3],points[0],points[1]);
			
			if (tri1 || tri2 || tri3 || tri4) {
				return true;
			}
		}
		return false;
	}
	
	//triangle
	public boolean inZone(Location player, Location alpha, Location beta,
			Location gamma) {
		double playerArea = area(player, alpha, beta)
				+ area(player, alpha, gamma) + area(player, beta, gamma);
		double zoneArea = area(alpha, beta, gamma);

		if (playerArea == zoneArea) {
			return true;
		} else {
			return false;
		}
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
		double a, b, c;
		a = alpha.getLatitude() * (beta.getLongitude() - gamma.getLongitude());
		b = beta.getLatitude() * (gamma.getLongitude() - alpha.getLongitude());
		c = gamma.getLatitude() * (alpha.getLongitude() - beta.getLongitude());

		return ((a + b + c) / 2);
	}

}