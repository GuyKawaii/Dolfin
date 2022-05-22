package Controller;

import Controller.Controller;
import UserInterface.UI;
import enums.AgeGroup;
import enums.Discipline;
import filehandling.FileHandlingTeam;
import other.Team;
import other.Trainer;
import record.Record;
import record.RecordTraining;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static enums.AgeGroup.*;
import static enums.Discipline.*;

public class TrainerController {
  Controller controller;
  Scanner scanner;
  FileHandlingTeam fileHandlingTeam = new FileHandlingTeam();
  
  public TrainerController(Controller controller) {
    this.controller = controller;
    scanner = new Scanner(System.in);
  }
  
  public void mainMenu() {
    boolean trainerMenu = true;
    do {
      System.out.print("""
          
          FORMAND:
          - See top 5 junior      -> 1
          - See top 5 senior      -> 1
          - see junior team       -> 2
          - see senior team       -> 3
          - add competitive entry -> 5
          - add training entry    -> 6
          - Return to main menu   -> Enter
          SELECT:\040""");
      String userInput = UI.receiveStringInput();
      switch (userInput) {
        case "1" -> seeTopFive(JUNIOR);
        case "2" -> seeTopFive(SENIOR);
        case "3" -> System.out.println("3");
        case "4" -> System.out.println("4");
        case "5" -> System.out.println("5");
        case "6" -> System.out.println("6");
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
    Trainer trainer1 = new Trainer("Søren");
    Trainer trainer2 = new Trainer("Jytte");
    Team junior = new Team(AgeGroup.JUNIOR, trainer1);
    Team senior = new Team(AgeGroup.SENIOR, trainer2);

    junior.createTrainingRecord(CRAWL, "William", 6, LocalDate.now());
    junior.createTrainingRecord(CRAWL, "Anders", 10, LocalDate.now());
    fileHandlingTeam.saveTeam(junior);


    ArrayList<Integer> intArray = new ArrayList<>();
    ArrayList<Object> objectArray = (ArrayList<Object>)(ArrayList<?>) (intArray);

    fileHandlingTeam.saveRecord(junior.getTrainer(), (ArrayList<Record>)(ArrayList<?>)(junior.getCrawlTraining()));
    fileHandlingTeam.saveRecord(junior.getTrainer(), junior.getBackCrawlTraining());
    fileHandlingTeam.saveRecord(junior.getTrainer(), junior.getBreastStrokeTraining());
    fileHandlingTeam.saveRecord(junior.getTrainer(), junior.getButterflyTraining());
    fileHandlingTeam.saveRecord(junior.getTrainer(), junior.getCrawlCompetition());
    fileHandlingTeam.saveRecord(junior.getTrainer(), junior.getBackCrawlCompetition());
    fileHandlingTeam.saveRecord(junior.getTrainer(), junior.getBreastStrokeCompetition());
    fileHandlingTeam.saveRecord(junior.getTrainer(), junior.getButterflyCompetition());
    // senior save?
    fileHandlingTeam.saveRecord(senior.getTrainer(), senior.getCrawlTraining());
    fileHandlingTeam.saveRecord(senior.getTrainer(), senior.getCrawlTraining());
    fileHandlingTeam.saveRecord(senior.getTrainer(), senior.getCrawlTraining());
    fileHandlingTeam.saveRecord(senior.getTrainer(), senior.getCrawlTraining());
    fileHandlingTeam.saveRecord(senior.getTrainer(), senior.getCrawlTraining());
    fileHandlingTeam.saveRecord(senior.getTrainer(), senior.getCrawlTraining());
    fileHandlingTeam.saveRecord(senior.getTrainer(), senior.getCrawlTraining());
    fileHandlingTeam.saveRecord(senior.getTrainer(), senior.getCrawlTraining());



  }
  
}