import java.util.ArrayList;

//subclass of SearchProblem
public class SaveWesteros extends SearchProblem{
	private String[][] grid;

	
	public Solution search(String[][] grid, SearchStrategies strategy, boolean visualization) {
		// TODO Auto-generated method stub
		this.grid = grid;
		Solution solution = null; 
		switch(strategy)
		{
			case BF:  solution = BFS(); break;
			case DF:  solution = DFS(); break;
			case UC:  solution = UCS(); break;
			case ID:  solution = IDS(); break;
			case GR1: solution = GR1(); break;
			case GR2: solution = GR2(); break;
			case AS1: solution = AS1(); break;
			case AS2: solution = AS2(); break;
			default: break;
		}
		
		return solution;
	}

	

	@Override
	public Solution BFS() {
		// TODO Auto-generated method stub
		
		return genericSearch(SearchStrategies.BF);
	}

	@Override
	public Solution DFS() {
		// TODO Auto-generated method stub
		return genericSearch(SearchStrategies.DF);
	}

	@Override
	public Solution UCS() {
		// TODO Auto-generated method stub
		return genericSearch(SearchStrategies.UC);
	}

	@Override
	public Solution IDS() {
		// TODO Auto-generated method stub
		return genericSearch(SearchStrategies.ID);
	}

	@Override
	public Solution GR1() {
		// TODO Auto-generated method stub
		return genericSearch(SearchStrategies.GR1);
	}

	@Override
	public Solution GR2() {
		// TODO Auto-generated method stub
		return genericSearch(SearchStrategies.GR2);
	}

	@Override
	public Solution AS1() {
		// TODO Auto-generated method stub
		return genericSearch(SearchStrategies.AS1);
	}

	@Override
	public Solution AS2() {
		// TODO Auto-generated method stub
		return genericSearch(SearchStrategies.AS2);
	}


	@Override
	Solution genericSearch(SearchStrategies strategy) {
		// TODO Auto-generated method stub
		boolean[][] visited = new boolean [4][4];
		Node initialState = new WesterosNode (0, 0, new ArrayList<String>(), visited, 0, 3, 3, strategy);
		GenericSearchDS currentDS = makeQ(initialState);
		
		/*
		 * STEPS TO BE IMPLEMENTED inside the following loop:
		 * 1- check if empty return null
		 * 2- dequeue
		 * 3- do the goal test on the dequeued node
		 * 4- expand current Node
		 * 5- enqueue its children (return of the expand) one by on.  
		 */
		
		while(true)
		{
			if(currentDS.isEmpty())
				return null;
			
			Node firstNode = currentDS.dequeue();
			/*
			 * Steps missing:
			 * 1- Goal Test
			 * 2- expand current Node
			 * 3- Enqueue
			 */
		}
		
		
//		if(firstNode!=null)
//		{	
//			// searching for the dragon stone
//			if(((WesterosNode)firstNode).getDragonGlassLeft()==0)
//			{
//				int currentRow = ((WesterosNode)firstNode).getxPosition();
//				int currentCol = ((WesterosNode)firstNode).getyPosition();
//				if (grid[currentRow][currentCol]=="D")
//				{
//					
//				}
//			}// searching for a white walker
//			else
//			{
//				
//			}
//		}
	}



	@Override
	ArrayList<Node> expand(Node node) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	GenericSearchDS makeQ(Node node) {
		ArrayList<Node> currentNodes = new ArrayList<Node>();
		currentNodes.add(node);
		return new GenericSearchDS(currentNodes);
	}



	@Override
	boolean goalTest(Node node) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
