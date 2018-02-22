package Reference;
/************************************************ 
 *Linked list referenced from...
 *Data Structures and Algorithms in Java
 *By. M. T. Goodrich, R. Tamassia, & M. H. Goldwasser
 ************************************************/
public class SinglyLinkedList<E> {

	private int _size = 0;
	private Node<E> _head = null;
	private Node<E> _tail = null;

	/************************************************ 
	 *Node class also referenced by above textbook. 
	 ************************************************/
	public static class Node<E> {
		private E _element;
		private Node<E> _pointer;
		
		/************************************************ 
		 *Constructor sets the element and pointer of the 
		 *node
		 *@param The element of generic type. 
		 *@param The pointer of Node type, also generic. 
		 ************************************************/
		public Node(E element, Node<E> pointer) {
			_element = element;
			_pointer = pointer;
		}
			
		/************************************************ 
		 *Returns the element. 
		 ************************************************/
		public E getElement() {
			return _element;
		}
		
		/************************************************ 
		 *Returns the pointer. 
		 ************************************************/
		public Node<E> getPointer() {
			return _pointer;
		}
		
		/************************************************ 
		 *Sets the pointer. 
		 *@param The next element to be pointed at. 
		 ************************************************/
		public void setPointer(Node<E> newPointer) {
			_pointer = newPointer;
		}
	}
	
	/************************************************ 
	 *Returns the size of the list. 
	 ************************************************/
	public int getSize() {
		return _size;
	}
	
	/************************************************ 
	 *Returns true if the list is empty, and false is
	 *not. 
	 ************************************************/
	public boolean isEmpty() {
		return _size == 0;
	}
	
	/************************************************ 
	 *Returns the first node in the list. 
	 ************************************************/
	public E getHead() {
		if(isEmpty())
			return null;
		return _head.getElement();
	}
	
	/************************************************ 
	 *Returns the last node in the list. 
	 ************************************************/
	public E getTail() {
		if(isEmpty())
			return null;
		return _tail.getElement();
	}
	
	/************************************************ 
	 *Adds a new head to the list and points at the
	 *old head. 
	 *@param The new element inserted into the list. 
	 ************************************************/
	public void addHead(E newElement) {
		_head = new Node<E>(newElement, _head);
		if(isEmpty()) 
			_tail = _head;
		_size++;
	}
	
	/************************************************ 
	 *Adds a new tail to the list, has the old tail
	 *point at it while it points at null. 
	 *@param The new element inserted into the list. 
	 ************************************************/
	public void addTail(E newElement) {
		Node<E> newNode = new Node<E>(newElement, null);
		if(isEmpty()) 
			_head = newNode;
		else 
			_tail.setPointer(newNode);
		_tail = newNode;
		_size++;
	}
	
	/************************************************ 
	 *Removes the head and returns the removed 
	 *element.
	 ************************************************/
	public E removeHead() {
		if(isEmpty())
			return null;
		E goneElement = _head.getElement();
		_head = _head.getPointer();
		_size--;
		if(isEmpty())
			_tail = null;
		return goneElement;
	}
	
	/************************************************ 
	 *Interates through the list comparing the passed 
	 *value. If it matches an element in the list it 
	 *returns true otherwise it returns false. 
	 *@param The element that is to be compared against. 
	 ************************************************/
	public boolean searchList(E match) {//Iterated through list searching for match to passed element.
		Node<E> temp = _head;
		while(temp != null) {
			if(temp.getElement() == match)
				return true;
			temp = temp.getPointer();
		}
		return false;
	}
}

