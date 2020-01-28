import java.util.PriorityQueue;
import java.util.Stack;

public class IterativeDeepeningSearch extends SearchStrategy {

	int depth;
	Node head;
	boolean reached_depth;
	
	public IterativeDeepeningSearch() {
		depth = 0;
		nodes = new Stack<Node>();
	}
	
	@Override
	Node getNext() {
		if (nodes.size()== 0 && reached_depth) {
			depth++;
			reached_depth = false;
			GenericSearchProcedure.visitedNodes.clear();
			return head;
		}
		return ((Stack<Node>)nodes).pop();
	}

	@Override
	void enqueue(Node node) {
		if (node.operator == "kill") {
			return;
		}
		if (node.depth == 0) {
			head = node;
			((Stack<Node>)nodes).push(node);
		}
		if (node.depth <= depth) {
			((Stack<Node>)nodes).push(node);
		} else {
			reached_depth = true;
		}
	}

	@Override
	protected boolean isEmpty() {
		return nodes.size()==0 && (!reached_depth);
	}

}
