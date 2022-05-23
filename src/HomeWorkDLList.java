public class HomeWorkDLList {
    //Task1: You have an alphabet-ordered list of books. Insert a new book, saving the alphabet order
    private static boolean isAlphabet(String word1, String word2) {
        for (int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
            if (Character.toLowerCase(word1.charAt(i)) != Character.toLowerCase(word2.charAt(i))) {
                return (Character.toLowerCase(word1.charAt(i)) < Character.toLowerCase(word2.charAt(i)));
            }
        }
        return (word1.length() < word2.length());
    }
    public static void task1(DLinkedList list, String book) {
        if (isAlphabet(book, list.getHead().getValue().toString())) {
            list.push(book, 0);
        } else {
            Node tmp = list.getHead();
            while (tmp.getNNode() != null) {
                if (isAlphabet(tmp.getValue().toString(), book) &&
                        isAlphabet(book, tmp.getNNode().getValue().toString())) {
                    Node newNode = new Node(book, tmp.getNNode(), tmp);
                    tmp.getNNode().setPNode(newNode);
                    tmp.setNNode(newNode);
                    return;
                }
                tmp = tmp.getNNode();
            }
            tmp.setNNode(new Node(book, null, tmp));
        }
    }

    //Task2: You have 2 descend-ordered lists. Merge them and get one descend-ordered list
    private static DLinkedList merge(DLinkedList list, Node node1, Node node2) {
        int count = 0;
        while (node1 != null && node2 != null) {
            if ((int) node2.getValue() >= (int) node1.getValue()) {
                list.push(node2.getValue(), count);
                node2 = node2.getNNode();
            } else {
                node1 = node1.getNNode();
            }
            count++;
        }
        return list;
    }
    public static DLinkedList sumListSort(DLinkedList list1, DLinkedList list2) {
        DLinkedList newList;
        Node node1;
        Node node2;

        if (list1.getLength() >= list2.getLength()) {
            node1 = list1.getHead();
            node2 = list2.getHead();
            return merge(list1, node1, node2);
        } else {
            node1 = list2.getHead();
            node2 = list1.getHead();
            return merge(list2, node1, node2);
        }
    }
}
