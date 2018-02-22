import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/****************************************************
 * AbstractBinaryTree abstract class referenced from... 
 * Data Structures & Algorithms in JAVA. 
 * By M.T. Goodrich, R. Tamassia, and M.H. Goldwasser 
 * 
 * Each node has at most two children.
 ****************************************************/
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	/****************************************************
	 * Iterates through all of the children of the 
	 * BinaryTree. Could not use the Iterable from the 
	 * book because it required two inputs. 
	 * @param Position of starting point. 
	 ****************************************************/
	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		List<Position<E>> snapshot = new ArrayList<>(2);
		if(left(p) != null)
			snapshot.add(left(p)); //Couldn't use one from the book because it required 2 passed variables. 
		if(right(p) != null)
			snapshot.add(right(p));
		return snapshot;
		}

	/****************************************************
	 * Iterates through all the children and returns the 
	 * number of them.
	 * @param The position of the start point. 
	 ****************************************************/
	@Override
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		int count = 0;
		if(left(p) != null)
			count++;
		if(right(p) != null)
			count++;
		return count;
		}
	
	/****************************************************
	 * Checks whether the passed position is a sibling, and 
	 * also which sibling it is. 
	 * @param Position of sibling. 
	 ****************************************************/
	@Override
	public Position<E> sibling(Position<E> p) {
		Position<E> parent = parent(p);
		if(parent == null) return null;
		if(p == left(parent))
			return right(parent);
		else
			return left(parent);
		}
	
	@Override
	public Position<E> root() {return null;}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {return null;}

	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {return false;}

	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {return false;}

	@Override
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {return false;}

	@Override
	public int size() {return 0;}

	@Override
	public boolean isEmpty() {return false;}

	@Override
	public Iterator<E> iterator() {return null;}

	@Override
	public Iterable<Position<E>> positions() {return null;}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {return null;}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {return null;}

}
