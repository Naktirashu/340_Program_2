
public class SubNode {

	private String name = "";
	private int weight = 0;

	private boolean isGoalNode = false;

	public SubNode(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isGoalNode() {
		return isGoalNode;
	}

	public void setGoalNode(boolean isGoalNode) {
		this.isGoalNode = isGoalNode;
	}
	
	
}
