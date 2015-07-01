package isomorphsim;

import graphs.Graph;
import graphs.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IsomorphismSolver {

	public static Map<Node, Node> constructIsomorphism(Graph g, Graph h){
		Map<Node, Node> isomorphism = new HashMap<Node, Node>();
		
		List<Set<Node>> gEquivSets = PathsToSelf.establishEquivalencySets(g);
		List<Set<Node>> hEquivSets = PathsToSelf.establishEquivalencySets(h);
		
		if (gEquivSets.size() != hEquivSets.size()){
			return null;
		}
		
		for(int i = 0; i < gEquivSets.size(); i++){
			Set<Node> gSet = gEquivSets.get(i);
			Set<Node> hSet = hEquivSets.get(i);
			if (gSet.size() != hSet.size()){
				return null;
			} else if (gSet.size() == 1){
				isomorphism.put(gSet.iterator().next(), hSet.iterator().next());
				gEquivSets.remove(gSet);
				hEquivSets.remove(hSet);
				i--;
			} 
			// Otherwise, skip, until we must make a choice.
		}
		
		while (isomorphism.keySet().size() < g.size()){
			
			// THE TRICKY PART
			
		}
		return isomorphism;
	}
	
	public static boolean verifyIsomorphism(Map<Node, Node> iso){
		for (Node n1 : iso.keySet()){
			Node n2 = iso.get(n1);
			for (Node m1 : n1.getNeighbors()){
				if (!n2.getNeighbors().contains(iso.get(m1))){
					return false;
				}
			}
		}
		return true;
	}
}
