package com.jsocial.estruturas;

import java.util.Comparator;

public class ComparadorPost implements Comparator<UsuarioPost> {

	// Seta o indice como critério de ordenação dos posts
	public int compare(UsuarioPost post1, UsuarioPost post2) {
		return post1.getPost().getIndice() > post2.getPost().getIndice() ? -1
				: (post1.getPost().getIndice() < post2.getPost().getIndice() ? +1
						: 0);
	}

}
