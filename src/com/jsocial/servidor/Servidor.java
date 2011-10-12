package com.jsocial.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {

	private Comandos comandos;
	private Integer porta;
	
	public Servidor(Integer porta){
		
		this.porta = porta;
		
	}

	public void iniciar() throws IOException {
		ServerSocket socket = new ServerSocket(this.porta);
		comandos = new Comandos();

		try {
			while (true) {
				atenderCliente(socket.accept());
			}
		} finally {
			socket.close();
		}
	}

	private void atenderCliente(final Socket cliente) throws IOException {
		// A ideia basica para atender um cliente é
		// - ler comando
		// - processar comando
		// - escrever resposta
		// Você deve fazer o controle da concorrência, pois vários
		// clientes podem ser atendindos concorrentemente
		// Você pode criar uma thread para atender cada cliente
		String comandoCompleto = readLine(cliente.getInputStream());
		Scanner comandoDividido = new Scanner(comandoCompleto);
		comandoDividido.useDelimiter(" ");
		String comando = comandoDividido.next();
		ArrayList<String> retorno = new ArrayList<String>();
		// Verifica e executa comando resetar
		if (comando.contentEquals("resetar")) {

			comandos = new Comandos();

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
		// Verifica e executa comando listar-mensagem-usuario
		else if (comando.contentEquals("listar-mensagens-usuario")) {

			retorno = comandos.listarMensagensUsuario(comandoDividido, retorno);

		}
		// Realiza os retornos para o servidor
		for (String linhaRetorno : retorno) {
			writeLine(cliente.getOutputStream(), linhaRetorno);
		}
		cliente.close();
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
