package com.otm;

import com.otm.entity.Course;
import com.otm.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            // begin transaction
            session.beginTransaction();

            // get course
            int id = 10;
            Course course = session.get(Course.class, id);

            // delete course
            session.delete(course);

            // commit any transactions
            session.getTransaction().commit();

            // close
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
