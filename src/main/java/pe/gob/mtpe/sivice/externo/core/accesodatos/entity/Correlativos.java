package pe.gob.mtpe.sivice.externo.core.accesodatos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBX_CORRELATIVO")
public class Correlativos implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CORRELATIVO_ID_PK")
	private Long cOrrelativoidpk;
	
	@Column(name = "V_REGION")
	private String  vRegion;
	
	@Column(name = "V_MODULO")
	private String  vModulo;
	
	@Column(name = "V_CONSEJO")
	private String  vConsejo;
	
	@Column(name = "V_TIPO")
	private String  vTipo;
	
	@Column(name = "N_VALOR_INICIAL")
	private Long   nValorInicial;

	public Long getcOrrelativoidpk() {
		return cOrrelativoidpk;
	}

	public void setcOrrelativoidpk(Long cOrrelativoidpk) {
		this.cOrrelativoidpk = cOrrelativoidpk;
	}

	public String getvRegion() {
		return vRegion;
	}

	public void setvRegion(String vRegion) {
		this.vRegion = vRegion;
	}

	public String getvModulo() {
		return vModulo;
	}

	public void setvModulo(String vModulo) {
		this.vModulo = vModulo;
	}

	public String getvConsejo() {
		return vConsejo;
	}

	public void setvConsejo(String vConsejo) {
		this.vConsejo = vConsejo;
	}

	public String getvTipo() {
		return vTipo;
	}

	public void setvTipo(String vTipo) {
		this.vTipo = vTipo;
	}

	public Long getnValorInicial() {
		return nValorInicial;
	}

	public void setnValorInicial(Long nValorInicial) {
		this.nValorInicial = nValorInicial;
	}
	
	

}
