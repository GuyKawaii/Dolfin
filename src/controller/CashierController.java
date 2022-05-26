package controller;

import userInterface.*;
import member.*;

import java.util.ArrayList;

public class CashierController {
  MainController mainController;
  
  public CashierController(MainController mainController) {
    this.mainController = mainController;
  }
  
  
  public void cashierMenu() {
    boolean cashierMenu = true;
    do {
      CashierUI.printCashierMenu();
      String userInput = UI.capitalizeStringInput();
      switch (userInput) {
        case "1" -> UI.printMembers(mainController.getMemberList().getMembers());
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
    ArrayList<Member> memberArrayList = mainController.getMemberList().getMembers();
    int restanceMembers = 0;
    int noRestanceMembers = 0;
    double totalRestance = 0;
    
    // header
    CashierUI.printMemberRestanceHeader();
    
    // for each member
    for (Member member : memberArrayList) {
      if (0 < member.getRestance()) {
        CashierUI.printMemberRestance(member);
        totalRestance += member.getRestance();
        restanceMembers++;
      } else {
        noRestanceMembers++;
      }
    }
    
    if (restanceMembers == 0) CashierUI.printNoMembersWithRestance();
    else CashierUI.printTotalRestance(restanceMembers, noRestanceMembers, totalRestance);
  }
  
  public void showExpeditedEarnings() {
    ArrayList<Member> memberArrayList = mainController.getMemberList().getMembers();
    StringBuilder restanceList = new StringBuilder();
    StringBuilder contingentList = new StringBuilder();
    double expectedEarnings = 0;
    
    // headers
    restanceList.append(CashierUI.printMissingPayment());
    contingentList.append(CashierUI.printContingentPayed());
    
    // add members to lists
    for (Member member : memberArrayList) {
      if (member.getRestance() == 0) {
        contingentList.append(CashierUI.stringMemberWithNoRestance(member));
        expectedEarnings += member.getContingent();
        
      } else {
        restanceList.append(CashierUI.stringMemberWithRestance(member));
      }
    }
    
    // print build lists
    CashierUI.printStringBuilderList(restanceList);
    CashierUI.printStringBuilderList(contingentList);
    CashierUI.printExpectedEarnings(expectedEarnings);
  }
  
  public void billAllMembers() {
    ArrayList<Member> members = mainController.getMemberList().getMembers();
    
    CashierUI.printBillMembersConfirmationAskForConfirmation();
    String input = UI.capitalizeStringInput();
    
    if (input.equals("Y")) {
      for (Member member : members) {
        member.addRestanceOnePeriod();
        mainController.getFileHandlingMemberList().saveMembers(members);
        
      }
      CashierUI.printBillAllMembersConfirmed();
      mainController.getFileHandlingMemberList().saveMembers(mainController.getMemberList().getMembers());
    }
  }
  
  public void changeMemberResistance() {
    MemberList memberList = mainController.getMemberList();
    Member member = null;
    double amount;
    Integer ID;
    
    do {
      
      getMember:
      do {
        // to help input correct information
        if (member == null) UI.printMembers(mainController.getMemberList().getMembers());
        
        CashierUI.printChangeRestanceAskForID();
        ID = UI.inputPositiveNumber();
        if (ID == null) return;
        else {
          member = memberList.getMember(ID);
          if (member == null) UI.cannotFindID();
          else break getMember;
        }
      } while (true);
  
      UI.printMemberHeader();
      UI.printMember(member);
      
      CashierUI.printAskForChoiceChangeRestance();
      String[] inputs = inputActionAndNumber();
      amount = Double.parseDouble(inputs[1]);
      
      switch (inputs[0]) {
        case "1" -> {
          member.addRestanceOnePeriod();
          mainController.getFileHandlingMemberList().saveMembers(mainController.getMemberList().getMembers());
        }
        case "2" -> {
          member.removeRestanceOnePeriod();
          mainController.getFileHandlingMemberList().saveMembers(mainController.getMemberList().getMembers());
        }
        case "add", "a" -> {
          member.addRestance(amount);
          mainController.getFileHandlingMemberList().saveMembers(mainController.getMemberList().getMembers());
        }
        case "remove", "r" -> {
          member.removeRestance(amount);
          mainController.getFileHandlingMemberList().saveMembers(mainController.getMemberList().getMembers());
        }
        default -> {
          return;
        }
      }
      
      CashierUI.printChangedRestance(member);
      
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
