<%--
  Created by IntelliJ IDEA.
  User: Alejandro
  Date: 24/09/2024
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*, com.escareno.webapp.http.models.*" %>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    String mensajeReq = (String) request.getAttribute("mensaje");
    String mensajeApp = (String) getServletContext().getAttribute("mensaje");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Listado de productos</title>
</head>
<body>
    <h1>Listado de productos</h1>
    <% if(username.isPresent()) { %>
    <div>Hola <%=username.get()%>, bienvenido!</div>
    <% } %>
    <table>
        <tr>
            <th>id</th>
            <th>nombre</th>
            <th>tipo</th>
            <% if(username.isPresent()) { %>
            <th>precio</th>
            <th>agregar</th>
            <% } %>
        </tr>

        <% for(Producto p: productos ) { %>
        <tr>
            <td><%=p.getId() %></td>
            <td><%=p.getNombre() %></td>
            <td><%=p.getTipo() %></td>
            <% if(username.isPresent()) { %>
            <td><%=p.getPrecio() %></td>
            <td><a href="<%=request.getContextPath() %>/carro/agregar?id=<%=p.getId() %>">Agregar al Carro</a></td>
            <% } %>
        </tr>
        <% } %>
    </table>
    <p><%=mensajeApp%></p>
    <p><%=mensajeReq%></p>
</body>
</html>
