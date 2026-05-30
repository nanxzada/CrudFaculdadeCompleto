import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static final String URL = "jdbc:mysql://localhost:3306/Faculdade" +
            "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "R3n4nm0t4080406%";

    public static Connection conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL não foi encontrado!");
        }catch (SQLException e){
            System.out.println("Erro ao conectar:" +e.getMessage());
        }
        return null;
    }
}

