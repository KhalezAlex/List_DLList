import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //Task0: You have an ordered list of books. Insert a new book saving the alphabet order
        /*String[] arrayOfBooksTask0 = {"Bible", "Decameron", "Diary of a Bookseller", "Jeeves and Wooster",
                "Jungle book", "Lord of the Rings", "Treasure Island"};
        String book1Task0 = "Joker";
        String book2Task0 = "Ancient History";
        String book3Task0 = "AncientOnes";
        String book4Task0 = "Treasures of The Caribbean";
        DLinkedList<String> list = new DLinkedList<>(arrayOfBooksTask0);
        HomeWorkDLList.task0(list, book1Task0);
        list.print();
        HomeWorkDLList.task0(list,book2Task0);
        list.print();
        HomeWorkDLList.task0(list, book3Task0);
        list.print();
        HomeWorkDLList.task0(list, book4Task0);
        list.print();*/


        //Task1: You have 2 descend-ordered lists. Combine them and get one descend-ordered list
        /*Integer[] arrayOfInts1Task1 = {77, 66, 45, 22, 14, 7, 3, 1, 0};
        Integer[] arrayOfInts2Task1 = {129, 77, 64, 62, 47};
        Integer[] arrayOfInts3Task1 = {77, 21, 20, 18, 11, 4, 2, 1, 0};
        Integer[] arrayOfInts4Task1 = {0};
        Integer[] arrayOfInts5Task1 = {200};
        HomeWorkDLList.task1(new DLinkedList<>(arrayOfInts1Task1), new DLinkedList<>(arrayOfInts2Task1)).print();
        HomeWorkDLList.task1(new DLinkedList<>(arrayOfInts2Task1), new DLinkedList<>(arrayOfInts1Task1)).print();
        HomeWorkDLList.task1(new DLinkedList<>(arrayOfInts1Task1), new DLinkedList<>(arrayOfInts3Task1)).print();
        HomeWorkDLList.task1(new DLinkedList<>(arrayOfInts1Task1), new DLinkedList<>(arrayOfInts4Task1)).print();
        HomeWorkDLList.task1(new DLinkedList<>(arrayOfInts1Task1), new DLinkedList<>(arrayOfInts5Task1)).print();*/


        //Task2: You have a list of integer values. Next elements are to be sorted in ascending order:
        // a) Positive valued elements b) even index number elements
        /*Integer[] arrayOfIntsTask2 = {-99, 77, 73, 62, -45, 34, 14, -7, 2, 1, 4, -128};
        DLinkedList<Integer> list = new DLinkedList<>(arrayOfIntsTask2);
        list.print();
        HomeWorkDLList.task2A(list);
        list.print();*/


        //Task8: You have a list of students. Every element contains: second name, father name, first name,
        //year of birth, course, group number, five subject grades.
        //What needs to be done:
        //a) List is to be sorted by course (students are to be sorted by alphabet inside one course)
        //b) Average grade by all subjects gor each group
        //c) Find out the youngest and the oldest student in university
        //d) Find the best student according to his average grade for each group

        //a)
        String path = "Students.txt";
        DLinkedList<DLinkedList<String>> list = HomeWorkDLList.students(new BufferedReader(new FileReader(path)));

        HomeWorkDLList.task8A(list);
        for (int i = 0; i < list.getLength(); i++) {
            list.getNode(i).getValue().print();
        }
        DLinkedList<DLinkedList<Double>> gradesList = HomeWorkDLList.avgGradesList(list);
        for (int i = 0; i < gradesList.getLength(); i++) {
            gradesList.getNode(i).getValue().print();
        }
    }
}
