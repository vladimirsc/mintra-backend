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
@Table(name = "TBC_COMISIONES")
public class Comisiones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBC_COMISIONES") 
	@SequenceGenerator(name="SEQ_TBC_COMISIONES",sequenceName="DB_TRAMITE.SEQ_TBC_COMISIONES", allocationSize=1) 
	@Column(name = "COMISION_ID_PK")
	private Long cOmisionidpk;

	@Column(name = "TIPOCOMS_FK")
	private Long tIpocomsfk;

	@Column(name = "V_CODCOMISION")
	private String vCodcomision;

	@Column(name = "V_NUMDOCAPR")
	private String vNumdocapr;

	@Column(name = "V_UBIDOCAP")
	private String vUbidocap;

	@Column(name = "D_FECDOCAPR")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecdocapr;

	@Column(name = "D_FECINICIO")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecinicio;

	@Column(name = "D_FECFIN")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecfin;

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

	public Comisiones() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getcOmisionidpk() {
		return cOmisionidpk;
	}

	public void setcOmisionidpk(Long cOmisionidpk) {
		this.cOmisionidpk = cOmisionidpk;
	}

	public Long gettIpocomsfk() {
		return tIpocomsfk;
	}

	public void settIpocomsfk(Long tIpocomsfk) {
		this.tIpocomsfk = tIpocomsfk;
	}

	public String getvCodcomision() {
		return vCodcomision;
	}

	public void setvCodcomision(String vCodcomision) {
		this.vCodcomision = vCodcomision;
	}

	public String getvNumdocapr() {
		return vNumdocapr;
	}

	public void setvNumdocapr(String vNumdocapr) {
		this.vNumdocapr = vNumdocapr;
	}

	public String getvUbidocap() {
		return vUbidocap;
	}

	public void setvUbidocap(String vUbidocap) {
		this.vUbidocap = vUbidocap;
	}

	public Date getdFecdocapr() {
		return dFecdocapr;
	}

	public void setdFecdocapr(Date dFecdocapr) {
		this.dFecdocapr = dFecdocapr;
	}

	public Date getdFecinicio() {
		return dFecinicio;
	}

	public void setdFecinicio(Date dFecinicio) {
		this.dFecinicio = dFecinicio;
	}

	public Date getdFecfin() {
		return dFecfin;
	}

	public void setdFecfin(Date dFecfin) {
		this.dFecfin = dFecfin;
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

	 
}
