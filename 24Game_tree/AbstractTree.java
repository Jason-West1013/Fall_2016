/****************************************************
 * AbstractTree abstract class referenced from... 
 * Data Structures & Algorithms in JAVA. 
 * By M.T. Goodrich, R. Tamassia, and M.H. Goldwasser 
 * 
 * Each node has at most two children.
 ****************************************************/
public abstract class AbstractTree<E> implements Tree<E> {
	
	public boolean isInternal(Position<E> p) {return numChildren(p) > 0;};
	public boolean isExternal(Position<E> p) {return numChildren(p) == 0;};
	public boolean isRoot(Position<E> p) {return p == root();};
	public boolean isEmpty() {return size() == 0;};

}
