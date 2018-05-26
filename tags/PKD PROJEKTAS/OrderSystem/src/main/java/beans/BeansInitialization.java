package beans;

//Singleton Pattern
public class BeansInitialization {
	
	private static BeansInitialization INSTANCE = null;
	
	private BeansInitialization(){

	}
	
	public synchronized static BeansInitialization getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new BeansInitialization();
		}
		return INSTANCE;
	}
	
}
