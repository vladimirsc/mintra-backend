package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Comisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Sesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoSesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FijasDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.SesionDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.SesionService;

@Service("SesionService")
@Transactional(readOnly = true)
public class SesionServiceImpl implements SesionService {

	@Autowired
	private FijasDao fijasDao;

	@Autowired
	private SesionDao sesionDao;

	@Autowired
	private ComisionDao comisionDao;

	@Override
	public List<Sesiones> listar(Sesiones sesion) {
		return sesionDao.listar(sesion);
	}

	@Override
	public Sesiones buscarPorId(Sesiones sesion) {
		return sesionDao.buscarPorId(sesion);
	}

	@Override
	public List<Sesiones> buscar(Sesiones sesion) {
		return sesionDao.buscar(sesion);
	}

	 
	@Override
	public Sesiones Registrar(Sesiones sesion) {

		/*
		if (sesion.getComisionfk().getcOmisionidpk() != null) {
			Comisiones comisionbuscar = new Comisiones();
			comisionbuscar.setcOmisionidpk(sesion.getComisionfk().getcOmisionidpk());
			comisionbuscar = comisionDao.buscarPorId(comisionbuscar);
			sesion.setComisionfk(comisionbuscar);
		}*/

		//sesion.setConsejofk(sesion.getConsejofk());
		//sesion.setTipoSesiones(sesion.getTipoSesiones());

		return sesionDao.Registrar(sesion);

	}

	 
	@Override
	public Sesiones Actualizar(Long comision, Long tipoSesion, Sesiones sesion) {

		// opcional la comision solo CONSATCOMISIONES/CORSATCOMISIONES
		
	 
		if (comision != null) {
			Comisiones comisionbuscar = new Comisiones();
			comisionbuscar.setcOmisionidpk(comision);
			comisionbuscar = comisionDao.buscarPorId(comisionbuscar);
			sesion.setComisionfk(comisionbuscar);
		}

		TipoSesiones tipoSesiones = new TipoSesiones();
		tipoSesiones.settIposesionidpk(tipoSesion);
		tipoSesiones = fijasDao.buscarPorCodigoTipoSesion(tipoSesiones);

		
		sesion.setTipoSesiones(tipoSesiones);
		return sesionDao.Actualizar(sesion);
	}

	@Override
	public Sesiones Eliminar(Sesiones sesion) {
		return sesionDao.Eliminar(sesion);
	}

	@Override
	public List<Sesiones> buscarSesion(String nombresesion) {
		 
		if(nombresesion==null) {
			return null;
		}
		return sesionDao.buscarSesion(nombresesion);
	}

}
