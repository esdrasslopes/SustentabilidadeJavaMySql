package sustentabilidade.aplicacao.model.contratos;

import sustentabilidade.aplicacao.model.entidades.Beneficio;
import sustentabilidade.aplicacao.model.entidades.Entrega;

import java.util.List;

public interface RepositorioEntregas {
    List<Entrega> buscarEntregas(int idUsuario);
   Beneficio buscarBeneficios(int entregaId);
}
