package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Consejos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Entidades;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.InformacionUsuario;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Profesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Regiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Roles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoComisiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoSesiones;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoTemas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Tipoconsejero;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FijasDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.FijasService;
import pe.gob.mtpe.sivice.externo.core.util.ConstantesUtil;

@Service("profesionesService")
@Transactional(readOnly = true)
public class FijasServiceImpl implements FijasService {
	
	

	@Autowired
	private FijasDao fijasDao;

	@Override
	public List<Profesiones> listarProfesiones() {
		return fijasDao.listarProfesiones();
	}

	@Override
	public Profesiones buscarPorCodigoProfesion(Profesiones profesion) {
		return fijasDao.buscarPorCodigoProfesion(profesion);
	}

	@Override
	public List<TipoDocumentos> listarTipoDocumentos() {
		return fijasDao.listarTipoDocumentos();
	}

	@Override
	public TipoDocumentos buscarPorCodigoTipoDocumento(TipoDocumentos tipoDocumentos) {
		return fijasDao.buscarPorCodigoTipoDocumento(tipoDocumentos);
	}

	@Override
	public List<Tipoconsejero> listarTipoConsejeros() {
		return fijasDao.listarTipoConsejeros();
	}

	@Override
	public Tipoconsejero buscarPorCodigoTipoConsejero(Tipoconsejero tipoconsejero) {
		return fijasDao.buscarPorCodigoTipoConsejero(tipoconsejero);
	}

	@Override
	public List<Regiones> listarTipoRegiones() {
		return fijasDao.listarTipoRegiones();
	}

	@Override
	public Regiones buscarPorCodigoRegion(Regiones regiones) {
		return fijasDao.buscarPorCodigoRegion(regiones);
	}

	@Override
	public List<Consejos> listarConsejos() {
		return fijasDao.listarConsejos();
	}

	@Override
	public Consejos buscarPorCodigoConsejo(Consejos consejos) {
		return fijasDao.buscarPorCodigoConsejo(consejos);
	}

	@Override
	public List<TipoComisiones> listarTipoComisiones() {
		return fijasDao.listarTipoComisiones();
	}

	@Override
	public TipoComisiones buscarPorCodigoTipoComision(TipoComisiones tipocomisiones) {
		return fijasDao.buscarPorCodigoTipoComision(tipocomisiones);
	}

	@Override
	public List<TipoSesiones> listarTipoSesion() {
		return fijasDao.listarTipoSesion();
	}

	@Override
	public TipoSesiones buscarPorCodigoTipoSesion(TipoSesiones sesion) {
		return fijasDao.buscarPorCodigoTipoSesion(sesion);
	}

	@Override
	public List<TipoTemas> listarTipoTemas() {
		return fijasDao.listarTipoTemas();
	}

	@Override
	public TipoTemas buscarPorCodigoTipoTema(TipoTemas temas) {
		return fijasDao.buscarPorCodigoTipoTema(temas);
	}

	@Override
	public List<Entidades> listarEntidades() {
		
		return fijasDao.listarEntidades();
	}

	@Override
	public Entidades buscarPorEntidad(Entidades entidad) {
		
		return fijasDao.buscarPorEntidad(entidad);
	}

	@Override
	public List<Roles> listaRoles() { 
		return fijasDao.listaRoles();
	}

	@Override
	public Roles buscaRoles(Roles rol) { 
		return fijasDao.buscaRoles(rol);
	}

	@Override
	public Long BuscarConsejoPorNombre(String rolusuario) { 
		return fijasDao.BuscarConsejoPorNombre(rolusuario);
	}

	@Override
	public InformacionUsuario informacionUsuario(Long idusuario) { 
		InformacionUsuario informacionUsuario = new InformacionUsuario();
		
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol = fijasDao.informacionUsuario(idusuario);
		
		if(usuarioRol!=null) {
			
			informacionUsuario.setnIdUsuario(usuarioRol.getUsuario().getuSuarioidpk());
			informacionUsuario.setnIdRegion(usuarioRol.getUsuario().getRegiones().getrEgionidpk());
			informacionUsuario.setnIdRol(usuarioRol.getRoles().getrOlidpk());
 
			informacionUsuario.setvNombreUsuario(usuarioRol.getUsuario().getvNombre());
			informacionUsuario.setvApellidoPaterno(usuarioRol.getUsuario().getvAppaterno());
			informacionUsuario.setvApellidoMaterno(usuarioRol.getUsuario().getvApmaterno());
			informacionUsuario.setvCorreo(usuarioRol.getUsuario().getUsername());
			
			informacionUsuario.setvNombreRegion(usuarioRol.getUsuario().getRegiones().getvDesnombre());
			informacionUsuario.setvNombreRol(usuarioRol.getRoles().getvDesnombre());
			
			switch(usuarioRol.getRoles().getvDesnombre()) {
			
			  case ConstantesUtil.C_ROLE_ADMCONSSAT:
			      break;
			   
			  case ConstantesUtil.C_ROLE_ADMCORSSAT:
				 break;
				 
			  case ConstantesUtil.C_ROLE_OPECONSSAT:
				 break;
				 
			  case ConstantesUtil.C_ROLE_OPECORSSAT:
				 break;
			
			};
		}
		
		return informacionUsuario;
	}

	
}
