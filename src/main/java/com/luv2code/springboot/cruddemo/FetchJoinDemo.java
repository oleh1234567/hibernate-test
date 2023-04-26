package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.Course;
import com.luv2code.springboot.cruddemo.entity.Instructor;
import com.luv2code.springboot.cruddemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;


public class FetchJoinDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try (factory; session) {
            session.beginTransaction();

            int id = 1;

            Query<Instructor> query =
                    session.createQuery("SELECT i FROM Instructor i "
                                    + "JOIN FETCH i.courses "
                                    + "WHERE i.id = :instructorId",
                            Instructor.class);

            query.setParameter("instructorId", id);

            Instructor instructor = query.getSingleResult();

            System.out.println("Oleh: Instructor: " + instructor);

            session.getTransaction().commit();
            session.close();

            System.out.println("Oleh: Courses: " + instructor.getCourses());

        }
    }

}

















