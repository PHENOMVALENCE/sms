package com.sms.service;

import com.sms.model.Student;
import com.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return repo.findById(id);
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}
