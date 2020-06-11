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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TBC_SESIONES",schema="DB_TRAMITE")
public class Sesiones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBC_SESION") 
	@SequenceGenerator(name="SEQ_TBC_SESION",sequenceName="DB_TRAMITE.SEQ_TBC_SESION", allocationSize=1) 
	@Column(name = "SESION_ID_PK")
	private Long sEsionidpk;

	@Column(name = "V_CODSESION")
	private String vCodsesion;

	@Column(name = "D_HORINICIO")
	private String dHorinicio;

	@Column(name = "D_HORFIN")
	private String dHorfin;

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
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CONSEJOS_FK",nullable = false, insertable = true, updatable = true)
	private Consejos consejofk;
	
	@OneToOne
	@JoinColumn(name = "COMISION_FK",nullable = true, insertable = true, updatable = true)
	private Comisiones comisionfk;
	
	@ManyToOne
	@JoinColumn(name = "TIPOSESION_FK",nullable = false, insertable = true, updatable = true)
	private TipoSesiones tipoSesiones;
	
	private transient Date dFechaInicio;
	
	private transient Date dFechaFin;

	public Sesiones() {

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

	public String getvCodsesion() {
		return vCodsesion;
	}

	public void setvCodsesion(String vCodsesion) {
		this.vCodsesion = vCodsesion;
	}

	public String getdHorinicio() {
		return dHorinicio;
	}

	public void setdHorinicio(String dHorinicio) {
		this.dHorinicio = dHorinicio;
	}

	public String getdHorfin() {
		return dHorfin;
	}

	public void setdHorfin(String dHorfin) {
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

	public Consejos getConsejofk() {
		return consejofk;
	}

	public void setConsejofk(Consejos consejofk) {
		this.consejofk = consejofk;
	}

	public Comisiones getComisionfk() {
		return comisionfk;
	}

	public void setComisionfk(Comisiones comisionfk) {
		this.comisionfk = comisionfk;
	}

	public TipoSesiones getTipoSesiones() {
		return tipoSesiones;
	}

	public void setTipoSesiones(TipoSesiones tipoSesiones) {
		this.tipoSesiones = tipoSesiones;
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

	 
	 
}
