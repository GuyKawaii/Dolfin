package Controller;

import UserInterface.UI;
import enums.AgeGroup;
import enums.Discipline;
import enums.MembershipStatus;
import member.Competitive;
import filehandling.FileHandlingTeam;
import other.Team;
import other.Trainer;
import record.Record;
import record.RecordTraining;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
          
          FORMAND:
          - add record          -> 1
          - See top 5 junior    -> 2
          - See top 5 senior    -> 3
         
          - see junior team     -> 4
          - see senior team     -> 5
          - Return to main menu -> Enter
          SELECT:\040""");
      String userInput = UI.capitalizeStringInput();
      switch (userInput) {
        case "1" -> addRecord();
        case "2" -> seeTopFive(JUNIOR);
        case "3" -> seeTopFive(SENIOR);
        case "4" -> printTeam(JUNIOR, controller.getMemberList().getCompetitors());
        case "5" -> printTeam(SENIOR, controller.getMemberList().getCompetitors());
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
  
  public void addRecord() {
    String userInput;
    Competitive competitive;
    
    // parameters
    String name;
    
    Discipline discipline;
    int timeInSeconds;
    LocalDate date;
    // competition specific
    boolean isCompetition;
    int placement;
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
      // todo Check if member is passive.
      if (competitive == null) break addingRecords;
      else {
        name = competitive.getName();
      }
      
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
      if (timeInSeconds == 0) break addingRecords;
      
      
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
          case JUNIOR -> controller.getTeamJunior().createTrainingRecord(discipline, name, timeInSeconds, date);
          case SENIOR -> controller.getTeamSenior().createTrainingRecord(discipline, name, timeInSeconds, date);
          // todo update csv files
          
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
        if (placement == 0) break addingRecords; // todo maybe change fail condition from 0 to something else
        
        // convention
        System.out.print("""
            
            TYPE:
            - convention          -> convention-name
            - Return to main menu -> Enter
            SELECT:\040""");
        convention = UI.capitalizeStringInput();
        if (convention.isEmpty()) break addingRecords;
        
        // competitive record
        switch (competitive.getAgeGroup()) {
          case JUNIOR ->
              controller.getTeamJunior().createCompetitiveRecord(discipline, name, timeInSeconds, date, placement, convention);
          case SENIOR ->
              controller.getTeamSenior().createCompetitiveRecord(discipline, name, timeInSeconds, date, placement, convention);
          // todo update csv files
        }
        
        
        // training record
      }
    }
  }
  
  public void printTeam(AgeGroup ageGroup, ArrayList<Competitive> competitors) {
    for (Competitive competitive : competitors) {
      if (competitive.getAgeGroup() == ageGroup && competitive.getMembershipStatus() == ACTIVE)
        UI.printCompetitive(competitive);
    }
  }
  
  
}