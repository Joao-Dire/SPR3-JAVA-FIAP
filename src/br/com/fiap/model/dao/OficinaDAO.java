package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Oficina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OficinaDAO {
    private Connection con;

    public OficinaDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Oficina oficina) {
        String sql = "INSERT INTO spr3_oficina (id_oficina, nome, telefone, rua, numero, bairro, cidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, oficina.getIdOficina());
            ps.setString(2, oficina.getNome());
            ps.setString(3, oficina.getTelefone());
            ps.setString(4, oficina.getRua());
            ps.setString(5, oficina.getNumero());
            ps.setString(6, oficina.getBairro());
            ps.setString(7, oficina.getCidade());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Oficina oficina) {
        String sql = "UPDATE spr3_oficina SET nome = ?, telefone = ?, rua = ?, numero = ?, bairro = ?, cidade = ? WHERE id_oficina = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, oficina.getNome());
            ps.setString(2, oficina.getTelefone());
            ps.setString(3, oficina.getRua());
            ps.setString(4, oficina.getNumero());
            ps.setString(5, oficina.getBairro());
            ps.setString(6, oficina.getCidade());
            ps.setInt(7, oficina.getIdOficina());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String deletar(Oficina oficina) {
        String sql = "DELETE FROM spr3_oficina WHERE id_oficina = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, oficina.getIdOficina());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso.";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Oficina> listarTodos() {
        String sql = "SELECT * FROM spr3_oficina";
        ArrayList<Oficina> listaOficina = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Oficina oficina = new Oficina();
                oficina.setIdOficina(rs.getInt("id_oficina"));
                oficina.setNome(rs.getString("nome"));
                oficina.setTelefone(rs.getString("telefone"));
                oficina.setRua(rs.getString("rua"));
                oficina.setNumero(rs.getString("numero"));
                oficina.setBairro(rs.getString("bairro"));
                oficina.setCidade(rs.getString("cidade"));
                listaOficina.add(oficina);
            }
            return listaOficina;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
