import enums.Discipline;
import member.*;
import filehandling.*;

import java.util.ArrayList;
import java.util.Arrays;

import static enums.MembershipStatus.*;
import static java.time.LocalDate.now;

public class Controller {
  // other controllers
  private FormandController formandController;
  private CashierController cashierController;
  private TrainerController trainerController;
  
  private FileHandlingMemberList fileHandlingMemberList;
  
  // program state
  private MemberList memberList;
  
  // Constructor
  public Controller() {
    formandController = new FormandController(this);
    cashierController = new CashierController(this);
    trainerController = new TrainerController(this);
    memberList = new MemberList();
    fileHandlingMemberList = new FileHandlingMemberList();
  }
  
  // getters
  public MemberList getMemberList() {
    return memberList;
  }
  
  // main loop
  public void go() {
    
    initDatabase();
    Competitive competitive1 = new member.Competitive("Mike", now().minusYears(21), ACTIVE);
    Competitive competitive2 = new member.Competitive("Mike", now().minusYears(21), ACTIVE);
    
    System.out.println("equals " + competitive1.equals(competitive2));
    
    
    // extra
    System.out.println(fileHandlingMemberList.stringDisciplines(memberList.getCompetitive("Mike").getDisciplines()));
    
    // select menu todo add main menu here
    do {
      System.out.println("=== main menu ===");
      System.out.println("MISSING MENU BRO");

//      formandController.mainMenu();
      cashierController.mainMenu();
//      trainerController.mainMenu();
    } while (true);
    
  }
  
  public void initDatabase() {
    // todo remove method later
    // motionist setup
    Motionist motionist1 = new member.Motionist("William", now().minusYears(17), ACTIVE);
    
    // competitive setup
    Competitive competitive1 = new member.Competitive("Lotte", now().minusYears(24), ACTIVE);
    Competitive competitive2 = new member.Competitive("Lotte", now().minusYears(24), ACTIVE);
    
    memberList.addCompetitive(competitive1);
    memberList.addCompetitive(competitive2);
  
    UI.printCompetitors(memberList.getCompetitors());
  }
  
  // program entry
  public static void main(String[] args) {
//    new FormandController().mainMenu();
    new Controller().go();
  }
}
