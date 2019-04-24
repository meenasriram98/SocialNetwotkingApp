import com.imaginea.training.service.ProcessService;
import com.imaginea.training.service.MenuService;

public class SocialNetwork {
	
	static MenuService menuService=new MenuService();
	static ProcessService menuProcess=new ProcessService();
	public static void main(String[] args) {
		menuService.printMenu();
		menuProcess.processInput();
	}
}


