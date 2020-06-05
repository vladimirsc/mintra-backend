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
@Table(name = "TBC_SESION")
public class Sesion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBC_SESION") 
	@SequenceGenerator(name="SEQ_TBC_SESION",sequenceName="DB_TRAMITE.SEQ_TBC_SESION", allocationSize=1) 
	@Column(name = "SESION_ID_PK")
	private Long sEsionidpk;

	@Column(name = "TIPOSESION_FK")
	private Long tIposesionfk;

	@Column(name = "COMISION_FK")
	private Long cOmisionfk;

	@Column(name = "V_CODSESION")
	private String vCodsesion;

	@Column(name = "D_HORINICIO")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dHorinicio;

	@Column(name = "D_HORFIN")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dHorfin;

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

	public Sesion() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getsEsionidpk() {
		return sEsionidpk;
	}

	public void setsEsionidpk(Long sEsionidpk) {
		this.sEsionidpk = sEsionidpk;
	}

	public Long gettIposesionfk() {
		return tIposesionfk;
	}

	public void settIposesionfk(Long tIposesionfk) {
		this.tIposesionfk = tIposesionfk;
	}

	public Long getcOmisionfk() {
		return cOmisionfk;
	}

	public void setcOmisionfk(Long cOmisionfk) {
		this.cOmisionfk = cOmisionfk;
	}

	public String getvCodsesion() {
		return vCodsesion;
	}

	public void setvCodsesion(String vCodsesion) {
		this.vCodsesion = vCodsesion;
	}

	public Date getdHorinicio() {
		return dHorinicio;
	}

	public void setdHorinicio(Date dHorinicio) {
		this.dHorinicio = dHorinicio;
	}

	public Date getdHorfin() {
		return dHorfin;
	}

	public void setdHorfin(Date dHorfin) {
		this.dHorfin = dHorfin;
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

	 
}
