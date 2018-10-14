
public class Main {
	
	public static void main(String [] args) {
		
		WesterosWorld world = new WesterosWorld(6, 6);
		SaveWesteros westeros = new SaveWesteros(world);

		System.out.println("World Map:\n ");
		System.out.print(world);
		System.out.println("\nCapacity of Dragon Glass: " + world.getCapacityOfDG());

		Solution solution = westeros.search(world.getWorld(), SearchStrategies.UC, true);
		
		if(solution != null) {
			System.out.println(solution);	
		} else {
			System.out.println("No solution was found.");
		}
	}
}
