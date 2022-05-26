package Controller;

import UserInterface.TrainerUI;
import UserInterface.UI;
import enums.AgeGroup;
import enums.Discipline;
import member.Competitive;
import other.Team;
import record.RecordCompetition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static UserInterface.Color.*;
import static enums.AgeGroup.*;
import static enums.Discipline.*;
import static enums.MembershipStatus.*;

public class TrainerController {
  MainController mainController;
  Scanner scanner;
  
  public TrainerController(MainController mainController) {
    this.mainController = mainController;
  }
  
  public void TrainerMenu() { // todo missing visual for competition
    boolean trainerMenu = true;
    do {
      System.out.print("""
          
          TRAINER:
          - add record                               -> 1
          - See Junior or Senior top 5               -> 2 [J/S]
          - see Junior or Senior team                -> 3 [J/S]
          - see junior or Senior competition records -> 4 [J/S]
          - Return to main menu                      -> Enter
          SELECT:\040""");
      String[] userInputs = UI.twoStingsArguments();;
      switch (userInputs[0]) {
        case "1" -> addRecord();
        case "2" -> seeTopFive(userInputs[1]);
        case "3" -> printTeam(userInputs[1], mainController.getMemberList().getCompetitors()); // todo can give error if there is no trainer loaded
        case "4" -> selectConvention(userInputs[1]);
        case "" -> trainerMenu = false;
        default -> UI.invalidInputMessage();
      }
    } while (trainerMenu);
  }
  
  public void seeTopFive(String userInput) {
    AgeGroup ageGroup;
    switch (userInput) {
      case "j" -> ageGroup = JUNIOR;
      case "s" -> ageGroup = SENIOR;
      default -> {
        UI.invalidInputMessage();
        return;
      }
    }
    
    switch (ageGroup) {
      case JUNIOR -> {
        UI.printTopFiveDiscipline(CRAWL, mainController.getTeamJunior());
        UI.printTopFiveDiscipline(BACK_CRAWL, mainController.getTeamJunior());
        UI.printTopFiveDiscipline(BREAST_STROKE, mainController.getTeamJunior());
        UI.printTopFiveDiscipline(BUTTERFLY, mainController.getTeamJunior());
      }
      case SENIOR -> {
        UI.printTopFiveDiscipline(CRAWL, mainController.getTeamSenior());
        UI.printTopFiveDiscipline(BACK_CRAWL, mainController.getTeamSenior());
        UI.printTopFiveDiscipline(BREAST_STROKE, mainController.getTeamSenior());
        UI.printTopFiveDiscipline(BUTTERFLY, mainController.getTeamSenior());
      }
    }
  }
  
  
  public void selectConvention(String userInput) {
    Team team;
    AgeGroup ageGroup;
    Discipline discipline;
    switch (userInput) {
      case "j" -> {
        ageGroup = JUNIOR;
        team = mainController.getTeamJunior();
      }
      case "s" -> {
        ageGroup = SENIOR;
        team = mainController.getTeamSenior();
      }
      default -> {
        UI.invalidInputMessage();
        return;
      }
    }
    
    // select discipline
    discipline = UI.selectDiscipline();
    if (discipline == null) return;
    
    // apply discipline
    switch (discipline) {
      case CRAWL -> seeCompetitiveRecords(ageGroup, discipline, team.getCrawlCompetition());
      case BACK_CRAWL -> seeCompetitiveRecords(ageGroup, discipline, team.getBackCrawlCompetition());
      case BREAST_STROKE -> seeCompetitiveRecords(ageGroup, discipline, team.getBreastStrokeCompetition());
      case BUTTERFLY -> seeCompetitiveRecords(ageGroup, discipline, team.getButterflyCompetition());
    }
  }
  
  public void seeCompetitiveRecords(AgeGroup ageGroup, Discipline discipline, ArrayList<RecordCompetition> records) { // todo maybe change CompetitiveRecord to ConventionRecord?
    ArrayList<RecordCompetition> conventionRecords = new ArrayList<>();
    String convention;
    
    // select convention
    System.out.print("Choose convention by name: ");
    convention = scanner.nextLine().toUpperCase();
    if (convention.isEmpty()) return;
    
    // select convention records
    for (RecordCompetition record : records) {
      if (record.getConvention().equalsIgnoreCase(convention))
        conventionRecords.add(record);
    }
    
    // no contestants
    if (conventionRecords.size() == 0) {
      System.out.printf("""
          
          %sCONVENTION: %s - DISCIPLINE: %s%s [NA]
          """, TEXT_BLUE, convention, discipline, TEXT_RESET);
      return;
    }
    
    // sort
    Collections.sort(conventionRecords);
    
    // header
    System.out.printf("""
        
        %sCONVENTION: %s - DISCIPLINE: %s%s
        %-15s PLACEMENT  TIME  DATE
        """, TEXT_BLUE, convention, discipline, TEXT_RESET, "NAME");
    
    // print convention records
    for (RecordCompetition record : conventionRecords) {
      System.out.printf("%-15s %9s  %-4s  %s\n", record.getName(), record.getPlacement(), record.getTimeInSeconds(), record.getDate());
    }
  }
  
