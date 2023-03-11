package pl.nesoft.astar;

import java.util.Objects;

/**
 * @author Piotr Wysocki on 10.03.2023
 * @project a_star_pathfinding
 */
public class Node {
	private final Point position;
	private final Node parent;

	private int h;
	private int g;
	private int f;

	public Node(Point position, Node parent) {
		this.position = position;
		this.parent = parent;
	}

	public void calculateCost(Node parent, Point startPosition, Point targetPosition) {
		h = Math.abs(position.getX() - targetPosition.getX()) + Math.abs(position.getY() - targetPosition.getY());
		g = parent.g + Math.abs(position.getX() - startPosition.getX()) + Math.abs(position.getY() - startPosition.getY());
		f = g + h;
	}

	public Point getPosition() {
		return position;
	}

	public Node getParent() {
		return parent;
	}

	public int getH() {
		return h;
	}

	public int getG() {
		return g;
	}

	public float getTotalCost() {
		return f;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return position.equals(node.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position);
	}
}
