package br.com.fiap.main;

import br.com.fiap.model.dao.MecanicoDAO;
import br.com.fiap.model.dto.Mecanico;
import br.com.fiap.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.util.ArrayList;

public class TesteMecanicoCRUD {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();
        MecanicoDAO mecanicoDAO = new MecanicoDAO(con);

        // Teste de Inserção
        Mecanico mecanico = new Mecanico();
        mecanico.setIdMecanico(1);
        mecanico.setNome("João Silva");
        mecanico.setTelefone("1234567890");
        mecanico.setEmail("joao.silva@example.com");
        mecanico.setOficinaIdOficina(1);
        System.out.println(mecanicoDAO.inserir(mecanico));

        // Inserir outro mecânico
        mecanico.setIdMecanico(2);
        mecanico.setNome("Maria Oliveira");
        mecanico.setTelefone("0987654321");
        mecanico.setEmail("maria.oliveira@example.com");
        mecanico.setOficinaIdOficina(2);
        System.out.println(mecanicoDAO.inserir(mecanico));

        // Teste de Alteração
        mecanico.setIdMecanico(1);
        mecanico.setNome("João Santos");
        mecanico.setTelefone("1122334455");
        System.out.println(mecanicoDAO.alterar(mecanico));

        // Teste de Exclusão
        mecanico.setIdMecanico(1);
        System.out.println(mecanicoDAO.deletar(mecanico));

        // Listar todos os mecânicos
        ArrayList<Mecanico> resultado = mecanicoDAO.listarTodos();
        if (resultado != null) {
            for (Mecanico mec : resultado) {
                System.out.println("ID: " + mec.getIdMecanico());
                System.out.println("Nome: " + mec.getNome());
                System.out.println("Telefone: " + mec.getTelefone());
                System.out.println("Email: " + mec.getEmail());
                System.out.println("ID Oficina: " + mec.getOficinaIdOficina());
                System.out.println();
            }
        }

        ConnectionFactory.fecharConexao(con);
    }
}
