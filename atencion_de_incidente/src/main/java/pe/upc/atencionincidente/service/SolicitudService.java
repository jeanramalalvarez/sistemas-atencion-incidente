package pe.upc.atencionincidente.service;

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

public interface SolicitudService {

	public List<Map<String, Object>> getListTipoSolicitud();
	public List<Map<String,Object>> getListEstado();
	public List<Solicitud> consultarSolicitud(ConsultaIncidenteForm form);
	public String  saveSolicitud(DatosSolicitudForm form,Solicitante solicitante);
	public String  saveIncidente(Incidente incidente);
	public String  saveAnexoSolicitud(AnexoSolicitud anexo);
	public List<Map<String, Object>> getSistemas();
	public List<Map<String, Object>> getListProcesos(String idSistema);
	public List<Map<String, Object>> getListSubProcesos(String idSistema,String idProceso);
	public List<Map<String, Object>> getListUsAfectados();
	public DatosSolicitudForm  getSolicitud(String nroSolicitud);
	public DetalleIncidenteForm  getIncidente(String idSolicitud);
	public List<Map<String, Object>> getListAnexos(String idSolicitud);
	public List<Map<String, Object>> getSolucionIncidente(String idSolicitud);
	
	public String  updateSolicitud(DatosSolicitudForm form,Solicitante solicitante);
	public String  updateIncidente(Incidente incidente);
	public String  asignarAnalista(Map<String,Object> datos);
	public String  mensajeNotificacion(Map<String,Object> datos);
	public Map<String, Object>  getAnalista(Map<String,Object> datos);
	
	public List<Solicitud> consultarSolicitudAnalista(ConsultaIncidenteForm form);
	String  saveEvaluacion(EvaluarIncidenteForm form);
	public String  updateEstadoIncidente(String idSolicitud,String idEstado);
	
}


