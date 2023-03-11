package com.sparta.mg.jpaproject.model.generators;

import com.sparta.mg.jpaproject.model.repositories.DepartmentRepository;
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

    private final EntityManager entityManager;

    @Autowired
    public StringSequenceIdentifier(EntityManager entityManager) {
        this.entityManager = entityManager;
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

        try {
            Session session = (Session) entityManager.getDelegate();

            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                   Statement statement = connection.createStatement();
                }
            });

            List<String> idStrings = departmentRepository.getAllDepartmentIds();
            List<Integer> ids = new ArrayList<>();
            for (String s: idStrings) {
                ids.add(Integer.parseInt(s.substring(1)));
            }

            Collections.sort(ids);
            int id = ids.get(ids.size()-1)+1;
            DecimalFormat formatIdToString = new DecimalFormat("#000");
            String generatedId = prefix + formatIdToString.format(id);
            return generatedId;
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