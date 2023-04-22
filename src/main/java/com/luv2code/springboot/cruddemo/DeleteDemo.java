package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.Instructor;
import com.luv2code.springboot.cruddemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

import static java.util.Objects.*;


public class DeleteDemo {

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
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, 3);
            requireNonNull(instructor);

            System.out.println("Found instructor: " + instructor);
            System.out.println("Deletin:" + instructor);

            session.remove(instructor);

            session.getTransaction().commit();

        }
    }

}





