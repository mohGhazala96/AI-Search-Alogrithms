import java.util.LinkedList;
import java.util.Stack;

public class DFS extends SearchStrategy {

	public DFS() {
		nodes = new Stack<Node>();
	}
	
	@Override
	Node getNext() {
		return ((Stack<Node>)nodes).pop();
	}

	@Override
	void enqueue(Node node) {
		((Stack<Node>)nodes).push(node);
	}

	@Override
	protected boolean isEmpty() {
		// TODO Auto-generated method stub
		return nodes.isEmpty();
	}

}
