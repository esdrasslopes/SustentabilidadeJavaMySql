package sustentabilidade.aplicacao.model.repositorios;

import sustentabilidade.aplicacao.model.DB.ConexaoMySql;
import sustentabilidade.aplicacao.model.contratos.RepositorioEmpresas;
import sustentabilidade.aplicacao.model.entidades.Empresa;
import sustentabilidade.aplicacao.model.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpresasRepositorio implements RepositorioEmpresas {
    private ConexaoMySql conexao;

    public EmpresasRepositorio(ConexaoMySql conexao) {
        this.conexao = conexao;
    }

    @Override
    public List<Empresa> buscarEmpresas() {
        String sql = "SELECT * FROM empresa";

        List<Empresa> empresas = new ArrayList<Empresa>();


        try (Connection conn = conexao.obterConexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                String cnpj = resultado.getString("cnpj");
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");

                Empresa e = new Empresa(cnpj, nome, email);

                empresas.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return empresas;
    }
}
