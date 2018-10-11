import java.util.ArrayList;
import java.util.Collections;

//A class representing our search tree node with information needed about the path
public class WesterosNode extends Node {
	private int whiteWalkersKilled;
	private ArrayList <PathObject> path;
	private boolean[][] visited;
	private int dragonGlassLeft;
	private int xPosition;
	private int yPosition;
	private String[][] grid;
	
	public WesterosNode(int whiteWalkersKilled, int pathCost, ArrayList<PathObject> path, boolean[][] visited,
			int dragonGlassLeft, int xPosition, int yPosition, SearchStrategies strategy, String[][] grid, int level) {
		super(pathCost, level, strategy);
		this.whiteWalkersKilled = whiteWalkersKilled;
		this.path = path;
		this.visited = visited;
		this.dragonGlassLeft = dragonGlassLeft;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.grid = grid;
	}
	

	public String toString() {
		return "{Cost: " + this.getCost() + ", X: " + this.getxPosition() + ", Y: " + this.getyPosition() + "}";
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
	public int getxPosition() {
		return xPosition;
	}
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getyPosition() {
		return yPosition;
	}
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}


	
	public String[][] getGrid() {
		return grid;
	}

	public void setGrid(String[][] grid) {
		this.grid = grid;
	}

	
	
	
}
