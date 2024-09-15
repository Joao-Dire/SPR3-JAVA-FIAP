package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO {
    private Connection con;

    public CarroDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Carro veiculo) {
        String sql = "INSERT INTO spr3_veiculo (id_veiculo, placa, modelo, ano, cor, cliente_id_cliente) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, veiculo.getIdCarro());
            ps.setString(2, veiculo.getPlaca());
            ps.setString(3, veiculo.getModelo());
            ps.setInt(4, veiculo.getAno());
            ps.setString(5, veiculo.getCor());
            ps.setInt(6, veiculo.getIdCliente()); // Adicionar ID cliente
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Carro veiculo) {
        String sql = "UPDATE spr3_veiculo SET placa = ?, modelo = ?, ano = ?, cor = ?, cliente_id_cliente = ? WHERE id_veiculo = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, veiculo.getModelo());
            ps.setInt(3, veiculo.getAno());
            ps.setString(4, veiculo.getCor());
            ps.setInt(5, veiculo.getIdCliente()); // Adicionar ID cliente
            ps.setInt(6, veiculo.getIdCarro()); // ID do veículo para atualização
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }


    public String deletar(Carro veiculo) {
        String sql = "DELETE FROM spr3_veiculo WHERE id_veiculo = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, veiculo.getIdCarro());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso.";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Carro> listarTodos() {
        String sql = "SELECT * FROM spr3_veiculo";
        ArrayList<Carro> listaVeiculo = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Carro veiculo = new Carro();
                veiculo.setIdCarro(rs.getInt("id_veiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setIdCliente(rs.getInt("cliente_id_cliente")); // Adicionar ID cliente
                listaVeiculo.add(veiculo);
            }
            return listaVeiculo;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
