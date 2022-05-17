public class Controller {
  // other controllers
  private FormandController formandController;
  
  // program state
  private MemberList memberList;
  
  public Controller() {
    formandController = new FormandController(this);
    memberList = new MemberList();
  }
  
  public void go() {
    
    // select menu todo add main menu here
    do {
      System.out.println("=== main menu ===");
      System.out.println("MISSING MENU BRO");
      
      formandController.mainMenu();
    } while (true);
    
  }
  
  public MemberList getMemberList() {
    return memberList;
  }
  
  // program entry
  public static void main(String[] args) {
//    new FormandController().mainMenu();
    new Controller().go();
  }
}
