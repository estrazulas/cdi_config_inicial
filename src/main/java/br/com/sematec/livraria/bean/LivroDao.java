package br.com.sematec.livraria.bean;

import java.io.Serializable;

import br.com.sematec.livraria.dao.DAO;
import br.com.sematec.livraria.modelo.Livro;

public class LivroDao extends DAO<Livro> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LivroDao(){
		super(Livro.class);
	}
}
