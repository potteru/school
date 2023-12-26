// Write your code here
package com.example.school.repository;

import java.util.ArrayList;

import com.example.school.model.Student;

public interface StudentRepository {

	ArrayList<Student> getStudentList();

	Student addStudent(Student student);

	String addBulkStudents(ArrayList<Student> arrlist);

	Student getStudentById(int studentId);

	Student updateStudent(int studentId, Student student);

	void deleteStudent(int studentId);

}
