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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBD_ACTAS")
public class Actas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBD_ACTAS") 
	@SequenceGenerator(name="SEQ_TBD_ACTAS",sequenceName="DB_TRAMITE.SEQ_TBD_ACTAS", allocationSize=1) 
	@Column(name = "ACTA_ID_PK")
	private Long aCtaidpk;

	@Column(name = "SESION_FK")
	private Long sEsionfk;

	@Column(name = "V_CODACTA")
	private String vCodacta;

	@Column(name = "D_FECACTA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecacta;

	@Column(name = "V_UBIARCH")
	private String vUbiarch;

	@Column(name = "C_FLAGELIMINA", length = 1)
	private String cFlagelimina;
	
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
	
	@Column(name = "V_NOMBREARCHIVO")
	private String vNombrearchivo;
	
	@Column(name = "V_ARCHIVOEXTENSION")
	private String vArchivoextension;
	

	public Actas() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
		this.dFecreg =new Date();
		this.cFlagelimina="0";
	}

	public Long getaCtaidpk() {
		return aCtaidpk;
	}

	public void setaCtaidpk(Long aCtaidpk) {
		this.aCtaidpk = aCtaidpk;
	}

	public Long getsEsionfk() {
		return sEsionfk;
	}

	public void setsEsionfk(Long sEsionfk) {
		this.sEsionfk = sEsionfk;
	}

	public String getvCodacta() {
		return vCodacta;
	}

	public void setvCodacta(String vCodacta) {
		this.vCodacta = vCodacta;
	}

	public Date getdFecacta() {
		return dFecacta;
	}

	public void setdFecacta(Date dFecacta) {
		this.dFecacta = dFecacta;
	}

	public String getvUbiarch() {
		return vUbiarch;
	}

	public void setvUbiarch(String vUbiarch) {
		this.vUbiarch = vUbiarch;
	}

	public String getcFlagelimina() {
		return cFlagelimina;
	}

	public void setcFlagelimina(String cFlagelimina) {
		this.cFlagelimina = cFlagelimina;
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
