package pe.gob.mtpe.sivice.externo.core.util;

public class ConstantesUtil {

	public static final String C_INDC_ACTIVO = "1";
	public static final String C_INDC_INACTIVO = "0";

	public static final String ACTA_ALIAS_CORRELATIVO = "ACTA";
	public static final String BOLETIN_ALIAS_CORRELATIVO = "BOLETIN";
	public static final String COMISION_ALIAS_CORRELATIVO = "COMISION";
	public static final String INFORME_ALIAS_CORRELATIVO = "INFORME";
	public static final String PLANTRABAJO_ALIAS_CORRELATIVO = "PLAN-TRABAJO";
	public static final String SESION_ALIAS_CORRELATIVO = "SESION";
	
	public static final String C_CONSEJERO_DOC_ASIGNACION ="DOCASIGNACION";
	public static final String C_BOLETINES ="BOLETIN";
	public static final String C_ENCARGADO_REGION ="ENCREGION";
	public static final String GENERAL_MSG_ERROR_BASE = "Ocurrio un error en la Base de Datos";

	// CINSTANTES JSON
	public static final String X_MENSAJE = "mensaje";
	public static final String X_ERROR = "error";
	public static final String X_ENTIDAD = "entidad";
	
	public static final String C_ARCHIVO_CARGA_CORRECTA ="El archivo se cargo correctamente";
	public static final String C_ARCHIVO_CARGA_INCORRECTA ="El archivo no cargo correctamente";
	public static final String C_ARCHIVO_VALIDACION_UBICACION_CORRECTA ="El archivo se encuentra en su ubicacion";
	public static final String C_ARCHIVO_VALIDACION_UBICACION_INCORRECTA ="El archivo no se encuentra en su ubicacion";
	
	//VALIDACIONES
	public static final String C_DNI_DUPLICADO_MSG_CONSEJEROS ="SE ENCUENTRA ASIGNADO A OTRO USUARIO";
	public static final String C_DNI_DUPLICADO_ERROR_CONSEJEROS ="El DNI se encuentra activo,con otro consejero";
	// ******* ACTAS ******

	// busquedas
	public static final String ACTAS_MSG_ERROR_BUSCAR = "No se encontro el Acta";
	public static final String ACTAS_ERROR_BUSCAR = "Acta no encontrada";
	public static final String ACTAS_MSG_EXITO_BUSCAR = "Acta encontrada con Correlativo N° ";

	// creacion
	public static final String ACTAS_MSG_ERROR_CREAR = "Ocurrio en error al Buscar el Acta con Codigo N° ";
	public static final String ACTAS_MSG_EXITO_CREAR = "EL Acta fue creada con \u00e9xito con Correlativo N° ";

	// actualizacion
	public static final String ACTAS_MSG_ERROR_ACTUALIZAR = "NO se encontro el Acta";
	public static final String ACTAS_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Acta N° ";

	// eliminacion
	public static final String ACTAS_MSG_EXITO_ELIMINAR = "El acta fue eliminado con \u00e9xito con con codigo de Acta N° ";

	// ***** ACUERDOS ******

	// busquedas
	public static final String ACUERDO_MSG_ERROR_BUSCAR = "No se encontro el Acuerdo";
	public static final String ACUERDO_ERROR_BUSCAR = "Acuerdo no encontrada";
	public static final String ACUERDO_MSG_EXITO_BUSCAR = "Acuerdo encontrado ";

	// creacion
	public static final String ACUERDO_MSG_EXITO_CREAR = "EL Acuerdo fue creada con \u00e9xito ";

	// actualizacion
	public static final String ACUERDO_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Acuerdo";

	// eliminacion
	public static final String ACUERDO_MSG_EXITO_ELIMINAR = "El Acuerdo fue eliminado con \u00e9xito con con codigo de Acta N° ";

	// ***** ASISTENCIA ******

	// busquedas
	public static final String ASISTENCIA_MSG_ERROR_BUSCAR = "No se encontro el Asistente";
	public static final String ASISTENCIA_ERROR_BUSCAR = "Asistente no encontrada";
	public static final String ASISTENCIA_MSG_EXITO_BUSCAR = "Asistente encontrado ";

