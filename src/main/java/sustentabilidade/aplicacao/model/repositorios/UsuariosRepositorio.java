package sustentabilidade.aplicacao.model.repositorios;

import sustentabilidade.aplicacao.model.DB.ConexaoMySql;
import sustentabilidade.aplicacao.model.contratos.RepositorioUsuarios;
import sustentabilidade.aplicacao.model.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuariosRepositorio implements RepositorioUsuarios {
    private ConexaoMySql conexao;

    public UsuariosRepositorio(ConexaoMySql conexao) {
        this.conexao = conexao;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario u " +
                "INNER JOIN pessoa p ON p.id = u.pessoa_id " +
                "WHERE p.email = ?";

        try (Connection conn = conexao.obterConexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                double longitude = resultado.getDouble("longitude");
                double latitude = resultado.getDouble("latitude");

                return new Usuario(id, nome, longitude, latitude);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
