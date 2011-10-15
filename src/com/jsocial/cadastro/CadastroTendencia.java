package com.jsocial.cadastro;

import java.util.ArrayList;
import java.util.Collections;

import com.jsocial.estruturas.ComparadorTendencia;
import com.jsocial.estruturas.Post;
import com.jsocial.estruturas.Tendencia;

public class CadastroTendencia {

	ArrayList<Tendencia> listaTendencia;

	public CadastroTendencia() {

		listaTendencia = new ArrayList<Tendencia>();

	}

	private Tendencia verificaTendencia(String hashTag) {

		for (Tendencia tendenciaLista : listaTendencia) {

			if (tendenciaLista.getHashTag().contentEquals(hashTag)) {
				return tendenciaLista;
			}

		}
		return null;

	}

	public synchronized void computaTendencia(Post post) {
		
		String listaPost[] = post.getTexto().split("[^a-zA-Z_0-9çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ#]");

		for (int i = 0; i < listaPost.length; i++) {

			if (listaPost[i].startsWith("#")) {

				Tendencia tendencia = this.verificaTendencia(listaPost[i]);
				if (tendencia != null) {

					tendencia.aumentaOcorrencia(post);

				} else {

					listaTendencia.add(new Tendencia(listaPost[i], post));

				}
				Collections.sort(this.listaTendencia, new ComparadorTendencia());

			}

		}

	}
	
	public synchronized ArrayList<String> listarTendencia() {
		
		ArrayList<String> retorno = new ArrayList<String>();
		for ( int i = 0; (i < 5 && i < this.listaTendencia.size()) ; i++) {
			
			retorno.add(this.listaTendencia.get(i).getHashTag());
			
		}
		return retorno;
		
	}
	
	public synchronized ArrayList<Post> retornaPostTendencia(String hashTag) {
		
		ArrayList<Post> listaPost = new ArrayList<Post>();
		for (Tendencia tendenciaLista : this.listaTendencia) {
			
			if (tendenciaLista.getHashTag().contentEquals(hashTag)) {
				listaPost = tendenciaLista.getListaPost();
			}
			
		}
		Collections.reverse(listaPost);
		return listaPost;
		
	}

}
