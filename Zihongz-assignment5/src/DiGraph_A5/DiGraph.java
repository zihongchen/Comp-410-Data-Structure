package DiGraph_A5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class DiGraph implements DiGraphInterface {
	HashMap<String, Node> content = new HashMap<String, Node>();  //key:label, value: Node
	HashSet<Long> nodeID = new HashSet<Long>();//store all nodes' id
	HashSet<Long> edgeID = new HashSet<Long>();//store all edges' id

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
  }
  
  // rest of your code to implement the various operations
  
  public boolean addNode(long idNum, String label)
  {
	  if(idNum<0 || label==null)
		  return false;
	  else if(content.containsKey(label)==false && nodeID.contains(idNum)==false)
	  {
		  Node newNode = new Node(idNum,label);
		  content.put(label, newNode);
		  nodeID.add(idNum);
		  return true;
	  }
	  else
		  return false;
		 		  
  }
  
  public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel)
  {
	  if(idNum<0 || edgeID.contains(idNum)==true)
		  return false;
	  else if(content.containsKey(sLabel)==false || content.containsKey(dLabel)==false)
		  return false;
	  else 
	  {
		  Node sourceNode = content.get(sLabel);
		  Node destiNode  = content.get(dLabel);
		  if(sourceNode.outEdge.containsKey(dLabel) && destiNode.inEdge.containsKey(sLabel))
			  return false;
		  else //add edge
		  {
			  Edge newEdge = new Edge(idNum,sLabel,dLabel,weight);
			  edgeID.add(idNum);
			  sourceNode.outEdge.put(dLabel, newEdge);
			  destiNode.inEdge.put(sLabel, newEdge);
			
			  return true;
		  }
		 
	  }
  }
  /*
  
   *  delNode
        in: string 
              label for the node to remove
        out: boolean
               return false if the node does not exist
               return true if the node is found and successfully removed
      delEdge
        in: string label for source node
            string label for destination node
        out: boolean
               return false if the edge does not exist
               return true if the edge is found and successfully removed
   */
public boolean delNode(String label)
{	
	  if(content.containsKey(label)==false)
		  return false;
	  else
	  {
		  Node remNode = content.get(label);
		
		  Iterator<String> InEdge_sL = remNode.inEdge.keySet().iterator();
		  Iterator<String> OutEdge_dL = remNode.outEdge.keySet().iterator();
		  
		  while(InEdge_sL.hasNext())
			  delEdge(InEdge_sL.next(),remNode.label);
		  while(OutEdge_dL.hasNext()) 			
			  delEdge(remNode.label,OutEdge_dL.next());
		  
		  content.remove(label,remNode);
		  nodeID.remove(remNode.ID);
		  return true;
	  }
}
  
  
  public boolean delEdge(String sLabel, String dLabel)
  {
	  if(content.containsKey(sLabel)==false || content.containsKey(dLabel)==false)
		  return false;
	  else 
	  {
		  Node sourceNode = content.get(sLabel);
		  Node destiNode = content.get(dLabel);
		  if(sourceNode.outEdge.containsKey(dLabel))
		  {
			  Edge remEdge = content.get(sLabel).outEdge.get(dLabel);
			  sourceNode.outEdge.remove(dLabel,remEdge);
		      destiNode.inEdge.remove(sLabel,remEdge);
 
			  edgeID.remove(remEdge.ID);
			  return true;
		  }
		  return false;
	  }
  }
  
  public long numNodes()
  {
    return nodeID.size();
  }
  
  public long numEdges()
  {
    return edgeID.size();
  }
  
  
   public ShortestPathInfo[] shortestPath(String label)
   {
	   int size = nodeID.size();
	   ShortestPathInfo[] sp = new ShortestPathInfo[size];
	   MinBinHeap PQ= new MinBinHeap();
	   EntryPair sNode = new EntryPair(label,0);
	   PQ.insert(sNode);
	   sp[0]= new ShortestPathInfo(label,0);
	   int i = 0;
	   while(PQ.size()!=0)
	   {
		   Node currNode = content.get(PQ.getMin().value);
		   long currDist = PQ.getMin().priority;
		   PQ.delMin();
		   if(currNode.flag==1)//known
			   continue;
		   else
		   {
			   sp[i]= new ShortestPathInfo(currNode.label,currDist);  
			   currNode.flag=1;
		   }
		   Iterator<Edge> adjNode = currNode.outEdge.values().iterator();//adjacent nodes
		   while(adjNode.hasNext())
		   {
			   Node aNode = content.get(adjNode.next().destin);
			   if(aNode.flag==0)//unknown Node
			   {
				   long newDist = currDist+currNode.outEdge.get(aNode.label).weight;
				   if(aNode.dist==0 || aNode.dist>newDist)
				   {
					   aNode.dist = newDist;
					   EntryPair a = new EntryPair(aNode.label,aNode.dist);
					   PQ.insert(a);
				   }
				 
			   }
		   }	
		   i++;
	   }
	   
	   if(i<size)
	   {
		   for(Node node:content.values())
		   {
			   if(node.flag==0)
			   {
				   node.dist=-1;
				   sp[i] = new ShortestPathInfo(node.label,node.dist);
				   i++;
			   }
			   
		   }
	   }	  
	  
	   return sp;
   }
}