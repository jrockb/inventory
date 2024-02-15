package co.com.jcd.inventory.util;

public enum Constants {
	
	OK("Respuesta OK"),
	COD_OK("00"),
	ERROR_NEGOCIO("Error de lógica"),
	COD_ERROR("-1"),
	ERROR("Error no especificado");	
	
	private String valor;

	Constants(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
