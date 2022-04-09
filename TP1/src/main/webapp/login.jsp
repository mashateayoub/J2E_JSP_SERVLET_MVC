<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN</title>
</head>
<body class='bg-dark'>
<div class="container">
      <div class="row justify-content-center mt-4 text-light">
      		
        <div class="col-12 col-md-8 col-lg-5">
            <h3 class="text-center mb-4">Connexion</h3>
            <div class="card  text-dark shadow p-3 mb-5 bg-body rounded">
                    
                <div class="card-body ">
                    <form class="col-md-12 p-t-10" role="form" method="POST" action="auth">

                        <div class="form-group">
                            <label class="control-label mb-1" for="email">Login</label>
                            <div>
                                <input type="text" class="form-control" name="email" id="email">
                            </div>
                        </div>

                        <div class="form-group mt-2">
                            <label class="control-label mb-1" for="password">Password</label>

                            <div>
                                <input type="password" class="form-control" name="password" id="password" >
                            </div>
                        </div>
                        
                        <!-- Scriptlet pour l'alert -->
                      <%
                      if(request.getAttribute("isLogged")!=null )
                      {%>  
                          <div class="alert alert-danger mt-4" role="alert">
  						Email ou mots de passe incorrectes
  						</div>
  						<%} %>
                        <div class="form-group mt-3 ">
                            <div class="">
                                <button type="submit" class="btn btn-block btn-dark ">
                                    Se connecter
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
               </div>
    </div>
  </div>
</body>
<style>
@import url('https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap');
body{
	font-family: 'Source Sans Pro', sans-serif;
	padding:8% 3%;
	background: #f8f8f8;

}
h3{
	font-weight:bolder;
	font-size: 44px;
}





</style>
</html>