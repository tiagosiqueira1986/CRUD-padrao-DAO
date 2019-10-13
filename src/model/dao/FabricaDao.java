package model.dao;

import model.dao.implementacao.VendedorDaoJDBC;
/*
 * Serve para no momento da inst�ncia��o o objeto n�o acessar a implementa��o 
 * e sim a Interface
 */
public class FabricaDao {
	
	public static VendedorDao criaVendedorDao() {
		return new VendedorDaoJDBC();
	}
}
