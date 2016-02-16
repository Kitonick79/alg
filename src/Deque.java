import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Admin on 2/15/2016.
 */
public class Deque<Item> implements Iterable<Item> {

    private int N;          // size of the Deque
    private Node first;     // top of deque
    private Node last;     // bottom of deque

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }


    public Deque() {                            // construct an empty dequepublic boolean isEmpty()                 // is the deque empty?
        first = null;
        last = null;
        N=0;
    }

    public int size() { return N; }                       // return the number of items on the deque

    public boolean isEmpty() { return first == null;}               // is the deque empty?

    public void addFirst(Item item) {         // add the item to the front

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;

    }

    public void addLast(Item item) {          // add the item to the end

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = last;
    }

    public Item removeFirst() {               // remove and return the item from the front

        Item item = first.item;
        first = first.next;
        return  item;
    }

    public Item removeLast() {                // remove and return the item from the end

        Item item = last.item;
        last = last.next;
        return item;
    }
    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    public Iterator<Item> iterator() {        // return an iterator over items in order from front to end

        return new DequeIterator();
    }

        // an iterator, doesn't implement remove() since it's optional

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
        }
    }

    public static void main(String[] args) {   // unit testing
        { // Create a stack and push/pop strings as directed on StdIn.
            Deque<String> s = new Deque<String>();
            while (!StdIn.isEmpty())
            {
                String item = StdIn.readString();
                if (!item.equals("-"))
                    s.addFirst(item);
                else if (!s.isEmpty()) StdOut.print(s.removeFirst() + " ");
            }
            StdOut.println("(" + s.size() + " left on stack)");
        }

    }
}