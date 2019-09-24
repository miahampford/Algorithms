//
//Amelia Hampford
//agh3ft
//Algorithms Spring 2019
//

public class LRULinkedList {
    public Node head;
    public Node tail;
    public int capacity =0;
    public int size =0;

    /**
     * Constructor creates a double linked list with a single value.
     */
    public LRULinkedList(int capacity){
        this.capacity = capacity;
    }

    /**
     * Add a node to the linked list.
     * @param key
     * @param value
     * @return new node
     */
    public Node add(int key, int value) {
        // TODO: implement this method

        Node newNode = new Node(key, value);
        newNode.next = head;
        if(head != null) {head.previous = newNode;}
        head = newNode;
        if(tail == null) {tail = newNode;}
        size += 1;
        if(size > capacity){
            deleteTail();
        }
        return head;
    }

    /**
     * Remove the last item in the linked list.
     */
    public void deleteTail(){
        if(tail.previous != null){
            tail.previous.next = null;
            tail = tail.previous;
            size -= 1;
        }
    }

    /**
     * Get the node that corresponds to the passed in key.
     * @param key
     * @return node that matches key
     */
    public Node getNode(int key){
        // TODO: implement this method

        Node node = head;
        while (node.key != key && node != tail){
            node = node.next;
        }
        if (node.key != key) {
            return null;}
        else {
            moveNodeToHead(node);
            return node;
        }

    }

    /**
     * Move the passed in node to the head position.
     * @param node
     */
    public void moveNodeToHead(Node node){
        // TODO: implement this method

        if(head != node){

            if (node == tail) {
                tail = node.previous;
            }

            if (node.previous != null) {
                node.previous.next = node.next;
            }
            if (node.next != null) {
                node.next.previous = node.previous;
            }

            node.previous = null;
            node.next = head;
            head.previous = node;
            head = node;
        }

    }

    public static void main(String[] args) {

    }

}
