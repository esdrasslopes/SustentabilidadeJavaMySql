package sustentabilidade.aplicacao.model.contratos;

import sustentabilidade.aplicacao.model.entidades.Empresa;

import java.util.List;

public interface RepositorioEmpresas {
    List<Empresa> buscarEmpresas();
}
