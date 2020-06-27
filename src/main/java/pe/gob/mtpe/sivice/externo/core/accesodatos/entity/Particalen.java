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
@Table(name = "TBD_PARTICALEN")
public class Particalen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBD_PARTICALEN")
	@SequenceGenerator(name = "SEQ_TBD_PARTICALEN", sequenceName = "DB_TRAMITE.SEQ_TBD_PARTICALEN", allocationSize = 1)
	@Column(name = "PARTCALEND_ID_PK")
	private Long pArtcalendidpk;
	
	@ManyToOne
	@JoinColumn(name = "CALENDARIO_FK",nullable = false, insertable = true, updatable = true)
	private Calendarios calendario;

	@Column(name = "V_NOMBRE")
	private String vNombre;

	@Column(name = "V_APELLIDOPATERNO")
	private String vApellidoPaterno;

	@Column(name = "V_APELLIDOMATERNO")
	private String vApellidoMaterno;

	@ManyToOne
	@JoinColumn(name = "TIPO_DOCUMENTO_FK",nullable = false, insertable = true, updatable = true)
	private TipoDocumentos tipodocumento;

	@Column(name = "V_NUMERODOCUMENTO")
	private String vNumerodocumento;

	@ManyToOne
	@JoinColumn(name = "ENTIDAD_FK",nullable = true, insertable = true, updatable = true)
	private Entidades entidad;

	@Column(name = "V_CORREO")
	private String vCorreo;

	@Column(name = "D_FECACTIVIDAD")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecactividad;

	@Column(name = "C_FLGELIMINADO", length = 1)
	private String cFlgeliminado;

	@Column(name = "N_USUREG")
	private Long nUsuregistra;

	@Column(name = "D_FECREG")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecregistro;

	@Column(name = "N_USUMODIFICA")
	private Long nUsuariomodifica;

	@Column(name = "D_FECMODIFICA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecmodifica;

	@Column(name = "N_USUELIMINA")
	private Long nUsuarioelimina;

	@Column(name = "D_FECELIMINA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecElimina;
	
	@Column(name = "COMISION")
	private String comision;
	
	private transient long  nCalendiariofk;
	private transient long nTipodocumento;
	private transient long nEntidad;
	private transient String vFechaActividad;
	
	public Particalen() {

	}

	@PrePersist
	protected void valoresIniciales() {
		this.dFecregistro = new Date();
		this.cFlgeliminado = "0";
	}

	public Long getpArtcalendidpk() {
		return pArtcalendidpk;
	}

	public void setpArtcalendidpk(Long pArtcalendidpk) {
		this.pArtcalendidpk = pArtcalendidpk;
	}

	public Calendarios getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendarios calendario) {
		this.calendario = calendario;
	}

	public String getvNombre() {
		return vNombre;
	}

	public void setvNombre(String vNombre) {
		this.vNombre = vNombre;
	}

	public String getvApellidoPaterno() {
		return vApellidoPaterno;
	}

	public void setvApellidoPaterno(String vApellidoPaterno) {
		this.vApellidoPaterno = vApellidoPaterno;
	}

	public String getvApellidoMaterno() {
		return vApellidoMaterno;
	}

	public void setvApellidoMaterno(String vApellidoMaterno) {
		this.vApellidoMaterno = vApellidoMaterno;
	}

	public TipoDocumentos getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(TipoDocumentos tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getvNumerodocumento() {
		return vNumerodocumento;
	}

	public void setvNumerodocumento(String vNumerodocumento) {
		this.vNumerodocumento = vNumerodocumento;
	}

	public Entidades getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidades entidad) {
		this.entidad = entidad;
	}

	public String getvCorreo() {
		return vCorreo;
	}

	public void setvCorreo(String vCorreo) {
		this.vCorreo = vCorreo;
	}

	public Date getdFecactividad() {
		return dFecactividad;
	}

	public void setdFecactividad(Date dFecactividad) {
		this.dFecactividad = dFecactividad;
	}

	public String getcFlgeliminado() {
		return cFlgeliminado;
	}

	public void setcFlgeliminado(String cFlgeliminado) {
		this.cFlgeliminado = cFlgeliminado;
	}

	public Long getnUsuregistra() {
		return nUsuregistra;
	}

	public void setnUsuregistra(Long nUsuregistra) {
		this.nUsuregistra = nUsuregistra;
	}

	public Date getdFecregistro() {
		return dFecregistro;
	}

	public void setdFecregistro(Date dFecregistro) {
		this.dFecregistro = dFecregistro;
	}

	public Long getnUsuariomodifica() {
		return nUsuariomodifica;
	}

	public void setnUsuariomodifica(Long nUsuariomodifica) {
		this.nUsuariomodifica = nUsuariomodifica;
	}

	public Date getdFecmodifica() {
		return dFecmodifica;
	}

	public void setdFecmodifica(Date dFecmodifica) {
		this.dFecmodifica = dFecmodifica;
	}

	public Long getnUsuarioelimina() {
		return nUsuarioelimina;
	}

	public void setnUsuarioelimina(Long nUsuarioelimina) {
		this.nUsuarioelimina = nUsuarioelimina;
	}

	public Date getdFecElimina() {
		return dFecElimina;
	}

	public void setdFecElimina(Date dFecElimina) {
		this.dFecElimina = dFecElimina;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public long getnCalendiariofk() {
		return nCalendiariofk;
	}

	public void setnCalendiariofk(long nCalendiariofk) {
		this.nCalendiariofk = nCalendiariofk;
	}

	public long getnTipodocumento() {
		return nTipodocumento;
	}

	public void setnTipodocumento(long nTipodocumento) {
		this.nTipodocumento = nTipodocumento;
	}

	public long getnEntidad() {
		return nEntidad;
	}

	public void setnEntidad(long nEntidad) {
		this.nEntidad = nEntidad;
	}

	public String getvFechaActividad() {
		return vFechaActividad;
	}

	public void setvFechaActividad(String vFechaActividad) {
		this.vFechaActividad = vFechaActividad;
	}

	 

}
