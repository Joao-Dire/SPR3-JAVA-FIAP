package br.com.fiap.main;

import br.com.fiap.model.dao.OficinaDAO;
import br.com.fiap.model.dto.Oficina;
import br.com.fiap.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.util.ArrayList;

public class TesteOficinaCRUD {

    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();

        OficinaDAO oficinaDAO = new OficinaDAO(con);

        // Teste de Inserção
        Oficina oficina1 = new Oficina();
        oficina1.setIdOficina(1);
        oficina1.setNome("Oficina Central");
        oficina1.setTelefone("123456789");
        oficina1.setRua("Rua das Flores");
        oficina1.setNumero("123");
        oficina1.setBairro("Centro");
        oficina1.setCidade("Cidade A");
        System.out.println("Inserção Oficina 1: " + oficinaDAO.inserir(oficina1));

        Oficina oficina2 = new Oficina();
        oficina2.setIdOficina(2);
        oficina2.setNome("Oficina do Bairro");
        oficina2.setTelefone("987654321");
        oficina2.setRua("Rua do Sol");
        oficina2.setNumero("456");
        oficina2.setBairro("Bairro Velho");
        oficina2.setCidade("Cidade B");
        System.out.println("Inserção Oficina 2: " + oficinaDAO.inserir(oficina2));

        // Teste de Alteração
        oficina1.setNome("Oficina Central Atualizada");
        oficina1.setTelefone("123123123");
        oficina1.setRua("Rua das Flores Alterada");
        oficina1.setNumero("321");
        oficina1.setBairro("Centro Atualizado");
        oficina1.setCidade("Cidade A Atualizada");
        System.out.println("Alteração Oficina 1: " + oficinaDAO.alterar(oficina1));

        // Teste de Exclusão
        //System.out.println("Exclusão Oficina 2: " + oficinaDAO.deletar(oficina2));

        // Listar todas as oficinas para verificar o estado final
        ArrayList<Oficina> resultado = oficinaDAO.listarTodos();
        if (resultado != null) {
            System.out.println("Lista de Oficinas:");
            for (Oficina ofi : resultado) {
                System.out.println("ID: " + ofi.getIdOficina());
                System.out.println("Nome: " + ofi.getNome());
                System.out.println("Telefone: " + ofi.getTelefone());
                System.out.println("Rua: " + ofi.getRua());
                System.out.println("Número: " + ofi.getNumero());
                System.out.println("Bairro: " + ofi.getBairro());
                System.out.println("Cidade: " + ofi.getCidade());
                System.out.println();
            }
        }

        ConnectionFactory.fecharConexao(con);
    }
}
