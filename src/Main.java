import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.xml.transform.SourceLocator;

public class Main {
		
	public static void display(ArrayList v) {
       for(Object o: v) {
    	   System.out.println(o);
       }
    }
	@SuppressWarnings("unchecked")
	public static void main(String [] args) {
		
		WesterosWorld world = new WesterosWorld(4, 4);
		SaveWesteros westeros = new SaveWesteros(world);

		System.out.println("World Map:\n ");
		System.out.print(world);
		System.out.println("\nCapacity of Dragon Glass: " + world.getCapacityOfDG());
		
		System.out.println("\nSearching for solution...");

		ArrayList<Solution> solutions = new ArrayList<Solution>();
		for (SearchStrategies strategy : SearchStrategies.values()) {
			// do what you want
			Solution solution;
			solution = westeros.executeSearch(strategy, false);
			if(solution != null) {
				solutions.add(solution);
			}
		}
		
		System.out.println("\nFinished searching...\n-----------------\n");
		if(solutions.size() > 0) {
			Collections.sort(solutions, new SortByCost());
			System.out.println("Printing optimal solution: ");
			System.out.println(solutions.get(0));
			
			System.out.println("-----------------");
			
			Collections.sort(solutions, new SortByNodesExpanded());
			display(solutions);

			System.out.println("Printing most efficent solution:");
			System.out.println(solutions.get(0));	
		} else {
			System.out.println("No solution was found.");
		}
	}
}
