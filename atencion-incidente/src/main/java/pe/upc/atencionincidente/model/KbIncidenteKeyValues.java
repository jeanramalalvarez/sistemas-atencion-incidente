package pe.upc.atencionincidente.model;

public class KbIncidenteKeyValues{
	
	private String idIncidenteBase;
	
	private String idKeyValue;
		
	private String usuarioAdicion;
	
	private String valoresClaveIncidente;

	public String getIdIncidenteBase() {
		return idIncidenteBase;
	}

	public void setIdIncidenteBase(String idIncidenteBase) {
		this.idIncidenteBase = idIncidenteBase;
	}

	public String getIdKeyValue() {
		return idKeyValue;
	}

	public void setIdKeyValue(String idKeyValue) {
		this.idKeyValue = idKeyValue;
	}

	public String getUsuarioAdicion() {
		return usuarioAdicion;
	}

	public void setUsuarioAdicion(String usuarioAdicion) {
		this.usuarioAdicion = usuarioAdicion;
	}
	
	public String getValoresClaveIncidente() {
		return valoresClaveIncidente;
	}
	
	public void setValoresClaveIncidente(String valoresClaveIncidente) {
		this.valoresClaveIncidente = valoresClaveIncidente;
	}
	
	
}