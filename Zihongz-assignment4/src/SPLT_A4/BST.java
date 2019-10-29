package SPLT_A4;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;
	int height;

	public BST() {
		size = 0;
		root = null;
		size = 0;
	}// the root is null and add node to it

	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {
		return root;
	}

	@Override
	public boolean insert(String s) {
		root = insert(root, s);
		int originalSize = size;
		insert(root, s);
		if (size > originalSize) {
			return true;
		}
		return false;
	}

	private BST_Node insert(BST_Node temp, String s) {
		if (temp == null) {
			size += 1;
			return new BST_Node(s);
		} else if (temp.getData().compareTo(s) > 0) {
			temp.left = insert(temp.left, s);
		} else if (temp.getData().compareTo(s) < 0) {
			temp.right = insert(temp.right, s);
		} else {
			return temp;// the value already exist
		}
		return temp;

	}
	@Override
	public boolean remove(String s) {
		root = deleteNode(root, s);//*********************
		int sizeOriginal = size;
		deleteNode(root, s);
		if(sizeOriginal > size) {
			return true;
		}
		return false;
	}

	private BST_Node deleteNode(BST_Node current, String s) {
		if (current == null) {
			return null;
		} else if (current.data.compareTo(s) == 0) {
			size -= 1;
			if (current.getLeft() == null && current.getRight() == null) {
				return null;
			} else if (current.getLeft() == null && current.getRight() != null) {
				return current.getRight();
			} else if (current.getLeft() != null && current.getRight() == null) {
				return current.getLeft();
			} else if (current.getLeft() != null && current.getRight() != null) {
				current.data = findMax(current.left).data;
				current.left = deleteNode(current.left, current.data);
			}

		} else if (current.data.compareTo(s) < 0) {
			current.right = deleteNode(current.right, s);
		} else {
			current.left = deleteNode(current.left, s);
		}
		return current;
	}

	private BST_Node findNode(BST_Node temp, String s) {
		if (temp.getData().compareTo(s) == 0) {
			return temp;
		} else if (temp.getData().compareTo(s) > 0 && temp.getLeft() != null) {
			return findNode(temp.getLeft(), s);
		} else if (temp.getData().compareTo(s) < 0 && temp.getRight() != null) {
			return findNode(temp.getRight(), s);
		}
		return null;
	}

	@Override
	public String findMin() {
		return findMin(root).data;
	}

	private BST_Node findMin(BST_Node temp) {
		return temp.getLeft() == null ? temp : findMin(temp.getLeft());
	}

	@Override
	public String findMax() {
		return findMax(root).data;
	}

	private BST_Node findMax(BST_Node temp) {
		return temp.getRight() == null ? temp : findMax(temp.getRight());
	}

	@Override
	public boolean empty() {
		size();
		if (root == null || size == 0) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean contains(String s) {
		BST_Node feedback = findNode(root, s);
		if (feedback != null) {
			return true;
		}
		return false;

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		return height(root) - 1;
	}

	private int height(BST_Node root) {
		if (root == null)
			return 0;
		return 1 + Math.max(height(root.left), height(root.right));
	}
}