  public void addRecord() {
    String userInput;
    Competitive competitive;
    
    // parameters
    int ID;
    String name;
    Discipline discipline;
    Integer timeInSeconds;
    LocalDate date;
    // competition specific
    boolean isCompetition;
    Integer placement;
    String convention;
    
    addingRecords:
    while (true) {
      // loop reset
      isCompetition = false;
      
      UI.printCompetitors(mainController.getMemberList().getCompetitors()); // todo change method
      
      // competitive, name, ageGroup
      System.out.print("""
          
          ADDING RECORDS:
          - ID for person       -> ID of person
          - Return to main menu -> Enter
          SELECT:\040""");
      competitive = UI.findActiveCompetitive(mainController.getMemberList());
      if (competitive == null) break addingRecords;
      else {
        ID = competitive.getID();
        name = competitive.getName();
      }
      
      System.out.println("\nMEMBER");
      UI.printMemberHeader();
      UI.printMember(competitive);
      
      // discipline
      discipline = UI.selectCompetitorDiscipline(competitive);
      if (discipline == null) break addingRecords;
      
      // timeInSeconds
      System.out.print("""
          
          TIME:
          - time (sec)          -> number
          - Return to main menu -> Enter
          SELECT:\040""");
      timeInSeconds = UI.inputPositiveNumber();
      if (timeInSeconds == null) break addingRecords;
      
      
      // date
      System.out.print("\nINPUT date (yyyy-mm-dd): ");
      date = UI.inputDate();
      if (date == null) break addingRecords;
      
      
      // record type
      System.out.print("""
          
          TYPE:
          - training            -> 1
          - competition         -> 2
          - Return to main menu -> Enter
          SELECT:\040""");
      userInput = UI.capitalizeStringInput();
      switch (userInput) {
        default -> UI.invalidInputMessage();
        case "1" -> isCompetition = false;
        case "2" -> isCompetition = true;
        case "" -> {
          break addingRecords;
        }
      }
      
      // ### Competitive or training record ###
      if (!isCompetition) {
        switch (competitive.getAgeGroup()) {
          case JUNIOR -> {
            mainController.getTeamJunior().createTrainingRecord(discipline, ID, name, timeInSeconds, date);
            mainController.getFileHandlingTeam().saveRecordTraining(mainController.getTeamJunior(), discipline);
          }
          case SENIOR -> {
            mainController.getTeamSenior().createTrainingRecord(discipline, ID, name, timeInSeconds, date);
            mainController.getFileHandlingTeam().saveRecordTraining(mainController.getTeamSenior(), discipline);
          }
        }
        
        // competitive specific attributes
      } else {
        
        // placement
        System.out.print("""
            
            PLACEMENT:
            - placement           -> number
            - Return to main menu -> Enter
            SELECT:\040""");
        placement = UI.inputPositiveNumber();
        if (placement == null) break addingRecords;
        
        // convention
        System.out.print("""
            
            TYPE:
            - convention          -> convention-name
            - Return to main menu -> Enter
            SELECT:\040""");
        convention = UI.uppercaseStringInput();
        if (convention.isEmpty()) break addingRecords;
        
        // competitive record
        switch (competitive.getAgeGroup()) {
          case JUNIOR -> {
            mainController.getTeamJunior().createCompetitiveRecord(discipline, ID, name, timeInSeconds, date, placement, convention);
            mainController.getFileHandlingTeam().saveRecordCompetition(mainController.getTeamJunior(), discipline);
          }
          case SENIOR -> {
            mainController.getTeamSenior().createCompetitiveRecord(discipline, ID, name, timeInSeconds, date, placement, convention);
            mainController.getFileHandlingTeam().saveRecordCompetition(mainController.getTeamSenior(), discipline);
          }
        }
      }
      
      TrainerUI.printAddRecord();
    }
    
  }
  
  public void printTeam(String userInput, ArrayList<Competitive> competitors) {
    Team team;
    switch (userInput) {
      case "j" -> team = mainController.getTeamJunior();
      case "s" -> team = mainController.getTeamSenior();
      default -> {
        UI.invalidInputMessage();
        return;
      }
    }
    
    // empty team
    if (competitors == null) { // todo WRONG takes all competitive members from the whole database will always be false
      System.out.printf("\n%sTEAM: %s | TRAINER: %s - ACTIVE MEMBERS%s [NA]\n", TEXT_GREEN, team.getAgeGroup(), team.getTrainer().getName(), TEXT_RESET);
      return;
    }
    
    // header
    System.out.printf("""
            
            %sTEAM: %s | TRAINER: %s - ACTIVE MEMBERS%s
            %-15s %s   | %5s | %10s | %13s | %9s |
            """, TEXT_GREEN,
        team.getAgeGroup(), team.getTrainer().getName(), TEXT_RESET,
        "NAME", "AGE", CRAWL, BACK_CRAWL, BREAST_STROKE, BUTTERFLY);
    
    // competitors
    for (Competitive competitive : competitors) {
      if (competitive.getAgeGroup() == team.getAgeGroup() && competitive.getMembershipStatus() == ACTIVE)
        System.out.printf("%-15s %3s   | %5s | %10s | %13s | %9s |\n", competitive.getName(), competitive.getAge(),
            competitive.hasDiscipline(CRAWL),
            competitive.hasDiscipline(BACK_CRAWL),
            competitive.hasDiscipline(BREAST_STROKE),
            competitive.hasDiscipline(BUTTERFLY));
    }
  }
  
  
}