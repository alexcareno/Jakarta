package com.escareno.webapp.http.config;



import com.escareno.webapp.http.util.JpaUtil;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ApplicationScoped
public class ProducerResources {
    @Inject
    private Logger log;
    @Resource(
            name = "jdbc/mysqlDB"
    )
    private DataSource ds;

    public ProducerResources() {
    }

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection beanConnection() throws NamingException, SQLException {
        return this.ds.getConnection();
    }

    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public void close(@Disposes @MysqlConn Connection connection) throws SQLException {
        connection.close();
        this.log.info("cerrando la conexion a la bbdd mysql!");
    }

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return JpaUtil.getEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
            this.log.info("cerrando la conexion del EntityManager!");
        }

    }
}
