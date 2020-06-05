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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBS_SEGUIMIENTOS")
public class Seguimientos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBS_SEGUIMIENTOS") 
	@SequenceGenerator(name="SEQ_TBS_SEGUIMIENTOS",sequenceName="DB_TRAMITE.SEQ_TBS_SEGUIMIENTOS", allocationSize=1) 
	@Column(name = "SEGIMIENTO_ID_PK")
	private Long sEgimientoidpk;

	@Column(name = "CONSEJERO_FK")
	private Long cOnsejerofk;

	@Column(name = "ACUERDO_FK")
	private Long aCuerdofk;

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

	@Column(name = "V_UBIARCH")
	private String vUbiarch;

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

	public Seguimientos() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgeliminado="0";
	}

	public Long getsEgimientoidpk() {
		return sEgimientoidpk;
	}

	public void setsEgimientoidpk(Long sEgimientoidpk) {
		this.sEgimientoidpk = sEgimientoidpk;
	}

	public Long getcOnsejerofk() {
		return cOnsejerofk;
	}

	public void setcOnsejerofk(Long cOnsejerofk) {
		this.cOnsejerofk = cOnsejerofk;
	}

	public Long getaCuerdofk() {
		return aCuerdofk;
	}

	public void setaCuerdofk(Long aCuerdofk) {
		this.aCuerdofk = aCuerdofk;
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

	public String getvUbiarch() {
		return vUbiarch;
	}

	public void setvUbiarch(String vUbiarch) {
		this.vUbiarch = vUbiarch;
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

	 

}
