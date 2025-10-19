package sustentabilidade.aplicacao.view;

import sustentabilidade.aplicacao.model.DB.ConexaoMySql;
import sustentabilidade.aplicacao.model.entidades.Usuario;
import sustentabilidade.aplicacao.model.repositorios.EmpresasRepositorio;
import sustentabilidade.aplicacao.model.repositorios.EntregasRepositorio;
import sustentabilidade.aplicacao.model.repositorios.PontoColetaRepositorio;
import sustentabilidade.aplicacao.model.repositorios.UsuariosRepositorio;
import sustentabilidade.aplicacao.service.EmpresaService;
import sustentabilidade.aplicacao.service.EntregaService;
import sustentabilidade.aplicacao.service.PontoColetaService;
import sustentabilidade.aplicacao.service.UsuarioService;

import java.util.Scanner;

public class Sustentabilidade {
    public static void main(String[] args){
        ConexaoMySql conexao = new ConexaoMySql();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual o seu email?");
        String email = scanner.next();

        UsuariosRepositorio usuariosRepositorio = new UsuariosRepositorio(conexao);

        UsuarioService usuarioService = new UsuarioService(usuariosRepositorio);

        Usuario u = usuarioService.buscarUsuario(email);

        if (u.getNome() != null && !u.getNome().isEmpty()) {
            System.out.println("Bem-vindo " + u.getNome());

            boolean controle = true;

            while(controle){
                System.out.println("1 - Listar empresas");
                System.out.println("2 - Listar minhas entregas");
                System.out.println("3 - Pontos de coleta próximos");
                System.out.println("4 - Sair");

                int opcao = scanner.nextInt();
                switch (opcao){
                    case 1:
                        EmpresasRepositorio empresasRepositorio = new EmpresasRepositorio(conexao);
                        EmpresaService empresaService = new EmpresaService(empresasRepositorio);
                        empresaService.buscarEmpresas();
                        break;
                    case 2:
                        EntregasRepositorio entregasRepositorio = new EntregasRepositorio(conexao);
                        EntregaService entregaService = new EntregaService(entregasRepositorio);
                        entregaService.buscarEntregas(u.getId());
                        break;
                    case 3:
                        PontoColetaRepositorio pontoColetaRepositorio = new PontoColetaRepositorio(conexao);
                        PontoColetaService pontoColetaService = new PontoColetaService(pontoColetaRepositorio);
                        pontoColetaService.buscarPontos(u.getLatitude(), u.getLongitude());
                        break;
                    case 4:
                        controle = false;
                }
            }

        }

    }
}
