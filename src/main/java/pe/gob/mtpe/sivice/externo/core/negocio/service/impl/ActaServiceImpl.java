package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Actas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Acuerdos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Asistencias;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Firmantes;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ActasDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AcuerdoDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AsistenciaDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FirmantesDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ActaService; 

@Service("ActaService")
@Transactional(readOnly = true)
public class ActaServiceImpl implements ActaService {
	
	private static final Logger logger = LoggerFactory.getLogger(ActaServiceImpl.class);

	@Autowired
	private ActasDao actasDao;

	@Autowired
	private SesionDao sesionDao;

	@Autowired
	private FirmantesDao firmantesDao;
	
	@Autowired
	private AsistenciaDao asistenciaDao;
	
	@Autowired
	private AcuerdoDao acuerdoDao;

	@Override
	public List<Actas> listar() {
		return actasDao.listar();
	}

	@Override
	public Actas buscarPorId(Actas actas) {
		return actasDao.buscarPorId(actas);
	}

	@Override
	public List<Actas> buscar(Actas actas) {
		return actasDao.buscar(actas);
	}

	@Override
	public Actas Registrar(Actas actas) {
		return actasDao.Registrar(actas);
	}

	@Override
	public Actas Actualizar(Actas actas) {
		actas.getdFecmodifica();
		return actasDao.Actualizar(actas);
	}

	@Override
	public Actas Eliminar(Actas actas) {
		return actasDao.Eliminar(actas);
	}

	@Override
	public Actas descargarActa(Actas actas) {
		return actasDao.buscarPorId(actas);
	}

	@Override
	public Sesiones cabeceraActa(Sesiones sesiones) {
		return sesionDao.buscarPorId(sesiones);
	}

	@Override
	public List<Acuerdos> listaAcuerdosPorActa(Actas actas) {
		// BUSCAMOS AL SESION
		Sesiones sesion = new Sesiones();
		sesion.setsEsionidpk(actas.getsEsionfk());
		sesion = sesionDao.buscarPorId(sesion);

		Actas acta = new Actas();
		List<Acuerdos> listarAcuerdos = new ArrayList<Acuerdos>();
		if (sesion != null) {// BUSCAMOS EL ACTA
			acta = actasDao.buscarActaPorIdSesion(sesion.getsEsionidpk());
			if (acta != null) {
				// BUSCAMOS LOS ACUEROS
				listarAcuerdos = actasDao.listaAcuerdosPorActa(acta);
			}
		}

		return listarAcuerdos;
	}

	@Override
	public Actas buscarActaPorIdSesion(Long idSesion) {
		Actas acta = new Actas();
		acta = actasDao.buscarActaPorIdSesion(idSesion);
		return acta;
	}

	@Override
	public List<Firmantes> listarFirmentes(Long idSesion) {
		// BUSCAMOS AL SESION
		Actas acta = new Actas();
		acta=buscarActaPorIdSesion(idSesion);
		 if(acta!=null) {
			List<Firmantes> listaFirmantes = new ArrayList<Firmantes>();
			listaFirmantes = firmantesDao.listarFirmantesPorActa(acta.getaCtaidpk());
			
			if(listaFirmantes.isEmpty()) { //Registramos los formantes de la entidad asistencia
				List<Asistencias> listarAsistentes = new ArrayList<Asistencias>();
				listarAsistentes = asistenciaDao.listarConsejerosAsistencia(idSesion);
				if(listarAsistentes!=null) {
					for(Asistencias i: listarAsistentes) {
						logger.info("============="+i.getaSistenciaidpk());
						
					}
				}
			} 
			 
		 }

		return null;
	}

	@Override
	public Acuerdos registrarAcueros(Acuerdos acuerdos) { 
		return acuerdoDao.Registrar(acuerdos);
	}

}
