import java.util.ArrayList;
import java.util.List;

  // Method to add course to available list
    
// Student class
class Student extends Person implements User{
    private List<Course> currentAvailableCourses; // Courses that the student is able to register
    private List<Course> registrationWaitingCourses; //Courses that are waiting to be registered
    private List<Course> registrationCompleteCourses; // Courses that finished registration
    private List<Course> cancelWaitingCourses; // Courses that are waiting to be canceled 
    private Transcript currentTranscript;
    private String studentID; // Additional field for student ID
    private Advisor currentAdvisor;
    // Method to create student
   /* public static Student create(String name, String lastName) {
        return new Student(name, lastName);
    }*/
    
    // Constructor
    public Student(String name, String lastName,String username,String password, String studentID, Transcript currentTranscript, Advisor currentAdvisor) {
        super(name, lastName,username,password);
        this.studentID = studentID;
        this.currentTranscript = currentTranscript;
        this.currentAdvisor = currentAdvisor; 
        this.currentAvailableCourses = new ArrayList<>();
        this.registrationCompleteCourses = new ArrayList<>();
        this.cancelWaitingCourses = new ArrayList<>();
        this.registrationWaitingCourses = new ArrayList<>();
    }
/*
    @Override
    public String[] getActionList() {
        return new String[0];
    }
*/
    //@Override
 //   public void runUserAction(int actionNumber) {

//    }
@Override
    public String[] getActionList() {
    	
    	String[] actionList = new String[8];
    	
    	actionList[0] = "Select an action.";
    	actionList[1] = "1) Check courses available to register.";
    	actionList[2] = "2) Check your registered courses.";
        actionList[3] = "3) See courses waiting to be registered.";
        actionList[4] = "4) See courses waiting to be canceled.";
        actionList[5] = "5) User transcript.";
    	actionList[6] = "6) Advisor information.";
    	actionList[7] = "7) Log out.";
    	
        return actionList;
    }
    @Override
   public String toString() {
    
    return "Name: " + this.name + ", Last Name: " + this.lastName + ", ";
   }


    @Override
    public void startActions(Controller controller) {
        String[] actionList= getActionList();
        int actionNumber = 0;
        
        actionNumber = controller.printListReturnSelection(actionList);
        runUserAction(actionNumber, controller);
        return;
    }


  

    @Override
    public void runUserAction(int actionNumber, Controller controller) {
        // Student selection part
        final int maxAction = 7;
        int currentUserSelection;
        Course currentCourse;
        while(actionNumber != maxAction){
            switch (actionNumber) {
            
                case 1: currentUserSelection = controller.printListReturnSelection(
                    getCourseReturnListString("Courses that are available to you, choose a course to add:" ,currentAvailableCourses));
                    if(currentUserSelection != 1){
                        currentCourse = currentAvailableCourses.get(currentUserSelection);
                        if(currentCourse.checkCourseSection(registrationCompleteCourses, registrationWaitingCourses, cancelWaitingCourses)){
                            removeElementFromCurrentAvailableCourses(currentCourse);
                            registrationWaitingCourses.add(currentCourse);
                        }
                        else{
                            controller.printErrorMessage("This course does not fit into your course times!");
                        }
                    }
                break; 
                case 2: currentUserSelection = controller.printListReturnSelection(
                    getCourseReturnListString("Courses that have finalized registeration, choose course to cancel:", registrationCompleteCourses));
                    if(currentUserSelection != 1){
                        currentCourse = registrationCompleteCourses.get(currentUserSelection);
                        removeElementFromRegistrationCompleteCourses(currentCourse);
                        cancelWaitingCourses.add(currentCourse);
                    }
                break;
                case 3: controller.printList(
                    getCourseListString("Courses that are waiting to be finalized by your advisor :", registrationWaitingCourses)); 
                break; 
                case 4: controller.printList(
                    getCourseListString("Courses that are waiting to be canceled by your  :", cancelWaitingCourses));
                break;
                case 5: controller.printList(currentTranscript.getStudentTranscriptStringList());
                break; 
                case 6: controller.printList(stringToList(currentAdvisor.toString()));
                break;
                case 7:
                break; 
                default:
                break;
            }
            
            
            }
    
    }


    private String[] stringToList(String giveString) {
        String[] stringList = new String[1];
        stringList[0] = giveString;
        return stringList;
    }

    private String[] getCourseReturnListString(String titleString, List<Course> coursesList) {
        int size = coursesList.size();
        String[] courseListString = new String[size + 1];
        courseListString[0] = titleString; 
        courseListString[1] = "1-)Return back";
        for(int i = 2; i < coursesList.size(); i++){
            courseListString[i] = i + "-)" + coursesList.get(i).toStringFormatted(1);
        }
        return courseListString;
    }

    private String[] getCourseListString(String titleString, List<Course> coursesList) {
        int size = coursesList.size();
        String[] courseListString = new String[size + 1];
        courseListString[0] = titleString;
        for(int i = 1; i < coursesList.size(); i++){
            courseListString[i] = i + "-)" + coursesList.get(i).toStringFormatted(1);
        }
        return courseListString;
    }

    public void removeElementFromCurrentAvailableCourses(Course course) {
        currentAvailableCourses.remove(course);
    }
    public void removeElementFromRegistrationWaitingCourses(Course course) {
        registrationWaitingCourses.remove(course);
    }
    public void removeElementFromRegistrationCompleteCourses(Course course) {
        registrationCompleteCourses.remove(course);
    }
    public void removeElementFromCancelWaitingCourses(Course course) {
        cancelWaitingCourses.remove(course);
    }
    public void addElementToCurrentAvailableCourses(Course course) {
        currentAvailableCourses.add(course);
    }
    public void addElementToRegistrationWaitingCourses(Course course) {
        registrationWaitingCourses.add(course);
    }
    public void addElementToRegistrationCompleteCourses(Course course) {
        registrationCompleteCourses.add(course);
    }
    public void addElementToCancelWaitingCourses(Course course) {
        cancelWaitingCourses.add(course);
    }

    public List<Course> getCancelWaitingCourses() {
        return cancelWaitingCourses;
    }

    public List<Course> getRegistrationWaitingCourses() {
        return registrationWaitingCourses;
    }
}
