/****************************************************
 * LinkedBinaryTree interface referenced from... 
 * Data Structures & Algorithms in JAVA. 
 * By M.T. Goodrich, R. Tamassia, and M.H. Goldwasser 
 * 
 * Each node has at most two children.
 ****************************************************/
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	
	//-------------Nested Node Class-------------
	protected static class Node<E> implements Position<E> {
		private E _element;
		private Node<E> _parent;
		private Node<E> _left;
		private Node<E> _right;
		
		//Constructs a node with the given element and neighbors.
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			_element = e;
			_parent = above;
			_left = leftChild;
			_right = rightChild;
		}
		
		public Node() {}

		//Accessor Methods
		@Override
		public E getElement() throws IllegalStateException {return _element;}
		public Node<E> getParent() {return _parent;}
		public Node<E> getLeft() {return _left;}
		public Node<E> getRight() {return _right;}
		
		//Update Methods
		public void setElement(E e) {_element = e;}
		public void setParent(Node<E> parentNode) {_parent = parentNode;}
		public void setLeft(Node<E> leftNode) {_left = leftNode;}
		public void setRight(Node<E> rightNode) {_right = rightNode;}
	}//-------------End of Nested Node Class-------------

	/****************************************************
	 * Creates a new node with the ability to chose its 
	 * parent, left child, and right child. 
	 * @param parent
	 * @param left child
	 * @param right child
	 ****************************************************/ 
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
		return new Node<E>(e, parent, left, right);
		
	}
	
	//LinkedBinaryTree instance variables. 
	protected Node<E> _root = null;
	private int _size = 0;
	private String _s;
	
	/***********************************************
	 * Constructor method for the LinkedBinaryTree 
	 * class. Sets the array used in the arithmeticPrint
	 * method to null. 
	 ***********************************************/
	public LinkedBinaryTree() {_s = null;}
	
	/***********************************************
	 * Protected method that validates the position
	 * to insure it is an instance of the node class. 
	 ***********************************************/
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if(!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type.");
		Node<E> node = (Node<E>) p;
		if(node.getParent() == node)
			throw new IllegalArgumentException("p is no longer in the tree.");
		return node;
	}
	
	/***********************************************
	 * Accessor method that returns the size of the 
	 * BinaryTree. 
	 ***********************************************/
	public int size() {return _size;}
	
	/***********************************************
	 * Checks if the BinaryTree is empty. 
	 ***********************************************/
	public boolean isEmpty() {return _size == 0;}
	
	/***********************************************
	 * Checks if the passed position if in an external 
	 * position. 
	 * @param A passed position.  
	 ***********************************************/
	public boolean isExternal(Position<E> p) {
		Node<E> node = validate(p);
		if(left(node) == null && right(node) == null)
			return true;
		else
			return false;
			
		
	}
	
	/***********************************************
	 * Returns the root.  
	 ***********************************************/
	public Position<E> root() {return _root;}
	
	/***********************************************
	 * Returns the parent of the passed node. 
	 * @param The position of child. 
	 ***********************************************/
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}
	
	/***********************************************
	 * Returns the left child of a parent passed. 
	 * @param The position of the parent.  
	 ***********************************************/
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}
	
	/***********************************************
	 * Returns the right child of a parent passed. 
	 * @param The position of the parent.  
	 ***********************************************/
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);			
		return node.getRight();
	}
	
	/***********************************************
	 * Adds the root to the tree. 
	 * @param The root element.  
	 ***********************************************/
	public Position<E> addRoot(E e) throws IllegalStateException {
		if(!isEmpty()) throw new IllegalStateException("Tree is not empty.");
		_root = createNode(e, null, null, null);
		_size = 1;
		return _root;
	}
	
	/***********************************************
	 * Adds a left child to the passed position. 
	 * @param The position of the parent. 
	 * @param The left child element. 
	 ***********************************************/
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if(parent.getLeft() != null)
			throw new IllegalArgumentException("p already has a left child.");
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		_size++;
		return child;
	}
	
	/***********************************************
	 * Adds a right child to the passed position. 
	 * @param The position of the parent. 
	 * @param The right child element. 
	 ***********************************************/
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if(parent.getRight() != null)
			throw new IllegalArgumentException("p already has a right child.");
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		_size++;
		return child;
	}
	
	/***********************************************
	 * Sets the position passed with a new element. 
	 * @param The position to be set. 
	 * @param The new element. 
	 ***********************************************/
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}
	
	/***********************************************
	 * Attaches two binary trees together. 
	 * @param The position where the attachment is located. 
	 * @param The first BinaryTree to be attached. 
	 * @param The second BinaryTree to be attached. 
	 ***********************************************/
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if(isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
		_size += t1.size() + t2.size();
		if(!t1.isEmpty()) { //Attach t1 as left subtree of node.
			t1._root.setParent(node);
			node.setLeft(t1._root);
			t2._root = null;
			t2._size = 0;
		}
		if(!t2.isEmpty()) { //Attach t2 as right subtree of node. 
			t2._root.setParent(node);
			node.setRight(t2._root);
			t2._root = null;
			t2._size = 0;
		}
	}
	
	/***********************************************
	 * Removes the node at a specific position. 
	 * @param The position of the node to remove.  
	 ***********************************************/
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if(numChildren(p) == 2)
			throw new IllegalArgumentException("p has two children.");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if(child != null)
			child.setParent(node.getParent()); // Child's grandparent becomes its parent. 
		if(node == _root)
			_root = child; //Child becomes root.
		else {
			Node<E> parent = node.getParent();
			if(node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		_size--;
		E temp = node.getElement();
		node.setElement(null); //Help with garbage collection. 
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node); //Our convention for defunct node.
		return temp;
	}
	
	/**********************************************
	 *  Performs inOrder traversal through the binary 
	 *  tree, mainly added for testing purposes.
	 *  @param The position representing how deep the 
	 *  traversal should go. The root if traversing 
	 *  the whole tree.
	 *  @param Position of the start point. 
	 **********************************************/
	public void inOrder(Position<E> p) {  
		Node<E> node = validate(p);
		if(left(node) != null) 
			inOrder(node.getLeft());	
		System.out.println(node.getElement());
		if(right(node) != null)
			inOrder(node.getRight());
	} 
	
	/**********************************************
	 *  Prints the arithmetic of the Binary Tree. 
	 *  Reference from the slides and added some 
	 *  code to produce the assigned output. 
	 *  @param The position of the starting node.  
	 **********************************************/
	public String arithmeticPrint(Position<E> p) {
		Node<E> node = validate(p);
		if(left(node) != null) {
			if(parent(node) != null) {
				if(node == right(parent(node))) { //Prints a parenthesis if the node is right child.  
					if(node.getElement() != parent(node).getElement()) { //if the parent and child are not the same element. 
						if(_s == null)
							_s = "(";
						else
							_s += "(";
					} else if((node.getElement() == parent(node).getElement()) &&
							(node.getElement() == "/" || node.getElement() == "-"))//if elements are equal, they are not division or subtraction.
						_s += "(";
				} else if((parent(node).getElement() == "/" || parent(node).getElement() == "*") && 
						(node.getElement() == "+" || node.getElement() == "-")) { //and if the child is subtraction or addition, the parent can not be division or multiplication. 
					if(_s == null)
						_s = "(";
					else
						_s += "(";  
				}
			}
			arithmeticPrint(left(node));
		}
		if(_s == null)
			_s = (String) node.getElement();
		else
			_s += node.getElement();
		if(right(node) != null) {
			arithmeticPrint(right(node));
			if(parent(node) != null)
				if(node == right(parent(node))) {
					if(node .getElement() != parent(node).getElement()) {//Same logic from the other half. 
						_s += ")";
					} else if((node.getElement() == parent(node).getElement()) &&
							(node.getElement() == "/" || node.getElement() == "-"))
						_s += ")";
				} else if((parent(node).getElement() == "/" || parent(node).getElement() == "*") && 
						(node.getElement() == "+" || node.getElement() == "-")) {
					_s += ")";
				}
			}
		return _s;
	}

	
	/**********************************************
	 *  Primarily referenced from the slides with the 
	 *  addition of the card logic and the operator 
	 *  evaluation. 
	 *  @param The position of the starting node
	 *  to be evaluated, usually the root. 
	 **********************************************/
	public double arithmeticEval(Position<E> p) {
		Node<E> node = validate(p);
		if(isExternal(node)) {
			if(node.getElement().equals("t") || node.getElement().equals("T"))
				return 10;
			if(node.getElement().equals("j") || node.getElement().equals("J"))
				return 11;
			if(node.getElement().equals("q") || node.getElement().equals("Q"))
				return 12;
			if(node.getElement().equals("k") || node.getElement().equals("K"))
				return 13;
			if(node.getElement().equals("a") || node.getElement().equals("A"))
				return 1;
			else
				return Integer.parseInt((String) node.getElement());
		}

		double leftVal = arithmeticEval(left(node));
		double rightVal = arithmeticEval(right(node));
		
		if(node.getElement() == (E)"+")
			return (double) (leftVal + rightVal);
		else if(node.getElement() == (E)"-")
			return (double) (leftVal - rightVal);
		else if(node.getElement() == (E)"*")
			return (double) (leftVal * rightVal);
		else { 
			try { 
				double solution = leftVal / rightVal;
				return solution;
			} catch (ArithmeticException ex) {
				return 0;
			}
		}
	}
}
