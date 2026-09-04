package com.kulkarniGroups.OrderManagementSystem.config;

import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInfoRunner implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseInfoRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            System.out.println("=================================");
            System.out.println("URL      : " + con.getMetaData().getURL());
            System.out.println("User     : " + con.getMetaData().getUserName());
            System.out.println("Catalog  : " + con.getCatalog());
            System.out.println("=================================");
        }
    }
}