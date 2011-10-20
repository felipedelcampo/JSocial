/**
 * @author felipe
 * Classe para ordenação das Tendências
 */

package com.jsocial.estruturas;

import java.util.Comparator;

public class ComparadorTendencia implements Comparator<Tendencia> {
	
	public int compare(Tendencia tend1, Tendencia tend2) {
		// Compara primeiro pelo número de ocorrencias, se for igual compara por ordem alfabetica
		return tend1.getNumeroDeOcorrencias() > tend2.getNumeroDeOcorrencias() ? -1
				: (tend1.getNumeroDeOcorrencias() < tend2.getNumeroDeOcorrencias() ? +1
						: tend1.getHashTag().compareTo(tend2.getHashTag()));
	}

}
