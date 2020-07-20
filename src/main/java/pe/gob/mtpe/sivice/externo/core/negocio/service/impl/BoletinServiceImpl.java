package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Boletines;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.BoletinDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.BoletinService;
import pe.gob.mtpe.sivice.externo.core.util.FechasUtil;
 

@Service("BoletinService")
@Transactional(readOnly = true)
public class BoletinServiceImpl implements BoletinService {

	@Autowired
	private BoletinDao boletinDao;
	
	@Override
	public List<Boletines> listar(Boletines boletines) {
		return boletinDao.listar(boletines);
	}

	@Override
	public Boletines buscarPorId(Boletines boletines) {
		return boletinDao.buscarPorId(boletines);
	}

	@Override
	public Boletines Registrar(Boletines boletines) {
		return boletinDao.Registrar(boletines);
	}

	@Override
	public Boletines Actualizar(Boletines boletines) {
		return boletinDao.Actualizar(boletines);
	}

	@Override
	public Boletines Eliminar(Boletines boletines) {
		return boletinDao.Eliminar(boletines);
	}

	@Override
	public List<Boletines> buscarRangoFechas(Boletines boletines) {
		return boletinDao.buscarRangoFechas(boletines);
	}

	@Override
	public List<Boletines> BuscarPorCodigo(Boletines boletines) {
		return boletinDao.BuscarPorCodigo(boletines);
	}

	@Override
	public List<Boletines> buscarBoletines(Boletines boletines) { 
		
		if(boletines.getvFecdesde()!=null && boletines.getvFechasta()!=null) {
			boletines.setdFechaInicio(FechasUtil.convertStringToDate(boletines.getvFecdesde()));
			boletines.setdFechaFin(FechasUtil.convertStringToDate(boletines.getvFechasta()));
		}
		return boletinDao.buscarBoletines(boletines);
	}

}
