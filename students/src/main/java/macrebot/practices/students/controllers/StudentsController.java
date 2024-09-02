package macrebot.practices.students.controllers;

import org.springframework.web.bind.annotation.RestController;

import macrebot.practices.students.models.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "John", "john@mail.com"),
            new Student(2, "Jane", "jane@mail.com"),
            new Student(3, "Bob", "bob@mail.com")));

    // Show all the students
    @GetMapping
    public List<Student> getStudents() {
        return this.students;
    }

    // Show a student by the email
    @GetMapping("/{email}")
    public Student getStudentByName(@PathVariable String email) {
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return s;
            }
        }
        return null;
    }

    // Create a student
    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        this.students.add(student);
        return student;
    }

    // Update the informaation of a student (partially and tottaly)
    @PutMapping
    public Student putStudent(@RequestBody Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                s.setId(student.getId());
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                return s;
            }
        }
        return null;
    }

    @PatchMapping
    public Student patchStudent(@RequestBody Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                if (student.getName() != null) {
                    s.setName(student.getName());
                }
                if (student.getEmail() != null) {
                    s.setEmail(student.getEmail());
                }
                return s;
            }
        }
        return null;
    }

    // Delete a Student by their ID
    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                return s;
            }
        }
        return null;
    }

}
