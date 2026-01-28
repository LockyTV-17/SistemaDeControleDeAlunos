import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
        public final String STR_CONNECTION = "";
        public final String USER = "";
        public final String PASSWORD = "";
       
        public  Connection getConnection() throws SQLException{
        return  DriverManager.getConnection(STR_CONNECTION,USER,PASSWORD); 
        }
}

