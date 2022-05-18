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
        case "1" -> printMemberList();
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
    int membersInRestance = 0;
    int membersNotInRestance = 0;
    
    for (int i = 0; i < memberArrayList.size(); i++) {
      Member member = memberArrayList.get(i);
      if (member.getRestance() > 0) {
        System.out.println(String.format("res:%.2f %s", member.getRestance(), member.getName())); //TODO Gør det pænere - evt. align navnene
        membersInRestance++;
      } else {
        membersNotInRestance++;
      }
    }
    
    System.out.println(String.format("Members with restance [%3d] and without restance [%3d]", membersInRestance, membersNotInRestance)); // todo make nice cash money money baby
    if (membersInRestance == 0) System.out.println("There are no members with restance");
  }
  
  public void printExpeditedEarnings() {
    ArrayList<Member> memberArrayList = controller.getMemberList().getMembers();
    StringBuilder restanceMembers = new StringBuilder();
    StringBuilder contingentMembers = new StringBuilder();
    double expectedEarnings = 0;
    
    restanceMembers.append("\nNONE PAYING\n");
    contingentMembers.append("PAYING\n");
    
    for (int i = 0; i < memberArrayList.size(); i++) {
      Member member = memberArrayList.get(i);
      if (member.getRestance() == 0) {
        contingentMembers.append(String.format("con:%.2f %s\n", member.getContingent(), member.getName())); //TODO gør den grøn for positive beløb
        expectedEarnings += member.getContingent();
      } else {
        restanceMembers.append(String.format("res:%.2f %s\n", member.getRestance(), member.getName())); //TODO gør den rød for folk der ikke er med
      }
      
    }
    
    System.out.println(restanceMembers);
    System.out.println(contingentMembers);
    System.out.println(String.format("Expected earnings: %.2f", expectedEarnings)); // todo make nice cash money money baby
  }
  
  public void billAllMembers() {
    ArrayList<Member> members = controller.getMemberList().getMembers();
    
    for (Member member : members)
      member.addRestanceOnePeriod();
    
    System.out.println("BILLED ALL MEMBERS");
  }
  
  public void changeMemberResistance() {
    MemberList memberList = controller.getMemberList();
    Member member = null;
    String name;
    
    getMember:
    do {
      
      printMemberList();
      
      System.out.println("""
          
          CHANGE MEMBER RESISTANCE
          abort at any time -> Enter
          select member     -> name
          INPUT:\40""");
      name = scanner.nextLine();
      if ("".equals(name)) break getMember;
      else {
        member = memberList.getMember(name);
        System.out.println(member);
        if (member != null) break getMember;
      }
    } while (true);
  
    System.out.println("""
          
          CHANGE MEMBER RESISTANCE
          add    restance for one period -> 1
          remove restance for one period -> 2
          add    amount of restance      -> a amount
          remove amount of restance      -> r amount
          INPUT:\40""");
    String[] inputs = scanner.nextLine().toLowerCase().split(" ", 2);
    
    switch (inputs[0]) {
      case "1" -> member.addRestanceOnePeriod();
      case "2" -> member.removeRestanceOnePeriod();
      
      
      
    }
    
  }
  
  public void input() {
  
  
  }
  
  
}
