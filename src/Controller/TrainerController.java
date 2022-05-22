package Controller;

import Controller.Controller;
import UserInterface.UI;

import java.util.Scanner;

public class TrainerController {
  Controller controller;
  Scanner scanner;
  
  public TrainerController(Controller controller) {
    this.controller = controller;
    scanner = new Scanner(System.in);
  }
  
  public void mainMenu() {
    boolean trainerMenu = true;
    do {
      System.out.print("""
          
          FORMAND:
          - See top 5             -> 1
          - see junior team       -> 2
          - see senior team       -> 3
          - add competitive entry -> 5
          - add training entry    -> 6
          - Return to main menu   -> Enter
          SELECT:\040""");
      String userInput = UI.receiveStringInput();
      switch (userInput) {
        case "1" -> UI.printMembers(controller.getMemberList().getMembers());
        case "2" -> System.out.println("2");
        case "3" -> System.out.println("3");
        case "4" -> System.out.println("4");
        case "5" -> System.out.println("5");
        case "6" -> System.out.println("6");
        case "" -> trainerMenu = false;
        default -> UI.invalidInputMessage();
      }
    } while (trainerMenu);
  }
  
  public void seeTopFive() {
    System.out.println("hello");
  }
  
  public void addEntry() {
  
  
  }
  
}