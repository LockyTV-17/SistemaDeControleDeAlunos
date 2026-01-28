
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlunoDAO {

    public void createAluno(String nome, String email) {
        ConexaoDB conexaoDB = new ConexaoDB();
        int idGerado;
        ResultSet rs;
        String insertSql = "INSERT INTO alunos (nome, email) VALUES (?,?)";

        //Forma moderna impedindo vazemento de memoria
        //try (Connection conexao = conexaoDB.getConnection();
        //PreparedStatement psInsert = conexao.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
        try {
            PreparedStatement psInsert;
            psInsert = conexaoDB.getConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

            psInsert.setString(1, nome);
            psInsert.setString(2, email);
            psInsert.executeUpdate();
            rs = psInsert.getGeneratedKeys();

            //Forma moderna
            //try(ResultSet rs = psInsert.getGeneratedKeys())    
            if (rs.next()) {
                idGerado = rs.getInt(1);
                System.out.printf("INSERT relizado com sucesso \n o Aluno %s foi inserido com o ID %d\n", nome, idGerado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no cadastro do aluno" + e.getMessage());// TODO: handle exception
        }

    }

    public void selecAluno(String nome, String email) {
        ResultSet resultadoDosDados;
        ConexaoDB conexaoDB = new ConexaoDB();
        try {
            PreparedStatement psSelec;
            String selectSql = "SELECT * FROM alunos WHERE nome = ? AND email = ?";
            psSelec = conexaoDB.getConnection().prepareStatement(selectSql);
            psSelec.setString(1, nome);
            psSelec.setString(2, email);

            resultadoDosDados = psSelec.executeQuery();
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
        }

    }

    public void updateAluno(String nome, String email, String novoEmail) {
        ConexaoDB conexaoDB = new ConexaoDB();
        String upadateSql = "UPDATE alunos SET email = ?  WHERE nome = ? AND email = ?";
        try {

            PreparedStatement psUpdate = conexaoDB.getConnection().prepareStatement(upadateSql);
            psUpdate.setString(1, novoEmail);
            psUpdate.setString(2, nome);
            psUpdate.setString(3, email);
            psUpdate.executeUpdate();

            System.out.println("O Aluno: " + nome + "\n" + "Teve o email: " + email + "\n" + "alterado para: " + novoEmail);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro em atulizar o aluno" + e.getMessage());
        }

    }

    public void deleteAluno(String nome, String email) {
        ConexaoDB conexaoDB = new ConexaoDB();
        String deleteSql = "DELETE FROM alunos WHERE email = ? AND nome = ?";
        try {
            PreparedStatement psDelete;
            psDelete = conexaoDB.getConnection().prepareStatement(deleteSql);
            psDelete.setString(1, email);
            psDelete.setString(2, nome);
            psDelete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro em Deletar o Aluno" + e.getMessage());
        }

    }
}
