package campuscreatures.location;

/* Zones are either circles designated with a center and a radius
 * or they are convex quadrilaterals designated by 4 points.  If the
 * zone is a quadrilateral the constructor will sort the points so 
 * that the two points farthest from each other are in the point array
 * at index 0 and 2.  This sorts the points into order.
 */

public class Zone {

	private Location[] points = null;
	private int numPoints = 0;
	private double radius = 0;

	public Zone(Location center, double rad) {
		numPoints = 1;
		points = new Location[numPoints];
		points[0] = center;
		radius = rad;
	}

	public Zone(Location alpha, Location beta, Location gamma, Location delta) {
		numPoints = 4;
		points = new Location[numPoints];

		double ab, ac, ad, bc, bd, cd;
		ab = pointDistance(alpha, beta);
		ac = pointDistance(alpha, gamma);
		ad = pointDistance(alpha, delta);
		bc = pointDistance(beta, gamma);
		bd = pointDistance(beta, delta);
		cd = pointDistance(gamma, delta);

		double[] lines = {ab, ac, ad, bc, bd, cd};

		int maxIndex = 0;
		for (int i = 0; i < 6; i++) {
			double newnumber = lines[i];
			if (newnumber > lines[maxIndex]) {
				maxIndex = i;
			}
		}

		//put end points of the longest line (a diagonal) in spaces 0 and 2
		switch (maxIndex) {
			//ab
			case 0:
				points[0] = alpha;
				points[1] = gamma;
				points[2] = beta;
				points[3] = delta;
				break;
			//ac
			case 1:
				points[0] = alpha;
				points[1] = beta;
				points[2] = gamma;
				points[3] = delta;
				break;
			//ad
			case 2:
				points[0] = alpha;
				points[1] = beta;
				points[2] = delta;
				points[3] = gamma;
				break;
			//bc
			case 3:
				points[0] = beta;
				points[1] = alpha;
				points[2] = gamma;
				points[3] = delta;
				break;
			//bd
			case 4:
				points[0] = beta;
				points[1] = alpha;
				points[2] = delta;
				points[3] = gamma;
				break;
			//cd
			case 5:
				points[0] = gamma;
				points[1] = alpha;
				points[2] = delta;
				points[3] = beta;
				break;
			default:
				break;
		}
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

	// quadrilateral
	private double area(Location alpha, Location beta, Location gamma,
			Location delta) {
		return ((area(alpha, gamma, beta))+(area(alpha, gamma, delta)));
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

}