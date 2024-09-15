package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    private Connection con;

    public ClienteDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Cliente cliente) {
        String sql = "INSERT INTO spr3_CLIENTE (id_cliente, nome, email, telefone, senha) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNomeCliente());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getSenha());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }


    public String alterar(Cliente cliente) {
        String sql = "UPDATE spr3_cliente SET nome = ?, email = ?, telefone = ?, senha = ? WHERE id_cliente = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getSenha());
            ps.setInt(5, cliente.getIdCliente());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }



    public String deletar(Cliente cliente) {
        String sql = "DELETE FROM spr3_cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getIdCliente());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Cliente excluído com sucesso.";
            } else {
                return "Nenhum cliente encontrado com o ID fornecido.";
            }
        } catch (SQLException e) {
            return "Erro ao excluir cliente: " + e.getMessage();
        }
    }


    public ArrayList<Cliente> listarTodos() {
        String sql = "SELECT id_cliente, nome, email, telefone, senha FROM spr3_cliente";
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNomeCliente(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setSenha(rs.getString("senha"));

                    listaCliente.add(cliente);
                }
                return listaCliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

}
