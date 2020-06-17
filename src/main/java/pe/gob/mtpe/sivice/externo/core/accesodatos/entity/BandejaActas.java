package pe.gob.mtpe.sivice.externo.core.accesodatos.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BandejaActas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int item;
	private Long idacta;
	private String codigoSesion;
	private String tipoSesion;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Lima")
	private Date fechaSesion;
	private String codigoacta;
	
	
	
	public BandejaActas() {
		super();
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public Long getIdacta() {
		return idacta;
	}
	public void setIdacta(Long idacta) {
		this.idacta = idacta;
	}
	public String getCodigoSesion() {
		return codigoSesion;
	}
	public void setCodigoSesion(String codigoSesion) {
		this.codigoSesion = codigoSesion;
	}
	public String getTipoSesion() {
		return tipoSesion;
	}
	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}
	 
	public Date getFechaSesion() {
		return fechaSesion;
	}
	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}
	public String getCodigoacta() {
		return codigoacta;
	}
	public void setCodigoacta(String codigoacta) {
		this.codigoacta = codigoacta;
	}
	
	
	
}
