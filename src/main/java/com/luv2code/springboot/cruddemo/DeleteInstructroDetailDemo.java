package com.luv2code.springboot.cruddemo;


import com.luv2code.springboot.cruddemo.entity.Instructor;
import com.luv2code.springboot.cruddemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteInstructroDetailDemo {


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
            int id = 4;
            InstructorDetail instructorDetail =
                    session.get(InstructorDetail.class, id);

            System.out.println("instructorDetail: " + instructorDetail);
            System.out.println("the associated instructor: " + instructorDetail.getInstructor());

            System.out.println("Deleting instructorDetail: " + instructorDetail);
            // break bi-directional link
            instructorDetail.getInstructor().setInstructorDetail(null);
            session.remove(instructorDetail);

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}