	// creacion
	public static final String ASISTENCIA_MSG_EXITO_CREAR = "EL Asistente fue creada con \u00e9xito ";

	// actualizacion
	public static final String ASISTENCIA_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Asistente";

	// eliminacion
	public static final String ASISTENCIA_MSG_EXITO_ELIMINAR = "El Acuerdo fue eliminado con \u00e9xito con con codigo de Acta N° ";

	// ***** BOLETINES ******

	// busquedas
	public static final String BOLETIN_MSG_ERROR_BUSCAR = "No se encontro el Boletin";
	public static final String BOLETIN_ERROR_BUSCAR = "Boletin no encontrada";
	public static final String BOLETIN_MSG_EXITO_BUSCAR = "Acuerdo encontrado ";

	// creacion
	public static final String BOLETIN_MSG_EXITO_CREAR = "EL Boletin fue creada con \u00e9xito ";

	// actualizacion
	public static final String BOLETIN_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Boletin";

	// eliminacion
	public static final String BOLETIN_MSG_EXITO_ELIMINAR = "El Boletin fue eliminado con \u00e9xito con con codigo de Acta N° ";

	// ***** CALENDARIO ******

	// busquedas
	public static final String CALENDARIO_MSG_ERROR_BUSCAR = "No se encontro el Calendario";
	public static final String CALENDARIO_ERROR_BUSCAR = "Calendario no encontrada";
	public static final String CALENDARIO_MSG_EXITO_BUSCAR = "Calendario encontrado ";

	// creacion
	public static final String CALENDARIO_MSG_EXITO_CREAR = "EL Calendario fue creada con \u00e9xito ";

	// actualizacion
	public static final String CALENDARIO_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Calendario";

	// eliminacion
	public static final String CALENDARIO_MSG_EXITO_ELIMINAR = "El Calendario fue eliminado con \u00e9xito con con codigo de Acta N° ";

	// ***** COMISION CONSEJERO ******

	// busquedas
	public static final String COMISION_CONSEJERO_MSG_ERROR_BUSCAR = "No se encontro la Comision Consejero";
	public static final String COMISION_CONSEJERO_ERROR_BUSCAR = "Comision Consejero no encontrada";
	public static final String COMISION_CONSEJERO_MSG_EXITO_BUSCAR = "Comision Consejero encontrado ";

	// creacion
	public static final String COMISION_CONSEJERO_MSG_EXITO_CREAR = "La Comision Consejero fue creada con \u00e9xito ";

	// actualizacion
	public static final String COMISION_CONSEJERO_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente La Comision Consejero";

	// eliminacion
	public static final String COMISION_CONSEJERO_MSG_EXITO_ELIMINAR = "La Comision Consejero fue eliminado con \u00e9xito con con codigo de Acta N° ";

	// ***** COMISION ******
	
	public static final String C_COMISIONES ="COMISION";

	// busquedas
	public static final String COMISION_MSG_ERROR_BUSCAR = "No se encontro el Acuerdo";
	public static final String COMISION_ERROR_BUSCAR = "Acuerdo no encontrada";
	public static final String COMISION_MSG_EXITO_BUSCAR = "Acuerdo encontrado ";

	// creacion
	public static final String COMISION_MSG_EXITO_CREAR = "EL Acuerdo fue creada con \u00e9xito ";

	// actualizacion
	public static final String COMISION_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Acuerdo";

	// eliminacion
	public static final String COMISION_MSG_EXITO_ELIMINAR = "El Acuerdo fue eliminado con \u00e9xito con con codigo de Acta N° ";

	// ***** CONSEJERO ******

	// busquedas
	public static final String CONSEJERO_MSG_ERROR_BUSCAR = "No se encontro el Consejero";
	public static final String CONSEJERO_ERROR_BUSCAR = "Consejero no encontrada";
	public static final String CONSEJERO_MSG_EXITO_BUSCAR = "Consejero encontrado ";

