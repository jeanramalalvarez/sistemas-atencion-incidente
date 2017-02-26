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

import pe.upc.atencionincidente.dao.ValidaRegistroDAO;
import pe.upc.atencionincidente.model.KbValidaRegistro;
import pe.upc.atencionincidente.model.KbValidaRegistroDet;


@SuppressWarnings("unchecked")
@Repository
public class ValidaRegistroDaoImpl implements ValidaRegistroDAO {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<KbValidaRegistro> buscarKbValidaRegistro(KbValidaRegistro form) {
		List<KbValidaRegistro> list = new ArrayList<KbValidaRegistro>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarKbValidaRegistro");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("idTipoSolicitud", form.getIdTipoSolicitud());
		inParamMap.put("idSistema", form.getIdSistema());
		inParamMap.put("idProceso", form.getIdProceso());
		inParamMap.put("idSubproceso", form.getIdSubProceso());		
		inParamMap.put("numSecuencia", form.getNumSecuencia());
		inParamMap.put("numClave",0);


		System.out.println("consultarKbValidaRegistro - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		System.out.println("simpleJdbcCallResult");
		System.out.println(simpleJdbcCallResult);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			KbValidaRegistro sol = new KbValidaRegistro();
			
			sol.setIdTipoSolicitud(String.valueOf(row.get("ID_TIPO_SOLICITUD")));
			sol.setIdSistema(String.valueOf(row.get("ID_SISTEMA")));
			sol.setIdProceso(String.valueOf(row.get("ID_PROCESO")));
			sol.setIdSubProceso(String.valueOf(row.get("ID_SUBPROCESO")));
			sol.setNumSecuencia(String.valueOf(row.get("NUM_SECUENCIA")));
			sol.setTxtDescripcion(String.valueOf(row.get("TXT_DESCRIPCION")));
			sol.setTxtObservacion(String.valueOf(row.get("TXT_OBSERVACION")));
			
			list.add(sol);
			
		});
			
		return list;

	}

	@Override
	public String registrarKbValidaRegistro(KbValidaRegistro form) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("registrarKbValidaRegistro");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idTipoSolicitud", form.getIdTipoSolicitud());
		inParamMap.put("idSistema", form.getIdSistema());
		inParamMap.put("idProceso", form.getIdProceso());
		inParamMap.put("idSubProceso",form.getIdSubProceso());
		inParamMap.put("numSecuencia", form.getNumSecuencia());
		inParamMap.put("txtDescripcion",form.getTxtDescripcion());
		inParamMap.put("txtObservacion", form.getTxtObservacion());
		inParamMap.put("usuarioAdicion", form.getUsuarioAdicion());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("newNumSecuencia"));
	
	}

	@Override
	public void eliminarKbValidaRegistro(KbValidaRegistro form) {
	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("eliminarKbValidaRegistro");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("numSecuencia", form.getNumSecuencia());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		simpleJdbcCall.execute(in);		
	}

	@Override
	public List<KbValidaRegistroDet> buscarKbValidaRegistroDet(KbValidaRegistroDet form) {
		List<KbValidaRegistroDet> list = new ArrayList<KbValidaRegistroDet>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarKbValidaRegistro");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("idTipoSolicitud", form.getIdTipoSolicitud());
		inParamMap.put("idSistema", form.getIdSistema());
		inParamMap.put("idProceso", form.getIdProceso());
		inParamMap.put("idSubProceso", form.getIdSubProceso());		
		inParamMap.put("numSecuencia", form.getNumSecuencia());
		inParamMap.put("numClave", form.getNumClave());
		

		System.out.println("buscarKbValidaRegistroDet - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		System.out.println("simpleJdbcCallResult");
		System.out.println(simpleJdbcCallResult);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			KbValidaRegistroDet sol = new KbValidaRegistroDet();
			
			sol.setIdTipoSolicitud(String.valueOf(row.get("ID_TIPO_SOLICITUD")));
			sol.setIdSistema(String.valueOf(row.get("ID_SISTEMA")));
			sol.setIdProceso(String.valueOf(row.get("ID_PROCESO")));
			sol.setIdSubProceso(String.valueOf(row.get("ID_SUBPROCESO")));
			sol.setNumSecuencia(String.valueOf(row.get("NUM_SECUENCIA")));
			sol.setNumClave(String.valueOf(row.get("NUM_CLAVE")));
			sol.setTipClave(String.valueOf(row.get("TIP_CLAVE")));
			sol.setDeTipClave(String.valueOf(row.get("DE_TIP_CLAVE")));
			sol.setTxtValorClave(String.valueOf(row.get("TXT_VALOR_CLAVE")));
			sol.setNumLogitudParam(String.valueOf(row.get("NUM_LONGITUD_PARAM")));
			sol.setTxtFormatoParam(String.valueOf(row.get("TXT_FORMATO_PARAM")));
			list.add(sol);
			
		});
			
		return list;

	}

	@Override
	public String registrarKbValidaRegistroDet(KbValidaRegistroDet form) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("registrarKbValidaRegistroDet");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idTipoSolicitud", form.getIdTipoSolicitud());
		inParamMap.put("idSistema", form.getIdSistema());
		inParamMap.put("idProceso", form.getIdProceso());
		inParamMap.put("idSubProceso",form.getIdSubProceso());
		inParamMap.put("numSecuencia", form.getNumSecuencia());
		inParamMap.put("numClave",form.getNumClave());
		inParamMap.put("tipClave",form.getTipClave());
		inParamMap.put("txtValorClave", form.getTxtValorClave());
		inParamMap.put("numLongitudParam", form.getNumLogitudParam());
		inParamMap.put("txtFormatoParam", form.getTxtFormatoParam());
		inParamMap.put("usuarioAdicion", form.getUsuarioAdicion());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("newNumClave"));
	}

	@Override
	public void eliminarKbValidaRegistroDet(KbValidaRegistroDet form) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("eliminarKbValidaRegistroDet");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("numSecuencia", form.getNumSecuencia());
		inParamMap.put("numClave", form.getNumClave());
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		simpleJdbcCall.execute(in);				
	}

}
