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
@Table(name = "TBD_COMI_CONSEJ")
public class ComiConsej implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBD_COMI_CONSEJ") 
	@SequenceGenerator(name="SEQ_TBD_COMI_CONSEJ",sequenceName="DB_TRAMITE.SEQ_TBD_COMI_CONSEJ", allocationSize=1) 
	@Column(name = "COMI_CONS_ID_PK")
	private Long cOmiconsidpk;

	@Column(name = "TPCONSEJERO_FK")
	private Long tPconsejerofk;

	@Column(name = "CONSEJERO_FK")
	private Long cOnsejerofk;

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

	public ComiConsej() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getcOmiconsidpk() {
		return cOmiconsidpk;
	}

	public void setcOmiconsidpk(Long cOmiconsidpk) {
		this.cOmiconsidpk = cOmiconsidpk;
	}

	public Long gettPconsejerofk() {
		return tPconsejerofk;
	}

	public void settPconsejerofk(Long tPconsejerofk) {
		this.tPconsejerofk = tPconsejerofk;
	}

	public Long getcOnsejerofk() {
		return cOnsejerofk;
	}

	public void setcOnsejerofk(Long cOnsejerofk) {
		this.cOnsejerofk = cOnsejerofk;
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
