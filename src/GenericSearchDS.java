import java.util.ArrayList;

public class GenericSearchDS {
	
	private ArrayList<Node> nodes;
	private SearchStrategies searchStrategy;
	
	public GenericSearchDS() {
		this.nodes = new ArrayList<Node>();
	}
	
	public GenericSearchDS(ArrayList<Node> nodes) {
		super();
		this.nodes = nodes;
	}
	
	public String toString() {
		return this.nodes.toString();
	}
	
	public void enqueue(SearchStrategies strategy, ArrayList<Node> nodes) {
		// according to strategy, enqueue will behave differently (insert at first/last for example)
		for(Node node: nodes) {
			switch(strategy) {
				case DF: this.nodes.add(node); break;
				case BF: this.nodes.add(node); break;
				case UC: this.nodes.add(node); break;
				case ID: this.nodes.add(node); break;
				case GR1: this.nodes.add(node); break;
				case GR2: this.nodes.add(node); break;
				case AS1: this.nodes.add(node); break;
				case AS2: this.nodes.add(node); break;
			}
			
		}
		
	}
	
	public Node dequeue()
	{
		if(!nodes.isEmpty())
			return nodes.remove(0);
		
		return null;
	}
	
	public boolean isEmpty()
	{
		return nodes.size()==0;
	}
	
	public Node getFirst(){
		if(!isEmpty())
		{
			return nodes.get(0);
		}
		return null;
	}
	
	public Node getLast(){
		if(!isEmpty())
		{
			return nodes.get(nodes.size()-1);
		}
		return null;
	}

	public int size() {
		return nodes.size();
	}
	
	 
}
