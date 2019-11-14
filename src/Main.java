import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    //poor-mans database
    private static ArrayList<Admin> adminDB = new ArrayList<>();
    private static ArrayList<Student> studentDB = new ArrayList<>();
    private static ArrayList<Faculty> facultyDB = new ArrayList<>();
    private static ArrayList<Course> courseDB = new ArrayList<>();
    private static ArrayList<Link> linkDB = new ArrayList<>();
    
    private static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        //call database constructor methods
        createAdmins();
        CreateStudentDB();
        CreateFacultyDB();
        CreateCourseDB();
        CreateLinkDB();

        // list contents of databases
//        showStudentDB();
//        showFacultyDB();
//        showCourseDB();
//        showAllLinksByCourse();

        // adding user information
        String userStr;

        // requires valid login to access app
        validateLogIn();

        do {
            System.out.print("\nOptions:\n\n" +
                    "\t1 - Add Student\n" +
                    "\t2 - Add Faculty\n" +
                    "\t3 - Edit Student\n" +
                    "\t4 - Edit Faculty\n" +
                    "\t5 - Add Course\n" +
                    "\t6 - Edit Course\n" +
                    "\t7 - Enroll Student\n" +
                    "\t8 - Hire a Faculty\n" +
                    "\t9 - View Databases\n\n" +
                    "\tEnter \"Q\" to Logout\n\n" +
                    "Your Choice: ");
            userStr = keyboard.nextLine();

            if (userStr.equalsIgnoreCase("1")) {
                createNewStudent();
            } else if (userStr.equalsIgnoreCase("2")) {
                createNewFaculty();
            } else if (userStr.equalsIgnoreCase("3")) {
                editStudent();
            } else if (userStr.equalsIgnoreCase("4")) {
                editFaculty();
            } else if (userStr.equalsIgnoreCase("5")) {
                createNewCourse();
            } else if (userStr.equalsIgnoreCase("6")) {
                editCourse();
            } else if (userStr.equalsIgnoreCase("7")) {
                enrollStudent();
            } else if (userStr.equalsIgnoreCase("8")) {
                hireFaculty();
            } else if (userStr.equalsIgnoreCase("9")) {
                System.out.print("Which database would you like to display? " +
                        "\nStudent [\"S\"] Faculty [\"F\"] Courses [\"C\"] All [\"A\"] Quit [\"Q\"]: ");
                userStr = keyboard.nextLine();

                if (userStr.equalsIgnoreCase("S")) {
                    showStudentDB();
                } else if (userStr.equalsIgnoreCase("F")) {
                    showFacultyDB();
                } else if (userStr.equalsIgnoreCase("C")) {
                    showCourseDB();
                } else if (userStr.equalsIgnoreCase("A")) {
                    showAllLinksByCourse();
                } else if (userStr.equalsIgnoreCase("Q")) {
                    break;
                }

            } else if (userStr.equalsIgnoreCase("Q")) {
                System.out.println("\nYou have logged out! See ya later <3");
               break;
            }
        } while (true);

    }

    private static void createNewStudent() {
        String name;
        String email;
        String password;

        System.out.print("\nPlease enter the Student's name: ");
        name = keyboard.nextLine();
        System.out.print("Please enter the Student's Email: ");
        email = keyboard.nextLine();
        System.out.print("Please enter the Student's Password: ");
        password = keyboard.nextLine();

        System.out.println("\nYou have successfully added a new student!");
        // setting person's id to be one larger than the current largest (thus making each id unique)
        // i.e if the last personID is 10, the new one will be 11
        int id = studentDB.size() + 1;
        studentDB.add(new Student(id, name, email, password));
    }

    private static void createNewFaculty() {
        String name;
        String email;
        String password;

        System.out.print("\nPlease enter the Faculty member's name: ");
        name = keyboard.nextLine();
        System.out.print("Please enter the Faculty member's Email: ");
        email = keyboard.nextLine();
        System.out.print("Please enter the Faculty member's Password: ");
        password = keyboard.nextLine();

        System.out.println("\nYou have successfully added a new Faculty member!");
        int id = facultyDB.size() + 1;
        facultyDB.add(new Faculty(id, name, email, password));
    }

    private static void createNewCourse() {
        String name;
        String description;
        int teacherId;

        System.out.print("\nPlease enter the Course name: ");
        name = keyboard.nextLine();
        System.out.print("Please enter the Course description: ");
        description = keyboard.nextLine();
        System.out.print("Please input the teacher ID you would like to assign to this course: ");
        teacherId = keyboard.nextInt();

        System.out.println("\nYou have successfully added a new Course!");
        // setting person's id to be one larger than the current largest (thus making each id unique)
        // i.e if the last personID is 10, the new one will be 11
        int id = courseDB.size() + 1;
        courseDB.add(new Course(id, teacherId, name, description));
    }

    private static void editStudent() {
        System.out.println("Enter the ID of the person you would like to edit...");
        showStudentDB();
        System.out.print("Your choice: ");
        int userId = keyboard.nextInt();
        keyboard.nextLine();

        // setting object to null so IDE doesn't complain about return
        Student myStudent = null;

        // find the person they entered and REMEMBER IT (to actual person object)
        for (Student student : studentDB) {
            if (student.getId() == userId) {
                myStudent = student;
            }
        }

        // assuming we found it and remembered it
        // replace said name with user input
        if (myStudent != null) {
            System.out.print("Please enter a new Name: ");
            String userStr = keyboard.nextLine();
            myStudent.setName(userStr);

            System.out.println("Successfully changed information to: ");
            System.out.println("\tId: " + myStudent.getId() + " Name: " + myStudent.getName());
        } else {
            System.out.println("Sorry, I could not find that student.");
        }
    }

    private static void editFaculty() {
        System.out.println("Enter the ID of the person you would like to edit...");
        showFacultyDB();
        System.out.print("Your choice: ");
        int userId = keyboard.nextInt();
        keyboard.nextLine();

        Faculty myFaculty = null;

        for (Faculty faculty : facultyDB) {
            if (faculty.getId() == userId) {
                myFaculty = faculty;
            }
        }

        if (myFaculty != null) {
            System.out.print("Please enter a new Name: ");
            String userStr = keyboard.nextLine();
            myFaculty.setName(userStr);

            System.out.println("Successfully changed information to: ");
            System.out.println("\tId: " + myFaculty.getId() + " Name: " + myFaculty.getName());
        } else {
            System.out.println("Sorry, I could not find that faculty member.");
        }
    }

    private static void editCourse() {
        System.out.println("Enter the ID of the Course you would like to edit...");
        showCourseDB();
        System.out.print("Your choice: ");
        int userId = keyboard.nextInt();
        keyboard.nextLine();

       Course myCourse = null;

        for (Course course : courseDB) {
            if (course.getId() == userId) {
                myCourse = course;
            }
        }

        if (myCourse != null) {
            System.out.print("Please enter a new Name: ");
            String userStr = keyboard.nextLine();
            myCourse.setName(userStr);

            System.out.println("Successfully changed information to: ");
            System.out.println("\tId: " + myCourse.getId() + " Name: " + myCourse.getName());
        } else {
            System.out.println("Sorry, I could not find that Course.");
        }
    }

    private static void hireFaculty() {
        System.out.println("Enter the ID of the Course you would like to assign a new teacher to...");
        showCourseDB();
        System.out.print("Your choice: ");
        int userId = keyboard.nextInt();
        keyboard.nextLine();

        Course myCourse = null;

        for (Course course : courseDB) {
            if (course.getId() == userId) {
                myCourse = course;
            }
        }

        if (myCourse != null) {
            System.out.print("Please enter the ID of the Faculty Member you would like to hire: ");
            showFacultyDB();
            System.out.print("Your choice: ");
            int userStr = keyboard.nextInt();
            myCourse.setId(userStr);

            System.out.println("Successfully hired new teacher.");
        } else {
            System.out.println("Sorry, I could not find that Faculty member.");
        }
    }

    private static void enrollStudent() {
        int studentId;
        System.out.println("\nPlease enter the ID of the student you wish to enroll...");
        showStudentDB();
        System.out.print("Your choice: ");
        studentId = keyboard.nextInt();
        keyboard.nextLine();
        String studentName = LookUpStudentName(studentId);

        int courseId;
        System.out.println("\nPlease enter the ID of the Course you would like " + studentName + " be enrolled in.");
        showCourseDB();
        System.out.print("Your choice: ");
        courseId = keyboard.nextInt();
        keyboard.nextLine();
        String courseName = LookUpCourseName(courseId);

        System.out.println("We are enrolling " + studentName + " in " + courseName);
        System.out.print("Enter the date of enrollment for " + studentName + " in " + courseName + ": ");
        String enrollDate = keyboard.nextLine();

        System.out.println();
        System.out.println("Successfully enrolled " + studentName + " in " + courseName);

        int id = linkDB.size() + 1;
        linkDB.add(new Link(id, studentId, courseId, enrollDate));
    }

    private static void showAllLinksByCourse() {
        int teacherCount = 0;

        System.out.println();
        for (Course course : courseDB) {
            System.out.println("★ " + course.getName() + ": ");
            System.out.println("\tStudents: ");
            // loop through all links
            for (Link link : linkDB) {
                // see if the link belongs to current company
                if (link.getCourseId() == course.getId()) {
                    // loop through all students
                    for (Student student : studentDB) {
                        if (student.getId() == link.getStudentId()) {
                            System.out.println("\tId: " + student.getId() + "\tName: " + student.getName());
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    private static void showStudentDB() {
        // for loop goes through database array and prints its contents
        System.out.println("\nStudent Database: ");
        for (Student student : studentDB) {
            System.out.println("\tId: " + student.getId() + "\tName: " + student.getName());
        }
        System.out.println();
    }

    private static void showFacultyDB() {
        System.out.println("\nFaculty Database: ");
        for (Faculty faculty : facultyDB) {
            System.out.println("\tId: " + faculty.getId() + "\tName: " + faculty.getName());
        }
        System.out.println();
    }

    private static void showCourseDB() {
        System.out.println("\nCourse Database: ");
        for (Course course : courseDB) {
            System.out.println("\tId: " + course.getId() + "\tName: " + course.getName());
        }
        System.out.println();
    }

    private static String LookUpStudentName(int studentId) {
        for (Student student : studentDB) {
            if (student.getId() == studentId) {
                return student.getName();
            }
        }
        return "Unknown";
    }

    private static String LookUpFacultyName(int facultyId) {
        for (Faculty faculty : facultyDB) {
            if (faculty.getId() == facultyId) {
                return faculty.getName();
            }
        }
        return "Unknown";
    }

    private static String LookUpCourseName(int courseId) {
        for (Course course : courseDB) {
            if (course.getId() == courseId) {
                return course.getName();
            }
        }
        return "Unknown";
    }

    private static void createAdmins(){
        adminDB.add(new Admin(1, "pierz", "pierz@gmail.com", "password"));
    }

    private static void CreateStudentDB() {
        studentDB.add(new Student(1, "Jenn", "jenn@gmail.com", "jennpass"));
        studentDB.add(new Student(2, "Mark", "mark@gmail.com", "markpass"));
        studentDB.add(new Student(3, "Tyrone", "tyrone@gmail.com", "tyronepass"));
        studentDB.add(new Student(4, "Justine", "justine@gmail.com", "justinepass"));
        studentDB.add(new Student(5, "Alexa", "alexa@gmail.com", "alexapass"));
        studentDB.add(new Student(6, "Mary", "mary@gmail.com", "marypass"));
        studentDB.add(new Student(7, "Kevin", "kevin@gmail.com", "kevinpass"));
        studentDB.add(new Student(8, "John", "john@gmail.com", "johnpass"));
    }

    private static void CreateFacultyDB() {
        facultyDB.add(new Faculty(1, "Collins", "collins@gmail.com", "collinspass"));
        facultyDB.add(new Faculty(2, "Barry", "barry@gmail.com", "barrypass"));
        facultyDB.add(new Faculty(3, "Mila", "mila@gmail.com", "milapass"));
        facultyDB.add(new Faculty(4, "Moss", "moss@gmail.com", "mosspass"));
    }

    private static void CreateCourseDB() {
        // FACULTY:
        // 1 = Mr Collins || 2 = Mrs Barry || 3 = Mrs Mila || 4 = Mr Moss
        // courseDB.add(new Course(1, 1, "Science", "Blow stuff up!"));

        courseDB.add(new Course(1, 2, "Science", "Blow stuff up!"));
        courseDB.add(new Course(2, 1, "Math", "Fall asleep."));
        courseDB.add(new Course(3, 4, "Art", "Draw even though you suck lol."));
        courseDB.add(new Course(4, 3,  "PE", "torture but you get credits for it."));
    }

    private static void CreateLinkDB() {
        // STUDENTS:
        // 1 = Jenn || 2 = Mark || 3 = Tyrone || 4 = Justine || 5 = Alexa || 6 = Mary || 7 = Kevin
        
        // COURSE:
        // 1 = Science || 2 = Math || 3 = Art || 4 = PE
        
        linkDB.add(new Link(1, 1, 1, "January 2020"));
        linkDB.add(new Link(2, 2, 1, "January 2020"));

        linkDB.add(new Link(3, 3, 2, "February 2020"));
        linkDB.add(new Link(4, 4, 2, "Janaury 2020"));

        linkDB.add(new Link(5, 5, 4, "January 2020"));
        linkDB.add(new Link(6, 6, 4, "February 2020"));

        linkDB.add(new Link(7, 7, 3, "March 2020"));
        linkDB.add(new Link(8, 8, 3, "March 2020"));
    }

    private static boolean validateLogIn() {
        // requires user to input valid username and password to use app

        String userName, userPassword;
        int counter = 0;

        while (true) {

            System.out.println("\n|▓▓▓【☆】▓▓▓| Pierz's School System |▓▓▓【☆】▓▓▓|");

            System.out.print("Please enter a valid username: ");
            userName = keyboard.nextLine();
            System.out.print("Please enter a valid password: ");
            userPassword = keyboard.nextLine();


            // loops through entire userDB array and compares keyboard input to it
            for (Admin admin : adminDB) {
                // returns true (thus ending loop) if a matching username and password combination are found
                if (admin.getName().equals(userName)) {
                    if (admin.getPassword().equals(userPassword)) {
                        System.out.println("\nYou have successfully logged in!");
                        return true;
                    }
                }
            }

            System.out.println("\nLol you failed logging in try again loser");
        }
    }
}
