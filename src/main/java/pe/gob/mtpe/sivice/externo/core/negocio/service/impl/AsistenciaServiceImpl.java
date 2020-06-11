package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.AsistenciaConsejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Asistencias;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.ComiConsej;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Invitados;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AsistenciaDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.InvitadosDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.AsistenciaService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Service("AsistenciaService")
@Transactional(readOnly = true)
public class AsistenciaServiceImpl implements AsistenciaService {

	private static final Logger logger = LoggerFactory.getLogger(AsistenciaServiceImpl.class);

	@Autowired
	private AsistenciaDao asistenciaDao;

	@Autowired
	private SesionDao SesionDao;

	@Autowired
	private ConsejeroDao consejeroDao;

	@Autowired
	private ComisionConsejeroDao comisionConsejeroDao;

	@Autowired
	private InvitadosDao invitadosDao;

 
	

	@Override
	public List<Asistencias> listar() {
		return asistenciaDao.listar();
	}

	@Override
	public Asistencias buscarPorId(Asistencias asistencia) {
		return asistenciaDao.buscarPorId(asistencia);
	}

	@Override
	public List<Asistencias> buscar(Asistencias asistencia) {
		return asistenciaDao.buscar(asistencia);
	}

	@Override
	public Asistencias Registrar(Asistencias asistencia) {
		return asistenciaDao.Registrar(asistencia);
	}

	@Override
	public Asistencias Actualizar(Asistencias asistencia) {
		return asistenciaDao.Actualizar(asistencia);
	}

	@Override
	public Asistencias Eliminar(Asistencias asistencia) {
		return asistenciaDao.Eliminar(asistencia);
	}

	@Override
	public Sesiones buscarSesion(Sesiones sesiones) {
		// TODO Auto-generated method stub
		return SesionDao.buscarPorId(sesiones);
	}

