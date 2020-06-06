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
import javax.validation.constraints.Email; 

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBX_CONSEJEROS")
public class Consejeros implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBX_CONSEJEROS")
	@SequenceGenerator(name = "SEQ_TBX_CONSEJEROS", sequenceName = "DB_TRAMITE.SEQ_TBX_CONSEJEROS", allocationSize = 1)
	@Column(name = "CONSEJERO_ID_PK")
	private Long cOnsejeroidpk;

	@Column(name = "REGION_FK")
	private Long rEgionfk;

	@Column(name = "CONSEJO_FK")
	private Long cOnsejofk;

	@Column(name = "COMISION_FK")
	private Long cOmisionfk;

	
	@Column(name = "V_TPDOCUMENTO")
	private String vTipdocumento;

	 
	@Column(name = "V_NUMDOCUMENTO")
	private String vNumdocumento;

	
	@Column(name = "V_DESNOMBRE")
	private String vDesnombre;

	@Column(name = "V_DESAP_PATERNO")
	private String vDesappaterno;

	@Column(name = "V_DESAP_MATERNO")
	private String vDesapmaterno;

	
	@Column(name = "V_PROFESION")
	private String vProfesion;
	
	@Email
	@Column(name = "V_DESEMAIL_1")
	private String vDesemail1;
	
	@Email
	@Column(name = "V_DESEMAIL_2")
	private String vDesemail2;

	@Column(name = "V_ENTIDAD")
	private String  vEntidad;

	@Column(name = "V_TPCONSEJERO")
	private String vTpconsejero;

	@Column(name = "D_FECINICIO")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecinicio;

	@Column(name = "D_FECFIN")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecfin;

	@Column(name = "V_NUMDOCASIG")
	private String vNumdocasig;

	@Column(name = "V_NOMBREDOCASIG")
	private String vNombredocasig;

	@Column(name = "V_UBIDOCASIG")
	private String vUbidocasig;

	@Column(name = "V_EXTDOCASIG")
	private String vExtdocasig;

	@Column(name = "C_FLGELIMINADO", length = 1)
	private String cFlgeliminado;

	@Column(name = "D_FECELIMINA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecelimina;

	@Column(name = "N_USUELIMINIA")
	private Long nUsueliminia;

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
	
    private  transient String vnombreComision;
	
	public Consejeros() {

	}

	@PrePersist
	protected void valoresIniciales() {
		this.dFecreg = new Date();
		this.cFlgeliminado = "0";
	}

	public Long getcOnsejeroidpk() {
		return cOnsejeroidpk;
	}

	public void setcOnsejeroidpk(Long cOnsejeroidpk) {
		this.cOnsejeroidpk = cOnsejeroidpk;
	}

	public Long getrEgionfk() {
		return rEgionfk;
	}

	public void setrEgionfk(Long rEgionfk) {
		this.rEgionfk = rEgionfk;
	}

	public Long getcOnsejofk() {
		return cOnsejofk;
	}

	public void setcOnsejofk(Long cOnsejofk) {
		this.cOnsejofk = cOnsejofk;
	}

	public Long getcOmisionfk() {
		return cOmisionfk;
	}

	public void setcOmisionfk(Long cOmisionfk) {
		this.cOmisionfk = cOmisionfk;
	}

	public String getvTipdocumento() {
		return vTipdocumento;
	}

	public void setvTipdocumento(String vTipdocumento) {
		this.vTipdocumento = vTipdocumento;
	}

	public String getvNumdocumento() {
		return vNumdocumento;
	}

	public void setvNumdocumento(String vNumdocumento) {
		this.vNumdocumento = vNumdocumento;
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

	public String getvProfesion() {
		return vProfesion;
	}

	public void setvProfesion(String vProfesion) {
		this.vProfesion = vProfesion;
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

	public String getvEntidad() {
		return vEntidad;
	}

	public void setvEntidad(String vEntidad) {
		this.vEntidad = vEntidad;
	}

	public String getvTpconsejero() {
		return vTpconsejero;
	}

	public void setvTpconsejero(String vTpconsejero) {
		this.vTpconsejero = vTpconsejero;
	}

	public Date getdFecinicio() {
		return dFecinicio;
	}

	public void setdFecinicio(Date dFecinicio) {
		this.dFecinicio = dFecinicio;
	}

	public Date getdFecfin() {
		return dFecfin;
	}

	public void setdFecfin(Date dFecfin) {
		this.dFecfin = dFecfin;
	}

	public String getvNumdocasig() {
		return vNumdocasig;
	}

	public void setvNumdocasig(String vNumdocasig) {
		this.vNumdocasig = vNumdocasig;
	}

	public String getvNombredocasig() {
		return vNombredocasig;
	}

	public void setvNombredocasig(String vNombredocasig) {
		this.vNombredocasig = vNombredocasig;
	}

	public String getvUbidocasig() {
		return vUbidocasig;
	}

	public void setvUbidocasig(String vUbidocasig) {
		this.vUbidocasig = vUbidocasig;
	}

	public String getvExtdocasig() {
		return vExtdocasig;
	}

	public void setvExtdocasig(String vExtdocasig) {
		this.vExtdocasig = vExtdocasig;
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

	public Long getnUsueliminia() {
		return nUsueliminia;
	}

	public void setnUsueliminia(Long nUsueliminia) {
		this.nUsueliminia = nUsueliminia;
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
		return this.getvUbidocasig()+this.getvNombredocasig()+"."+this.getvExtdocasig();
	}

	public String getVnombreComision() {
		return vnombreComision;
	}

	public void setVnombreComision(String vnombreComision) {
		this.vnombreComision = vnombreComision;
	}

	
}
