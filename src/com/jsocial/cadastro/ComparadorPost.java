package com.jsocial.cadastro;

import java.util.Comparator;

public class ComparadorPost implements Comparator<Post>{
	
	// Seta o indice como critério de ordenação dos posts
	public int compare(Post post1, Post post2) {
		return post1.getIndice() > post2.getIndice() ? -1 : (post1.getIndice() < post2.getIndice() ? +1 : 0);
	}

}
