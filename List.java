public class List <T extends Comparable<T>>{
    public class Node {
        public T value;
        public Node next;

        Node() {
            this.value = null;
            this.next = null;
        }

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node tail;
    private Node head;
    private int size;

    //default constructor
    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //copy constructor
    public List(List<T> other) {
        copy(other);
    }

    //copy mimic
    public void copy(List<T> other) {
        if (other.head == null) {
            this.head = null;
            this.size = 0;
            this.tail = null;

            return;
        } 
        
        Node oWalker = other.head;
        this.head = new Node(oWalker.value);
        Node tWalker = this.head;

        while (oWalker.next != null) {
            oWalker = oWalker.next;
            Node newNode = new Node(oWalker.value);
            tWalker.next = newNode;
            tWalker = tWalker.next;
        }

        this.tail = tWalker;
        this.size = other.size;
    }

    //destructor aka cleanup method
    public void close() {
        head = null;
        tail = null;
        size = 0;
    }

    //checks if linked list is empty
    public boolean isEmpty() {
        // boolean flag = false;
        // if (this.size == 0) {
        //     flag = true;
        // }
        // return flag;
        return this.head == null;
    }

    //get element at an index
    public T get(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty.");
        }

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        Node walker = this.head;

        for (int i = 0; i < index; i++) {
            walker = walker.next;
        }

        return walker.value;
    }

    //sets element at an index
    public T set(int index, T element) {
        //throw new IndexOutOfBoundsException();
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        if (isEmpty()) {
            Node set = new Node(element);
            this.size++;
            this.head = set;
            throw new IndexOutOfBoundsException("List is empty, setting at index 0.");
        }

        Node walker = this.head;

        for (int i = 0; i < index; i++) {
            walker = walker.next;
        }

        T temp = walker.value;
        walker.value = element;

        return temp;
    }

    //returns size of list
    public int length() {
        return this.size;
    }

    //pushes node to the end of a list
    public void push(T element) {
        Node pushed = new Node(element);
        this.size++;

        if (this.head == null) {
            this.head = pushed;
            this.tail = pushed;
        } else {
            Node walker = this.head;

            if (walker.next != null) {
                walker = walker.next;
            }
            walker.next = pushed;
            this.tail = pushed;
        }
    }

    //adds a node to an index
    public void addAt(int index, T element) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        Node added = new Node(element);

        //at beginning of list
        if (index == 0) {
            added.next = this.head;
            this.head = added;
        }

        int i = 0;
        Node walker = this.head;

        while (walker != null && i < index - 1) {
            walker.next = walker;
            i++;
        }

        added.next = walker.next;
        walker.next = added;
        this.size++;

        while(walker.next != null) {
            walker = walker.next;
        }

        this.tail = walker;
    }

    //removes a node ta an index
    public T remove(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty.");
        }
        
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        Node walker = this.head;
        T value;

        if (index == 0) {
            value = walker.value;
            this.head = walker.next;
        } else {
            Node prev = null;

            for (int i = 0; i < index; i++) {
                prev = walker;
                walker = walker.next;
            }

            value = walker.value;

            if (index < this.size - 1) {
                prev.next = walker.next;
            } else {
                prev.next = null;
            }
        }

        this.size--;

        Node tWalker = this.head;

        while(tWalker.next != null) {
            tWalker = tWalker.next;
        }
        
        this.tail = tWalker;

        return value;
    }

    //indexOf - returns index takes in element, gives the index of the first ocurrence
    public int indexOf(T element) {
        Node walker = this.head;
        int i = 0;

        while(walker != null) {
            if (walker.value == element) {
                System.out.println("Element found at index " + i);
                return i;
            }
            walker = walker.next;
            i++;
        }

        System.out.println("Element not found!");
        return -1;
    }

    //indexes of - returns an array
    public int[] indexesOf(T element) {
        Node walker = this.head;
        Node sWalker = this.head;
        int SIZE = 0;

        while(sWalker != null) {
            if (sWalker.value == element) {
                SIZE++;
            }
            sWalker = sWalker.next;
        }

        int[] arr = new int[SIZE];
        System.out.println("Size: " + SIZE);
        int i = 0;
        int j = 0;

        System.out.print('[');
        
        while(walker != null) {
            if (walker.value == element) {
                System.out.print(i + ' ');
                arr[j] = i;
                j++;
            }
            walker = walker.next;
            i++;
        }

        System.out.println(']');

        return arr;
    }

    //print function
    public void print() {
        Node walker = this.head;

        while (walker != null) {
            System.out.print(walker.value + " ");
            walker = walker.next;
        }

        System.out.println(' ');
    }

    //search function
    public boolean search(T element) {
        Node walker = this.head;
        boolean flag = false;

        while(walker != null) {
            if (walker.value == element) {
                System.out.println("Element Found!");
                flag = true;
            }
            walker = walker.next;
        }
        System.out.println("Element Not Found!");
        return flag;
    }

    //print tail
    public void printTail() {
        System.out.println("Tail Value: " + tail.value);
    }

    public static final void main(String[] args) {
        List<Integer> l = new List<Integer>();
        List<Integer> l2 = new List<Integer>();
    
        //push test
        l.push(7);
        l.push(11);
        l.push(13);
    
        //print test
        l.print();
    
        //search test
        l.search(12);
        l.search(11);
    
        // //addAt test 
        l.addAt(1, 12);
        l.print();
    
        //remove test
        l.remove(1);
        l.print();
        l.remove(0);
        l.print();
        l.remove(l.length() - 1);
        l.print();
        l.remove(4);
        l.print();
    
        //set test
        l.set(1, 5);
        l.print();
        l.set(0, 4);
        l.print();
        l.set(2, 6);
        l.print();
        l.set(7, 8);
        l2.set(0, 15);
        l2.print();
    
        //get test
        System.out.println("Index 0: " + l.get(0));
        System.out.println("Index 1: " + l.get(1));
        System.out.println("Index 2: " + l.get(2));
        System.out.println("Index 0: " + l2.get(0));
    
        //copy constructor test
        List<Integer> l3 = new List<>(l);
        l3.print();
        System.out.println("Size of L3: " + l3.length());
        List<Integer> l4 = new List<>(l2);
        l4.print();
    
        //indexOf test
        l.indexOf(8);
        l.indexOf(5);
    
        //indexesOf test
        l.set(0, 5);
        l.indexesOf(5);
        l.set(2, 5);
        l.indexesOf(5);
        l2.indexesOf(9);
    }
}
