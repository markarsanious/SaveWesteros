import java.util.ArrayList;

public abstract class SearchProblem {
	abstract Solution BFS();
	abstract Solution DFS();
	abstract Solution UCS();
	abstract Solution IDS();
	abstract Solution GR1();
	abstract Solution GR2();
	abstract Solution AS1();
	abstract Solution AS2();
	abstract Solution genericSearch(SearchStrategies strategy);
	abstract ArrayList<Node> expand(Node node);
	abstract GenericSearchDS makeQ(Node node);
	abstract boolean goalTest(Node node);
	
}
