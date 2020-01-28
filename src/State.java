
public abstract class State implements Comparable{
	boolean valid;
	boolean accepted;
	

	boolean h1 = false;
	boolean h2 = false;
	boolean cost = true;
	
	protected State() {
		
	}
	
	public State(String input){
		
	}
	
	public abstract String getUniqueString();

	public abstract State getState(String action);
}
