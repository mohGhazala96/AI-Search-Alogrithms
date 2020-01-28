import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GenericSearchProcedure {

	static HashSet<String> visitedNodes;
	public static double finishTime;
public Node GenericSearchProcedure(SearchProblem problem,SearchStrategy search) {
	visitedNodes = new HashSet<String>();
	search.enqueue(problem.initNode());
    long start = System.currentTimeMillis();
	while(!search.isEmpty()) {
		
		Node curr = search.getNext();
//		System.out.println(((EndGameState)curr.state).stonesCollected);
		if(problem.GoalTest(curr)) {
		    long end = System.currentTimeMillis(); 
		    finishTime= ((end-start) /1000f );
//			System.out.println("success");
			return curr;
		}
		Node[] expandedNodes = curr.expand(problem.getActions());
//		visitedNodes.add(curr.state.getUniqueString());
		
		for (Node node: expandedNodes) {
			if(!isRepeated(node)) {
				search.enqueue(node);
				visitedNodes.add(node.state.getUniqueString());
			}
		}
		
	}

//	System.out.println("fail");
	return null;
}

public boolean isRepeated(Node check) {
	  return visitedNodes.contains(check.state.getUniqueString());
}

}
