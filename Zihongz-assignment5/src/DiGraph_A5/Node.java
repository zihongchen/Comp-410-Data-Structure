package DiGraph_A5;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Node {
	 String label;
	 long ID;
	 Map<String, Edge> inEdge = new HashMap<String, Edge>();
     Map<String, Edge> outEdge = new HashMap<String, Edge>();
	 int flag;
	 long dist;//min dist 

	public Node(long ID,String label) {
		this.label = label;
		this.ID = ID;
		
	}

	public boolean addEdge(long idNum,String source, String destin, long weight) {
		if (source == label) {// out edge
			if (outEdge.containsKey(destin)) {// the edge already exist
				return false;
			} else {// create the value
				Edge E = new Edge(idNum,source, destin, weight);
				outEdge.put(destin, E);
				return true;
			}

		} else if (destin == label) {// in edge
			if (inEdge.containsKey(source)) {// the edge already exist
				return false;
			} else {// create the value
				Edge E = new Edge(idNum,source, destin, weight);
				outEdge.put(source, E);
				return true;
			}

		}
		
		return false;

	}

}
