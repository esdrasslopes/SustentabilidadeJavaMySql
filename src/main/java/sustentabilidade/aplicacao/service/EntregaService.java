package sustentabilidade.aplicacao.service;

import sustentabilidade.aplicacao.model.contratos.RepositorioEntregas;
import sustentabilidade.aplicacao.model.entidades.Beneficio;
import sustentabilidade.aplicacao.model.entidades.Entrega;

import java.util.List;

public class EntregaService {
    private RepositorioEntregas repositorioEntregas;

    public EntregaService(RepositorioEntregas repositorioEntregas) {
        this.repositorioEntregas = repositorioEntregas;
    }

    public void buscarEntregas(int idUsuario) {
        List<Entrega> entregas = this.repositorioEntregas.buscarEntregas(idUsuario);

        if (entregas.isEmpty()) {
            System.out.println("Não há entregas disponíveis.");
        } else {
            System.out.println("\n===== Entregas Disponíveis =====\n");

            int contador = 1;

            for (Entrega e : entregas) {
                System.out.println("Entrega " + contador++);
                e.apresentar();
                Beneficio beneficioEntrega = this.repositorioEntregas.buscarBeneficios(e.getId());
                beneficioEntrega.apresentar();
                System.out.println("------------------------------");
            }

            System.out.println("Total de entregas: " + entregas.size() + "\n");
        }
    }
}