	// creacion
	public static final String CONSEJERO_MSG_EXITO_CREAR = "EL Consejero fue creada con \u00e9xito ";

	// actualizacion
	public static final String CONSEJERO_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Consejero";

	// eliminacion
	public static final String CONSEJERO_MSG_EXITO_ELIMINAR = "El Consejero fue eliminado con \u00e9xito con con codigo de Acta N° ";
	
	public static final String CONSEJERO_MSG_EXISTE_BUSCAR = "El consejero Existe";
	public static final String CONSEJERO_ERROR_EXISTE_BUSCAR = "Consejero encontrado";
	
	public static final String CONSEJERO_MSG_DOCAPROBACION_VACIO = "Adjuntar Documento de aprobacion";
	public static final String CONSEJERO_ERROR_DOCAPROBACION_VACIO = "El documento de aprobacion no puede estar vacio" ;
	
	
	// ***** COMISION CONSEJERO ******
	
	public static final String C_DOCCOMISIONCONSEJERO="DOC_COMICON";
	// FIJAS
	// personas
	// busquedas
	public static final String FPROFESION_MSG_ERROR_BUSCAR = "No se encontro el Profesion";
	public static final String FPROFESION_ERROR_BUSCAR = "Profesion no encontrada";
	public static final String FPROFESION_MSG_EXITO_BUSCAR = "Profesion encontrado ";

	// tipo docuemnto
	// busquedas
	public static final String TIPODOCUMENTO_MSG_ERROR_BUSCAR = "No se encontro el Tipo Documento";
	public static final String TIPODOCUMENTO_ERROR_BUSCAR = "Tipo Documento no encontrada";
	public static final String TIPODOCUMENTO_MSG_EXITO_BUSCAR = "Tipo Documento encontrado ";

	// tipo consejero
	// busquedas
	public static final String TIPOCONSEJERO_MSG_ERROR_BUSCAR = "No se encontro el Tipo Consejero";
	public static final String TIPOCONSEJERO_ERROR_BUSCAR = "Tipo Consejero no encontrada";
	public static final String TIPOCONSEJERO_MSG_EXITO_BUSCAR = "Tipo Consejero encontrado ";

	// region
	// busquedas
	public static final String REGION_MSG_ERROR_BUSCAR = "No se encontro el Region";
	public static final String REGION_ERROR_BUSCAR = "La Region no encontrada";
	public static final String REGION_MSG_EXITO_BUSCAR = "Region encontrado ";

	// tipo consejo
	// busquedas
	public static final String TIPO_CONSEJO_MSG_ERROR_BUSCAR = "No se encontro el Tipo Consejo";
	public static final String TIPO_CONSEJO_ERROR_BUSCAR = "El Tipo Consejo no encontrada";
	public static final String TIPO_CONSEJOMSG_EXITO_BUSCAR = "Tipo Consejo encontrado ";

	// tipo consejo
	// busquedas
	public static final String TIPO_COMISION_MSG_ERROR_BUSCAR = "No se encontro el Tipo Comision";
	public static final String TIPO_COMISION_ERROR_BUSCAR = "El Tipo Comision no encontrada";
	public static final String TIPO_COMISION_MSG_EXITO_BUSCAR = "Tipo Comision encontrado ";

	// tipo consejo
	// busquedas
	public static final String TIPO_SESION_MSG_ERROR_BUSCAR = "No se encontro el Tipo Comision";
	public static final String TIPO_SESION_ERROR_BUSCAR = "El Tipo Comision no encontrada";
	public static final String TIPO_SESION_MSG_EXITO_BUSCAR = "Tipo Comision encontrado ";

	// tipo tema
	// busquedas
	public static final String TIPO_TEMA_MSG_ERROR_BUSCAR = "No se encontro el Tipo Tema";
	public static final String TIPO_TEMA_ERROR_BUSCAR = "El Tipo Tema no encontrada";
	public static final String TIPO_TEMA_MSG_EXITO_BUSCAR = "Tipo Tema encontrado ";

	// ***** FIRMANTES ******

