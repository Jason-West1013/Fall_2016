/****************************************************
 * Position interface referenced from... 
 * Data Structures & Algorithms in JAVA. 
 * By M.T. Goodrich, R. Tamassia, and M.H. Goldwasser
 ****************************************************/
public interface Position<E> {
	E getElement() throws IllegalStateException;
}
