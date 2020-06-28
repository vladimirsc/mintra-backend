package pe.gob.mtpe.sivice.externo.core.accesodatos.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBS_ACUERDOS")
public class Acuerdos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBS_ACUERDOS") 
	@SequenceGenerator(name="SEQ_TBS_ACUERDOS",sequenceName="DB_TRAMITE.SEQ_TBS_ACUERDOS", allocationSize=1) 
	@Column(name = "ACUERDO_ID_PK")
	private Long aCuerdoidpk;
 
	@ManyToOne
	@JoinColumn(name = "ACTA_FK",nullable = false, insertable = true, updatable = true)
	private Actas acta;
	
	@ManyToOne
	@JoinColumn(name = "ENTIDAD_FK",nullable = false, insertable = true, updatable = true)
	private Entidades entidad;

	@Column(name = "V_DESACUERDO")
	private String vDesacuerdo;

	@Column(name = "D_FECATENCION")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecatencion;

	@Column(name = "D_FECREACION")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecreacion;

	@Column(name = "C_FLGELIMINADO", length = 1)
	private String cFlgeliminado;
	
	@Column(name = "D_FECELIMINA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecelimina;

	@Column(name = "N_USUREG")
	private Long nUsureg;

	@Column(name = "D_FECREG")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecreg;

	@Column(name = "N_USUMODIFICA")
	private Long nUsumodifica;

	@Column(name = "D_FECMODIFICA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecmodifica;
	
	@Column(name = "V_RESPONSABLE")
	private String vResponsable;
	
	private transient String  vCodigoSesion;
	private transient Long    nTipoSesion;
	private transient String  vfechaInicio;
	private transient String  vfechafin;

	public Acuerdos() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getaCuerdoidpk() {
		return aCuerdoidpk;
	}

	public void setaCuerdoidpk(Long aCuerdoidpk) {
		this.aCuerdoidpk = aCuerdoidpk;
	}

	public Actas getActa() {
		return acta;
	}

	public void setActa(Actas acta) {
		this.acta = acta;
	}

	public Entidades getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidades entidad) {
		this.entidad = entidad;
	}

	public String getvDesacuerdo() {
		return vDesacuerdo;
	}

	public void setvDesacuerdo(String vDesacuerdo) {
		this.vDesacuerdo = vDesacuerdo;
	}

	public Date getdFecatencion() {
		return dFecatencion;
	}

	public void setdFecatencion(Date dFecatencion) {
		this.dFecatencion = dFecatencion;
	}

	public Date getdFecreacion() {
		return dFecreacion;
	}

	public void setdFecreacion(Date dFecreacion) {
		this.dFecreacion = dFecreacion;
	}

	public String getcFlgeliminado() {
		return cFlgeliminado;
	}

	public void setcFlgeliminado(String cFlgeliminado) {
		this.cFlgeliminado = cFlgeliminado;
	}

	public Date getdFecelimina() {
		return dFecelimina;
	}

	public void setdFecelimina(Date dFecelimina) {
		this.dFecelimina = dFecelimina;
	}

	public Long getnUsureg() {
		return nUsureg;
	}

	public void setnUsureg(Long nUsureg) {
		this.nUsureg = nUsureg;
	}

	public Date getdFecreg() {
		return dFecreg;
	}

	public void setdFecreg(Date dFecreg) {
		this.dFecreg = dFecreg;
	}

	public Long getnUsumodifica() {
		return nUsumodifica;
	}

	public void setnUsumodifica(Long nUsumodifica) {
		this.nUsumodifica = nUsumodifica;
	}

	public Date getdFecmodifica() {
		return dFecmodifica;
	}

	public void setdFecmodifica(Date dFecmodifica) {
		this.dFecmodifica = dFecmodifica;
	}

	public String getvResponsable() {
		return vResponsable;
	}

	public void setvResponsable(String vResponsable) {
		this.vResponsable = vResponsable;
	}

	public String getvCodigoSesion() {
		return vCodigoSesion;
	}

	public void setvCodigoSesion(String vCodigoSesion) {
		this.vCodigoSesion = vCodigoSesion;
	}

	public Long getnTipoSesion() {
		return nTipoSesion;
	}

	public void setnTipoSesion(Long nTipoSesion) {
		this.nTipoSesion = nTipoSesion;
	}

	public String getVfechaInicio() {
		return vfechaInicio;
	}

	public void setVfechaInicio(String vfechaInicio) {
		this.vfechaInicio = vfechaInicio;
	}

	public String getVfechafin() {
		return vfechafin;
	}

	public void setVfechafin(String vfechafin) {
		this.vfechafin = vfechafin;
	}

	 
	 
}
