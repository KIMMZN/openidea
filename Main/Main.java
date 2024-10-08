package Main;

import service.IdeaService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Open Idea");
		
		IdeaService is = new IdeaService();
		is.menu();
	}

}
