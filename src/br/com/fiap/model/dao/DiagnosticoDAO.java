package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Diagnostico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiagnosticoDAO {
    private Connection con;

    public DiagnosticoDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Diagnostico diagnostico) {
        String sql = "INSERT INTO spr3_diagnostico (id_diagnostico, data_hora_diagnostico, resultado, detalhes, veiculo_id_veiculo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, diagnostico.getIdDiagnostico());  // Use o nome correto
            ps.setTimestamp(2, diagnostico.getDataHoraDiagnostico());  // Supondo que você esteja usando Timestamp
            ps.setString(3, diagnostico.getResultado());
            ps.setString(4, diagnostico.getDetalhes());
            ps.setInt(5, diagnostico.getVeiculoIdVeiculo());  // Relacionado à tabela veiculo
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Diagnostico diagnostico) {
        String sql = "UPDATE spr3_diagnostico SET data_hora_diagnostico = ?, resultado = ?, detalhes = ?, veiculo_id_veiculo = ? WHERE id_diagnostico = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setTimestamp(1, diagnostico.getDataHoraDiagnostico());  // Supondo que você esteja usando Timestamp
            ps.setString(2, diagnostico.getResultado());
            ps.setString(3, diagnostico.getDetalhes());
            ps.setInt(4, diagnostico.getVeiculoIdVeiculo());  // Relacionado à tabela veiculo
            ps.setInt(5, diagnostico.getIdDiagnostico());  // Use o nome correto
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String deletar(Diagnostico diagnostico) {
        String sql = "DELETE FROM spr3_diagnostico WHERE id_diagnostico = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, diagnostico.getIdDiagnostico());  // Use o nome correto
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso.";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Diagnostico> listarTodos() {
        String sql = "SELECT * FROM spr3_diagnostico";
        ArrayList<Diagnostico> listaDiagnostico = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setIdDiagnostico(rs.getInt("id_diagnostico"));
                diagnostico.setDataHoraDiagnostico(rs.getTimestamp("data_hora_diagnostico"));  // Supondo que você esteja usando Timestamp
                diagnostico.setResultado(rs.getString("resultado"));
                diagnostico.setDetalhes(rs.getString("detalhes"));
                diagnostico.setVeiculoIdVeiculo(rs.getInt("veiculo_id_veiculo"));  // Relacionado à tabela veiculo
                listaDiagnostico.add(diagnostico);
            }
            return listaDiagnostico;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
