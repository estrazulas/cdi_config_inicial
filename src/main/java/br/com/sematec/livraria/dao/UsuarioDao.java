package br.com.sematec.livraria.dao;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sematec.livraria.modelo.Usuario;

public class UsuarioDao extends DAO<Usuario>implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private JPAUtil jp;
	
	public UsuarioDao() {
		super(Usuario.class);
	}

	public boolean existe(Usuario usuario) {
		
		TypedQuery<Usuario> query = jp.getEntityManager().createQuery(" select u from Usuario u " + " where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		return true;
	}
}
