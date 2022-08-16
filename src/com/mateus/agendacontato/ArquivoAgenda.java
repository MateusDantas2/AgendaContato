package com.mateus.agendacontato;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que manipula o arquivo onde os registros da agenda são armazenados
 * 
 * @author Mateus Dantas
 */
public class ArquivoAgenda {

	private static final String ARQUIVO_AGENDA = "agenda.txt";

	/**
	 * Gravar os contatos no arquivo.
	 * 
	 * @param contatos a serem gravados.
	 * @throws IOException
	 */
	public void gravar(Collection<Contato> contatos) throws IOException {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(ARQUIVO_AGENDA);

			// Escreve cada contato em uma linha do arquivo no padrão '<nome> , <telefone>'
			for (Contato contato : contatos) {
				writer.print(contato.getNome());
				writer.print(" ,");
				writer.println(contato.getTelefone());
			}
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * Lê os contatos do arquivo.
	 * 
	 * @return Lista de contatos lidos.
	 */
	public List<Contato> ler() {

		List<Contato> contatos = new ArrayList<Contato>();

		Scanner scanner = null;

		try {
			// Utiliza um scanner para ler os dados do arquivo
			scanner = new Scanner(new File(ARQUIVO_AGENDA));
			while (scanner.hasNextLine()) {
				String contatoStr = scanner.nextLine();

				// Divide cada linha do arquivo em tokens, onde o caractere ',' é o delimitador
				String[] tokens = contatoStr.split(" ,");

				Contato contato = new Contato(tokens[0], tokens[1]);

				contatos.add(contato);
			}

			return contatos;
		} catch (FileNotFoundException e) {
			// Se o arquivo de contatos não for encontrado, retorna a lista vazia
			return contatos;

		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}