	// busquedas
	public static final String FIRMANTES_MSG_ERROR_BUSCAR = "No se encontro el Firmante";
	public static final String FIRMANTES_ERROR_BUSCAR = "Firmante no encontrada";
	public static final String FIRMANTES_MSG_EXITO_BUSCAR = "Firmante encontrado ";

	// creacion
	public static final String FIRMANTES_MSG_EXITO_CREAR = "EL Firmante fue creada con \u00e9xito ";

	// actualizacion
	public static final String FIRMANTES_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Firmante";

	// eliminacion
	public static final String FIRMANTES_MSG_EXITO_ELIMINAR = "El Firmante fue eliminado con \u00e9xito con con codigo de Acta N° ";

	// ***** INFORMES ANUALES ******

	// busquedas
	public static final String INFORMES_MSG_ERROR_BUSCAR = "No se encontro el Informe Anual";
	public static final String INFORMES_ERROR_BUSCAR = "Informe Anual no encontrada";
	public static final String INFORMES_MSG_EXITO_BUSCAR = "Informe Anual encontrado ";
	public static final String C_INFORME_ANUALES ="INFORME";

	// creacion
	public static final String INFORMES_MSG_EXITO_CREAR = "EL Informe Anual fue creada con \u00e9xito ";

	// actualizacion
	public static final String INFORMES_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Informe Anual";

	// eliminacion
	public static final String INFORMES_MSG_EXITO_ELIMINAR = "El Informe Anual fue eliminado con \u00e9xito con con codigo de Acta N° ";

	
	
	// ***** PARTICIPANTE CALENDARIO ******

	// busquedas
	public static final String PARTICICALENDA_MSG_ERROR_BUSCAR = "No se encontro los Participantes en el Calendario";
	public static final String PARTICICALENDA_ERROR_BUSCAR = "Participantes en el Calendario no encontrada";
	public static final String PARTICICALENDA_MSG_EXITO_BUSCAR = "Participantes en el Calendario encontrado ";

	// creacion
	public static final String PARTICICALENDA_MSG_EXITO_CREAR = "Participantes en el Calendario fue creada con \u00e9xito ";

	// actualizacion
	public static final String PARTICICALENDA_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Participantes en el Calendario";

	// eliminacion
	public static final String PARTICICALENDAMSG_EXITO_ELIMINAR = "El Participantes en el Calendario fue eliminado con \u00e9xito";

	
	
	// ***** PLAN DE TRABAJO ******

	// busquedas
	public static final String PLANTRABAJO_MSG_ERROR_BUSCAR = "No se encontro el Plan de Trabajo";
	public static final String PLANTRABAJO_ERROR_BUSCAR = "Plan de Trabajo no encontrada";
	public static final String PLANTRABAJO_MSG_EXITO_BUSCAR = "Plan de Trabajo encontrado ";
	public static final String PLANTRABAJO_MSG_DOC_ADJUNTOS ="Adjuntar documentos";
	public static final String PLANTRABAJO_ERROR_DOC_ADJUNTOS ="adjunte documento de aprobacion y plan de trabajo";
	public static final String C_DOC_PLAN_TRABAJO_SIGLAS ="DOCPLANTRABAJO";
	public static final String C_DOCAPROBACION_PLAN_TRABAJO_SIGLAS ="DOC_APROPLAN";
	// creacion
	public static final String PLANTRABAJO_MSG_EXITO_CREAR = "EL Plan de Trabajo fue creada con \u00e9xito ";

	// actualizacion
	public static final String PLANTRABAJO_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Plan de Trabajo";

	// eliminacion
	public static final String PLANTRABAJO_MSG_EXITO_ELIMINAR = "El Plan de Trabajo fue eliminado con \u00e9xito";

	// ***** SEGUIMIENTO ******

	// busquedas
	public static final String SEGUIMIENTO_MSG_ERROR_BUSCAR = "No se encontro el Seguimiento";
	public static final String SEGUIMIENTO_ERROR_BUSCAR = "Seguimiento no encontrada";
	public static final String SEGUIMIENTO_MSG_EXITO_BUSCAR = "Seguimiento encontrado ";

