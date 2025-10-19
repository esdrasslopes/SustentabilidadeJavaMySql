package sustentabilidade.aplicacao.model.entidades;

public class Beneficio {
    private double valor;

    public Beneficio(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void apresentar(){
        System.out.println("Benefício: " + valor);
    }
}
