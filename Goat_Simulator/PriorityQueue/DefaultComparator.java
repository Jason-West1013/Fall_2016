package PriorityQueue;
import java.util.Comparator;

/*************************************
 * DefaultComparator Interface referenced from...
 * Data Structures and Algorithms in Java
 * by M.T. Goodrich
 * R. Tamassia
 * M.H. Goldwasser
 *************************************/
public class DefaultComparator<E> implements Comparator<E> {

	@Override
	public int compare(E a,E b) throws ClassCastException {
		return ((Comparable<E>) a).compareTo(b);
	}

}
