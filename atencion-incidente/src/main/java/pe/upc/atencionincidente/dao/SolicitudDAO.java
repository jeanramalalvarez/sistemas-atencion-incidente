package pe.upc.atencionincidente.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pe.upc.atencionincidente.form.ConsultaIncidenteForm;
import pe.upc.atencionincidente.form.DatosSolicitudForm;
import pe.upc.atencionincidente.form.DetalleIncidenteForm;
import pe.upc.atencionincidente.form.EvaluarIncidenteForm;
import pe.upc.atencionincidente.model.AnexoSolicitud;
import pe.upc.atencionincidente.model.Incidente;
import pe.upc.atencionincidente.model.Solicitante;
import pe.upc.atencionincidente.model.Solicitud;

public interface SolicitudDAO {

	List<Map<String,Object>> getListTipoSolicitud();
	List<Map<String,Object>> getListEstado();
	List<Solicitud> consultarSolicitud(ConsultaIncidenteForm form);
	String  saveSolicitud(DatosSolicitudForm form,Solicitante solicitante);
	String  saveIncidente(Incidente incidente);
	String  saveAnexoSolicitud(AnexoSolicitud anexo);
	String  saveSolucionAF(EvaluarIncidenteForm form);
	List<Map<String, Object>> getListSistemas();
	List<Map<String, Object>> getListProcesos(String idSistema);
	List<Map<String, Object>> getListSubProcesos(String idSistema,String idProceso);
	List<Map<String, Object>> getListUsAfectados();
	DatosSolicitudForm  getSolicitud(String nroSolicitud);
	String  getEmailOp(String idSolicitud);
	DetalleIncidenteForm  getIncidente(String idSolicitud);
	List<Map<String, Object>> getListAnexos(String idSolicitud);
	List<Map<String, Object>> getSolucionIncidente(EvaluarIncidenteForm form);
	
	String  updateSolicitud(DatosSolicitudForm form,Solicitante solicitante);
	String  updateIncidente(Incidente incidente);
	
	String  asignarAnalista(Map<String,Object> datos);
	String  mensajeNotificacion(Map<String,Object> datos);
	Map<String, Object>  getAnalista(Map<String,Object> datos);
	
	List<Solicitud> consultarSolicitudAnalista(ConsultaIncidenteForm form);
	String  saveEvaluacion(EvaluarIncidenteForm form);
	String  updateEstadoIncidente(String idSolicitud,String idEstado);
	
}
