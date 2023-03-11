package pl.nesoft.astar;

import java.util.Arrays;
import java.util.List;

/**
 * @author Piotr Wysocki on 10.03.2023
 * @project a_star_pathfinding
 */
public class AStar {

//	public static final int[][] WORLD_MAP = new int[][]
//	{
//			{-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//			{1, 1, 1, 1, 1, 0, 1, 1, 1, 0},
//			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
//			{0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0, -2}
//	};

	public static final int[][] WORLD_MAP = new int[100][100];

	public static final int MAP_WIDTH = WORLD_MAP[0].length - 1;
	public static final int MAP_HEIGHT = WORLD_MAP.length - 1;

	public static void main(String[] args) {
		System.out.println("############## Starting map ################");
		printMap();
		PathFinding pathFinding = new PathFinding();
		int y = WORLD_MAP.length - 1;
		Point startPosition = new Point(0, 0);
		Point targetPosition = new Point(WORLD_MAP[y].length - 1, y);
		List<Node> path = pathFinding.findPath(startPosition, targetPosition);
		if (path.isEmpty()) {
			System.out.println("Path has not been found");
		} else {
			System.out.println("############## Path map ################");
			setPath(path);
			printMap();
		}
	}

	private static void printMap() {
		for (int[] array : WORLD_MAP) {
			System.out.println(Arrays.toString(array));
		}
	}

	private static void setPath(List<Node> path) {
		for (Node node : path) {
			WORLD_MAP[node.getPosition().getY()][node.getPosition().getX()] = 9;
		}
	}
}
