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

import pe.upc.atencionincidente.model.KbSolucionCheck;
@SuppressWarnings("unchecked")
@Repository
public class SolucionCheckDaoImpl implements SolucionCheckDao {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<KbSolucionCheck> buscarKbSolucionCheck(KbSolucionCheck form) {
		List<KbSolucionCheck> list = new ArrayList<KbSolucionCheck>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("consultarKbSolucionCheck");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("idSolucionCh", form.getIdSolucionCh());
		/*inParamMap.put("txtGlosa", form.getTxtGlosa());
		inParamMap.put("txtPautas", form.getTxtPautas());
		inParamMap.put("txtDescripcion", form.getTxtDescripcion());		
		inParamMap.put("txtAnexo", form.getTxtAnexo());
		inParamMap.put("txtRuta",form.getTxtRuta());*/

		System.out.println("consultarKbSolucionCheck - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		System.out.println("simpleJdbcCallResult");
		System.out.println(simpleJdbcCallResult);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			KbSolucionCheck sol = new KbSolucionCheck();
			
			sol.setIdSolucionCh(String.valueOf(row.get("ID_SOLUCION_CH")));
			sol.setTxtGlosa(String.valueOf(row.get("TXT_GLOSA")));
			sol.setTxtPautas(String.valueOf(row.get("TXT_PAUTAS")));
			sol.setTxtDescripcion(String.valueOf(row.get("TXT_DESCRIPCION")));
			sol.setTxtAnexo(String.valueOf(row.get("TXT_ANEXO")));
			sol.setTxtRuta(String.valueOf(row.get("TXT_RUTA")));
			

			
			list.add(sol);
			
		});
			
		return list;

	}

	@Override
	public String registrarKbSolucionCheck(KbSolucionCheck form) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("registrarKbSolucionCheck");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolucionCh", form.getIdSolucionCh());
		inParamMap.put("txtGlosa", form.getTxtGlosa());
		inParamMap.put("txtPautas", form.getTxtPautas());
		inParamMap.put("txtDescripcion", form.getTxtDescripcion());		
		inParamMap.put("txtAnexo", form.getTxtAnexo());
		inParamMap.put("txtRuta",form.getTxtRuta());
		inParamMap.put("usuarioAdicion", form.getUsuarioAdicion());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		System.out.println(data);
		
		Map<String,Object> map = data.get(0);
		
		return String.valueOf(map.get("newIdSolucionCh"));
	
	}

	@Override
	public void eliminarKbSolucionCheck(KbSolucionCheck form) {
	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("eliminarKbSolucionCheck");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("idSolucionCh", form.getIdSolucionCh());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		simpleJdbcCall.execute(in);		
	}

}