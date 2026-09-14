package com.untec.libro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.untec.libro.model.Libro;

/**
 * <h2>Clase LibroDAO</h2>
 * Clase que permite ejecutar consultas sobre la <b>tabla libro</b> de la Base de Datos.
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class LibroDAO {
	
	//Definir un objeto que nos permita conectar con la Base de Datos
	private static final Conexion CONEXION = Conexion.getEstado();
	
	//Definir un objeto que nos permita preparar las consultas y luego ejecutarlas.
	private PreparedStatement ps;
	
	//Definir un objeto que nos permita recibir los datos obtenidos desde un consula (SELECT).
	private ResultSet rs;
	
	/**
	 * Consulta para INSERTAR un Libro.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String INSERT_SQL = 
			"insert into libro (nombre_libro, autor_libro, editorial, ano_publicacion) "
			+ "values (?,?,?,?)";
	
	/**
	 * Método que nos permitá registrar un nuevo libro en la base de datos.
	 * 
	 * @param libro Libro
	 * @return boolean
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public boolean create(Libro libro) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(INSERT_SQL);
			ps.setString(1, libro.getNombre_libro());
			ps.setString(2, libro.getAutor_libro());
			ps.setString(3, libro.getEditorial());
			ps.setInt(4, libro.getAno_publicacion());
			int filas = ps.executeUpdate();
			return filas > 0;
		} finally {
			CONEXION.cerrarConexion();
		}		
	}
	
	/**
	 * Consulta para leer todos los registros de la tabla libro de la base de datos.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_ALL_SQL =
			"select id_libro, nombre_libro, autor_libro, editorial, ano_publicacion "
			+ "from libro order by id_libro desc";
	
	/**
	 * Método que permite obtener todos los registros de la tabla libro.
	 * 
	 * @return List<Libro>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Libro> readAll() throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_ALL_SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				Libro l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
			
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener un libro por id_libro.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_ID_SQL = 
			"select id_libro, nombre_libro, autor_libro, editorial, ano_publicacion "
			+ "from libro where id_libro = ?";
	
	/**
	 * Método que permite obtener un libro por su id_libro.
	 * 
	 * @param id del libro que se desea buscar
	 * @return Libro
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public Libro readById(int id) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_ID_SQL);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Libro l = null;
			if (rs.next()) {
				l = convertirLibro(rs);
			}
			return l;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener todos los registros de un libro por nombre_libro.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_NOMBRE_LIBRO_SQL =
			"select id_libro, nombre_libro, autor_libro, editorial, ano_publicacion "
					+ "from libro where nombre_libro LIKE ?"
					+ "order by id_libro desc";
	
	/**
	 * Método que permite tener todos los libros por su nombre_libro.
	 * 
	 * @param nombre del libro que se quiere buscar
	 * @return Lista<Libro>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Libro> readByNombreLibro(String nombre) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_NOMBRE_LIBRO_SQL);
			ps.setString(1, "%" + nombre + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Libro l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener todos los registros de un libro por autor_libro.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_AUTOR_LIBRO_SQL =
			"select id_libro, nombre_libro, autor_libro, editorial, ano_publicacion "
					+ "from libro where autor_libro LIKE ?"
					+ "order by id_libro desc";
	
	/**
	 * Método que permite tener todos los libros por su autor_libro.
	 * 
	 * @param autor del libro que se quiere buscar
	 * @return Lista<Libro>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Libro> readByAutorLibro(String autor) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_AUTOR_LIBRO_SQL);
			ps.setString(1, "%" + autor + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Libro l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener todos los registros de un libro por editorial.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_EDITORIAL_SQL =
			"select id_libro, nombre_libro, autor_libro, editorial, ano_publicacion "
					+ "from libro where editorial LIKE ?"
					+ "order by id_libro desc";
	
	/**
	 * Método que permite tener todos los libros por su editorial.
	 * 
	 * @param editorial del libro que se quiere buscar
	 * @return Lista<Libro>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Libro> readByEditorialLibro(String editorial) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_EDITORIAL_SQL);
			ps.setString(1, "%" + editorial + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Libro l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener todos los libros por ano_publicacion.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_ANO_PUBLICACION_SQL = 
			"select id_libro, nombre_libro, autor_libro, editorial, ano_publicacion "
			+ "from libro where ano_publicacion = ?"
			+ "order by id_libro desc";
	
	/**
	 * Método que permite obtener un libro por su ano_publicacion.
	 * 
	 * @param año del libro que se desea buscar
	 * @return Libro
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Libro> readByAnoPublicacion(int ano) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_ANO_PUBLICACION_SQL);
			ps.setInt(1, ano);
			rs = ps.executeQuery();
			while (rs.next()) {
				Libro l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener todos los registros de un libro según el criterio.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_CRITERIO_SQL =
			"select id_libro, nombre_libro, autor_libro, editorial, ano_publicacion "
					+ "from libro where nombre_libro LIKE ? or autor_libro like ? or editorial LIKE ?"
					+ "order by id_libro desc";
	
	/**
	 * Método que permite tener todos los libros según un criterio.
	 * 
	 * @param criterio por el cual se quiere buscar un libro
	 * @return Lista<Libro>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Libro> readByCriterio(String criterio) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_CRITERIO_SQL);
			String parametro = "%" + criterio + "%";
			ps.setString(1, parametro);
			ps.setString(2, parametro);
			ps.setString(3, parametro);
			rs = ps.executeQuery();
			while (rs.next()) {
				Libro l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para actualizar los datos de un libro por id_libro.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String UPDATE_SQL = 
			"update libro "
			+ "set nombre_libro = ?, autor_libro = ?, editorial = ?, ano_publicacion = ? "
			+ "where id_libro = ?";
	
	/**
	 * Método para actualizar los datos de un libro por su id_libro.
	 * 
	 * @param libro que se desea actualizar sus atributos
	 * @return verdadero si se logró actualizar el registro o falso si ocurrió un error
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public boolean update(Libro libro) throws SQLException {
        try {
        	ps = CONEXION.getConexion().prepareStatement(UPDATE_SQL);
        	ps.setString(1,libro.getNombre_libro());
        	ps.setString(2,libro.getAutor_libro());
        	ps.setString(3,libro.getEditorial());
        	ps.setInt(4,libro.getAno_publicacion());
        	ps.setInt(5,libro.getId_libro());
        	int filas = ps.executeUpdate();
            return filas > 0;
        } finally {
        	CONEXION.cerrarConexion();
		}
    }
	
	/**
	 * Consulta para eliminar un libro de la base de datos por id_libro.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String DELETE_SQL = 
			"delete from libro where id_libro = ?";
	
	/**
	 * Método que permite eliminar un libro de la base de datos por su id_libro.
	 * 
	 * @param id del libro que se desea eliminar
	 * @return verdadero si se pudo eliminar el registro o falso si ocurrió ub error
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public boolean delete(int id) throws SQLException {
        try {
        	ps = CONEXION.getConexion().prepareStatement(DELETE_SQL);
            ps.setInt(1,id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } finally {
        	CONEXION.cerrarConexion();
		}
    }
	
	/**
	 * Método que permite crear un objeto Libro a partir de los datos obtenidos al ejecutar una consulta.
	 * 
	 * @param rs ResultSet
	 * @return Libro
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private Libro convertirLibro(ResultSet rs) throws SQLException {
		Libro l = new Libro();
		l.setId_libro(rs.getInt("id_libro"));
		l.setNombre_libro(rs.getString("nombre_libro"));
		l.setAutor_libro(rs.getString("autor_libro"));
		l.setEditorial(rs.getString("editorial"));
		l.setAno_publicacion(rs.getInt("ano_publicacion"));
		return l;
	}

}
