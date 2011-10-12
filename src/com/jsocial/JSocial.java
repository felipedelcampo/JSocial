/**
 * @author felipe
 * Classe principal que controla o servidor
 */

package com.jsocial;

import java.io.IOException;

import com.jsocial.servidor.Servidor;

public class JSocial {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Porta de conexao
		Integer porta = 1234;
		System.out.println("Inciando servidor na porta "+porta.toString());
		Servidor servidor = new Servidor(porta);
		servidor.iniciar();

	}

}
