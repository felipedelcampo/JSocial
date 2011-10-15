package com.jsocial.cadastro;

import java.util.ArrayList;

import com.jsocial.estruturas.Post;
import com.jsocial.estruturas.Usuario;

public class CadastroPost {
	
	private ArrayList<Post> post;
	private Long indice;
	
	public CadastroPost() {
		
		this.post = new ArrayList<Post>();
		this.indice = new Long(0);
		
	}
	
	public synchronized Post cadastrarPost(String texto, Usuario usuario) {
		
		Post postAtual = new Post(texto, indice, usuario);
		this.post.add(postAtual);
		this.indice++;
		return postAtual;
		
	}
	
	public synchronized ArrayList<String> lerPosts(ArrayList<Post> posts) {
		
		ArrayList<String> textoPosts = new ArrayList<String>();
		for (Post postLista : posts) {
			textoPosts.add(postLista.getTexto());
		}
		return textoPosts;
		
	}

}
