package application;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.criaVendedorDao();
		
		System.out.println("=== Teste 1: buscar Vendedor por Id");
		Vendedor vendedor = vendedorDao.buscarPorId(3);
				
		System.out.println(vendedor);

	}

}
