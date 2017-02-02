package pe.upc.atencionincidente.service;

import java.util.List;

import pe.upc.atencionincidente.model.KbIncidente;

public interface IncidenteService {

	List<KbIncidente> buscarKbIncidente(KbIncidente form);
	
}
