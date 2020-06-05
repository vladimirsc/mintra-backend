package pe.gob.mtpe.sivice.externo.core.accesodatos.entity;

import java.io.Serializable;

public class Archivos implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String correlativo;
	private String nombre;
	private String tipo;
	private String extension;
	private String ubicacion;
	private String nombreExtension;
	private String rutaCompleta;
	private String rutaNombreArchivo;
	private boolean verificarCarga;
	private boolean verificarUbicacion;
	private String mensaje;
	
	public Archivos() {
		 
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	 public String getNombreExtension() {
		return nombreExtension;
	}

	public void setNombreExtension(String nombreExtension) {
		this.nombreExtension = nombreExtension;
	}

	public String getRutaCompleta() {
		return rutaCompleta;
	}

	public void setRutaCompleta(String rutaCompleta) {
		this.rutaCompleta = rutaCompleta;
	}

	public String getRutaNombreArchivo() {
		return rutaNombreArchivo;
	}

	public void setRutaNombreArchivo(String rutaNombreArchivo) {
		this.rutaNombreArchivo = rutaNombreArchivo;
	}

	public boolean isVerificarCarga() {
		return verificarCarga;
	}

	public void setVerificarCarga(boolean verificarCarga) {
		this.verificarCarga = verificarCarga;
	}

	public boolean isVerificarUbicacion() {
		return verificarUbicacion;
	}

	public void setVerificarUbicacion(boolean verificarUbicacion) {
		this.verificarUbicacion = verificarUbicacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
