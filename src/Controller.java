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
  
  public Controller() {
    formandController = new FormandController(this);
    cashierController = new CashierController(this);
    memberList = new MemberList();
    fileHandlingMemberList = new FileHandlingMemberList();
  }
  
  public void go() {

    initDatabase();
  
    // fileHandlingMemberList.appendCompetitor(memberList.getCompetitors()); // append one competitor to the end of the file csv
    
    fileHandlingMemberList.saveMotionists(memberList.getMotionists());        // save whole database as csv
    System.out.println("Original database motionist arraylist");
    UI.printMotionists(memberList.getMotionists());
    System.out.println("Loaded database motionist.csv");
    UI.printMotionists(fileHandlingMemberList.loadMotionists());
  

    fileHandlingMemberList.saveCompetitors(memberList.getCompetitors());
    System.out.println("Original database competitive from arrayList");
    UI.printCompetitors(memberList.getCompetitors());
    System.out.println("Loaded database competitive.csv");
    UI.printCompetitors(fileHandlingMemberList.loadCompetitors());
  
    
    // extra
    System.out.println(fileHandlingMemberList.stringDisciplines(memberList.getCompetitive("Mike").getDisciplines()));

    mainMenu(); //starts the program
  }

  public void mainMenu() {
    boolean mainMenu = true;
    do {
      UI.printMainMenu();
      String userInput = UI.receiveStringInput();
      switch (userInput) {
        case "1" -> cashierController.cashierMenu();
        case "2" -> System.out.println("Does not work right now"); //trainerController.mainMenu();
        case "3" -> formandController.formandMenu();
        case ""  -> mainMenu = false;
        default -> UI.invalidInputMessage();
      }

    } while (mainMenu);

  }
  
  public MemberList getMemberList() {
    return memberList;
  }
  
  public void initDatabase() {
    // todo remove method later
   // memberList.addMotionist(new member.Motionist("Daniel", now().minusYears(25), ACTIVE));
   // memberList.addMotionist(new member.Motionist("Kasper", now().minusYears(22), ACTIVE));
    memberList.addMotionist(new member.Motionist("William", now().minusYears(17), ACTIVE));
    memberList.addMotionist(new member.Motionist("Thomas", now().minusYears(19), PASSIVE));
    
    Motionist harRestance1 = new member.Motionist("Daniel", now().minusYears(25), ACTIVE);
    harRestance1.setRestance(54);
    Motionist harRestance2 = new member.Motionist("Kasper", now().minusYears(22), ACTIVE);
    harRestance2.setRestance(500);
    memberList.addMotionist(harRestance1);
    memberList.addMotionist(harRestance2);
    
    // ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    ArrayList<Discipline> disciplines = new ArrayList<>(Arrays.asList(Discipline.BACK_CRAWL, Discipline.CRAWL));
    Competitive mike = new member.Competitive("Mike", now().minusYears(21), ACTIVE,  disciplines);
    
    memberList.addCompetitive(mike);
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
