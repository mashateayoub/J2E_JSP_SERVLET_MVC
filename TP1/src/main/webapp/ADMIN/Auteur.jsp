<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="fr" xml:lang="fr" xmlns="http://www.w3.org/1999/xhtml">
<%
    session=request.getSession(false);
    if(session.getAttribute("login")==null )
    {
        response.sendRedirect("../login.jsp");
    }

%>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

  </head>
  <body>  
<jsp:include page="\adminheader.jsp"></jsp:include>  

<div class="container-fluid">
  <div class="row">
  <jsp:include page="\adminsidebar.jsp"></jsp:include>       
			<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  url="jdbc:mysql://localhost/db_library"  user="root"  password="ayoub1999"/> 
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 p-4">

      <h2>Les Auteurs</h2>
      <div class='container mt-4 p-0'>
      

      			<div class="col-12 title align-items-end row row-cols-sm-auto row-cols-md-auto row-cols-lg-auto g-3  mb-4 ">
				<h5>Liste des Auteurs :</h5>
			  </div>


      <div class="table-responsive">
        <table class="table table-striped table-hover table-sm">
          <thead class="bg-dark text-light">
					<tr>
						<th>Matricule</th>
						<th>Nom</th>
						<th>Prenom</th>
						<th>Genre</th>
					</tr>
          </thead>
          <sql:query dataSource="${db}" var="rs">  
			SELECT * from Auteur;  
			</sql:query> 
          <tbody>
					
					<c:forEach var="auteur" items="${rs.rows}">

						<tr>
							<td><c:out value="${auteur.matricule}" /></td>
							<td><c:out value="${auteur.nom}" /></td>
							<td><c:out value="${auteur.prenom}" /></td>
							<td><c:out value="${auteur.genre}" /></td>
						</tr>
					</c:forEach>
					
				</tbody>
        </table>
      </div> 
      </div>
      
    </main>
  </div>
</div>

 </body>
 <style>
@import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
body{
	font-family: 'Roboto', sans-serif;
	font-size:0.9rem;
	}
.title{
	margin-right:55%!important;

}
 </style>
</html>