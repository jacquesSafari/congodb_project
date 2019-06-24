package Data;


import java.util.Scanner;

public class Message extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String Alert(String message) {
		return message;
	}
	
	public static String Confirm(String message) {
		return "R�ussite "+message;
	}
	
	public static String Error(Exception e) {
		return ""+e.getMessage()+"\t"+e.getCause()+"\t"+e.getClass();
		
		
	}
	//******************************************************************
	
	//******************************************************************
	public Message(String e) {
		System.out.println("Exception "+e);
	}
	
	public static boolean Menu() {
		Message.Alert("");
		Message.Alert("Taper 1 ou 2");
		Message.Alert("1. Nouvelle requette");
		Message.Alert("2. Quitter");
		Message.Alert("------------------");
		Scanner scanner=new Scanner(System.in);
		try{
			int value=scanner.nextInt();
		
		
		if(value==1)
			return true;
		else
			return false;
		}
		catch(Exception e) {Message.Alert("la valeur saisie est incorrecte");
			return false;
		}
	}
}
