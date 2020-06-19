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
@Table(name = "TBX_PERSONAS")
public class Personas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBX_PERSONAS") 
	@SequenceGenerator(name="SEQ_TBX_PERSONAS",sequenceName="DB_TRAMITE.SEQ_TBX_PERSONAS", allocationSize=1) 
	@Column(name = "PERSONA_ID_PK")
	private Long pErsonaidpk;

	@ManyToOne
	@JoinColumn(name ="TPDOCUMENTO_FK")
	private TipoDocumentos tipodocumento;

	@Column(name = "V_DESNOMBRE")
	private String vDesnombre;

	@Column(name = "V_DESAP_PATERNO")
	private String vDesappaterno;

	@Column(name = "V_DESAP_MATERNO")
	private String vDesapmaterno;

	@Column(name = "V_NUMDOCUMENTO")
	private String vNumdocumento;

	@Column(name = "V_DESEMAIL_1")
	private String vDesemail1;

	@Column(name = "V_DESEMAIL_2")
	private String vDesemail2;

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
	
	private transient Long rol;
    private transient Long perfil;
    private transient String clave;
    private transient Long tPdocumentofk;

	public Personas() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getpErsonaidpk() {
		return pErsonaidpk;
	}

	public void setpErsonaidpk(Long pErsonaidpk) {
		this.pErsonaidpk = pErsonaidpk;
	}

	 

	public TipoDocumentos getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(TipoDocumentos tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getvDesnombre() {
		return vDesnombre;
	}

	public void setvDesnombre(String vDesnombre) {
		this.vDesnombre = vDesnombre;
	}

	public String getvDesappaterno() {
		return vDesappaterno;
	}

	public void setvDesappaterno(String vDesappaterno) {
		this.vDesappaterno = vDesappaterno;
	}

	public String getvDesapmaterno() {
		return vDesapmaterno;
	}

	public void setvDesapmaterno(String vDesapmaterno) {
		this.vDesapmaterno = vDesapmaterno;
	}

	public String getvNumdocumento() {
		return vNumdocumento;
	}

	public void setvNumdocumento(String vNumdocumento) {
		this.vNumdocumento = vNumdocumento;
	}

	public String getvDesemail1() {
		return vDesemail1;
	}

	public void setvDesemail1(String vDesemail1) {
		this.vDesemail1 = vDesemail1;
	}

	public String getvDesemail2() {
		return vDesemail2;
	}

	public void setvDesemail2(String vDesemail2) {
		this.vDesemail2 = vDesemail2;
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

	public Long getRol() {
		return rol;
	}

	public void setRol(Long rol) {
		this.rol = rol;
	}

	public Long getPerfil() {
		return perfil;
	}

	public void setPerfil(Long perfil) {
		this.perfil = perfil;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Long gettPdocumentofk() {
		return tPdocumentofk;
	}

	public void settPdocumentofk(Long tPdocumentofk) {
		this.tPdocumentofk = tPdocumentofk;
	}

	 
	 
}
