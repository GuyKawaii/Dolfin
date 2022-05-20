import enums.Discipline;
import enums.MembershipStatus;
import member.Competitive;
import member.Motionist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static enums.Discipline.*;
import static enums.MembershipStatus.*;

public class FormandController {
  Controller controller;
  
  public FormandController(Controller controller) {
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
      String userInput = UI.receiveStringInput();
      switch (userInput) {
        case "1", "see", "s" -> UI.printMembers(controller.getMemberList().getMembers());
        case "2", "register", "r" -> registerMembers();
        case "3", "delete", "d" -> deleteMembers();
        case "" -> formandMenu = false;
        default -> UI.invalidInputMessage();
      }
    } while (formandMenu);
    
  }
  
  
  public void registerMembers() { // todo check if member already exists in memberList before adding more details
    // default input
    String input;
    
    // member parameters
    String name;
    LocalDate birthday;
    MembershipStatus membershipStatus;
    ArrayList<Discipline> disciplines;
    
    System.out.print("""
        
        REGISTERING MEMBERS
        abort at any time -> Enter
        """);
    
    registering:
    while (true) { // todo add user input validation and abort ability
      // name
      System.out.print("""
          
          INPUT name:\40""");
      name = UI.receiveStringInput();
      if (name.isBlank()) break registering;
      
      // birthday
      birthday = inputBirthday();
      if (birthday == null) break registering;
      
      // membership status
      System.out.print("""
          
          MEMBERSHIP STATUS
          active  -> 1
          passive -> 2
          abort   -> Enter
          SELECT:\40""");
      input = UI.receiveStringInput();
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
      input = UI.receiveStringInput();
      switch (input) {
        default -> {
          break registering;
        }
        // motionist
        case "1", "m", "motionist" ->
            controller.getMemberList().addMotionist(new Motionist(name, birthday, membershipStatus));
        // competitive
        case "2", "c", "competitive" -> {
          disciplines = inputDisciplines();
          controller.getMemberList().addCompetitive(new Competitive(name, birthday, membershipStatus, disciplines));
        }
        
      }
      
      System.out.println("MEMBER CREATED");
      UI.printMember(controller.getMemberList().getMember(name));
    }
  }
  
  public LocalDate inputBirthday() {
    String input;
    LocalDate birthday = null;
    
    System.out.print("\nINPUT birthday (yyyy-mm-dd): ");
    
    do {
      input = UI.receiveStringInput();
      if (input.isEmpty()) return null;
      
      try {
        birthday = LocalDate.parse(input);
      } catch (Exception e) {
        System.out.println(input + " is not valid date format");
      }
      
    } while (birthday == null);
    
    return birthday;
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
      input = UI.receiveStringInput().toLowerCase();
      
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
  
  public void deleteMembers() {
    boolean selectingMember = true;
    boolean removedMember;
    
    while (selectingMember) {
      System.out.print("""
          
          DELETE MEMBER
          - delete member -> name of given member
          - abort         -> [Enter]
          SELECT:\040""");
      String name = UI.receiveStringInput();
      
      removedMember = controller.getMemberList().removeMember(name);
      
      if (name.isBlank())
        selectingMember = false;
      else if (removedMember)
        System.out.printf("%s - REMOVED\n", name); // todo update csv files here or other place?
      else {
        System.out.printf(Color.TEXT_RED + "%s - MEMBER NOT FOUND" + Color.TEXT_RED + "\n", name);
      }
    }
    
  }
  
  
}
