package com.escareno.webapp.http.interceptors;


import com.escareno.webapp.http.config.MysqlConn;
import com.escareno.webapp.http.services.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import java.util.logging.Logger;


@TransactionalJpa
@Interceptor
public class TransactionalInterceptorJpa {
    @Inject
    private EntityManager em;
    @Inject
    private Logger log;

    public TransactionalInterceptorJpa() {
    }

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {
        try {
            Logger var10000 = this.log;
            String var10001 = invocation.getMethod().getName();
            var10000.info(" ------> iniciando transaccion " + var10001 + " de la clase " + String.valueOf(invocation.getMethod().getDeclaringClass()));
            this.em.getTransaction().begin();
            Object resultado = invocation.proceed();
            this.em.getTransaction().commit();
            var10000 = this.log;
            var10001 = invocation.getMethod().getName();
            var10000.info(" ------> realizando commit y finalizando transaccion " + var10001 + " de la clase " + String.valueOf(invocation.getMethod().getDeclaringClass()));
            return resultado;
        } catch (ServiceJdbcException var3) {
            this.em.getTransaction().rollback();
            throw var3;
        }
    }
}
