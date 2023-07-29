package br.com.aluraoracle.t5one;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.triggersConnection();
        Statement statement = connection.createStatement();
        statement.execute("delete from tbproduct where id > 1");
        int countLines = statement.getUpdateCount();
        System.out.println("Quantidade de linhas deletadas: " + countLines);
    }
}
