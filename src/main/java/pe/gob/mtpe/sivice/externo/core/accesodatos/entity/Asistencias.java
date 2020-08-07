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
@Table(name = "TBD_ASISTENCIAS")
public class Asistencias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBD_ASISTENCIAS")
	@SequenceGenerator(name = "SEQ_TBD_ASISTENCIAS", sequenceName = "DB_TRAMITE.SEQ_TBD_ASISTENCIAS", allocationSize = 1)
	@Column(name = "ASISTENCIA_ID_PK")
	private Long aSistenciaidpk;

	@Column(name = "V_HOENTRADA")
	private String vHoentrada;

	@Column(name = "SESION_FK")
	private Long sEsionfk;

	@Column(name = "V_HOSALIDA")
	private String vHosalida;

	@Column(name = "C_FLGASISTIO", length = 1)
	private String cFlgasistio;

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
	
	@ManyToOne
	@JoinColumn(name="CONSEJERO_FK")
	private  Consejeros consejero;
	
	@Column(name = "INVITADOS_FK")
	private Long iNvitadosfk;

	public Asistencias() {

	}

	@PrePersist
	protected void valoresIniciales() {
		this.dFecreg = new Date();
		this.cFlgeliminado = "0";
	}

	public Long getaSistenciaidpk() {
		return aSistenciaidpk;
	}

	public void setaSistenciaidpk(Long aSistenciaidpk) {
		this.aSistenciaidpk = aSistenciaidpk;
	}


	public String getvHoentrada() {
		return vHoentrada;
	}

	public void setvHoentrada(String vHoentrada) {
		this.vHoentrada = vHoentrada;
	}

	public Long getsEsionfk() {
		return sEsionfk;
	}

	public void setsEsionfk(Long sEsionfk) {
		this.sEsionfk = sEsionfk;
	}

	public String getvHosalida() {
		return vHosalida;
	}

	public void setvHosalida(String vHosalida) {
		this.vHosalida = vHosalida;
	}

	public String getcFlgasistio() {
		return cFlgasistio;
	}

	public void setcFlgasistio(String cFlgasistio) {
		this.cFlgasistio = cFlgasistio;
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

	public Consejeros getConsejero() {
		return consejero;
	}

	public void setConsejero(Consejeros consejero) {
		this.consejero = consejero;
	}

	public Long getiNvitadosfk() {
		return iNvitadosfk;
	}

	public void setiNvitadosfk(Long iNvitadosfk) {
		this.iNvitadosfk = iNvitadosfk;
	}

	 
	 
	
}
