import java.util.ArrayList;
import java.util.Comparator;
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
public class Solution implements Comparable {
	private ArrayList<PathObject> path;
	private int cost;
	private int nodesExpanded;
	private SearchStrategies strategy;
	
	public Solution(ArrayList<PathObject> path, int cost, int nodesExpanded, SearchStrategies strategy) {
		super();
		this.path = path;
		this.cost = cost;
		this.nodesExpanded = nodesExpanded;
		this.setStrategy(strategy);
	}
	
	public String toString() {
		String statement = "\n";
		statement += "Strategy: ";
		statement += this.getStrategy();
		
		statement += "\nPath: ";
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

	@Override
	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		return this.getNodesExpanded() - ((Solution)other).getNodesExpanded();
	}

	public SearchStrategies getStrategy() {
		return strategy;
	}

	public void setStrategy(SearchStrategies strategy) {
		this.strategy = strategy;
	}
}

class SortByCost implements Comparator<Solution> 
{ 
    public int compare(Solution a, Solution b) 
    { 
        return a.getCost() - b.getCost(); 
    } 
} 
  

class SortByNodesExpanded implements Comparator<Solution> 
{ 
    public int compare(Solution a, Solution b) 
    { 
        return a.getNodesExpanded() - b.getNodesExpanded(); 
    } 
} 
  
