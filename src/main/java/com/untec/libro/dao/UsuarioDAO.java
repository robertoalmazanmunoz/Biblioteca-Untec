package com.untec.libro.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.untec.libro.model.Usuario;

/**
 * <h2>Case UsuarioDAO</h2>
 * Clase que permite ejecutar consultas sobre la <b>tabla usuario</b> de la Base de Datos.
 * @since 1.0
 * @author Roberto Almazán (Untec)
 */
public class UsuarioDAO {
	
	//Definir un objeto que nos permita conectar con la Base de Datos.
	private static final Conexion CONEXION = Conexion.getEstado();
	
	//Definir un objeto que nos permita preparar las consultas y luego ejecutarlas.
	private PreparedStatement ps;
	
	//Definir un objeto que nos permita recibir los datos obtenidos desde un consula (SELECT).
	private ResultSet rs;
	
	/**
	 * Consulta para INSERTAR un Usuario.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String INSERT_SQL = 
			"insert into usuario (usuario, contrasena, rol) "
			+ "values (?,?,?)";
	
	/**
	 * Método que nos permitá registrar un nuevo Usuario en la base de datos.
	 * 
	 * @param usuario Usuario
	 * @return boolean
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public boolean create(Usuario usuario) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(INSERT_SQL);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getContrasena());
			ps.setString(3, usuario.getRol());
			int filas = ps.executeUpdate();
			return filas > 0;
		} finally {
			CONEXION.cerrarConexion();
		}		
	}
	
	/**
	 * Consulta para leer todos los registros de la tabla usuario de la base de datos.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_ALL_SQL =
			"select id, usuario, contrasena, rol "
			+ "from usuario order by id desc";
	
	/**
	 * Método que permite obtener todos los registros de la tabla usuario.
	 * 
	 * @return List<Usuario>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Usuario> readAll() throws SQLException {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_ALL_SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = convertirUsuario(rs);
				usuarios.add(u);
			}
			return usuarios;
			
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener un usuario por id.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_ID_SQL = 
			"select id, usuario, contrasena, rol "
			+ "from usuario where id = ?";
	
	/**
	 * Método que permite obtener un usuario por su id.
	 * 
	 * @param id del usuario que se desea buscar
	 * @return Libro
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public Usuario readById(int id) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_ID_SQL);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Usuario u = null;
			if (rs.next()) {
				u = convertirUsuario(rs);
			}
			return u;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener todos los registros de un usuario por usuario.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_USUARIO_SQL =
			"select id, usuario, contrasena, rol "
					+ "from usuario where usuario LIKE ?"
					+ "order by id desc";
	
	/**
	 * Método que permite tener todos los usuarios por su usuario.
	 * 
	 * @param nombre de usuario del usuario que se quiere buscar
	 * @return Lista<Usuario>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Usuario> readByUsuario(String usuario) throws SQLException {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_USUARIO_SQL);
			ps.setString(1, "%" + usuario + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = convertirUsuario(rs);
				usuarios.add(u);
			}
			return usuarios;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener todos los registros de un usuario por rol.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_ROL_SQL =
			"select id, usuario, contrasena, rol "
					+ "from usuario where rol LIKE ?"
					+ "order by id desc";
	
	/**
	 * Método que permite tener todos los usuarios por su rol.
	 * 
	 * @param rol del usuario que se quiere buscar
	 * @return Lista<Usuario>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Usuario> readByRol(String rol) throws SQLException {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_ROL_SQL);
			ps.setString(1, "%" + rol + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = convertirUsuario(rs);
				usuarios.add(u);
			}
			return usuarios;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para obtener todos los registros de un usuario según el criterio.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String SELECT_BY_CRITERIO_SQL =
			"select id, usuario, contrasena, rol "
					+ "from usuario where usuario LIKE ? or rol like ?"
					+ "order by id desc";
	
	/**
	 * Método que permite tener todos los usuarios según un criterio.
	 * 
	 * @param criterio por el cual se quiere buscar un usuario
	 * @return Lista<Usuario>
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public List<Usuario> readByCriterio(String criterio) throws SQLException {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			ps = CONEXION.getConexion().prepareStatement(SELECT_BY_CRITERIO_SQL);
			String parametro = "%" + criterio + "%";
			ps.setString(1, parametro);
			ps.setString(2, parametro);
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = convertirUsuario(rs);
				usuarios.add(u);
			}
			return usuarios;
		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	/**
	 * Consulta para actualizar los datos de un usuario por id.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String UPDATE_SQL = 
			"update usuario "
			+ "set usuario = ?, contrasena = ?, rol = ? "
			+ "where id = ?";
	
	/**
	 * Método para actualizar los datos de un usuario por su id.
	 * 
	 * @param usuario que se desea actualizar sus atributos
	 * @return verdadero si se logró actualizar el registro o falso si ocurrió un error
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	public boolean update(Usuario usuario) throws SQLException {
        try {
        	ps = CONEXION.getConexion().prepareStatement(UPDATE_SQL);
        	ps.setString(1,usuario.getUsuario());
        	ps.setString(2,usuario.getContrasena());
        	ps.setString(3,usuario.getRol());
        	ps.setInt(4,usuario.getId());
        	int filas = ps.executeUpdate();
            return filas > 0;
        } finally {
        	CONEXION.cerrarConexion();
		}
    }
	
	/**
	 * Consulta para eliminar un usuario de la base de datos por id.
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private static final String DELETE_SQL = 
			"delete from usuario where id = ?";
	
	/**
	 * Método que permite eliminar un usuario de la base de datos por su id.
	 * 
	 * @param id del usuario que se desea eliminar
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
	 * Método que permite crear un objeto Usuario a partir de los datos obtenidos al ejecutar una consulta.
	 * 
	 * @param rs ResultSet
	 * @return Usuario
	 * @throws SQLException
	 * @since 1.0
	 * @author Roberto Almazán (Untec)
	 */
	private Usuario convertirUsuario(ResultSet rs) throws SQLException {
		Usuario u = new Usuario();
		u.setId(rs.getInt("id"));
		u.setUsuario(rs.getString("usuario"));
		u.setContrasena(rs.getString("contrasena"));
		u.setRol(rs.getString("rol"));
		
		return u;
	}

}