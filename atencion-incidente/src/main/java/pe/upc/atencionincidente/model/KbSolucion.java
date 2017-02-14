package pe.upc.atencionincidente.model;

public class KbSolucion{
	
	private String idIncidenteBase;
	
	private String idSolucion;
	
	private String nuSecuencia;
	
	private String nuPrioridad;
	
	private String nuVecesUso;
	
	private String txtDescripcion;
	
	private String usuarioAdicion;
	
	private String tipoConsulta;

	public String getIdIncidenteBase() {
		return idIncidenteBase;
	}

	public void setIdIncidenteBase(String idIncidenteBase) {
		this.idIncidenteBase = idIncidenteBase;
	}

	public String getIdSolucion() {
		return idSolucion;
	}

	public void setIdSolucion(String idSolucion) {
		this.idSolucion = idSolucion;
	}

	public String getNuSecuencia() {
		return nuSecuencia;
	}

	public void setNuSecuencia(String nuSecuencia) {
		this.nuSecuencia = nuSecuencia;
	}

	public String getNuPrioridad() {
		return nuPrioridad;
	}

	public void setNuPrioridad(String nuPrioridad) {
		this.nuPrioridad = nuPrioridad;
	}

	public String getNuVecesUso() {
		return nuVecesUso;
	}

	public void setNuVecesUso(String nuVecesUso) {
		this.nuVecesUso = nuVecesUso;
	}

	public String getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public String getUsuarioAdicion() {
		return usuarioAdicion;
	}

	public void setUsuarioAdicion(String usuarioAdicion) {
		this.usuarioAdicion = usuarioAdicion;
	}
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	
}