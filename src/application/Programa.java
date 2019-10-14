package application;


import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		VendedorDao vendedorDao = FabricaDao.criaVendedorDao();
		
		System.out.println("\n=== Teste 1: buscar Vendedor por Id===");
		Vendedor vendedor = vendedorDao.buscarPorId(3);		
		System.out.println(vendedor);
		
		System.out.println("\n=== Teste 2: buscar Vendedor por Departamento===");
		Departamento departamento = new Departamento(2, null);		
		List<Vendedor> lista = vendedorDao.buscarPorDepartamento(departamento);
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== Teste 3: buscar Vendedor===");		
		lista = vendedorDao.buscarTodosVendedores();
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== Teste 4: inserir Vendedor===");	
		Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departamento);
		vendedorDao.inserir(novoVendedor);
		System.out.println("Inserido! novo Id = " + novoVendedor.getId());
		
		System.out.println("\n=== Teste 5: atualizar Vendedor===");
		vendedor = vendedorDao.buscarPorId(1);
		vendedor.setNome("Martha Waine");
		vendedorDao.atualizar(vendedor);
		System.out.println("Atualização efetuada! ");
		
		System.out.println("\n=== Teste 6: deletar Vendedor===");
		System.out.println("Entre com o Id que será deletado: ");
		int id = sc.nextInt();
		vendedorDao.deletarPorId(id);
		System.out.println("Registro deletado! ");
	}

}
