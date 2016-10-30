import java.util.ArrayList;

//example on building the adj list
//http://stackoverflow.com/questions/14783831/java-implementation-of-adjacency-list


public class Node {

	private String name = "";
	private ArrayList<SubNode> subNodeArray;
	
	private boolean isStartNode = false;
	private boolean isGoalNode = false;
	
	private boolean hasPrevious = false;
	
	
	private boolean visted = false;

	public Node(String name, boolean isStartNode, boolean isGoalNode, boolean hasPrevious) {
		this.name = name;
		this.isStartNode = isStartNode;
		this.isGoalNode = isGoalNode;
		this.hasPrevious = hasPrevious;
		subNodeArray = new ArrayList<SubNode>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStartNode() {
		return isStartNode;
	}

	public void setStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}

	public boolean isGoalNode() {
		return isGoalNode;
	}

	public void setGoalNode(boolean isGoalNode) {
		this.isGoalNode = isGoalNode;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public boolean isVisted() {
		return visted;
	}

	public void setVisted(boolean visted) {
		this.visted = visted;
	}

	public ArrayList<SubNode> getSubNodeArray() {
		return subNodeArray;
	}

	public void setSubNodeArray(ArrayList<SubNode> subNodeArray) {
		this.subNodeArray = subNodeArray;
	}
	
	
	
}
