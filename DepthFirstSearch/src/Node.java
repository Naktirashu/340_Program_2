
//example on building the adj list
//http://stackoverflow.com/questions/14783831/java-implementation-of-adjacency-list


public class Node {

	private String name = "";
	
	private boolean isStartNode = false;
	private boolean isGoalNode = false;
	
	private boolean hasPrevious = false;
	
	private int weight = 0;
	
	
	private boolean visted = false;

	public Node(String name, boolean isStartNode, boolean isGoalNode, boolean hasPrevious, int weight) {
		super();
		this.name = name;
		this.isStartNode = isStartNode;
		this.isGoalNode = isGoalNode;
		this.hasPrevious = hasPrevious;
		this.weight = weight;
	}
	
	
	
}
