import java.util.ArrayList;

//A class representing our search tree node with information needed about the path
public class WesterosNode extends Node implements Comparable {
	private int whiteWalkersKilled;
	private ArrayList <String> path;
	private boolean[][] visited;
	private int dragonGlassLeft;
	private int xPosition;
	private int yPosition;
	private SearchStrategies strategy;
	private String[][] grid;
	
	public WesterosNode(int whiteWalkersKilled, int pathCost, ArrayList<String> path, boolean[][] visited,
			int dragonGlassLeft, int xPosition, int yPosition, SearchStrategies strategy, String[][] grid) {
		super(pathCost);
		this.whiteWalkersKilled = whiteWalkersKilled;
		this.path = path;
		this.visited = visited;
		this.dragonGlassLeft = dragonGlassLeft;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.setStrategy(strategy);
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
	public ArrayList<String> getPath() {
		return path;
	}
	public void setPath(ArrayList<String> path) {
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

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public SearchStrategies getStrategy() {
		return strategy;
	}

	public void setStrategy(SearchStrategies strategy) {
		this.strategy = strategy;
	}	
	
	public String[][] getGrid() {
		return grid;
	}

	public void setGrid(String[][] grid) {
		this.grid = grid;
	}
	
	
	
}
