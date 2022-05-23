import java.io.BufferedReader;
import java.io.IOException;

public class HomeWorkDLList {
    //Task0: You have an alphabet-ordered list of books. Insert a new book, saving the alphabet order

    private static boolean isAlphabet(String word1, String word2) {
        for (int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
            if (Character.toLowerCase(word1.charAt(i)) != Character.toLowerCase(word2.charAt(i))) {
                return (Character.toLowerCase(word1.charAt(i)) < Character.toLowerCase(word2.charAt(i)));
            }
        }
        return (word1.length() < word2.length());
    }
    public static void task0(DLinkedList<String> list, String book) {
        if (isAlphabet(book, list.getHead().getValue())) {
            list.push(book, 0);
        } else {
            Node<String> tmp = list.getHead();
            while (tmp.getNNode() != null) {
                if (isAlphabet(tmp.getValue(), book) && isAlphabet(book, tmp.getNNode().getValue())) {
                    Node<String> newNode = new Node<>(book, tmp.getNNode(), tmp);
                    tmp.getNNode().setPNode(newNode);
                    tmp.setNNode(newNode);
                    return;
                }
                tmp = tmp.getNNode();
            }
            tmp.setNNode(new Node<>(book, null, tmp));
        }
    }



    //Task1: You have 2 descend-ordered lists. Merge them and get one descend-ordered list
    private static DLinkedList<Integer> merge(DLinkedList<Integer> list, Node<Integer> node1, Node<Integer> node2) {
        int count = 0;
        while (node1 != null && node2 != null) {
            if ( node2.getValue() >= node1.getValue()) {
                list.push(node2.getValue(), count);
                node2 = node2.getNNode();
            } else {
                node1 = node1.getNNode();
            }
            count++;
        }
        return list;
    }
    public static DLinkedList<Integer> task1(DLinkedList<Integer> list1, DLinkedList<Integer> list2) {
        Node<Integer> node1;
        Node<Integer> node2;

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



    //Task2: You have a list of integer values. Next elements are to be sorted in ascending order:
    // a) Positive valued elements b) even index number elements
    private static Node<Integer> getNextPos(Node<Integer> node) {
        while (node.getNNode() != null) {
            node = node.getNNode();
            if (node.getValue() > 0) {
                return node;
            }
        }
        return null;
    }
    private static boolean bubbleIteration(Node<Integer> node1, Node<Integer> node2) {
        int counter = 0;
        while (node2 != null) {
            assert node1 != null;
            if (node1.getValue() > node2.getValue()) {
                Integer tmp  = node1.getValue();
                node1.setValue(node2.getValue());
                node2.setValue(tmp);
                counter++;
            }
            node1 = getNextPos(node1);
            node2 = getNextPos(node2);
        }
        return counter == 0;
    }
    public static void task2A(DLinkedList<Integer> list) {
        if (list.getHead().getValue() <= 0) {
            for (int i = 0; i < list.getLength() - 1; i++) {
                Node<Integer> node1 = getNextPos(list.getHead());
                Node<Integer> node2 = getNextPos(list.getHead());
                assert node2 != null;
                node2 = getNextPos(node2);
                if (bubbleIteration(node1, node2)) {
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < list.getLength() - 1; i++) {
                Node<Integer> node1 = list.getHead();
                Node<Integer> node2 = getNextPos(list.getHead());
                if (bubbleIteration(node1, node2)) {
                    break;
                }
            }
        }
    }



    //Task8: You have a list of students. Every element contains: second name, father name, first name,
    //year of birth, course, group number, five subject grades.
    //What needs to be done:
    //a) List is to be sorted by course (students are to be sorted by alphabet inside one course)
    //b) Average grade by all subjects for each group
    //c) Find out the youngest and the oldest student in university
    //d) Find the best student according to his average grade for each group

    private static DLinkedList<String> studentInfo(String str) {
        DLinkedList<String> studInfList = new DLinkedList<>();
        for (int i = 0; i < str.split(" ").length; i++) {
            if ((i % 2) != 0) {
                if (i / 2 == 0) {
                    studInfList.push(str.split(" ")[i], 0);
                }
                else {
                    studInfList.push(str.split(" ")[i]);
                }
            }
        }
        return studInfList;
    }
    public static DLinkedList<DLinkedList<String>> students(BufferedReader br) throws IOException {
        DLinkedList<DLinkedList<String>> list = new DLinkedList<>();
        String str = br.readLine();
        list.push(studentInfo(str), 0);
        while (true) {
            try {
                if ((str = br.readLine()) == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.push(studentInfo(str));
        }
        return list;
    }

    //a)
    private static void sortByCourse(DLinkedList<DLinkedList<String>> list) {
        DLinkedList<String> tmp;
        for (int i = list.getLength() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt((list.getNode(j).getValue()).getNode(4).getValue()) >
                        (Integer.parseInt((list.getNode(j + 1).getValue()).getNode(4).getValue()))) {
                    tmp = list.getNode(j).getValue();
                    list.getNode(j).setValue((list.getNode(j + 1).getValue()));
                    list.getNode(j + 1).setValue(tmp);
                }
            }
        }
    }
    private static String studName(DLinkedList<String> list) {
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sB.append(list.getNode(i).getValue());
        }
        return sB.toString();
    }
    private static void sortByAlphabet(DLinkedList<DLinkedList<String>> list) {
        DLinkedList<String> tmp;
        for (int i = list.getLength() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (!isAlphabet(studName(list.getNode(j).getValue()),
                        studName(list.getNode(j + 1).getValue()))) {
                    tmp = list.getNode(j).getValue();
                    list.getNode(j).setValue((list.getNode(j + 1).getValue()));
                    list.getNode(j + 1).setValue(tmp);
                }
            }
        }
    }
    public static void task8A(DLinkedList<DLinkedList<String>> list) {
        sortByAlphabet(list);
        sortByCourse(list);
    }

    //b)
    private static DLinkedList<Double> avgGradesGroup(Node<DLinkedList<String>> node) {
        DLinkedList<Double> gradeList = new DLinkedList<>();
        int counterStuds = 1;
        for (int i = 0; i < 5; i++) {
            gradeList.push(Double.parseDouble(node.getValue().getNode(6 + i).getValue()));
        }
        while (node.getNNode() != null && (node.getValue()).getNode(5).getValue().equals
                ((node.getNNode().getValue()).getNode(5).getValue())) {
            node = node.getNNode();
            for (int i = 0; i < 5; i++) {
                gradeList.getNode(i).setValue(Double.parseDouble(gradeList.getNode(i).getValue().toString()) +
                                                Double.parseDouble(node.getValue().getNode(6 + i).getValue()));
            }
            counterStuds++;
        }
        for (int i = 0; i < 5; i++) {
            gradeList.getNode(i).setValue
                    ((double)(((int) (100 * gradeList.getNode(i).getValue())) / (counterStuds)) / 100);
        }
        return gradeList;
    }
    public static  DLinkedList<DLinkedList<Double>> avgGradesList(DLinkedList<DLinkedList<String>> list) {
        DLinkedList<DLinkedList<Double>> avgGradesList = new DLinkedList<>();
        Node<DLinkedList<String>> tmp = list.getHead();
        avgGradesList.push(avgGradesGroup(tmp));
        while (tmp.getNNode() != null) {
            tmp = tmp.getNNode();
            if (!(tmp.getValue()).getNode(5).getValue().
                                equals((tmp.getPNode().getValue()).getNode(5).getValue())) {
                avgGradesList.push(avgGradesGroup(tmp));
            }
        }
        return avgGradesList;
    }

    //c)

}
