import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
        public final String STR_CONNECTION = "jdbc:mysql://127.0.0.1:3306/sistema_escolar";
        public final String USER = "root";
        public final String PASSWORD = "157157";
       
        public  Connection getConnection() throws SQLException{
        return  DriverManager.getConnection(STR_CONNECTION,USER,PASSWORD); 
        }
}

