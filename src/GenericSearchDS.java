import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		switch(strategy) {
		case DF: for(int i = nodes.size()-1; i >= 0; i--) this.nodes.add(0, nodes.get(i)); break;
		case BF: for(Node node: nodes) this.nodes.add(node); break;
		case UC: 
			for(Node node: nodes)
				insertInRightPosition(node);
			break;
//		case ID: this.nodes.add(node); break;
//		case GR1: this.nodes.add(node); break;
//		case GR2: this.nodes.add(node); break;
//		case AS1: this.nodes.add(node); break;
//		case AS2: this.nodes.add(node); break;
		}
		
//		nodes.sort(new WesterosComparator());		
	}
	
	// TODO: insertion sort ya @mohabamroo
	private void insertInRightPosition(Node x) {
	    int pos = Collections.binarySearch((List)this.nodes, x);
	    if (pos < 0) {
	        this.nodes.add(-pos-1, x);
	    }
	    	System.out.println(pos);
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
