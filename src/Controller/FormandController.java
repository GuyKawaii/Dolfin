package Controller;

import UserInterface.*;
import enums.Discipline;
import enums.MembershipStatus;
import filehandling.FileHandlingMemberList;
import member.Competitive;
import member.Member;
import member.MemberList;
import member.Motionist;

import java.time.LocalDate;
import java.util.ArrayList;

import static enums.Discipline.*;
import static enums.MembershipStatus.*;

public class FormandController {
  Controller controller;
  
  public FormandController(Controller controller) {
    // controller
    this.controller = controller;
  }
  
  public void formandMenu() {
    boolean formandMenu = true;
    do {
      System.out.print("""
                    
          FORMAND:
          - See all members     -> 1
          - Register members    -> 2
          - Delete members      -> 3
          - Return to main menu -> Enter
          SELECT:\040""");
      String userInput = UI.capitalizeStringInput();
      switch (userInput) {
        case "1", "see", "s" -> UI.printMembers(controller.getMemberList().getMembers());
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
      System.out.println("""
                    
          INPUT DISCIPLINES
          crawl -> c | back crawl -> bc | breast stroke -> bs | butterfly -> b | retry -> r
          INPUT:\40""");
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
      
      System.out.println(disciplines);
    }
    
    return disciplines;
  }
  
  
  public void registerMembers() { // todo check if member already exists in memberList before adding more details
    // default input
    String input;
    
    // member parameters
    int ID;
    String name;
    LocalDate birthday;
    MembershipStatus membershipStatus;
    ArrayList<Discipline> disciplines;
    
    System.out.print("""
                
        REGISTERING MEMBERS
        abort at any time -> Enter
        """);
    
    registering:
    while (true) {
      
      // name
      System.out.print("""
                    
          INPUT name:\40""");
      name = UI.capitalizeStringInput();
      
      if (name.isBlank()) break registering;
      
      // birthday
      System.out.print("\nINPUT birthday (yyyy-mm-dd): ");
      birthday = UI.inputDate();
      if (birthday == null) break registering;
      
      // membership status
      System.out.print("""
                    
          MEMBERSHIP STATUS
          active  -> 1
          passive -> 2
          abort   -> Enter
          SELECT:\40""");
      input = UI.capitalizeStringInput();
      switch (input) {
        case "1", "active", "a" -> membershipStatus = ACTIVE;
        case "2", "passive", "p" -> membershipStatus = PASSIVE;
        default -> {
          break registering;
        }
      }
  
      // member type
      System.out.print("""
          
          MEMBER TYPE
          motionist   -> 1
          competitive -> 2
          SELECT:\40""");
      input = UI.capitalizeStringInput();
      switch (input) {
        default -> {
          break registering;
        }
        // motionist
        case "1", "m", "motionist" -> {
          ID = controller.getMemberList().createID();
          Motionist newMotionist = new Motionist(ID, name, birthday, membershipStatus);
          controller.getMemberList().addMotionist(newMotionist);
          controller.getFileHandlingMemberList().saveMotionists(controller.getMemberList().getMotionists());
          controller.getFileHandlingMemberList().saveIdCounter(controller.getMemberList());
        }
  
        // competitive
        case "2", "c", "competitive" -> {
          disciplines = inputDisciplines();
          ID = controller.getMemberList().createID();
          Competitive newCompetitive = new Competitive(ID, name, birthday, membershipStatus, disciplines);
          controller.getMemberList().addCompetitive(newCompetitive);
          controller.getFileHandlingMemberList().saveCompetitors(controller.getMemberList().getCompetitors());
          controller.getFileHandlingMemberList().saveIdCounter(controller.getMemberList());
        }
        
      }
      
      System.out.println("MEMBER CREATED");
      UI.printMemberHeader();
      UI.printMember(controller.getMemberList().getMember(ID));
    }
  }
  
  public void deleteMembers() {
    Member removedMember;
    
    selectingMember:
    while (true) {
      System.out.print("""
                    
          DELETE MEMBER
          - delete member -> ID of given member
          - abort         -> [Enter]
          SELECT:\040""");
      Integer ID = UI.inputPositiveNumber();
      if (ID == null) break selectingMember;
      
      // try removing
      removedMember = controller.getMemberList().removeMember(ID);
      if (removedMember != null) {
        System.out.println("\nREMOVED");
        UI.printMemberHeader();
        UI.printMember(removedMember);
        
        controller.getFileHandlingMemberList().saveMotionists(controller.getMemberList().getMotionists());
        controller.getFileHandlingMemberList().saveCompetitors(controller.getMemberList().getCompetitors());
      } else {
        System.out.printf(Color.TEXT_RED + "%s - MEMBER NOT FOUND" + Color.TEXT_RESET + "\n", ID);
      }
    }
    
  }
  
  
}
