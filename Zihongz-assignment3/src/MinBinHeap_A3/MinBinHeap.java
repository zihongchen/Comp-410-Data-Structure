package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will initially
												// be null. This is ok! Just build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for simplicity
													// of child/parent computations...
													// the book/animation page both do this.
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		array[size+1] = entry;
		size++;
		insert(size);

	}
	private void insert(int index) {
		if(index/2 !=0) {
			EntryPair parent = array[index/2];
			if (array[index].priority < parent.priority) {
				EntryPair temp = array[index];
				array[index] = parent;
				array[index/2] = temp;
				insert(index/2);
			}
		}
		
	}

	@Override
	public void delMin() {
		array[1] = array[size];
		array[size] = null;
		size--;
		recursivebuild(1);
	}

	@Override
	public EntryPair getMin() {

		return array[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		size = entries.length;
		for (int i = 0; i < size; i++) {
			array[i + 1] = entries[i];// copy the array
		}
		for (int i = size; i > 0; i--) {
			recursivebuild(i);
		}

	}

	private void recursivebuild(int index) {
		EntryPair left = array[index * 2];
		EntryPair right = array[index *2 +1];
		if (right != null && left != null) {
				
			if (right.priority < left.priority&& right.priority < array[index].priority) {
				EntryPair temp = array[index];
				array[index] = right;
				array[index * 2 + 1] = temp;
				recursivebuild(2 * index + 1);
			}else if (right.priority > left.priority &&left.priority < array[index].priority) {
				EntryPair temp = array[index];
				array[index] = left;
				array[index * 2] = temp;
				recursivebuild(2 * index);
			}

		} else if (right == null && left != null) {
			if (left.priority < array[index].priority) {
				EntryPair temp = array[index];
				array[index] = left;
				array[index * 2] = temp;
			}
			recursivebuild(2 * index);// there is no need since
		} else {
			return;
		}

	}

}