import java.util.ArrayList;
import java.util.PriorityQueue;

public class GenericSearchDS {

	private PriorityQueue<Node> nodes;

	public GenericSearchDS() {
		this.nodes = new PriorityQueue<Node>();
	}

	public GenericSearchDS(Node node) {
		super();
		PriorityQueue<Node> currentNodes = new PriorityQueue<Node>();
		currentNodes.add(node);
		this.nodes = currentNodes;
	}

	public String toString() {
		return this.nodes.toString();
	}

	public void enqueue(ArrayList<Node> nodes) {
		for (Node node : nodes)
			this.nodes.add(node);
	}
	
	public Node dequeue() {
		if (!nodes.isEmpty())
			return nodes.remove();

		return null;
	}

	public boolean isEmpty() {
		return nodes.size() == 0;
	}

	public int size() {
		return nodes.size();
	}

}
