package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.InfAnuales;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.InformAnualDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.InformAnualService;

@Service("InformAnualService")
@Transactional(readOnly = true)
public class InformAnualServiceImpl implements InformAnualService {
	
	@Autowired
	private InformAnualDao informAnualDao;

	@Override
	public List<InfAnuales> listar() {
		return informAnualDao.listar();
	}

	@Override
	public InfAnuales buscarPorId(InfAnuales infAnuales) {
		return informAnualDao.buscarPorId(infAnuales);
	}

	@Override
	public List<InfAnuales> buscar(InfAnuales infAnuales) {
		return informAnualDao.buscar(infAnuales);
	}

	@Override
	public InfAnuales Registrar(InfAnuales infAnuales) {
		return informAnualDao.Registrar(infAnuales);
	}

	@Override
	public InfAnuales Actualizar(InfAnuales infAnuales) {
		return informAnualDao.Actualizar(infAnuales);
	}

	@Override
	public InfAnuales Eliminar(InfAnuales infAnuales) {
		return informAnualDao.Eliminar(infAnuales);
	}

}
