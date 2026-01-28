
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AlunoDAO {

    public void createAluno(String nome, String email) {
        int idGerado;
        ResultSet resultadoDosDados = null;
        PreparedStatement psInsert = null;
        String insertSql = "INSERT INTO alunos (nome, email) VALUES (?,?)";
        ConexaoDB conexaoDB = new ConexaoDB();
        Connection connection =  null;


        //Forma moderna impedindo vazemento de memoria
        //try (Connection conexao = conexaoDB.getConnection();
        //PreparedStatement psInsert = conexao.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
        try {
            connection = conexaoDB.getConnection();
            psInsert = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, nome);
            psInsert.setString(2, email);
            psInsert.executeUpdate();
            resultadoDosDados = psInsert.getGeneratedKeys();

            //Forma moderna
            //try(ResultSet rs = psInsert.getGeneratedKeys())    
            if (resultadoDosDados.next()) {
                idGerado = resultadoDosDados.getInt(1);
                System.out.printf("INSERT relizado com sucesso \n o Aluno %s foi inserido com o ID %d\n", nome, idGerado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no cadastro do aluno" + e.getMessage());// TODO: handle exception
        } finally{
            try {
                if(psInsert != null) psInsert.close();
                if(resultadoDosDados != null) resultadoDosDados.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void selecAluno(String nome, String email) {
        ConexaoDB conexaoDB = new ConexaoDB();
        Connection connection = null;
        ResultSet resultadoDosDados = null;
        PreparedStatement psSelec = null;
        String selectSql = "SELECT * FROM alunos WHERE nome = ? AND email = ?";
        try {
            connection = conexaoDB.getConnection();
            psSelec = connection.prepareStatement(selectSql);
            psSelec.setString(1, nome);
            psSelec.setString(2, email);

             resultadoDosDados= psSelec.executeQuery();
            System.out.println("resultado da Consulta");

            if (resultadoDosDados.next()) {
                System.out.println(
                        "ID: " + resultadoDosDados.getInt("matricula") + "\n"
                        + "name: " + resultadoDosDados.getString("nome") + "\n"
                        + "email: " + resultadoDosDados.getString("email") + "\n"
                        + "data cadastro: " + resultadoDosDados.getString("data_cadastro"));
            } else {
                System.out.println("Nada encontrado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no achar do aluno" + e.getMessage());
        }finally{
            try {
                if(psSelec !=null) psSelec.close();
                if(resultadoDosDados !=null) resultadoDosDados.close();
                if(connection != null)connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateAluno(String nome, String email, String novoEmail) {
        ConexaoDB conexaoDB = new ConexaoDB();
        Connection connection = null;
        PreparedStatement psUpdate = null;
        String upadateSql = "UPDATE alunos SET email = ?  WHERE nome = ? AND email = ?";
        try {

            connection = conexaoDB.getConnection();
            psUpdate = connection.prepareStatement(upadateSql);
            psUpdate.setString(1, novoEmail);
            psUpdate.setString(2, nome);
            psUpdate.setString(3, email);
            psUpdate.executeUpdate();

            System.out.println("O Aluno: " + nome + "\n" + "Teve o email: " + email + "\n" + "alterado para: " + novoEmail);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro em atulizar o aluno" + e.getMessage());
        }finally {
            try {
                if (psUpdate != null) psUpdate.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteAluno(String nome, String email) {
        ConexaoDB conexaoDB = new ConexaoDB();
        PreparedStatement psDelete = null;
        String deleteSql = "DELETE FROM alunos WHERE email = ? AND nome = ?";
        Connection connection = null;
        
        try {
            connection = conexaoDB.getConnection();

            psDelete = connection.prepareStatement(deleteSql);
            psDelete.setString(1, email);
            psDelete.setString(2, nome);
            psDelete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro em Deletar o Aluno" + e.getMessage());
        } finally {
            try {
                if (psDelete != null)  psDelete.close();
                if (connection != null)  connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
