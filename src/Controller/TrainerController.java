package Controller;

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
  Controller controller;
  Scanner scanner;
  
  public TrainerController(Controller controller) {
    this.controller = controller;
    scanner = new Scanner(System.in);
  }
  
  public void mainMenu() { // todo missing visual for competition
    boolean trainerMenu = true;
    do {
      System.out.print("""
          
          TRAINER:
          - add record          -> 1
          - See top 5 junior    -> 2
          - See top 5 senior    -> 3
          - see junior team     -> 4
          - see senior team     -> 5
          - see junior competition records -> 6
          - see senior competition records -> 7
          - Return to main menu -> Enter
          SELECT:\040""");
      String userInput = UI.capitalizeStringInput();
      switch (userInput) {
        case "1" -> addRecord();
        case "2" -> seeTopFive(JUNIOR);
        case "3" -> seeTopFive(SENIOR);
        case "4" -> printTeam(JUNIOR, controller.getMemberList().getCompetitors());
        case "5" -> printTeam(SENIOR, controller.getMemberList().getCompetitors());
        case "6" -> selectConvention(JUNIOR, controller.getTeamJunior());
        case "7" -> selectConvention(JUNIOR, controller.getTeamSenior());
        case "" -> trainerMenu = false;
        default -> UI.invalidInputMessage();
      }
    } while (trainerMenu);
  }
  
  public void seeTopFive(AgeGroup ageGroup) {
    
    
    switch (ageGroup) {
      case JUNIOR -> {
        UI.printTopFiveDiscipline(CRAWL, controller.getTeamJunior());
        UI.printTopFiveDiscipline(BACK_CRAWL, controller.getTeamJunior());
        UI.printTopFiveDiscipline(BREAST_STROKE, controller.getTeamJunior());
        UI.printTopFiveDiscipline(BUTTERFLY, controller.getTeamJunior());
      }
      case SENIOR -> {
        UI.printTopFiveDiscipline(CRAWL, controller.getTeamSenior());
        UI.printTopFiveDiscipline(BACK_CRAWL, controller.getTeamSenior());
        UI.printTopFiveDiscipline(BREAST_STROKE, controller.getTeamSenior());
        UI.printTopFiveDiscipline(BUTTERFLY, controller.getTeamSenior());
      }
    }
  }
  
  
  public void selectConvention(AgeGroup ageGroup, Team team) {
    Discipline discipline;
    
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
      
      // competitive, name, ageGroup
      System.out.print("""
          
          ADDING RECORDS:
          - name of person      -> name
          - Return to main menu -> Enter
          SELECT:\040""");
      competitive = UI.findActiveCompetitive(controller.getMemberList());
      if (competitive == null) break addingRecords;
      else name = competitive.getName();
      
      System.out.println("\nMEMBER");
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
            controller.getTeamJunior().createTrainingRecord(discipline, name, timeInSeconds, date);
            controller.getFileHandlingTeam().saveRecordTraining(controller.getTeamJunior(), discipline);
          }
          case SENIOR -> {
            controller.getTeamSenior().createTrainingRecord(discipline, name, timeInSeconds, date);
            controller.getFileHandlingTeam().saveRecordTraining(controller.getTeamSenior(), discipline);
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
            controller.getTeamJunior().createCompetitiveRecord(discipline, name, timeInSeconds, date, placement, convention);
            controller.getFileHandlingTeam().saveRecordCompetition(controller.getTeamJunior(), discipline);
          }
          case SENIOR -> {
            controller.getTeamSenior().createCompetitiveRecord(discipline, name, timeInSeconds, date, placement, convention);
            controller.getFileHandlingTeam().saveRecordCompetition(controller.getTeamSenior(), discipline);
          }
        }
      }
      
    }
    
  }
  
  public void printTeam(AgeGroup ageGroup, ArrayList<Competitive> competitors) {
    // empty team
    if (competitors.size() == 0) {
      System.out.printf("\n%sTEAM: %s - ACTIVE MEMBERS%s [NA]\n", TEXT_GREEN, ageGroup, TEXT_RESET);
      return;
    }
    
    // header
    System.out.printf("""
        
        %sTEAM: %s - ACTIVE MEMBERS%s
        %-15s %s   | %5s | %10s | %13s | %9s |
        """, TEXT_GREEN, ageGroup, TEXT_RESET, "NAME", "AGE", CRAWL, BACK_CRAWL, BREAST_STROKE, BUTTERFLY);
    
    // competitors
    for (Competitive competitive : competitors) {
      if (competitive.getAgeGroup() == ageGroup && competitive.getMembershipStatus() == ACTIVE)
        System.out.printf("%-15s %3s   | %5s | %10s | %13s | %9s |\n", competitive.getName(), competitive.getAge(),
            competitive.hasDiscipline(CRAWL),
            competitive.hasDiscipline(BACK_CRAWL),
            competitive.hasDiscipline(BREAST_STROKE),
            competitive.hasDiscipline(BUTTERFLY));
    }
  }
  
  
}