package model.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {
	
	//conexão com o banco de dados
	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Vendedor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO vendedor "
					+ "(nome, email, dataNasc, salarioBase, departamentoId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS
					);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDataNasc().getTime()));
			st.setDouble(4, obj.getSalarioBase());
			st.setInt(5, obj.getDepartamento().getId());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
					DB.closeResultSet(rs);
				}else {
					throw new DbException("Erro inexperado! Nenhum registro foi inserido!");
				}
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*, departamento.depNome "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.departamentoId = departamento.id "
					+ "WHERE vendedor.id = ? ");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) { // testa se trouxe algum resultado na consulta
				Departamento dep = instanciaDepartamento(rs);
				Vendedor obj = instanciaVendedor(rs, dep);
				return obj;
			}
			return null;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//fechar a conexão somente no programa, pois ela pode ser usada em outros métodos
		}
		
	}

	//métodos que instância de vendedor e departamento
	private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor obj = new Vendedor();
		obj.setId(rs.getInt("id"));
		obj.setNome(rs.getString("nome"));
		obj.setEmail(rs.getString("email"));
		obj.setSalarioBase(rs.getDouble("salarioBase"));
		obj.setDataNasc(rs.getDate("dataNasc"));
		obj.setDepartamento(dep);
		return obj;
	}

	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("departamentoId"));
		dep.setNome(rs.getString("depNome"));
		return dep;
	}

	@Override
	public List<Vendedor> buscarTodosVendedores() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*, departamento.depNome "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.departamentoId = departamento.Id "
					+ "ORDER BY nome ");
			
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) { // testa se trouxe algum resultado na consulta
				
				Departamento dep = map.get(rs.getInt("departamentoId"));
				
				if(dep == null) {
					dep = instanciaDepartamento(rs);
					map.put(rs.getInt("departamentoId"), dep);
				}	

				Vendedor obj = instanciaVendedor(rs, dep);
				lista.add(obj);
			}
			return lista;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//fechar a conexão somente no programa, pois ela pode ser usada em outros métodos
		}	
	}

	@Override
	public List<Vendedor> buscarPorDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*, departamento.depNome "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.departamentoId = departamento.Id "
					+ "WHERE departamentoId = ? "
					+ "ORDER BY nome ");
			
			st.setInt(1, departamento.getId());
			
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) { // testa se trouxe algum resultado na consulta
				
				Departamento dep = map.get(rs.getInt("departamentoId"));
				
				if(dep == null) {
					dep = instanciaDepartamento(rs);
					map.put(rs.getInt("departamentoId"), dep);
				}	

				Vendedor obj = instanciaVendedor(rs, dep);
				lista.add(obj);
			}
			return lista;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//fechar a conexão somente no programa, pois ela pode ser usada em outros métodos
		}	
	}
}
