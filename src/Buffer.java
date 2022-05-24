import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Buffer {
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


    private static boolean isAlphabet(String w1, String w2) {
        for (int i = 0; i < Math.min(w1.length(), w2.length()); i++) {
            if (Character.toLowerCase(w1.charAt(i)) != Character.toLowerCase(w2.charAt(i))) {
                return (Character.toLowerCase(w1.charAt(i)) < Character.toLowerCase(w2.charAt(i)));
            }
        }
        return (w1.length() < w2.length());
    }
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


    public static void main(String[] args) throws IOException {
        String path = "StudentsBuffer.txt";
        DLinkedList<Student> studList = students(new BufferedReader(new FileReader(path)));
        task08A(studList);
        for (int i = 0; i < studList.getLength(); i++) {
            studList.getNode(i).getValue().print();
        }
        DLinkedList<Double[]> gradesList = task08B(studList);
        for (int i = 0; i < gradesList.getLength(); i++) {
            System.out.println(Arrays.toString(gradesList.getNode(i).getValue()));
        }
        System.out.println("Самый молодой студент- " + task08C(studList,true));
        System.out.println("Самый старший студент- " + task08C(studList, false));
        DLinkedList<String> bestStuds = task08D(studList);
        for (int i = 0; i < bestStuds.getLength(); i++) {
            System.out.println(bestStuds.getNode(i).getValue());
        }
    }
}
