package pl.nesoft.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static pl.nesoft.astar.AStar.MAP_HEIGHT;
import static pl.nesoft.astar.AStar.MAP_WIDTH;
import static pl.nesoft.astar.AStar.WORLD_MAP;

/**
 * @author Piotr Wysocki on 10.03.2023
 * @project a_star_pathfinding
 */
public class PathFinding {

	private final List<Node> notVisited = new ArrayList<>();
	private final List<Node> visited = new ArrayList<>();

	public List<Node> findPath(Point startPosition, Point targetPosition) {
		long startMs = System.currentTimeMillis();
		Node startNode = new Node(startPosition, null);
		notVisited.add(startNode);

		while (!notVisited.isEmpty()) {
			final var currentNode = findNodeWithLowestF();
			notVisited.remove(currentNode);

			for (Node successor : getSuccessors(currentNode)) {
				if (!isWalkable(successor)) {
					continue;
				}

				if (successor.getPosition().equals(targetPosition)) {
					System.out.println("findPath executed in "+ (System.currentTimeMillis() - startMs)+ " ms");
					return createPath(currentNode, targetPosition);
				}

				successor.calculateCost(currentNode, startPosition, targetPosition);

				if (notVisitedNodeWithSamePositionAndLowerFExists(successor)) {
					continue;
				}
				if (visitedNodeWithSamePositionAndLowerFExists(successor)) {
					continue;
				}
				notVisited.add(successor);
			}

			visited.add(currentNode);
		}
		return Collections.emptyList();
	}

	private boolean isWalkable(Node successor) {
		return WORLD_MAP[successor.getPosition().getY()][successor.getPosition().getX()] != 1;
	}

	private boolean notVisitedNodeWithSamePositionAndLowerFExists(Node successor) {
		for (Node node : notVisited) {
			final var nodeWithSamePositionExists = node.getPosition().equals(successor.getPosition());
			if (nodeWithSamePositionExists && node.getTotalCost() < successor.getTotalCost()) {
				return true;
			}
		}
		return false;
	}

	private boolean visitedNodeWithSamePositionAndLowerFExists(Node successor) {
		for (Node node : visited) {
			final var nodeWithSamePositionExists = node.getPosition().equals(successor.getPosition());
			if (nodeWithSamePositionExists && node.getTotalCost() < successor.getTotalCost()) {
				return true;
			}
		}
		return false;
	}


	private List<Node> getSuccessors(Node currentNode) {
		List<Node> successors = new ArrayList<>();
		int x = currentNode.getPosition().getX();
		int y = currentNode.getPosition().getY();
		if (x < MAP_WIDTH) {
			// prawy 
			successors.add(new Node(new Point(x + 1, y), currentNode));
		}
		if (y < MAP_HEIGHT) {
			// góra
			successors.add(new Node(new Point(x, y + 1), currentNode));
		}
		if (y > 0) {
			// dół
			successors.add(new Node(new Point(x, y - 1), currentNode));
		}
		if (x < MAP_WIDTH && y < MAP_HEIGHT) {
			// prawy górny
			successors.add(new Node(new Point(x + 1, y + 1), currentNode));
		}

		if (x < MAP_WIDTH && y > 0) {
			// prawy dolny
			successors.add(new Node(new Point(x + 1, y - 1), currentNode));
		}
		if (x > 0) {
			// lewy
			successors.add(new Node(new Point(x - 1, y), currentNode));
		}
		if (x > 0 && y < MAP_HEIGHT) {
			// lewy górny
			successors.add(new Node(new Point(x - 1, y + 1), currentNode));
		}
		if (x > 0 && y > 0) {
			// lewy dolny
			successors.add(new Node(new Point(x - 1, y - 1), currentNode));
		}

		return successors;
	}

	private Node findNodeWithLowestF() {
		Node nodeWithLowestF = notVisited.get(0);
		for (Node node : notVisited) {
			if (node == nodeWithLowestF) {
				continue;
			}
			if (node.getTotalCost() < nodeWithLowestF.getTotalCost()) {
				nodeWithLowestF = node;
			}
		}
		return nodeWithLowestF;
	}


	private List<Node> createPath(Node currentNode, Point targetPosition) {
		List<Node> path = new ArrayList<>();
		while (currentNode.getParent() != null) {
			path.add(currentNode);
			currentNode = currentNode.getParent();
		}
		Collections.reverse(path);
		path.add(new Node(targetPosition, path.get(path.size() - 1)));
		return path;
	}
}
