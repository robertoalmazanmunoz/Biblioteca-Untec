const botonesEliminar = document.querySelectorAll(".btn-eliminar");
botonesEliminar.forEach(boton => {
	boton.addEventListener("click", function() {
		const id = this.dataset.id_libro;
		const nombre = this.dataset.nombre_libro;
		Swal.fire({
			title: "¿Eliminar Libro?",
			html: "Estas a punto de eliminar Libro de <strong>" + nombre + "</strong>. Esta acción no se podrá deshacer.",
			icon: "warning",
			showCancelButton: true,
			confirmButtonText: "Si, eliminar de todos modos.",
			cancelButtonText: "No eliminar.",
			confirmButtonColor: "#dc3545",
			reverseButtons: true
		}).then((resultado) => {
			if (resultado.isConfirmed) {
				document.getElementById("idEliminar").value = id;
				document.getElementById("formEliminar").submit();
			}
		});
	});
});