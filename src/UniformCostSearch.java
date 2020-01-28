import java.util.PriorityQueue;
import java.util.Queue;

public class UniformCostSearch extends SearchStrategy {

	public UniformCostSearch() {
		nodes = new PriorityQueue<Node>();
	}
	
	@Override
	Node getNext() {
		return ((PriorityQueue<Node>)nodes).remove();
	}

	@Override
	void enqueue(Node node) {
		((PriorityQueue<Node>)nodes).add(node);
	}

	@Override
	protected boolean isEmpty() {
		// TODO Auto-generated method stub
		return nodes.isEmpty();
	}

}
