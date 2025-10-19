package sustentabilidade.aplicacao.service;

import sustentabilidade.aplicacao.model.contratos.RepositorioUsuarios;
import sustentabilidade.aplicacao.model.entidades.Usuario;

public class UsuarioService {
    private RepositorioUsuarios repositorioUsuarios;

    public UsuarioService(RepositorioUsuarios repositorioUsuario) {
        this.repositorioUsuarios = repositorioUsuario;
    }

    public Usuario buscarUsuario(String email) {
        return this.repositorioUsuarios.buscarPorEmail(email);
    }
}
