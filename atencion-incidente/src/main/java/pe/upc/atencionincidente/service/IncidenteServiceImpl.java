package pe.upc.atencionincidente.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.atencionincidente.dao.IncidenteDAO;
import pe.upc.atencionincidente.model.KbIncidente;
import pe.upc.atencionincidente.model.KbIncidenteKeyValues;
import pe.upc.atencionincidente.model.KbSolucion;
import pe.upc.atencionincidente.model.KbSolucionCheck;
import pe.upc.atencionincidente.model.KbSolucionSetup;
import pe.upc.atencionincidente.model.KbSolucionSetupCheck;

@Service
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
	
	@Override
	public void eliminarKbIncidenteKeyValues(KbIncidenteKeyValues form) {
		incidenteDAO.eliminarKbIncidenteKeyValues(form);
	}

	@Override
	public List<KbSolucion> buscarKbSolucion(KbSolucion form) {
		return incidenteDAO.buscarKbSolucion(form);
	}

	@Override
	public String registrarKbSolucion(KbSolucion form) {
		return incidenteDAO.registrarKbSolucion(form);
	}

	@Override
	public void eliminarKbSolucion(KbSolucion form) {
		incidenteDAO.eliminarKbSolucion(form);
	}

	@Override
	public List<KbSolucionSetup> buscarKbSolucionSetup(KbSolucionSetup form) {
		return incidenteDAO.buscarKbSolucionSetup(form);
	}

	@Override
	public String registrarKbSolucionSetup(KbSolucionSetup form) {
		return incidenteDAO.registrarKbSolucionSetup(form);
	}

	@Override
	public void eliminarKbSolucionSetup(KbSolucionSetup form) {
		incidenteDAO.eliminarKbSolucionSetup(form);
	}

	@Override
	public List<KbSolucionCheck> buscarKbSolucionCheck(KbSolucionCheck form) {
		return incidenteDAO.buscarKbSolucionCheck(form);
	}

	@Override
	public String registrarKbSolucionSetupCheck(KbSolucionSetupCheck form) {
		return incidenteDAO.registrarKbSolucionSetupCheck(form);
	}

	@Override
	public void eliminarKbSolucionSetupCheck(KbSolucionSetupCheck form) {
		incidenteDAO.eliminarKbSolucionSetupCheck(form);
	}

}
