package com.mateus.agendacontato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe utilizada para ler dados do console
 * @author Mateus Dantas
 */
public class Console {
	
	/**
	 * Lê uma string do console
	 * @return String lida
	 */
	public static String readString() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();			
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o dado do teclado!");
		}
	}
	
	/**
	 * Lê um caractere do console
	 * @return String lida
	 */
	public static char readChar() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine().charAt(0);			
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o dado do teclado!");
		}
	}

	public static int readInt() {
		String str = readString();
		
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			throw new RuntimeException(str + " Não é um inteiro válido!");
		}
	}
}
