package br.com.sematec.livraria.dao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPAUtil(){
		
	}
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria");

	public void close(@Disposes EntityManager em) {
		em.close();
	}


	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
