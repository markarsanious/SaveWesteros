import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Prolog {
	
	private static String[][] world; //2D array representing the grid
	private static int capacityOfDG; //capacity of dragon class Jon Snow can hold
	private static int whiteWalkersCapacity;
	private static int worldRows;
	private static int worldCols;
	public static int obstaclesCapacity;
	private static String KB = "";
	private static int[][] whiteWalkersPositions;
	
	// string constants
	private static final String EMPTY = "E";
	private static final String DRAGON_GLASS = "D";
	private static final String WHITE_WALKER = "W";
	private static final String OBSTACLE = "O";
	private static void setRandomVars() {
		capacityOfDG = (int)(Math.random() * 5) +1;
		whiteWalkersCapacity = (int)(Math.random() * ((worldRows*worldCols)/2)) + 1;
		whiteWalkersPositions = new int[whiteWalkersCapacity][2];
		obstaclesCapacity = (int)(Math.random() * ((worldRows*worldCols)/8)) + 1; 
	}
	
	private static void addToKb(String element, int i, int j) {
		String statement = "";
		switch(element) {
			case "E":
				statement = "empty(" + i +"," + j + ")";
				break;
			case "W":
				statement = "whiteWalker(" + i +"," + j + ")";
				break;
			case "O":
				statement = "obstacle(" + i +"," + j + ")";
				break;
			case "D":
				statement = "dragonGlass(" + i +"," + j + ")";
				break;
			case "J":
				statement = "jonSnow(" + i +"," + j + ")";
				break;
		}
		KB  = KB + statement + ".\n";
	}

	public static void writeKBToFile() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("kb.pl", "UTF-8");
			writer.print(KB);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void addDefaultsToKB() {
		addToKb("J", worldRows - 1, worldCols - 1);
		KB +=  "maxGlass(" + capacityOfDG + ").\n";
		KB +=  "remWalkers(" + whiteWalkersCapacity + ").\n";
		KB += "gridShape(" + worldRows + "," + worldCols + ").\n";
	}

	public static String[][] genGrid(int x, int y) {
		worldRows = y;
		worldCols = x;
		setRandomVars();
		//generate empty world with JonSnow
		String[][] world = new String[worldRows][worldCols];
		
		for(int i=0; i<world.length; i++)
		{
			for(int j=0; j<world[i].length; j++)
			{
				world[i][j] = EMPTY;
			}
		}
		
		//decide position of DragonStone
		int dSRow = (int)(Math.random()*worldRows);
		int dSCol = (int)(Math.random()*worldCols);
		while(dSRow == worldRows-1 && dSCol == worldCols-1)
		{
			dSRow = (int)(Math.random()*worldRows);
			dSCol = (int)(Math.random()*worldCols);
		}

		world[dSRow][dSCol] = DRAGON_GLASS;
		addToKb(DRAGON_GLASS, dSRow, dSCol);

		//position white walkers
		int whiteWalkersCreated = 0;
		int obstaclesCreated = 0;
		while(whiteWalkersCreated<whiteWalkersCapacity)
		{
			int randomRow = (int)(Math.random()*worldRows);			
			int randomCol = (int)(Math.random()*worldCols);
			if(world[randomRow][randomCol] == EMPTY)
			{
				world[randomRow][randomCol] = WHITE_WALKER;
				addToKb(WHITE_WALKER, randomRow, randomCol);
				whiteWalkersPositions[whiteWalkersCreated][0]= randomRow;
				whiteWalkersPositions[whiteWalkersCreated][1]= randomCol;
				whiteWalkersCreated++;
			}
		}
		
		//position obstacles
		while(obstaclesCreated<obstaclesCapacity)
		{
			int randomRow = (int)(Math.random()*worldRows);			
			int randomCol = (int)(Math.random()*worldCols);
			if(world[randomRow][randomCol] == EMPTY)
			{
				world[randomRow][randomCol] = OBSTACLE;
				addToKb(OBSTACLE, randomRow, randomCol);
				obstaclesCreated++;
			}
		}
		addDefaultsToKB();
		return world;
	}
	
	private static void printGrid(String[][] grid, boolean original) {
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

	public static void main(String[] args) {
		String[][] world = genGrid(5, 5);
		printGrid(world, true);
		writeKBToFile();
		System.out.println(KB);
	}
}
