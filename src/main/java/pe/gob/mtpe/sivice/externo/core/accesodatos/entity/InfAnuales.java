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
@Table(name = "TBX_INF_ANUALES")
public class InfAnuales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBX_INF_ANUALES") 
	@SequenceGenerator(name="SEQ_TBX_INF_ANUALES",sequenceName="DB_TRAMITE.SEQ_TBX_INF_ANUALES", allocationSize=1) 
	@Column(name = "INFORME_ID_PK")
	private Long iNformeidpk;

	@Column(name = "V_SESION")
	private String vSesion;

	@Column(name = "COMISION_FK")
	private String comision;

	@Column(name = "V_CODINFORME")
	private String vCodinforme;
 
	@Column(name = "V_NUMDOCAP")
	private String vNumdocap;

	@Column(name = "V_UBIARCH")
	private String vUbiarch;

	@Column(name = "C_FLGELIMINADO", length = 1)
	private String cFlgeliminado;
	
	@Column(name = "D_FECELIMINA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecelimina;

	@Column(name = "N_USUREG")
	private Long nUsureg;

	@Column(name = "D_FECDESDE")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecdesde;
 
	@Column(name = "N_USUMODIFICA")
	private Long nUsumodifica;

	@Column(name = "D_FECMODIFICA")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecmodifica;
	
	@Column(name = "V_EXTENSION")
    private String vExtension;
	
	@Column(name = "D_FECREG")
	private Date dFecreg;
	
	
	@Column(name = "V_NOMARCHIVO")
	 private String vNombreArchivo;
	
	private transient Date dFhasta;
	
	
	public InfAnuales() {

	}
	
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}


	public Long getiNformeidpk() {
		return iNformeidpk;
	}


	public void setiNformeidpk(Long iNformeidpk) {
		this.iNformeidpk = iNformeidpk;
	}


	public String getvSesion() {
		return vSesion;
	}


	public void setvSesion(String vSesion) {
		this.vSesion = vSesion;
	}
 
	public String getComision() {
		return comision;
	}


	public void setComision(String comision) {
		this.comision = comision;
	}


	public String getvCodinforme() {
		return vCodinforme;
	}


	public void setvCodinforme(String vCodinforme) {
		this.vCodinforme = vCodinforme;
	}

 

	public String getvNumdocap() {
		return vNumdocap;
	}


	public void setvNumdocap(String vNumdocap) {
		this.vNumdocap = vNumdocap;
	}


	public String getvUbiarch() {
		return vUbiarch;
	}


	public void setvUbiarch(String vUbiarch) {
		this.vUbiarch = vUbiarch;
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


	public Date getdFecdesde() {
		return dFecdesde;
	}


	public void setdFecdesde(Date dFecdesde) {
		this.dFecdesde = dFecdesde;
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


	public String getvExtension() {
		return vExtension;
	}


	public void setvExtension(String vExtension) {
		this.vExtension = vExtension;
	}


	public Date getdFecreg() {
		return dFecreg;
	}


	public void setdFecreg(Date dFecreg) {
		this.dFecreg = dFecreg;
	}


	public String getvNombreArchivo() {
		return vNombreArchivo;
	}


	public void setvNombreArchivo(String vNombreArchivo) {
		this.vNombreArchivo = vNombreArchivo;
	}


	public String obtenerRutaAbsolutaDocAprobacion() {
		return this.getvUbiarch()+this.getvNombreArchivo()+"."+this.getvExtension();
	}


	public Date getdFhasta() {
		return dFhasta;
	}


	public void setdFhasta(Date dFhasta) {
		this.dFhasta = dFhasta;
	}
 
	 
}
