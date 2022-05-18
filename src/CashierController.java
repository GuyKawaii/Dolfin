import member.Member;

import java.util.ArrayList;
import java.util.Scanner;

public class CashierController {
  Controller controller;
  Scanner scanner;
  
  public CashierController(Controller controller) {
    this.controller = controller;
    scanner = new Scanner(System.in);
  }
  
  
  public void mainMenu() {
    boolean mainMenu = true;
    do {
      System.out.print("""
          
          CASHIER:
          - see all members                   -> 1
          - see members in restance           -> 2
          - see expedited earnings for period -> 3
          - bill all members for one period   -> 4
          - change member resistance          -> 5
          - abort                             -> Enter
          SELECT:\040""");
      String userInput = scanner.nextLine().toLowerCase();
      switch (userInput) {
        case "1" -> UI.printMembers(controller.getMemberList().getMembers());
        case "2" -> printRestanceMembers();
        case "3" -> printExpeditedEarnings();
        case "4" -> billAllMembers();
        case "5" -> changeMemberResistance();
        default -> mainMenu = false;
      }
    } while (mainMenu);
    
  }
  
  public void printMemberList() {
    System.out.println(controller.getMemberList()); // todo add print method in THIS class NOT in memberList for printing
  }
  
  public void printRestanceMembers() {
    ArrayList<Member> memberArrayList = controller.getMemberList().getMembers();
    int amountRestance = 0;
    int amountNoRestance = 0;
    double totalRestance = 0;
    
    // for each member
    for (Member member : memberArrayList) {
      if (member.getRestance() > 0) {
        System.out.printf(Color.TEXT_RED + "res:%8.2f %s" + Color.TEXT_RESET + "\n", member.getRestance(), member.getName());
        totalRestance += member.getRestance();
        amountRestance++;
      } else {
        amountNoRestance++;
      }
    }
    
    if (amountRestance == 0) System.out.println("There are no members with restance!");
    else {
      System.out.printf("""
        RESTANCE TOTAL: %.2f
        %3d RESTANCE     MEMBERS
        %3d NON-RESTANCE MEMBERS
        """, totalRestance, amountRestance, amountNoRestance);
    }
    
  }
  
  public void printExpeditedEarnings() {
    ArrayList<Member> memberArrayList = controller.getMemberList().getMembers();
    StringBuilder restanceMembers = new StringBuilder();
    StringBuilder contingentMembers = new StringBuilder();
    
    double expectedEarnings = 0;
    
    restanceMembers.append("\nMISSING PAYMENT\n");
    contingentMembers.append("CONTINGENT PAYED\n");
    
    for (Member member : memberArrayList) {
      if (member.getRestance() == 0) {
        contingentMembers.append(String.format(Color.TEXT_GREEN + "con:%8.2f %s\n" + Color.TEXT_RESET, member.getContingent(), member.getName()));
        expectedEarnings += member.getContingent();
        
      } else {
        restanceMembers.append(String.format(Color.TEXT_RED + "res:%8.2f %s\n" + Color.TEXT_RESET, member.getRestance(), member.getName()));
      }
    }
    
    System.out.println(restanceMembers);
    System.out.println(contingentMembers);
    System.out.printf("EXPECTED EARNINGS: %.2f\n", expectedEarnings);
  }
  
  public void billAllMembers() {
    ArrayList<Member> members = controller.getMemberList().getMembers();
    
    System.out.print("Are you sure Y/N ");
    String input = scanner.nextLine().toLowerCase();
    
    if (input.equals("y")) {
      for (Member member : members)
        member.addRestanceOnePeriod();
      
      System.out.println("BILLED ALL MEMBERS");
    }
  }
  
  public void changeMemberResistance() {
    MemberList memberList = controller.getMemberList();
    Member member = null;
    double amount;
    String name;
    
    do {
      
      
      getMember:
      do {
        
        // to help input correct information
        printMemberList();
        if (member != null) System.out.println(member);
        
        System.out.print("""
            
            CHANGE MEMBER RESISTANCE
            abort at any time -> Enter
            select member     -> name
            INPUT:\40""");
        name = scanner.nextLine();
        if ("".equals(name)) return;
        else {
          member = memberList.getMember(name);
          if (member != null) break getMember;
        }
      } while (true);
      
      System.out.println(member);
      
      System.out.print("""
          
          CHANGE MEMBER RESISTANCE
          add    restance for one period -> 1
          remove restance for one period -> 2
          add    amount of restance      -> a amount
          remove amount of restance      -> r amount
          INPUT:\40""");
      String[] inputs = inputActionAndNumber();
      amount = Double.parseDouble(inputs[1]);
      
      switch (inputs[0]) {
        case "1" -> member.addRestanceOnePeriod();
        case "2" -> member.removeRestanceOnePeriod();
        case "add", "a" -> member.addRestance(amount);
        case "remove", "r" -> member.removeRestance(amount);
        default -> {
          return;
        }
      }
      
      System.out.println(member);
      
    } while (true);
  }
  
  public String[] inputActionAndNumber() {
    // returns an action and a valid-number as strings.
    // user can input: ACTION NO-NUMBER -> ACTION 0
    // user can input: ACTION NUMBER    -> ACTION NUMBER
    String[] inputs;
    Double number = null;
    
    do {
      inputs = scanner.nextLine().toLowerCase().split(" ", 2);
      
      // return with 0
      if (inputs.length == 1) return new String[]{inputs[0], "0"};
      
      // return with valid number
      try {
        number = Double.parseDouble(inputs[1]);
      } catch (Exception e) {
        System.out.println(inputs[1] + " is not a valid number");
      }
    } while (number == null);
    
    return inputs;
  }
  
  
}