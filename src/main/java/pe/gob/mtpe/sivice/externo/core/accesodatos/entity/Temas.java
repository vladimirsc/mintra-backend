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
@Table(name = "TBD_TEMAS")
public class Temas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBD_TEMAS")
	@SequenceGenerator(name = "SEQ_TBD_TEMAS", sequenceName = "DB_TRAMITE.SEQ_TBD_TEMAS", allocationSize = 1)
	@Column(name = "TEMA_ID_PK")
	private Long tEmaidpk;

	@Column(name = "SESION_FK")
	private Long sEsionfk;

	@Column(name = "V_DESCRIPCION")
	private String vDescripcion;

	@Column(name = "V_UBIARCH_1")
	private String vUbiarch1;

	@Column(name = "V_UBIARCH_2")
	private String vUbiarch2;

	@Column(name = "V_UBIARCH_3")
	private String vUbiarch3;

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

	@Column(name = "V_NOMBREARCHIVO1")
	private String vNombrearchivo1;

	@Column(name = "V_UBIARCHIVO1")
	private String vUbiarchivo1;

	@Column(name = "V_EXTARCHIVO1")
	private String vExtarchivo1;

	@Column(name = "V_NOMBREARCHIVO2")
	private String vNombrearchivo2;

	@Column(name = "V_UBIARCHIVO2")
	private String vUbiarchivo2;

	@Column(name = "V_EXTARCHIVO2")
	private String vExtarchivo2;

	@Column(name = "V_NOMBREARCHIVO3")
	private String vNombrearchivo3;

	@Column(name = "V_UBIARCHIVO3")
	private String vUbiarchivo3;

	@Column(name = "V_EXTARCHIVO3")
	private String vExtarchivo3;

	@ManyToOne
	@JoinColumn(name = "TIPOTEMA_FK")
	private TipoTemas tIpotemafk;

	public Temas() {

	}

	@PrePersist
	protected void valoresIniciales() {
		this.dFecreg = new Date();
		this.cFlgeliminado = "0";
	}

	public Long gettEmaidpk() {
		return tEmaidpk;
	}

	public void settEmaidpk(Long tEmaidpk) {
		this.tEmaidpk = tEmaidpk;
	}

	public Long getsEsionfk() {
		return sEsionfk;
	}

	public void setsEsionfk(Long sEsionfk) {
		this.sEsionfk = sEsionfk;
	}

	public String getvDescripcion() {
		return vDescripcion;
	}

	public void setvDescripcion(String vDescripcion) {
		this.vDescripcion = vDescripcion;
	}

	public String getvUbiarch1() {
		return vUbiarch1;
	}

	public void setvUbiarch1(String vUbiarch1) {
		this.vUbiarch1 = vUbiarch1;
	}

	public String getvUbiarch2() {
		return vUbiarch2;
	}

	public void setvUbiarch2(String vUbiarch2) {
		this.vUbiarch2 = vUbiarch2;
	}

	public String getvUbiarch3() {
		return vUbiarch3;
	}

	public void setvUbiarch3(String vUbiarch3) {
		this.vUbiarch3 = vUbiarch3;
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

	public String getvNombrearchivo1() {
		return vNombrearchivo1;
	}

	public void setvNombrearchivo1(String vNombrearchivo1) {
		this.vNombrearchivo1 = vNombrearchivo1;
	}

	public String getvUbiarchivo1() {
		return vUbiarchivo1;
	}

	public void setvUbiarchivo1(String vUbiarchivo1) {
		this.vUbiarchivo1 = vUbiarchivo1;
	}

	public String getvExtarchivo1() {
		return vExtarchivo1;
	}

	public void setvExtarchivo1(String vExtarchivo1) {
		this.vExtarchivo1 = vExtarchivo1;
	}

	public String getvNombrearchivo2() {
		return vNombrearchivo2;
	}

	public void setvNombrearchivo2(String vNombrearchivo2) {
		this.vNombrearchivo2 = vNombrearchivo2;
	}

	public String getvUbiarchivo2() {
		return vUbiarchivo2;
	}

	public void setvUbiarchivo2(String vUbiarchivo2) {
		this.vUbiarchivo2 = vUbiarchivo2;
	}

	public String getvExtarchivo2() {
		return vExtarchivo2;
	}

	public void setvExtarchivo2(String vExtarchivo2) {
		this.vExtarchivo2 = vExtarchivo2;
	}

	public String getvNombrearchivo3() {
		return vNombrearchivo3;
	}

	public void setvNombrearchivo3(String vNombrearchivo3) {
		this.vNombrearchivo3 = vNombrearchivo3;
	}

	public String getvUbiarchivo3() {
		return vUbiarchivo3;
	}

	public void setvUbiarchivo3(String vUbiarchivo3) {
		this.vUbiarchivo3 = vUbiarchivo3;
	}

	public String getvExtarchivo3() {
		return vExtarchivo3;
	}

	public void setvExtarchivo3(String vExtarchivo3) {
		this.vExtarchivo3 = vExtarchivo3;
	}

	public TipoTemas gettIpotemafk() {
		return tIpotemafk;
	}

	public void settIpotemafk(TipoTemas tIpotemafk) {
		this.tIpotemafk = tIpotemafk;
	}

	public String obtenerRutaAbsolutaArchivoTema1() {
		return this.getvUbiarchivo1()+ this.getvNombrearchivo1() + "." + this.getvExtarchivo1();
	}

	public String obtenerRutaAbsolutaArchivoTema2() {
		return this.getvUbiarchivo2() + this.getvNombrearchivo2() + "." + this.getvExtarchivo2();
	}

	public String obtenerRutaAbsolutaArchivoTema3() {
		return this.getvUbiarchivo3() + this.getvNombrearchivo3() + "." + this.getvExtarchivo3();
	}

}
