package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Mecanico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MecanicoDAO {
    private Connection con;

    public MecanicoDAO(Connection con) {
        this.con = con;
    }

    public String inserir(Mecanico mecanico) {
        String sql = "INSERT INTO spr3_mecanico (id_mecanico, nome, telefone, email, oficina_id_oficina) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mecanico.getIdMecanico());
            ps.setString(2, mecanico.getNome());
            ps.setString(3, mecanico.getTelefone());
            ps.setString(4, mecanico.getEmail());
            ps.setInt(5, mecanico.getOficinaIdOficina());
            return ps.executeUpdate() > 0 ? "Inserido com sucesso." : "Erro ao inserir";
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Mecanico mecanico) {
        String sql = "UPDATE spr3_mecanico SET nome = ?, telefone = ?, email = ?, oficina_id_oficina = ? WHERE id_mecanico = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mecanico.getNome());
            ps.setString(2, mecanico.getTelefone());
            ps.setString(3, mecanico.getEmail());
            ps.setInt(4, mecanico.getOficinaIdOficina());
            ps.setInt(5, mecanico.getIdMecanico());
            return ps.executeUpdate() > 0 ? "Alterado com sucesso." : "Erro ao alterar";
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String deletar(Mecanico mecanico) {
        String sql = "DELETE FROM spr3_mecanico WHERE id_mecanico = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mecanico.getIdMecanico());
            return ps.executeUpdate() > 0 ? "Excluído com sucesso." : "Erro ao excluir";
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Mecanico> listarTodos() {
        String sql = "SELECT * FROM spr3_mecanico";
        ArrayList<Mecanico> listaMecanico = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Mecanico mecanico = new Mecanico();
                mecanico.setIdMecanico(rs.getInt("id_mecanico"));
                mecanico.setNome(rs.getString("nome"));
                mecanico.setTelefone(rs.getString("telefone"));
                mecanico.setEmail(rs.getString("email"));
                mecanico.setOficinaIdOficina(rs.getInt("oficina_id_oficina"));
                listaMecanico.add(mecanico);
            }
            return listaMecanico;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
