import java.util.PriorityQueue;

public class AStarSearch extends SearchStrategy {

	boolean h1;
	boolean h2;
	public AStarSearch(boolean h1, boolean h2) {
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
		node.state.cost = true;
		((PriorityQueue<Node>)nodes).add(node);
	}

	@Override
	protected boolean isEmpty() {
		// TODO Auto-generated method stub
		return nodes.isEmpty();
	}


}
