package com.springboot.mvc.DAOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.springboot.mvc.Interfaces.IDAO;
import com.springboot.mvc.models.Customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAO implements IDAO<Customer> {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Customer> getAll() {
    Session session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    try {
      TypedQuery<Customer> query = session.createQuery("From Customer order by lastName", Customer.class);
      return query.getResultList();
    } catch (Exception exception) {
      exception.printStackTrace();
      return new ArrayList<Customer>();
    } finally {
      tx.commit();
      session.close();
    }
  }

  public void seeding() {

    deleteAll();

    Session session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();

    Customer customer1 = new Customer("Residue", "Theorem", "residuetheorem@gmail.com");
    Customer customer2 = new Customer("Fermat's", "Last Theorem", "fermatlastthm@gmail.com");
    Customer customer3 = new Customer("Riemann", "Hypothesis", "riemannhypothesis@gmail.com");
    Customer customer4 = new Customer("Banach", "Space", "bspace@gmail.com");

    session.save(customer1);
    session.save(customer2);
    session.save(customer3);
    session.save(customer4);

    tx.commit();
    session.close();
  }

  public void deleteAll() {
    Session session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();

    TypedQuery<Customer> query = session.createQuery("From Customer", Customer.class);
    List<Customer> customers = query.getResultList();

    for (Customer customer : customers) {
      session.delete(customer);
    }

    tx.commit();
    session.close();
  }

  @Override
  public void add(Customer customer) {
    Session session = sessionFactory.getCurrentSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(customer);
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      tx.commit();
      session.close();
    }
  }
}