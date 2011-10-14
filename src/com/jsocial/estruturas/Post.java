package com.jsocial.estruturas;

public class Post {

	private String texto;
	private Long indice;
	private Usuario usuario;

	public String getTexto() {
		return texto;
	}

	public Long getIndice() {
		return indice;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Post(String texto, Long indice, Usuario usuario) {

		this.texto = texto;
		this.indice = indice;
		this.usuario = usuario;
		this.usuario.postar(this);

	}

}
