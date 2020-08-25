import java.util.List;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
public class Student {

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getCourses() {
        return courses;
    }

    private String name;
    private int age;
    private List<String> courses;

    public Student(String name, int age, List<String> courses) {
    }


}