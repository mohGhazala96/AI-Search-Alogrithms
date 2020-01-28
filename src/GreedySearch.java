import java.util.PriorityQueue;

public class GreedySearch extends SearchStrategy {

	boolean h1;
	boolean h2;
	public GreedySearch(boolean h1, boolean h2) {
		this.h1 = h1;
		this.h2 = h2;
		nodes = new PriorityQueue<Node>();
	}
	
	@Override
	Node getNext() {
		return ((PriorityQueue<Node>)nodes).remove();
	}

	@Override
	void enqueue(Node node) {
		node.state.h1 = h1;
		node.state.h2 = h2;
		node.state.cost = false;
		((PriorityQueue<Node>)nodes).add(node);
	}

	@Override
	protected boolean isEmpty() {
		return nodes.isEmpty();
	}
}
