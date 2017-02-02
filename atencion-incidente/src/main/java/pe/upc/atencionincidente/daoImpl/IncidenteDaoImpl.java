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
		inParamMap.put("tipo", 1);
		inParamMap.put("idIncidenteBase", null);
		inParamMap.put("idTipoSolicitud", form.getIdTipoSolicitud());
		inParamMap.put("idSistema", form.getIdSistema());
		inParamMap.put("idProceso", form.getIdProceso());
		inParamMap.put("idSubproceso", form.getIdSubproceso());
		inParamMap.put("idSolucion", null);
		inParamMap.put("idTipoSolucion", null);
		inParamMap.put("numSecuencia", null);
		
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


}
