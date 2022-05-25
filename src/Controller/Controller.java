package Controller;

import UserInterface.UI;
import enums.AgeGroup;
import enums.Discipline;
import filehandling.FileHandlingTeam;
import member.*;
import filehandling.FileHandlingMemberList;
import other.Team;
import other.Trainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static enums.Discipline.*;
import static enums.MembershipStatus.*;
import static java.time.LocalDate.now;

public class Controller {
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
  public Controller() {
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
    initTeams();
    // todo remove method later
    ArrayList<Discipline> disciplines = new ArrayList<>(Arrays.asList(CRAWL, BACK_CRAWL, BREAST_STROKE));
    memberList.addCompetitive(new Competitive(memberList.createID(), "1a", LocalDate.now(), PASSIVE, disciplines));
    memberList.addCompetitive(new Competitive(memberList.createID(), "2a", LocalDate.now(), PASSIVE, disciplines));
    memberList.addCompetitive(new Competitive(memberList.createID(), "3a", LocalDate.now(), ACTIVE, disciplines));
    memberList.addCompetitive(new Competitive(memberList.createID(), "4a", LocalDate.now(), ACTIVE, disciplines));
    memberList.addCompetitive(new Competitive(memberList.createID(), "5a", LocalDate.now().minusYears(30), PASSIVE, disciplines));
    memberList.addCompetitive(new Competitive(memberList.createID(), "6a", LocalDate.now().minusYears(30), PASSIVE, disciplines));
    memberList.addCompetitive(new Competitive(memberList.createID(), "7a", LocalDate.now().minusYears(30), ACTIVE, disciplines));
    memberList.addCompetitive(new Competitive(memberList.createID(), "8a", LocalDate.now().minusYears(30), ACTIVE, disciplines));
    fileHandlingMemberList.saveIdCounter(memberList);
    
    
    
    
    System.out.println(memberList.getMember("1"));
    System.out.println(memberList.getMember("2a"));
    System.out.println(memberList.getMember("3a"));
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

//    trainerController.mainMenu();
    mainMenu(); //starts the program
    
  }
  
  public void mainMenu() {
    boolean mainMenu = true;
    do {
      UI.printMainMenu();
      String userInput = UI.capitalizeStringInput();
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
  
  // program entry
  public static void main(String[] args) {
//    new FormandController().mainMenu();
    new Controller().go();
  }
}
