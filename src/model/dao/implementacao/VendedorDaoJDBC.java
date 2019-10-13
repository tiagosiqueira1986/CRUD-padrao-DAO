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
	
	//conex�o com o banco de dados
	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub
		
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
					"SELECT vendedor.*, "
					+ "departamento.nome "
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
			//fechar a conex�o somente no programa, pois ela pode ser usada em outros m�todos
		}
		
	}

	//m�todos que inst�ncia de vendedor e departamento
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
		dep.setNome(rs.getString("nome"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		return null;
	}

}
