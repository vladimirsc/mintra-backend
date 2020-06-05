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
@Table(name = "TBX_PLAN_TRABAJO")
public class PlanTrabajo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBX_PLAN_TRABAJO") 
	@SequenceGenerator(name="SEQ_TBX_PLAN_TRABAJO",sequenceName="DB_TRAMITE.SEQ_TBX_PLAN_TRABAJO", allocationSize=1) 
	@Column(name = "PLAN_TRAB_ID_PK")
	private Long pLantrabidpk;

	@Column(name = "COMISION_FK")
	private Long cOmisionfk;

	@Column(name = "V_CODIGOPLANTRAB")
	private String vCodigoplantrab;

	@Column(name = "D_FECAPROBACION")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecaprobacion;

	@Column(name = "D_FECINICIO")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecinicio;

	@Column(name = "D_FECFIN")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecfin;

	@Column(name = "V_NUMDOCAPR")
	private String vNumdocapr;

	@Column(name = "V_UBIDOCAPR")
	private String vUbidocapr;

	@Column(name = "V_UBIPLANAPR")
	private String vUbiplanapr;

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
	
	@Column(name = "V_UBIARCHPLAN")
	private String vUbiarchplan;
	
	@Column(name = "V_NOMARCHPLAN")
	private String vNomarchplan;
	
	@Column(name = "V_EXTARCHPLAN")
	private String vExtarchplan;
	
	@Column(name = "V_UBIDOCAPROBACION")
	private String vUbidocaprobacion;
	
	@Column(name = "V_NOMARCHDOCAPROB")
	private String vNomarchdocaprob;
	
	@Column(name = "V_EXTARCHDOCAPROB")
	private String vExtarchdocaprob;
	
	

	public PlanTrabajo() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getpLantrabidpk() {
		return pLantrabidpk;
	}

	public void setpLantrabidpk(Long pLantrabidpk) {
		this.pLantrabidpk = pLantrabidpk;
	}

	public Long getcOmisionfk() {
		return cOmisionfk;
	}

	public void setcOmisionfk(Long cOmisionfk) {
		this.cOmisionfk = cOmisionfk;
	}

	public String getvCodigoplantrab() {
		return vCodigoplantrab;
	}

	public void setvCodigoplantrab(String vCodigoplantrab) {
		this.vCodigoplantrab = vCodigoplantrab;
	}

	public Date getdFecaprobacion() {
		return dFecaprobacion;
	}

	public void setdFecaprobacion(Date dFecaprobacion) {
		this.dFecaprobacion = dFecaprobacion;
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

	public String getvNumdocapr() {
		return vNumdocapr;
	}

	public void setvNumdocapr(String vNumdocapr) {
		this.vNumdocapr = vNumdocapr;
	}

	public String getvUbidocapr() {
		return vUbidocapr;
	}

	public void setvUbidocapr(String vUbidocapr) {
		this.vUbidocapr = vUbidocapr;
	}

	public String getvUbiplanapr() {
		return vUbiplanapr;
	}

	public void setvUbiplanapr(String vUbiplanapr) {
		this.vUbiplanapr = vUbiplanapr;
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

	public String getvUbiarchplan() {
		return vUbiarchplan;
	}

	public void setvUbiarchplan(String vUbiarchplan) {
		this.vUbiarchplan = vUbiarchplan;
	}

	public String getvNomarchplan() {
		return vNomarchplan;
	}

	public void setvNomarchplan(String vNomarchplan) {
		this.vNomarchplan = vNomarchplan;
	}

	public String getvExtarchplan() {
		return vExtarchplan;
	}

	public void setvExtarchplan(String vExtarchplan) {
		this.vExtarchplan = vExtarchplan;
	}

	public String getvUbidocaprobacion() {
		return vUbidocaprobacion;
	}

	public void setvUbidocaprobacion(String vUbidocaprobacion) {
		this.vUbidocaprobacion = vUbidocaprobacion;
	}

	public String getvNomarchdocaprob() {
		return vNomarchdocaprob;
	}

	public void setvNomarchdocaprob(String vNomarchdocaprob) {
		this.vNomarchdocaprob = vNomarchdocaprob;
	}

	public String getvExtarchdocaprob() {
		return vExtarchdocaprob;
	}

	public void setvExtarchdocaprob(String vExtarchdocaprob) {
		this.vExtarchdocaprob = vExtarchdocaprob;
	}

	 

}
