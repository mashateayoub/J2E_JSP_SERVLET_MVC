<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="position-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link " aria-current="page" href="http://localhost:8080/TP1/ADMIN/admindashboard.jsp"><i class="fas fa-tachometer-alt"></i> 
              Dashboard
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/TP1/ADMIN/Livre.jsp"><i class="fas fa-books"></i>
              Livres
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/TP1/ADMIN/Auteur.jsp"><i class="fas fa-user-friends"></i>   
              Auteurs
            </a>
          </li>
        </ul>
    </nav>
</body>
<style>

.sidebar {
  position: fixed;
  top: 0;
  bottom: 0;
  /* rtl:remove */
  left: 0;
  z-index: 100; /* Behind the navbar */
  padding: 48px 0 0; /* Height of navbar */
  box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
}

@media (max-width: 767.98px) {
  .sidebar {
    top: 2rem;
  }
}

.sidebar-sticky {
  position: relative;
  top: 0;
  height: calc(100vh - 48px);
  padding-top: .5rem;
  overflow-x: hidden;
  overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
}

.sidebar .nav-link {
  font-weight: 500;
  color: #333;
  transition: 0.5s ;
}

.nav-link:hover{
	color: #212529;
	margin-left: 10px;
}



.sidebar-heading {
  font-size: .75rem;
  text-transform: uppercase;
}
</style>
</html>