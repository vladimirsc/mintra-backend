package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Comisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ConsejeroService; ;

@Service("ConsejeroService")
@Transactional(readOnly = true)
public class ConsejeroServiceImpl implements ConsejeroService {

 
	@Autowired
	public ConsejeroDao consejeroDao;
	
	@Autowired
	public ComisionDao comisionDao;
	
 

	@Override
	public List<Consejeros> listar(Consejeros consejero) {
		return consejeroDao.listar(consejero);
	}

	@Override
	public Consejeros buscarPorId(Consejeros consejero) {
		return consejeroDao.buscarPorId(consejero);
	}

	@Override
	public List<Consejeros> buscar(Consejeros consejero) {
		return consejeroDao.buscar(consejero);
	}

	@Override
	public Consejeros Registrar(Consejeros consejero) {
		// TODO Auto-generated method stub
		return consejeroDao.Registrar(consejero);
	}

	@Override
	public Consejeros Actualizar(Consejeros consejero) {
		// TODO Auto-generated method stub
		return consejeroDao.Actualizar(consejero);
	}

	@Override
	public Consejeros Eliminar(Consejeros consejero) {
		// TODO Auto-generated method stub
		return consejeroDao.Eliminar(consejero);
	}

	@Override
	public Consejeros buscarPorTipoDocNumero(Consejeros consejero) {
		return consejeroDao.buscarPorTipoDocNumero(consejero);
	}

	@Override
	public List<Consejeros> listarConsejerosPorComision(Consejeros consejero) {
		List<Consejeros> lista = new ArrayList<Consejeros>();
		lista = consejeroDao.listarConsejerosPorComision(consejero);
		if(!lista.isEmpty()) {
			Comisiones comision = new Comisiones();
			comision.setcOmisionidpk(consejero.getcOmisionfk());
			comision = comisionDao.buscarPorId(comision);
			
			for (Consejeros i : lista) {
				i.setvEntidad(comision.getvCodcomision());
			}
		}
		
		return lista;
	}

}
