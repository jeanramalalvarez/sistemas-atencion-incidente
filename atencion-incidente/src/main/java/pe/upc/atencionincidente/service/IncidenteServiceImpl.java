package pe.upc.atencionincidente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.atencionincidente.dao.IncidenteDAO;
import pe.upc.atencionincidente.model.KbIncidente;

@Service(value="incidenteService")
public class IncidenteServiceImpl implements IncidenteService {
	
	@Autowired
	private IncidenteDAO incidenteDAO; 

	@Override
	public List<KbIncidente> buscarKbIncidente(KbIncidente form) {
		return incidenteDAO.buscarKbIncidente(form);
	}


}
