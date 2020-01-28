
public class EndGameProblem extends SearchProblem{
	
	public EndGameProblem(String input) {
		super();
		this.input = input;
	}
	
	@Override
	public EndGameNode initNode() {
		// TODO Auto-generated method stub
		return new EndGameNode(input);
	}

	@Override
	public boolean GoalTest(Node testNode) {
		return testNode.state.accepted;
	}

	@Override
	public String[] getActions() {
		String[] returnVal = {"snap", "collect", "up", "down", "left", "right", "kill"};
		return returnVal;
	}
}
