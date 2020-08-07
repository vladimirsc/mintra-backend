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
@Table(name = "TBX_INVITADOS")
public class Invitados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBX_INVITADOS")
	@SequenceGenerator(name = "SEQ_TBX_INVITADOS", sequenceName = "DB_TRAMITE.SEQ_TBX_INVITADOS", allocationSize = 1)
	@Column(name = "INVITADOS_ID_PK")
	private Long iNvitadosidpk;
	
	@Column(name = "SESION_FK")
	private Long sEsionfk;
	
	@ManyToOne
	@JoinColumn(name ="ENTIDAD_FK",nullable = false, insertable = true, updatable = true) 
	private Entidades entidad;
 
	@ManyToOne
	@JoinColumn(name ="TIPO_DOCUMENTO_FK",nullable = false, insertable = true, updatable = true)
	private TipoDocumentos tipodocumento ;
 
	@Column(name = "V_NUMERODOCUMENTO")
	private String vNumerodocumento;
	
	@Column(name = "V_NOMBRE")
	private String vNombre;
	
	@Column(name = " V_APELLIDO_PATERNO")
	private String vApellido_paterno;
	
	@Column(name = "V_APELLIDO_MATERNO")
	private String vApellido_materno;
	
	@Column(name = "V_NUMEROCELULAR")
	private String vNumerocelular;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	@Column(name = "D_FECREGISTRO")
	private Date dFecregistro;
	
	@Column(name = "N_USUREG")
	private Long nUsureg;
	
	@Column(name = "C_FLGELIMINADO",length=1)
	private String cFlgeliminado;
	
	@Column(name = "ASISTENCIA_FK")
	private Long aSistenciafk;
	
 
	public Invitados() {
		 
	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecregistro =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getiNvitadosidpk() {
		return iNvitadosidpk;
	}

	public void setiNvitadosidpk(Long iNvitadosidpk) {
		this.iNvitadosidpk = iNvitadosidpk;
	}

	public Long getsEsionfk() {
		return sEsionfk;
	}

	public void setsEsionfk(Long sEsionfk) {
		this.sEsionfk = sEsionfk;
	}

	public Entidades getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidades entidad) {
		this.entidad = entidad;
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

	public String getvNombre() {
		return vNombre;
	}

	public void setvNombre(String vNombre) {
		this.vNombre = vNombre;
	}

	public String getvApellido_paterno() {
		return vApellido_paterno;
	}

	public void setvApellido_paterno(String vApellido_paterno) {
		this.vApellido_paterno = vApellido_paterno;
	}

	public String getvApellido_materno() {
		return vApellido_materno;
	}

	public void setvApellido_materno(String vApellido_materno) {
		this.vApellido_materno = vApellido_materno;
	}

	public String getvNumerocelular() {
		return vNumerocelular;
	}

	public void setvNumerocelular(String vNumerocelular) {
		this.vNumerocelular = vNumerocelular;
	}

	public Date getdFecregistro() {
		return dFecregistro;
	}

	public void setdFecregistro(Date dFecregistro) {
		this.dFecregistro = dFecregistro;
	}

	public Long getnUsureg() {
		return nUsureg;
	}

	public void setnUsureg(Long nUsureg) {
		this.nUsureg = nUsureg;
	}

	public String getcFlgeliminado() {
		return cFlgeliminado;
	}

	public void setcFlgeliminado(String cFlgeliminado) {
		this.cFlgeliminado = cFlgeliminado;
	}

	public Long getaSistenciafk() {
		return aSistenciafk;
	}

	public void setaSistenciafk(Long aSistenciafk) {
		this.aSistenciafk = aSistenciafk;
	}

	 
	
	
}
