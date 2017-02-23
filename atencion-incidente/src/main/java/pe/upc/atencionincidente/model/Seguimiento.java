package pe.upc.atencionincidente.model;

public class Seguimiento {
	
	private String tipoConsulta;
	
	private String fecInicio;
	
	private String fecFin;
	
	private String idAnalista;

	public String getTipoConsulta() {
		return tipoConsulta;
	}
	
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(String fecInicio) {
		this.fecInicio = fecInicio;
	}

	public String getFecFin() {
		return fecFin;
	}

	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

}
