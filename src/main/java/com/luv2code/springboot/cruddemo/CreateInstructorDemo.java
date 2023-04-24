package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.Course;
import com.luv2code.springboot.cruddemo.entity.Instructor;
import com.luv2code.springboot.cruddemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructorDemo {

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
            Instructor instructor =
                    new Instructor("White", "Black", "gggg.public@gmail.com" );
            InstructorDetail instructorDetail =
                    new InstructorDetail("youtube/Black", "final");

            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();

            session.persist(instructor);

            session.getTransaction().commit();

        }
    }

}





