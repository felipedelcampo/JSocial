package com.jsocial.servidor;

import java.util.ArrayList;
import java.util.Scanner;

import com.jsocial.cadastro.CadastroPost;
import com.jsocial.cadastro.CadastroTendencia;
import com.jsocial.cadastro.CadastroUsuario;
import com.jsocial.estruturas.Post;
import com.jsocial.estruturas.Usuario;

public class Comandos {

	private CadastroUsuario cadastroUsuario;
	private CadastroPost cadastroPost;
	private CadastroTendencia cadastroTendencia;

	public Comandos() {

		this.cadastroUsuario = new CadastroUsuario();
		this.cadastroPost = new CadastroPost();
		this.cadastroTendencia = new CadastroTendencia();

	}
	
	public synchronized void reinicia() {
		
		this.cadastroUsuario = new CadastroUsuario();
		this.cadastroPost = new CadastroPost();
		this.cadastroTendencia = new CadastroTendencia();
		
	}

	public ArrayList<String> cadastrarUsuario(Scanner comandoDividido,
			ArrayList<String> retorno) {

		retorno.add(this.cadastroUsuario.adicionaUsuario(comandoDividido.next()));
		return retorno;

	}

	public ArrayList<String> postarMensagem(Scanner comandoDividido,
			ArrayList<String> retorno) {
		Usuario usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			String textoPost = comandoDividido.nextLine().trim();
			if (!textoPost.isEmpty() && textoPost.length() <= 140) {
				Post post = this.cadastroPost.cadastrarPost(textoPost,usuario);
				this.cadastroTendencia.computaTendencia(post);
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
		Usuario usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			retorno = this.cadastroPost.lerPosts(usuario.getPosts());
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;
	}

	public ArrayList<String> seguir(Scanner comandoDividido,
			ArrayList<String> retorno) {
		Usuario usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		Usuario usuario2 = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			if (usuario2 != null) {
				if (!usuario.equals(usuario2)) {
					if (!usuario.verificaSeguindo(usuario2)) {
						usuario.seguir(usuario2);
						usuario2.teSeguindo(usuario);
						retorno.add("ok");
					} else {
						retorno.add("ja-seguindo");
					}
				} else {
					retorno.add("seguidor-e-seguido-sao-iguais");
				}
			} else {
				retorno.add("seguido-nao-encontrado");
			}
		} else {
			retorno.add("seguidor-nao-encontrado");

		}
		return retorno;

	}

	public ArrayList<String> listarSeguidores(Scanner comandoDividido,
			ArrayList<String> retorno) {

		Usuario usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			retorno = usuario.listarSeguidores();
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;

	}

	public ArrayList<String> listarSeguidos(Scanner comandoDividido,
			ArrayList<String> retorno) {

		Usuario usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			retorno = usuario.listarSeguidos();
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;

	}

	public ArrayList<String> deixarDeSeguir(Scanner comandoDividido,
			ArrayList<String> retorno) {

		Usuario usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		Usuario usuario2 = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			if (usuario2 != null) {
				if (!usuario.equals(usuario2)) {
					if (usuario.verificaSeguindo(usuario2)) {
						usuario.naoSeguir(usuario2);
						usuario2.naoTeSeguindo(usuario);
						retorno.add("ok");
					} else {
						retorno.add("nao-seguindo");
					}
				} else {
					retorno.add("seguidor-e-seguido-sao-iguais");
				}
			} else {
				retorno.add("seguido-nao-encontrado");
			}
		} else {
			retorno.add("seguidor-nao-encontrado");

		}
		return retorno;

	}

	public ArrayList<String> listarMensagensSeguidos(Scanner comandoDividido,
			ArrayList<String> retorno) {

		Usuario usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {
			for (Post postLista : usuario.getPostsSeguidos()) {
				retorno.add(postLista.getUsuario().getNome() + " "
						+ postLista.getTexto());
			}
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;
	}

	public ArrayList<String> listarEstatiscasUsuario(Scanner comandoDividido,
			ArrayList<String> retorno) {

		Usuario usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (usuario != null) {

			retorno.add(usuario.getNumeroPost().toString());
			retorno.add(usuario.getNumeroSeguidos().toString());
			retorno.add(usuario.getNumeroSerguidores().toString());

		} else {

			retorno.add("usuario-nao-encontrado");

		}
		return retorno;
	}
	
	public ArrayList<String> listarTendencia(Scanner comandoDividido, ArrayList<String> retorno) {
		
		return this.cadastroTendencia.listarTendencia();
		
	}
	
	public ArrayList<String> listarMensagensComPalavraMarcada(Scanner comandoDividido, ArrayList<String> retorno) {
		
		for (Post postLista : this.cadastroTendencia.retornaPostTendencia(comandoDividido.next())) {
			
			retorno.add(postLista.getUsuario().getNome() + " " + postLista.getTexto());
			
		}
		return retorno;
	}

}
