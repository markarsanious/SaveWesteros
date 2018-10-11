
public class Main {
	
	public static void main(String [] args){
		WesterosWorld world = new WesterosWorld(4, 4);
		String [][] grid = world.getWorld();
		SaveWesteros westeros = new SaveWesteros(world);
		// westeros.search(grid, SearchStrategies.BF , false);
		System.out.println("World Map:\n ");
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length; j++)
			{
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\nCapacity of Dragon Glass: " + world.getCapacityOfDG());
		Solution solution = westeros.search(grid, SearchStrategies.GR2, false);
		if(solution != null) {
			System.out.println(solution.toString());	
		} else {
			System.out.println("No solution was found.");
		}
	}
}
