package com.untec.libro.model;

/**
 * <h2>Clase Libro</h2>
 * <p>La clase <b>Libro</b> ha sido crada como un simil de la tabla <b>libro en la Base de Datos</b>, de manera de poder manipular <b>objetos 
 * libro</b> en nuestra <b>aplicación Java y web</b> que tengan el mismo formato que la Base de Datos, podamos <b>manipularlos temporalmente</b>
 * y una vez que acabemos de manipuarlos podemos <b>guardarlos</b> nuevamente en la Base de Datos.</p>
 * <h3>Atributos de la Clase</h3>
 * <ul>
 * 	<li>int id_libro</li>
 * 	<li>String nombre_libro</li>
 * 	<li>String autor_libro</li>
 * 	<li>String editorial</li>
 * 	<li>int ano_publicacion</li>
 * </ul>
 * <h3>Constructores de la Clase</h3>
 * <ul>
 * 	<li>public Libro()</li>
 * 	<li>public Libro(String nombre_libro, String autor_libro, String editorial, int ano_publicacion)</li>
 * </ul>
 * <h3>Getters y Setters de la Clase</h3>
 * <table>
 * 	<th>Getter</th>
 * 	<th>Setter</th>
 * 	<tr>
 * 		<td>int getId_libro()</td>
 * 		<td>setId_libro(int id_libro)</td>
 * 	</tr>
 * 	<tr>
 * 		<td>String getNombre_libro()</td>
 * 		<td>setNombre_libro(String nombre_libro)</td>
 * 	</tr>
 * 	<tr>
 * 		<td>String getAutor_libro()</td>
 * 		<td>setAutor_libro(String autor_libro)</td>
 * 	</tr>
 * 	<tr>
 * 		<td>String getEditorial()</td>
 * 		<td>setEditorial(String editorial)</td>
 * 	</tr>
 * 	<tr>
 * 		<td>int getAno_publicacion()</td>
 * 		<td>setAno_publicacion(int ano_publicacion)</td>
 * 	</tr>
 * </table>
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class Libro {
	
	/**
	 * Atributos de la clase Libro, es un simil de la tabla libro de la Base de Datos.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private int id_libro;
	private String nombre_libro;
    private String autor_libro;
    private String editorial;
    private int ano_publicacion;
    
    //Constructores de la clase Libro.
    /**
     * Constructor <b>vacío</b> de la Clase Libro
     * @since 1.0
	 * @author Roberto Almazán (Untec)
     */
    public Libro() {
    	
    }

    /**
     * Constructor con <b>parámetros</b> de la Clase Libro.
     * @param nombre_libro String
     * @param autor_libro String
     * @param editorial Atring
     * @param ano_publicacion int
     * @since 1.0
	 * @author Roberto Almazán (Untec)
     */
	public Libro(String nombre_libro, String autor_libro, String editorial, int ano_publicacion) {
		this.nombre_libro = nombre_libro;
		this.autor_libro = autor_libro;
		this.editorial = editorial;
		this.ano_publicacion = ano_publicacion;
	}

	//Getters y Setters de la Clase Libro.
	/**
	 * Método que permite obtener el id de un objeto libro.
	 * @return id int
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public int getId_libro() {
		return id_libro;
	}

	/**
	 * Método que permite asignar un id a un objeto libro.
	 * @param id_libro int
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}
	
	/**
	 * Método que permite obtener el nombre de un objeto libro.
	 * @return nombre String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public String getNombre_libro() {
		return nombre_libro;
	}

	/**
	 * Método que permite asignar un nombre a un objeto libro.
	 * @param nombre_libro String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setNombre_libro(String nombre_libro) {
		this.nombre_libro = nombre_libro;
	}

	/**
	 * Método que permite obtener el autor de un objeto libro.
	 * @return autor String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public String getAutor_libro() {
		return autor_libro;
	}

	/**
	 * Método que permite asignar un autor a un objeto libro.
	 * @param autor_libro String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setAutor_libro(String autor_libro) {
		this.autor_libro = autor_libro;
	}

	/**
	 * Método que permite obtener la editorial de un objeto libro.
	 * @return editorial String
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public String getEditorial() {
		return editorial;
	}

	/**
	 * Método que permite asignar una editorial a un objeto libro.
	 * @param id_libro int
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	/**
	 * Método que permite obtener el año de publicación de un objeto libro.
	 * @return año int
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public int getAno_publicacion() {
		return ano_publicacion;
	}

	/**
	 * Método que permite asignar un año de publicación a un objeto libro.
	 * @param ano_publicacion int
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public void setAno_publicacion(int ano_publicacion) {
		this.ano_publicacion = ano_publicacion;
	}

}