	// creacion
	public static final String SEGUIMIENTOMSG_EXITO_CREAR = "EL Seguimiento fue creada con \u00e9xito ";

	// actualizacion
	public static final String SEGUIMIENTO_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Seguimiento";

	// eliminacion
	public static final String SEGUIMIENTO_MSG_EXITO_ELIMINAR = "El Seguimiento fue eliminado con \u00e9xito con con codigo de Acta N° ";

	
	
	
	// ***** SESION ******

	// busquedas
	public static final String SESION_MSG_ERROR_BUSCAR = "No se encontro el Sesion";
	public static final String SESION_ERROR_BUSCAR = "Sesion no encontrada";
	public static final String SESION_MSG_EXITO_BUSCAR = "Sesion encontrado ";
	public static final String SESION_ARCHIVO_1_SIGLA="SEARCHIVO1";
	public static final String SESION_ARCHIVO_2_SIGLA ="SEARCHIVO2";
	public static final String SESION_ARCHIVO_3_SIGLA ="SEARCHIVO3";
	public static final String SESION_MSG_ERROR_ARCHIVO = "No adjntos archivo";
	public static final String SESION_ERROR_ARCHIVO  = "Archivos no cargados";

	// creacion
	public static final String SESION_MSG_EXITO_CREAR = "EL Sesion fue creada con \u00e9xito ";

	// actualizacion
	public static final String SESION_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Sesion";

	// eliminacion
	public static final String SESION_MSG_EXITO_ELIMINAR = "El Sesion fue eliminado con \u00e9xito";

	
	
	// ***** TEMA ******

	// busquedas
	public static final String TEMA_MSG_ERROR_BUSCAR = "No se encontro el Tema";
	public static final String TEMA_ERROR_BUSCAR = "Tema no encontrada";
	public static final String TEMA_MSG_EXITO_BUSCAR = "Tema encontrado ";

	// creacion
	public static final String TEMA_MSG_EXITO_CREAR = "EL Tema fue creada con \u00e9xito ";

	// actualizacion
	public static final String TEMA_MSG_EXITO_ACTUALIZAR = "Se actualizo correctamente el Tema";

	// eliminacion
	public static final String TEMA_MSG_EXITO_ELIMINAR = "El Tema fue eliminado con \u00e9xito con con codigo de Acta N° ";
	
	//seguridad
	public static final String C_USUARIO_EXISTE_ERROR ="Usuario registrado";
	public static final String C_USUARIO_EXISTE_MENSAJE ="El usuario ya existe";
 
	public static final String C_FLAG_ASISTIO_SI="1";
	public static final String C_FLAG_ASISTIO_NO="0";
	public static final String C_HORA_INICIO_DEFAULT="00:00 AM";
	public static final String C_HORA_FINALDEFAULT="00:00 AM";
	
	
    public static final String C_ARCHIVO_MENSAJE ="Archivo";
    public static final String C_ARCHIVO_ERROR_MENSAJE ="No se encontro el Archivo";
    
    public static final String C_MSG_MENSAJE_USUARIOROL ="Error al cambiar estado del rol";
    public static final String C_MSG_ERROR_USUARIOROL ="no realizo el cambio";
    
    
    public static final String C_CONSSAT="CONSSAT";
    public static final String C_CONRSAT="CONRSAT";
    public static final String C_COMICONSSAT="COMICONSSAT";
    public static final String C_COMICORSAT="COMICORSAT";
    
    
    public static final String C_ROLE_ADMCONSSAT="ROLE_ADMCONSSAT";
    public static final String C_ROLE_ADMCORSSAT="ROLE_ADMCORSSAT";
    public static final String C_ROLE_OPECONSSAT="ROLE_OPECONSSAT";
    public static final String C_ROLE_OPECORSSAT="ROLE_OPECORSSAT";
    
   /*
    public static final String c_rolusuario   ="ROLE_ADMCONSSAT";
    public static final Long   c_codigoregion =Long.parseLong("14");
    public static final Long   c_usuariologin =Long.parseLong("21");
    public static final Long   c_consejero =Long.parseLong("133"); */
    
  
 

}
