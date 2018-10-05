import java.util.ArrayList;

//subclass of SearchProblem
public class SaveWesteros extends SearchProblem{
	private String[][] grid;

	
	@Override
	public Solution search(String[][] grid, SearchStrategies strategy, boolean visualization) {
		// TODO Auto-generated method stub
		this.grid = grid;
		boolean[][] visited = new boolean [4][4];
		StateNode initialState = new StateNode (0, 0, new ArrayList<String>(), visited, 0, 3, 3, strategy);
		ArrayList<StateNode> currentNodes = new ArrayList<StateNode>();
		currentNodes.add(initialState);
		GenericSearchDS currentDS = new GenericSearchDS(currentNodes);
		Solution solution = null; 
		switch(strategy)
		{
			case BF:  solution = BFS(currentDS); break;
			case DF:  solution = DFS(currentDS); break;
			case UC:  solution = UCS(currentDS); break;
			case ID:  solution = IDS(currentDS); break;
			case GR1: solution = GR1(currentDS); break;
			case GR2: solution = GR2(currentDS); break;
			case AS1: solution = AS1(currentDS); break;
			case AS2: solution = AS2(currentDS); break;
			default: break;
		}
		
		return solution;
	}

	

	@Override
	public Solution BFS(GenericSearchDS currentNodes) {
		// TODO Auto-generated method stub
		if(currentNodes.isEmpty())
			return null;
		StateNode firstNode = currentNodes.dequeue();
		
		if(firstNode!=null)
		{	
			// searching for the dragon stone
			if(firstNode.getDragonGlassLeft()==0)
			{
				int currentRow = firstNode.getxPosition();
				int currentCol = firstNode.getyPosition();
				if (grid[currentRow][currentCol]=="D")
				{
					
				}
			}// searching for a white walker
			else
			{
				
			}
		}
		return null;
	}

	@Override
	public Solution DFS(GenericSearchDS currentNodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution UCS(GenericSearchDS currentNodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution IDS(GenericSearchDS currentNodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution GR1(GenericSearchDS currentNodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution GR2(GenericSearchDS currentNodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution AS1(GenericSearchDS currentNodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Solution AS2(GenericSearchDS currentNodes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
