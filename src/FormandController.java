import enums.Discipline;
import enums.MembershipStatus;
import member.Motionist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static enums.Discipline.*;
import static enums.MembershipStatus.*;

public class FormandController {
  Controller controller;
  Scanner scanner;
  
  public FormandController(Controller controller) {
    this.controller = controller;
    scanner = new Scanner(System.in);
  }
  
  public void mainMenu() {
    boolean mainMenu = true;
    do {
      System.out.print("""
          
          FORMAND:
          - See all members     -> s
          - Register members    -> r
          - Delete members      -> d
          - Return to main menu -> Enter
          SELECT:\040""");
      String userInput = scanner.nextLine().toLowerCase();
      switch (userInput) {
        case "see", "s" -> printMemberList();
        case "delete", "d" -> deleteMember();
        case "register", "r" -> registerMember();
        default -> mainMenu = false;
      }
    } while (mainMenu);
    
  }
  
  public void printMemberList() {
    System.out.println(controller.getMemberList()); // todo add print method in THIS class NOT in memberList for printing
  }
  
  public void registerMember() {
    // default input
    String input;
  
    // member parameters
    String name;
    LocalDate birthday;
    MembershipStatus membershipStatus;
    ArrayList<Discipline> disciplines = new ArrayList<>();
    
    System.out.print("""
        
        REGISTERING MEMBERS
        abort at any time -> Enter
        """);
    
    register:
    do { // todo add user input validation and abort ability
      // name
      System.out.print("""
          
          INPUT name:\40""");
      name = scanner.nextLine();
      if (name.isBlank()) break register;
      
      // birthday
      System.out.print("\nINPUT birthday (yyyy-mm-dd): ");
      birthday = LocalDate.parse(scanner.nextLine());
      
      // membership status
      System.out.print("""
          
          MEMBERSHIP STATUS
          active  -> a
          passive -> p
          abort   -> Enter
          SELECT:\40""");
      input = scanner.nextLine().toLowerCase();
      switch (input) {
        case "a" -> membershipStatus = ACTIVE;
        case "p" -> membershipStatus = PASSIVE;
        default -> {
          break register;
        }
      }
      
      // member type
      System.out.print("""
          
          MEMBER TYPE
          competitive -> c
          motionist   -> m
          SELECT:\40""");
      input = scanner.nextLine();
      switch (input) {
        default -> {
          break register;
        }
        case "m" -> controller.getMemberList().addMotionist(new Motionist(name, birthday, membershipStatus));
        
        // discipline
        case "c" -> {
          disciplines.clear();
          discipline:
          do {
            System.out.println("""
                
                INPUT DISCIPLINES
                crawl -> c | back crawl -> bc | breast stroke -> bs | butterfly -> b | retry -> r
                INPUT:\40""");
            input = scanner.nextLine().toLowerCase();
            
            switch (input) {
              case "retry", "r" -> disciplines.clear();
              case "crawl", "c" -> {
                if (!disciplines.contains(CRAWL)) disciplines.add(CRAWL);
              }
              case "back crawl", "bc" -> {
                if (!disciplines.contains(BACK_CRAWL)) disciplines.add(BACK_CRAWL);
              }
              case "breast stroke", "bs" -> {
                if (!disciplines.contains(BREAST_STROKE)) disciplines.add(BREAST_STROKE);
              }
              case "butterfly", "b" -> {
                if (!disciplines.contains(BUTTERFLY)) disciplines.add(BUTTERFLY);
              }
              default -> {
                break discipline;
              }
            }
  
            System.out.println(disciplines);
          } while (disciplines.size() < 4);
          
          controller.getMemberList().addMotionist(new Motionist(name, birthday, membershipStatus));
        }
        
      }
      
      System.out.println(controller.getMemberList().getMember(name));
    } while (true);
    
  }
  
  public void deleteMember() { // todo maybe enable functionality in formand mainMenu directly by using "d name"
    boolean selectingMember = true;
    boolean removed;
    
    do {
      System.out.print("""
          
          DELETE MEMBER
          - delete member -> name of given member
          - abort         -> [Enter]
          SELECT:\040""");
      String name = scanner.nextLine();
      
      removed = controller.getMemberList().removeMember(name);
      
      if (name.isBlank())
        selectingMember = false;
      else if (removed)
        System.out.println(String.format("%s - REMOVED", name)); // todo update csv files here or other place?
      else
        System.out.println(String.format("%s - MEMBER NOT FOUND", name));
      
    } while (selectingMember);
    
  }
  
  
}
