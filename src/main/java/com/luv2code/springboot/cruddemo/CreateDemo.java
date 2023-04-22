package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.Instructor;
import com.luv2code.springboot.cruddemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try (factory) {
            Instructor instructor =
                    new Instructor("Oleh", "Baibula", "baibula.oo@gmail.com" );
            InstructorDetail instructorDetail =
                    new InstructorDetail("youtube/oleh", "sex");

            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();

            session.persist(instructor);

            session.getTransaction().commit();

        }
    }

}





