
public class Find24_Tree {
	
	//Instance Variables
	private String[] _op = {"+", "-", "*", "/"}; 
	private LinkedBinaryTree<String> _tree;
	private NewLinkedList<String> list;
	private Position _root;
	private int _count;
	
	
	/***********************************************
	 *  Constructor for the class. Initializes the 
	 *  NewLinkedList instance.
	 ***********************************************/
	public Find24_Tree() {
		list = new NewLinkedList<String>();
		_count = 0;
	}
	
	/***********************************************
	 * Count accessor method.
	 ***********************************************/
	public int getCount() {
		return _count;
	}
	
	/***********************************************
	 * Permeates through all the operators with 
	 * the specific hand. calls the postfix method
	 * within the class to test. When the result 
	 * is 24 returns true. 
	 * @param The postfix notation to be tested. 
	 ***********************************************/
	public void checkOps(String[] n) {
		for(int x = 0; x < _op.length; x++) 
			for(int y = 0; y < _op.length; y++)
				for(int z = 0; z < _op.length; z++) {
					
					String[] ops = {_op[z],_op[y],_op[x]};
					
					// Loops through all postfix notations. 
					for(int w = 1; w <= 5; w++) {
						if(buildTree(w,n,ops) >= 24 && buildTree(w,n,ops) <= 24.9999) {
							String p = _tree.arithmeticPrint(_root);
							if(!list.searchList(p)) {
								list.addHead(p);
								System.out.println(p);
							}
							_count++;
						}
					}
				}
	}
	
	/*************************************************
	 * Builds every version of the postfix using
	 * binary trees. The choice of postfix is 
	 * dependent on the integer passed.
	 * @param The version of the postfix desired.
	 * @param The cards the player selected. 
	 * @param The ops decided by the permeation loop. 
	 *************************************************/
	public double buildTree(int n, String[] cards, String[] ops) {
		_tree = new LinkedBinaryTree<String>();
		Position leftOfRoot;
		Position rightOfRoot;
		Position childOfLeft;
		Position childOfRight;
		
		_root = _tree.addRoot(ops[0]);
		
		if(n == 1) {
			leftOfRoot = _tree.addLeft(_root,ops[1]);
			_tree.addRight(_root,cards[3]);
			childOfLeft = _tree.addLeft(leftOfRoot,ops[2]);
			_tree.addRight(leftOfRoot,cards[2]);
			_tree.addLeft(childOfLeft,cards[0]);
			_tree.addRight(childOfLeft,cards[1]);
			return _tree.arithmeticEval(_root);
		}
		if(n == 2) {
			_tree.addLeft(_root,cards[0]);
			rightOfRoot = _tree.addRight(_root,ops[1]);
			_tree.addLeft(rightOfRoot,cards[1]);
			childOfRight = _tree.addRight(rightOfRoot,ops[2]);
			_tree.addLeft(childOfRight,cards[2]);
			_tree.addRight(childOfRight,cards[3]);
			return _tree.arithmeticEval(_root);
		}
		if(n == 3) {
			leftOfRoot = _tree.addLeft(_root,ops[1]);
			_tree.addRight(_root,cards[3]);
			_tree.addLeft(leftOfRoot,cards[0]);
			childOfLeft = _tree.addRight(leftOfRoot,ops[2]);
			_tree.addLeft(childOfLeft,cards[1]);
			_tree.addRight(childOfLeft,cards[2]);
			return _tree.arithmeticEval(_root);
		}
		if(n == 4) {
			_tree.addLeft(_root,cards[0]);
			rightOfRoot = _tree.addRight(_root,ops[1]);
			childOfRight = _tree.addLeft(rightOfRoot,ops[2]);
			_tree.addRight(rightOfRoot,cards[3]);
			_tree.addLeft(childOfRight,cards[1]);
			_tree.addRight(childOfRight,cards[2]);
			return _tree.arithmeticEval(_root);
		}
		if(n == 5) {
			leftOfRoot = _tree.addLeft(_root,ops[1]);
			rightOfRoot = _tree.addRight(_root,ops[2]);
			_tree.addLeft(leftOfRoot,cards[0]);
			_tree.addRight(leftOfRoot,cards[1]);
			_tree.addLeft(rightOfRoot,cards[2]);
			_tree.addRight(rightOfRoot,cards[3]);
			return _tree.arithmeticEval(_root);
		}
		return 0;
	}	
}
