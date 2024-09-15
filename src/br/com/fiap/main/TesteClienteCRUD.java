package br.com.fiap.main;

import br.com.fiap.model.dao.ClienteDAO;
import br.com.fiap.model.dto.Cliente;
import br.com.fiap.model.dao.ConnectionFactory;

import java.sql.Connection;
import java.util.ArrayList;

class TesteClienteCRUD {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();


        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO(con);

        //teste de inserção
        cliente.setIdCliente(1);
        cliente.setNomeCliente("Joao");
        cliente.setEmail("joao@gmail.com");
        cliente.setTelefone("9999999999");
        cliente.setSenha("senha123");
        System.out.println(clienteDAO.inserir(cliente));


        cliente.setIdCliente(2);
        cliente.setNomeCliente("Carol");
        cliente.setEmail("Carol@gmail.com");
        cliente.setTelefone("11111111111");
        cliente.setSenha("senha1234");
        System.out.println(clienteDAO.inserir(cliente));

        cliente.setIdCliente(3);
        cliente.setNomeCliente("Luan");
        cliente.setEmail("luan@gmail.com");
        cliente.setTelefone("8888888888");
        cliente.setSenha("senha12345");
        System.out.println(clienteDAO.inserir(cliente));


        //teste de alteração
        cliente.setIdCliente(1);
        cliente.setNomeCliente("Joao");
        cliente.setEmail("joaovitor@gmail.com");
        cliente.setTelefone("9999999999");
        cliente.setSenha("senha123");
        System.out.println(clienteDAO.alterar(cliente));


        //teste de exclusão
        cliente.setIdCliente(2);
        System.out.println(clienteDAO.deletar(cliente));


        ArrayList<Cliente> resultado = clienteDAO.listarTodos();
        if (resultado != null) {
            for (Cliente cli : resultado) {
                System.out.println("ID: " + cli.getIdCliente());
                System.out.println("Nome: " + cli.getNomeCliente());
                System.out.println("Email: " + cli.getEmail());
                System.out.println("Telefone: " + cli.getTelefone());
                System.out.println();
            }
        }

        ConnectionFactory.fecharConexao(con);
    }
}
