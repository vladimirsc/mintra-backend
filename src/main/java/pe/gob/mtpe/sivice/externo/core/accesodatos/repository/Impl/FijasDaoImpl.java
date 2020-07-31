package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Entidades;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Profesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Roles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoComisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoSesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoTemas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Tipoconsejero;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FijasDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class FijasDaoImpl extends BaseDao<Long, Profesiones> implements FijasDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Profesiones> listarProfesiones() {
		EntityManager manager = createEntityManager();
		List<Profesiones> lista = manager
				.createQuery("FROM Profesiones p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public Profesiones buscarPorCodigoProfesion(Profesiones profesion) {
		return buscarId(profesion.getpRofesionidpk());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoDocumentos> listarTipoDocumentos() {
		EntityManager manager = createEntityManager();
		List<TipoDocumentos> lista = manager
				.createQuery("FROM TipoDocumentos p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public TipoDocumentos buscarPorCodigoTipoDocumento(TipoDocumentos tipoDocumentos) {
		TipoDocumentos vtipodoc = new TipoDocumentos();
		TipoDocumentos vtipodocfinal = new TipoDocumentos();
		EntityManager manager = createEntityManager();
		vtipodocfinal = manager.find(vtipodoc.getClass(), tipoDocumentos.gettPdocumentoidpk());
		manager.close();
		return vtipodocfinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tipoconsejero> listarTipoConsejeros() {
		EntityManager manager = createEntityManager();
		List<Tipoconsejero> lista = manager
				.createQuery("FROM Tipoconsejero p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public Tipoconsejero buscarPorCodigoTipoConsejero(Tipoconsejero tipoconsejero) {
		Tipoconsejero vtipoconsejero = new Tipoconsejero();
		Tipoconsejero vtipoconsejerofinal = new Tipoconsejero();
		EntityManager manager = createEntityManager();
		vtipoconsejerofinal = manager.find(vtipoconsejero.getClass(), tipoconsejero.gettPconsejeroidpk());
		manager.close();
		return vtipoconsejerofinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Regiones> listarTipoRegiones() {
		EntityManager manager = createEntityManager();
		List<Regiones> lista = manager
				.createQuery("FROM Regiones p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public Regiones buscarPorCodigoRegion(Regiones regiones) {
		Regiones vregion = new Regiones();
		Regiones vregionfinal = new Regiones();
		EntityManager manager = createEntityManager();
		vregionfinal = manager.find(vregion.getClass(), regiones.getrEgionidpk());
		manager.close();
		return vregionfinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Regiones buscarPorNombreRegion(String nombreregion) {
		Regiones vregion = new Regiones();
		EntityManager manager = createEntityManager();
		List<Regiones> lista = manager
				.createQuery("FROM Regiones p WHERE p.vDesnombre=:nombreregion AND p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("nombreregion", nombreregion)
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			vregion = null;
		}else {
			vregion = lista.get(0);
		}
		return vregion;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Consejos> listarConsejos() {

		EntityManager manager = createEntityManager();
		List<Consejos> lista = manager
				.createQuery("FROM Consejos p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public Consejos buscarPorCodigoConsejo(Consejos consejos) {
		Consejos vconsejo = new Consejos();
		Consejos vconsejofinal = new Consejos();
		EntityManager manager = createEntityManager();
		vconsejofinal = manager.find(vconsejo.getClass(), consejos.getcOnsejoidpk());
		manager.close();
		return vconsejofinal;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Long BuscarConsejoPorNombre(String rolusuario) {
       Long codigoconsejo = 0L;
       
       String consejo=null;
       switch(rolusuario) {
		case ConstantesUtil.C_ROLE_ADMCONSSAT: consejo=ConstantesUtil.C_CONSSAT;     break;
		case ConstantesUtil.C_ROLE_ADMCORSSAT: consejo=ConstantesUtil.C_CONRSAT;     break; 
		case ConstantesUtil.C_ROLE_CONSSAT:    consejo=ConstantesUtil.C_CONSSAT;     break;
		case ConstantesUtil.C_ROLE_CORSSAT:    consejo=ConstantesUtil.C_CONRSAT;     break; 
		case ConstantesUtil.C_ROLE_OPECONSSAT: consejo=ConstantesUtil.C_COMICONSSAT; break; 
		case ConstantesUtil.C_ROLE_OPECORSSAT: consejo=ConstantesUtil.C_COMICORSAT;  break;
		}
       
       
		EntityManager manager = createEntityManager();
		List<Consejos> lista = manager
				.createQuery("SELECT p FROM Consejos p WHERE vDesnombre=:nombreconsejo  AND p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("nombreconsejo", consejo)
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		
		if (lista.isEmpty()) {
			return null;
		}else {
			codigoconsejo = lista.get(0).getcOnsejoidpk();
		}
		return codigoconsejo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoComisiones> listarTipoComisiones() {

		EntityManager manager = createEntityManager();
		List<TipoComisiones> lista = manager
				.createQuery("FROM TipoComisiones p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public TipoComisiones buscarPorCodigoTipoComision(TipoComisiones tipoComisiones) {
		TipoComisiones vtipocomision = new TipoComisiones();
		TipoComisiones vtipocomisionfinal = new TipoComisiones();
		EntityManager manager = createEntityManager();
		vtipocomisionfinal = manager.find(vtipocomision.getClass(), tipoComisiones.gettIpocomsidpk());
		manager.close();
		return vtipocomisionfinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoSesiones> listarTipoSesion() {
		EntityManager manager = createEntityManager();
		List<TipoSesiones> lista = manager
				.createQuery("FROM TipoSesiones p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public TipoSesiones buscarPorCodigoTipoSesion(TipoSesiones tiposesion) {
		TipoSesiones vtiposesion = new TipoSesiones();
		TipoSesiones vtiposesionfinal = new TipoSesiones();
		EntityManager manager = createEntityManager();
		vtiposesionfinal = manager.find(vtiposesion.getClass(), tiposesion.gettIposesionidpk());
		manager.close();
		return vtiposesionfinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoTemas> listarTipoTemas() {
		EntityManager manager = createEntityManager();
		List<TipoTemas> lista = manager
				.createQuery("FROM TipoTemas p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public TipoTemas buscarPorCodigoTipoTema(TipoTemas temas) {
		TipoTemas vtipotema = new TipoTemas();
		TipoTemas vtipotemafinal = new TipoTemas();
		EntityManager manager = createEntityManager();
		vtipotemafinal = manager.find(vtipotema.getClass(), temas.gettIpotemaidpk());
		manager.close();
		return vtipotemafinal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entidades> listarEntidades() {
		EntityManager manager = createEntityManager();
		List<Entidades> lista = manager
				.createQuery("SELECT p FROM Entidades p WHERE p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Entidades buscarPorEntidad(Entidades entidad) {
		EntityManager manager = createEntityManager();
		List<Entidades> lista = manager.createQuery(
				"FROM Entidades p WHERE p.eNtidadidpk=:entidadfk AND  p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("entidadfk", entidad.geteNtidadidpk())
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> listaRoles() {
		EntityManager manager = createEntityManager();
		List<Roles> lista = manager
				.createQuery("FROM Roles r WHERE r.cFlgactivo=:activo AND r.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@Override
	public Roles buscaRoles(Roles rol) {
		buscarId(rol.getrOlidpk());
		return rol;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Regiones> listarTipoRegiones(Regiones regiones) {
		EntityManager manager = createEntityManager();
		List<Regiones> lista = manager
				.createQuery("SELECT p FROM Regiones p WHERE p.rEgionidpk=:idregion AND p.cFlgactivo=:activo AND p.cFlgeliminado=:eliminado")
				.setParameter("idregion", regiones.getrEgionidpk())
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> listaRolesCorssat() {
		EntityManager manager = createEntityManager();
		List<Roles> lista = manager
				.createQuery("SELECT r FROM Roles r WHERE r.vDesnombre IN('"+ConstantesUtil.C_ROLE_ADMCORSSAT+"','"+ConstantesUtil.C_ROLE_CORSSAT+"','"+ConstantesUtil.C_ROLE_OPECORSSAT+"') AND r.cFlgactivo=:activo AND r.cFlgeliminado=:eliminado")
				.setParameter("activo", ConstantesUtil.C_INDC_ACTIVO)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		if (lista.isEmpty()) {
			return null;
		}
		return lista;
	}

 

}
