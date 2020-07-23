package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.ComiConsej;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Comisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejeros;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoComisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Tipoconsejero;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ComisionDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.ConsejeroDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.ComisionConsejeroService; 

@Service("ComisConsejeroService")
@Transactional(readOnly = true)
public class ComisionConsejeroServiceImpl implements ComisionConsejeroService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ComisionConsejeroServiceImpl.class);

	@Autowired
	private ComisionConsejeroDao comisiConsejeDao;
	
	@Autowired
	private ComisionDao comisiondao;
	
	@Autowired
	private ConsejeroDao consejerodao;

	@Override
	public List<ComiConsej> listar() {
		return comisiConsejeDao.listar();
	}

	@Override
	public ComiConsej buscarPorId(ComiConsej comiConsej) {
		return comisiConsejeDao.buscarPorId(comiConsej);
	}

	@Override
	public List<ComiConsej> buscar(Long comision,Long idRegion,Long idUsuario) {
		
		//PREGUNTAMOS SI TIENE CONSEJEROS ASIGNADOS
		List<ComiConsej>  listaConsejeros  = new ArrayList<ComiConsej>();
		listaConsejeros= comisiConsejeDao.buscar(comision); 
		logger.info("==================="+listaConsejeros.size());
		if(listaConsejeros.size()==0) {
			
			Comisiones comsiones = new Comisiones();
			
			//LISTO LOS CONSEJEROS POR REGION
			TipoComisiones tipoComisiones = new TipoComisiones();
			Regiones region = new Regiones();
			Consejos consejo = new Consejos();
			
			comsiones.setTipocomision(tipoComisiones);
			comsiones.setConsejo(consejo);
			comsiones.setRegion(region);
			comsiones.setcOmisionidpk(comision);
			comsiones = comisiondao.consultaPorId(comsiones);
			
			if(comsiones!=null) {
				
				List<Consejeros> lsconsejeros = consejerodao.listarConsejerosPorRegion(comsiones.getRegion().getrEgionidpk());
				
				if(lsconsejeros.size()>0) {
					
					for(Consejeros consejero:lsconsejeros) {
						
						ComiConsej insertarcomiconsejero = new ComiConsej();
						
						ComiConsej comiconsero = new ComiConsej();
						comiconsero.setComision(comsiones);
						
						Tipoconsejero tipoconsejero = new Tipoconsejero();
						tipoconsejero.settPconsejeroidpk(consejero.getTipoconsejero().gettPconsejeroidpk());
						
						insertarcomiconsejero.setComision(comsiones);
						insertarcomiconsejero.setTipoconsejero(tipoconsejero);
						insertarcomiconsejero.setConsejero(consejero);
						
						//insertamos el regostro
						comisiConsejeDao.Registrar(insertarcomiconsejero);
					}
					
				}
				
				//listamos los nuevos insertados
				listaConsejeros= comisiConsejeDao.buscar(comision);
			}
			
			
		} 
		 
		
		return listaConsejeros;
	}

	@Override
	public ComiConsej Registrar(ComiConsej comiConsej) {
		return comisiConsejeDao.Registrar(comiConsej);
	}

	@Override
	public ComiConsej Actualizar(ComiConsej comiConsej) {
		return comisiConsejeDao.Actualizar(comiConsej);
	}

	@Override
	public ComiConsej Eliminar(ComiConsej comiConsej) {
		return comisiConsejeDao.Eliminar(comiConsej);
	}

	@Override
	public List<ComiConsej> listaConsejerosPorComision(Long idcomision) {
		
		return null;
	}

}
