package com.escareno.webapp.http.filters;

import com.escareno.webapp.http.services.ServiceJdbcException;
import com.escareno.webapp.http.util.ConexcionDB;
import com.escareno.webapp.http.util.ConexcionDBDS;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebFilter("/*")
public class ConexionFilter implements Filter {

    /*@Inject
    @MysqlConn
    private Connection conn;*/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /*try  {
            Connection connRequest = this.conn;

            if (connRequest.getAutoCommit()) {
                connRequest.setAutoCommit(false);
            }
*/
        try {
//                request.setAttribute("conn", connRequest);
            chain.doFilter(request, response);
            //connRequest.commit();
        } catch (ServiceJdbcException e) {
            //connRequest.rollback();
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            e.printStackTrace();
        }
  /*      } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}
