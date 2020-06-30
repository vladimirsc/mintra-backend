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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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

	@ManyToOne
	@JoinColumn(name="REGION_FK",nullable = false, insertable = true, updatable = true)
	private Regiones region;

	@ManyToOne
	@JoinColumn(name="CONSEJO_FK",nullable = false, insertable = true, updatable = true)
	private Consejos consejo;
 
	@ManyToOne
	@JoinColumn(name="V_TPCONSEJERO",nullable = false, insertable = true, updatable = true)
	private Tipoconsejero tipoconsejero;
	
	@ManyToOne
	@JoinColumn(name="V_TPDOCUMENTO",nullable = false, insertable = true, updatable = true)
	private TipoDocumentos tipodocumento;

	@ManyToOne
	@JoinColumn(name="V_PROFESION",nullable = true, insertable = true, updatable = true)
	private Profesiones profesion;
	
	@ManyToOne
	@JoinColumn(name="V_ENTIDAD",nullable = true, insertable = true, updatable = true)
	private Entidades  entidad;
	 
	@Column(name = "V_NUMDOCUMENTO")
	private String vNumdocumento;

	@Column(name = "V_DESNOMBRE")
	private String vDesnombre;

	@Column(name = "V_DESAP_PATERNO")
	private String vDesappaterno;

	@Column(name = "V_DESAP_MATERNO")
	private String vDesapmaterno;
 
	 
	@Column(name = "V_DESEMAIL_1")
	private String vDesemail1;
 
	@Column(name = "V_DESEMAIL_2")
	private String vDesemail2;

 
 
	@Column(name = "D_FECINICIO") 
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dFecinicio;

	@Column(name = "D_FECFIN") 
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dFecelimina;

	@Column(name = "N_USUELIMINIA")
	private Long nUsueliminia;

	@Column(name = "N_USUREG")
	private Long nUsureg;

	@Column(name = "D_FECREG")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dFecreg;

	@Column(name = "N_USUMODIFICA")
	private Long nUsumodifica;

	@Column(name = "D_FECMODIFICA")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dFecmodifica;
	
    private  transient String vnombreComision;
    private transient Long vEntidad;
    private transient Long rEgionfk;
    private transient Long cOnsejofk;
	
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

	 
	public Tipoconsejero getTipoconsejero() {
		return tipoconsejero;
	}

	public void setTipoconsejero(Tipoconsejero tipoconsejero) {
		this.tipoconsejero = tipoconsejero;
	}

	public TipoDocumentos getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(TipoDocumentos tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public Profesiones getProfesion() {
		return profesion;
	}

	public void setProfesion(Profesiones profesion) {
		this.profesion = profesion;
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

	public Entidades getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidades entidad) {
		this.entidad = entidad;
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

	public String getVnombreComision() {
		return vnombreComision;
	}

	public void setVnombreComision(String vnombreComision) {
		this.vnombreComision = vnombreComision;
	}

	public String obtenerRutaAbsoluta() {
		return this.getvUbidocasig()+this.getvNombredocasig()+"."+this.getvExtdocasig();
	}

	public Long getvEntidad() {
		return vEntidad;
	}

	public void setvEntidad(Long vEntidad) {
		this.vEntidad = vEntidad;
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
	
	

	
}
