package controller;

import userInterface.TrainerUI;
import userInterface.UI;
import enums.AgeGroup;
import enums.Discipline;
import member.Competitive;
import team.TeamRecords;
import record.RecordCompetition;

import java.time.LocalDate;
import java.util.*;

import static enums.AgeGroup.*;
import static enums.Discipline.*;
import static enums.MembershipStatus.*;

public class TrainerController {
  MainController mainController;
  
  public TrainerController(MainController mainController) {
    this.mainController = mainController;
  }
  
  public void TrainerMenu() {
    boolean trainerMenu = true;
    do {
      TrainerUI.printTrainerMenu();
      String[] userInputs = UI.twoStingsArguments();
      switch (userInputs[0]) {
        case "1" -> addRecord();
        case "2" -> seeTopFive(userInputs[1]);
        case "3" ->
            writeTeam(userInputs[1], mainController.getMemberList().getCompetitors());
        case "4" -> selectConvention(userInputs[1]);
        case "5" -> showConventions(userInputs[1]);
        case "" -> trainerMenu = false;
        default -> UI.invalidInputMessage();
      }
    } while (trainerMenu);
  }
  
  private void showConventions(String userInput) {
    ArrayList<String> conventions = new ArrayList<>();
    AgeGroup ageGroup;
    TeamRecords teamRecords;
    
    switch (userInput) {
      case "j" -> {
        ageGroup = JUNIOR;
        teamRecords = mainController.getTeamJunior();
      }
      case "s" -> {
        ageGroup = SENIOR;
        teamRecords = mainController.getTeamSenior();
      }
      default -> {
        UI.invalidInputMessage();
        return;
      }
    }
    
    
    for (RecordCompetition recordCompetition : teamRecords.getCrawlCompetition())
      if (!conventions.contains(recordCompetition.getConvention()))
        conventions.add(TrainerUI.printTeamCrawlCompetitionsRecords(recordCompetition.getConvention()));
    
    for (RecordCompetition recordCompetition : teamRecords.getBackCrawlCompetition())
      if (!conventions.contains(recordCompetition.getConvention()))
        conventions.add(TrainerUI.printTeamBackCrawlCompetitionRecords(recordCompetition.getConvention()));
    
    for (RecordCompetition recordCompetition : teamRecords.getBreastStrokeCompetition())
      if (!conventions.contains(recordCompetition.getConvention()))
        conventions.add(TrainerUI.printTeamBreastStrokeCompetitionRecords(recordCompetition.getConvention()));
    
    for (RecordCompetition recordCompetition : teamRecords.getButterflyCompetition())
      if (!conventions.contains(recordCompetition.getConvention()))
        conventions.add(TrainerUI.printTeamButterflyCompetitionRecords(recordCompetition.getConvention()));
    
    if (conventions.size() == 0) {
      TrainerUI.printEmptyConventionHeader(ageGroup);
      return;
    }
    
    
    // header
    TrainerUI.printConventionHeader(ageGroup);
    // write conventions
    for (String convention : conventions) {
      UI.printString(convention);
    }
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
    TeamRecords teamRecords;
    Discipline discipline;
    switch (userInput) {
      case "j" -> teamRecords = mainController.getTeamJunior();
      case "s" -> teamRecords = mainController.getTeamSenior();
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
      case CRAWL -> seeCompetitiveRecords(discipline, teamRecords.getCrawlCompetition());
      case BACK_CRAWL -> seeCompetitiveRecords(discipline, teamRecords.getBackCrawlCompetition());
      case BREAST_STROKE -> seeCompetitiveRecords(discipline, teamRecords.getBreastStrokeCompetition());
      case BUTTERFLY -> seeCompetitiveRecords(discipline, teamRecords.getButterflyCompetition());
    }
  }
  
  public void seeCompetitiveRecords(Discipline discipline, ArrayList<RecordCompetition> records) {
    ArrayList<RecordCompetition> conventionRecords = new ArrayList<>();
    String convention;
    
    // select convention
    TrainerUI.printInputConventionInstructions();
    convention = UI.UpperCaseInput();
    if (convention.isEmpty()) return;
    
    // select convention records
    for (RecordCompetition record : records) {
      if (record.getConvention().equalsIgnoreCase(convention))
        conventionRecords.add(record);
    }
    
    // no contestants
    if (conventionRecords.size() == 0) {
      TrainerUI.printConventionHasNoMembers(convention, discipline);
      return;
    }
    
    // sort
    Collections.sort(conventionRecords);
    
    // header
    TrainerUI.printConventionInfo(convention, discipline);
    
    // print convention records
    for (RecordCompetition record : conventionRecords) {
      TrainerUI.printMemberRecordInfo(record.getName(), record.getPlacement(), record.getTimeInSeconds(), record.getDate());
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
      
      UI.printCompetitors(mainController.getMemberList().getCompetitors());
      
      // competitive, name, ageGroup
      TrainerUI.printInputNameInRecordInstructions();
      competitive = UI.findActiveCompetitive(mainController.getMemberList());
      if (competitive == null) break addingRecords;
      else {
        ID = competitive.getID();
        name = competitive.getName();
      }
      
      UI.printWordMember();
      UI.printMemberHeader();
      UI.printMember(competitive);
      
      // discipline
      discipline = UI.selectCompetitorDiscipline(competitive);
      if (discipline == null) break addingRecords;
      
      // timeInSeconds
      TrainerUI.printInputTimeInRecordInstructions();
      timeInSeconds = UI.inputPositiveNumber();
      if (timeInSeconds == null) break addingRecords;
      
      
      // date
      TrainerUI.printInputDateInRecordInstructions();
      date = UI.inputDate();
      if (date == null) break addingRecords;
      
      
      // record type
      TrainerUI.printInputRecordTypeInstructions();
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
        TrainerUI.printInputPlacementInRecordCompetitionInstructions();
        placement = UI.inputPositiveNumber();
        if (placement == null) break addingRecords;
        
        // convention
        TrainerUI.printInputPlaceInRecordCompetitionInstructions();
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
  
  public void writeTeam(String userInput, ArrayList<Competitive> competitors) {
    ArrayList<Competitive> ageGroupCompetitors = new ArrayList<>();
    TeamRecords teamRecords;
    switch (userInput) {
      case "j" -> teamRecords = mainController.getTeamJunior();
      case "s" -> teamRecords = mainController.getTeamSenior();
      default -> {
        UI.invalidInputMessage();
        return;
      }
    }
    
    // Get TEAM members
    for (Competitive competitive : competitors)
      if ((competitive.getAgeGroup() == teamRecords.getAgeGroup()) && (competitive.getMembershipStatus() == ACTIVE))
        ageGroupCompetitors.add(competitive);
    
    // header
    if (ageGroupCompetitors.isEmpty()) {
      TrainerUI.printNoMembersInTeam(teamRecords.getAgeGroup(), teamRecords.getTrainer().getName());
      return;
    }
    
    TrainerUI.printTeam(teamRecords.getAgeGroup(), teamRecords.getTrainer().getName());
    
    // competitors
    for (Competitive competitive : ageGroupCompetitors) {
      TrainerUI.printMemberInTeam(competitive);
    }
  }
  
  
}