package br.com.aluraoracle.t5one;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.triggersConnection();
        PreparedStatement statement = connection.prepareStatement(
                "delete from tbproduct where id > ?");
        statement.setInt(1, 2);
        statement.execute();
        int countLines = statement.getUpdateCount();
        System.out.println("Quantidade de linhas deletadas: " + countLines);
        connection.close();
    }

}
