package UserInterface;

// todo maybe take printing elements into this class from controller part (kinda arbitrary split within a text based setup tbh hear patrick or tine again again maybe)
public class CashierUI {

  public static void printCashierMenu() {
    System.out.print("""
          
          CASHIER:
          - see all members                   -> 1
          - see members in restance           -> 2
          - see expedited earnings for period -> 3
          - bill all members for one period   -> 4
          - change member resistance          -> 5
          - abort                             -> Enter
          SELECT:\040""");
  }

  public static void printMemberRestance(double restance, String name) {
    System.out.printf(Color.TEXT_RED + "res:%8.2f %s" + Color.TEXT_RESET + "\n", restance, name);
  }

  public static void printNoMembersWithRestance() {
    System.out.println("There are no members with restance!");
  }

  public static void printTotalRestance(int amountRestance, int amountNoRestance, double totalRestance){
    System.out.printf("""
        RESTANCE TOTAL: %.2f
        %3d RESTANCE     MEMBERS
        %3d NON-RESTANCE MEMBERS
        """,totalRestance, amountRestance, amountNoRestance); //this is not working right now
  }

  public static String printMissingPayment() {
    String missingPayment = "\nMISSING PAYMENT\n";
    return missingPayment;
  }

  public static String printContingentPayed() {
    String contingentPayed = "CONTINGENT PAYED\n";
    return contingentPayed;
  }

  public static void printBillMembersConfirmationAskForConfirmation() {
    System.out.print("Are you sure Y/N ");
  }

  public static void printBillAllMembersConfirmed() {
    System.out.println("BILLED ALL MEMBERS");
  }

  public static void printChangeRestanceAskForName() {
    System.out.print("""
                        
            CHANGE MEMBER RESISTANCE
            abort at any time -> Enter
            select member     -> name
            INPUT:\40""");
  }

  public static void printAskForChoiceChangeRestance() {
    System.out.print("""
                    
          CHANGE MEMBER RESISTANCE
          add    restance for one period -> 1
          remove restance for one period -> 2
          add    amount of restance      -> a amount
          remove amount of restance      -> r amount
          INPUT:\40""");
  }

 public static String printMemberContingentAppend(double contingent, String name) {
    String contingentAndName = String.format(Color.TEXT_GREEN + "con:%8.2f %s\n" + Color.TEXT_RESET, contingent, name);
   return contingentAndName;
 }

 public static String printMemberRestanceAppend(double restance, String name) {
    String restanceAndName = String.format(Color.TEXT_RED + "res:%8.2f %s\n" + Color.TEXT_RESET, restance, name);
   return restanceAndName;
 }

 public static void printRestanceMemberStringBuilder(StringBuilder restanceMembers) {
   System.out.println(restanceMembers);
 }

 public static void printContingentStringBuilder(StringBuilder contingentMembers) {
   System.out.println(contingentMembers);
 }

 public static void printExpectedEarnings(double expectedEarnings) {
   System.out.printf("EXPECTED EARNINGS: %.2f\n", expectedEarnings);
 }


}
