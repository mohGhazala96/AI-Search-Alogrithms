
public class EndGameNode extends Node{
	
	
	public EndGameNode(Node parent, String action) {
		super();
		state = parent.state.getState(action);
		depth = parent.depth + 1;
		parent_node = parent;
		operator = action;
	}

	public EndGameNode(String input) {
		super();
		state = new EndGameState(input);
	}
	
	public Node getNode(String action) {
		return new EndGameNode(this, action);
	}

	public int getPathCost() {
		return 100 - ((EndGameState)state).health;
	}
}
