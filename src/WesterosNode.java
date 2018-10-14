import java.util.ArrayList;
import java.util.Collections;

class Position {
	private int x;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private int y;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "X: " + this.getX() + ", Y: " + this.getY();
	}
}

// A class representing our search tree node with information needed about the
// path
public class WesterosNode extends Node {
	private int whiteWalkersKilled;
	private ArrayList<PathObject> path;
	private boolean[][] visited;
	private int dragonGlassLeft;
	private Position position;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	private String[][] grid;

	public WesterosNode(int whiteWalkersKilled, int pathCost, ArrayList<PathObject> path, boolean[][] visited,
			int dragonGlassLeft, Position position, SearchStrategies strategy, String[][] grid, int level) {
		super(pathCost, level, strategy);
		this.whiteWalkersKilled = whiteWalkersKilled;
		this.path = path;
		this.visited = visited;
		this.dragonGlassLeft = dragonGlassLeft;
		this.position = position;
		this.grid = grid;
	}

	public WesterosNode(int whiteWalkersKilled, int pathCost, ArrayList<PathObject> path, boolean[][] visited,
			int dragonGlassLeft, Position position, SearchStrategies strategy, String[][] grid, int level,
			int estimate) {
		super(pathCost, level, strategy, estimate);
		this.whiteWalkersKilled = whiteWalkersKilled;
		this.path = path;
		this.visited = visited;
		this.dragonGlassLeft = dragonGlassLeft;
		this.position = position;
		this.grid = grid;
	}

	public String toString() {
		return "{Cost: " + this.getCost() + ", X: " + this.getPosition().getX() + ", Y: " + this.getPosition().getY()
				+ "}";
	}

	public int getWhiteWalkersKilled() {
		return whiteWalkersKilled;
	}

	public void setWhiteWalkersKilled(int whiteWalkersKilled) {
		this.whiteWalkersKilled = whiteWalkersKilled;
	}

	public ArrayList<PathObject> getPath() {
		return path;
	}

	public void setPath(ArrayList<PathObject> path) {
		this.path = path;
	}

	public boolean[][] getVisited() {
		return visited;
	}

	public void setVisited(boolean[][] visited) {
		this.visited = visited;
	}

	public int getDragonGlassLeft() {
		return dragonGlassLeft;
	}

	public void setDragonGlassLeft(int dragonGlassLeft) {
		this.dragonGlassLeft = dragonGlassLeft;
	}

	public String[][] getGrid() {
		return grid;
	}

	public void setGrid(String[][] grid) {
		this.grid = grid;
	}

}
