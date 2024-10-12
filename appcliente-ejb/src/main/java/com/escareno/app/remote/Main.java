package com.escareno.app.remote;

import com.escareno.webapp.ejb.models.Producto;
import com.escareno.webapp.ejb.services.ServiceEjbRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        ServiceEjbRemote service = null;
        ServiceEjbRemote service2 = null;

//        final Properties env = new Properties();
//        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//        env.put("jboss.naming.client.ejb.context", true);
        try {
            InitialContext ctx = new InitialContext();
            service = (ServiceEjbRemote) ctx.lookup("ejb:/appejb-remote/ServiceEjb!com.escareno.webapp.ejb.services.ServiceEjbRemote?stateful"); // se quita flag si es stateless
            service2 = (ServiceEjbRemote) ctx.lookup("ejb:/appejb-remote/ServiceEjb!com.escareno.webapp.ejb.services.ServiceEjbRemote?stateful");
            String saludo = service.saludar("Alex");
            String saludo2 = service2.saludar("Aran");
            System.out.println(saludo);
            System.out.println(saludo2);

            Producto p = service.crear(new Producto("sandia"));
            System.out.println(p);

            service.listar().forEach(System.out::println);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
