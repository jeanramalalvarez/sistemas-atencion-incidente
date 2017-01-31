package pe.upc.atencionincidente.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.upc.atencionincidente.dao.SolicitudDAO;
import pe.upc.atencionincidente.form.ConsultaIncidenteForm;
import pe.upc.atencionincidente.form.DatosSolicitudForm;
import pe.upc.atencionincidente.form.DetalleIncidenteForm;
import pe.upc.atencionincidente.form.EvaluarIncidenteForm;
import pe.upc.atencionincidente.model.AnexoSolicitud;
import pe.upc.atencionincidente.model.Incidente;
import pe.upc.atencionincidente.model.Solicitante;
import pe.upc.atencionincidente.model.Solicitud;

@Repository
public class SolicitudDaoImpl implements SolicitudDAO {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public List<Map<String, Object>> getListTipoSolicitud() {
		
		System.out.println("SolicitudDaoImpl getListTipoSolicitud: ");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listTipoSolicitud");
		System.out.println("SolicitudDaoImpl getListTipoSolicitud simpleJdbcCall.execute: ");
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute();
		System.out.println("SolicitudDaoImpl getListTipoSolicitud fin simpleJdbcCall.execute: ");
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(item->{
			
			Map<String,Object> row = ( Map<String,Object>) item;
			
			row.put("id",String.valueOf(row.get("id")));
			row.put("description",row.get("description").toString());
			
			list.add(row);
			
		});
		
		System.out.println("SolicitudDaoImpl fin getListTipoSolicitud: ");
			
		return list;
	}

	@Override
	public List<Map<String, Object>> getListEstado() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listEstados");
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute();
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(item->{
			
			Map<String,Object> row = ( Map<String,Object>) item;
			
			row.put("id",String.valueOf(row.get("id")));
			row.put("description",row.get("description").toString());
			
			list.add(row);
			
		});
			
