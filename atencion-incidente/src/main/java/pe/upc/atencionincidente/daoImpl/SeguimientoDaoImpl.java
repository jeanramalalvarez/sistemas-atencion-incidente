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

import pe.upc.atencionincidente.dao.SeguimientoDAO;
import pe.upc.atencionincidente.model.Analista;
import pe.upc.atencionincidente.model.Seguimiento;
import pe.upc.atencionincidente.model.SeguimientoCarteraAF;

@SuppressWarnings("unchecked")
@Repository
public class SeguimientoDaoImpl implements SeguimientoDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Analista> getAnalista() {
		List<Analista> list = new ArrayList<Analista>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listAnalista");
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute();
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			Analista sol = new Analista();
			
			sol.setId(String.valueOf(row.get("id")));
			sol.setNombresApellidos(String.valueOf(row.get("nombresApellidos")));
			
			list.add(sol);
			
		});
			
		return list;
	}

	@Override
	public List<SeguimientoCarteraAF> getCarteraAnalistaFuncional(Seguimiento form){
		
		List<SeguimientoCarteraAF> list = new ArrayList<SeguimientoCarteraAF>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listSeguimientoCTI");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipo());
		inParamMap.put("fecInicio", form.getFecInicio());
		inParamMap.put("fecFin", form.getFecFin());
		inParamMap.put("idAnalista", form.getIdAnalista());
		System.out.println("listSeguimientoCTI - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		    
		data.forEach(row->{
			
			SeguimientoCarteraAF sol = new SeguimientoCarteraAF();
			
			sol.setAnalistaFunciona(String.valueOf(row.get("analistaFuncional")));
			sol.setEnProceso(Integer.valueOf(String.valueOf(row.get("enProceso"))));
			sol.setEnProceso(Integer.valueOf(String.valueOf(row.get("asignados"))));
			sol.setEnProceso(Integer.valueOf(String.valueOf(row.get("total"))));
			
			list.add(sol);
			
		});
			
		return list;
	}
	
}
