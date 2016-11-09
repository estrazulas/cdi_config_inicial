package br.com.sematec.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional 
@Interceptor
public class GerenciadorTransacao implements Serializable {

	@Inject
	EntityManager em;
	
	@AroundInvoke
	public Object executaTX(InvocationContext ic) throws Exception{
		em.getTransaction().begin();
		System.out.println("entrou");
		Object obj = ic.proceed();
		em.getTransaction().commit();
		return obj;
	}
}
