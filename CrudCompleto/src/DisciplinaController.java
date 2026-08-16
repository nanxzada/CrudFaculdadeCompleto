import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.plaf.nimbus.State;

public class DisciplinaController {
   String sql = "";
    public void criarTabela(){
        sql = "CREATE TABLE IF NOT EXIST Disciplina(" +
                "idDisciplina INT AUTO_INCREMENT PRIMARY KEY," +
                "nomeDisciplina VARCHAR(150)," +
                "descricao VARCHAR(300)," +
                "professor VARCHAR(100))";
        try (
                Connection conn = Conexao.conectar();
                Statement stmt = conn.createStatement()
                ){
            stmt.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela: "+e.getMessage());
        }
    }
    public void inserir (Disciplina disciplina){
        sql = "INSERT INTO Disciplina (nomeDisciplina, descricao, professor) VALUE (?,?,?)";
        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, disciplina.getNomeDisciplina());
            stmt.setString(2, disciplina.getDescricao());
            stmt.setString(3, disciplina.getProfessor());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Disciplina Cadastrada com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Disciplina: "+e.getMessage());
        }
    }
    public void alterar(Disciplina disciplina){
        sql = "UPDATE Disciplina SET nomeDisciplina = ?, descricao = ?, professor = ? WHERE idDisciplina = ?";
        try(
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            stmt.setString(1, disciplina.getNomeDisciplina());
            stmt.setString(2, disciplina.getDescricao());
            stmt.setString(3, disciplina.getProfessor());
            stmt.setInt(4, disciplina.getIdDisciplina());
            int linhas = stmt.executeUpdate();
            if (linhas >0 ){
                JOptionPane.showMessageDialog(null, "Disciplina Alterada com Sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Disciplina não Encontrada.");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Alterar Disciplina: "+e.getMessage());
        }
    }
    public void deletar(int idDisciplina){
        sql = "DELETE FROM Disciplina WHERE idDisciplina = ?";
        try(
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            stmt.setInt(1,idDisciplina);
            int linhas = stmt.executeUpdate();
            if (linhas > 0){
                JOptionPane.showMessageDialog(null,"Disciplina Excluida com Sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Disciplina Não Encontrada.");
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Erro ao Excluir Disciplina: "+e.getMessage());
        }
    }
    public void listar (){
        sql = "SELECT * FROM Disciplina";
        try(
                Connection conn = Conexao.conectar();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
                ){
            System.out.println("======== Lista ========");
            while (rs.next()){
                System.out.println(rs.getInt("idDisciplina") + " | " +
                                rs.getString("nomeDisciplina") + " | " +
                                rs.getString("descricao") + " | " +
                                rs.getString("professor") + " | ");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Listar Disciplinas "+ e.getMessage());
        }
    }
    public void estatisticaDisciplina(){
     sql = "SELECT d.idDisciplina, d.nomeDisciplina, COUNT(a.id_curso) AS total_alunos, SUM(a.valor) AS ValorTotalCurso " +
             "FROM Disciplina d " +
             "LEFT JOIN Alunos a ON d.idDisciplina = a.id_curso " +
             "GROUP BY d.idDisciplina, d.nomeDisciplina;";
     try(
             Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             ){
         while (rs.next()){
             System.out.println("ID_DISCIPLINA: " + rs.getInt("idDisciplina")+" | Disciplina: " +
                     rs.getString("nomeDisciplina")+ " | QTD DE ALUNOS: " +
                     rs.getInt("total_alunos") + " | R$: " +
                     rs.getDouble("ValorTotalCurso"));
         }
     } catch (SQLException e) {
         System.out.println("Erro ao listar estatisticas: "+ e.getMessage());
     }
    }
}
