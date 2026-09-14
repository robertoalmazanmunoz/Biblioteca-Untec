<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>Home | Biblioteca Untec</title>
</head>
<body>
	<!-- NAVBAR -->
	<%@ include file="components/navbar.jspf" %>

		<!-- CONTENIDO PRINCIPAL -->
	<main>
		<section class="bg-light py-5">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-lg-7">
						<span class="badge bg-primary mb-3">Sistema de biblioteca Untec</span>
						<h1 class="display-4 fw-bold">Biblioteca Untec</h1>
						<p class="lead mt-3">Aplicación web desarrollada con Java EE,
							JSP, Servlets, JSTL, Bootstrap y MySQL.</p>
						<p class="text-muted">Permite agregar libros, consultar y modificar el catálogo de libros.</p>
						<div class="mt-4">
							<!-- USUARIO AUTENTICADO -->
                			<c:if test="${not empty sessionScope.usuario}">
								 <c:if test="${sessionScope.rol == 'Administrador'}">
									<a href="${pageContext.request.contextPath}/agregar.jsp"
									class="btn btn-primary btn-lg me-2"> Agregar Libro </a> 
								</c:if>
								<a
								href="${pageContext.request.contextPath}/BuscarLibro"
								class="btn btn-outline-primary btn-lg"> Ver Catálogo de Libros </a>
							</c:if>
							<!-- USUARIO NO AUTENTICADO -->
                			<c:if test="${empty sessionScope.usuario}">
                				<a href="${pageContext.request.contextPath}/Login"
									class="btn btn-primary btn-lg me-2"> Iniciar sesión </a> 
                			</c:if>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- Sobre nuestra Biblioteca  -->
		<section class="py-5">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-10">
						<h2 class="text-center mb-4">Sobre nuestra Biblioteca</h2>
						<p>Nuestra Biblioteca Untec es una biblioteca digital en desarrollo, buscamos tener la más
						amplia variedad de libros a disposición de nuestros Usuarios, aquí usted podrá consultar nuestro 
						Catálogo de Libros y próximamente podrá solicitar el préstamos de ellos y retirarlo su sucursal más cercana.</p>
					</div>
				</div>
			</div>
		</section>

		<!-- HISTORIA  -->
		<section class="bg-light py-5">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-10">
						<h2 class="text-center mb-5">Breve historia de nuestra biblioteca</h2>
						<div class="row g-4">
							<!-- QUETELET -->
							<div class="col-md-4">
								<div class="card h-100 shadow-sm border-0">
									<div class="card-body">
										<h4 class="card-title">Nuestro origen</h4>
										<p class="card-text">Nuestra biblioteca nace a través de un emprendimiento de Untec que busca 
										apoyar en la necesidad de nuestros usuarios por tener acceso a la más variada y completa colección
										de libros y saciar su apetito por la ectura.</p>
										<p class="card-text">Agradecemos el apoyo de nuestra comunidad que en base a sus sugerencias
										esperamos hacer crecer nuestro Catálogo y poder cubrir la más amplia variedad de libros acorde 
										a sus necesidades.</p>
									</div>
								</div>
							</div>

							<!-- RELACIÓN PESO ESTATURA -->
							<div class="col-md-4">
								<div class="card h-100 shadow-sm border-0">
									<div class="card-body">
										<h4 class="card-title">Nuestra Misión</h4>
										<p class="card-text">Buscamos ser la biblioteca preferida a nivel Nacional, la que todos
										busquen porque saben que aquí encontrarán todos los libros de su interés y si no los encuentran 
										pueden sugerirlo y nos comprometemos a tenerlo disponible a la brevedad.</p>
										<p class="card-text">Si te gustan los libros Biblioteca Untec es tú biblioteca.</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

	</main>
	
	<!-- FOOTER -->
	<%@ include file="components/footer.jspf" %>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>