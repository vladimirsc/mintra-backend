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
	private String cOmisionfk;

	@Column(name = "V_DESACTIVIDAD")
	private String vDesactividad;

	@Column(name = "D_FECACTIVIDAD")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecactividad;

	@Column(name = "V_HORAINICIO") 
	private String vHorainicio;

	@Column(name = "V_HORAFIN") 
	private String vHorafin;

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
	
	private transient String vCoidigoComision;
	private transient String vFechaActividad;
	private transient String vFechaInicioActividad;
	private transient String vFechaFinActividad;
	
	private transient Date dFechaActividad;
	private transient Date dFechaInicioActividad;
	private transient Date dFechaFinActividad;
	
	@ManyToOne
	@JoinColumn(name="REGION_FK",nullable = false, insertable = true, updatable = true)
	private Regiones region;
 
	@ManyToOne
	@JoinColumn(name="CONSEJO_FK",nullable = false, insertable = true, updatable = true)
	private Consejos consejo;
	
	
	@Column(name = "N_USUELIMINIA")
	private Long nUsuelimina;
	
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

	public String getcOmisionfk() {
		return cOmisionfk;
	}

	public void setcOmisionfk(String cOmisionfk) {
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

	public String getvHorainicio() {
		return vHorainicio;
	}

	public void setvHorainicio(String vHorainicio) {
		this.vHorainicio = vHorainicio;
	}

	public String getvHorafin() {
		return vHorafin;
	}

	public void setvHorafin(String vHorafin) {
		this.vHorafin = vHorafin;
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

	public String getvFechaActividad() {
		return vFechaActividad;
	}

	public void setvFechaActividad(String vFechaActividad) {
		this.vFechaActividad = vFechaActividad;
	}

	public Regiones getRegion() {
		return region;
	}

	public void setRegion(Regiones region) {
		this.region = region;
	}

	public Consejos getConsejo() {
		return consejo;
	}

	public void setConsejo(Consejos consejo) {
		this.consejo = consejo;
	}

	public Long getnUsuelimina() {
		return nUsuelimina;
	}

	public void setnUsuelimina(Long nUsuelimina) {
		this.nUsuelimina = nUsuelimina;
	}

	public String getvCoidigoComision() {
		return vCoidigoComision;
	}

	public void setvCoidigoComision(String vCoidigoComision) {
		this.vCoidigoComision = vCoidigoComision;
	}

	public String getvFechaInicioActividad() {
		return vFechaInicioActividad;
	}

	public void setvFechaInicioActividad(String vFechaInicioActividad) {
		this.vFechaInicioActividad = vFechaInicioActividad;
	}

	public String getvFechaFinActividad() {
		return vFechaFinActividad;
	}

	public void setvFechaFinActividad(String vFechaFinActividad) {
		this.vFechaFinActividad = vFechaFinActividad;
	}

	public Date getdFechaActividad() {
		return dFechaActividad;
	}

	public void setdFechaActividad(Date dFechaActividad) {
		this.dFechaActividad = dFechaActividad;
	}

	public Date getdFechaInicioActividad() {
		return dFechaInicioActividad;
	}

	public void setdFechaInicioActividad(Date dFechaInicioActividad) {
		this.dFechaInicioActividad = dFechaInicioActividad;
	}

	public Date getdFechaFinActividad() {
		return dFechaFinActividad;
	}

	public void setdFechaFinActividad(Date dFechaFinActividad) {
		this.dFechaFinActividad = dFechaFinActividad;
	}

	 
	 
}
