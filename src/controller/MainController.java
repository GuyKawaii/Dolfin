package controller;

import userInterface.UI;
import enums.AgeGroup;
import filehandling.FileHandlingTeam;
import member.*;
import filehandling.FileHandlingMemberList;
import team.TeamRecords;

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
  private TeamRecords teamRecordsJunior;
  private TeamRecords teamRecordsSenior;
  
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
    teamRecordsJunior = new TeamRecords(AgeGroup.JUNIOR);
    teamRecordsSenior = new TeamRecords(AgeGroup.SENIOR);
  }
  
  // getters
  public MemberList getMemberList() {
    return memberList;
  }
  
  public TeamRecords getTeamJunior() {
    return teamRecordsJunior;
  }
  
  public TeamRecords getTeamSenior() {
    return teamRecordsSenior;
  }
  
  public FileHandlingTeam getFileHandlingTeam() {
    return fileHandlingTeam;
  }
  
  // main loop
  public void go() {
    initializeDatabase();
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
        case "" -> mainMenu = false;
        default -> UI.invalidInputMessage();
      }
      
    } while (mainMenu);
    
  }
  
  public void initializeDatabase() {
    // memberList
    memberList.setMotionists(fileHandlingMemberList.loadMotionists());
    memberList.setCompetitors(fileHandlingMemberList.loadCompetitors());
    
    // recordsTraining
    fileHandlingTeam.loadTrainingRecords(teamRecordsJunior);
    fileHandlingTeam.loadTrainingRecords(teamRecordsSenior);
    
    // recordsCompetitive
    fileHandlingTeam.loadCompetitiveRecords(teamRecordsJunior);
    fileHandlingTeam.loadCompetitiveRecords(teamRecordsSenior);
    
    // team trainers
    fileHandlingTeam.loadTrainerFile(teamRecordsJunior);
    fileHandlingTeam.loadTrainerFile(teamRecordsSenior);
    
    // idCounter
    fileHandlingMemberList.loadIdCounter(memberList);
  }
  
  public FileHandlingMemberList getFileHandlingMemberList() {
    return fileHandlingMemberList;
  }
}
