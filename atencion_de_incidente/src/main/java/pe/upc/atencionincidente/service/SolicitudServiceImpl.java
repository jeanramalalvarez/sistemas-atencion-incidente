package pe.upc.atencionincidente.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.atencionincidente.dao.SolicitudDAO;
import pe.upc.atencionincidente.form.ConsultaIncidenteForm;
import pe.upc.atencionincidente.form.DatosSolicitudForm;
import pe.upc.atencionincidente.form.DetalleIncidenteForm;
import pe.upc.atencionincidente.form.EvaluarIncidenteForm;
import pe.upc.atencionincidente.model.AnexoSolicitud;
import pe.upc.atencionincidente.model.Incidente;
import pe.upc.atencionincidente.model.Solicitante;
import pe.upc.atencionincidente.model.Solicitud;

@Service(value="solicitudService")
public class SolicitudServiceImpl implements SolicitudService {
	
	@Autowired
	private SolicitudDAO solicitudDAO; 
	
	@Override
	public List<Map<String, Object>> getListTipoSolicitud() {
		
		List<Map<String, Object>> lstTipoSoli = solicitudDAO.getListTipoSolicitud(); 
		return lstTipoSoli;
		
	}

	@Override
	public List<Map<String, Object>> getListEstado() {
		
		List<Map<String, Object>> lstEstados = solicitudDAO.getListEstado(); 
		return lstEstados;
		
	}

	@Override
	public List<Solicitud> consultarSolicitud(ConsultaIncidenteForm form) {
		List<Solicitud> list = solicitudDAO.consultarSolicitud(form);
		return list;
	}

	@Override
	public String saveSolicitud(DatosSolicitudForm form, Solicitante solicitante) {
		String idSolicitud  = solicitudDAO.saveSolicitud(form, solicitante);
		return idSolicitud;
	}

	@Override
	public String saveIncidente(Incidente incidente) {
		String estado  = solicitudDAO.saveIncidente(incidente);
		return estado;
	}

	@Override
	public List<Map<String, Object>> getSistemas() {
		List<Map<String, Object>> list = solicitudDAO.getListSistemas();
		return list;
	}

	@Override
	public List<Map<String, Object>> getListProcesos(String idSistema) {
		
		List<Map<String, Object>> list = solicitudDAO.getListProcesos(idSistema);
		return list;
	}

	@Override
	public List<Map<String, Object>> getListSubProcesos(String idSistema, String idProceso) {
		
		List<Map<String, Object>> list = solicitudDAO.getListSubProcesos(idSistema, idProceso);
		return list;
	}

	@Override
	public List<Map<String, Object>> getListUsAfectados() {
		List<Map<String, Object>> list = solicitudDAO.getListUsAfectados();
		return list;
	}

	@Override
	public String saveAnexoSolicitud(AnexoSolicitud anexo) {
		String nombre  = solicitudDAO.saveAnexoSolicitud(anexo);
		return nombre;
	}

	@Override
	public DatosSolicitudForm getSolicitud(String nroSolicitud) {
		DatosSolicitudForm sol = solicitudDAO.getSolicitud(nroSolicitud);
		return sol;
	}

	@Override
	public DetalleIncidenteForm getIncidente(String idSolicitud) {
		DetalleIncidenteForm inc = solicitudDAO.getIncidente(idSolicitud);
		return inc;
	}

	@Override
	public List<Map<String, Object>> getListAnexos(String idSolicitud) {
		List<Map<String, Object>> list = solicitudDAO.getListAnexos(idSolicitud);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getSolucionIncidente(String idSolicitud) {
		List<Map<String, Object>> list = solicitudDAO.getSolucionIncidente(idSolicitud);
		return list;
	}

	@Override
	public String updateSolicitud(DatosSolicitudForm form, Solicitante solicitante) {
		String sol = solicitudDAO.updateSolicitud(form, solicitante);
		return sol;
	}

	@Override
	public String updateIncidente(Incidente incidente) {
		String in = solicitudDAO.updateIncidente(incidente);
		return in;
	}

	@Override
	public String asignarAnalista(Map<String, Object> datos) {
		
		String as = solicitudDAO.asignarAnalista(datos);
		return as;
	}

	@Override
	public String mensajeNotificacion(Map<String, Object> datos) {
		String as = solicitudDAO.mensajeNotificacion(datos);
		return as;
	}

	@Override
	public Map<String, Object> getAnalista(Map<String, Object> datos) {
		Map<String, Object> analista = solicitudDAO.getAnalista(datos);
		return analista;
	}

	@Override
	public List<Solicitud> consultarSolicitudAnalista(ConsultaIncidenteForm form) {
		List<Solicitud> list = solicitudDAO.consultarSolicitudAnalista(form);
		return list;
	}

	@Override
	public String saveEvaluacion(EvaluarIncidenteForm form) {
		String newSolInc  = solicitudDAO.saveEvaluacion(form);
		return newSolInc;
	}

	@Override
	public String updateEstadoIncidente(String idSolicitud, String idEstado) {
		String in = solicitudDAO.updateEstadoIncidente(idSolicitud,idEstado);
		return in;
	}

}





