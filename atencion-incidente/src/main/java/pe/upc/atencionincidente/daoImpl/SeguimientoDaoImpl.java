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
import pe.upc.atencionincidente.model.SeguimientoCarteraCTI;
import pe.upc.atencionincidente.model.SeguimientoDemandaOferta;
import pe.upc.atencionincidente.model.SeguimientoProductividad;

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
	public List<SeguimientoCarteraAF> getCarteraAF(Seguimiento form){
		
		List<SeguimientoCarteraAF> list = new ArrayList<SeguimientoCarteraAF>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listSeguimientoCTI");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("fecInicio", form.getFecInicio());
		inParamMap.put("fecFin", form.getFecFin());
		inParamMap.put("idAnalista", form.getIdAnalista());
		System.out.println("listSeguimientoCTI - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		System.out.println(data);
		    
		data.forEach(row->{
			
			SeguimientoCarteraAF sol = new SeguimientoCarteraAF();
			
			sol.setAnalistaFunciona(String.valueOf(row.get("analistaFuncional")));
			sol.setEnProceso(Integer.valueOf(String.valueOf(row.get("enProceso"))));
			sol.setAsignado(Integer.valueOf(String.valueOf(row.get("asignados"))));
			sol.setTotal(Integer.valueOf(String.valueOf(row.get("total"))));
			
			list.add(sol);
			
		});
			
		return list;
	}

	@Override
	public List<SeguimientoCarteraCTI> getCarteraCTI(Seguimiento form) {
		
		List<SeguimientoCarteraCTI> list = new ArrayList<SeguimientoCarteraCTI>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listSeguimientoCTI");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("fecInicio", form.getFecInicio());
		inParamMap.put("fecFin", form.getFecFin());
		inParamMap.put("idAnalista", form.getIdAnalista());
		System.out.println("listSeguimientoCTI - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		System.out.println(data);
		    
		data.forEach(row->{
			
			SeguimientoCarteraCTI sol = new SeguimientoCarteraCTI();
			
			sol.setEstado(String.valueOf(row.get("estado")));
			sol.setTotal(Integer.valueOf(String.valueOf(row.get("total"))));
			
			list.add(sol);
			
		});
			
		return list;
	}

	@Override
	public List<SeguimientoDemandaOferta> getDemandaOferta(Seguimiento form) {
		
		List<SeguimientoDemandaOferta> list = new ArrayList<SeguimientoDemandaOferta>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listSeguimientoCTI");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("fecInicio", form.getFecInicio());
		inParamMap.put("fecFin", form.getFecFin());
		inParamMap.put("idAnalista", form.getIdAnalista());
		System.out.println("listSeguimientoCTI - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data= (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		System.out.println(data);
		    
		data.forEach(row->{
			
			SeguimientoDemandaOferta sol = new SeguimientoDemandaOferta();
			
			sol.setFecha(String.valueOf(row.get("fecha")));
			sol.setCartera(Integer.valueOf(String.valueOf(row.get("cartera"))));
			sol.setCapacidad(Integer.valueOf(String.valueOf(row.get("capacidad"))));
			sol.setOferta(Integer.valueOf(String.valueOf(row.get("oferta"))));
			sol.setDemanda(Integer.valueOf(String.valueOf(row.get("demanda"))));
			list.add(sol);
			
		});
			
		return list;
	}

	@Override
	public List<SeguimientoProductividad> getProductividad(Seguimiento form) {
		
		List<SeguimientoProductividad> list = new ArrayList<SeguimientoProductividad>();
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("listSeguimientoCTI");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("tipo", form.getTipoConsulta());
		inParamMap.put("fecInicio", form.getFecInicio());
		inParamMap.put("fecFin", form.getFecFin());
		inParamMap.put("idAnalista", form.getIdAnalista());
		System.out.println("listSeguimientoCTI - INPUT: " + inParamMap);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		ArrayList<Map<String,Object>> data = (ArrayList<Map<String,Object>>) simpleJdbcCallResult.get("#result-set-1");
		System.out.println(data);
		    
		data.forEach(row->{
			
			SeguimientoProductividad sol = new SeguimientoProductividad();
			
			sol.setFecha(String.valueOf(row.get("fecha")));
			sol.setIdAnalista(String.valueOf(row.get("id_Analista")));
			sol.setAnalista(String.valueOf(row.get("analista")));
			sol.setProductividad(Integer.valueOf(String.valueOf(row.get("productividad"))));
			list.add(sol);
			
		});
			
		return list;
	}
	
}
