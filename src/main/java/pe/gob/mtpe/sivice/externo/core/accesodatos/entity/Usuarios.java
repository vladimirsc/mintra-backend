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
@Table(name = "TBX_USUARIOS")
public class Usuarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBX_USUARIOS") 
	@SequenceGenerator(name="SEQ_TBX_USUARIOS",sequenceName="DB_TRAMITE_BK.SEQ_TBX_USUARIOS", allocationSize=1) 
	@Column(name = "USUARIO_ID_PK")
	private Long uSuarioidpk;

	@ManyToOne
	@JoinColumn(name ="TPDOCUMENTO_FK")
	private TipoDocumentos tipodocumento;

	@Column(name = "V_NOMBRE")
	private String vNombre;

	@Column(name = "V_AP_PATERNO")
	private String vAppaterno;

	@Column(name = "V_AP_MATERNO")
	private String vApmaterno;

	@Column(name = "V_NUMDOCUMENTO")
	private String vNumdocumento;

	@Column(name = "V_CORREO")
	private String username;

	@Column(name = "V_CLAVE")
	private String password;

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
	
	@Column(name = "C_FLGACTIVO", length = 1)
	private  String enabled;
	
	@ManyToOne
	@JoinColumn(name ="REGION_FK",nullable = false, insertable = true, updatable = true)
	private Regiones regiones;
	
	private transient String vrol;
	
	private transient String vregion;

	public Regiones getRegiones() {
		return regiones;
	}

	public void setRegiones(Regiones regiones) {
		this.regiones = regiones;
	}

	public Usuarios() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	  this.enabled="1";
	}

	public Long getuSuarioidpk() {
		return uSuarioidpk;
	}

	public void setuSuarioidpk(Long uSuarioidpk) {
		this.uSuarioidpk = uSuarioidpk;
	}

	public TipoDocumentos getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(TipoDocumentos tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getvNombre() {
		return vNombre;
	}

	public void setvNombre(String vNombre) {
		this.vNombre = vNombre;
	}

	public String getvAppaterno() {
		return vAppaterno;
	}

	public void setvAppaterno(String vAppaterno) {
		this.vAppaterno = vAppaterno;
	}

	public String getvApmaterno() {
		return vApmaterno;
	}

	public void setvApmaterno(String vApmaterno) {
		this.vApmaterno = vApmaterno;
	}

	public String getvNumdocumento() {
		return vNumdocumento;
	}

	public void setvNumdocumento(String vNumdocumento) {
		this.vNumdocumento = vNumdocumento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getVrol() {
		return vrol;
	}

	public void setVrol(String vrol) {
		this.vrol = vrol;
	}

	public String getVregion() {
		return vregion;
	}

	public void setVregion(String vregion) {
		this.vregion = vregion;
	}

	 
	 
}
