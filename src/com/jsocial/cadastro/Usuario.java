package com.jsocial.cadastro;

import java.util.ArrayList;
import java.util.Collections;

public class Usuario {
	
	private String nome;
	private ArrayList<String> posts = new ArrayList<String>();

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}
	
	public void postar(String post) {
		
		this.posts.add(post);
		
	}
	
	public ArrayList<String> lerPosts() {
		
		ArrayList<String> postsReverso = new ArrayList<String>(this.posts);
		Collections.reverse(postsReverso);
		return postsReverso;
		
	}
	
	public Usuario(String nome) {
		
		this.setNome(nome);
		
	}
	
}
