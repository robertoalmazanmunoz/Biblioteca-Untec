<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Roberto Marcos Almazán Muñoz">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

<title>Catálogo de Libros | Biblioteca Digital UNTEC</title>
</head>
<body>

	<!-- NAVBAR -->
	<%@ include file="components/navbar.jspf"%>

	<div class="container mt-5">
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h2>Libros disponibles</h2>
			<c:if test="${sessionScope.rol == 'Administrador'}">
			<a href="${pageContext.request.contextPath}/agregar.jsp"
				class="btn btn-primary"> Nuevo Libro </a>
			</c:if>
		</div>

		<div class="card shadow mb-4">
			<div class="card-body">
				<form action="BuscarLibro" method="get" class="row g-2">
					<input type="hidden" name="accion" value="buscar">
					<div class="col-md-9">
						<label for="criterio" class="visually-hidden"> Nombre Libro, Autor o Editorial </label> 
						<input type="text" class="form-control" id="criterio"
							name="criterio" value="${criterio}"
							placeholder="Buscar por Nombre del Libro, Autor o Editorial...">
					</div>

					<div class="col-md-3 d-flex gap-2">
						<button type="submit" class="btn btn-primary">Buscar</button>
						<a href="BuscarLibro" class="btn btn-secondary"> Limpiar </a>
					</div>
				</form>
			</div>
		</div>

		<c:if test="${not empty mensaje}">
			<div class="alert alert-success alert-dismissible fade show">
				${mensaje}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>


		<c:if test="${not empty error}">
			<div class="alert alert-danger alert-dismissible fade show">
				${error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<div class="card shadow">
			<div class="card-body">
				<div class="table-responsive">
					<table
						class="table table-striped table-hover table-bordered align-middle">
						<thead class="table-dark">
							<tr>
								<th>ID</th>
								<th>Nombre Libro</th>
								<th>Autor</th>
								<th>Editorial</th>
								<th>Año de Publicación</th>
								<c:if test="${sessionScope.rol == 'Administrador'}">
								<th class="text-center">Acciones</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="libro" items="${libros}">
								<tr>
									<td>${libro.id_libro}</td>
									<td>${libro.nombre_libro}</td>
									<td>${libro.autor_libro}</td>
									<td>${libro.editorial}</td>
									<td>${libro.ano_publicacion}</td>

									<c:if test="${sessionScope.rol == 'Administrador'}">
									<td class="text-center">
										<a href="ModificarLibro?accion=editar&id=${libro.id_libro}" class="btn btn-sm btn-warning my-1">
											<i class="bi bi-pencil"></i>
										</a> 
										<button type="button" class="btn btn-sm btn-danger my-1 btn-eliminar"
											data-id_libro="${libro.id_libro}" data-nombre_libro="${libro.nombre_libro}" >
											<i class="bi bi-trash"></i>
										</button>
									</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:if test="${sessionScope.rol == 'Administrador'}">
					<form id="formEliminar" action="${pageContext.request.contextPath}/EliminarLibro" method="post">
						<input type="hidden" name="accion" value="eliminar">
						<input type="hidden" name="id" id="idEliminar">
					</form>
					</c:if>
				</div>
			</div>
		</div>
	</div>



	<!-- FOOTER -->
	<%@ include file="components/footer.jspf"%>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
		
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	<c:if test="${mensajeInsertado}">
		<script type="text/javascript">
			Swal.fire({
				title: "Registro insertado",
				text: "El libro ha sido insertado correctamente en la Base de Datos.",
				icon: "success",
				confirmButtonText: "Aceptar"
			});
		</script>
	</c:if>
	
	<c:if test="${mensajeActualizado}">
		<script type="text/javascript">
			Swal.fire({
				title: "Registro actualizado",
				text: "El libro seleccionado ha sido actualizado correctamente.",
				icon: "success",
				confirmButtonText: "Aceptar"
			});
		</script>
	</c:if>
	
	<c:if test="${mensajeEliminado}">
		<script type="text/javascript">
			Swal.fire({
				title: "Registro eliminado",
				text: "El libro seleccionado ha sido eliminado correctamente.",
				icon: "success",
				confirmButtonText: "Aceptar"
			});
		</script>
	</c:if>
	
	<c:if test="${errorEliminar}">
		<script type="text/javascript">
			Swal.fire({
				title: "Error",
				text: "No fue posible eliminar el libro.",
				icon: "error",
				confirmButtonText: "Aceptar"
			});
		</script>
	</c:if>
	
	<c:if test="${sinPermiso}">
		<script>
			Swal
					.fire({
						title : "Error",
						text : "No tienes los permisos necesarios para ejecutar la acción.",
						icon : "error",
						confirmButtonText : "Aceptar"
					});
		</script>
	</c:if>
		
	<script src="assets/js/delete.js"></script>
</body>
</html>