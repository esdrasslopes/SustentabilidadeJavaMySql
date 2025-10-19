package sustentabilidade.aplicacao.model.contratos;

import sustentabilidade.aplicacao.model.entidades.Usuario;

public interface RepositorioUsuarios {
    Usuario buscarPorEmail(String email);
}
