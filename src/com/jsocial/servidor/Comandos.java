package com.jsocial.servidor;

import java.util.ArrayList;
import java.util.Scanner;

import com.jsocial.cadastro.CadastroPost;
import com.jsocial.cadastro.CadastroUsuario;
import com.jsocial.estruturas.Post;
import com.jsocial.estruturas.Usuario;
import com.jsocial.estruturas.UsuarioPost;

public class Comandos {

	private CadastroUsuario cadastroUsuario;
	private Usuario usuario, usuario2;
	private CadastroPost cadastroPost;

	public Comandos() {

		this.cadastroUsuario = new CadastroUsuario();
		this.cadastroPost = new CadastroPost();

	}

	public ArrayList<String> cadastrarUsuario(Scanner comandoDividido,
			ArrayList<String> retorno) {

		retorno.add(this.cadastroUsuario.adicionaUsuario(comandoDividido.next()));
		return retorno;

	}

	public ArrayList<String> postarMensagem(Scanner comandoDividido,
			ArrayList<String> retorno) {
		this.usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (this.usuario != null) {
			String textoPost = comandoDividido.nextLine().trim();
			if (!textoPost.isEmpty() && textoPost.length() <= 140) {
				Post post = this.cadastroPost.cadastrarPost(textoPost);
				this.usuario.postar(post);
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
		this.usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (this.usuario != null) {
			retorno = this.cadastroPost.lerPosts(this.usuario.getPosts());
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;
	}

	public ArrayList<String> seguir(Scanner comandoDividido,
			ArrayList<String> retorno) {
		this.usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		this.usuario2 = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (this.usuario != null) {
			if (this.usuario2 != null) {
				if (!this.usuario.equals(usuario2)) {
					if (!this.usuario.verificaSeguindo(usuario2)) {
						this.usuario.seguir(usuario2);
						this.usuario2.teSeguindo(usuario);
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

		this.usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (this.usuario != null) {
			retorno = this.usuario.listarSeguidores();
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;

	}

	public ArrayList<String> listarSeguidos(Scanner comandoDividido,
			ArrayList<String> retorno) {

		this.usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (this.usuario != null) {
			retorno = this.usuario.listarSeguidos();
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;

	}

	public ArrayList<String> deixarDeSeguir(Scanner comandoDividido,
			ArrayList<String> retorno) {

		this.usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		this.usuario2 = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (this.usuario != null) {
			if (this.usuario2 != null) {
				if (!this.usuario.equals(usuario2)) {
					if (this.usuario.verificaSeguindo(usuario2)) {
						this.usuario.naoSeguir(usuario2);
						this.usuario2.naoTeSeguindo(usuario);
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

		this.usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (this.usuario != null) {
			for (UsuarioPost usuarioPostLista : this.usuario.getPostsSeguidos()) {
				retorno.add(usuarioPostLista.getUsuario().getNome() + " "
						+ usuarioPostLista.getPost().getTexto());
			}
		} else {
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;
	}

	public ArrayList<String> listarEstatiscasUsuario(Scanner comandoDividido,
			ArrayList<String> retorno) {

		this.usuario = this.cadastroUsuario.cadastrado(comandoDividido.next());
		if (this.usuario != null) {

			retorno.add(this.usuario.getNumeroPost().toString());
			retorno.add(this.usuario.getNumeroSeguidos().toString());
			retorno.add(this.usuario.getNumeroSerguidores().toString());

		} else {

			retorno.add("usuario-nao-encontrado");

		}
		return retorno;
	}

}
