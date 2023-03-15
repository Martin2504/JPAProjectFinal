package com.sparta.mg.jpaproject.model.generators;

import jakarta.persistence.EntityManager;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Service
public class StringSequenceIdentifier implements
        IdentifierGenerator {

    private final JdbcTemplate template;

    @Autowired
    public StringSequenceIdentifier(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        IdentifierGenerator.super.configure(type, params, serviceRegistry);
    }

    @Override
    public void registerExportables(Database database) {
        IdentifierGenerator.super.registerExportables(database);
    }

    @Override
    public void initialize(SqlStringGenerationContext context) {
        IdentifierGenerator.super.initialize(context);
    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {

        String prefix = "d";
        List<Integer> ids = new ArrayList<>();

        try {
            List<String> idStrings = template.queryForList("SELECT dept_no AS count FROM employees.departments", String.class);

            for (String s: idStrings) {
                ids.add(Integer.parseInt(s.substring(1)));
            }

            Collections.sort(ids);
            int id = ids.get(ids.size()-1)+1;
            DecimalFormat formatIdToString = new DecimalFormat("#000");
            return prefix + formatIdToString.format(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return IdentifierGenerator.super.supportsJdbcBatchInserts();
    }
}