package Controller;

import UserInterface.UI;
import enums.AgeGroup;
import enums.Discipline;
import member.*;
import filehandling.FileHandlingMemberList;
import other.Team;
import other.Trainer;
import record.RecordCompetition;
import record.RecordTraining;

import java.time.LocalDate;

import static enums.Discipline.BACK_CRAWL;
import static enums.Discipline.BUTTERFLY;
import static enums.MembershipStatus.*;
import static java.time.LocalDate.now;

public class Controller {
  // other controllers
  private FormandController formandController;
  private CashierController cashierController;
  private TrainerController trainerController;
  
  // file
  private FileHandlingMemberList fileHandlingMemberList;
  
  // program state
  private MemberList memberList;
  private Team teamJunior;
  private Team teamSenior;

  
  // Constructor
  public Controller() {
    // other controllers
    formandController = new FormandController(this);
    cashierController = new CashierController(this);
    trainerController = new TrainerController(this);

    // file
    fileHandlingMemberList = new FileHandlingMemberList();
    
    // program state
    memberList = new MemberList();
    teamJunior = new Team(AgeGroup.JUNIOR, new Trainer("JUNIOR"));
    teamSenior = new Team(AgeGroup.SENIOR, new Trainer("SENIOR"));

    RecordCompetition recordCompetition = new RecordCompetition("name", AgeGroup.JUNIOR, 6, LocalDate.now(), 8,"hallen");
    RecordTraining recordTraining = new RecordTraining("name", AgeGroup.JUNIOR, 6, LocalDate.now());
    System.out.println(recordCompetition);
  }
  
  // getters
  public MemberList getMemberList() {
    return memberList;
  }

  public Team getTeamJunior() {
    return teamJunior;
  }

  public Team getTeamSenior() {
    return teamSenior;
  }

  // main loop
  public void go() {


    initDatabase();
    initTeams();
//    Competitive competitive1 = new member.Competitive("Mike", now().minusYears(21), ACTIVE);
//    Competitive competitive2 = new member.Competitive("Mike", now().minusYears(21), ACTIVE);
//
//    fileHandlingMemberList.saveMotionists(memberList.getMotionists());        // save whole database as csv
//    System.out.println("Original database motionist arraylist");
//    UI.printMotionists(memberList.getMotionists());
//    System.out.println("Loaded database motionist.csv");
//    UI.printMotionists(fileHandlingMemberList.loadMotionists());
//
//
//    fileHandlingMemberList.saveCompetitors(memberList.getCompetitors());
//    System.out.println("Original database competitive from arrayList");
//    UI.printCompetitors(memberList.getCompetitors());
//    System.out.println("Loaded database competitive.csv");
//    UI.printCompetitors(fileHandlingMemberList.loadCompetitors());
    
    // extra
    //System.out.println(fileHandlingMemberList.stringDisciplines(memberList.getCompetitive("Mike").getDisciplines())); //virker ikke

    trainerController.mainMenu();
//    mainMenu(); //starts the program
  }

  public void mainMenu() {
    boolean mainMenu = true;
    do {
      UI.printMainMenu();
      String userInput = UI.receiveStringInput();
      switch (userInput) {
        case "1" -> cashierController.cashierMenu();
        case "2" -> trainerController.mainMenu();
        case "3" -> formandController.formandMenu();
        case "" -> {

          mainMenu = false;
        }
        default -> UI.invalidInputMessage();
      }

    } while (mainMenu);

  }

  public void initDatabase() {
    // todo remove method later
    // Team setup
    teamJunior.createTrainingRecord(BACK_CRAWL, "7", 7, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, "6", 6, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, "5", 5, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, "4", 4, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, "3", 3, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, "2", 2, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, "1", 1, LocalDate.now());
  
    teamJunior.createTrainingRecord(BUTTERFLY, "3", 3, LocalDate.now());
    teamJunior.createTrainingRecord(BUTTERFLY, "2", 2, LocalDate.now());
    teamJunior.createTrainingRecord(BUTTERFLY, "1", 1, LocalDate.now());
  }

  public FileHandlingMemberList getFileHandlingMemberList() {
    return fileHandlingMemberList;
  }

  public void initTeams() {
    // todo remove method later


    UI.printCompetitors(memberList.getCompetitors());
  }

  // program entry
  public static void main(String[] args) {
//    new FormandController().mainMenu();
    new Controller().go();
  }
}
