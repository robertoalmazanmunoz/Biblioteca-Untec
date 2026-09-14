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
import com.untec.libro.util.Validacion;

/**
 * <h2>Servlet AgregarLibroServlet</h2>
 * <p><b>Servlet</b> que maneja las llama post y get cuando se va a <b>agregar un libro</b> a la base de datos.</p>
 * <p>Contiene la <b>lógica de negocio</b> para que el <b>nombre del libro</b>, el <b>autor</b>, la <b>editorial</b> y el 
 * <b>año de publicación</b> estén estandarizados y no generen problemas al <b>insertarlos</b> en la base de datos.</p>
 * <h2>url-pattern</h2>
 * <p>El servlet se llama con el nombre /AgregarLibro</p>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class AgregarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Conexión a la tabla libro de la base de datos
	private LibroDAO libroDao;
	
	public void init()  {
		libroDao = new LibroDAO();
	}
     
	/**
	 * <p>Cuando se <b>inserta correctamente un libro</b> a la base de datos envía una señal a <b>listarLibro.jsp</b>
	 * para quese levante una <b>alerta exitosa</b>.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mensaje = request.getParameter("mensaje");
		
		//Se verifica que llegue el mensaje que va a gatillar la alerta
		if("insertado".equals(mensaje)) {
			//Al recibir el mensaje se gatila la alerta
			request.setAttribute("mensajeInsertado", true);
		}
		try {
			//Cargamos el catálogo de libros para que se despliegue al cargar la página listaLibros.jsp
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
	 * <p>Cuando el formulario de la página <b>agregar.jsp</b> ejecuta el submit envía los datos vía <b>post</b>, aquí se recibe
	 * cada campo y se valida gracias a los métodos creados en la Clase <b>Validacion</b> antes de intentar mediante el <b>DAO</b>
	 * insertarlos en la base de datos, si se <b>inserta correctamente</b> se envía el mensaje via <b>Get</b> para que se gatille 
	 * la <b>alerta de éxito</b>.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//Se reciben los datos del formulario de la página agregar.jsp.
		String nombre_libro = request.getParameter("nombre_libro");
		String autor_libro = request.getParameter("autor_libro");
		String editorial = request.getParameter("editorial");
		String ano_publicacion_texto = request.getParameter("ano_publicacion");
		
		//Se valida cada campo gracias a los métodos de la Clase Validación antes de intentar hacer el Insert.
		if (!Validacion.validarNombreLibro(nombre_libro)) {
			request.setAttribute("error", "El Nombre del Libro ingresado no es válido.");
			request.getRequestDispatcher("agregar.jsp").forward(request, response);
			return;
		}
		
		if (!Validacion.validarAutorLibro(autor_libro)) {
			request.setAttribute("error", "El Nombre del Autor del Libro ingresado no es válido.");
			request.getRequestDispatcher("agregar.jsp").forward(request, response);
			return;
		}
		
		if (!Validacion.validarEditorial(editorial)) {
			request.setAttribute("error", "El Nombre de la Editorial del Libro ingresado no es válido.");
			request.getRequestDispatcher("agregar.jsp").forward(request, response);
			return;
		}
		
		try {
			int ano_publicacion = Integer.parseInt(ano_publicacion_texto);
			
			if (!Validacion.validarAnoPublicacion(ano_publicacion)) {
				request.setAttribute("error", "El Año de Publicación del Libro ingresado no es válido.");
				request.getRequestDispatcher("agregar.jsp").forward(request, response);
				return;
			}
			
			Libro l = new Libro(nombre_libro, autor_libro, editorial, ano_publicacion);
			
			boolean insertado = libroDao.create(l);//Se hace el Insert en la base de datos.
			
			if (!insertado) {//En caso de error se devuelve al formulario en la página agregar.jsp con un mensaje de error.
				request.setAttribute("error", "No fue posible registrar el Libro. Revise los datos ingresados en el formulario e inténtelo nuevamente.");
				request.setAttribute("libro", l);
				request.getRequestDispatcher("agregar.jsp").forward(request, response);
				return;
			}
			
			//En caso de éxito se envía un mensaje vía Get para gatillar la Alerta en la página listaLibros.jsp.
			response.sendRedirect(request.getContextPath() + "/AgregarLibro?mensaje=insertado");
			return;
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "El Año de Publicación del Libro deben ser valor numérico.");
			request.getRequestDispatcher("agregar.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "No fue posible registrar el Libro. Revise los datos ingresados en el formulario e inténtelo nuevamente.");
			request.getRequestDispatcher("agregar.jsp").forward(request, response);
		}
		
		
	}

}
