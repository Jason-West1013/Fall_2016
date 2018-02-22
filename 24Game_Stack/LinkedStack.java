/****************************************************
 * Linked list stack referenced from... 
 * Data Structures & Algorithms in JAVA. 
 * By M.T. Goodrich, R. Tamassia, and M.H. Goldwasser 
 ****************************************************/
public class LinkedStack<E> implements Stack<E> {

	private NewLinkedList<E> _list = new NewLinkedList<E>();
	
	/*********************************
	 * Returns the size of the stack
	 *********************************/
	@Override
	public int size() {
		return _list.getSize();
	}

	/*********************************
	 * Returns true is the stack is empty
	 * and false otherwise.
	 *********************************/
	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	/*********************************
	 * Adds a new element to the top of 
	 * the stack. 
	 * @param A generic element. 
	 *********************************/
	@Override
	public void push(E element) {
		_list.addHead(element);
		
	}

	/*********************************
	 * Returns the top element of the 
	 * stack. Returns null if the stack 
	 * is empty. 
	 *********************************/
	@Override
	public E top() {
		return (E) _list.getHead();
	}

	/*********************************
	 * Returns and removes the top element
	 * of the stack. Returns null if the 
	 * stack is empty. 
	 *********************************/
	@Override
	public E pop() {
		return _list.removeHead();
	}

}
