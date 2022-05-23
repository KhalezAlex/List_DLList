public class Node<T> {
    private T value;
    private Node<T> nNode;
    private Node<T> pNode;

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public Node<T> getNNode() {
        return nNode;
    }
    public void setNNode(Node<T> nNode) {
        this.nNode = nNode;
    }
    public Node<T> getPNode() {
        return pNode;
    }
    public void setPNode(Node<T> pNode) {
        this.pNode = pNode;
    }

    public Node() {
        this.value = null;
        this.nNode = null;
    }

    public Node(T value) {
        this.value = value;
        this.nNode = null;
    }

    public Node(T value, Node<T> nNode, Node<T> pNode) {
        this.value = value;
        this.nNode = nNode;
        this.pNode = pNode;
    }

    public boolean nodeEqualVal(Node<T> node) {
        return (this.getValue() == node.getValue());
    }
}
