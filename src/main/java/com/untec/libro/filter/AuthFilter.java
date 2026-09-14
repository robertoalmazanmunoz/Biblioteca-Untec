package com.untec.libro.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * <h2>Clase AuthFilter</h2>
 * <p>Esta clase nos permite <b>asignar</b> qué contenidos puede ver o no cada <b>Usuario</b> según su <b>Rol</b> al navegar por nuestra
 * <b>Aplicación Web</b>.</p>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
    
	/**
	 * <p>Método que se ejecuta cada vez que se <b>llama a un JSP<b> y controla los <b>permisos</b> de cada <b>Usuario</b> según su <b>Rol</b>
	 * y le da acceso o esconde ciertas estructuras, botones o JSP según corresponda</p>
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // LOGIN
        boolean loginRequest = uri.equals(contextPath + "/Login");

        // PÁGINA DE INICIO PÚBLICA
        boolean indexRequest = uri.equals(contextPath + "/") || uri.equals(contextPath + "/index.jsp");

        // RECURSOS ESTÁTICOS
        boolean recursosPublicos = uri.contains("/js/");
        
        boolean pagina404 = uri.equals(contextPath + "/404.jsp");

        // SESIÓN
        HttpSession session = httpRequest.getSession(false);
        
        boolean autenticado = session != null && session.getAttribute("usuario") != null;

        // PÁGINAS PÚBLICAS        
        if (loginRequest || indexRequest || recursosPublicos || pagina404) {
            chain.doFilter(request,response);
            return;
        }

        // USUARIO NO AUTENTICADO
        if (!autenticado) {
        	String urlSolicitada = httpRequest.getRequestURI();

            String queryString = httpRequest.getQueryString();

            if (queryString != null && !queryString.isBlank()) {
                urlSolicitada += "?" + queryString;
            }
            //Si el usuario no está autenticado se redirecciona al login
            HttpSession nuevaSession = httpRequest.getSession(true);
            nuevaSession.setAttribute("urlSolicitada", urlSolicitada);
            httpResponse.sendRedirect(contextPath + "/Login");
            return;
        }


        // AUTORIZACIÓN
        String rol = (String) session.getAttribute("rol");
        String accion = httpRequest.getParameter("accion");

        // OPERACIONES EXCLUSIVAS DE ADMIN
        //Se pueden definir las operación que un USER puede realizar
        boolean operacionAdministrativa = 
        		uri.endsWith(
                        "/modificar.jsp"//Acseso a la página modificar.jsp
                )
                ||uri.endsWith(
                        "/agregar.jsp"//Acceso a la página agregar.jsp
                )
                ||
                "editar".equals(//Acceso a generar la acción editar
                        accion
                )
                ||
                "actualizar".equals(//Acceso a generar la acción actualizar
                        accion
                )
                ||
                "eliminar".equals(//Acceso a generar la acción eliminar
                        accion
                );

        //o se pueden generar el listado de las operaciones que el USER no puede realizar
        //boolean operacionesEscritura =  "eliminar".equals(accion);
        
        
        // USER NO PUEDE ADMINISTRAR
        /*Si un usuario con Rol Usuario intenta acceder a un jsp o ejecutar las acciones designadas como operacionesAdministrativa se
         * redirecciona con una alerta de error de permiso, cualquier otro rol si tiene acceso.
         */
        if ("Usuario".equals(rol) && operacionAdministrativa) {
            httpResponse.sendRedirect(contextPath + "/BuscarLibro?mensaje=sinPermiso");
            return;
        }
        
       /* 
        if ("WRITER".equals("rol") && operacionesEscritura) {
        	httpResponse.sendRedirect(contextPath + "/paciente?mensaje=sinPermiso");
            return;
        }
		*/
        // CONTINUAR
         chain.doFilter(request,response);
    }
}