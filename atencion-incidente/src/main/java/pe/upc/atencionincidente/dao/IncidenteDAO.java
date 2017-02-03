package pe.upc.atencionincidente.dao;

import java.util.List;

import pe.upc.atencionincidente.model.KbIncidente;

public interface IncidenteDAO {

	List<KbIncidente> buscarKbIncidente(KbIncidente form);
	
	String registrarKbIncidente(KbIncidente form);
	
	int obtenerSecuencia();
	
}
