package sustentabilidade.aplicacao.model.DB;

import java.sql.Connection;

public interface ConexaoDB {
    Connection obterConexao();

    void fecharConexao(Connection conexao);
}
