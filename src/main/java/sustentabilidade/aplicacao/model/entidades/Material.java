package sustentabilidade.aplicacao.model.entidades;

public class Material {
    private String nome;

    public Material(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void apresentar(){
        System.out.println(this.nome);
    }

}
