package pe.gob.mtpe.sivice.externo.core.accesodatos.entity;

import java.io.Serializable;

public class InformacionUsuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long    nIdRol;
	String  vNombreRol;
	Long    nIdUsuario;
	String  vNombreUsuario;
	String  vApellidoPaterno;
	String  vApellidoMaterno;
	String  vCorreo;
	Long    nIdRegion;
	String  vNombreRegion;
	Long    nIdConsejo;
	
	public Long getnIdRol() {
		return nIdRol;
	}
	public void setnIdRol(Long nIdRol) {
		this.nIdRol = nIdRol;
	}
	public String getvNombreRol() {
		return vNombreRol;
	}
	public void setvNombreRol(String vNombreRol) {
		this.vNombreRol = vNombreRol;
	}
	public Long getnIdUsuario() {
		return nIdUsuario;
	}
	public void setnIdUsuario(Long nIdUsuario) {
		this.nIdUsuario = nIdUsuario;
	}
	public String getvNombreUsuario() {
		return vNombreUsuario;
	}
	public void setvNombreUsuario(String vNombreUsuario) {
		this.vNombreUsuario = vNombreUsuario;
	}
	public String getvApellidoPaterno() {
		return vApellidoPaterno;
	}
	public void setvApellidoPaterno(String vApellidoPaterno) {
		this.vApellidoPaterno = vApellidoPaterno;
	}
	public String getvApellidoMaterno() {
		return vApellidoMaterno;
	}
	public void setvApellidoMaterno(String vApellidoMaterno) {
		this.vApellidoMaterno = vApellidoMaterno;
	}
	public String getvCorreo() {
		return vCorreo;
	}
	public void setvCorreo(String vCorreo) {
		this.vCorreo = vCorreo;
	}
	 
	public Long getnIdRegion() {
		return nIdRegion;
	}
	public void setnIdRegion(Long nIdRegion) {
		this.nIdRegion = nIdRegion;
	}
	public String getvNombreRegion() {
		return vNombreRegion;
	}
	public void setvNombreRegion(String vNombreRegion) {
		this.vNombreRegion = vNombreRegion;
	}
 	 public Long getnIdConsejo() {
		return nIdConsejo;
	}
	public void setnIdConsejo(Long nIdConsejo) {
		this.nIdConsejo = nIdConsejo;
	}
	public String  obtnerNombreCompleto() {
		 return this.vApellidoPaterno+" "+this.vApellidoMaterno+","+this.vNombreUsuario;
	 }

}
