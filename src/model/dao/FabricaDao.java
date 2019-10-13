package model.dao;

import model.dao.implementacao.VendedorDaoJDBC;
/*
 * Serve para no momento da instânciação o objeto não acessar a implementação 
 * e sim a Interface
 */
public class FabricaDao {
	
	public static VendedorDao criaVendedorDao() {
		return new VendedorDaoJDBC();
	}
}
