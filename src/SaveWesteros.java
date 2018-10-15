import java.util.ArrayList;

public class SaveWesteros extends SearchProblem {

	private String[][] grid;
	private WesterosWorld world;
	private boolean visualization;
	private int levelLimit;
	private static final int KILL_COST = 10;
	private static final int STEP_COST = 1;

	static int[] yList = { 0, 0, -1, 1 };
	static int[] xList = { -1, 1, 0, 0 };
	static String[] direction = { "L", "R", "U", "D" };

	public SaveWesteros(WesterosWorld world) {
		super();
		this.world = world;
	}

	public Solution search(String[][] grid, SearchStrategies strategy, boolean visualization) {
		this.grid = grid;
		this.setVisualization(visualization);

		switch (strategy) {
		case BF:
			return BFS();
		case DF:
			return DFS();
		case UC:
			return UCS();
		case ID:
			return IDS();
		case GR1:
			return GR1();
		case GR2:
			return GR2();
		case AS1:
			return AS1();
		case AS2:
			return AS2();
		default:
			return BFS();
		}

	}

	

	private int h1(int yPosition, int xPosition) {
		int min = 10000;

		for (int[] i : this.world.getWhiteWalkersPositions()) {
			int currentDistance = Math.abs(yPosition - i[0]) + Math.abs(xPosition - i[1]);
			min = Math.min(min, currentDistance);
		}
		return min - 1;
	}

	private int h2(int yPosition, int xPosition, int whiteWalkersKilled) {
		int minDistance = h1(yPosition, xPosition);
		int remainingWhiteWalkersCost = (this.world.getWhiteWalkersCapacity() - whiteWalkersKilled) * 10 / 3;
		return minDistance + remainingWhiteWalkersCost;
	}

	/*
	 * Mimic of the generic search function in the lectures
	 */
	@Override
	Solution genericSearch(SearchStrategies strategy) {
		boolean[][] visited = new boolean[this.grid.length][this.grid[0].length];
		Position jonSnow = new Position(this.grid.length - 1, this.grid[0].length - 1);
		Node initialState = new WesterosNode(0, 0, new ArrayList<PathObject>(), visited, 0, jonSnow, strategy,
				this.grid, 0);
		GenericSearchDS currentDS = makeQ(initialState);

		// Loop till DS is empty
		while (true) {
			if (currentDS.isEmpty())
				return null;

			Node firstNode = currentDS.dequeue();
			WesterosNode castedNode = (WesterosNode) firstNode;

			if (goalTest(firstNode)) {
				// Remove the last move since it's an extra move after reaching
				// the goal
				castedNode.getPath().remove(castedNode.getPath().size() - 1);
				Solution solution = new Solution(castedNode.getPath(), castedNode.getCost(),
						this.getSequenceofExpansion().size(), strategy);
				if (this.visualization) {
					this.visualize(solution);
				}
				return solution;
			} else {

				// Expand only if strategy is not ID, or it is ID and we are
				// within the level limit
				if (!castedNode.getStrategy().equals(SearchStrategies.ID)
						|| (castedNode.getStrategy().equals(SearchStrategies.ID)
								&& castedNode.getLevel() <= this.levelLimit)) {
					ArrayList<Node> nodes = this.expand(firstNode);
					currentDS.enqueue(nodes);
				}
			}

		}

	}

