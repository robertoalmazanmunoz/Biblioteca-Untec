<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<title>Página no encontrada</title>
</head>
<body>

	<!-- NAVBAR -->
	<%@ include file="components/navbar.jspf" %>

	<main>
		<div class="container">
			<div class="row justify-content-center align-items-center" style="min-height: 70vh;">
				<div class="col-md-7 col-lg-6">
					<div class="card shadow border-0 text-center">
						<div class="card-body p-5">
							<div class="display-1 fw-bold text-primary"> 404 </div>
							<h1 class="h3 mt-3">Página no encontrada</h1>
							<p class="text-muted mt-3">Lo sentimos, la página que estás
								buscando no existe o la dirección ingresada no es correcta.</p>
							<div class="mt-4">
								<a href="${pageContext.request.contextPath}/index.jsp"
									class="btn btn-primary"> Volver al inicio </a> <a
									href="javascript:history.back()"
									class="btn btn-outline-secondary ms-2"> Volver atrás </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	
	<!-- FOOTER -->
	<%@ include file="components/footer.jspf" %>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>


</body>

</html>