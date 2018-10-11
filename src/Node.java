
@SuppressWarnings("rawtypes")
public class Node implements Comparable {
	private int cost;
	private int level;
	private SearchStrategies strategy;

	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Node(int cost, int level, SearchStrategies strategy) {
		super();
		this.cost = cost;
		this.level = level;
		this.strategy = strategy;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String toString() {
		return "Cost: " + this.getCost();
	}
	
	public SearchStrategies getStrategy() {
		return strategy;
	}

	public void setStrategy(SearchStrategies strategy) {
		this.strategy = strategy;
	}	

	@Override
	public int compareTo(Object o) {
		switch(strategy)
		{
			case DF: return 1;
			case BF: return -1;
			case UC: return this.getCost() - ((WesterosNode)o).getCost();
		}
		
		return 0;
	}
	
}
