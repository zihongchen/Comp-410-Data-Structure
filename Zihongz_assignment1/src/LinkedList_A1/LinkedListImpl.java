/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel; // this will be the entry point to your linked list (the head)

	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
		sentinel = new Node(0); // Note that the root's data is not a true part of your data set!
	}

	// implement all methods in interface, and include the getRoot method we made
	// for testing purposes. Feel free to implement private helper methods!

	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {
		if (index > size() || index < 0) {
			return false;
		}else{

		if (index == size()&& index ==0) {
			
				Node insert = new Node(elt);
				insert.prev = sentinel;
				insert.next = sentinel;
				sentinel.prev = insert;
				sentinel.next = insert;	
		
		} 
		else if(index == size()) {
			Node insert = new Node(elt);
			Node original = getNode(index - 1);
			insert.prev = original;
			original.next = insert;
			insert.next = sentinel;
			sentinel.prev = insert;
			
			
		}
		else if (index >= 0 && index < size()) {
			Node insert = new Node(elt);
			Node original = getNode(index);
			original.prev.next = insert;
			insert.prev = original.prev;
			insert.next = original;
			original.prev = insert;

		}
		return true; 	

	}
	}

	@Override
	public boolean remove(int index) {
		if (index >= size() || index < 0) {
			return false;
		} else {
			Node remove = getNode(index);
			remove.prev.next = remove.next;
			remove.next.prev = remove.prev;
			return true;
		}

	}

	@Override
	public double get(int index) {
		Node currentNode = sentinel.next;
		if (index < 0 || index >= size()||currentNode == null) {
			return Double.NaN;
		} else {
			int i = 0;
			while (i < index) {
				currentNode = currentNode.next;
				i++;
			}
		}

		return currentNode.getData();

	}

	public Node getNode(int index) {
		Node currentNode = sentinel.next;
		if (currentNode == null) {
			// return Double.NaN;
		} else {
			int i = 0;
			while (i < index) {
				currentNode = currentNode.next;
				i++;
			}
		}

		return currentNode;
	}

	@Override
	public int size() {
		Node currentNode = sentinel.next; // it points to the first node;
		int num = 0;
		if (currentNode == null) {
			return 0;
		}
		while (currentNode != sentinel) {
			num++;
			currentNode = currentNode.next;

		}

		return num;
	}

	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;

		} else
			return false;
	}

	@Override
	public void clear() {

		
		sentinel.next = null;
		sentinel.prev = null;

	}
}