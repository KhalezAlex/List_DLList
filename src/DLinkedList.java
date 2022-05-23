public class DLinkedList<T> {
/** properties */

    private Node<T> head;
    private int length;



/**getters and setters*/

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Node<T> getHead() {
        return head;
    }

    private void setHead(Node<T> head) {
        this.head = head;
    }

    /**returns an element by index (returns null, if index > list.length*/
    public Node<T> getNode(int index) {
        Node<T> tmp = this.head;
        int count = 0;
        while (tmp != null) {
            if(count == index){
                return tmp;
            }
            count++;
            tmp = tmp.getNNode();
        }
        return null;
        //return new Node(null, null, null);
    }

    /**returns first Node with a specified value and null if there is no such a node in the list*/
    //проверить, что лучше вернуть, null или new Node(null, null, null)
    public Node<T> getNode(Object val) {
        Node<T> tmp = this.head;
        while (tmp != null) {
            if (val == tmp.getValue()) {
                return tmp;
            }
            tmp = tmp.getNNode();
        }
        return null;
    }

    /**Returns last element of the list*/
    public Node<T> getLast() {
        Node<T> tmp = this.head;
        while (tmp.getNNode() != null) {
            tmp = tmp.getNNode();
        }
        return tmp;
    }



/**Constructors*/

    /**Constructs an empty list*/
    public DLinkedList() {
        this.head = null;
        this.length = 0;
    }

    /**Constructs list by setting its head*/
    public DLinkedList(Node<T> head) {
        this.head = head;
        this.length = 1;
    }

    /**Constructs list containing elements of a specified array*/
    public DLinkedList(T[] arrInput) {
        this.head = new Node<>(arrInput[0]);
        for (int i = 1; i < arrInput.length; i++) {
            this.push(arrInput[i]);
        }
        this.length = arrInput.length;
    }

    /**Constructs a new list which is a copy of parameter "list"*/
    public DLinkedList(DLinkedList<T> list) {
        this.head = new Node<>(list.getHead().getValue());
        this.length = 1;
        Node<T> tmp = list.getHead().getNNode();
        while (tmp != null) {
            this.push(tmp.getValue()); //проверить
            tmp = tmp.getNNode();
        }
    }



/**Output*/

    /**prints list if val-properties are printable*/
    public void print() {
        System.out.print("[");
        Node<T> tmp = this.head;
        while (tmp.getNNode() != null) {
            System.out.print(tmp.getValue() + ", ");
            tmp = tmp.getNNode();
        }
        System.out.print(tmp.getValue() + "]");
        System.out.println();
    }

    /**Prints the inverse list without inverting it*/
    public void printInv() {
        System.out.print("[");
        Node<T> tmp = this.getLast();
        while (tmp.getPNode() != null) {
            System.out.print(tmp.getValue() + ", ");
            tmp = tmp.getPNode();
        }
        System.out.print(tmp.getValue() + "]");
    }



/**Push/pop methods*/

    /**inserts an element with "val" value as the last element*/
    public void push(T val) {
        if (this.isVoid()) {
            this.pushHead(val);
        }
        else {
            Node<T> tmp = this.head;
            while (tmp.getNNode() != null) {
                tmp = tmp.getNNode();
            }
            tmp.setNNode(new Node<>(val, null, tmp));
        }
        this.length++;
    }

    /**inserts an element with "val" value as "index" element*/
    public void push(T val, int index) {
        if (index == 0) {
            this.pushHead(val);
        }
        else {
            Node<T> tmp = this.head;
            for (int i = 0; i < index - 1 && tmp.getNNode() != null; i++) {
                tmp = tmp.getNNode();
            }
            Node<T> node = new Node<>(val, tmp.getNNode(), tmp);
            node.getNNode().setPNode(node);
            tmp.setNNode(node);
        }
        this.length++;
    }

    /**inserts an element with "val" value as the first element*/
    private void pushHead(T val) {
        if (this.isVoid()) {
            this.setHead(new Node<>(val));
        }
        else {
            Node<T> node = new Node<>(val, this.head, null);
            this.head.setPNode(node);
            this.head = node;
        }
    }

    /**deletes last element in the list*/
    public void pop() {
        Node<T> tmp = this.head;
        while (tmp.getNNode().getNNode() != null) {
            tmp = tmp.getNNode();
        }
        tmp.getNNode().setPNode(null);
        tmp.setNNode(null);
        this.length--;
    }

    /**deletes index-th element in the list*/
    public void pop(int index) {
        if (index == 0) {
            popHead();
        }
        else {
            if(index == this.length - 1) {
                pop();
            }
            else {
                Node<T> tmp = this.head;
                for (int i = 0; i < index - 1 && tmp.getNNode().getNNode() != null; i++) {
                    tmp = tmp.getNNode();
                }
                tmp.getNNode().getNNode().setPNode(tmp);
                tmp.setNNode(tmp.getNNode().getNNode());
                this.length--;
            }
        }
    }

    /**deletes the first element in the list*/
    public void popHead() {
        if(this.head.getNNode() == null) {
            this.head.setPNode(null);
            this.head.setNNode(null);
            this.head.setValue(null);
        }
        else {
            this.head = this.head.getNNode();
            this.head.setPNode(null);
        }
        this.length--;
    }

    /**deletes all elements in the list*/
    public void popAll() {
        while (this.head.getValue() != null) {
            this.popHead();
        }
    }

    /**deletes first element with val- value in the list*/
    public void popValue(T val) {
        Node<T> tmp = this.head;
        int count = 0;
        while (tmp != null) {
            if (tmp.getValue() == val) {
                this.pop(count);
                count--;
                break;
            }
            count++;
            tmp = tmp.getNNode();
        }
    }

    /**deletes all elements with val- value in the list*/
    public void popAllValues(Object val) {
        Node<T> tmp = this.head;
        int count = 0;
        while (tmp != null) {
            if (tmp.getValue() == val) {
                this.pop(count);
                count--;
            }
            count++;
            tmp = tmp.getNNode();
        }
    }

    /**deletes all elements, which have values, which are equal to value of an index-th element*/
    public void popClones(int index) {
        for (int i = 0; i < this.length; i ++) {
            if (i != index) {
                if (this.getNode(i).getValue() == this.getNode(index).getValue()) {
                    this.pop(i);
                    i--;
                }
            }
        }
    }

    /**deletes all clone valued elements except first unique valued element*/
    public void popAllClones() {
        for (int i = 0; i < this.length; i++) {
            this.popClones(i);
        }
    }



/**other methods*/

    /**sets valNew value to all elements, that have valPrev value*/
    public void replace(T valPrev, T valNew) {
        Node<T> tmp = this.head;
        while (tmp != null) {
            if(tmp.getValue() == valPrev) {
                tmp.setValue(valNew);
            }
            tmp = tmp.getNNode();
        }
    }

    /**returns true if the list is symmetric*/
    public boolean isSymmetric() {
        for (int i = 0; i < this.length / 2; i++) {
            if (getNode(i).getValue() != getNode(this.length - 1 - i).getValue()) {
                return false;
            }
        }
        return true;
    }

    /**returns the amount of elements in the list with unique values*/
    public int uniqueValues() {
        DLinkedList<T> dLList = new DLinkedList<>(this);
        int countUnique = 0;
        while (dLList.getHead().getValue() != null) {
            dLList.popAllValues(dLList.getHead().getValue());
            countUnique++;
        }
        return countUnique;
    }

    /**returns false, if the list has at least one element and true if it is void*/
    public boolean isVoid() {
        return (this.length == 0);
    }

    /**Inverts the elements order in the list*/
    public void inverse() {
        T tmp;
        for (int i = 0; i < this.length / 2; i ++) {
            tmp = this.getNode(i).getValue();
            this.getNode(i).setValue(this.getNode(this.length - i - 1).getValue());
            this.getNode(this.length - i - 1).setValue(tmp);
        }
    }
}

    /*public DoubleLinkedList reverse() {
        DoubleLinkedList listTmp = new DoubleLinkedList(new NodeDouble(this.getLast().getValue(), null, null));
        NodeDouble nodeTmp = this.getLast();
        while(nodeTmp.getPrevNode() != null) {
            listTmp.push(nodeTmp.getPrevNode().getValue());
            nodeTmp = nodeTmp.getPrevNode();
        }
        this. = listTmp;
    }*/
