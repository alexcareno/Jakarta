<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="layout/header.jsp" />
  <h1>${title}</h1>
  <form action="${pageContext.request.contextPath}/login" method="POST">
    <div class="row my-2">
      <label class="form-label" for="username">Username</label>
      <div>
        <input class="form-control" type="text" name="username" id="username">
      </div>
    </div>
    <div class="row my-2">
      <label class="form-label" for="password">Password</label>
      <div>
        <input class="form-control" type="password" name="password" id="password">
      </div>
    </div>
    <div class="row my-2">
      <input type="submit" value="login" class="btn btn-primary">
    </div>
  </form>
<jsp:include page="layout/footer.jsp" />