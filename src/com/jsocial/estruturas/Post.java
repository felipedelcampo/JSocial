package com.jsocial.estruturas;

public class Post {

	private String texto;
	private Long indice;

	public String getTexto() {
		return texto;
	}

	public Long getIndice() {
		return indice;
	}

	public Post(String texto, Long indice) {

		this.texto = texto;
		this.indice = indice;

	}

}
