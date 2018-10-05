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
	
	public void enqueue(SearchStrategies strategy){
		
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

	
	
	 
}
