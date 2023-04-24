package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.Instructor;
import com.luv2code.springboot.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetInstructorDetailDemo {


    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try (factory; session) {
            session.beginTransaction();
            int id = 2999;
            InstructorDetail instructorDetail =
                    session.get(InstructorDetail.class, id);

            System.out.println("instructorDetail: " + instructorDetail);
            System.out.println("the associated instructor: " + instructorDetail.getInstructor());


            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}





