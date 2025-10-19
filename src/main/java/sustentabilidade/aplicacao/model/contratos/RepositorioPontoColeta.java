package sustentabilidade.aplicacao.model.contratos;

import sustentabilidade.aplicacao.model.entidades.Material;
import sustentabilidade.aplicacao.model.entidades.PontoColeta;

import java.util.List;

public interface RepositorioPontoColeta {
    List<PontoColeta> buscarPontosColetaProximos(double usuarioLatitude, double usuarioLongitude);

    List<Material> buscarMateriaisDoPonto(int pontoId);
}
