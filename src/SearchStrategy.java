import java.util.Collection;
import java.util.LinkedList;

public abstract class SearchStrategy {
	Collection<Node> nodes;	
	
	abstract Node getNext();
	abstract void enqueue(Node node);
	protected abstract boolean isEmpty();
}
