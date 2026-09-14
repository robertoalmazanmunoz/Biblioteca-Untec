<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Roberto Marcos Almazán Muñoz">

    <!--Link CSS Bootstrap-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">

<title>Agregar Libro | Biblioteca Digital UNTEC</title>
</head>
<body>

	<!-- NAVBAR -->
	<%@ include file="components/navbar.jspf" %>
	
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-header bg-primary text-white">
						<h2 class="card-title">Agregar Libro UNTEC</h2>
					</div>
					<div class="card-body">
						<c:if test="${not empty error}">
							<div class="alert alert-danger" role="alert">
								<strong>Error:</strong> ${error}
							</div>
						</c:if>
						<form action="${pageContext.request.contextPath}/AgregarLibro" method="post">
							<div class="mb-3">
								<label for="nombe_libro" class="fw-bold form-label">Nombre del Libro</label> 
								<input
									class="form-control" type=text name="nombre_libro" id="nombre_libro_agregar"
									value="${not empty libro ? libro.nombre_libro : param.nombre_libro}" required>
							</div>
							<div class="mb-3">
								<label for="autor_libro" class="fw-bold form-label">Autor del Libro</label> 
								<input
									class="form-control" type="text" name="autor_libro" id="autor_libro_agregar"
									value="${not empty libro ? libro.autor_libro : param.autor_libro}" required>
							</div>
							<div class="mb-3">
								<label for="editorial" class="fw-bold form-label">Editorial del Libro</label> 
								<input
									class="form-control" type="text" name="editorial" id="editorial_agregar"
									value="${not empty libro ? libro.editorial : param.editorial}" required>
							</div>
							<div class="mb-3">
								<label for="ano_publicacion" class="fw-bold form-label">Año de Publicación</label> 
								<input
									class="form-control" type="number" name="ano_publicacion" id="ano_publicacion_agregar"
									value="${not empty libro ? libro.ano_publicacion : param.ano_publicacion}" required>
							</div>
							<div class="mb-3">
								<button type="submit" class="btn btn-primary">Registrar</button>
							</div>
						</form>
					</div>
					<div class="card-footer">
						<div class="mb-3">
							<a href="menu.jsp" class="btn btn-primary">Volver al Menú</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- FOOTER -->
	<%@ include file="components/footer.jspf" %>    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>