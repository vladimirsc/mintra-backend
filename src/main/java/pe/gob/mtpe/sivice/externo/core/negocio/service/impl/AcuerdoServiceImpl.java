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
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.BandejaActas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ActasDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.AcuerdoDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;

@Service("AcuerdoService")
@Transactional(readOnly = true)
public class AcuerdoServiceImpl implements pe.gob.mtpe.sivice.externo.core.negocio.service.AcuerdoService {
	
	private static final Logger logger = LoggerFactory.getLogger(AcuerdoServiceImpl.class);
	
	@Autowired
	private AcuerdoDao acuerdoDao;
	
	@Autowired
	private SesionDao sesionDao;
	
	@Autowired
	private ActasDao actasDao;

	@Override
	public List<Acuerdos> listar() {
		return acuerdoDao.listar();
	}

	@Override
	public Acuerdos buscarPorId(Acuerdos acuerdos) {
		return acuerdoDao.buscarPorId(acuerdos);
	}

	@Override
	public List<Acuerdos> buscar(Acuerdos acuerdos) {
		return acuerdoDao.buscar(acuerdos);
	}

	@Override
	public Acuerdos Registrar(Acuerdos acuerdos) {
		return acuerdoDao.Registrar(acuerdos);
	}

	@Override
	public Acuerdos Actualizar(Acuerdos acuerdos) {
		return acuerdoDao.Actualizar(acuerdos);
	}

	@Override
	public Acuerdos Eliminar(Acuerdos acuerdos) {
		return acuerdoDao.Eliminar(acuerdos);
	}

	@Override
	public List<BandejaActas> buscarAcuerdosPorSesion(Sesiones sesiones) { 
		List<Sesiones> listaSesion = new ArrayList<Sesiones>();
		List<BandejaActas> listarBandejaActas = new ArrayList<BandejaActas>();
		
		
		listaSesion =sesionDao.buscar(sesiones);
		if(listaSesion!=null) {
			logger.info("==================== INGRESO A BUSCAR ACTAS "+listaSesion.size());
			 int contador = 0;
			for(Sesiones i: listaSesion) {
				Actas acta = new Actas(); 
				acta= actasDao.buscarActaPorIdSesion(i.getsEsionidpk());
				if(acta!=null) {
					contador = contador+1;
					BandejaActas bandejaActas = new BandejaActas();
					bandejaActas.setItem(contador);
					bandejaActas.setCodigoSesion(i.getvCodsesion());
					bandejaActas.setTipoSesion(i.getTipoSesiones().getvDesnombre());
					bandejaActas.setFechaSesion(i.getdFecreacion());
					bandejaActas.setCodigoacta(acta.getvCodacta());
					bandejaActas.setIdacta(acta.getaCtaidpk());
					listarBandejaActas.add(bandejaActas);
				}
				
			}
		}
		
		return listarBandejaActas;
	}

	@Override
	public List<Acuerdos> listarAcuerdosPorActa(Actas acta) { 
		return acuerdoDao.listarAcuerdosPorActa(acta);
	}

}
