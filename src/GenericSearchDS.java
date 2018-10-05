import java.util.ArrayList;

public class GenericSearchDS {
	
	private ArrayList<StateNode> nodes;
	private SearchStrategies searchStrategy;
	
	public GenericSearchDS() {
		this.nodes = new ArrayList<StateNode>();
	}
	
	public GenericSearchDS(ArrayList<StateNode> nodes) {
		super();
		this.nodes = nodes;
	}
	
	public void enqueue(StateNode stateNode, SearchStrategies strategy){
		
	}
	
	public StateNode dequeue()
	{
		if(!nodes.isEmpty())
			return nodes.remove(0);
		
		return null;
	}
	
	public boolean isEmpty()
	{
		return nodes.size()==0;
	}
	
	public StateNode getFirst(){
		if(!isEmpty())
		{
			return nodes.get(0);
		}
		return null;
	}
	
	public StateNode getLast(){
		if(!isEmpty())
		{
			return nodes.get(nodes.size()-1);
		}
		return null;
	}

	
	
	 
}
