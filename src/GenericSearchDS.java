import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class GenericSearchDS {
	
	private PriorityQueue<Node> nodes;
	private SearchStrategies searchStrategy;
	
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
	
	public void enqueue(SearchStrategies strategy, ArrayList<Node> nodes) {
		for(Node node: nodes) 
			this.nodes.add(node); 
	}
	
	// TODO: insertion sort ya @mohabamroo
	
	public Node dequeue()
	{
		if(!nodes.isEmpty())
			return nodes.remove();
		
		return null;
	}
	
	public boolean isEmpty()
	{
		return nodes.size()==0;
	}
	
	public int size() {
		return nodes.size();
	}
	
	 
}
