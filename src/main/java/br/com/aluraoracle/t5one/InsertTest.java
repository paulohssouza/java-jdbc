package br.com.aluraoracle.t5one;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.triggersConnection();
        Statement statement = connection.createStatement();
        statement.execute("insert into tbproduct(name, description) values" +
                "('Microsoft Mouse', 'O mouse leve, portátil e ergonômico é perfeito " +
                "para navegação precisa em movimento Sem cabos ou dongle — conecta-se " +
                "sem fio ao seu computador via Bluetooth A roda melhorada é otimizada " +
                "para rolagem suave e natural Funciona em praticamente qualquer " +
                "superfície graças à tecnologia BlueTrack Design elegante e esculpido " +
                "— cabe confortavelmente em suas mãos' )", Statement.RETURN_GENERATED_KEYS);
        ResultSet resultSet = statement.getGeneratedKeys();
        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            System.out.println("ID criado: " + id);
        }
    }
}
