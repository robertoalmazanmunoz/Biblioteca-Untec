package com.untec.libro.model;

/**
 * <h2>Clase Usuario</h2>
 * <p>La clase <b>Usuario</b> ha sido crada como un simil de la tabla <b>usuario en la Base de Datos</b>, de manera de poder manipular <b>objetos 
 * usuario</b> en nuestra <b>aplicación Java y web</b> que tengan el mismo formato que la Base de Datos, podamos <b>manipularlos temporalmente</b>
 * y una vez que acabemos de manipuarlos podemos <b>guardarlos</b> nuevamente en la Base de Datos.</p>
 * <h3>Atributos de la Clase</h3>
 * <ul>
 * 	<li>int id</li>
 * 	<li>String usuario</li>
 * 	<li>String contrasena</li>
 * 	<li>String rol</li>
 * </ul>
 * <h3>Constructores de la Clase</h3>
 * <ul>
 * 	<li>public Usuario()</li>
 * 	<li>public Usuario(String usuario, String contrasena, String rol)</li>
 * </ul>
 * <h3>Getters y Setters de la Clase</h3>
 * <table>
 * 	<th>Getter</th>
 * 	<th>Setter</th>
 * 	<tr>
 * 		<td>int getId()</td>
 * 		<td>setId(int id)</td>
 * 	</tr>
 * 	<tr>
 * 		<td>String getUsuario()</td>
 * 		<td>setUsuario(String usuario)</td>
 * 	</tr>
 * 	<tr>
 * 		<td>String getContrasena()</td>
 * 		<td>setContrasena(String contrasena)</td>
 * 	</tr>
 * 	<tr>
 * 		<td>String getRol()</td>
 * 		<td>setRol(String rol)</td>
 * 	</tr>
 * </table>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class Usuario {
	
	/**
	 * Atributos de la Clase Usuario, es un simil de la tabla usuario de la base de datos.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private int id;
    private String usuario;
    private String contrasena;
    private String rol;
    
    //Constructores de la Clase Usuario.
    /**
     * Constructor <b>vacío</b> de la Clase Usuario.
     * @since 1.0
	 * @author Roberto Almazán (Untec)
     */
    public Usuario() {
    	
    }

    /**
     * Constructor con <b>parámetros</b> de la Clase Usuario.
     * @param usuario String
     * @param contrasena String
     * @param rol String
     */
	public Usuario(String usuario, String contrasena, String rol) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	//Getters y Setters de la Clase Usuario.
	/**
	 * Método que permite obtener el id de un objeto usuario.
	 * @return id int
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método que permite asignar un id a un objeto usuario.
	 * @param id int
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Método que permite obtener el usuario de un objeto usuario.
	 * @return usuario String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Método que permite asignar un usuario a un objeto usuario.
	 * @param usuario String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método que permite obtener la contraseña de un objeto usuario.
	 * @return contraseña String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Método que permite asignar una contraseña a un objeto usuario.
	 * @param contrasena String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Método que permite obtener el rol de un objeto usuario.
	 * @return rol String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Método que permite asignar un rol a un objeto usuario
	 * @param rol String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

}
