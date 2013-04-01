import java.awt.geom.Point2D;

public class SomeObject {
	private Point2D position;

	public SomeObject(Point2D position) {
		this.position = position;
	}

	public double centerToCenter(SomeObject other) {
		return this.position.distance(other.position);
	}

	public static void main(String[] args) {
		SomeObject ball = new SomeObject(new Point2D.Double(0.0, 0.0));
		SomeObject biggerBall = new SomeObject(new Point2D.Double(4.0, 3.0));
		System.out.println(biggerBall.centerToCenter(ball));
		
	}
}
