package components;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public interface CRUD{

	Scanner teclado = new Scanner(System.in);

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void cadastrar() {}
	public static void listar() {};
	public static void atualizar() {};
	public static void deletar() {};

}
