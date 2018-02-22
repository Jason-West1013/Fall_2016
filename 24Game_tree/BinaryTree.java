/****************************************************
 * BinaryTree interface referenced from... 
 * Data Structures & Algorithms in JAVA. 
 * By M.T. Goodrich, R. Tamassia, and M.H. Goldwasser 
 * 
 * Each node has at most two children.
 ****************************************************/
public interface BinaryTree<E> extends Tree<E> {
	
	//Returns position of p's left child.
	Position<E> left(Position<E> p) throws IllegalArgumentException;
	
	//Returns position of p's right child. 
	Position<E> right(Position<E> p) throws IllegalArgumentException;
	
	//Returns position of p's sibling. 
	Position<E> sibling(Position<E> p) throws IllegalArgumentException;

}
