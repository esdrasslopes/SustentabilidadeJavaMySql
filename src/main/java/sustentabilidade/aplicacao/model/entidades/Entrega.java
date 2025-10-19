package sustentabilidade.aplicacao.model.entidades;

import java.time.LocalDateTime;

public class Entrega {
    private int id;
    private String status;
    private int quantidadeItens;
    private LocalDateTime dataEntrega;
    private int usuarioId;

    public Entrega(int id, String status, int quantidadeItens, LocalDateTime dataEntrega, int usuarioId) {
        this.id = id;
        this.status = status;
        this.quantidadeItens = quantidadeItens;
        this.dataEntrega = dataEntrega;
        this.usuarioId = usuarioId;
    }

    public String getStatus() {
        return status;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getId(){
        return id;
    }

    public void apresentar(){
        System.out.println("Status: " + this.status);
        System.out.println("Quantidade de itens: " + this.quantidadeItens);
        System.out.println("Data da entrega: " + this.dataEntrega);
    }
}

