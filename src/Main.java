import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Main {
	static int rows;
	static int cols;

	public static String solve(String grid, String strategy, boolean visualize) {
		EndGameProblem endProb = new EndGameProblem(grid);
		BFS bfs = new BFS();
		DFS dfs = new DFS();
		UniformCostSearch uc = new UniformCostSearch();
		IterativeDeepeningSearch ida = new IterativeDeepeningSearch();
		GenericSearchProcedure pro = new GenericSearchProcedure();
		switch (strategy) {
		case "BF":
			return getString(pro.GenericSearchProcedure(endProb, bfs),visualize);
		case "DF":
			return getString(pro.GenericSearchProcedure(endProb, dfs),visualize);
		case "ID":
			return getString(pro.GenericSearchProcedure(endProb, ida),visualize);
		case "UC":
			return getString(pro.GenericSearchProcedure(endProb, uc),visualize);
		case "GR1":
			return getString(pro.GenericSearchProcedure(endProb, new GreedySearch(true, false)),visualize);
		case "GR2":
			return getString(pro.GenericSearchProcedure(endProb, new GreedySearch(false, true)),visualize);
		case "AS1":
			return getString(pro.GenericSearchProcedure(endProb, new AStarSearch(true, false)),visualize);
		case "AS2":
			return getString(pro.GenericSearchProcedure(endProb, new AStarSearch(false, true)),visualize);
		}

		// if solution found return the plan(actions)/cost/nodes
		// else
		return "There is no solution";
	}

	public static String getString(Node finalN, boolean visualize) {
		String x = "";
		Stack reversedOrder = new Stack();
		EndGameState lastState = null;
		int health = -1;
  


		while (true) {
			if (finalN.parent_node != null) {
				
				EndGameNode endGameNode= (EndGameNode)finalN.parent_node;
				EndGameState currentState=(EndGameState) endGameNode.state;
				if(health==-1) {
					health= currentState.health;
				}
				HashMap<String, String> currentGrid =currentState.grid;
		
				reversedOrder.push(currentGrid);
				
				x = finalN.operator + "," + x;
				finalN = finalN.parent_node;
			} else {
				break;
			}
		}


		if(visualize) {
			int length = reversedOrder.size();
			for(int i=0;i<length;i++) {
				@SuppressWarnings("unchecked")
				HashMap<String, String> currentGrid = (HashMap<String, String>) reversedOrder.pop();
				
				for( int horizontal=0;horizontal<rows;horizontal++) {
					for( int vertical=0;vertical<cols;vertical++) {
						System.out.print(",");

						if(currentGrid.get(horizontal+","+vertical)==null) {
							System.out.print("_");

						}else {
							System.out.print(currentGrid.get(horizontal+","+vertical));
						}
					}
					System.out.println(" ");
				}
				
				System.out.println("___________");

			}

		}

		System.out.printf(x +health + " ;%.2f", GenericSearchProcedure.finishTime  );
		System.out.printf("\n");
		return x;
	}
}
