package sustentabilidade.aplicacao.model.entidades;

import sustentabilidade.aplicacao.model.contratos.RepositorioUsuarios;

public class Usuario {
    private int id;
    private String nome;
    private double longitude;
    private double latitude;
    private RepositorioUsuarios repositorioUsuario;

    public Usuario(int id, String nome, double longitude, double latitude){
        this.id = id;
        this.nome = nome;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}
