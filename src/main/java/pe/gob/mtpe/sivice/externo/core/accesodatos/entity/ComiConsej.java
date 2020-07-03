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
	
	@ManyToOne
	@JoinColumn(name="COMISION_FK",nullable = false, insertable = true, updatable = true)
	private  Comisiones comision;

	@ManyToOne
	@JoinColumn(name="TPCONSEJERO_FK",nullable = false, insertable = true, updatable = true)
	private Tipoconsejero tipoconsejero;

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
 
	@ManyToOne
	@JoinColumn(name="CONSEJERO_FK")
	private  Consejeros consejero;
	 
	private transient Long tipoconsejeropk;
	private transient Long comisionfk;
	

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

	public Comisiones getComision() {
		return comision;
	}

	public void setComision(Comisiones comision) {
		this.comision = comision;
	}

	public Tipoconsejero getTipoconsejero() {
		return tipoconsejero;
	}

	public void setTipoconsejero(Tipoconsejero tipoconsejero) {
		this.tipoconsejero = tipoconsejero;
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

	public Consejeros getConsejero() {
		return consejero;
	}

	public void setConsejero(Consejeros consejero) {
		this.consejero = consejero;
	}

	public Long getTipoconsejeropk() {
		return tipoconsejeropk;
	}

	public void setTipoconsejeropk(Long tipoconsejeropk) {
		this.tipoconsejeropk = tipoconsejeropk;
	}

	public Long getComisionfk() {
		return comisionfk;
	}

	public void setComisionfk(Long comisionfk) {
		this.comisionfk = comisionfk;
	}
 
	 
}
