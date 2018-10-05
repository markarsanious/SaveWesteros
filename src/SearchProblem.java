
public abstract class SearchProblem {
	abstract Solution search(String[][] grid, SearchStrategies strategy, boolean visualization);
	abstract Solution BFS(GenericSearchDS currentNodes);
	abstract Solution DFS(GenericSearchDS currentNodes);
	abstract Solution UCS(GenericSearchDS currentNodes);
	abstract Solution IDS(GenericSearchDS currentNodes);
	abstract Solution GR1(GenericSearchDS currentNodes);
	abstract Solution GR2(GenericSearchDS currentNodes);
	abstract Solution AS1(GenericSearchDS currentNodes);
	abstract Solution AS2(GenericSearchDS currentNodes);
}
