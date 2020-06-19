package pe.gob.mtpe.sivice.externo.core.negocio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Perfiles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Personas;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Roles;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.TipoDocumentos;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.UsuarioPerfilRol;
import pe.gob.mtpe.sivice.externo.core.accesodatos.entity.Usuarios;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.FijasDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.PersonaDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioDao;
import pe.gob.mtpe.sivice.externo.core.accesodatos.repository.UsuarioPerfilDao;
import pe.gob.mtpe.sivice.externo.core.negocio.service.PersonaService;

@Service("PersonaService")
@Transactional(readOnly = true)
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaDao personaDao;

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private FijasDao fijasDao;

	@Autowired
	private UsuarioPerfilDao usuarioPerfilDao;

	@Override
	public List<Personas> listar() {
		return personaDao.listar();
	}

	@Override
	public Personas buscarPorId(Personas personas) {
		return personaDao.buscarPorId(personas);
	}

	@Override
	public List<Personas> buscar(Personas personas) {
		return personaDao.buscar(personas);
	}

	@Override
	public Personas ActualizarPersona(Personas personas) {
		return personaDao.ActualizarPersona(personas);
	}

	@Override
	public Personas EliminarPersona(Personas personas) {
		return personaDao.EliminarPersona(personas);
	}

	@Override
	public Personas buscarTipoDocNumero(Personas personas) {
		TipoDocumentos tipodocumento = new TipoDocumentos();
		tipodocumento.settPdocumentoidpk(personas.gettPdocumentofk());
		tipodocumento = fijasDao.buscarPorCodigoTipoDocumento(tipodocumento);
		personas.setTipodocumento(tipodocumento);
		return personaDao.buscarTipoDocNumero(personas);
	}

	@Override
	public Personas Registrar(Personas persona) {

		// VERIFICAMOS SI EL ROL Y
		if (persona.getPerfil() != null && persona.getRol() != null) {
			TipoDocumentos tipodocumento = new TipoDocumentos();
			tipodocumento.settPdocumentoidpk(persona.gettPdocumentofk());
			tipodocumento = fijasDao.buscarPorCodigoTipoDocumento(tipodocumento);
			persona.setTipodocumento(tipodocumento);

			persona = personaDao.Registrar(persona);
			if (persona != null) {
				// REGISTRAMOS EL USUARIO
				Usuarios usuario = new Usuarios();
				usuario.setPersona(persona);
				usuario.setvDesusuario(obtnerusuario(persona));
				usuario.setvDesclave(persona.getClave());
				usuario.setcFlgactivo("1");
				usuario = usuarioDao.Registrar(usuario);

				// REGISTRAMOS EL ROL Y EL PERFIL
				if (usuario != null) {
					Perfiles perfil = new Perfiles();
					perfil.setpErfilidpk(persona.getPerfil());
					perfil = fijasDao.BuscarPerfil(perfil);
					
					Roles rol = new Roles();
					rol.setrOlidpk(persona.getRol());
					rol = fijasDao.buscaRoles(rol);

					UsuarioPerfilRol usuarioPerfilRol = new UsuarioPerfilRol();
					usuarioPerfilRol.setUsuario(usuario);
					usuarioPerfilRol.setRoles(rol);
					usuarioPerfilRol.setPerfil(perfil);
					usuarioPerfilRol.setcFlgactivo("1");
					usuarioPerfilRol = usuarioPerfilDao.Registrar(usuarioPerfilRol);
				}

			}
		} else {
			persona = null;
		}
		return persona;
	}

	public String obtnerusuario(Personas persona) {
		return persona.getvNumdocumento() + persona.getTipodocumento().gettPdocumentoidpk();
	}

}
