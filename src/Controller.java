import member.*;

import static enums.MembershipStatus.*;
import static java.time.LocalDate.now;

public class Controller {
  // other controllers
  private FormandController formandController;
  private CashierController cashierController;
  
  // program state
  private MemberList memberList;
  
  public Controller() {
    formandController = new FormandController(this);
    cashierController = new CashierController(this);
    memberList = new MemberList();
  }
  
  public void go() {
    
    initDatabase();
    
    // select menu todo add main menu here
    do {
      System.out.println("=== main menu ===");
      System.out.println("MISSING MENU BRO");
      
//      formandController.mainMenu();
      cashierController.mainMenu();
    } while (true);
    
  }
  
  public MemberList getMemberList() {
    return memberList;
  }
  
  public void initDatabase() {
    // todo remove method later
    memberList.addMotionist(new member.Motionist("Daniel", now().minusYears(25), ACTIVE));
    memberList.addMotionist(new member.Motionist("Kasper", now().minusYears(22), ACTIVE));
    memberList.addMotionist(new member.Motionist("William", now().minusYears(17), ACTIVE));
    memberList.addMotionist(new member.Motionist("Thomas", now().minusYears(19), PASSIVE));
    
    memberList.addCompetitive(new member.Competitive("Mike", now().minusYears(21), ACTIVE));
    memberList.addCompetitive(new member.Competitive("Lotte", now().minusYears(24), ACTIVE));
    memberList.addCompetitive(new member.Competitive("Veronica", now().minusYears(26), ACTIVE));
    memberList.addCompetitive(new member.Competitive("Grethe", now().minusYears(23), PASSIVE));
    
    // restance members
    Competitive restance1 = new member.Competitive("Mark", now().minusYears(24), PASSIVE);
    restance1.addRestanceOnePeriod();
    
    Competitive restance2 = new member.Competitive("Mathias", now().minusYears(17), ACTIVE);
    restance2.addRestanceOnePeriod();
    
    Competitive restance3 = new member.Competitive("Finn", now().minusYears(24), ACTIVE);
    restance3.addRestanceOnePeriod();
  
    Competitive restance4 = new member.Competitive("Dorthe", now().minusYears(65), ACTIVE);
    restance4.addRestanceOnePeriod();
  
    Competitive restance5 = new member.Competitive("Dorthe", now().minusYears(65), PASSIVE);
    restance5.addRestanceOnePeriod();
  
    memberList.addCompetitive(restance1);
    memberList.addCompetitive(restance2);
    memberList.addCompetitive(restance3);
    memberList.addCompetitive(restance4);
    memberList.addCompetitive(restance5);
  }
  
  // program entry
  public static void main(String[] args) {
//    new FormandController().mainMenu();
    new Controller().go();
  }
}
