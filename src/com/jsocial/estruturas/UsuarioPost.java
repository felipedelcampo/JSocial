package com.jsocial.estruturas;

public class UsuarioPost {

	private Usuario usuario;
	private Post post;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public UsuarioPost(Usuario usuario, Post post) {
		
		this.setPost(post);
		this.setUsuario(usuario);
	}

}
