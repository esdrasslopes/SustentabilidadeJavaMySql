package sustentabilidade.aplicacao.service;

import sustentabilidade.aplicacao.model.contratos.RepositorioEmpresas;
import sustentabilidade.aplicacao.model.entidades.Empresa;

import java.util.List;

public class EmpresaService {

    private RepositorioEmpresas repositorioEmpresas;

    public EmpresaService(RepositorioEmpresas repositorioEmpresas) {
        this.repositorioEmpresas = repositorioEmpresas;
    }

    public void buscarEmpresas() {
        List<Empresa> empresas = this.repositorioEmpresas.buscarEmpresas();

        if (empresas.isEmpty()) {
            System.out.println("Não há empresas disponíveis.");
        } else {
            System.out.println("\n===== Empresas Disponíveis =====\n");

            int contador = 1;

            for (Empresa e : empresas) {
                System.out.println("Empresa " + contador++);
                e.apresentar();
                System.out.println("------------------------------");
            }

            System.out.println("Total de empresas: " + empresas.size() + "\n");
        }
    }


}
