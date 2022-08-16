package com.mateus.agendacontato;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contato da agenda
 * 
 * @author Mateus Dantas
 */
public class Contato {

	private String nome;
	private String telefone;

	/**
	 * Construtor
	 * 
	 * @param nome     Nome do contato
	 * @param telefone Telefone do contato
	 */
	public Contato(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Valida os dados do contato
	 * 
	 * @throws AgendaException Lançada se ocorrer alguma falha de validação
	 */
	public void validarDados() throws AgendaException {
		validarNome();
		validarTelefone();
	}

	public void validarNome() throws AgendaException {
		if (nome == null | nome.isEmpty()) {
			throw new AgendaException("O nome do contato não pode ser vazio!");
		}
	}
	
	/**
	 * Valida o telefone. Ele deve estar no padrão '(XX) X XXXX-XXXX'
	 * @throws AgendaException Lançada se ocorrer alguma falha na validação
	 */
	public void validarTelefone() throws AgendaException {
		String regex = "\\(\\d\\d\\)\\s\\d\\s\\d\\d\\d\\d-\\d\\d\\d\\d";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(telefone);
		
		if (!m.matches()) {
			throw new AgendaException("O telefone " + telefone + " não é válido, por favor insira um número no padrão: (XX) X XXXX-XXXX!");
		}
	}
	
	@Override
	public String toString() {
		return nome + " -> " + telefone;
	}
}
