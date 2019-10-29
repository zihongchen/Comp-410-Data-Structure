package MinBinHeap_A3;

public class MinBinHeap_Playground {
  public static void main(String[] args){   
    //Add more tests as methods and call them here!!
    TestBuild();
  }
  
  public static void TestBuild(){ 
    // constructs a new minbinheap, constructs an array of EntryPair, 
    // passes it into build function. Then print collection and heap.
    MinBinHeap mbh= new MinBinHeap();
    EntryPair[] collection= new EntryPair[2];
    collection[0]=new EntryPair("a",3);
    collection[1]=new EntryPair("b",8);
   

    mbh.build(collection);
    printHeapCollection(collection);
    printHeap(mbh.getHeap(), mbh.size());
    mbh.insert(new EntryPair("c",1));
    mbh.insert(new EntryPair("d",6));
    mbh.insert(new EntryPair("e",5));
    mbh.insert(new EntryPair("f",4));
    mbh.insert(new EntryPair("g",7));
    mbh.insert(new EntryPair("h",2));
    mbh.insert(new EntryPair("i",9));
    mbh.insert(new EntryPair("j",0));
    /*mbh.insert(new EntryPair("c",11));
    mbh.insert(new EntryPair("d",16));
    mbh.insert(new EntryPair("e",15));
    mbh.insert(new EntryPair("f",14));
    mbh.insert(new EntryPair("g",17));
    mbh.insert(new EntryPair("h",12));
    mbh.insert(new EntryPair("i",19));
    mbh.insert(new EntryPair("j",20));
    mbh.insert(new EntryPair("c",21));
    mbh.insert(new EntryPair("d",26));
    mbh.insert(new EntryPair("e",25));
    mbh.insert(new EntryPair("f",24));
    mbh.insert(new EntryPair("g",27));
    mbh.insert(new EntryPair("h",22));
    mbh.insert(new EntryPair("i",29));
    mbh.insert(new EntryPair("j",30));*/
    System.out.println(mbh.size());
    printHeap(mbh.getHeap(), mbh.size());
    
    
    
    
    mbh.delMin();
    System.out.println(mbh.size());
    printHeap(mbh.getHeap(), mbh.size());
    mbh.delMin();
    System.out.println(mbh.size());
    printHeap(mbh.getHeap(), mbh.size());
    mbh.delMin();
    System.out.println(mbh.size());
    
    
    
    
  }
  
  public static void printHeapCollection(EntryPair[] e) { 
    //this will print the entirety of an array of entry pairs you will pass 
    //to your build function.
    System.out.println("Printing Collection to pass in to build function:");
    for(int i=0;i < e.length;i++){
      System.out.print("("+e[i].value+","+e[i].priority+")\t");
    }
    System.out.print("\n");
  }
  
  public static void printHeap(EntryPair[] e,int len) { 
    //pass in mbh.getHeap(),mbh.size()... this method skips over unused 0th index....
    System.out.println("Printing Heap");
    for(int i=1;i < len+1;i++){
      System.out.print("("+e[i].value+","+e[i].priority+")\t");
    }
    System.out.print("\n");
  }
}
