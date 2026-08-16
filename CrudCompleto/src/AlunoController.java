import javax.swing.*;
import java.sql.*;

public class AlunoController {
    String sql = "";
    public void criarTabela(){
        String sql = "CREATE TABLE IF NOT EXIST Alunos("+
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(25)," +
                "id_curso INT," +
                "valor DECIMAL (10,2)," +
                "contraint fk_curso FOREING KEY (id_curso) REFERENCES Disciplina (idDisciplina))";
        try (
                Connection conn = Conexao.conectar();
                Statement stmt = conn.createStatement();
                ){
            stmt.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela "+ e.getMessage());
        }

    }
    public void inserir(Aluno aluno){
        sql = "INSERT INTO Alunos(nome, id_curso, valor) VALUES (?,?,?)";
        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getCurso());
            stmt.setDouble(3, aluno.getValor());
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Aluno Cadastrado com Sucesso!");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao executar acao:"+e.getMessage());
        }
    }
    public void alterar(Aluno aluno){
        sql = "UPDATE Alunos" +
                "SET nome=?, id_curso=?,valor=?" +
                "WHERE id=?";
        try(
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getCurso());
            stmt.setDouble(3, aluno.getValor());
            stmt.setInt(4, aluno.getId());
            int linhas = stmt.executeUpdate();
            if(linhas > 0){
                JOptionPane.showMessageDialog(null, "Aluno alterado.");
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao alterar: "+e.getMessage());
        }
    }
    public void deletar (int id){
        sql = "DELETE From Aluno" +
                "WHERE id=?";
        try(
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, id);
            int linhas =stmt.executeUpdate();
            if (linhas > 0){
                JOptionPane.showMessageDialog(null,"Aluno Excluido.");
            } else {
                JOptionPane.showMessageDialog(null, "Aluno nao Encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Nao foi possivel deletar o aluno: "+e.getMessage());

        }
    }
    public void listar(){
        sql = "SELECT * FROM Alunos";
        try (
            Connection conn = Conexao.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
                ){
            System.out.println("======== Lista ========");
            while(rs.next()){
                System.out.println(
                        rs.getInt("ID") + " | " +
                                rs.getString("Nome") + " | " +
                                rs.getString("ID_Curso")+ " | R$ " +
                                rs.getDouble("Valor")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Nao foi possível listar a tabela: "+e.getMessage());
        }
    }
}
