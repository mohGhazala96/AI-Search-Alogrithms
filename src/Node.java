import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Node implements Comparable<Node>{
	State state;
	Node parent_node;
	int depth;
	String operator;
	
	protected Node() {
		parent_node = null;
		depth = 0;
		operator = null;
	}
	
	public Node(String input) {
	}
	
	@Override
	public int compareTo(Node other) {
		return state.compareTo(other.state);
	}
	
	public Node[] expand(String[] actions) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for(String action: actions) {
			Node curr = getNode(action);
			if (curr.state.valid)
				nodes.add(curr);
		}
		int size = nodes.size();
		Node[] returnVal = new Node[size];
		for (int i = 0; i < size ; i++)
			returnVal[i] = nodes.get(i);
		return returnVal;
			
	}
	
	public abstract Node getNode(String action);
	public abstract int getPathCost();
}
