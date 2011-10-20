/**
 * @author felipe
 * Classe que recebe os comandos e os processa
 */

package com.jsocial.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessaComando extends Thread {
	
	private Socket cliente;
	private Comandos comandos;
	
	public ProcessaComando(final Socket cliente, Comandos comandos) {
		
		this.cliente = cliente;
		this.comandos = comandos;
		
	}
	//Método da Thread
	public void run() {
		
		try {
			this.atenderCliente();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
	
	private void atenderCliente() throws IOException {
		// A ideia basica para atender um cliente é
		// - ler comando
		// - processar comando
		// - escrever resposta
		// Você deve fazer o controle da concorrência, pois vários
		// clientes podem ser atendindos concorrentemente
		// Você pode criar uma thread para atender cada cliente
		String comandoCompleto = readLine(this.cliente.getInputStream());
		Scanner comandoDividido = new Scanner(comandoCompleto);
		comandoDividido.useDelimiter(" ");
		String comando = comandoDividido.next();
		ArrayList<String> retorno = new ArrayList<String>();
		// Verifica e executa comando resetar
		if (comando.contentEquals("resetar")) {
			
			comandos.reinicia();


		}
		// Verifica e executa comando criar-usuario
		else if (comando.contentEquals("criar-usuario")) {

			// Retorna para o cliente o resultado do adicionaUsuario
			retorno = comandos.cadastrarUsuario(comandoDividido, retorno);

		}
		// Verifica e executa comando postar-mensagem
		else if (comando.contentEquals("postar-mensagem")) {

			retorno = comandos.postarMensagem(comandoDividido, retorno);

		}
		// Verifica e executa comando listar-mensagens-usuario
		else if (comando.contentEquals("listar-mensagens-usuario")) {

			retorno = comandos.listarMensagensUsuario(comandoDividido, retorno);

		}
		// Verifica e executa comando seguir
		else if (comando.contentEquals("seguir")) {
			
			retorno = comandos.seguir(comandoDividido, retorno);
			
		}
		// Verifica e executa comando listar-seguidos
		else if (comando.contentEquals("listar-seguidos")) {
			
			retorno = comandos.listarSeguidos(comandoDividido, retorno);
			
		}
		// Verifica e executa comando listar-seguidores
		else if (comando.contentEquals("listar-seguidores")) {
			
			retorno = comandos.listarSeguidores(comandoDividido, retorno);
			
		}
		//verifica e executa comando deixar-de-seguir
		else if (comando.contentEquals("deixar-de-seguir")) {
			
			retorno = comandos.deixarDeSeguir(comandoDividido, retorno);
			
		}
		//verifica e executa comando listar-mensagens-seguidos
		else if (comando.contentEquals("listar-mensagens-seguidos")) {
			
			retorno = comandos.listarMensagensSeguidos(comandoDividido, retorno);
			
		}
		// verifica e executa comando listar-estatisticas-usuario
		else if (comando.contentEquals("listar-estatisticas-usuario")) {
			
			retorno = comandos.listarEstatiscasUsuario(comandoDividido, retorno);
			
		}
		// Verifica e executa comando listar-tendencia
		else if (comando.contentEquals("listar-tendencia")) {
			
			retorno = comandos.listarTendencia(comandoDividido, retorno);
		}
		// Verifica e executa comando listar-mensagens-com-palavra-marcada
		else if (comando.contentEquals("listar-mensagens-com-palavra-marcada")) {
			
			retorno = comandos.listarMensagensComPalavraMarcada(comandoDividido, retorno);
			
		}
		// Realiza os retornos para o servidor
		for (String linhaRetorno : retorno) {
			writeLine(this.cliente.getOutputStream(), linhaRetorno);
		}
		this.cliente.close();
	}

	private static String readLine(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		return reader.readLine();
	}

	private static void writeLine(OutputStream out, String linhas)
			throws IOException {
		out.write(linhas.getBytes());
		out.write('\n');
	}


}