	@Override
	ArrayList<Node> expand(Node node) {

		WesterosNode castedNode = (WesterosNode) (node);
		this.getSequenceofExpansion().add(castedNode);
		// get current state attributes
		int whiteWalkersKilled = castedNode.getWhiteWalkersKilled();
		ArrayList<PathObject> path = castedNode.getPath();
		boolean[][] oldVisited = castedNode.getVisited();
		int remainingDragonGlasses = castedNode.getDragonGlassLeft();
		int xPosition = castedNode.getPosition().getX();
		int yPosition = castedNode.getPosition().getY();

		SearchStrategies strategy = castedNode.getStrategy();
		int pathCost = castedNode.getCost(); // where to get the real cost?
		int oldLevel = castedNode.getLevel();
		String[][] nodeGrid = castedNode.getGrid();

		String currentCell = this.world.getWorld()[yPosition][xPosition];

		// update the current cell as visited
		oldVisited[yPosition][xPosition] = true;

		// copy the the old grid and old visited
		String[][] newNodeGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
		boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
		for (int i = 0; i < nodeGrid.length; i++)
			for (int j = 0; j < nodeGrid[i].length; j++) {
				newNodeGrid[i][j] = nodeGrid[i][j];
				newVisited[i][j] = oldVisited[i][j];
			}

		// Recharge dragonglass if on dragonstone cell
		if (currentCell == "D") {
			// Reset visited only if our goal was the dragonstone
			if (remainingDragonGlasses == 0)
				oldVisited = new boolean[world.getWorldRows()][world.getWorldCols()];
			remainingDragonGlasses = this.world.getCapacityOfDG();

		}

		// Sufficient dragonglass, look for white walkers
		if (remainingDragonGlasses > 0) {

			int totalSurroundingWhiteWalkers = 0;

			// check 4 neighboring cells and kill white walkers if found
		for (int direction = 0; direction < xList.length; direction++) {
				if ((yPosition + yList[direction]) >= 0 && (xPosition + xList[direction]) >= 0
						&& (yPosition + yList[direction]) < grid.length && (xPosition + xList[direction]) < grid.length
						&& nodeGrid[yPosition + yList[direction]][xPosition + xList[direction]] == "W") {
					totalSurroundingWhiteWalkers += 1;
					newNodeGrid[yPosition + yList[direction]][xPosition + xList[direction]] = "E";
				}
			}

			if (totalSurroundingWhiteWalkers > 0 && remainingDragonGlasses > 0) {
				whiteWalkersKilled += totalSurroundingWhiteWalkers;
				int oldDragonGlasses = remainingDragonGlasses;
				remainingDragonGlasses -= 1;
				// Path cost is increased by KILL_COST when using a dragon glass
				pathCost += KILL_COST;

				// no dragon-glass found, reset visited array and new goal is
				// dragon-stone
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
		// All white walkers killed?
		return ((WesterosNode) node).getWhiteWalkersKilled() == this.world.getWhiteWalkersCapacity();
	}

	// Checks if the move is a valid one
	private boolean canMove(int i, boolean[][] visited, String[][] grid, int xPosition, int yPosition) {

		return (yPosition + yList[i]) >= 0 && (xPosition + xList[i]) >= 0 && (yPosition + yList[i]) < grid.length
				&& (xPosition + xList[i]) < grid.length && !visited[yPosition + yList[i]][xPosition + xList[i]]
				&& !grid[yPosition + yList[i]][xPosition + xList[i]].equals("W")
				&& !grid[yPosition][xPosition + xList[i]].equals("O");
	}

	private int calculateEstimate(int yPosition, int xPosition, int whiteWalkersKilled, SearchStrategies strategy) {
		int estimate = 0;
		switch (strategy) {
		case GR1:
		case AS1:
			estimate = h1(yPosition, xPosition);
			break;
		case GR2:
		case AS2:
			estimate = h2(yPosition, xPosition, whiteWalkersKilled);
			break;
		default:
			break;
		}
		return estimate;
	}

	private ArrayList<Node> getNeighbouringCells(int whiteWalkersKilled, ArrayList<PathObject> path,
			boolean[][] visited, int dragonGlassesLeft, int xPosition, int yPosition, SearchStrategies strategy,
			int pathCost, String[][] grid, int level) {
		ArrayList<Node> returnedNodes = new ArrayList<Node>();

		// Check the 4 directions
		for (int iteration = 0; iteration < xList.length; iteration++) {
			// Can the player move in that direction?
			if (canMove(iteration, visited, grid, xPosition, yPosition)) {

				// Copy old values
				boolean[][] newVisited = new boolean[this.world.getWorldRows()][this.world.getWorldCols()];
				String[][] newGrid = new String[this.world.getWorldRows()][this.world.getWorldCols()];
				// Visited and Grid are always the same dimensions
				for (int i = 0; i < visited.length; i++)
					for (int j = 0; j < visited[i].length; j++) {
						newVisited[i][j] = visited[i][j];
						newGrid[i][j] = grid[i][j];
					}

				ArrayList<PathObject> newPath = new ArrayList<PathObject>();

				for (PathObject currentDirection : path)
					newPath.add(currentDirection);

				// Add the new direction to the path array
				newPath.add(new PathObject(direction[iteration], newGrid, (xPosition + xList[iteration]),
						(yPosition + yList[iteration])));

				// Calculate estimates cost for this direction
				int estimate = calculateEstimate(yPosition + yList[iteration], xPosition + xList[iteration],
						whiteWalkersKilled, strategy);
				Position newPos = new Position(xPosition + xList[iteration], yPosition + yList[iteration]);
				Node newNode = new WesterosNode(whiteWalkersKilled, pathCost + STEP_COST, newPath, newVisited,
						dragonGlassesLeft, newPos, strategy, newGrid, level, estimate);
				returnedNodes.add(newNode);
			}
		}

		return returnedNodes;
	}

	public boolean isVisualization() {
		return visualization;
	}

	public void setVisualization(boolean visualization) {
		this.visualization = visualization;
	}

	private void visualize(Solution solution) {
		System.out.println("Visualizing..");
		ArrayList<PathObject> path = solution.getPath();

		System.out.println("Original Grid\n");
		this.printGrid(grid, true);

		for (PathObject pathObj : path) {
			System.out.println("Moving: " + pathObj.getDirection() + "\n");
			String[][] grid = pathObj.getGrid();
			grid[pathObj.getJonY()][pathObj.getJonX()] = "J";
			this.printGrid(grid, false);
			System.out.println();
		}
		System.out.println("Finished Visualization.");
	}

	private void printGrid(String[][] grid, boolean original) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (i == grid.length - 1 && j == grid[i].length - 1 && original) {
					System.out.print("J ");
				} else {
					System.out.print(grid[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println("------------------------\n");
	}

	@Override
	Solution executeSearch(SearchStrategies strategy, boolean visualization) {
		// TODO Auto-generated method stub
		return this.search(this.world.getWorld(), strategy, visualization);
	}

}
