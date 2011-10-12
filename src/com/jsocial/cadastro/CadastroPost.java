package com.jsocial.cadastro;

import java.util.ArrayList;

public class CadastroPost {
	
	private ArrayList<Post> post;
	private Long indice;
	
	public CadastroPost() {
		
		this.post = new ArrayList<Post>();
		this.indice = new Long(0);
		
	}
	
	public Post cadastrarPost(String texto) {
		
		Post postAtual = new Post(texto, indice);
		this.post.add(postAtual);
		this.indice++;
		return postAtual;
		
	}
	
	public ArrayList<String> lerPosts(ArrayList<Post> posts) {
		
		ArrayList<String> textoPosts = new ArrayList<String>();
		for (Post postLista : posts) {
			textoPosts.add(postLista.getTexto());
		}
		return textoPosts;
		
	}

}
