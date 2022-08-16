package com.mateus.agendacontato;

import java.io.IOException;
import java.util.List;

public class Aplicacao {

	private Menu menu;

	private Agenda agenda;

	public void iniciar() throws IOException {
		menu = new Menu();
		agenda = new Agenda();

		int opcao = 0;
		// Fica em loop enquanto o usuário não escolher a opção 'Sair'
		while (opcao != Menu.OPCAO_SAIR) {
			try {
				// Exibe o menu e aguardar uma escolha do usuário
				opcao = menu.exibirOpcoes();
				switch (opcao) {
				case Menu.OPCAO_INSERIR:
					inserir();
					break;
				case Menu.OPCAO_ALTERAR:
					alterar();
					break;
				case Menu.OPCAO_EXCLUIR:
					excluir();
					break;
				case Menu.OPCAO_LISTAR_LETRA:
					listarPorLetra();
					break;
				case Menu.OPCAO_PROCURAR:
					procurar();
					break;
				}
			} catch (AgendaException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println();
			}
		}

		System.out.println("Fim da Aplicação!");
	}

	/**
	 * Opção de Inserção
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	public void inserir() throws AgendaException, IOException {
		// Solicita os dados
		System.out.print("Nome: ");
		String nome = Console.readString();
		System.out.print("Telefone: ");
		String telefone = Console.readString();

		// Cria o contato e insere
		Contato contato = new Contato(nome, telefone);
		agenda.inserir(contato);

		System.out.println("Contato Inserido com Sucesso!");
		System.out.println();
	}

	/**
	 * Opção de Alteração
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	public void alterar() throws AgendaException, IOException {
		// Solicita os dados
		System.out.print("Nome: ");
		String nome = Console.readString();

		// Cria o Contato e altera
		Contato contato = agenda.obterContato(nome);

		if (contato == null) {
			throw new AgendaException("O contato " + nome + " não existe");
		}

		System.out.print("Telefone: ");
		String telefone = Console.readString();

		contato.setTelefone(telefone);
		agenda.alterar(contato);

		System.out.println("Contato Alterado com Sucesso!");
		System.out.println();
	}

	/**
	 * Opção de Exclusão
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	public void excluir() throws AgendaException, IOException {
		// Solicita o nome
		System.out.print("Nome: ");
		String nome = Console.readString();
		
		// Exclui o contato
		agenda.excluir(nome);
		
		System.out.println("Contato Excluído com Sucesso!");
		System.out.println();
	}

	/**
	 * Opção de Listagem por Letra
	 * 
	 * @throws AgendaException
	 */
	public void listarPorLetra() throws AgendaException {
		// Solicita a letra
		System.out.print("Letra: ");
		char letra = Console.readChar();
		
		// Obtém os contatos com base na letra
		List<Contato> contatos = agenda.listarContatosPorLetra(letra);
		
		System.out.println("Contatos com a letra '" + Character.toUpperCase(letra) + "':");
		
		// Exibte os contatos
		if (contatos.isEmpty()) {
			System.out.println("Nenhum contato foi encontrado!");
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}
		System.out.println();
	}

	/**
	 * Opção de Procura
	 * 
	 * @throws AgendaException
	 */
	private void procurar() throws AgendaException {
		// Solicita a parte do nome para a busca
		System.out.print("Parte do nome: ");
		String parteNome = Console.readString();
		
		// Obtém os contatos com base na parte do nome
		List<Contato> contatos = agenda.listarContatosPorParteNome(parteNome);
		
		System.out.println("Contatos encontrados com a parte do nome '" + parteNome + "':");
		
		// Exibe os contatos
		if (contatos.isEmpty()) {
			System.out.println("Nenhum contato foi encontrado!");
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}
		System.out.println();
	}
}
