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
          - see members in restance           -> 1, s
          - see expedited earnings for period -> 2, e
          - bill all members for one period   -> 3, b
          - change member resistance          -> 4, c
          - abort                             -> Enter
          SELECT:\040""");
      String userInput = scanner.nextLine().toLowerCase();
      switch (userInput) {
        case "1", "s" -> printRestanceMembers();
        case "2", "e" -> printExpeditedEarnings();
        case "3", "b" -> System.out.println("bill all members for one period");
        case "4", "c" -> System.out.println("change member resistance");
        default -> mainMenu = false;
      }
    } while (mainMenu);
    
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
    int membersInRestance = 0;
    int membersNotInRestance = 0;
    int expectedEarnings = 0;
  
    for (int i = 0; i < memberArrayList.size(); i++) {
      Member member = memberArrayList.get(i);
      if (member.getRestance() == 0) {
        System.out.println(String.format("res:%.2f %s", member.getRestance(), member.getName())); //TODO gør den grøn for positive beløb
        membersInRestance++;
      } else {
        System.out.println(String.format("res:%.2f %s", member.getRestance(), member.getName())); //TODO gør den rød for folk der ikke er med
        membersNotInRestance++;
      }
    }
  
    System.out.println(String.format("Members with restance [%3d] and without restance [%3d]", membersInRestance, membersNotInRestance)); // todo make nice cash money money baby
    if (membersInRestance == 0) System.out.println("There are no members with restance");
  }
  
  public void billAllMembers() {
    System.out.println("WHERE'S THE MONEY LEBOWSKI - https://www.youtube.com/watch?v=KdftbYqA_VQ");
  }
  
  public void changeMemberResistance() {
    Member member; // drag member into this method from memberList and change values
    String input;
    
    changing:
    do {
      System.out.println("""
          War never changes.Or does it? The war has changed. Did it? The answer is "no".
          Unless it is "yes". No, of course it is! Is war.
          Yes!
          No.
          Yes?
          """);
      input = scanner.nextLine().toLowerCase();
      switch (input) {
        case "increase", "i", "1" -> System.out.println("adding");
        case "decrease", "d", "2" -> System.out.println("adding");
        default -> {
          break changing;
        }
      }
      
      
    } while (true);
    
  }
  
  
}
