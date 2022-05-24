public class Student {
    private String[] name;
    private final int year;
    private final int course;
    private final String group;
    private int[] grades;

    private final String[] subj = {"Математика", "Русский язык", "Литература", "Психология", "Философия"};


    public String[] getName() {
        return name;
    }
    public void setName(String[] name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public int getCourse() {
        return course;
    }
    public String getGroup() {
        return group;
    }
    public int[] getGrades() {
        return grades;
    }
    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    private boolean grValid(int[] gr) {
        if (!(gr.length == subj.length)) {
            return false;
        }
        for (int j : gr) {
            if (j > 5 || j < 2) {
                return false;
            }
        }
        return true;
    }

    public Student(String[] name, int year, int course, String group, int[] grades) {
        if (name.length == 3) {
            this.name = name;
        }
        this.year = year;
        this.course = course;
        this.group = group;
        if (grValid(grades)) {
            this.grades = grades;
        }
    }


    public double avgGrade() {
        int gradeSum = 0;
        for (int grade : this.grades) {
            gradeSum += grade;
        }
        return (double) gradeSum / this.grades.length;
    }

    public void print(){
        for (String s : name) {
            System.out.print(s + " ");
        }
        System.out.print(" г.р. " + year + "  ");
        System.out.print("курс " + course + "  ");
        System.out.print("группа " + group + "  ");
        for (int i = 0; i < subj.length; i++) {
            System.out.print(subj[i] + " " + grades[i] + " ");
        }
        System.out.println();
    }
}
