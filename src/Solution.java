import java.util.ArrayList;

//Class representing the solution object with the needed info
public class Solution {
	private ArrayList<String> path;
	private int cost;
	private int nodesExpanded;
	
	
	public Solution(ArrayList<String> path, int cost, int nodesExpanded) {
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
			statement += this.path.get(i) + ", ";
		}
		statement += "\n";
		statement += "Cost: " + this.getCost() + "\n";
		statement += "Nodes Expanded: " + this.getNodesExpanded();
		return statement;
		
	}
	public ArrayList<String> getPath() {
		return path;
	}
	public void setPath(ArrayList<String> path) {
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
