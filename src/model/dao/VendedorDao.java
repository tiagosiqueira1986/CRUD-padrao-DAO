package model.dao;

import java.util.List;

import model.entities.Vendedor;

public interface VendedorDao {
	
	void insert(Vendedor dep);
	void update(Vendedor dep);
	void deleteById(Integer dep);
	void findById(Integer dep);
	List<Vendedor> findAll();
	
}
