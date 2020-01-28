
public abstract class SearchProblem {
	String input;
	
	public abstract Node initNode();
	public abstract boolean GoalTest(Node testNode);
	public abstract String[] getActions();
}
