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
		
		while (isomorphism.keySet().size() < g.size()){
		
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
		
			if (isomorphism.keySet().size() != g.size()){
				
				Set<Node> gSet = gEquivSets.get(0);
				Set<Node> hSet = hEquivSets.get(0);
				Node gNode = gSet.iterator().next();
				Node hNodeAcceptable = null;
				for (Node hNode : hSet){
					boolean acceptable = true;
					for (Node gNeighbor : gNode.getNeighbors()){
						if (isomorphism.containsKey(gNeighbor)){
							if (!hNode.hasNeighbor(isomorphism.get(gNeighbor))){
								acceptable = false;
							}
						}
					}
					if (acceptable){
						for (Node hNeighbor : hNode.getNeighbors()){
							if(isomorphism.values().contains(hNeighbor)){
								if (!gNode.hasNeighbor(getInvertedMapKey(isomorphism, hNeighbor))){
									acceptable = false;
								}
							}
						}
					}
					if (acceptable){
						hNodeAcceptable = hNode;
						break;
					}
				}
				if (hNodeAcceptable == null){
					return null;
				}
				isomorphism.put(gNode, hNodeAcceptable);
				gSet.remove(gNode);
				hSet.remove(hNodeAcceptable);
			}	
		}
		if (!verifyIsomorphism(isomorphism)){
			return null;
		}
		
		return isomorphism;
	}
	
	public static Node getInvertedMapKey(Map<Node, Node> map, Node value){
		for(Node n : map.keySet()){
			if (map.get(n) == value){
				return n;
			}
		}
		return null;
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
