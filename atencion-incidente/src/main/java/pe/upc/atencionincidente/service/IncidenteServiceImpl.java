package pe.upc.atencionincidente.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.atencionincidente.dao.IncidenteDAO;
import pe.upc.atencionincidente.model.KbIncidente;
import pe.upc.atencionincidente.model.KbIncidenteKeyValues;

@Service(value="incidenteService")
public class IncidenteServiceImpl implements IncidenteService {
	
	@Autowired
	private IncidenteDAO incidenteDAO; 

	@Override
	public List<KbIncidente> buscarKbIncidente(KbIncidente form) {
		return incidenteDAO.buscarKbIncidente(form);
	}
	
	@Override
	public String registrarKbIncidente(KbIncidente form) {
		return incidenteDAO.registrarKbIncidente(form);
	}

	@Override
	public int obtenerSecuencia() {
		return incidenteDAO.obtenerSecuencia();
	}

	@Override
	public List<Map<String, Object>> getListKeyValues() {
		return incidenteDAO.getListKeyValues();
	}

	@Override
	public String registrarKbIncidenteKeyValues(KbIncidenteKeyValues form) {
		return incidenteDAO.registrarKbIncidenteKeyValues(form);
	}

	@Override
	public List<KbIncidente> buscarKbIncidenteValorClave(KbIncidente form) {
		return incidenteDAO.buscarKbIncidenteValorClave(form);
	}

}
