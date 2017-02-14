package pe.upc.atencionincidente.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.upc.atencionincidente.dao.IncidenteDAO;
import pe.upc.atencionincidente.model.KbIncidente;
import pe.upc.atencionincidente.model.KbIncidenteKeyValues;
import pe.upc.atencionincidente.model.KbSolucion;

@SuppressWarnings("unchecked")
@Repository
public class IncidenteDaoImpl implements IncidenteDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<KbIncidente> buscarKbIncidente(KbIncidente form) {
		
		List<KbIncidente> list = new ArrayList<KbIncidente>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarKbIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("idIncidenteBase", form.getIdIncidenteBase());
		inParamMap.put("idTipoSolicitud", form.getIdTipoSolicitud());
		inParamMap.put("idSistema", form.getIdSistema());
		inParamMap.put("idProceso", form.getIdProceso());
		inParamMap.put("idSubproceso", form.getIdSubproceso());
		inParamMap.put("idSolucion", null);
		inParamMap.put("idTipoSolucion", null);
		inParamMap.put("numSecuencia", null);
		System.out.println("consultarKbIncidente - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		System.out.println("simpleJdbcCallResult");
		System.out.println(simpleJdbcCallResult);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			KbIncidente sol = new KbIncidente();
			
			sol.setIdIncidenteBase(String.valueOf(row.get("ID_INCIDENTEBASE")));
			sol.setIdTipoSolicitud(String.valueOf(row.get("ID_TIPO_SOLICITUD")));
			sol.setIdSistema(String.valueOf(row.get("ID_SISTEMA")));
			sol.setIdProceso(String.valueOf(row.get("ID_PROCESO")));
			sol.setIdSubproceso(String.valueOf(row.get("ID_SUBPROCESO")));
			sol.setNuSecuencia(String.valueOf(row.get("NUM_SECUENCIA")));
			sol.setTxtDescripcion(String.valueOf(row.get("TXT_DESCRIPCION")));
			sol.setFlgResolucion(String.valueOf(row.get("FLG_RESOLUCION")));
			
			list.add(sol);
			
		});
			
		return list;
	}

	public String registrarKbIncidente(KbIncidente form){
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("registrarKbIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idIncidenteBase", form.getIdIncidenteBase());
		inParamMap.put("idTipoSolicitud", form.getIdTipoSolicitud());
		inParamMap.put("idSistema", form.getIdSistema());
		inParamMap.put("idProceso", form.getIdProceso());
		inParamMap.put("idSubProceso",form.getIdSubproceso());
		inParamMap.put("nuSecuencia", form.getNuSecuencia());
		inParamMap.put("txtDescripcion",form.getTxtDescripcion());
		inParamMap.put("flgResolucion", form.getFlgResolucion());
		inParamMap.put("usuarioAdicion", form.getUsuarioAdicion());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("newIdIncidenteBase"));
		
	}
	
	@Override
	public int obtenerSecuencia() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarKbIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", 6);
		inParamMap.put("idIncidenteBase", null);
		inParamMap.put("idTipoSolicitud", null);
		inParamMap.put("idSistema", null);
		inParamMap.put("idProceso", null);
		inParamMap.put("idSubproceso", null);
		inParamMap.put("idSolucion", null);
		inParamMap.put("idTipoSolucion", null);
		inParamMap.put("numSecuencia", null);
		System.out.println("INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		System.out.println("simpleJdbcCallResult");
		System.out.println(simpleJdbcCallResult);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		int secuencia = Integer.valueOf(String.valueOf(data.get(0).get("NUM_SECUENCIA")));
		return secuencia;
	}

	@Override
	public List<Map<String, Object>> getListKeyValues() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listKeyValues");
		
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
	public String registrarKbIncidenteKeyValues(KbIncidenteKeyValues form) {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("registrarKbIncidenteKeyValues");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idIncidenteBase", form.getIdIncidenteBase());
		inParamMap.put("idKeyValue", form.getIdKeyValue());
		inParamMap.put("usuarioAdicion", form.getUsuarioAdicion());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("newIdIncidenteBase"));
	}
	
	@Override
	public List<KbIncidente> buscarKbIncidenteValorClave(KbIncidente form) {
		
		List<KbIncidente> list = new ArrayList<KbIncidente>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarKbIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("idIncidenteBase", form.getIdIncidenteBase());
		inParamMap.put("idTipoSolicitud", null);
		inParamMap.put("idSistema", null);
		inParamMap.put("idProceso", null);
		inParamMap.put("idSubproceso", null);
		inParamMap.put("idSolucion", null);
		inParamMap.put("idTipoSolucion", null);
		inParamMap.put("numSecuencia", null);
		System.out.println("consultarKbIncidente - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		System.out.println("simpleJdbcCallResult");
		System.out.println(simpleJdbcCallResult);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			KbIncidente sol = new KbIncidente();
			
			sol.setIdIncidenteBase(String.valueOf(row.get("ID_INCIDENTEBASE")));
			sol.setKeyValue(String.valueOf(row.get("ID_KEYVALUE")));
			
			list.add(sol);
			
		});
			
		return list;
	}

	@Override
	public List<KbSolucion> buscarKbSolucion(KbSolucion form) {
		List<KbSolucion> list = new ArrayList<KbSolucion>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarKbIncidente");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("idIncidenteBase", form.getIdIncidenteBase());
		inParamMap.put("idTipoSolicitud", null);
		inParamMap.put("idSistema", null);
		inParamMap.put("idProceso", null);
		inParamMap.put("idSubproceso", null);
		inParamMap.put("idSolucion", null);
		inParamMap.put("idTipoSolucion", null);
		inParamMap.put("numSecuencia", null);
		System.out.println("consultarKbIncidente - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		System.out.println("simpleJdbcCallResult");
		System.out.println(simpleJdbcCallResult);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			KbSolucion sol = new KbSolucion();
			
			sol.setIdIncidenteBase(String.valueOf(row.get("ID_INCIDENTEBASE")));
			sol.setIdSolucion(String.valueOf(row.get("ID_SOLUCION")));
			sol.setNuSecuencia(String.valueOf(row.get("NUM_SECUENCIA")));
			sol.setNuPrioridad(String.valueOf(row.get("NUM_PRIORIDAD")));
			sol.setNuVecesUso(String.valueOf(row.get("NUM_VECESUSO")));
			sol.setTxtDescripcion(String.valueOf(row.get("TXT_DESCRIPCION")));
			
			list.add(sol);
			
		});
		
		return list;
		
	}

	@Override
	public String registrarKbSolucion(KbSolucion form) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("registrarKbSolucion");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolucion", form.getIdSolucion());
		inParamMap.put("idIncidenteBase", form.getIdIncidenteBase());
		inParamMap.put("nuSecuencia", form.getNuSecuencia());
		inParamMap.put("txtDescripcion",form.getTxtDescripcion());
		inParamMap.put("nuPrioridad", form.getNuPrioridad());
		inParamMap.put("nuVecesUso", form.getNuVecesUso());
		inParamMap.put("usuarioAdicion", form.getUsuarioAdicion());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("newIdSolucion"));
	}


}
