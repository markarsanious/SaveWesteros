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
		Node initialState = new WesterosNode (0, 0, new ArrayList<String>(), visited, 0, 3, 3, strategy, this.grid);
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
			WesterosNode castedNode = (WesterosNode) firstNode;
			
			if(goalTest(firstNode)) {
				
				return new Solution(castedNode.getPath(), castedNode.getCost(), this.sequenceofExpansion.size());
			} else {
				ArrayList<Node> nodes = this.expand(firstNode);
				currentDS.enqueue(strategy, nodes);
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
		// Initialise return value
		ArrayList<Node> expandedNodes = new ArrayList<Node>();		

		WesterosNode castedNode = (WesterosNode)(node);
		this.sequenceofExpansion.add(castedNode);

		// get current state attributes
		int whiteWalkersKilled = castedNode.getWhiteWalkersKilled();
		ArrayList<String> path = castedNode.getPath();
		boolean[][] oldVisited = castedNode.getVisited();
		int remainingDragonGlasses = castedNode.getDragonGlassLeft();
		int xPosition = castedNode.getxPosition();
		int yPosition = castedNode.getyPosition();
		SearchStrategies strategy = castedNode.getStrategy();
		int pathCost = castedNode.getCost(); // where to get the real cost?
		String[][] nodeGrid = castedNode.getGrid();
		
		String currentCell = this.world.getWorld()[yPosition][xPosition];

		// update the current cell as visited
		oldVisited[xPosition][yPosition] = true;
		
		
		// if no dragon glasses left, look for dragonstone
		// otherwise look for white walkers
		
		if(remainingDragonGlasses == 0)
		{
			// if dragonstone found, recharge
			if(currentCell == "D")
			{
				remainingDragonGlasses = this.world.getCapacityOfDG();
			}
			
			return this.getNeighbouringCells(whiteWalkersKilled, path, oldVisited, remainingDragonGlasses, xPosition, yPosition, strategy, pathCost, nodeGrid);
		}
		// sufficient dragonglass, look for white walkers
		else
		{
			
			int totalSurroundingWhiteWalkers = 0;
			
			// copy the the old grid
			String[][] newNodeGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<nodeGrid.length; i++)
				  for(int j=0; j<nodeGrid[i].length; j++)
					  newNodeGrid[i][j] = nodeGrid[i][j];
			
			// check 4 neighboring cells and kill white walkers if found
			if(xPosition > 0) {
				totalSurroundingWhiteWalkers += nodeGrid[yPosition][xPosition - 1] == "W" ? 1 : 0;
				newNodeGrid[yPosition][xPosition-1]= "E";
			}
			if(xPosition < (this.world.getWorldCols() - 1)) {
				totalSurroundingWhiteWalkers += nodeGrid[yPosition][xPosition + 1] == "W" ? 1 : 0;
				newNodeGrid[yPosition][xPosition+1]= "E";
			}
			if(yPosition > 0) {
				totalSurroundingWhiteWalkers += nodeGrid[yPosition - 1][xPosition] == "W" ? 1 : 0;
				newNodeGrid[yPosition-1][xPosition]= "E";
			}
			if(yPosition < (this.world.getWorldRows() -1)) {
				totalSurroundingWhiteWalkers += nodeGrid[yPosition + 1][xPosition] == "W" ? 1 : 0;
				newNodeGrid[yPosition+1][xPosition]= "E";
			}
			
			//System.out.println("Killed: " + totalSurroundingWhiteWalkers);
			
			// copy old visited array
			boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<oldVisited.length; i++)
				  for(int j=0; j<oldVisited[i].length; j++)
					  newVisited[i][j] = oldVisited[i][j];
			
			
			if(totalSurroundingWhiteWalkers > 0) {
				whiteWalkersKilled += totalSurroundingWhiteWalkers;
				// TODO: what if knives is zero?
				remainingDragonGlasses -= 1;
				// cost is increased by 10 when using a dragon glass
				pathCost+=10;
				
				
				// no dragonglass found, reset visited array and new goal is dragonstone
				if(remainingDragonGlasses==0)
				{
					for(int i=0; i<newVisited.length; i++)
						  for(int j=0; j<newVisited[i].length; j++)
							  newVisited[i][j] = false;
				}
			}
			
			return this.getNeighbouringCells(whiteWalkersKilled, path, newVisited, remainingDragonGlasses, xPosition, yPosition, strategy, pathCost, newNodeGrid);
		}
		
	}


	@Override
	GenericSearchDS makeQ(Node node) {
		ArrayList<Node> currentNodes = new ArrayList<Node>();
		currentNodes.add(node);
		return new GenericSearchDS(currentNodes);
	}


	@Override
	boolean goalTest(Node node) {
		// check if zero white walkers => done
		// else, check for dragon stones
		WesterosNode castedNode = (WesterosNode)node;
		if(castedNode.getWhiteWalkersKilled() == this.world.getWhiteWalkersCapacity()) {
			return true;
		}
		return false;
	}
	
	
	private ArrayList<Node> getNeighbouringCells(int whiteWalkersKilled, ArrayList<String> path, boolean[][] visited,
			int dragonGlassesLeft, int xPosition, int yPosition, SearchStrategies strategy, int pathCost, String[][] grid)
	{
		ArrayList<Node> returnedNodes = new ArrayList<Node>();
		
		
		// check left
		if(xPosition > 0 && !visited[yPosition][xPosition-1] && !grid[yPosition][xPosition-1].equals("W") && !grid[yPosition][xPosition-1].equals("O"))
		{
			boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
			// cloning visited array by value not reference; O(n*m)
			// TODO: enhance this?
			for(int i=0; i<visited.length; i++)
				  for(int j=0; j<visited[i].length; j++)
					  newVisited[i][j] = visited[i][j];
			
			String[][] newGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<grid.length; i++)
				  for(int j=0; j<grid[i].length; j++)
					  newGrid[i][j] = grid[i][j];
			
			ArrayList<String> newPath = new ArrayList<String>();
//			while(!path.isEmpty())
//			{
//				newPath.add(path.remove(0));
//			}
			for(String currentDirection: path)
				newPath.add(currentDirection);
			
			newPath.add("LEFT from " + xPosition + " " + yPosition);
			
			Node leftNode = new WesterosNode(whiteWalkersKilled, pathCost, newPath, newVisited, dragonGlassesLeft, xPosition-1, yPosition, strategy, newGrid);
			returnedNodes.add(leftNode);
		}
		
		// check right
		if(xPosition < world.getWorldCols()-1 && !visited[yPosition][xPosition+1] && !grid[yPosition][xPosition+1].equals("W") && !grid[yPosition][xPosition+1].equals("O"))
		{
			boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<visited.length; i++)
				  for(int j=0; j<visited[i].length; j++)
					  newVisited[i][j] = visited[i][j];
			
			String[][] newGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<grid.length; i++)
				  for(int j=0; j<grid[i].length; j++)
					  newGrid[i][j] = grid[i][j];
			
			ArrayList<String> newPath = new ArrayList<String>();
