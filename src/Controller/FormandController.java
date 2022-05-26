package Controller;

import UserInterface.*;
import enums.Discipline;
import enums.MembershipStatus;
import member.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static enums.Discipline.*;
import static enums.MembershipStatus.*;

public class FormandController {
  MainController mainController;
  
  public FormandController(MainController mainController) {
    this.mainController = mainController;
  }
  
  public void formandMenu() {
    boolean formandMenu = true;
    do {
      FormandUI.printFormandMenu();
      String userInput = UI.capitalizeStringInput();
      switch (userInput) {
        case "1", "see", "s" -> UI.printMembers(mainController.getMemberList().getMembers());
        case "2", "register", "r" -> registerMembers();
        case "3", "delete", "d" -> deleteMembers();
        case "" -> formandMenu = false;
        default -> UI.invalidInputMessage();
      }
    } while (formandMenu);
  }
  
  
  public ArrayList<Discipline> inputDisciplines() {
    ArrayList<Discipline> disciplines = new ArrayList<>();
    String input;
    
    addDiscipline:
    while (disciplines.size() < 4) {
     FormandUI.printInputDisciplineInstructions();
      input = UI.stringInput();
      
      switch (input) {
        case "1", "crawl", "c" -> {
          if (!disciplines.contains(CRAWL)) disciplines.add(CRAWL);
        }
        case "2", "back crawl", "bc" -> {
          if (!disciplines.contains(BACK_CRAWL)) disciplines.add(BACK_CRAWL);
        }
        case "3", "breast stroke", "bs" -> {
          if (!disciplines.contains(BREAST_STROKE)) disciplines.add(BREAST_STROKE);
        }
        case "4", "butterfly", "b" -> {
          if (!disciplines.contains(BUTTERFLY)) disciplines.add(BUTTERFLY);
        }
        case "5", "retry", "r" -> disciplines.clear();
        default -> {
          break addDiscipline;
        }
      }
      
      FormandUI.printDisciplines(disciplines);
    }
    
    return disciplines;
  }
  
  
  public void registerMembers() {
    // default input
    String input;
    
    // member parameters
    int ID;
    String name;
    LocalDate birthday;
    MembershipStatus membershipStatus;
    ArrayList<Discipline> disciplines;

    FormandUI.printRegisteringMembers();

    registering:
    while (true) {
      
      // name
      FormandUI.printInputNameInstructions();
      name = UI.capitalizeStringInput();
      
      if (name.isBlank()) break registering;
      
      // birthday
      FormandUI.printInputBirthdayInstructions();
      birthday = UI.inputDate();
      if (birthday == null) break registering;
      
      // membership status
     FormandUI.printInputMembershipStatusInstructions();
      input = UI.capitalizeStringInput();
      switch (input) {
        case "1", "active", "a" -> membershipStatus = ACTIVE;
        case "2", "passive", "p" -> membershipStatus = PASSIVE;
        default -> {
          break registering;
        }
      }
  
      // member type
     FormandUI.printInputMemberTypeInstructions();
      input = UI.capitalizeStringInput();
      switch (input) {
        default -> {
          break registering;
        }
        // motionist
        case "1", "m", "motionist" -> {
          ID = mainController.getMemberList().createID();
          Motionist newMotionist = new Motionist(ID, name, birthday, membershipStatus);
          // file update
          mainController.getMemberList().addMotionist(newMotionist);
          mainController.getFileHandlingMemberList().saveMotionists(mainController.getMemberList().getMotionists());
          mainController.getFileHandlingMemberList().saveIdCounter(mainController.getMemberList());
        }
  
        // competitive
        case "2", "c", "competitive" -> {
          disciplines = inputDisciplines();
          ID = mainController.getMemberList().createID();
          Competitive newCompetitive = new Competitive(ID, name, birthday, membershipStatus, disciplines);
          // file update
          mainController.getMemberList().addCompetitive(newCompetitive);
          mainController.getFileHandlingMemberList().saveCompetitors(mainController.getMemberList().getCompetitors());
          mainController.getFileHandlingMemberList().saveIdCounter(mainController.getMemberList());
        }
        
      }
      
      FormandUI.printMemberCreatedConfirmation();
      UI.printMemberHeader();
      UI.printMember(mainController.getMemberList().getMember(ID));
    }
  }
  
  public void deleteMembers() {
    Member removedMember;
    
    selectingMember:
    while (true) {
     FormandUI.printInputDeleteMemberInstructions();
      Integer ID = UI.inputPositiveNumber();
      if (ID == null) break selectingMember;
      
      // try removing
      removedMember = mainController.getMemberList().removeMember(ID);
      if (removedMember != null) {
       FormandUI.printMemberRemovedConfirmation();
        UI.printMemberHeader();
        UI.printMember(removedMember);
        
        // file update
        mainController.getFileHandlingMemberList().saveMotionists(mainController.getMemberList().getMotionists());
        mainController.getFileHandlingMemberList().saveCompetitors(mainController.getMemberList().getCompetitors());
      } else {
        FormandUI.printMemberNotFoundError(ID);
      }
    }
    
  }
  
  
}
