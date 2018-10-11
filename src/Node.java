
@SuppressWarnings("rawtypes")
public class Node implements Comparable {
	private int cost;
	private int level;
	private int estimate;
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
	
	public Node(int cost, int level, SearchStrategies strategy, int estimate) {
		super();
		this.cost = cost;
		this.level = level;
		this.strategy = strategy;
		this.estimate = estimate;
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
	
	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	@Override
	public int compareTo(Object o) {
		switch(strategy)
		{
			case DF: return -1;
			case BF: return 1;
			case UC: return this.getCost() - ((WesterosNode)o).getCost();
			case ID: return -1;
			case GR1:
			case GR2: return this.getEstimate() - ((WesterosNode)o).getEstimate();
			case AS1:
			case AS2: return ASearchEstimate(this) - ASearchEstimate((Node)o);
		}
		return 0;
	}
	
	private int ASearchEstimate(Node node)
	{
		return node.getEstimate()+node.getCost();
	}
	
}
