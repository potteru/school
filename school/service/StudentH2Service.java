/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here
package com.example.school.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

@Service
public class StudentH2Service implements StudentRepository {

	@Autowired
	private JdbcTemplate db;

	@Override
	public ArrayList<Student> getStudentList() {

		Collection<Student> studentlist = db.query("select * from student", new StudentRowMapper());
		ArrayList<Student> arrList = new ArrayList<>(studentlist);
		return arrList;
	}

	@Override
	public Student addStudent(Student student) {
		db.update("insert into student(studentName, Gender, Standard) values(?,?,?)", student.getStudentName(),
				student.getGender(), student.getStandard());
		Student savedStudent = db.queryForObject(
				"select * from student where studentName = ? and gender = ? and Standard = ? ",
				new StudentRowMapper(), student.getStudentName(), student.getGender(), student.getStandard());
		return savedStudent;
	}

	@Override
	public String addBulkStudents(ArrayList<Student> arrlist) {
		int size = arrlist.size();
		for (Student student : arrlist) {
			addStudent(student);
		}
		return "size";
	}

	@Override
	public Student getStudentById(int studentId) {
		try {
			Student student = (Student) db.query("select * from student where studentId = ? ", new StudentRowMapper(),
					studentId);
			return student;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Student updateStudent(int studentId, Student student) {
		try {
			if (student.getStudentName() != null) {
				db.update("update student set studentName = ? where studentId = ? ", student.getStudentName(),
						studentId);
			}
			if (student.getGender() != null) {
				db.update("update student set Gender = ? where studentId = ? ", student.getGender(), studentId);
			}
			if (student.getStandard() != 0) {
				db.update("update student set Standard = ? where studentId = ? ", student.getStandard(), studentId);
			}

			return getStudentById(studentId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteStudent(int studentId) {

		db.update("delete from student where studentId = ? ", studentId);

	}

}
