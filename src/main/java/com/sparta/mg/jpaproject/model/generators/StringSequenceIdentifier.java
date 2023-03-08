package com.sparta.mg.jpaproject.model.generators;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class StringSequenceIdentifier implements
        IdentifierGenerator {

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

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employees",
                "root",
                "root")
        ){
            Statement statement=connection.createStatement();

            List<Integer> ids = new ArrayList<>();
            ResultSet rs=statement.executeQuery("SELECT dept_no AS count FROM employees.departments");

            while(rs.next())
            {
                String total = rs.getString(1);
                String intValue = total.substring(1);
                int id = Integer.parseInt(intValue);
                ids.add(id);

            }
            Collections.sort(ids);
            int id = ids.get(ids.size()-1)+1;
            DecimalFormat formatIdToString = new DecimalFormat("#000");
            String generatedId = prefix + formatIdToString.format(id);
            return generatedId;
        } catch (SQLException e) {
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