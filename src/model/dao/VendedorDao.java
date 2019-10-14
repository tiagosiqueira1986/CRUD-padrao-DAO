package model.dao;

import java.util.List;

import model.entities.Departamento;
import model.entities.Vendedor;

public interface VendedorDao {
	
	void inserir(Vendedor obj);
	void atualizar(Vendedor obj);
	void deletarPorId(Integer id);
	List<Vendedor> buscarTodosVendedores();
	List<Vendedor> buscarPorDepartamento(Departamento departamento);
	Vendedor buscarPorId(Integer id);
	
}
