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
@Table(name = "TBS_ACCIONES")
public class Acciones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBS_ACCIONES") 
	@SequenceGenerator(name="SEQ_TBS_ACCIONES",sequenceName="DB_TRAMITE.SEQ_TBS_ACCIONES", allocationSize=1) 
	@Column(name = "ACCIONES_ID_PK")
	private Long aCcionesidpk;
 
	@ManyToOne
	@JoinColumn(name="ACUERDO_FK")
	private Acuerdos acuerdo;
	
	@ManyToOne
	@JoinColumn(name="ENTIDAD_FK")
	private Entidades entidad;
 
	@Column(name = "V_RESPONSABLE")
	private String vResponsable;
 
	@Column(name = "V_DESACCION")
	private String vDesaccion;

	@Column(name = "D_FECEJECUTARA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecejecutara;

	@Column(name = "C_FLGEJECUTO", length = 1)
	private String cFlgejecuto;

	@Column(name = "D_FECEJECUTO")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecejecuto;
	
	@Column(name = "V_NOMBREARCHIVO")
	private String vNombrearchivo;
	
	@Column(name = "V_UBIARCH")
	private String vUbiarch;

	@Column(name = "V_EXTARCHIVO")
	private String vExtarchivo;

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
	
 

	public Acciones() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getaCcionesidpk() {
		return aCcionesidpk;
	}

	public void setaCcionesidpk(Long aCcionesidpk) {
		this.aCcionesidpk = aCcionesidpk;
	}

	public Acuerdos getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(Acuerdos acuerdo) {
		this.acuerdo = acuerdo;
	}

	public Entidades getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidades entidad) {
		this.entidad = entidad;
	}

	public String getvResponsable() {
		return vResponsable;
	}

	public void setvResponsable(String vResponsable) {
		this.vResponsable = vResponsable;
	}

	public String getvDesaccion() {
		return vDesaccion;
	}

	public void setvDesaccion(String vDesaccion) {
		this.vDesaccion = vDesaccion;
	}

	public Date getdFecejecutara() {
		return dFecejecutara;
	}

	public void setdFecejecutara(Date dFecejecutara) {
		this.dFecejecutara = dFecejecutara;
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

	public String getvNombrearchivo() {
		return vNombrearchivo;
	}

	public void setvNombrearchivo(String vNombrearchivo) {
		this.vNombrearchivo = vNombrearchivo;
	}

	public String getvUbiarch() {
		return vUbiarch;
	}

	public void setvUbiarch(String vUbiarch) {
		this.vUbiarch = vUbiarch;
	}

	public String getvExtarchivo() {
		return vExtarchivo;
	}

	public void setvExtarchivo(String vExtarchivo) {
		this.vExtarchivo = vExtarchivo;
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

	public String obtenerRutaAbsoluta() {
		return this.getvUbiarch()+this.getvNombrearchivo()+"."+this.getvExtarchivo();
	}
	  

}
