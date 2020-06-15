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
@Table(name = "TBS_FIRMANTES")
public class Firmantes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBS_FIRMANTES") 
	@SequenceGenerator(name="SEQ_TBS_FIRMANTES",sequenceName="DB_TRAMITE.SEQ_TBS_FIRMANTES", allocationSize=1) 
	@Column(name = "FIRMANTE_ID_PK")
	private Long fIrmanteidpk;

	@ManyToOne
	@JoinColumn(name = "ACTA_FK",nullable = false, insertable = true, updatable = true)
	private Actas actas;
	
	@Column(name = "V_ENTIDAD")
	private String vEntidad;
	
	@Column(name = "V_TIPO_DOCUMENTO")
	private String vTipodocumento;
	
	@Column(name = "V_NUMERO_DOCUMENTO")
	private String vNumerodocumento;
	
	@Column(name = "V_NOMBRE")
	private String vNombre;
	
	@Column(name = "V_TIPO")
	private String vTipo;
	
	@Column(name = "C_FLGASISTIO")
	private String cFlgasistio;
	
	@Column(name = "C_FLGELIMINO")
	private String cFlgelimino;
	
	@Column(name = "N_USUREG")
	private String nFsureg;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	@Column(name = "D_FECREG")
	private Date dFecreg;
	
	@Column(name = "N_USUMODIFICA")
	private String nUsumodifica;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	@Column(name = "D_FECMODIFICA")
	private Date dFecmodifica;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	@Column(name = "D_FECELIMINA")
	private Date dFecelimina;
 
	public Firmantes() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgelimino="0";
	}

	public Long getfIrmanteidpk() {
		return fIrmanteidpk;
	}

	public void setfIrmanteidpk(Long fIrmanteidpk) {
		this.fIrmanteidpk = fIrmanteidpk;
	}

	public Actas getActas() {
		return actas;
	}

	public void setActas(Actas actas) {
		this.actas = actas;
	}

	public String getvEntidad() {
		return vEntidad;
	}

	public void setvEntidad(String vEntidad) {
		this.vEntidad = vEntidad;
	}

	public String getvTipodocumento() {
		return vTipodocumento;
	}

	public void setvTipodocumento(String vTipodocumento) {
		this.vTipodocumento = vTipodocumento;
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

	public String getvTipo() {
		return vTipo;
	}

	public void setvTipo(String vTipo) {
		this.vTipo = vTipo;
	}

	public String getcFlgasistio() {
		return cFlgasistio;
	}

	public void setcFlgasistio(String cFlgasistio) {
		this.cFlgasistio = cFlgasistio;
	}

	public String getcFlgelimino() {
		return cFlgelimino;
	}

	public void setcFlgelimino(String cFlgelimino) {
		this.cFlgelimino = cFlgelimino;
	}

	public String getnFsureg() {
		return nFsureg;
	}

	public void setnFsureg(String nFsureg) {
		this.nFsureg = nFsureg;
	}

	public Date getdFecreg() {
		return dFecreg;
	}

	public void setdFecreg(Date dFecreg) {
		this.dFecreg = dFecreg;
	}

	public String getnUsumodifica() {
		return nUsumodifica;
	}

	public void setnUsumodifica(String nUsumodifica) {
		this.nUsumodifica = nUsumodifica;
	}

	public Date getdFecmodifica() {
		return dFecmodifica;
	}

	public void setdFecmodifica(Date dFecmodifica) {
		this.dFecmodifica = dFecmodifica;
	}

	public Date getdFecelimina() {
		return dFecelimina;
	}

	public void setdFecelimina(Date dFecelimina) {
		this.dFecelimina = dFecelimina;
	}

	 
}
