package com.savana.shop.usecases.cdi.simple;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import com.savana.shop.entities.Course;
import com.savana.shop.entities.Student;
import com.savana.shop.usecases.cdi.dao.CourseDAO;
import com.savana.shop.usecases.cdi.dao.StudentDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
@Slf4j
public class RequestUseCaseControllerJPA {

    @Getter
    private Course course = new Course();
    @Getter
    private Student student = new Student();
    @Getter
    private List<Student> allStudents;

    @PostConstruct
    public void init() {
        loadAllStudents();
    }

    @Inject
    private CourseDAO courseDAO;
    @Inject
    private StudentDAO studentDAO;

    @Transactional
    public void createCourseStudent() {
        student.getCourseList().add(course);
        course.getStudentList().add(student);
        courseDAO.create(course);
        studentDAO.create(student);
        log.info("Maybe OK...");
    }

    private void loadAllStudents() {
        allStudents = studentDAO.getAllStudents();
    }
}
