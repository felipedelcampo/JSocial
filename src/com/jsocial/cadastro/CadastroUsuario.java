package com.jsocial.cadastro;

import java.util.ArrayList;

import com.jsocial.estruturas.Usuario;

public class CadastroUsuario {

	private ArrayList<Usuario> usuarios;

	public CadastroUsuario() {

		this.usuarios = new ArrayList<Usuario>();

	}
	
	public Usuario cadastrado(String nome) {
		
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().contentEquals(nome)) {
				return usuario;
			}
		}
		return null;
		
	}


	public String adicionaUsuario(String nome) {

		if (nome.length() < 3 || nome.length() > 20) {
			return "nome-invalido";
		} else {
			for (int i = 0; i < nome.length(); i++) {
				int testeLetra = (int) nome.charAt(i);
				if (!(testeLetra >= 65 && testeLetra <= 90)
						&& !(testeLetra >= 97 && testeLetra <= 122)) {
					return "nome-invalido";
				}
			}
		}
		if (this.cadastrado(nome) == null) {
			usuarios.add(new Usuario(nome));
			return "ok";
		} else {
			return "usuario-ja-existe";
		}

	}

}
