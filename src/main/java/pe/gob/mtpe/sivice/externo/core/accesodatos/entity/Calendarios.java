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
@Table(name = "TBC_CALENDARIOS")
public class Calendarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBC_CALENDARIOS") 
	@SequenceGenerator(name="SEQ_TBC_CALENDARIOS",sequenceName="DB_TRAMITE.SEQ_TBC_CALENDARIOS", allocationSize=1)
	@Column(name = "CALENDARIO_ID_PK")
	private Long cAlendarioidpk;

	@Column(name = "COMISION_FK")
	private Long cOmisionfk;

	@Column(name = "V_DESACTIVIDAD")
	private String vDesactividad;

	@Column(name = "D_FECACTIVIDAD")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecactividad;

	@Column(name = "D_HORAINICIO")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dHorainicio;

	@Column(name = "D_HORAFIN")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dHorafin;

	@Column(name = "C_FLGEJECUTO", length = 1)
	private String cFlgejecuto;

	@Column(name = "D_FECEJECUTO")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecejecuto;

	@Column(name = "V_DESEJECUCION")
	private String vDesejecucion;

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

	public Calendarios() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getcAlendarioidpk() {
		return cAlendarioidpk;
	}

	public void setcAlendarioidpk(Long cAlendarioidpk) {
		this.cAlendarioidpk = cAlendarioidpk;
	}

	public Long getcOmisionfk() {
		return cOmisionfk;
	}

	public void setcOmisionfk(Long cOmisionfk) {
		this.cOmisionfk = cOmisionfk;
	}

	public String getvDesactividad() {
		return vDesactividad;
	}

	public void setvDesactividad(String vDesactividad) {
		this.vDesactividad = vDesactividad;
	}

	public Date getdFecactividad() {
		return dFecactividad;
	}

	public void setdFecactividad(Date dFecactividad) {
		this.dFecactividad = dFecactividad;
	}

	public Date getdHorainicio() {
		return dHorainicio;
	}

	public void setdHorainicio(Date dHorainicio) {
		this.dHorainicio = dHorainicio;
	}

	public Date getdHorafin() {
		return dHorafin;
	}

	public void setdHorafin(Date dHorafin) {
		this.dHorafin = dHorafin;
	}

	public String getcFlgejecuto() {
		return cFlgejecuto;
	}

	public void setcFlgejecuto(String cFlgejecuto) {
		this.cFlgejecuto = cFlgejecuto;
	}

	public Date getdFecejecuto() {
		return dFecejecuto;
	}

	public void setdFecejecuto(Date dFecejecuto) {
		this.dFecejecuto = dFecejecuto;
	}

	public String getvDesejecucion() {
		return vDesejecucion;
	}

	public void setvDesejecucion(String vDesejecucion) {
		this.vDesejecucion = vDesejecucion;
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
