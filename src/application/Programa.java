package application;

import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.criaVendedorDao();
		
		System.out.println("=== Teste 1: buscar Vendedor por Id===");
		Vendedor vendedor = vendedorDao.buscarPorId(3);		
		System.out.println(vendedor);
		
		System.out.println("=== Teste 2: buscar Vendedor por Departamento===");
		Departamento departamento = new Departamento(2, null);		
		List<Vendedor> lista = vendedorDao.buscarPorDepartamento(departamento);
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
		
		System.out.println("=== Teste 3: buscar Vendedor===");		
		lista = vendedorDao.buscarTodosVendedores();
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
	}

}
