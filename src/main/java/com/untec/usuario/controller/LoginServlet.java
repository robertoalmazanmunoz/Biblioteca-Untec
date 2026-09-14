package com.untec.usuario.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.untec.libro.dao.UsuarioDAO;
import com.untec.libro.model.Usuario;

/**
 * <h2>Servlet LoginServlet</h2>
 * <p><b>Servlet</b> que maneja las llama post y get cuando se va a <b>iniciar sesión</b> en nuestra aplicación.</p>
 * <p>Contiene la <b>lógica de negocio</b> para que un <b>Usuario</b> pueda <b>autenticarse</b> correctamente y se le designe su <b>rol</b>
 * según como esté registrado en la Base de Datos.</p>
 * <h2>url-pattern</h2>
 * <p>El servlet se llama con el nombre /Login</p>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Conexión a la tabla usuario de la Base de Datos.
	private UsuarioDAO usuarioDao;
	private List<Usuario> usuarios;

	
	public void init()  {
		usuarioDao = new UsuarioDAO();
	}
    
	/**
	 * Se redirecciona a la página <b>index.jsp</b> si se recibe solicitug vía <b>Get</b> por un usuario con una sesión activa dentro de
	 * la aplicación
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("usuario") != null) {
        	response.sendRedirect(request.getContextPath() + "/index.jsp");
        	return;
        }
    	
    	request.getRequestDispatcher("login.jsp").forward(request,response);
    }
	
	/**
	 * <p>Método que recibe las peticiones vía <b>Post</b> del formulario de la página <b>login.jsp</b> y consulta en la Base de Datos si
	 * el <b>Usuario existe</b> en la Base de Datos, de existir, compara la <b>Contraseña</b> registrada en la Base de Datos con la ingresada
	 * en el formulario, de coincidir entrega el <b>Usuario y el Rol</b> a la página <b>menu.jsp</b> y da acceso al contenido de la aplicación
	 * según los <b>permisos</b> que le corresponden al <b>rol</b> detectado.</p>
	 * <p>De lo contrario el <b>Usuario no autenticado</b>será redireccionado a la página <b>index.jsp</b> y solo tendrá acceso a las 
	 * <b>páginas públicas</b> de nuestra aplicación.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		//Recibe los datos del formulario
		String usuario = request.getParameter("usuario");
		String contraseña = request.getParameter("contraseña");
		
		Usuario usuarioEncontrado = null;
		usuarios = null;
		
		try {
			
			//nos conectamos a la base de dato para buscar si existe el usuario
			usuarios = usuarioDao.readByUsuario(usuario);
			
			// BUSCAR USUARIO
	        for (Usuario u : usuarios) {//Dentro de la lista de usuarios encontrados verificamos si alguno coincide con la contraseña ingresada
	            if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contraseña)) {
	                usuarioEncontrado = u;
	                break;
	            }
	        }
			
	        //LOGIN CORRECTO
			if (usuarioEncontrado !=null) {//Se consede el acceso y se asigna el rol que corresponde al usuario autenticado
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usuarioEncontrado);
				session.setAttribute("rol", usuarioEncontrado.getRol());
				String urlSolicitada = (String) session.getAttribute("urlSolicitada");
	            session.removeAttribute("urlSolicitada");
	            
	            if (urlSolicitada != null && !urlSolicitada.isBlank()) {
	                response.sendRedirect(urlSolicitada);
	            } else {
	                response.sendRedirect(request.getContextPath() + "/menu.jsp");//Se redirecciona al usuario autenticado al menú
	            }
	            return;
			} 
			
			// LOGIN INCORRECTO
	        request.setAttribute("error", "Usuario o contraseña incorrectos.");
	        request.getRequestDispatcher("login.jsp").forward(request,response);//Se mantiene al usuario en la página de login y se muestra un error
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "No fue posible procesar la solicitud.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}