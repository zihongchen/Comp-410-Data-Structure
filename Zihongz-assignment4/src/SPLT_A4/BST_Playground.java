

package SPLT_A4;

public class BST_Playground {
/*
 * you will test your own BST implementation in here
 *
 * we will replace this with our own when grading, and will
 * do what you should do in here... create BST objects,
 * put data into them, take data out, look for values stored
 * in it, checking size and height, and looking at the BST_Nodes
 * to see if they are all linked up correctly for a BST
 * 
*/
  
  public static void main(String[]args){

  BST temp = new BST();
  
  temp.insert("5");
  temp.insert("3");
  temp.insert("7");
  temp.insert("1");
  temp.insert("4");
  temp.insert("6");
  temp.insert("8");
  
  
  
 // System.out.println(temp.contains("E"));
  printGivenLevel(temp.root,0);
  printGivenLevel(temp.root,1);
  
  printGivenLevel(temp.root,2);
  printGivenLevel(temp.root,5);
  
  }

  
    
  
  static void printGivenLevel(BST_Node root,int level){
    if(root==null)return;
    if(level==0)System.out.print(root.data+" ");
    else if(level>0){
      printGivenLevel(root.left,level-1);
      printGivenLevel(root.right,level-1);
    }
  }
  static void printInOrder(BST_Node root){
  //will print your current tree In-Order
  if(root!=null){
    printInOrder(root.getLeft());
    System.out.print(root.getData() + " ");
    printInOrder(root.getRight());
  }
  }
}
