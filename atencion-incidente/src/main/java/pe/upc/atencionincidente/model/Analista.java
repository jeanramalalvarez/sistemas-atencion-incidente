package pe.upc.atencionincidente.model;

import java.util.List;

public class Analista {
	
	String id;
	String nombresApellidos;
	String area;
	String cargo;
	
	private List<SeguimientoProductividad> productividadList;

	public String getNombresApellidos() {
		return nombresApellidos;
	}
	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public List<SeguimientoProductividad> getProductividadList() {
		return productividadList;
	}
	
	public void setProductividadList(
			List<SeguimientoProductividad> productividadList) {
		this.productividadList = productividadList;
	}
}