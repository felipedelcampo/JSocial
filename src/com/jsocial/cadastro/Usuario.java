package com.jsocial.cadastro;

import java.util.ArrayList;
import java.util.Collections;

public class Usuario {

	private String nome;
	private ArrayList<Post> post = new ArrayList<Post>();
	private ArrayList<Usuario> seguidores = new ArrayList<Usuario>();
	private ArrayList<Usuario> seguidos = new ArrayList<Usuario>();

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	public void postar(Post post) {

		this.post.add(post);

	}

	public void seguir(Usuario usuario) {

		this.seguidos.add(usuario);

	}
	
	public void naoSeguir(Usuario usuario) {
		
		this.seguidos.remove(usuario);
		
	}

	public void teSeguindo(Usuario usuario) {

		this.seguidores.add(usuario);

	}
	
	public void naoTeSeguindo(Usuario usuario) {
		
		this.seguidores.remove(usuario);
		
	}

	public Boolean verificaSeguindo(Usuario usuario) {
		
		for (Usuario usuarioLista : this.seguidores) {
			if (usuario.equals(usuarioLista)) {
				return true;
			}
		}
		return false;
		
	}

	public ArrayList<String> listarSeguidores() {

		ArrayList<String> seguidoresReverso = new ArrayList<String>();
		for (Usuario usuarioLista : this.seguidores) {
			seguidoresReverso.add(usuarioLista.nome);
		}
		Collections.reverse(seguidoresReverso);
		return seguidoresReverso;

	}

	public ArrayList<String> listarSeguidos() {

		ArrayList<String> seguidosReverso = new ArrayList<String>();
		for (Usuario usuarioLista : this.seguidos) {
			seguidosReverso.add(usuarioLista.nome);
		}
		Collections.reverse(seguidosReverso);
		return seguidosReverso;

	}
	
	public ArrayList<Post> getPostsSeguidos() {
		
		ArrayList<Post> postsSeguidos = new ArrayList<Post>();
		for (Usuario usuarioLista : this.seguidos) {
			for (Post postUsuario : usuarioLista.getPosts()) {
				
				postsSeguidos.add(postUsuario);
				
			}
		}
		Collections.sort(postsSeguidos, new ComparadorPost());
		return postsSeguidos;
		
	}

	public ArrayList<Post> getPosts() {

		ArrayList<Post> postsReverso = new ArrayList<Post>(this.post);
		Collections.reverse(postsReverso);
		return postsReverso;

	}

	public Usuario(String nome) {

		this.setNome(nome);

	}

}
