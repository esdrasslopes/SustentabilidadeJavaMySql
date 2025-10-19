package sustentabilidade.aplicacao.model.entidades;

public class Empresa {
    private String cnpj;
    private String nome;
    private String email;

    public Empresa(String cnpj, String nome, String email){
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void apresentar(){
        System.out.println("Empresa: " + this.nome);
        System.out.println("CNPJ: " + this.cnpj);
        System.out.println("Email: " + this.email);
    }
}
