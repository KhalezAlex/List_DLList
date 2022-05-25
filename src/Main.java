import java.io.BufferedReader;
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


        //Task1: You have 2 descend-ordered lists. Combine them to get one descend-ordered list
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
        // a) Positive valued elements b) Even index number elements
        /*Integer[] arrayOfIntsTask2 = {-99, 77, 73, 62, -45, 34, 14, -7, 2, 1, 4, -128};
        DLinkedList<Integer> list = new DLinkedList<>(arrayOfIntsTask2);
        list.print();
        HomeWorkDLList.task02A(list);
        list.print();
        HomeWorkDLList.task02B(list);
        list.print();*/


        //Task3: You have two lists. Find out if their sets are identical
        /*Integer[] arrayOfInts =          {-99, 77, 73, 62, -45, 34, 14, -7, 2, 1, 4, -128};
        Integer[] arrayOfIntsShuffled =  {-99, -45, 34, 4, 1, 2, -7, 77, 73, 62, 14, -128};
        DLinkedList<Integer> list1 = new DLinkedList<>(arrayOfInts);
        DLinkedList<Integer> list2 = new DLinkedList<>(arrayOfIntsShuffled);
        System.out.println(HomeWorkDLList.task03(list1, list2));*/


        //Task4: You have a list. Insert previous part of the list after each element
        /*Character[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
        DLinkedList<Character> list = new DLinkedList<>(charArray);
        list.print();
        HomeWorkDLList.task04(list);
        list.print();*/


        //Task5: The list contains words from the sentence. Replace all words "imathrepetitor" with a word "silence"
        /*String[] sentence = {"imathrepetitor", "Bible", "Decameron", "imathrepetitor", "Diary of a Bookseller", "imathrepetitor",
                "Joker", "Joker", "Jeeves and Wooster", "imathrepetitor", "imathrepetitor", "imathrepetitor",
                "Jungle book", "Lord of the Rings", "Treasure Island", "imathrepetitor"};
        String wordPrev = "imathrepetitor";
        String wordNew = "silence";
        DLinkedList<String> list = new DLinkedList<>(sentence);
        HomeWorkDLList.task05(list, wordPrev, wordNew);
        list.print();*/


        //Task6: You Have a text file. Get a double linked list which elements contain numbers of characters in each line
        /*String path = "Students.txt";
        HomeWorkDLList.task06(new BufferedReader(new FileReader(path))).print();*/


        //Task8: You have a list of students. Every element contains: second name, first name, father name,
        //year of birth, course, group number, five subject grades.
        //What needs to be done:
        //a) List is to be sorted by course (students are to be sorted by alphabet inside one course)
        //b) Average subject grades gor each group
        //c) Find out who is the youngest and the oldest student in university
        //d) Find the best student according to his average grade for each group
        /*
        //a)
        String path = "StudentsBuffer.txt";
        DLinkedList<Student> studList = HomeWorkDLList.students(new BufferedReader(new FileReader(path)));
        HomeWorkDLList.task08A(studList);
        for (int i = 0; i < studList.getLength(); i++) {
            studList.getNode(i).getValue().print();
        }

        //b)
        DLinkedList<Double[]> gradesList = HomeWorkDLList.task08B(studList);
        for (int i = 0; i < gradesList.getLength(); i++) {
            System.out.println(Arrays.toString(gradesList.getNode(i).getValue()));
        }

        //c)
        System.out.println("Самый молодой студент- " + HomeWorkDLList.task08C(studList,true));
        System.out.println("Самый старший студент- " + HomeWorkDLList.task08C(studList, false));

        //d)
        DLinkedList<String> bestStuds = HomeWorkDLList.task08D(studList);
        for (int i = 0; i < bestStuds.getLength(); i++) {
            System.out.println(bestStuds.getNode(i).getValue());
        }*/
    }
}
