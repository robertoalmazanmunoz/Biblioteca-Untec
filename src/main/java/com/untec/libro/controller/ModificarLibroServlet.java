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
 * <h2>Servlet ModificarLibroServlet</h2>
 * <p><b>Servlet</b> que maneja las llama post y get cuando se va a <b>modificar un libro</b> en la base de datos.</p>
 * <p>Contiene la <b>lógica de negocio</b> para que el <b>nombre del libro</b>, el <b>autor</b>, la <b>editorial</b> y el 
 * <b>año de publicación</b> estén consistentes para no generen problemas al <b>actualizar</b> en la base de datos.</p>
 * <h2>url-pattern</h2>
 * <p>El servlet se llama con el nombre /ModificarLibro</p>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class ModificarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Conexión con la tabla libro de la base de datos.
	private LibroDAO libroDao;
	
	public void init()  {
		libroDao = new LibroDAO();
	}       

	/**
	 * <p>Para actualizar algún atributo de un libro en la base de datos se envía la acción <b>editar</b> vía <b>Get</b> junto al <b>id</b>
	 * del libro que se desea modificar, se recibe aquí esa información para poder <b>buscar</b> en la base de datos la información de
	 * ese libro para ser enviado al formulario en la página <b>modificar.jsp</b> donde se editará la información que e <b>Usuario</b>
	 * estime conveniente.</p>
	 * <p>Al intentar actualizar los datos en la base de datos, se puede producir un <b>fallo</b> o se puede <b>actualizar correctamente</b>
	 * en caso de fallar se envía un mensaje de <b>error</b> de vuelta al formulario en la página <b>modificar.jsp</b>, 
	 * en el caso de éxito se gatilla una <b>alerta</b> que se despliega en la página <b>listarLibro.jsp</b>.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		String mensaje = request.getParameter("mensaje");
		
		//Si se recibe el mensaje actualizado, se gatilla la alerta de éxito.
		if("actualizado".equals(mensaje)) {
			request.setAttribute("mensajeActualizado", true);
		}
		
		try {	
			//Editar
			if ("editar".equals(accion)) {//cuando llega la acción editar se busca el libro en la base de datos.
				String idTexto = request.getParameter("id"); 
				int id = Integer.parseInt(idTexto);
				Libro l = libroDao.readById(id);
				if (l == null) {
					request.setAttribute("error", "El Libro solicitado no existe.");
					response.sendRedirect(request.getContextPath() + "/ModificarLibro");
					return;
				}
				//Si el libro se encuentra, se envían los datos al formulario en la página modificar.jsp.
				request.setAttribute("libro", l);
				request.getRequestDispatcher("modificar.jsp").forward(request, response);
				return;
			}
			try {
				//Antes de redireccionar al Catálogo de Libros donde se despliega la alerta, se buscan todos los libros disponibles para desplegarlos.
				List<Libro> libros = libroDao.readAll();			
				request.setAttribute("libros", libros);
				request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "No fue posible procesar la solicitud. Inténtelo nuevamente.");
				request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "No fue posible procesar la solicitud");			
			request.getRequestDispatcher("modificar.jsp").forward(request, response);
		}
	}


	/**
	 * <p>Cuando se gatilla el submit del formulario en la página <b>modificar.jsp</b> envía la petición vía <b>Post</b> con los datos 
	 * actualizados por el <b>Usuario</b> del libro solicitado, aquí se valida que los datos cumplan con estándares mínimos para que
	 * pueda ejecutarse la <b>consulta update</b> en la base de datos con datos coherentes con los atributos de la tabla libro
	 * gracias a los métodos de la Clase <b>Validacion</b>.</p>
	 * <p>Una vez que se ejecuta la <b>consulta Update</b> en la base de datos se envía vía <b>Get</b> el mensaje correspondiente de 
	 * <b>fallo</b> o  de <b>éxito</b> para que se despliegue la <b>alerta</b> en la página <b>modificar.jsp</b> o <b>listaLibros.jsp</b>
	 * respectivamente.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		//Se recibe la acción Actualizar y los datos actualizados del libro desde el formulario en la página modificar.jsp.
		String accion = request.getParameter("accion");
		
		String nombre_libro = request.getParameter("nombre_libro");
		String autor_libro = request.getParameter("autor_libro");
		String editorial = request.getParameter("editorial");
		String ano_publicacion_texto = request.getParameter("ano_publicacion");
		
		//Se valida cada campo con los métodos de la Clase Validacion
		if (!Validacion.validarNombreLibro(nombre_libro)) {
			request.setAttribute("error", "El Nombre del Libro ingresado no es válido.");
			request.getRequestDispatcher("modificar.jsp").forward(request, response);
			return;
		}
		
		if (!Validacion.validarAutorLibro(autor_libro)) {
			request.setAttribute("error", "El Nombre del Autor del Libro ingresado no es válido.");
			request.getRequestDispatcher("modificar.jsp").forward(request, response);
			return;
		}
		
		if (!Validacion.validarEditorial(editorial)) {
			request.setAttribute("error", "El Nombre de la Editorial del Libro ingresado no es válido.");
			request.getRequestDispatcher("modificar.jsp").forward(request, response);
			return;
		}
		
		try {
			int ano_publicacion = Integer.parseInt(ano_publicacion_texto);
			
			if (!Validacion.validarAnoPublicacion(ano_publicacion)) {
				request.setAttribute("error", "El Año de Publicación del Libro ingresado no es válido.");
				request.getRequestDispatcher("modificar.jsp").forward(request, response);
				return;
			}
			
			Libro l = new Libro(nombre_libro, autor_libro, editorial, ano_publicacion);
			if ("actualizar".equals(accion)) {
				String idTexto = request.getParameter("id");
				int id = Integer.parseInt(idTexto);
				l.setId_libro(id);
				boolean actualizado = libroDao.update(l);//Se ejecuta el Update en la base de datos.
				
				if (!actualizado) {//En caso de error se redirecciona al formulario en la página modificar.jsp con el mensaje de error.
					request.setAttribute("error", "No fue posible actualizar los datos del Libro");
					request.setAttribute("libro", l);
					request.getRequestDispatcher("modificar.jsp").forward(request, response);
					return;
				}
				//En caso de éxito se envía el mensaje vía Get para que se gatille la alerta en la página listaLibros.jsp.
				response.sendRedirect(request.getContextPath() + "/ModificarLibro?mensaje=actualizado");
				return;
			}
			request.setAttribute("libro", l);
			request.getRequestDispatcher("listaLibros.jsp").forward(request, response);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "Año de publicación del Libro debe ser un valor numéricos");
			request.getRequestDispatcher("modificar.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "No fue posible registrar la atención del paciente. Revise los datos ingresados en el formulario e inténtelo nuevamente.");
			request.getRequestDispatcher("modificar.jsp").forward(request, response);
		}
	}

}
