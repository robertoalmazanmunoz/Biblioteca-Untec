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

    <title>Login | Biblioteca Digital UNTEC</title>
</head>
<body>

	<!-- NAVBAR -->
	<%@ include file="components/navbar.jspf"%>
	
    <div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-header bg-primary text-white">
						<h2 class="card-title">Login UNTEC</h2>
					</div>
					<div class="card-body">
						<c:if test="${not empty error}">
							<div class="alert alert-danger">${error}</div>
						</c:if>
						<form action="${pageContext.request.contextPath}/Login" method="post">
							<div class="mb-3">
								<label for="usuario" class="fw-bold form-label">Usuario</label> 
								<input
									class="form-control" type="email" name="usuario" id="usuario"
									required>
							</div>
							<div class="mb-3">
								<label for="contraseña" class="fw-bold form-label">Contraseña</label> 
								<input
									class="form-control" type="password" name="contraseña" id="contraseña"
									required>
							</div>
							<div class="mb-3">
								<button class="btn btn-primary">Ingresar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
    
    <!-- FOOTER -->
	<%@ include file="components/footer.jspf"%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>