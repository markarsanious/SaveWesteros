import java.util.ArrayList;
// Not generic
// Class representing the solution object with the needed info
class PathObject {
	private String direction;
	private String[][] grid; // 2D array representing the grid
	private int JonX;
	private int JonY;
	PathObject(String direction, String[][] grid, int x, int y) {
		this.direction = direction;
		this.grid = grid;
		this.setJonX(x);
		this.setJonY(y);
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String[][] getGrid() {
		return grid;
	}
	public void setGrid(String[][] world) {
		this.grid = world;
	}

	public int getJonY() {
		return JonY;
	}

	public void setJonY(int jonY) {
		JonY = jonY;
	}

	public int getJonX() {
		return JonX;
	}

	public void setJonX(int jonX) {
		JonX = jonX;
	}
}
public class Solution {
	private ArrayList<PathObject> path;
	private int cost;
	private int nodesExpanded;
	
	
	public Solution(ArrayList<PathObject> path, int cost, int nodesExpanded) {
		super();
		this.path = path;
		this.cost = cost;
		this.nodesExpanded = nodesExpanded;
	}
	
	public String toString() {
		String statement = "Found Solution:\n";
		statement += "Path: ";
		for(int i=0; i<this.path.size(); i++)
		{
			statement += this.path.get(i).getDirection() + ", ";
		}
		statement += "\n";
		statement += "Cost: " + this.getCost() + "\n";
		statement += "Nodes Expanded: " + this.getNodesExpanded();
		return statement;
		
	}
	public ArrayList<PathObject> getPath() {
		return path;
	}
	public void setPath(ArrayList<PathObject> path) {
		this.path = path;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getNodesExpanded() {
		return nodesExpanded;
	}
	public void setNodesExpanded(int nodesExpanded) {
		this.nodesExpanded = nodesExpanded;
	}
}
