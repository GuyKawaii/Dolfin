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
