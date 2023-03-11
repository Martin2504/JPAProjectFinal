package com.sparta.mg.jpaproject.model.generators;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeGenerator implements IdentifierGenerator {

    private final JdbcTemplate template;

    @Autowired
    public EmployeeGenerator(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {

        try {
            Integer id = template.queryForObject("SELECT MAX(emp_no) FROM employees.employees", Integer.class);


            return id+1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
