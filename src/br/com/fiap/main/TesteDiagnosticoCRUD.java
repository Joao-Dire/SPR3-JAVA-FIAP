package br.com.fiap.main;

import br.com.fiap.model.dao.DiagnosticoDAO;
import br.com.fiap.model.dto.Diagnostico;
import br.com.fiap.model.dao.ConnectionFactory;
import java.sql.Timestamp;
import java.sql.Connection;
import java.util.ArrayList;

public class TesteDiagnosticoCRUD {

    public static void main(String[] args) {
        Connection con = ConnectionFactory.abrirConexao();

        Diagnostico diagnostico = new Diagnostico();
        DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO(con);

        // Teste de Inserção
        diagnostico.setIdDiagnostico(1);
        diagnostico.setResultado("Em andamento");
        diagnostico.setDataHoraDiagnostico(new Timestamp(System.currentTimeMillis()));
        diagnostico.setDetalhes("Diagnóstico inicial");
        diagnostico.setVeiculoIdVeiculo(1);
        System.out.println(diagnosticoDAO.inserir(diagnostico));

        diagnostico.setIdDiagnostico(2);
        diagnostico.setDataHoraDiagnostico(new Timestamp(System.currentTimeMillis()));
        diagnostico.setResultado("Em andamento");
        diagnostico.setDataHoraDiagnostico(new Timestamp(System.currentTimeMillis()));
        diagnostico.setDetalhes("Diagnóstico inicial");
        diagnostico.setVeiculoIdVeiculo(2);
        System.out.println(diagnosticoDAO.inserir(diagnostico));

        // Teste de Alteração
        diagnostico.setIdDiagnostico(1);
        diagnostico.setResultado("Concluido");
        diagnostico.setDetalhes("Diagnóstico completo e detalhado");
        diagnostico.setDataHoraDiagnostico(new Timestamp(System.currentTimeMillis()));
        diagnostico.setVeiculoIdVeiculo(1);
        System.out.println(diagnosticoDAO.alterar(diagnostico));

        // Teste de Exclusão
        //diagnostico.setIdDiagnostico(2);
        //System.out.println(diagnosticoDAO.deletar(diagnostico));

        ArrayList<Diagnostico> resultado = diagnosticoDAO.listarTodos();
        if (resultado != null) {
            for (Diagnostico diag : resultado) {
                System.out.println("ID: " + diag.getIdDiagnostico());
                System.out.println("Data e Hora: " + diag.getDataHoraDiagnostico());
                System.out.println("Resultado: " + diag.getResultado());
                System.out.println("Detalhes: " + diag.getDetalhes());
                System.out.println("ID do Veículo: " + diag.getVeiculoIdVeiculo());
                System.out.println();
            }
        }



        ConnectionFactory.fecharConexao(con);
    }
}


