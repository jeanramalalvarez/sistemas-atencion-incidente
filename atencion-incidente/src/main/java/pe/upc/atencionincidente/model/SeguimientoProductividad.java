package pe.upc.atencionincidente.model;

public class SeguimientoProductividad {
	
	private String fecha;
	
	private String idAnalista;
	
	private String analista;
	
	private Integer productividad;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getAnalista() {
		return analista;
	}

	public void setAnalista(String analista) {
		this.analista = analista;
	}

	public Integer getProductividad() {
		return productividad;
	}

	public void setProductividad(Integer productividad) {
		this.productividad = productividad;
	}

}
