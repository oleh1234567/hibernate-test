package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.Course;
import com.luv2code.springboot.cruddemo.entity.Instructor;
import com.luv2code.springboot.cruddemo.entity.InstructorDetail;
import com.luv2code.springboot.cruddemo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetCourseAndReviewDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try (factory; session) {
            session.beginTransaction();

            int id = 3;
            Course course = session.get(Course.class, id);

            System.err.println(course);
            System.err.println(course.getReviews());

            session.getTransaction().commit();

        }
    }

}

















