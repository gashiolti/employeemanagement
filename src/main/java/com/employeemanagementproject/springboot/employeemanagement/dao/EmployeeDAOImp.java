package com.employeemanagementproject.springboot.employeemanagement.dao;

import com.employeemanagementproject.springboot.employeemanagement.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOImp implements EmployeeDAO {

    // DEFINE FIELD FOR ENTITYMANAGER

    private EntityManager entityManager;

    // SET UP CONSTRUCTOR INJECTION

    @Autowired
    public EmployeeDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public List<Employee> findAll() {

        // GET THE CURRENT HIBERNATE SESSION
        Session session = entityManager.unwrap(Session.class);

        // CREATE A QUERY
        Query<Employee> query = session.createQuery("from Employee", Employee.class);

        // EXECUTE QUERY AND GET RESULT LIST
        List<Employee> employeeList = query.getResultList();

        return employeeList;
    }

    @Override
    public Employee findById(int id) {

        // GET THE CURRENT HIBERNATE SESSION
        Session session = entityManager.unwrap(Session.class);

        // GET THE EMPLOYEE
        Employee employee = session.get(Employee.class, id);

        // RETURN THE EMPLOYEE
        return employee;
    }

    @Override
    public void save(Employee employee) {

        // GET THE CURRENT HIBERNATE SESSION
        Session session = entityManager.unwrap(Session.class);

        // SAVE THE EMPLOYEE
        session.saveOrUpdate(employee);

    }

    @Override
    public void deleteById(int id) {

        // GET THE CURRENT HIBERNATE SESSION
        Session session = entityManager.unwrap(Session.class);

        // DELETE OBJECT WITH PRIMARY KEY
        Query query = session.createQuery("delete from Employee where id=:employeeId");

        query.setParameter("employeeId", id);

        query.executeUpdate();
    }
}
