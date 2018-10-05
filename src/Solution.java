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
