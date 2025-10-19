package sustentabilidade.aplicacao.service;

import sustentabilidade.aplicacao.model.contratos.RepositorioPontoColeta;
import sustentabilidade.aplicacao.model.entidades.Entrega;
import sustentabilidade.aplicacao.model.entidades.Material;
import sustentabilidade.aplicacao.model.entidades.PontoColeta;
import sustentabilidade.aplicacao.model.repositorios.PontoColetaRepositorio;

import java.util.List;

public class PontoColetaService {
    private RepositorioPontoColeta repositorioPontoColeta;

    public PontoColetaService(RepositorioPontoColeta repositorioPontoColeta) {
        this.repositorioPontoColeta = repositorioPontoColeta;
    }

    public void buscarPontos(double latitudeUsuario, double longitudeUsuario) {
        List<PontoColeta> pontosColeta =
                this.repositorioPontoColeta.buscarPontosColetaProximos(latitudeUsuario, longitudeUsuario);

        if (pontosColeta.isEmpty()) {
            System.out.println("Não há pontos de coleta disponíveis.");
        } else {
            System.out.println("\n===== Pontos de coleta próximo de você em até 10km =====\n");

            int contador = 1;

            for (PontoColeta p : pontosColeta) {
                System.out.println("\n========================================");
                System.out.println("Ponto de Coleta " + contador++);
                System.out.println("========================================");

                p.apresentar();

                List<Material> materiais = this.repositorioPontoColeta.buscarMateriaisDoPonto(p.getId());

                if (materiais.isEmpty()) {
                    System.out.println("Nenhum material aceito neste ponto.");
                } else {
                    System.out.println("\nMateriais aceitos neste ponto:");
                    System.out.println("----------------------------------------");
                    for (Material m : materiais) {
                        m.apresentar();
                    }
                }

                System.out.println("----------------------------------------\n");
            }

            System.out.println("------------------------------");

            System.out.println("Total de pontosColeta: " + pontosColeta.size() + "\n");
        }
    }
}
