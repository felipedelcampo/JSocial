package com.jsocial.servidor;

import java.util.ArrayList;
import java.util.Scanner;

import com.jsocial.cadastro.CadastroUsuario;
import com.jsocial.cadastro.Usuario;

public class Comandos {

	private CadastroUsuario cadastroUsuario;
	private Usuario usuario;

	public Comandos(){
		
		this.cadastroUsuario = new CadastroUsuario();

	}
	
	public ArrayList<String> cadastrarUsuario(Scanner comandoDividido,
			ArrayList<String> retorno) {

		retorno.add(cadastroUsuario.adicionaUsuario(comandoDividido.next()));
		return retorno;

	}

	public ArrayList<String> postarMensagem(Scanner comandoDividido,
			ArrayList<String> retorno) {
		this.usuario = cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			String post = comandoDividido.nextLine().trim();
			if (!post.isEmpty() && post.length() <= 140) {
				usuario.postar(post);
				retorno.add("ok");
			} else {
				retorno.add("mensagem-invalida");
			}
		} else {

			retorno.add("usuario-nao-encontrado");

		}
		return retorno;
	}

	public ArrayList<String> listarMensagensUsuario(Scanner comandoDividido,
			ArrayList<String> retorno) {
		this.usuario = cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			retorno = usuario.lerPosts();
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;
	}

}
