package pe.gob.mtpe.sivice.externo.core.accesodatos.entity;

import java.io.Serializable;

public class AsistenciaConsejeros  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idAsistencia;
	private String asistio;
	private String tipoDocumento;
	private String numeroDocumento;
    private String apellidosNombre;
    private String entidad;
    private String horaEntrada;
    private String horaSalida;
    
	public AsistenciaConsejeros() {
		 
	}

	public Long getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(Long idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	 
	public String getAsistio() {
		return asistio;
	}

	public void setAsistio(String asistio) {
		this.asistio = asistio;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getApellidosNombre() {
		return apellidosNombre;
	}

	public void setApellidosNombre(String apellidosNombre) {
		this.apellidosNombre = apellidosNombre;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	 
	
	
	
    
}
