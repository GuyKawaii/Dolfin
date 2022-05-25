package Controller;

import UserInterface.*;
import member.*;

import java.util.ArrayList;

public class CashierController {
  Controller controller;
  
  public CashierController(Controller controller) {
    this.controller = controller;
  }
  
  
  public void cashierMenu() {
    boolean cashierMenu = true;
    do {
      CashierUI.printCashierMenu();
      String userInput = UI.capitalizeStringInput();
      switch (userInput) {
        case "1" -> UI.printMemberList(controller.getMemberList());
        case "2" -> showRestanceMembers();
        case "3" -> showExpeditedEarnings();
        case "4" -> billAllMembers();
        case "5" -> changeMemberResistance();
        case "" -> cashierMenu = false;
        default -> UI.invalidInputMessage();
      }
    } while (cashierMenu);

  }

 // UI.printMembers(controller.getMemberList().getMembers()); Til formanden


  public void showRestanceMembers() {
    ArrayList<Member> memberArrayList = controller.getMemberList().getMembers();
    int amountRestance = 0;
    int amountNoRestance = 0;
    double totalRestance = 0;
    
    // for each member
    for (Member member : memberArrayList) {
      if (member.getRestance() > 0) {
        CashierUI.printMemberRestance(member.getRestance(), member.getName());
        //System.out.printf(Color.TEXT_RED + "res:%8.2f %s" + Color.TEXT_RESET + "\n", member.getRestance(), member.getName());
        //replaced the above and put it in CashierUI
        totalRestance += member.getRestance();
        amountRestance++;
      } else {
        amountNoRestance++;
      }
    }
    
    if (amountRestance == 0) CashierUI.printNoMembersWithRestance();
    else {
      CashierUI.printTotalRestance(amountRestance, amountNoRestance, totalRestance);
      /* System.out.printf("""
        RESTANCE TOTAL: %.2f
        %3d RESTANCE     MEMBERS
        %3d NON-RESTANCE MEMBERS
        """, totalRestance, amountRestance, amountNoRestance); */
    }
    
  }
  
  public void showExpeditedEarnings() {
    ArrayList<Member> memberArrayList = controller.getMemberList().getMembers();
    StringBuilder restanceMembers = new StringBuilder();
    StringBuilder contingentMembers = new StringBuilder();
    
    double expectedEarnings = 0;
    
   // restanceMembers.append("\nMISSING PAYMENT\n");
    restanceMembers.append(CashierUI.printMissingPayment()); //replaces line above.
   // contingentMembers.append("CONTINGENT PAYED\n");
    contingentMembers.append(CashierUI.printContingentPayed());
    
    for (Member member : memberArrayList) {
      if (member.getRestance() == 0) {
        contingentMembers.append(CashierUI.printMemberContingentAppend(member.getContingent(), member.getName()));
        expectedEarnings += member.getContingent();
        
      } else {
        restanceMembers.append(CashierUI.printMemberRestanceAppend(member.getRestance(), member.getName()));
      }
    }

    CashierUI.printRestanceMemberStringBuilder(restanceMembers);
    CashierUI.printContingentStringBuilder(contingentMembers);
    CashierUI.printExpectedEarnings(expectedEarnings);
  }
  
  public void billAllMembers() {
    ArrayList<Member> members = controller.getMemberList().getMembers();
    
    CashierUI.printBillMembersConfirmationAskForConfirmation();
    String input = UI.capitalizeStringInput();
    
    if (input.equals("Y")) {
      for (Member member : members) {
        member.addRestanceOnePeriod();
        controller.getFileHandlingMemberList().saveMembers(members);

      }
      CashierUI.printBillAllMembersConfirmed();
      controller.getFileHandlingMemberList().saveMembers(controller.getMemberList().getMembers());
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

        if (member != null) System.out.println(member);
        UI.printMemberList(controller.getMemberList());
       CashierUI.printChangeRestanceAskForName();
        name = UI.capitalizeStringInput();
        if ("".equals(name)) return;
        else {
          member = memberList.getMember(name);
          if (member != null) break getMember;
        }
      } while (true);
      
      System.out.println(member);
      
      CashierUI.printAskForChoiceChangeRestance();
      String[] inputs = inputActionAndNumber();
      amount = Double.parseDouble(inputs[1]);
      
      switch (inputs[0]) {
        case "1" -> {
          member.addRestanceOnePeriod();
          controller.getFileHandlingMemberList().saveMembers(controller.getMemberList().getMembers());
        }
        case "2" -> {
          member.removeRestanceOnePeriod();
          controller.getFileHandlingMemberList().saveMembers(controller.getMemberList().getMembers());
        }
        case "add", "a" -> {
          member.addRestance(amount);
          controller.getFileHandlingMemberList().saveMembers(controller.getMemberList().getMembers());
        }
        case "remove", "r" -> {
          member.removeRestance(amount);
          controller.getFileHandlingMemberList().saveMembers(controller.getMemberList().getMembers());
        }
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
      // inputs = scanner.nextLine().toLowerCase().split(" ", 2);
      inputs = UI.capitalizeStringInput().toLowerCase().split(" ", 2); //changed first letter to uppercase in receiveStringInput method
      // return with 0
      if (inputs.length == 1) return new String[]{inputs[0], "0"};
      
      // return with valid number
      try {
        number = Double.parseDouble(inputs[1]);
      } catch (Exception e) {
        UI.printNumberInputError(inputs[1]);
      }
    } while (number == null);
    
    return inputs;
  }
  
  
}
