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
@Table(name = "TBX_USUARIOROL")
public class UsuarioRol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBX_USUPERFROL")
	@SequenceGenerator(name = "SEQ_TBX_USUPERFROL", sequenceName = "DB_TRAMITE_BK.SEQ_TBX_USUPERFROL", allocationSize = 1)
	@Column(name = "USUARIOROL_ID_PK")
	private Long uSuariorolidpk;

	@ManyToOne
	@JoinColumn(name = "USUARIO_FK",nullable = false, insertable = true, updatable = true)
	private Usuarios usuario;

	@ManyToOne
	@JoinColumn(name = "ROL_FK",nullable = false, insertable = true, updatable = true)
	private Roles roles;
 
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

	@Column(name = "C_FLGACTIVO")
	private String cFlgactivo;

	public UsuarioRol() {

	}

	@PrePersist
	protected void valoresIniciales() {
		this.dFecreg = new Date();
		this.cFlgelimino = "0";
		this.cFlgactivo="1";
	}
 
	public Long getuSuariorolidpk() {
		return uSuariorolidpk;
	}

	public void setuSuariorolidpk(Long uSuariorolidpk) {
		this.uSuariorolidpk = uSuariorolidpk;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
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

	public String getcFlgactivo() {
		return cFlgactivo;
	}

	public void setcFlgactivo(String cFlgactivo) {
		this.cFlgactivo = cFlgactivo;
	}

	 
	

}
