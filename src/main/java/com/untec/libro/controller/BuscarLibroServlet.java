package com.untec.libro.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.untec.libro.dao.LibroDAO;
import com.untec.libro.model.Libro;

/**
 * <h2>Servlet BuscarLibroServlet</h2>
 * <p><b>Servlet</b> que maneja las llama post y get cuando se va a <b>buscar un libro</b> a la base de datos.</p>
 * <p>Contiene la <b>lógica de negocio</b> para que se despliegue de manera correcta el <b>Catálogo de Libros</b> según
 * los <B>criterios</b> de búsqueda que use el <b>Usuario</b>.</p>
 * <h2>url-pattern</h2>
 * <p>El servlet se llama con el nombre /BuscarLibro</p>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class BuscarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Conexión a la tabla libro de la base de datos
	private LibroDAO libroDao;
	
	public void init()  {
		libroDao = new LibroDAO();
	}  
 
	/**
	 * <p>En caso de que un <b>Usuario</b> intente ingresar a una página que <b>no le corresponde</b>, se redirecciona al <b>Catálogo
	 * de Libros</b> con un mensaje de error vía <b>Get</b> aquí se captura el mensaje y se gatilla el <b>despliegue de la alerta</b> que
	 * se visualizará en la página <b>listarLibro.jsp</b>.</p>
	 * <p>Cuando se usa el <b>buscador</b> en el <b>Catálogo de Libros</b> se envía una petición <b>Get</b> con la acción <b>Buscar</b>,
	 * aquí se recibe esa acción junto con el <b>criterio</b> de búsqueda solicitado para generar la <b>consulta de lectura</b> a la base 
	 * de datos y desplegar el <b>Catálogo de Libros</b> filtrado según lo solicitado por el <b>Usuario</b>.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		String mensaje = request.getParameter("mensaje");
		
		//En caso de intento de ingreso de un usuario a una página no permitida según su rol se gatilla la alerta.
		if("sinPermiso".equals(mensaje)) {
			request.setAttribute("mensajeSinPermiso", true);
		}
		
		try {
			//Buscar
			if ("buscar".equals(accion)) {
				String criterio = request.getParameter("criterio");
				if (criterio == null) {
					criterio = "";
				}
				
				List<Libro> libros;
				//Si se ocupa la barra de búsqueda  en el catálogo sin ningún criterio específico, se despliega el catálogo completo.
				if (criterio.trim().isEmpty()) {
					libros = libroDao.readAll();
				} else {//Si se ocupa con alguna palabra, se ocupa ese criterio para filtrar en la base de datos el catálogo.
					libros = libroDao.readByCriterio(criterio);
				}
				request.setAttribute("libros", libros);
				request.setAttribute("criterio", criterio);
			} else {//cuando se carga la página del catálogo, siempre se despliega con tódos los libros disponibles.
				List<Libro> libros = libroDao.readAll();			
				request.setAttribute("libros", libros);
			}			
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "No fue posible buscar el Libro solicitado, inténtelo nuevamente.");
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "No fue posible procesar la solicitud");			
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
		}
		
	}

	/**
	 * <p>No se aceptan peticiones del tipo <b>Post<7b> por lo que es redireccionado al método <b>Get</b>.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
