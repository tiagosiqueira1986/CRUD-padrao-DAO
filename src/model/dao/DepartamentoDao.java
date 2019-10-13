package model.dao;

import java.util.List;

import model.entities.Departamento;

public interface DepartamentoDao {
	
	void insert(Departamento dep);
	void update(Departamento dep);
	void deleteById(Integer dep);
	void findById(Integer dep);
	List<Departamento> findAll();
	
}
