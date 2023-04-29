package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.Course;
import com.luv2code.springboot.cruddemo.entity.Instructor;
import com.luv2code.springboot.cruddemo.entity.InstructorDetail;
import com.luv2code.springboot.cruddemo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndReviewDemo {

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

            Course course = new Course("Pacman - How To Score One Million Points");

            course.addReview(new Review("Great course ... love it"));
            course.addReview(new Review("Cool course, job well done"));
            course.addReview(new Review("What a dumb course, you are an idiot"));

            System.out.println("Saving the course");
            System.out.println(course);
            System.out.println(course.getReviews());
            session.persist(course);

            session.getTransaction().commit();

        }
    }

}

















