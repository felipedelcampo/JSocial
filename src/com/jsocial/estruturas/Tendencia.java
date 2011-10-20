/**
 * @author felipe
 * Classe para criação de tendências
 */

package com.jsocial.estruturas;

import java.util.ArrayList;

public class Tendencia {

	private String hashTag;
	private Integer numeroDeOcorrencia;
	private ArrayList<Post> listaPost;

	public String getHashTag() {
		return hashTag;
	}

	public Integer getNumeroDeOcorrencias() {
		return numeroDeOcorrencia;
	}

	public ArrayList<Post> getListaPost() {
		return listaPost;
	}

	public void aumentaOcorrencia(Post post) {

		this.numeroDeOcorrencia++;
		this.listaPost.add(post);

	}

	public Tendencia(String hashTag, Post post) {

		this.hashTag = hashTag;
		this.numeroDeOcorrencia = 1;
		this.listaPost = new ArrayList<Post>();
		this.listaPost.add(post);

	}

}
