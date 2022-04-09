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
    <meta charset="utf-8" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	  <meta name="format-detection" content="telephone=no" />  
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

  </head>
  <body>
    
<jsp:include page="\adminheader.jsp"></jsp:include>  

<div class="container-fluid">
  <div class="row">
  <jsp:include page="\adminsidebar.jsp"></jsp:include>    
     <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 p-4">

      <h2>Livres</h2>
      <div class='mt-4 '>
      			<div class="col-12 title">
				<h5>Mise a jour :</h5>
			  </div>  
      </div>
      <div class="container mt-4">
      
		<form action="updatelivre" method="post" accept-charset="utf-8" >
        <div  class="row g-3" >
  <div class="col-md-2">
    <label for="validationDefault01" class="form-label">ISBN</label>
    <input type="text" class="form-control " id="validationDefault01" value="<c:out value='${Livre.isbn}' />"   name='isbn'  lang='fr' readonly>
  </div>
  <div class="col-md-4">
    <label for="validationDefault02" class="form-label">Titre</label>
    <input type="text" class="form-control" id="validationDefault02"  value="<c:out value='${Livre.titre}' />"   name='titre' lang='fr' required>
  </div>
  <div class="col-md-4">
    <label for="validationDefault03" class="form-label">Matricule</label>
    <input type="text" class="form-control" id="validationDefault03"  value="<c:out value='${Livre.matricule}' />"  name='matricule' lang='fr' required>
  </div>
  
  <div class="col-md-4">
    <label for="validationDefault03" class="form-label">Date d'edition</label>
    <input type="Date" class="form-control" id="validationDefault03"  value="<c:out value='${Livre.date_edition}' />"  name='date_edition' lang='fr' required>
  </div>
  
  <div class="col-md-4">
    <label for="validationDefault04" class="form-label">Editeur</label>
					    <select class="form-select" aria-label="Default select example"   name='editeur' id='validationDefault04'>
					  <option selected value="<c:out value='${Livre.editeur}' />"><c:out value='${Livre.editeur}' /></option>
					  <option value="ENI">ENI</option>
					  <option value="First">First</option>
					  <option value="Dunod">Dunod</option>

					</select>
  </div> 
  <div class="col-md-12">
    <label for="exampleFormControlTextarea1">Description</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" lang='fr' accept-charset="utf-8" name='description' rows="5"><c:out value='${Livre.description}' /></textarea>
  </div>
</div>

      <div class="mt-4">
        <button type="submit" class="btn btn-dark">Confirmer</button>
      </div>


</form>
      
      
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
	margin-right:50%!important;

}
</style>
</html>