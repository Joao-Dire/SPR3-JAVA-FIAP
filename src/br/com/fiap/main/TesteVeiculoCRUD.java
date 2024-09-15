package br.com.fiap.main;

import br.com.fiap.model.dao.CarroDAO;
import br.com.fiap.model.dto.Carro;
import br.com.fiap.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.util.ArrayList;

public class TesteVeiculoCRUD {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();

        Carro carro = new Carro();
        CarroDAO carroDAO = new CarroDAO(con);

        carro.setIdCarro(1);
        carro.setIdCarro(2);

        // Teste de Inserção
        carro.setIdCarro(1);
        carro.setPlaca("ABC1234");
        carro.setModelo("Civic");
        carro.setAno(2020);
        carro.setCor("Preto");
        carro.setIdCliente(1);
        System.out.println(carroDAO.inserir(carro));

        // Inserir outro carro
        carro.setIdCarro(2);
        carro.setPlaca("XYZ5678");
        carro.setModelo("Fusca");
        carro.setAno(1965);
        carro.setCor("Vermelho");
        carro.setIdCliente(3);
        System.out.println(carroDAO.inserir(carro));

        // Teste de Alteração
        carro.setIdCarro(1);
        carro.setPlaca("DEF5678");
        carro.setModelo("Ferrari");
        carro.setCor("Amarelo");
        carro.setAno(2021);
        carro.setIdCliente(1);
        System.out.println(carroDAO.alterar(carro));

        // Teste de Exclusão
        //carro.setIdCarro(2);
        //System.out.println(carroDAO.deletar(carro));

        ArrayList<Carro> resultado = carroDAO.listarTodos();
        if (resultado != null) {
            for (Carro carro1 : resultado) {
                System.out.println("ID: " + carro1.getIdCarro());
                System.out.println("Placa: " + carro1.getPlaca());
                System.out.println("Modelo: " + carro1.getModelo());
                System.out.println("Ano: " + carro1.getAno());
                System.out.println("Cor: " + carro1.getCor());
                System.out.println("ID Cliente: " + carro1.getIdCliente());
                System.out.println();
            }
        }

        ConnectionFactory.fecharConexao(con);
    }
}
