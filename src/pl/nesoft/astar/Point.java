package pl.nesoft.astar;

import java.util.Objects;

/**
 * @author Piotr Wysocki on 10.03.2023
 * @project a_star_pathfinding
 */
public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point point = (Point) o;
		return x == point.x && y == point.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
