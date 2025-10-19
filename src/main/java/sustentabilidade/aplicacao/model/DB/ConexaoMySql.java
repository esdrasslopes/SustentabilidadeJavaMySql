package sustentabilidade.aplicacao.model.DB;

import sustentabilidade.aplicacao.model.env.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql implements ConexaoDB{
    private static Config config = new Config();
    private static String USUARIO = config.get("DB_USER");
    private static String SENHA = config.get("DB_PASS");
    private static String URL = config.get("DB_URL");

    @Override
    public Connection obterConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no MySQL: " + e.getMessage());
        }
    }

    @Override
    public void fecharConexao(Connection conexao) {
        try {
            conexao.close();
            System.out.println("Conexão fechada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
