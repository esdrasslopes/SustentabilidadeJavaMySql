package sustentabilidade.aplicacao.model.entidades;

import java.util.List;

public class PontoColeta {
    private int id;
    private String nome;
    private double latitude;
    private double longitude;
    private List<Material> materias;

    public PontoColeta(int id,String nome, double latitude, double longitude){
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getId(){
        return id;
    }

    public List<Material> getMaterias() {
        return materias;
    }

    public void apresentar(){
        System.out.println("Nome: " + this.nome);
    }
}
