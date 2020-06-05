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
@Table(name = "TBX_USUPERFROL")
public class UsuarioPerfilRol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_TBX_USUPERFROL") 
	@SequenceGenerator(name="SEQ_TBX_USUPERFROL",sequenceName="DB_TRAMITE.SEQ_TBX_USUPERFROL", allocationSize=1) 
	@Column(name = "USUPERFROL_ID_PK")
	private Long uSuperfrolidpk;

	@Column(name = "USUARIO_FK")
	private Long uSuariofk;

	@Column(name = "ROL_FK")
	private Long rOlfk;

	@Column(name = "PERFIL_FK")
	private Long pErfilfk;

	@Column(name = "D_FECREGISTRO")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date dFecregistro;

	@Column(name = "C_FLGELIMINO", length = 1)
	private String cFlgelimino;
	
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

	public UsuarioPerfilRol() {

	}
	
	@PrePersist
	protected void valoresIniciales() {
	  this.dFecreg =new Date();
	  this.cFlgelimino="0";
	}

	public Long getuSuperfrolidpk() {
		return uSuperfrolidpk;
	}

	public void setuSuperfrolidpk(Long uSuperfrolidpk) {
		this.uSuperfrolidpk = uSuperfrolidpk;
	}

	public Long getuSuariofk() {
		return uSuariofk;
	}

	public void setuSuariofk(Long uSuariofk) {
		this.uSuariofk = uSuariofk;
	}

	public Long getrOlfk() {
		return rOlfk;
	}

	public void setrOlfk(Long rOlfk) {
		this.rOlfk = rOlfk;
	}

	public Long getpErfilfk() {
		return pErfilfk;
	}

	public void setpErfilfk(Long pErfilfk) {
		this.pErfilfk = pErfilfk;
	}

	public Date getdFecregistro() {
		return dFecregistro;
	}

	public void setdFecregistro(Date dFecregistro) {
		this.dFecregistro = dFecregistro;
	}

	public String getcFlgelimino() {
		return cFlgelimino;
	}

	public void setcFlgelimino(String cFlgelimino) {
		this.cFlgelimino = cFlgelimino;
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
