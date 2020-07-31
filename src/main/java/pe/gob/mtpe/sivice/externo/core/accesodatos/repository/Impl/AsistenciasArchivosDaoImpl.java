package pe.gob.mtpe.sivice.externo.core.accesodatos.repository.Impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import pe.gob.mtpe.sivice.externo.core.accesodatos.base.BaseDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciasArchivos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AsistenciasArchivosDao;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Component
public class AsistenciasArchivosDaoImpl extends BaseDao<Long, AsistenciasArchivos> implements AsistenciasArchivosDao {

	@Override
	public AsistenciasArchivos cargarArchivo(AsistenciasArchivos asistenciasArchivos) {
		guardar(asistenciasArchivos);
		return asistenciasArchivos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AsistenciasArchivos descargarArchivo(Long idsession) {
		AsistenciasArchivos asistencia = new AsistenciasArchivos(); 
		EntityManager manager = createEntityManager();
		List<AsistenciasArchivos> lista = manager
				.createQuery("FROM AsistenciasArchivos b WHERE b.sEsionfk=:sesion AND b.vFlgeliminado=:eliminado ORDER BY b.aSistearchivoidpk DESC")
				.setParameter("sesion",idsession)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		
		if(lista.isEmpty()) {
			asistencia = null;
		}else {
			asistencia = lista.get(0);
		}
		
 
		return asistencia;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AsistenciasArchivos VerificarArchivo(Long idsession) {
		AsistenciasArchivos asistencia = new AsistenciasArchivos(); 
		EntityManager manager = createEntityManager();
		List<AsistenciasArchivos> lista = manager
				.createQuery("SELECT b FROM AsistenciasArchivos b WHERE b.sEsionfk=:sesion AND b.vFlgeliminado=:eliminado ORDER BY b.aSistearchivoidpk DESC")
				.setParameter("sesion",idsession)
				.setParameter("eliminado", ConstantesUtil.C_INDC_INACTIVO).getResultList();
		manager.close();
		
		if(lista.isEmpty()) {
			asistencia = null;
		}else {
			asistencia = lista.get(0);
		}
		
 
		return asistencia;
	}

}
