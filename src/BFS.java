import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS extends SearchStrategy {

	public BFS() {
		nodes = new LinkedList<Node>();
	}
	
	@Override
	Node getNext() {
		return ((Queue<Node>)nodes).poll();
	}

	@Override
	void enqueue(Node node) {
		((Queue<Node>)nodes).add(node);
	}

	@Override
	protected boolean isEmpty() {
		// TODO Auto-generated method stub
		return nodes.isEmpty();
	}

}
