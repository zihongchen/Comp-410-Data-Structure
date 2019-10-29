package DiGraph_A5;

public class Edge {
	String source;
	String destin;
	long weight;
	long ID;
	
	public Edge(long idNum, String source, String destin, long weight) {
		this.source = source;
		this.ID = idNum;
		this.destin = destin;
		this.weight = weight;
	}
	
	public boolean equal(Edge t) {
		if(t.source == this.source && t.destin == this.destin) {
			return true;
		}else return false;
	}

}
