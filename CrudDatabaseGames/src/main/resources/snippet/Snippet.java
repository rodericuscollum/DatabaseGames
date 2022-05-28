package snippet;

public class Snippet {
	<!DOCTYPE html>
	<html xmlns:th="http://www.thymeleaf.org">
	<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
	</head>
	
	<h1>Títulos de Amiga</h1>
	    <div class="row" th:each="amiga : ${listaAmiga}">
	        <div class="col-1" th:text="${amiga.id}">ID</div>
	        <div class="col-1"><img src="" th:src="@{${amiga.image} ?: 'https://api.adorable.io/avatars/64/{title}.png'(title=${amiga.title})}" width="64px" /></div>
	        <div class="col-1" th:text="${amiga.title}">loquesea</div>
	        <div class="col-1" th:text="${amiga.chip}">loquesea</div>
	        <div class="col-1" th:text="${amiga.year}">loquesea</div>
			<div class="col-1" th:text="${amiga.developer}">loquesea</div>
	        <div class="col-1" th:text="${amiga.publisher}">loquesea</div>
	       
	        <div class="col-1">
	          <a class="btn btn-primary" th:href="@{/amiga/edit/__${amiga.id}__}"  role="button" >Cambia</a>
	        </div>
	        <div class="col-1">
	          <a class="btn btn-primary" th:href="@{/amiga/delete/__${amiga.id}__}" role="button" >Borra</a>
	        </div>
	    </div>
	
	</body>
	</html>
}

