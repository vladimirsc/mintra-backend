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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Firmantes;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Invitados;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ActasDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AcuerdoDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AsistenciaDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FirmantesDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.InvitadosDao;
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
	
	@Autowired
	private ConsejeroDao consejeroDao;
	
	@Autowired
	private InvitadosDao invitadosDao;

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
		List<Firmantes> listaFirmantes = new ArrayList<Firmantes>();
		Actas acta = new Actas();
		acta=buscarActaPorIdSesion(idSesion);
		 if(acta!=null) {
			
			listaFirmantes = firmantesDao.listarFirmantesPorActa(acta.getaCtaidpk());
			
			if(listaFirmantes.isEmpty()) { //Registramos los formantes de la entidad asistencia
				List<Asistencias> listarAsistentes = new ArrayList<Asistencias>();
				listarAsistentes = asistenciaDao.listarConsejerosAsistencia(idSesion);
				if(listarAsistentes!=null) {
					for(Asistencias i: listarAsistentes) {
						 Firmantes firmantes = new Firmantes();
						 
						if(i.getConsejero() != null) { // es consejero
							Consejeros consejero = new Consejeros();
							consejero.setcOnsejeroidpk(i.getConsejero().getcOnsejeroidpk());
							consejero = consejeroDao.buscarPorId(consejero);
							firmantes.setActas(acta);
							firmantes.setvEntidad(consejero.getvEntidad());
							firmantes.setvTipodocumento(consejero.getvTipdocumento());
							firmantes.setvNumerodocumento(consejero.getvNumdocumento());
							firmantes.setvNombre(consejero.getvDesnombre()+" "+consejero.getvDesappaterno()+" "+consejero.getvDesapmaterno());
							firmantes.setvTipo(consejero.getvTpconsejero());
							firmantes.setcFlgasistio("0");
							firmantes = firmantesDao.Registrar(firmantes);
							logger.info("============= CONSEJEROS ");
						}else { // es invitado
							Invitados invitados = new Invitados();
							invitados.setiNvitadosidpk(i.getiNvitadosfk());
							invitados= invitadosDao.buscarPorId(invitados);
							firmantes.setActas(acta);
							firmantes.setvEntidad(invitados.getEntidad().getvDesnombre());
							firmantes.setvTipodocumento(invitados.getTipodocumento().getvDesabreviado());
							firmantes.setvNumerodocumento(invitados.getvNumerodocumento());
							firmantes.setvNombre(invitados.getvNombre()+" "+invitados.getvApellido_paterno()+" "+invitados.getvApellido_materno());
							firmantes.setvTipo("INVITADO");
							firmantes.setcFlgasistio("0");
							firmantes = firmantesDao.Registrar(firmantes);
							logger.info("============= INVITADOS ");
						}
						
						
						logger.info("============="+i.getaSistenciaidpk());
						
					}
					
					listaFirmantes = firmantesDao.listarFirmantesPorActa(acta.getaCtaidpk());
				}
			} 
			 
		 }

		return listaFirmantes;
	}

	@Override
	public Acuerdos registrarAcueros(Acuerdos acuerdos) { 
		return acuerdoDao.Registrar(acuerdos);
	}

	@Override
	public Firmantes actualizarFirmante(Firmantes firmantes) {
		Firmantes generico = new Firmantes();
		generico.setfIrmanteidpk(firmantes.getfIrmanteidpk());
		generico = firmantesDao.buscarPorId(generico);
		generico.setcFlgasistio(firmantes.getcFlgasistio());
		return firmantesDao.Actualizar(generico);
	}

}
