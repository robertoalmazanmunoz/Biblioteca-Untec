package com.untec.libro.util;

public class Validacion {
	
	/**
     * Validar Nombre de Libro
     * 
     * @param nombre del Libro
     * @return
     */
    public static boolean validarNombreLibro(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        String valor = nombre.trim();

        if (valor.length() < 2 || valor.length() > 50) {
            return false;
        }

        return valor.matches("^[\\p{L}\\d]+(?:[ .'-][\\p{L}\\d]+)*$");
    }
    
    /**
     * Validar Autor de Libro
     * 
     * @param nombre del Autor del Libro
     * @return
     */
    public static boolean validarAutorLibro(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        String valor = nombre.trim();

        if (valor.length() < 2 || valor.length() > 50) {
            return false;
        }

        return valor.matches("[\\p{L}ÁÉÍÓÚáéíóúÑñÜü]+(?:[ '-][\\p{L}ÁÉÍÓÚáéíóúÑñÜü]+)*");
    }
    
    /**
     * Validar Editorial del Libro
     * 
     * @param nombre de la Editorial del libro
     * @return
     */
    public static boolean validarEditorial(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        String valor = nombre.trim();

        if (valor.length() < 2 || valor.length() > 30) {
            return false;
        }

        return valor.matches("^[\\p{L}\\d]+(?:[ .'-][\\p{L}\\d]+)*$");
    }
    
    /**
     * Validar Año de Publicación del Libro
     * 
     * @param ano de Publicación del Libro
     * @return
     */
    public static boolean validarAnoPublicacion(double ano) {
        return ano > 0 && ano <= 2100;
    }

}
