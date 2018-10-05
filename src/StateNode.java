import java.util.ArrayList;

//A class representing our search tree node with information needed about the path
public class StateNode implements Comparable{
	private int whiteWalkersKilled;
	private int pathCost;
	private ArrayList <String> path;
	private boolean[][] visited;
	private int dragonGlassLeft;
	private int xPosition;
	private int yPosition;
	private SearchStrategies strategy;
	
	public StateNode(int whiteWalkersKilled, int pathCost, ArrayList<String> path, boolean[][] visited,
			int dragonGlassLeft, int xPosition, int yPosition, SearchStrategies strategy) {
		super();
		this.whiteWalkersKilled = whiteWalkersKilled;
		this.pathCost = pathCost;
		this.path = path;
		this.visited = visited;
		this.dragonGlassLeft = dragonGlassLeft;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.strategy = strategy;
	}
	
	public int getWhiteWalkersKilled() {
		return whiteWalkersKilled;
	}
	public void setWhiteWalkersKilled(int whiteWalkersKilled) {
		this.whiteWalkersKilled = whiteWalkersKilled;
	}
	public int getPathCost() {
		return pathCost;
	}
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
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
	
	
	
}
