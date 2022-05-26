package Controller;

import UserInterface.UI;
import enums.AgeGroup;
import filehandling.FileHandlingTeam;
import member.*;
import filehandling.FileHandlingMemberList;
import other.Team;

import java.time.LocalDate;

import static enums.Discipline.*;
import static java.time.LocalDate.now;

public class MainController {
  // other controllers
  private FormandController formandController;
  private CashierController cashierController;
  private TrainerController trainerController;
  // file
  private FileHandlingMemberList fileHandlingMemberList;
  private FileHandlingTeam fileHandlingTeam;
  // program state
  private MemberList memberList;
  private Team teamJunior;
  private Team teamSenior;
  
  // Constructor
  public MainController() {
    // other controllers
    formandController = new FormandController(this);
    cashierController = new CashierController(this);
    trainerController = new TrainerController(this);
    
    // file
    fileHandlingMemberList = new FileHandlingMemberList();
    fileHandlingTeam = new FileHandlingTeam();
    
    // program state
    memberList = new MemberList();
    teamJunior = new Team(AgeGroup.JUNIOR);
    teamSenior = new Team(AgeGroup.SENIOR);
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
  
  public FileHandlingTeam getFileHandlingTeam() {
    return fileHandlingTeam;
  }
  
  // main loop
  public void go() {
    initializeDatabase();
//    initTeams();
    mainMenu(); //starts the program
  }
  
  public void mainMenu() {
    boolean mainMenu = true;
    do {
      UI.printMainMenu();
      String userInput = UI.capitalizeStringInput();
      switch (userInput) {
        case "1" -> cashierController.cashierMenu();
        case "2" -> trainerController.TrainerMenu();
        case "3" -> formandController.formandMenu();
        case "" -> {
          
          mainMenu = false;
        }
        default -> UI.invalidInputMessage();
      }
      
    } while (mainMenu);
    
  }
  
  public void initializeDatabase() {
    // memberList
    memberList.setMotionists(fileHandlingMemberList.loadMotionists());
    memberList.setCompetitors(fileHandlingMemberList.loadCompetitors());
    
    // recordsTraining
    fileHandlingTeam.loadTrainingRecords(teamJunior);
    fileHandlingTeam.loadTrainingRecords(teamSenior);
    
    // recordsCompetitive
    fileHandlingTeam.loadCompetitiveRecords(teamJunior);
    fileHandlingTeam.loadCompetitiveRecords(teamSenior);
    
    // team trainers
    fileHandlingTeam.loadTrainerFile(teamJunior);
    fileHandlingTeam.loadTrainerFile(teamSenior);
    
    // idCounter
    fileHandlingMemberList.loadIdCounter(memberList);
  }
  
  public FileHandlingMemberList getFileHandlingMemberList() {
    return fileHandlingMemberList;
  }
  
  public void initTeams() {
    // todo remove method later
    // Team setup
    teamJunior.createTrainingRecord(BACK_CRAWL, 7, "7", 7, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, 6, "6", 6, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, 5, "5", 5, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, 4, "4", 4, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, 3, "3", 3, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, 2, "2", 2, LocalDate.now());
    teamJunior.createTrainingRecord(BACK_CRAWL, 1, "1", 1, LocalDate.now());
    
    teamJunior.createTrainingRecord(BUTTERFLY, 3, "3", 3, LocalDate.now());
    teamJunior.createTrainingRecord(BUTTERFLY, 2, "2", 2, LocalDate.now());
    teamJunior.createTrainingRecord(BUTTERFLY, 1, "1", 1, LocalDate.now());
  }
  
  // program entry
  public static void main(String[] args) {
//    new FormandController().mainMenu();
    new MainController().go();
  }
}
