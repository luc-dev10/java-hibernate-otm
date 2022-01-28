package com.otm;

import com.otm.entity.Course;
import com.otm.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            // begin transaction
            session.beginTransaction();

            // create a course
            Course course = new Course();
            course.setTitle("Course 1");

            // add some reviews
            Review review = new Review();
            review.setTitle("Horrible");

            Review review2 = new Review();
            review2.setTitle("Good");

            Review review3 = new Review();
            review3.setTitle("Meh");

            // add review
            course.addReview(review);
            course.addReview(review2);
            course.addReview(review3);

            // save the course
            session.save(course);

            // commit any transactions
            session.getTransaction().commit();

            // close
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