		return list;
	}

	@Override
	public List<Solicitud> consultarSolicitud(ConsultaIncidenteForm form) {
		
		List<Solicitud> list = new ArrayList<Solicitud>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarSolicitud");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("nroCti", form.getNroCti());
		inParamMap.put("estado", form.getEstado());
		inParamMap.put("tipoSol", form.getTipoSol());
		inParamMap.put("fechaIni", form.getFechaIni());
		inParamMap.put("fechaFin", form.getFechaFin());
		inParamMap.put("nroSS", form.getNroSS());
		inParamMap.put("usuarioAdicion", form.getUsuarioAdicion());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			Solicitud sol = new Solicitud();
			
			sol.setId(String.valueOf(row.get("id")));
			sol.setAnalistaF(String.valueOf(row.get("analista")));
			sol.setNroCti(String.valueOf(row.get("nroCti")));
			sol.setEstado(String.valueOf(row.get("estado")));
			sol.setFechaReg(String.valueOf(row.get("fecha_reg")));
			sol.setNroSS(String.valueOf(row.get("nross")));
			sol.setPrioridad(String.valueOf(row.get("prioridad")));
			sol.setProceso(String.valueOf(row.get("proceso")));
			sol.setSistema(String.valueOf(row.get("sistema")));
			sol.setSolicitante(String.valueOf(row.get("solicitante")));
			sol.setTipoSol(String.valueOf(row.get("tipo_sol")));
			sol.setUsAfec(String.valueOf(row.get("us_afectados")));
			
			list.add(sol);
			
		});
			
		return list;
	}

	@SuppressWarnings("unchecked")
	public String  saveSolicitud(DatosSolicitudForm form,Solicitante solicitante){
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("newSolicitud");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSistema", form.getSistema());
		inParamMap.put("idProceso", form.getProceso());
		inParamMap.put("idSubProceso", form.getSubProceso());
		inParamMap.put("idTipoSolicitud", form.getTipoSolicitud());
		inParamMap.put("idAnalista",form.getAnalistaF());
		inParamMap.put("idAgencia", solicitante.getAgencia());
		inParamMap.put("idAreaSolicitante",solicitante.getArea());
		inParamMap.put("usuarioAdicion", solicitante.getId());
		inParamMap.put("txtDescripcion", form.getDescripcionSol());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("new_solicitud"));
		
	}
	
	public String  saveIncidente(Incidente incidente){
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("newIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",incidente.getIdSolicitud());
		inParamMap.put("idAreaAtencion",incidente.getIdAreaAtencion());
		inParamMap.put("numUsuariosAfectados",incidente.getNumUsuariosAfectados());
		inParamMap.put("txtAsunto", incidente.getTxtAsunto());
		inParamMap.put("flgAfectaIdi",incidente.getFlgAfectaIdi());
		inParamMap.put("txtResumen", incidente.getTxtResumen());
		
		System.out.println("incidente.getTxtDescripcion()");
		System.out.println(incidente.getTxtDescripcion());
		
		inParamMap.put("txtDescripcion",incidente.getTxtDescripcion());
		inParamMap.put("txtError",incidente.getTxtError());
		inParamMap.put("txtFechasCorte",incidente.getTxtFechasCorte());
		inParamMap.put("txtEstructuraReporte",incidente.getTxtEstructuraReporte());
		
		System.out.println("incidente.getTxtEstructuraReporte()");
		System.out.println(incidente.getTxtEstructuraReporte());
		
		inParamMap.put("flgRegulatorio",incidente.getFlgRegulatorio());
		inParamMap.put("usuarioAdicion",incidente.getUsuarioAdicion());
		
		inParamMap.put("datosModif",incidente.getDatosModif());
		inParamMap.put("volumenes",incidente.getVolumenes());

		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("idSolicitud"));
	}
	
	public String  saveAnexoSolicitud(AnexoSolicitud anexo){
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("newAnexoSolicitud");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",anexo.getIdSolicitud());
		inParamMap.put("numSecuencia",anexo.getNumSecuencia());
		inParamMap.put("txtNombre",anexo.getTxtRuta());
		inParamMap.put("txtRuta",anexo.getTxtRuta());
		inParamMap.put("txtDescripcion", anexo.getTxtDescripcion());
		inParamMap.put("usuarioAdicion",anexo.getUsuarioAdicion());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("anexo"));
	}

	@Override
	public List<Map<String, Object>> getListSistemas() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listSistemas");
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute();
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(item->{
			
			Map<String,Object> row = ( Map<String,Object>) item;
			
			row.put("id",String.valueOf(row.get("id")));
			row.put("description",row.get("description").toString());
			
			list.add(row);
			
		});
			
		return list;
	}

	@Override
	public List<Map<String, Object>> getListProcesos(String idSistema) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listProcesos");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSistema",idSistema);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(inParamMap);
		
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(item->{
			
			Map<String,Object> row = ( Map<String,Object>) item;
			
			row.put("id",String.valueOf(row.get("id")));
			row.put("description",row.get("description").toString());
			
			list.add(row);
			
		});
			
		return list;
	}

	@Override
	public List<Map<String, Object>> getListSubProcesos(String idSistema,String idProceso) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listSubProcesos");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSistema",idSistema);
		inParamMap.put("idProceso",idProceso);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(inParamMap);
		
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(item->{
			
			Map<String,Object> row = ( Map<String,Object>) item;
			
			row.put("id",String.valueOf(row.get("id")));
			row.put("description",row.get("description").toString());
			
			list.add(row);
			
		});
			
		return list;
	}

	@Override
	public List<Map<String, Object>> getListUsAfectados() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listUsAfectados");
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute();
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(item->{
			
			Map<String,Object> row = ( Map<String,Object>) item;
			
			row.put("id",String.valueOf(row.get("id")));
			row.put("description",row.get("description").toString());
			
			list.add(row);
			
		});
			
		return list;
	}

	@Override
	public DatosSolicitudForm getSolicitud(String nroSolicitud) {
		
		DatosSolicitudForm solicitud = new DatosSolicitudForm();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getSolicitud");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("nroSolicitud",nroSolicitud);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(inParamMap);
		
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		Map<String,Object> row = data.get(0);
	
		solicitud.setId_solicitud(String.valueOf(row.get("id_solicitud")));	
		solicitud.setTipoSolicitud(String.valueOf(row.get("id_tipo_solicitud")));
		solicitud.setSistema(String.valueOf(row.get("sistema")));
		solicitud.setProceso(String.valueOf(row.get("proceso")));
		solicitud.setSubProceso(String.valueOf(row.get("subProceso")));
		//solicitud.setNroUsAfec(String.valueOf(row.get("id")));
		solicitud.setNroCti(String.valueOf(row.get("nroCti")));
		//solicitud.setAnalistaF(analistaF);
		
		return solicitud;
	}

	@Override
	public DetalleIncidenteForm getIncidente(String idSolicitud) {
		
		DetalleIncidenteForm incidente = new DetalleIncidenteForm();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",idSolicitud);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(inParamMap);
		
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		Map<String,Object> row = data.get(0);
	
		incidente.setIdSolicitud(idSolicitud);
		incidente.setAsunto(String.valueOf(row.get("asunto")));
		incidente.setAfectaIdi(String.valueOf(row.get("afectIdi")));
		incidente.setDescripcion(String.valueOf(row.get("descripcion")));
		incidente.setError(String.valueOf(row.get("error")));
		incidente.setDatosModif(String.valueOf(row.get("datosModif")));
		incidente.setDescripcion2(String.valueOf(row.get("descripcion")));
		incidente.setEstructuraReporte(String.valueOf(row.get("estructuraReporte")));
		incidente.setFechasCorte(String.valueOf(row.get("fechasCorte")));
		incidente.setResumen(String.valueOf(row.get("resumen")));
		incidente.setRegulatorio(String.valueOf(row.get("regulatorio")));
		incidente.setVolumenes(String.valueOf(row.get("volumenes")));
		incidente.setUsAfectados(String.valueOf(row.get("usAfec")));
		
		return incidente;
	}

	@Override
	public List<Map<String, Object>> getListAnexos(String idSolicitud) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getAnexos");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",idSolicitud);
		
		System.out.println("idSolicitud Anexo: " + idSolicitud);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(inParamMap);
		
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
	
		System.out.println("data Anexo: " + data);
	
		data.forEach(item->{
			
			Map<String,Object> row = ( Map<String,Object>) item;
			
			row.put("numSecuencia",String.valueOf(row.get("numSecuencia")));
			row.put("txtRuta",String.valueOf(row.get("txtRuta")));
			row.put("fechaCarga",String.valueOf(row.get("fechaCarga")));
			
			System.out.println("Anexo: " + row);
			
			list.add(row);
			
		});
			
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getSolucionIncidente(EvaluarIncidenteForm form) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listSolucionIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",form.getNroSolicitud());
		
		System.out.println("idSolicitud: " + form.getNroSolicitud());
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(inParamMap);
		
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
	
		System.out.println("data Solucion: " + data);
	
		data.forEach(item->{
			
			Map<String,Object> row = ( Map<String,Object>) item;
			
			row.put("idSolucion",String.valueOf(row.get("idSolucion")));
			row.put("txtDescripcion",String.valueOf(row.get("txtDescripcion")));
			
			System.out.println("Solucion: " + row);
			
			list.add(row);
			
		});
			
		return list;
	}

	@Override
	public String updateSolicitud(DatosSolicitudForm form, Solicitante solicitante) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("updateSolicitud");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",form.getId_solicitud());
		inParamMap.put("idSistema", form.getSistema());
		inParamMap.put("idProceso", form.getProceso());
		inParamMap.put("idSubProceso", form.getSubProceso());
		inParamMap.put("idTipoSolicitud", form.getTipoSolicitud());
		inParamMap.put("idAnalista",form.getAnalistaF());
		inParamMap.put("idAgencia", solicitante.getAgencia());
		inParamMap.put("idAreaSolicitante",solicitante.getArea());
		inParamMap.put("usuarioAdicion", solicitante.getId());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("update_solicitud"));
	}

	@Override
	public String updateIncidente(Incidente incidente) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("updateIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",incidente.getIdSolicitud());
		inParamMap.put("idAreaAtencion",incidente.getIdAreaAtencion());
		inParamMap.put("numUsuariosAfectados",incidente.getNumUsuariosAfectados());
		inParamMap.put("txtAsunto", incidente.getTxtAsunto());
		inParamMap.put("flgAfectaIdi",incidente.getFlgAfectaIdi());
		inParamMap.put("txtResumen", incidente.getTxtResumen());
		inParamMap.put("txtDescripcion",incidente.getTxtDescripcion());
		inParamMap.put("txtError",incidente.getTxtError());
		inParamMap.put("txtFechasCorte",incidente.getTxtFechasCorte());
		inParamMap.put("txtEstructuraReporte",incidente.getTxtEstructuraReporte());
		inParamMap.put("flgRegulatorio",incidente.getFlgRegulatorio());
		inParamMap.put("usuarioAdicion",incidente.getUsuarioAdicion());
		
		inParamMap.put("datosModif",incidente.getDatosModif());
		inParamMap.put("volumenes",incidente.getVolumenes());

		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("idSolicitud"));
	}

	@Override
	public String asignarAnalista(Map<String,Object> datos) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("asignarAnalista");
		
		System.out.println("Asignar analista inParamMap");
		System.out.println(datos);
		
		
		SqlParameterSource in = new MapSqlParameterSource(datos);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		   
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("id_analista"));
	}

	@Override
	public String mensajeNotificacion(Map<String, Object> datos) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getReglaNotificacion");
		
		System.out.println("mensajeNotificacion datos");
		System.out.println(datos);
		
		
		SqlParameterSource in = new MapSqlParameterSource(datos);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		   
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("mensaje"));
	}

	@Override
	public Map<String, Object> getAnalista(Map<String, Object> datos) {
		
SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getAnalista");
		
		System.out.println("getAnalista datos");
		System.out.println(datos);
		
		
		SqlParameterSource in = new MapSqlParameterSource(datos);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		   
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return map;
	}

	@Override
	public List<Solicitud> consultarSolicitudAnalista(ConsultaIncidenteForm form) {
		
		List<Solicitud> list = new ArrayList<Solicitud>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarSolicitudAnalista");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("nroCti", form.getNroCti());
		inParamMap.put("estado", form.getEstado());
		inParamMap.put("tipoSol", form.getTipoSol());
		inParamMap.put("fechaIni", form.getFechaIni());
		inParamMap.put("fechaFin", form.getFechaFin());
		inParamMap.put("nroSS", form.getNroSS());
		inParamMap.put("sistema", form.getSistema());
		inParamMap.put("proceso", form.getProceso());
		inParamMap.put("idAnalista", form.getIdAnalista());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		System.out.println("simpleJdbcCallResult");
		System.out.println(simpleJdbcCallResult);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			Solicitud sol = new Solicitud();
			
			sol.setId(String.valueOf(row.get("id")));
			sol.setAnalistaF(String.valueOf(row.get("analista")));
			sol.setNroCti(String.valueOf(row.get("nroCti")));
			sol.setEstado(String.valueOf(row.get("estado")));
			sol.setFechaReg(String.valueOf(row.get("fecha_reg")));
			sol.setNroSS(String.valueOf(row.get("nross")));
			sol.setPrioridad(String.valueOf(row.get("prioridad")));
			sol.setProceso(String.valueOf(row.get("proceso")));
			sol.setSistema(String.valueOf(row.get("sistema")));
			sol.setSolicitante(String.valueOf(row.get("solicitante")));
			sol.setTipoSol(String.valueOf(row.get("tipo_sol")));
			sol.setUsAfec(String.valueOf(row.get("us_afectados")));
			
			list.add(sol);
			
		});
			
		return list;
	}

	@Override
	public String saveEvaluacion(EvaluarIncidenteForm form) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("saveEvaluacion");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("valor",form.getValor());
		inParamMap.put("analisisFuncional",form.getAnalisisFuncional());
		inParamMap.put("analisisTecnico",form.getAnalisisTecnico());
		inParamMap.put("tProgramadoAtencion", form.gettProgramadoAtencion());
		inParamMap.put("tipoSolucion",form.getTipoSolucion());
		inParamMap.put("tCalAtencion", form.gettCalAtencion());
		inParamMap.put("nivelSolucion",form.getNivelSolucion());
		inParamMap.put("tRealAtencion",form.gettRealAtencion());
		inParamMap.put("modalErrorMensaje",form.getModalErrorMensaje());
		inParamMap.put("modalObservacionDetalle",form.getModalObservacionDetalle());
		inParamMap.put("areaSoporte",form.getAreaSoporte());
		inParamMap.put("servicioDesarrollo",form.getServicioDesarrollo());
		inParamMap.put("jefeDesarrollo", form.getJefeDesarrollo());
		inParamMap.put("analistaFuncional",form.getAnalistaFuncional());
		inParamMap.put("detalleAnalisisSoporte",form.getDetalleAnalisisSoporte());
		inParamMap.put("nombreProveedor",form.getNombreProveedor());
		inParamMap.put("detalleAnalisisProveedor",form.getDetalleAnalisisProveedor());
		inParamMap.put("generarSS",form.getGenerarSS());
		inParamMap.put("nroSolicitud",form.getNroSolicitud());
		inParamMap.put("modalRechazoDetalle",form.getModalRechazoDetalle());
		

		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("newSolIncidente"));
	}
	
	@Override
	public String saveSolucionAF(EvaluarIncidenteForm form) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("saveSolucionAF");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolucion",form.getIdSolucion());
		inParamMap.put("idSolicitud",form.getNroSolicitud());

		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("nroIncidente"));
	}
	

	public String updateEstadoIncidente(String idSolicitud, String idEstado) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("updateEstadoIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",idSolicitud);
		inParamMap.put("idEstado",idEstado);
		

		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("idSolicitud"));
	}

	@Override
	public String getEmailOp(String idSolicitud) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getEmailOp");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolicitud",idSolicitud);
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("correo"));
	}

}
