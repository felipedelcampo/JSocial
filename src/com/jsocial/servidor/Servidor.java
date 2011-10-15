package com.jsocial.servidor;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {

	private Integer porta;
	
	public Servidor(Integer porta){
		
		this.porta = porta;
		
	}

	public void iniciar() throws IOException {
		ServerSocket socket = new ServerSocket(this.porta);
		Comandos comandos = new Comandos();
		
		try {
			while (true) {
				ProcessaComando processaComando = new ProcessaComando(socket.accept(), comandos);
				processaComando.start();
			}
		} finally {
			socket.close();
		}
	}
}
