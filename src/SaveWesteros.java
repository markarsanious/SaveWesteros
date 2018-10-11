import java.util.ArrayList;
import java.util.PriorityQueue;

//subclass of SearchProblem
public class SaveWesteros extends SearchProblem {
	// duplicate attribute in WestrosWorld!
	private String[][] grid;
	private WesterosWorld world;
	private ArrayList<WesterosNode> sequenceofExpansion;
	private int levelLimit;
	private static final int KILL_COST = 10;
	private static final int STEP_COST = 1;

	static int[] yList = { 0, 0, -1, 1 };
	static int[] xList = { -1, 1, 0, 0 };
	static String[] direction = {"LEFT", "RIGHT", "UP", "DOWN"};

	// better add reference to your world
	// constructor
	public SaveWesteros(WesterosWorld world) {
		this.world = world;
		this.sequenceofExpansion = new ArrayList<>();
		this.levelLimit = 0;

	}

	public Solution search(String[][] grid, SearchStrategies strategy, boolean visualization) {
		// TODO Auto-generated method stub
		this.grid = grid;
		Solution solution = null;
		switch (strategy) {
		case BF:
			solution = BFS();
			break;
		case DF:
			solution = DFS();
			break;
		case UC:
			solution = UCS();
			break;
		case ID:
			solution = IDS();
			break;
		case GR1:
			solution = GR1();
			break;
		case GR2:
			solution = GR2();
			break;
		case AS1:
			solution = AS1();
			break;
		case AS2:
			solution = AS2();
			break;
		default:
			break;
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
		int nodesExpandedOld = 0;
		Solution solution = null;

		do {
			nodesExpandedOld = this.sequenceofExpansion.size();
			this.sequenceofExpansion = new ArrayList<>();

			solution = genericSearch(SearchStrategies.ID);

			if (solution != null)
				break;

			this.levelLimit++;
		} while (nodesExpandedOld != this.sequenceofExpansion.size());

		return solution;
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
	
	private int h1(int yPosition, int xPosition){
		int min=10000;
		
		for (int[] i : this.world.getWhiteWalkersPositions())
		{
			int currentDistance = Math.abs(yPosition - i[0]) + Math.abs(xPosition - i[1]);
			min = Math.min(min, currentDistance);
		}
		return min-1;
	}
	
	private int h2(int yPosition, int xPosition, int whiteWalkersKilled){
		int minDistance = h1(yPosition, xPosition);
		int remainingWhiteWalkersCost = (this.world.getWhiteWalkersCapacity() - whiteWalkersKilled)*10 /3;
		return minDistance + remainingWhiteWalkersCost;
	}

	@Override
	Solution genericSearch(SearchStrategies strategy) {
		// TODO Auto-generated method stub
		boolean[][] visited = new boolean[this.grid.length][this.grid[0].length];
		Node initialState = new WesterosNode(0, 0, new ArrayList<String>(), visited, 0, 3, 3, strategy, this.grid, 0);
		GenericSearchDS currentDS = makeQ(initialState);
		/*
		 * STEPS TO BE IMPLEMENTED inside the following loop: 1- check if empty
		 * return null 2- dequeue 3- do the goal test on the dequeued node 4-
		 * expand current Node 5- enqueue its children (return of the expand)
		 * one by on.
		 */

		while (true) {
			if (currentDS.isEmpty())
				return null;

			Node firstNode = currentDS.dequeue();
			WesterosNode castedNode = (WesterosNode) firstNode;
			// System.out.println(castedNode);

			if (goalTest(firstNode)) {
				// remove last move since it's an extra move after reaching the
				// goal
				castedNode.getPath().remove(castedNode.getPath().size() - 1);
				return new Solution(castedNode.getPath(), castedNode.getCost(), this.sequenceofExpansion.size());
			} else {

				if (!castedNode.getStrategy().equals(SearchStrategies.ID)
						|| (castedNode.getStrategy().equals(SearchStrategies.ID)
								&& castedNode.getLevel() <= this.levelLimit)) {
					ArrayList<Node> nodes = this.expand(firstNode);
					currentDS.enqueue(strategy, nodes);
				}
			}
			/*
			 * Steps missing: 1- Goal Test 2- expand current Node 3- Enqueue
			 */
		}

	}

	@Override
	ArrayList<Node> expand(Node node) {
		// Initialise return value
		// ArrayList<Node> expandedNodes = new ArrayList<Node>();

		WesterosNode castedNode = (WesterosNode) (node);
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
		int oldLevel = castedNode.getLevel();
		String[][] nodeGrid = castedNode.getGrid();

		String currentCell = this.world.getWorld()[yPosition][xPosition];

		// update the current cell as visited
		oldVisited[yPosition][xPosition] = true;

		// copy the the old grid
		String[][] newNodeGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
		for (int i = 0; i < nodeGrid.length; i++)
			for (int j = 0; j < nodeGrid[i].length; j++)
				newNodeGrid[i][j] = nodeGrid[i][j];

		// copy old visited array
		boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
		for (int i = 0; i < oldVisited.length; i++)
			for (int j = 0; j < oldVisited[i].length; j++)
				newVisited[i][j] = oldVisited[i][j];

		// if no dragon glasses left, look for dragonstone
		// otherwise look for white walkers

		// if dragonstone found, recharge
		if (currentCell == "D") {
			remainingDragonGlasses = this.world.getCapacityOfDG();
			path.add("Recharged dragonglass");
			oldVisited = new boolean[world.getWorldRows()][world.getWorldCols()];

		}

		// sufficient dragonglass, look for white walkers
		if (remainingDragonGlasses > 0) {

			int totalSurroundingWhiteWalkers = 0;

			// check 4 neighboring cells and kill white walkers if found
			if (xPosition > 0 && nodeGrid[yPosition][xPosition - 1] == "W") {

				totalSurroundingWhiteWalkers += 1;
				newNodeGrid[yPosition][xPosition - 1] = "E";
			}
			if (xPosition < (this.world.getWorldCols() - 1) && nodeGrid[yPosition][xPosition + 1] == "W") {
				totalSurroundingWhiteWalkers += 1;
				newNodeGrid[yPosition][xPosition + 1] = "E";
			}
			if (yPosition > 0 && nodeGrid[yPosition - 1][xPosition] == "W") {
				totalSurroundingWhiteWalkers += 1;
				newNodeGrid[yPosition - 1][xPosition] = "E";
			}
			if (yPosition < (this.world.getWorldRows() - 1) && nodeGrid[yPosition + 1][xPosition] == "W") {
				totalSurroundingWhiteWalkers += 1;
				newNodeGrid[yPosition + 1][xPosition] = "E";
			}

			// System.out.println("Killed: " + totalSurroundingWhiteWalkers);

			if (totalSurroundingWhiteWalkers > 0 && remainingDragonGlasses > 0) {
				whiteWalkersKilled += totalSurroundingWhiteWalkers;
				// TODO: what if knives is zero?
				int oldDragonGlasses = remainingDragonGlasses;
				remainingDragonGlasses -= 1;
				// cost is increased by 10 when using a dragon glass
				pathCost += KILL_COST;

				path.add("Killed " + totalSurroundingWhiteWalkers);

				// no dragonglass found, reset visited array and new goal is
				// dragonstone
				if (oldDragonGlasses > 0 && remainingDragonGlasses == 0) {
					newVisited = new boolean[newVisited.length][newVisited[0].length];
				}
			}

		}

		return this.getNeighbouringCells(whiteWalkersKilled, path, newVisited, remainingDragonGlasses, xPosition,
				yPosition, strategy, pathCost, newNodeGrid, oldLevel + 1);

	}

	@Override
	GenericSearchDS makeQ(Node node) {
		return new GenericSearchDS(node);
	}

	@Override
	boolean goalTest(Node node) {
		// check if zero white walkers => done
		// else, check for dragon stones
		WesterosNode castedNode = (WesterosNode) node;
		if (castedNode.getWhiteWalkersKilled() == this.world.getWhiteWalkersCapacity()) {
			return true;
		}
		return false;
	}

	// check if the move is a valid one
	private boolean canMove(int i, boolean[][] visited, String[][] grid, int xPosition, int yPosition) {
		return (yPosition + yList[i]) >= 0 && (xPosition + xList[i]) >= 0 && (yPosition + yList[i]) < grid.length
				&& (xPosition + xList[i]) < grid.length && !visited[yPosition + yList[i]][xPosition + xList[i]]
				&& !grid[yPosition + yList[i]][xPosition + xList[i]].equals("W")
				&& !grid[yPosition][xPosition + xList[i]].equals("O");
	}
	
	private int calculateEstimate(int yPosition, int xPosition, int whiteWalkersKilled, SearchStrategies strategy)
	{
		int estimate = 0;
		switch(strategy)
		{
			case GR1: 
			case AS1: estimate = h1(yPosition, xPosition); break;
			case GR2:
			case AS2: estimate = h2(yPosition, xPosition, whiteWalkersKilled); break;
		}
		return estimate;
	}


	private ArrayList<Node> getNeighbouringCells(int whiteWalkersKilled, ArrayList<String> path, boolean[][] visited,
			int dragonGlassesLeft, int xPosition, int yPosition, SearchStrategies strategy, int pathCost,
			String[][] grid, int level) {
		ArrayList<Node> returnedNodes = new ArrayList<Node>();

		// check the 4 directions
		for (int iteration = 0; iteration < xList.length; iteration++) {
			if (canMove(iteration, visited, grid, xPosition, yPosition)) {
				boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
				// cloning visited array by value not reference; O(n*m)
				// TODO: enhance this?
				for (int i = 0; i < visited.length; i++)
					for (int j = 0; j < visited[i].length; j++)
						newVisited[i][j] = visited[i][j];

				String[][] newGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
				for (int i = 0; i < grid.length; i++)
					for (int j = 0; j < grid[i].length; j++)
						newGrid[i][j] = grid[i][j];

				ArrayList<String> newPath = new ArrayList<String>();

				for (String currentDirection : path)
					newPath.add(currentDirection);

				newPath.add(direction[iteration] + " to "+ (yPosition + yList[iteration]) + " " + (xPosition + xList[iteration]));

				int estimate = calculateEstimate(yPosition+yList[iteration], xPosition+xList[iteration], whiteWalkersKilled, strategy);
				
				Node newNode = new WesterosNode(whiteWalkersKilled, pathCost + STEP_COST, newPath, newVisited,
						dragonGlassesLeft, xPosition + xList[iteration], yPosition + yList[iteration], strategy, newGrid, level, estimate);
				returnedNodes.add(newNode);
			}
		}

		return returnedNodes;
	}

}
