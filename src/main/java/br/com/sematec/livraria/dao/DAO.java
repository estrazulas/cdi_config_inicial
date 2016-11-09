package br.com.sematec.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

@Dependent
public class DAO<T> implements Serializable{
	
	private final Class<T> classe;

	@Inject
	JPAUtil jpaUtil;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {
		// consegue a entity manager
		EntityManager em = jpaUtil.getEntityManager();
		// abre transacao
		em.getTransaction().begin();
		// persiste o objeto
		em.persist(t);
		// commita a transacao
		em.getTransaction().commit();
		// fecha a entity manager
		em.close();
	}

	public void atualiza(T t) {
		EntityManager em = jpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	public T buscaPorId(Integer id) {
		EntityManager em = jpaUtil.getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}

	public int contaTodos() {
		EntityManager em = jpaUtil.getEntityManager();
		long result = (Long) em.createQuery("select count(n) from livro n").getSingleResult();
		em.close();
		return (int) result;
	}

	public List<T> listaTodos() {
		EntityManager em = jpaUtil.getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = jpaUtil.getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
		em.close();
		return lista;
	}

	public void remove(T t) {
		EntityManager em = jpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}
}