//			while(!path.isEmpty())
//			{
//				newPath.add(path.remove(0));
//			}
			for(String currentDirection: path)
				newPath.add(currentDirection);
			
			newPath.add("RIGHT from " + xPosition + " " + yPosition);
			
			Node rightNode = new WesterosNode(whiteWalkersKilled, pathCost, newPath, newVisited, dragonGlassesLeft, xPosition+1, yPosition, strategy, newGrid);
			returnedNodes.add(rightNode);
			
		}
		
		// check up
		if(yPosition > 0 && !visited[yPosition-1][xPosition] && !grid[yPosition-1][xPosition].equals("W") && !grid[yPosition-1][xPosition].equals("O"))
		{
			boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<visited.length; i++)
				  for(int j=0; j<visited[i].length; j++)
					  newVisited[i][j] = visited[i][j];
			
			String[][] newGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<grid.length; i++)
				  for(int j=0; j<grid[i].length; j++)
					  newGrid[i][j] = grid[i][j];
			
			ArrayList<String> newPath = new ArrayList<String>();
//			while(!path.isEmpty())
//			{
//				newPath.add(path.remove(0));
//			}
			for(String currentDirection: path)
				newPath.add(currentDirection);
			
			newPath.add("UP from " + xPosition + " " + yPosition);
			
			Node upNode = new WesterosNode(whiteWalkersKilled, pathCost, newPath, newVisited, dragonGlassesLeft, xPosition, yPosition-1, strategy, newGrid);
			returnedNodes.add(upNode);
		}
		// check down
		if(yPosition < world.getWorldRows()-1 && !visited[yPosition+1][xPosition] && !grid[yPosition+1][xPosition].equals("W") && !grid[yPosition+1][xPosition].equals("O"))
		{
			boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<visited.length; i++)
				  for(int j=0; j<visited[i].length; j++)
					  newVisited[i][j] = visited[i][j];
			
			String[][] newGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
			for(int i=0; i<grid.length; i++)
				  for(int j=0; j<grid[i].length; j++)
					  newGrid[i][j] = grid[i][j];
			
			ArrayList<String> newPath = new ArrayList<String>();
//			while(!path.isEmpty())
//			{
//				newPath.add(path.remove(0));
//			}
			for(String currentDirection: path)
				newPath.add(currentDirection);
			
//			System.out.println("I'm at " + xPosition + " " + yPosition);
			newPath.add("DOWN from " + xPosition + " " + yPosition);
			
			Node downNode = new WesterosNode(whiteWalkersKilled, pathCost, newPath, newVisited, dragonGlassesLeft, xPosition, yPosition+1, strategy, newGrid);
			returnedNodes.add(downNode);
		}
		
		
		return returnedNodes;
	}
	
}
