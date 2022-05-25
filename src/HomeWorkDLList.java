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

    private static void pushNotHead(DLinkedList<String> list, String book) {
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

    public static void task0(DLinkedList<String> list, String book) {
        if (isAlphabet(book, list.getHead().getValue())) {
            list.push(book, 0);
        } else {
            pushNotHead(list, book);
        }
    }


    //Task1: You have 2 descend-ordered lists. Merge them to get one descend-ordered list
    private static DLinkedList<Integer> merge(DLinkedList<Integer> list, Node<Integer> node1, Node<Integer> node2) {
        int count = 0;
        while (node1 != null && node2 != null) {
            if (node2.getValue() >= node1.getValue()) {
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
        if (list1.getLength() >= list2.getLength()) {
            return merge(list1, list1.getHead(), list2.getHead());
        } else {
            return merge(list2, list2.getHead(), list1.getHead());
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

    private static void changeValues(Node<Integer> node1, Node<Integer> node2) {
        Integer tmp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(tmp);
    }

    private static boolean bubbleIteration(Node<Integer> node1, Node<Integer> node2) {
        int counter = 0;
        while (node2 != null) {
            assert node1 != null;
            if (node1.getValue() > node2.getValue()) {
                changeValues(node1, node2);
                counter++;
            }
            node1 = getNextPos(node1);
            node2 = getNextPos(node2);
        }
        return counter == 0;
    }

    private static void ifNegativeHead(DLinkedList<Integer> list) {
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

    private static void ifPositiveHead(DLinkedList<Integer> list) {
        for (int i = 0; i < list.getLength() - 1; i++) {
            Node<Integer> node1 = list.getHead();
            Node<Integer> node2 = getNextPos(list.getHead());
            if (bubbleIteration(node1, node2)) {
                break;
            }
        }
    }

    public static void task02A(DLinkedList<Integer> list) {
        if (list.getHead().getValue() <= 0) {
            ifNegativeHead(list);
        } else {
            ifPositiveHead(list);
        }
    }

    private static Node<Integer> getNextEvenInd(Node<Integer> node) {
        return node.getNNode().getNNode();
    }

    private static void evenBubbleIteration(DLinkedList<Integer> list) {
        Node<Integer> node1 = list.getHead();
        Node<Integer> node2 = getNextEvenInd(list.getHead());
        while (node2 != null) {
            if (node1.getValue() > node2.getValue()) {
                changeValues(node1, node2);
            }
            node1 = getNextEvenInd(node1);
            node2 = getNextEvenInd(node2);
        }
    }

    public static void task02B(DLinkedList<Integer> list) {
        for (int i = 0; i < list.getLength() / 2; i++) {
            evenBubbleIteration(list);
        }
    }


    //Task3: You have two lists. Find out if their sets are identical

    private static boolean isIdentical(DLinkedList<Integer> list1, DLinkedList<Integer> list2) {
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

    public static boolean task03(DLinkedList<Integer> listA, DLinkedList<Integer> listB) {
        if (listA.getLength() != listB.getLength()) {
            return false;
        }
        DLinkedList<Integer> list1 = new DLinkedList<>(listA);
        DLinkedList<Integer> list2 = new DLinkedList<>(listB);
        return isIdentical(list1, list2);
    }


    //Task4: You have a list. Insert previous part of the list after each element
    private static void pushLastInd(DLinkedList<Character> list, Node<Character> tmp, int index) {
        for (int j = 0; j < index; j++) {
            list.push(tmp.getValue());
            tmp = tmp.getNNode();
        }
    }

    private static void pushNotLastInd(DLinkedList<Character> list, Node<Character> tmp, int index) {
        for (int j = 0; j < index; j++) {
            list.push(tmp.getValue(), index + j + 1);
            tmp = tmp.getNNode();
        }
    }

    private static void pushIteration(DLinkedList<Character> list, int index) {
        Node<Character> nodeIndex = list.getHead();
        Node<Character> tmp = list.getHead();
        for (int i = 0; i < index; i++) {
            nodeIndex = nodeIndex.getNNode();
        }
        if (index == list.getLength() - 1) {
            pushLastInd(list, tmp, index);
        } else {
            pushNotLastInd(list, tmp, index);
        }
    }

    public static void task04(DLinkedList<Character> list) {
        for (int index = list.getLength() - 1; index > 0; index--) {
            pushIteration(list, index);
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
    private static void initializeList(DLinkedList<Integer> list, BufferedReader bR) throws IOException {
        String str = bR.readLine();
        list.push(str.length());
        while ((str = bR.readLine()) != null) {
            list.push(str.length());
        }
    }

    public static DLinkedList<Integer> task06(BufferedReader bR) throws IOException {
        DLinkedList<Integer> list = new DLinkedList<>();
        initializeList(list, bR);
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
        String[] studInfoArr = str.split(" ");
        String[] name = new String[]{studInfoArr[0], studInfoArr[1], studInfoArr[2]};
        int[] grades = new int[5];
        for (int i = 0; i < 5; i++) {
            grades[i] = Integer.parseInt(studInfoArr[6 + i]);
        }
        return new Student(name, Integer.parseInt(studInfoArr[3]), Integer.parseInt(studInfoArr[4]),
                            studInfoArr[5], grades);
    }

    public static DLinkedList<Student> students(BufferedReader br) throws IOException {
        DLinkedList<Student> list = new DLinkedList<>();
        String str = br.readLine();
        list.push(studentInfo(str), 0);
        while ((str = br.readLine()) != null) {
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
        Student studTmp;
        for (int i = list.getLength() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (!isAlphabet(studName(list.getNode(j).getValue()),
                        studName(list.getNode(j + 1).getValue()))) {
                    studTmp = list.getNode(j).getValue();
                    list.getNode(j).setValue((list.getNode(j + 1).getValue()));
                    list.getNode(j + 1).setValue(studTmp);
                }
            }
        }
    }

    public static void task08A(DLinkedList<Student> list) {
        sortByAlphabet(list);
        sortByCourse(list);
    }

    //b)
    //считает студентов и меняет суммарную оценку по предметам одновременно- нет смысла разделять методы
    private  static int counterStuds(Double[] avgGr, Node<Student> node) {
        int counterStuds = 0;
        while (node.getNNode() != null && (node.getValue()).getGroup().equals
                ((node.getNNode().getValue()).getGroup())) {
            for (int i = 0; i < 5; i++) {
                avgGr[i] += (double) node.getValue().getGrades()[i];
            }
            counterStuds++;
            node = node.getNNode();
        }
        counterStuds++;
        for (int i = 0; i < 5; i++) {
            avgGr[i] += (double) node.getValue().getGrades()[i];
        }
        return counterStuds;
    }

    private static Double[] avgGradesGroup(Node<Student> node) {
        Double[] avgGr = {0.0, 0.0, 0.0, 0.0, 0.0};
        int counterStuds = counterStuds(avgGr,node);
        for (int i = 0; i < 5; i++) {
            avgGr[i] = (double) ((int) (100 * avgGr[i]) / (counterStuds)) / 100;
        }
        return avgGr;
    }

    public static DLinkedList<Double[]> task08B(DLinkedList<Student> list) {
        DLinkedList<Double[]> avgGradesList = new DLinkedList<>();
        Node<Student> studTmp = list.getHead();
        avgGradesList.push(avgGradesGroup(studTmp));
        while (studTmp.getNNode() != null) {
            studTmp = studTmp.getNNode();
            if (!(studTmp.getValue()).getGroup().equals((studTmp.getPNode().getValue()).getGroup())) {
                avgGradesList.push(avgGradesGroup(studTmp));
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
        Node<Student> studTmp = list.getHead();
        avgGrList.push(bestGrStud(studTmp));
        while (studTmp.getNNode() != null) {
            studTmp = studTmp.getNNode();
            if (!studTmp.getValue().getGroup().equals(studTmp.getPNode().getValue().getGroup())) {
                avgGrList.push(bestGrStud(studTmp));
            }
        }
        return avgGrList;
    }
}
