package com.kniziol.serwer.repository.connections;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbConnection {

    private static DbConnection connection = new DbConnection();

    private final static String url = "jdbc:mysql://localhost:3306/cinema_db?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    private final static String username = "root";

    private final static String password = "root";

    private Jdbi jdbi;


    private final static String movies =
                                        """
                                            create table if not exists movies (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            title VARCHAR(50) NOT NULL,
                                            genre VARCHAR(50) NOT NULL,
                                            price DECIMAL(4,2),
                                            duration INT,
                                            release_date DATE);
                                        """;

    private final static String salesStand =
                                        """
                                            create table if not exists sales_stand (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            customer_id INT(11),
                                            movies_id INT(11));
                                        """;

    private final static String user =
                                        """
                                            create table if not exists user (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            name VARCHAR(50) NOT NULL,
                                            surname VARCHAR(50) NOT NULL,
                                            password VARCHAR(50) NOT NULL,
                                            age INT,
                                            email VARCHAR(50),
                                            loyaltyCardId INT,
                                            foreign key (loyaltyCardId) references loyaltycard(id) on delete cascade on update cascade);
                                        """;

    private final static String loyaltyCard =
                                        """
                                            create table if not exists loyaltycard (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            expirationDate DATE,
                                            discount DECIMAL,
                                            moviesNumber INT(11));
                                        """;


    @PostConstruct
    public void init() {

        // inicjalizacja polaczenia
        jdbi = Jdbi.create(url, username, password);

        // tworzenie tabeli
        jdbi.useHandle(handle -> handle.execute(movies));
        jdbi.useHandle(handle -> handle.execute(salesStand));
        jdbi.useHandle(handle -> handle.execute(user));
        jdbi.useHandle(handle -> handle.execute(loyaltyCard));
    }

    public static DbConnection getConnection() {
        return connection;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }
}
