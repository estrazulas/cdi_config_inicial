package br.com.sematec.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Temporizador
@Interceptor
public class GerenciadorTemporizador implements Serializable {

	@Inject
	EntityManager em;
	
	@AroundInvoke
	public Object executaTX(InvocationContext ic) throws Exception{
		long tempoInicio = System.currentTimeMillis();
		Object obj = ic.proceed();
		System.out.println("Tempo total"+(System.currentTimeMillis()-tempoInicio));
		return obj;
	}
}