	@Override
	public List<AsistenciaConsejeros> listarConsejerosAsistencia(Long idsesion) {
		Sesiones sesiones = new Sesiones();
		List<AsistenciaConsejeros> generica = new ArrayList<AsistenciaConsejeros>();
		List<Asistencias> listAsistencia = new ArrayList<Asistencias>();

		try {
			// OBTENEMOS INFORMACION DE LA SESSION
			sesiones.setsEsionidpk(idsesion);
			sesiones = SesionDao.buscarPorId(sesiones);

			Long cantdadAsistentes = asistenciaDao.cantidadAsistentesPorSesion(idsesion); // CANTIDAD DE ASISTENTES DE
																							// LA SESION
			int tipoConsejo = sesiones.getConsejofk().getcOnsejoidpk().intValue();

			if (tipoConsejo == 1 || tipoConsejo == 2) {

				if (cantdadAsistentes == 0) { // registramos
					List<Consejeros> listaConsejeros = new ArrayList<Consejeros>();
					listaConsejeros = consejeroDao.listarConsejerosPorConsejo(Long.valueOf(tipoConsejo));

					// INSERTAMOS LOS CONSEJEROS
					for (Consejeros i : listaConsejeros) {
						Consejeros filaconsejero = new Consejeros();
						filaconsejero.setcOnsejeroidpk(i.getcOnsejeroidpk());
						Asistencias asistencia = new Asistencias();
						asistencia.setsEsionfk(idsesion);
						asistencia.setConsejero(filaconsejero);
						asistencia.setcFlgasistio(ConstantesUtil.C_FLAG_ASISTIO_NO);
						asistencia.setvHoentrada(ConstantesUtil.C_HORA_INICIO_DEFAULT);
						asistencia.setvHosalida(ConstantesUtil.C_HORA_FINALDEFAULT);
						asistenciaDao.Registrar(asistencia);
					}
				}

			} else if (tipoConsejo == 3 || tipoConsejo == 4) {

				if (cantdadAsistentes == 0) {
					// BUSCAMOS EN LA TABLA CONSEJERO COMISION
					List<ComiConsej> listaComicionConsejero = new ArrayList<ComiConsej>();
					Long idcomision = sesiones.getComisionfk().getcOmisionidpk();

					listaComicionConsejero = comisionConsejeroDao.listaConsejerosPorComision(idcomision);
					logger.info("=============== COMISONES ====================" + listaComicionConsejero.size());
					for (ComiConsej i : listaComicionConsejero) {
						logger.info("============" + i.getConsejero().getcOnsejeroidpk());
						Consejeros filaconsejero = new Consejeros();
						filaconsejero.setcOnsejeroidpk(i.getConsejero().getcOnsejeroidpk());
						Asistencias asistencia = new Asistencias();
						asistencia.setsEsionfk(idsesion);
						asistencia.setConsejero(filaconsejero);
						asistencia.setcFlgasistio(ConstantesUtil.C_FLAG_ASISTIO_NO);
						asistencia.setvHoentrada(ConstantesUtil.C_HORA_INICIO_DEFAULT);
						asistencia.setvHosalida(ConstantesUtil.C_HORA_FINALDEFAULT);
						asistenciaDao.Registrar(asistencia);
					}

				}

			}

			listAsistencia = asistenciaDao.listarConsejerosAsistencia(idsesion);

			// LLENAMOS LA TABLA GENERICA
			for (Asistencias i : listAsistencia) {
				if(i.getConsejero()!=null) {
					AsistenciaConsejeros fila = new AsistenciaConsejeros();
					fila.setIdAsistencia(i.getaSistenciaidpk());
					fila.setAsistio(i.getcFlgasistio());
					fila.setTipoDocumento(i.getConsejero().getvTipdocumento());
					fila.setNumeroDocumento(i.getConsejero().getvNumdocumento());
					fila.setApellidosNombre(i.getConsejero().getvDesnombre() + "," + i.getConsejero().getvDesappaterno()
							+ " " + i.getConsejero().getvDesapmaterno());
					fila.setEntidad(i.getConsejero().getvEntidad());
					fila.setHoraEntrada(i.getvHoentrada());
					fila.setHoraSalida(i.getvHosalida());
					generica.add(fila);
					
				}else { //PARA LOS INVITADOS
					if(i.getiNvitadosfk()!=null) {
						TipoDocumentos tipoDocumentos = new TipoDocumentos();
						Invitados invitado  = new Invitados();
						invitado.setiNvitadosidpk(i.getiNvitadosfk());
						invitado.setTipodocumento(tipoDocumentos);
						invitado = invitadosDao.buscarPorId(invitado);
						if(invitado !=null) {
							AsistenciaConsejeros fila = new AsistenciaConsejeros();
							fila.setIdAsistencia(i.getaSistenciaidpk());
							fila.setAsistio(i.getcFlgasistio());
							fila.setTipoDocumento(invitado.getTipodocumento().getvDesabreviado());
							fila.setNumeroDocumento(invitado.getvNumerodocumento());
							fila.setApellidosNombre(invitado.getvNombre() + "," + invitado.getvApellido_paterno()
									+ " " +invitado.getvApellido_materno());
							fila.setEntidad(invitado.getEntidad().getvDesnombre());
							fila.setHoraEntrada(i.getvHoentrada());
							fila.setHoraSalida(i.getvHosalida());
							generica.add(fila);
						}
					}
					
					
				}
				
			}
			
			

		} catch (Exception e) {
			generica = null;
			logger.info("==========" + e.getMessage() + "***" + e.getCause());
		}
		return generica;
	}

	@Override
	public Long cantidadAsistentesPorSesion(Long idsesion) {

		return asistenciaDao.cantidadAsistentesPorSesion(idsesion);
	}

	@Override
	public Invitados RegistrarInvitados(Invitados invitados) {
		Invitados infoInvitados = new Invitados();
		infoInvitados = invitadosDao.Registrar(invitados);
		
		if(infoInvitados!=null) {
			Asistencias asistencia = new Asistencias();
			asistencia.setsEsionfk(infoInvitados.getsEsionfk());
			asistencia.setiNvitadosfk(infoInvitados.getiNvitadosidpk());
			asistencia.setcFlgasistio(ConstantesUtil.C_FLAG_ASISTIO_NO);
			asistencia.setvHoentrada(ConstantesUtil.C_HORA_INICIO_DEFAULT);
			asistencia.setvHosalida(ConstantesUtil.C_HORA_FINALDEFAULT);
			asistencia=asistenciaDao.Registrar(asistencia);
			
			infoInvitados.setaSistenciafk(asistencia.getaSistenciaidpk());
			invitadosDao.Actualizar(infoInvitados);
		}
		
		 return infoInvitados; 

	}

}
