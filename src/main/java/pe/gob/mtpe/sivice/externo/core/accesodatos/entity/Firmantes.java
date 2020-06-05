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
@Table(name = "TBS_FIRMANTES")
public class Firmantes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBS_FIRMANTES") 
	@SequenceGenerator(name="SEQ_TBS_FIRMANTES",sequenceName="DB_TRAMITE.SEQ_TBS_FIRMANTES", allocationSize=1) 
	@Column(name = "FIRMANTE_ID_PK")
	private Long fIrmanteidpk;

	@Column(name = "CONSEJERO_FK")
	private Long cOnsejerofk;

	@Column(name = "ACTA_FK")
	private Long aCtafk;

	@Column(name = "C_FLGASISTIO", length = 1)
	private String cFlgasistio;

	@Column(name = "C_FLGELIMINO", length = 1)
	private String cFlgelimino;
	
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

	public Firmantes() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgelimino="0";
	}

	public Long getfIrmanteidpk() {
		return fIrmanteidpk;
	}

	public void setfIrmanteidpk(Long fIrmanteidpk) {
		this.fIrmanteidpk = fIrmanteidpk;
	}

	public Long getcOnsejerofk() {
		return cOnsejerofk;
	}

	public void setcOnsejerofk(Long cOnsejerofk) {
		this.cOnsejerofk = cOnsejerofk;
	}

	public Long getaCtafk() {
		return aCtafk;
	}

	public void setaCtafk(Long aCtafk) {
		this.aCtafk = aCtafk;
	}

	public String getcFlgasistio() {
		return cFlgasistio;
	}

	public void setcFlgasistio(String cFlgasistio) {
		this.cFlgasistio = cFlgasistio;
	}

	public String getcFlgelimino() {
		return cFlgelimino;
	}

	public void setcFlgelimino(String cFlgelimino) {
		this.cFlgelimino = cFlgelimino;
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
