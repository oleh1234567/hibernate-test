package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AddCoursesForMaryDemo {

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

            Student mary = session.get(Student.class, 2);
            System.err.println(mary);
            System.err.println(mary.getCourses());

            Course course1 = new Course("Rubik's cube");
            Course course2 = new Course("Atari 2600");

            course1.addStudent(mary);
            course2.addStudent(mary);

            System.err.println("saving the courses");

            session.persist(course1);
            session.persist(course2);

            session.getTransaction().commit();

        }
    }

}

















