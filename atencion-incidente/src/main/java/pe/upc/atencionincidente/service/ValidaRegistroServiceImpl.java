package pe.upc.atencionincidente.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.atencionincidente.dao.ValidaRegistroDAO;
import pe.upc.atencionincidente.model.KbValidaRegistro;
import pe.upc.atencionincidente.model.KbValidaRegistroDet;

@Service

public class ValidaRegistroServiceImpl implements ValidaRegistroService {

	@Autowired
	private ValidaRegistroDAO validaRegistroDAO; 
	
	@Override
	public List<KbValidaRegistro> buscarKbValidaRegistro(KbValidaRegistro form) {
		return validaRegistroDAO.buscarKbValidaRegistro(form);
	}

	@Override
	public String registrarKbValidaRegistro(KbValidaRegistro form) {
		return validaRegistroDAO.registrarKbValidaRegistro(form);
	}

	@Override
	public List<Map<String, Object>> getListKeyValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarKbValidaRegistro(KbValidaRegistro form) {
		 validaRegistroDAO.eliminarKbValidaRegistro(form);
	}

	@Override
	public List<KbValidaRegistroDet> buscarKbValidaRegistroDet(KbValidaRegistroDet form) {
		// TODO Auto-generated method stub
		return validaRegistroDAO.buscarKbValidaRegistroDet(form);
	}

	@Override
	public String registrarKbValidaRegistroDet(KbValidaRegistroDet form) {
		// TODO Auto-generated method stub
		return validaRegistroDAO.registrarKbValidaRegistroDet(form);
	}

	@Override
	public void eliminarKbValidaRegistroDet(KbValidaRegistroDet form) {
		 validaRegistroDAO.eliminarKbValidaRegistroDet(form);	
	}



}
