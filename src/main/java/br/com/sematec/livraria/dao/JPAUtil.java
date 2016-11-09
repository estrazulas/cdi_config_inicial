package br.com.sematec.livraria.dao;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
@ApplicationScoped
public class JPAUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPAUtil(){
		
	}
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria");

	public void close(EntityManager em) {
		em.close();
	}

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
