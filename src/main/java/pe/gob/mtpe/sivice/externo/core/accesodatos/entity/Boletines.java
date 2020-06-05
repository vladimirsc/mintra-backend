package pe.gob.mtpe.sivice.externo.core.accesodatos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBX_BOLETINES",schema="DB_TRAMITE")
public class Boletines implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBX_BOLETINES") 
	@SequenceGenerator(name="SEQ_TBX_BOLETINES",sequenceName="DB_TRAMITE.SEQ_TBX_BOLETINES", allocationSize=1) 
	@Column(name = "BOLETIN_ID_PK")
	private Long bOletinidpk;

	@Column(name = "V_NUMBOL")
	private String vNumbol;

	@Column(name = "D_FECBOLETIN")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecboletin;

	@Column(name = "V_UBIARCH")
	private String vUbiarch;

	@Column(name = "C_FLGELIMINADO", length = 1)
	private String cFlgeliminado;
	
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
	
	@Transient
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private transient  Date dFechaInicio; 
	
	@Transient
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private transient  Date dFechaFin;
	
	
	@Column(name = "V_NOMBREARCHIVO")
	private String vNombrearchivo;
	
	@Column(name = "V_ARCHIVOEXTENSION")
	private String vArchivoextension;

	public Boletines() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getbOletinidpk() {
		return bOletinidpk;
	}

	public void setbOletinidpk(Long bOletinidpk) {
		this.bOletinidpk = bOletinidpk;
	}

	public String getvNumbol() {
		return vNumbol;
	}

	public void setvNumbol(String vNumbol) {
		this.vNumbol = vNumbol;
	}

	public Date getdFecboletin() {
		return dFecboletin;
	}

	public void setdFecboletin(Date dFecboletin) {
		this.dFecboletin = dFecboletin;
	}

	public String getvUbiarch() {
		return vUbiarch;
	}

	public void setvUbiarch(String vUbiarch) {
		this.vUbiarch = vUbiarch;
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

	public Date getdFechaInicio() {
		return dFechaInicio;
	}

	public void setdFechaInicio(Date dFechaInicio) {
		this.dFechaInicio = dFechaInicio;
	}

	public Date getdFechaFin() {
		return dFechaFin;
	}

	public void setdFechaFin(Date dFechaFin) {
		this.dFechaFin = dFechaFin;
	}

	public String getvNombrearchivo() {
		return vNombrearchivo;
	}

	public void setvNombrearchivo(String vNombrearchivo) {
		this.vNombrearchivo = vNombrearchivo;
	}

	public String getvArchivoextension() {
		return vArchivoextension;
	}

	public void setvArchivoextension(String vArchivoextension) {
		this.vArchivoextension = vArchivoextension;
	}

	public String obtenerRutaAbsoluta() {
		return this.getvUbiarch()+this.getvNombrearchivo()+"."+this.vArchivoextension;
	}

}
