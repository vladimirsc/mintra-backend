package pe.gob.mtpe.sivice.externo.core.accesodatos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBD_ASISTENCIA_ARCHIVO")
public class AsistenciasArchivos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBD_ASISTENCIA_ARCHIVO")
	@SequenceGenerator(name = "SEQ_TBD_ASISTENCIA_ARCHIVO", sequenceName = "DB_TRAMITE.SEQ_TBD_ASISTENCIA_ARCHIVO", allocationSize = 1)
	@Column(name = "ASISTE_ARCHIVO_ID_PK")
	private Long aSistearchivoidpk;

	@Column(name = "SESION_FK")
	private Long sEsionfk;

	@Column(name = "NOMBRE_ARCHIVO")
	private String nOmbrearchivo;

	@Column(name = "EXTENSION_ARCHIVO")
	private String eXtensionarchivo;

	@Column(name = "UBICACION_ARCHIVO ")
	private String uBicacionarchivo;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	@Column(name = "D_FECHAREGISTRO")
	private Date dFecharegistro;

	@Column(name = "V_FLGELIMINADO", length = 1)
	private String vFlgeliminado;
	
	public AsistenciasArchivos() {
		 
	}

	public Long getaSistearchivoidpk() {
		return aSistearchivoidpk;
	}

	public void setaSistearchivoidpk(Long aSistearchivoidpk) {
		this.aSistearchivoidpk = aSistearchivoidpk;
	}

	public Long getsEsionfk() {
		return sEsionfk;
	}

	public void setsEsionfk(Long sEsionfk) {
		this.sEsionfk = sEsionfk;
	}

	public String getnOmbrearchivo() {
		return nOmbrearchivo;
	}

	public void setnOmbrearchivo(String nOmbrearchivo) {
		this.nOmbrearchivo = nOmbrearchivo;
	}

	public String geteXtensionarchivo() {
		return eXtensionarchivo;
	}

	public void seteXtensionarchivo(String eXtensionarchivo) {
		this.eXtensionarchivo = eXtensionarchivo;
	}

	public String getuBicacionarchivo() {
		return uBicacionarchivo;
	}

	public void setuBicacionarchivo(String uBicacionarchivo) {
		this.uBicacionarchivo = uBicacionarchivo;
	}

	public Date getdFecharegistro() {
		return dFecharegistro;
	}

	public void setdFecharegistro(Date dFecharegistro) {
		this.dFecharegistro = dFecharegistro;
	}

	public String getvFlgeliminado() {
		return vFlgeliminado;
	}

	public void setvFlgeliminado(String vFlgeliminado) {
		this.vFlgeliminado = vFlgeliminado;
	}
	


}
