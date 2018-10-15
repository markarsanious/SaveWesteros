import java.util.ArrayList;

public abstract class SearchProblem {
	private ArrayList<WesterosNode> sequenceofExpansion;
	private int levelLimit;

	abstract Solution genericSearch(SearchStrategies strategy);
	abstract ArrayList<Node> expand(Node node);
	abstract GenericSearchDS makeQ(Node node);
	abstract boolean goalTest(Node node);
	abstract Solution executeSearch(SearchStrategies strategy, boolean visualization);
	
	SearchProblem() {
		this.sequenceofExpansion = new ArrayList<>();
		this.setLevelLimit(0);
	}

	public Solution BFS() {
		return genericSearch(SearchStrategies.BF);
	}

	public Solution DFS() {
		return genericSearch(SearchStrategies.DF);
	}

	public Solution UCS() {
		return genericSearch(SearchStrategies.UC);
	}

	public Solution IDS() {
		int nodesExpandedOld = 0;
		Solution solution = null;

		do {
			nodesExpandedOld = this.sequenceofExpansion.size();
			this.sequenceofExpansion = new ArrayList<>();

			solution = genericSearch(SearchStrategies.ID);

			if (solution != null)
				break;

			this.setLevelLimit(this.getLevelLimit() + 1);
		} while (nodesExpandedOld != this.sequenceofExpansion.size());

		return solution;
	}

	public Solution GR1() {
		return genericSearch(SearchStrategies.GR1);
	}

	public Solution GR2() {
		return genericSearch(SearchStrategies.GR2);
	}

	public Solution AS1() {
		return genericSearch(SearchStrategies.AS1);
	}

	public Solution AS2() {
		return genericSearch(SearchStrategies.AS2);
	}

	public ArrayList<WesterosNode> getSequenceofExpansion() {
		return sequenceofExpansion;
	}

	public void setSequenceofExpansion(ArrayList<WesterosNode> sequenceofExpansion) {
		this.sequenceofExpansion = sequenceofExpansion;
	}	

	public int getLevelLimit() {
		return levelLimit;
	}

	public void setLevelLimit(int levelLimit) {
		this.levelLimit = levelLimit;
	}
	
}
