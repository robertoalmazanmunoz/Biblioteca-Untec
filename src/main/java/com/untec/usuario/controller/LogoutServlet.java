package com.untec.usuario.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * <h2>Servlet LogoutServlet</h2>
 * <p><b>Servlet</b> que maneja las llama get cuando se va a <b>cerrar sesión</b> en nuestra aplicación.</p>
 * <p>El Servlet <b>elimina</b> la sesión si se presiona el botón <b>cerrar sesión</b> y redirecciona al <b>Usuario</b> a la página
 * <b>index.jsp</b> pero sin esión autenticada, por o que solo puede navegar por las <b>páginas públicas</b> de nuestra aplicación.</p>
 * <h2>url-pattern</h2>
 * <p>El servlet se llama con el nombre /Login</p>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	/**
	 * <p>Método que se activa al presionar el botón <b>cerrar sesión</b>, elimina la sesión activa quita los permisos al <b>Usuario</b> 
	 * de navegar por las <b>páginas restringidas por rol</b>.</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Si hay una sesión activa la elimina.
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();		
		}

		//Redireccionamos al Usuario al Index sin una sesión activa ni rol definido.
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
}