package sustentabilidade.aplicacao.model.repositorios;

import sustentabilidade.aplicacao.model.DB.ConexaoMySql;
import sustentabilidade.aplicacao.model.contratos.RepositorioEntregas;
import sustentabilidade.aplicacao.model.entidades.Beneficio;
import sustentabilidade.aplicacao.model.entidades.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntregasRepositorio implements RepositorioEntregas {
    private ConexaoMySql conexao;

    public EntregasRepositorio(ConexaoMySql conexao) {
        this.conexao = conexao;
    }

    @Override
    public List<Entrega> buscarEntregas(int idUsuario) {
        String sql = "SELECT * FROM entrega where usuario_id = ?";

        List<Entrega> entregas = new ArrayList<Entrega>();

        try (Connection conn = conexao.obterConexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String status = resultado.getString("status");
                int quantidadeItens = resultado.getInt("quantidade_itens");
                Timestamp ts = resultado.getTimestamp("data_entrega"); // pega do banco como Timestamp
                LocalDateTime dataEntrega = ts.toLocalDateTime();

                Entrega e = new Entrega(id, status, quantidadeItens, dataEntrega, idUsuario);

                entregas.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return entregas;
    }

    @Override
    public Beneficio buscarBeneficios(int entregaId) {
        String sql = "SELECT * FROM beneficio where entrega_id = ?";

        try (Connection conn = conexao.obterConexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, entregaId);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                double valor = resultado.getDouble("valor");

              return new Beneficio(valor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
