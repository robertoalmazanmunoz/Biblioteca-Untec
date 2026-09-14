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
 * <h2>Servlet EliminarLibroServlet</h2>
 * <p><b>Servlet</b> que maneja las llama post y get cuando se va a <b>eliminar un libro</b> de la base de datos.</p>
 * <p>Contiene la <b>lógica de negocio</b> para que se <b>elimine correctamente</b> un libro de la base de datos.</p>
 * <h2>url-pattern</h2>
 * <p>El servlet se llama con el nombre /EliminarLibro</p>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class EliminarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Conexión a la tabla libro de la base de datos.
	private LibroDAO libroDao;
	
	public void init()  {
		libroDao = new LibroDAO();
	}
	
       /**
        * <p>Cuando se intentan <b>eliminar un libro</b> de la base de datos puede <b>fallar<7B> o <b>borrarse correctamente</b> en ambos 
        * casos se envía una mensaje via <b>Get</b> que se recibe aquí y se gatilla la <b>alerta correspondiente</b>.</p>
        * @since 1.0
        * @author Roberto Almazán (Untec)
        */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mensaje = request.getParameter("mensaje");
		
		//Si el mensaje recibido es eliminado se gatilla la alerta de éxito
		if("eliminado".equals(mensaje)) {
			request.setAttribute("mensajeEliminado", true);
		}
		
		//Si el mensaje recibido es errorEliminar se gatilla la alerta de error
		if("errorEliminar".equals(mensaje)) {
			request.setAttribute("errorEliminar", true);
		}
		
		try {
			//En ambos casos se redirecciona al Catálogo de Libros donde se desplegará la alerta correspondiente
			List<Libro> libros = libroDao.readAll();			
			request.setAttribute("libros", libros);
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "No fue posible procesar la solicitud. Inténtelo nuevamente.");
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
		}

	}

	/**
	 * <p>Cuando se gatilla el formulario de <b>eliminación de un libro</b>, se envían los datos mediante una petición <b>Post</b>
	 * con la acción <b>eliminar</b>, los datos se reciben aquí y se ejecuta la <b>consulta delete</b> a la base de datos para
	 * eliminar el libro solicitado, si la consulta es <b>exitosa</b> o <b>fallida</b> se envía el mensaje vía <b>Get</b> para gatillar
	 * la <b>alerta correspondiente</b>.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String accion = request.getParameter("accion");
		
		if ("eliminar".equals(accion)) {
			try {
				String idTexto = request.getParameter("id"); 
				int id = Integer.parseInt(idTexto);
				boolean eliminado = libroDao.delete(id);//ejecución del delete en la base de datos.
				
				if (eliminado) {
					//Si se eliminó correctamente el libro se envía el mensaje de éxito.
					response.sendRedirect(request.getContextPath() + "/EliminarLibro?mensaje=eliminado");
				} else {
					//Si ocurre un error se envía el mensaje de fallo.
					response.sendRedirect(request.getContextPath() + "/EliminarLibro?mensaje=errorEliminar");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "EliminarLibro?mensaje=errorEliminar");
			}
			return;
		}
	}

}
