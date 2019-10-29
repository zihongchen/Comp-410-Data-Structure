package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par;
	boolean status;
	

	BST_Node(String data) {
		this.data = data;
		this.status = true;
	}

	BST_Node(String data, BST_Node left, BST_Node right, BST_Node par) { // feel free to modify this constructor to suit
																			// your needs
		this.data = data;
		this.left = left;
		this.right = right;
		this.par = par;
		this.status = true;
	}
	public String getData() {
		return data;
	}
	public BST_Node getLeft() {
		return left;
	}
	public BST_Node getRight() {
		return right;
	}
	
	
	
	

	public BST_Node containsNode(String s) { 
		if (data.compareTo(s) == 0) {
			splay(this);
			return this;
		}
		if (data.compareTo(s) > 0) {// s lexiconically less than data
			if (left != null) {
				return left.containsNode(s);
			} else {
				splay(this);
				return this;
			}
		} else if (data.compareTo(s) < 0) {
			if (right != null) {
				return right.containsNode(s);
			} else {
				//**********************************
				splay(this);
				return this;
			}
		} else
			splay(this);
		return this;

	}

	public BST_Node insertNode(String s) {
		if (s.compareTo(this.data) < 0) {
			if (this.left != null) {
				return this.left.insertNode(s);
			}
		   this.left = new BST_Node(s);
		   this.left.par =this;
			BST_Node root = this.left;
			splay(this.left);
			   
			   return root;
		}
		if (s.compareTo(this.data) > 0) {
			if (this.right != null) {
				return this. right.insertNode(s);
			}
			this.right = new BST_Node(s);
			this.right.par = this;
			BST_Node root = this.right;
			splay(this.right);
			return root;
		}
		splay(this);
		return this;

	}

	public BST_Node findMin() {
		if (left != null) {
			return left.findMin();
		}
		splay(this);
		return this;
	}

	public BST_Node findMax() {
		if (right != null) {
			return right.findMax();
		}
		splay(this);
		return this;
	}

	public int getHeight() {
		int l = 0;
		int r = 0;
		
		
		if (left != null)
			
			l += left.getHeight() + 1;
		if (right != null)
			r += right.getHeight() + 1;
		return Integer.max(l, r);
	}

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}

	
	private int judge(BST_Node grandparent,BST_Node parent, BST_Node x) {
		if(x.data.compareTo(parent.data)<0 && parent.data.compareTo(grandparent.data)<0) {
			return 1;// rotate right twice, first grandparent, then parent
		}else  if(x.data.compareTo(parent.data)>0 && parent.data.compareTo(grandparent.data)>0) {
			return 2;// rotate left twice, first grandparent, then parent
		}// both zig-zig
		
		
		
		
		//zig-zag
		if(x.data.compareTo(parent.data)<0 && parent.data.compareTo(grandparent.data)>0) {
			return 3;//  rotate y right then rotate z left
		}else  if(x.data.compareTo(parent.data)>0 && parent.data.compareTo(grandparent.data)<0) {
			return 4;// rotate y left and z right
		}
		return 0;	
		
		
	}

	public void splay(BST_Node splay) {
		// rotate y left and z right// rotate y right then rotate z left
		// both zig-zig// rotate right twice, first grandparent, then parent
		while (splay.par != null) {
			BST_Node parent = splay.par;
			BST_Node gp = parent.par;
			if (gp == null) {

				// zig --------------------------------------------- at the surface very near
				// root
				if (splay == parent.left) {
					RR(splay);
				} else {
					RL(splay);
				}
			} else if (splay == parent.left) {

				// to the left of parent
				if (parent == gp.left) {

					// left of grandparent
					RR(splay);

					RR(splay);
				} else {

					RR(splay);


					// zig-zag//first parent, thne grantparents!!! why can't i reverse the order??
					RL(splay);
				}
			} else if (splay == parent.right) {
				// to the right of parent
				if (parent == gp.right) { 
					//right of grandparent
					RL(splay);
					RL(splay);
				} else { 
					// zag zig  
					RL(splay);
					RR(splay);
				}
			}
		}
	}
	

	private void RR(BST_Node temp) {
		BST_Node roots = temp.par;
		if (roots.par != null) {
			if (roots != roots.par.left) {
				roots.par.right = temp;
			} else {
				roots.par.left = temp;
			}
		}
		if (temp.right != null) {
			temp.right.par = roots;
		}
		temp.par = roots.par;
		roots.par = temp;
		roots.left = temp.right;
		temp.right = roots;
		}

	

	private void RL(BST_Node temp) {
		BST_Node parent = temp.par;
		if (parent.par != null) {
			if (parent != parent.par.left) {
				parent.par.right = temp;
			} else {
				parent.par.left = temp;
			}
		}
		if (temp.left != null) {
			temp.left.par = parent;
		}
		temp.par = parent.par;
		parent.par = temp;
		parent.right = temp.left;
		temp.left = parent;
	}
}