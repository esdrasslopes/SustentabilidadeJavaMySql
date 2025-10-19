package sustentabilidade.aplicacao.model.repositorios;

import sustentabilidade.aplicacao.model.DB.ConexaoMySql;
import sustentabilidade.aplicacao.model.contratos.RepositorioPontoColeta;
import sustentabilidade.aplicacao.model.entidades.Empresa;
import sustentabilidade.aplicacao.model.entidades.Material;
import sustentabilidade.aplicacao.model.entidades.PontoColeta;
import sustentabilidade.aplicacao.model.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PontoColetaRepositorio implements RepositorioPontoColeta {

    private ConexaoMySql conexao;

    public PontoColetaRepositorio(ConexaoMySql conexao) {
        this.conexao = conexao;
    }


    @Override
    public List<PontoColeta> buscarPontosColetaProximos(double usuarioLatitude, double usuarioLongitude) {
        String sql = """
                SELECT *,
                (
                  6371 * acos(
                    cos(radians(?)) * cos(radians(latitude)) *
                    cos(radians(longitude) - radians(?)) +
                    sin(radians(?)) * sin(radians(latitude))
                  )
                ) AS distance_km
                FROM ponto_de_coleta
                HAVING distance_km <= 10
                """;

        List<PontoColeta> pontosColeta = new ArrayList<PontoColeta>();

        try (Connection conn = conexao.obterConexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, usuarioLatitude);
            stmt.setDouble(2, usuarioLongitude);
            stmt.setDouble(3, usuarioLatitude);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                double longitude = resultado.getDouble("longitude");
                double latitude = resultado.getDouble("latitude");

                PontoColeta p = new PontoColeta(id, nome, latitude, longitude);

                pontosColeta.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pontosColeta;
    }

    @Override
    public List<Material> buscarMateriaisDoPonto(int pontoId) {
        String sql =
                "SELECT * FROM material WHERE ponto_coleta_id = ?";

        List<Material> materiais = new ArrayList<Material>();

        try (Connection conn = conexao.obterConexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pontoId);


            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                String nome = resultado.getString("nome");

                Material m = new Material(nome);

                materiais.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return materiais;
    }
}
