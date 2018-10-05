
public class Main {
	
	public static void main(String [] args){
		SaveWesteros westeros = new SaveWesteros();
		WesterosWorld world = new WesterosWorld();
		String [][] grid = world.genGrid();
		westeros.search(grid, SearchStrategies.BF , false);
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
	}
}
