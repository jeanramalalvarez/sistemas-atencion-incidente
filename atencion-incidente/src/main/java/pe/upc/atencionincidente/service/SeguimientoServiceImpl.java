package pe.upc.atencionincidente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.atencionincidente.dao.SeguimientoDAO;
import pe.upc.atencionincidente.model.Seguimiento;
import pe.upc.atencionincidente.model.SeguimientoCarteraAF;

@Service
public class SeguimientoServiceImpl implements SeguimientoService {
	
	@Autowired
	private SeguimientoDAO seguimientoDAO;

	@Override
	public List<SeguimientoCarteraAF> getCarteraAnalistaFuncional(Seguimiento form) {
		return seguimientoDAO.getCarteraAnalistaFuncional(form);
	} 

	
}
