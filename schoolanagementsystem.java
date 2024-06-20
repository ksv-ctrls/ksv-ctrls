class User{
    private String id;
    private String name;
    private String password;
    private String role;

     User(String id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }
	public String getId() {
		return null;
	}
	public Object getPassword() {
		return null;
	}
}
class student extends User {
    private String familyDetails;
    
    student(String id, String name, String password, String familyDetails) {
        super(id, name, password, "Student");
        this.familyDetails = familyDetails;
    }
	public String getName() {
		return null;
	}
}
class Attendance {
    private String studentId;
    private Date date;
    private boolean present;

    Attendance(String studentId, Date date, boolean present) {
        this.studentId = studentId;
        this.date = date;
        this.present = present;
    }
    // Getters and Setters
    // toString() method
}

class UserDAO {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

     User getUser(String id) {
        return users.get(id);
    }
}
class StudentDAO {
    private Map<String, student> students = new HashMap<>();

    public void addStudent(student student) {
        students.put(student.getId(), student);
    }

    student getStudent(String id) {
        return students.get(id);
    }

    // Other CRUD operations
}

class AttendanceDAO {
    private List<Attendance> attendanceRecords = new ArrayList<>();

    public void markAttendance(Attendance attendance) {
        attendanceRecords.add(attendance);
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }

    // Other CRUD operations
}

class UserService {
    private UserDAO userDAO = new UserDAO();

    public void registerUser(User user) {
        userDAO.addUser(user);
    }

    public User loginUser(String id, String password) {
        User user = userDAO.getUser(id);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    public void registerStudent(student student) {
        studentDAO.addStudent(student);
    }

    public student getStudent(String id) {
        return studentDAO.getStudent(id);
    }
}
class AttendanceService {
    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    public void markAttendance(Attendance attendance) {
        attendanceDAO.markAttendance(attendance);
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceDAO.getAttendanceRecords();
    }
}
public class SchoolManagementSystem {
	public static void main(String[] args) {
		UserService userService = new UserService();
        StudentService studentService = new StudentService();
        AttendanceService attendanceService = new AttendanceService();
        
        Scanner scanner = new Scanner(System.in);

        // Registering a new student
        System.out.println("Register a new student:");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Family Details: ");
        String familyDetails = scanner.nextLine();

        student student = new student(id, name, password, familyDetails);
        studentService.registerStudent(student);

        // Logging in as the student
        System.out.println("Login as student:");
        System.out.print("Enter ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String loginPassword = scanner.nextLine();

        student loggedInStudent = (student) userService.loginUser(loginId, loginPassword);
        if (loggedInStudent != null) {
            System.out.println("Login successful! Welcome " + loggedInStudent.getName());
            // Mark attendance for the student
            System.out.println("Mark attendance for today.");
            Attendance attendance = new Attendance(loggedInStudent.getId(), new Date(), true);
            attendanceService.markAttendance(attendance);
            
            System.out.println("Attendance marked successfully!");
        } else {
            System.out.println("Invalid ID or Password.");
        }

	}

}
