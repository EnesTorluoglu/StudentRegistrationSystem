import java.util.*;

// Controller class
class Controller {
    private final UniversityFileSystem UNIVERSITY_FILE_SYSTEM;
    private jsonWriter jsonWriter;
    private final UI UI;
    private User user;
    //Default Constructor
    public Controller() {
        UNIVERSITY_FILE_SYSTEM = new UniversityFileSystem();
        UI = new UI();
        UI.initialize();
    }
    //Constructor
    public Controller(Scanner input) {
        UNIVERSITY_FILE_SYSTEM = new UniversityFileSystem();
        UI = new UI(input);
        UI.initialize();
    }



    public void start() {
        // load all json course and person files
        UNIVERSITY_FILE_SYSTEM.loadFiles();
        /*
        String[] userInfo = UI.requestCredentials();
        user = UNIVERSITY_FILE_SYSTEM.getSignedPerson(userInfo, this);
        if (user != null)
        user.startActions(this);
        */
        String[] startMenu = new String[3];
        startMenu[0] = "Select an action.";
        startMenu[1] = "1) Log in.";
        startMenu[2] = "2) Exit.";
        int actionNumber = 1;
        while(true) {
        	actionNumber = UI.printConsoleListReturnSelection(startMenu, -1);
        	if (actionNumber == 2) break;
        	
        	String[] userInfo = UI.requestCredentials();
            user = UNIVERSITY_FILE_SYSTEM.getSignedPerson(userInfo, this);
            if (user != null)
            user.startActions(this);
        }
        
        jsonWriter = new jsonWriter((Person) user);
        jsonWriter.saveFiles();
        UI.callEndMessage(0);
    }


    public void printErrorMessage(String errorMessage) {
        UI.printConsoleErrorMessage(errorMessage);
    }

    //Error int -1 for no error
    public int printListReturnSelection(String[] stringsList, int errorInt) {
        return UI.printConsoleListReturnSelection(stringsList, errorInt);
    }

    public void printList(String[] stringList) {
        UI.printConsoleList(stringList);
    }

    public void printSuccessMessage(String successMessage) {
        UI.printConsoleSuccessMessage(successMessage);
    }
    
    public String[] requestMessageString() {
    	return UI.requestMessageStringFromUser();
    }
}



