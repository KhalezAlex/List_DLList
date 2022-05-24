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
    public static DLinkedList<Integer> task01(DLinkedList<Integer> list1, DLinkedList<Integer> list2) {
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
    public static void task02A(DLinkedList<Integer> list) {
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

    private static Node<Integer> getNextEvenInd(Node<Integer> node) {
        return node.getNNode().getNNode();
    }
    public static void task02B(DLinkedList<Integer> list) {
        for (int i = 0; i < list.getLength() / 2; i++) {
            Node<Integer> node1 = list.getHead();
            Node<Integer> node2 = getNextEvenInd(list.getHead());
            while (node2 != null) {
                if (node1.getValue() > node2.getValue()) {
                    Integer tmp = node1.getValue();
                    node1.setValue(node2.getValue());
                    node2.setValue(tmp);
                }
                node1 = getNextEvenInd(node1);
                node2 = getNextEvenInd(node2);
            }
        }
    }



    //Task3: You have two lists. Find out if their sets are identical
    public static boolean task03(DLinkedList<Integer> listA, DLinkedList<Integer> listB) {
        if (listA.getLength() != listB.getLength()) {
            return false;
        }
        DLinkedList<Integer> list1 = new DLinkedList<>(listA);
        DLinkedList<Integer> list2 = new DLinkedList<>(listB);
        int length = list2.getLength();
        while (list1.getHead().getValue() != null) {
            list2.popValue(list1.getHead().getValue());
            if (list2.getLength() == length) {
                return false;
            }
            list1.popHead();
            length = list2.getLength();
        }
        return list2.isVoid();
    }

    //Task4:
    public static void task04(DLinkedList<Character> list) {
        Node<Character> nodeIndex;
        Node<Character> nodeTmp;
        for (int index = list.getLength() - 1; index > 0; index--) {
            nodeIndex = list.getHead();
            nodeTmp = list.getHead();
            for (int i = 0; i < index; i++) {
                nodeIndex = nodeIndex.getNNode();
            }
            if (index == list.getLength() - 1) {
                for (int j = 0; j < index; j++) {
                    list.push(nodeTmp.getValue());
                    nodeTmp = nodeTmp.getNNode();
                }
            } else {
                for (int j = 0; j < index; j++) {
                    list.push(nodeTmp.getValue(), index + j + 1);
                    nodeTmp = nodeTmp.getNNode();
                }
            }
        }
    }

    //Task5: The list contains words from the sentence. Replace all words "imathrepetitor" with a word "silence"
    public static void task05(DLinkedList<String> list, String prevWord, String changeWord) {
        Node<String> tmp = list.getHead();
        while (tmp != null) {
            if (tmp.getValue().equals(prevWord)) {
                tmp.setValue(changeWord);
            }
            tmp = tmp.getNNode();
        }
    }

    //Task6: You Have a text file. Get a double linked list which elements contain numbers of characters in each line
    public static DLinkedList<Integer> task06(BufferedReader bR) throws IOException {
        DLinkedList<Integer> list = new DLinkedList<>();
        String str = bR.readLine();
        list.setHead(new Node<>(str.length()));
        while (true) {
            try {
                if ((str = bR.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.push(str.length());
        }
        return list;
    }


    //Task8: You have a list of students. Every element contains: second name, first name, father name,
    //year of birth, course, group number, five subject grades.
    //What needs to be done:
    //a) List is to be sorted by course (students are to be sorted in alphabet order inside one course)
    //b) List with five average subject grades for each group
    //c) Find out who is the youngest and the oldest student in university
    //d) Find the best student according to his average grade for each group

    private static Student studentInfo(String str) {
        String[] sA = str.split(" ");
        String[] name = new String[]{sA[0], sA[1], sA[2]};
        int[] grades = new int[5];
        for (int i = 0; i < 5; i++) {
            grades[i] = Integer.parseInt(sA[6 + i]);
        }
        return new Student(name, Integer.parseInt(sA[3]), Integer.parseInt(sA[4]), sA[5], grades);
    }
    public static DLinkedList<Student> students(BufferedReader br) throws IOException {
        DLinkedList<Student> list = new DLinkedList<>();
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
    private static void sortByCourse(DLinkedList<Student> list) {
        Student tmp;
        for (int i = list.getLength() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (list.getNode(j).getValue().getCourse() > list.getNode(j + 1).getValue().getCourse()) {
                    tmp = list.getNode(j).getValue();
                    list.getNode(j).setValue((list.getNode(j + 1).getValue()));
                    list.getNode(j + 1).setValue(tmp);
                }
            }
        }
    }
    private static String studName(Student stud) {
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sB.append(stud.getName()[i]);
        }
        return sB.toString();
    }
    private static void sortByAlphabet(DLinkedList<Student> list) {
        Student tmp;
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
    public static void task08A(DLinkedList<Student> list) {
        sortByAlphabet(list);
        sortByCourse(list);
    }

    //b)
    private static Double[] avgGradesGroup(Node<Student> node) {
        Double[] avgGr = {0.0, 0.0, 0.0, 0.0, 0.0};
        int counterStuds = 0;
        while (node.getNNode() != null && (node.getValue()).getGroup().equals
                ((node.getNNode().getValue()).getGroup())) {
            for (int i = 0; i < 5; i++) {
                avgGr[i] += (double) node.getValue().getGrades()[i];
            }
            counterStuds++;
            node = node.getNNode();
        }
        counterStuds ++;
        for (int i = 0; i < 5; i++) {
            avgGr[i] += (double) node.getValue().getGrades()[i];
            avgGr[i] = (double) ((int) (100 * avgGr[i]) / (counterStuds)) / 100;
        }
        return avgGr;
    }
    public static DLinkedList<Double[]> task08B(DLinkedList<Student> list) {
        DLinkedList<Double[]> avgGradesList = new DLinkedList<>();
        Node<Student> tmp = list.getHead();
        avgGradesList.push(avgGradesGroup(tmp));
        while (tmp.getNNode() != null) {
            tmp = tmp.getNNode();
            if (!(tmp.getValue()).getGroup().equals((tmp.getPNode().getValue()).getGroup())) {
                avgGradesList.push(avgGradesGroup(tmp));
            }
        }
        return avgGradesList;
    }

    //c)
    private static String young(Node<Student> stud) {
        String name = stud.getValue().getName()[0].concat(" ").concat(stud.getValue().getName()[1]).
                concat(" ").concat(stud.getValue().getName()[2]);
        int year = stud.getValue().getYear();
        while (stud.getNNode() != null) {
            if (year < stud.getValue().getYear()) {
                year = stud.getValue().getYear();
                name = stud.getValue().getName()[0].concat(" ").concat(stud.getValue().getName()[1]).
                        concat(" ").concat(stud.getValue().getName()[2]);
            }
            stud = stud.getNNode();
        }
        return name;
    }
    private static String old(Node<Student> stud) {
        String name = stud.getValue().getName()[0].concat(" ").concat(stud.getValue().getName()[1]).
                concat(" ").concat(stud.getValue().getName()[2]);
        int year = stud.getValue().getYear();
        while (stud.getNNode() != null) {
            if (year > stud.getValue().getYear()) {
                year = stud.getValue().getYear();
                name = stud.getValue().getName()[0].concat(" ").concat(stud.getValue().getName()[1]).
                        concat(" ").concat(stud.getValue().getName()[2]);
            }
            stud = stud.getNNode();
        }
        return name;
    }
    public static String task08C(DLinkedList<Student> list, boolean isJun) {
        if (isJun) {
            return young(list.getHead());
        } else {
            return old(list.getHead());
        }
    }

    //d)
    private static String bestGrStud(Node<Student> stud) {
        String name = stud.getValue().getName()[0].concat(" ").concat(stud.getValue().getName()[1]).
                concat(" ").concat(stud.getValue().getName()[2]);
        double grade = stud.getValue().avgGrade();

        while (stud.getNNode() != null && (stud.getValue()).getGroup().equals(stud.getNNode().getValue().getGroup())) {
            if (grade < stud.getValue().avgGrade()) {
                grade = stud.getValue().avgGrade();
                name = stud.getValue().getName()[0].concat(" ").concat(stud.getValue().getName()[1]).
                        concat(" ").concat(stud.getValue().getName()[2]);
            }
            stud = stud.getNNode();
        }
        return name;
    }
    public static DLinkedList<String> task08D(DLinkedList<Student> list) {
        DLinkedList<String> avgGrList = new DLinkedList<>();
        Node<Student> tmp = list.getHead();
        avgGrList.push(bestGrStud(tmp));
        while (tmp.getNNode() != null) {
            tmp = tmp.getNNode();
            if (!tmp.getValue().getGroup().equals(tmp.getPNode().getValue().getGroup())) {
                avgGrList.push(bestGrStud(tmp));
            }
        }
        return avgGrList;
    }
}
