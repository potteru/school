package com.example.school.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

@RestController
public class StudentController {
	
	@Autowired
	private StudentH2Service studentService;
	
	@GetMapping("/students")
	public ArrayList<Student> get_student_list(){
		
		return studentService.getStudentList();
	}
	@GetMapping("/students/{studentId}")
	public Student get_Student_ById(@PathVariable("studentId") int studentId) {
		return studentService.getStudentById(studentId);
	}
	
	@PostMapping("/students")
	public Student add_Student(@RequestBody Student student) {
		
		return studentService.addStudent(student);
	}
	
	@PostMapping("/students/{bulk}")
	public String add_Bulk_Students(@RequestBody ArrayList<Student> arrList ) {
		
		return studentService.addBulkStudents(arrList);
	}
	
	@PutMapping("/students/{studentId}")
	public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
		
		return studentService.updateStudent(studentId, student);
	}

	@DeleteMapping("/students/{studentId}")
	public void deleteStudent(@PathVariable("studentId") int studentId) {
		
		studentService.deleteStudent(studentId);
	}
	
}
