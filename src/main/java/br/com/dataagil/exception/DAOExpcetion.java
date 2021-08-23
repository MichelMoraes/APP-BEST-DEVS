package br.com.dataagil.exception;

public class DAOExpcetion extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DAOExpcetion(String msg, Throwable causa) {
		
		super(msg, causa);
	}

	
	public DAOExpcetion(String msg){
		super(msg);
	}
	
	public DAOExpcetion(Throwable causa){
		super(causa);
	}


	public DAOExpcetion(String msg, String message) {
	}


	public String getMessage(String string) {
		return null;
	}

	
	
}
