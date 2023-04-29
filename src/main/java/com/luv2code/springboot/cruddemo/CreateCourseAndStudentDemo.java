package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try (factory; session) {
            session.beginTransaction();

            Course course = new Course("Pacman - How To Score One Million Points");

            System.err.println("Save the course");
            session.persist(course);
            System.err.println(course);

            Student student1 = new Student("John", "Doe", "adsdsa@gmail.com");
            Student student2 = new Student("Mary", "Public", "fpasf@gmail.com");

            course.addStudent(student1);
            course.addStudent(student2);

            System.err.println("Saving students ...");
            session.persist(student1);
            session.persist(student2);
            System.err.println("Saved Students: " + course.getStudents());

            session.getTransaction().commit();

        }
    }

}

















