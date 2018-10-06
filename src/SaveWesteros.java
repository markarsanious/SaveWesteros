import java.util.ArrayList;

//subclass of SearchProblem
public class SaveWesteros extends SearchProblem{
	// duplicate attribute in WestrosWorld!
	private String[][] grid;
	private WesterosWorld world;
	private ArrayList<WesterosNode> sequenceofExpansion;
	// better add reference to your world
	// constructor
	public SaveWesteros(WesterosWorld world) {
		this.world = world;
		this.sequenceofExpansion = new ArrayList<>();
	}

	
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
		boolean[][] visited = new boolean [this.grid.length][this.grid[0].length];
		Node initialState = new WesterosNode (0, 0, new ArrayList<String>(), visited, 0, 3, 3, strategy);
		GenericSearchDS currentDS = makeQ(initialState);
		System.out.println(currentDS.toString());
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
			WesterosNode castedNode = (WesterosNode) firstNode;
			this.sequenceofExpansion.add(castedNode);
			// add to the expansion sequence kda kda
			// add to path to goal kman?
			if(goalTest(firstNode)) {
				// return Node or solution?
				return new Solution(castedNode.getPath(), castedNode.getCost(), this.sequenceofExpansion.size());
			} else {
				ArrayList<Node> nodes = this.expand(firstNode);
			}
			/*
			 * Steps missing:
			 * 1- Goal Test
			 * 2- expand current Node
			 * 3- Enqueue
			 */
		}
		

	}



	@Override
	ArrayList<Node> expand(Node node) {
		WesterosNode castedNode = (WesterosNode) node;
		int xPosition = castedNode.getxPosition();
		int yPosition = castedNode.getyPosition();
		// TODO: check special cases: obstacle or Dragon Cave
		
		int whiteWalkersKilled = castedNode.getWhiteWalkersKilled();
		int remainingKnives = castedNode.getDragonGlassLeft();
		ArrayList<String> path = castedNode.getPath();
		int surroundingWalkers = this.getTotalSurroundingWalkers(xPosition, yPosition);
		
		int pathCost = 0; // where to get the real cost?
		
		// update current state
		boolean[][] oldVisited = castedNode.getVisited();
		boolean[][] updatedVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
		
		if(surroundingWalkers > 0) {
			whiteWalkersKilled += surroundingWalkers;
			// TODO: what if knives is zero?
			remainingKnives -= 1;
			// cost is increased by 1 when using a knive
			pathCost ++;
		}
		
		// cloning visited array by value not reference; O(n*m)
		// TODO: enhance this?
		for(int i=0; i<oldVisited.length; i++)
			  for(int j=0; j<oldVisited[i].length; j++)
				  updatedVisited[i][j] = oldVisited[i][j];
		
		// updated visited grid
		updatedVisited[xPosition][yPosition] = true;

		
		// cases: 4 corners, 4 sides, in-between
		ArrayList<Node> nodes = new ArrayList();
		
		if(xPosition > 0) {
			// at least second row, we have UP
			ArrayList<String> upPath = new ArrayList<String>(path);
			upPath.add("U");
			Node upNode = new WesterosNode(whiteWalkersKilled, pathCost, upPath, updatedVisited,
					remainingKnives, xPosition - 1, yPosition, castedNode.getStrategy());
			nodes.add(upNode);
		}
		
		if(xPosition < (this.world.getWorldRows() - 1)) {
			// at least second row, we have Down
			ArrayList<String> downPath = new ArrayList<String>(path);
			downPath.add("D");
			Node downNode = new WesterosNode(whiteWalkersKilled, pathCost, downPath, updatedVisited,
					remainingKnives, xPosition + 1, yPosition, castedNode.getStrategy());
			nodes.add(downNode);
		}
		
		if(yPosition > 0) {
			// at least second row, we have Left
			ArrayList<String> leftPath = new ArrayList<String>(path);
			leftPath.add("L");
			Node leftNode = new WesterosNode(whiteWalkersKilled, pathCost, leftPath, updatedVisited,
					remainingKnives, xPosition, yPosition - 1, castedNode.getStrategy());
			nodes.add(leftNode);
		}
		
		if(yPosition < (this.world.getWorldCols() - 1)) {
			// at least second row, we have Right
			ArrayList<String> rightPath = new ArrayList<String>(path);
			rightPath.add("R");
			Node downNode = new WesterosNode(whiteWalkersKilled, pathCost, rightPath, updatedVisited,
					remainingKnives, xPosition, yPosition + 1, castedNode.getStrategy());
			nodes.add(downNode);
		}
		
		return nodes;
	}


	@Override
	GenericSearchDS makeQ(Node node) {
		ArrayList<Node> currentNodes = new ArrayList<Node>();
		currentNodes.add(node);
		return new GenericSearchDS(currentNodes);
	}
	
	private int getTotalSurroundingWalkers(int xPosition, int yPosition) {
		int total = 0;
		String[][] grid = this.world.getWorld();
		if(xPosition > 0) {
			total += grid[xPosition - 1][yPosition] == "W" ? 1 : 0;
		}
		if(xPosition < (this.world.getWorldRows() - 1)) {
			total += grid[xPosition + 1][yPosition] == "W" ? 1 : 0;
		}
		if(yPosition > 0) {
			total += grid[xPosition][yPosition - 1] == "W" ? 1 : 0;
		}
		if(yPosition < (this.world.getWorldCols() -1)) {
			total += grid[xPosition][yPosition - 1] == "W" ? 1 : 0;
		}
		
		return total;
	}



	@Override
	boolean goalTest(Node node) {
		// check if zero white walkers => done
		// else, check for dragon stones
		WesterosNode castedNode = (WesterosNode)node;
		if(castedNode.getWhiteWalkersKilled() == this.world.getWhiteWalkersCapacity()) {
			// reached goal state
		}
		return false;
	}
	
	
}
