
public class Node {
	private int cost;

	
	public Node(int cost) {
		super();
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String toString() {
		return "Cost: " + this.getCost();
	}
	
}
