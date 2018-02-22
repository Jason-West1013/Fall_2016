/****************************************************
 * Stack interface referenced from... 
 * Data Structures & Algorithms in JAVA. 
 * By M.T. Goodrich, R. Tamassia, and M.H. Goldwasser 
 ****************************************************/
public interface Stack<E> {
	
	/*********************************
	 * Returns the size of the stack
	 *********************************/
	int size();
	
	/*********************************
	 * Returns true is the stack is empty
	 * and false otherwise.
	 *********************************/
	boolean isEmpty();
	
	/*********************************
	 * Adds a new element to the top of 
	 * the stack. 
	 * @param A generic element. 
	 *********************************/
	void push(E element);
	
	/*********************************
	 * Returns the top element of the 
	 * stack. Returns null if the stack 
	 * is empty. 
	 *********************************/
	E top();
	
	/*********************************
	 * Returns and removes the top element
	 * of the stack. Returns null if the 
	 * stack is empty. 
	 *********************************/
	E pop();

}
