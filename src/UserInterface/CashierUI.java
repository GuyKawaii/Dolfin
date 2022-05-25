package UserInterface;

import member.Member;

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
  
  public static void printMemberRestance(Member member) {
    System.out.printf(Color.TEXT_RED + "%4s | %-15s , res:%8.2f ," + Color.TEXT_RESET + "\n",
        member.getID(),
        member.getName(),
        member.getRestance());
  }
  
  public static void printNoMembersWithRestance() {
    System.out.println("There are no members with restance!");
  }
  
  public static void printTotalRestance(int amountRestance, int amountNoRestance, double totalRestance) {
    System.out.printf(Color.TEXT_RED + """
         --------------------------------------
         NUM | RESTANCE TOTAL: %14.2f
        %4d | MEMBERS IN      RESTANCE
        %4d | MEMBERS WITH NO RESTANCE
        """ + Color.TEXT_RESET, totalRestance, amountRestance, amountNoRestance);
  }
  
  public static String printMissingPayment() {
    return String.format("""
            MISSING PAYMENT
            %s%4s | %-15s | %-12s |%s
            """, Color.TEXT_RED,
        "ID", "Name", "RESTANCE",
        Color.TEXT_RESET);
  }
  
  public static String printContingentPayed() {
    return String.format("""
            CONTINGENT PAYED
            %s%4s | %-15s | %-12s |%s
            """, Color.TEXT_GREEN,
        "ID", "Name", "RESTANCE",
        Color.TEXT_RESET);
  }
  
  public static void printBillMembersConfirmationAskForConfirmation() {
    System.out.print("Are you sure Y/N ");
  }
  
  public static void printBillAllMembersConfirmed() {
    System.out.println("BILLED ALL MEMBERS");
  }
  
  public static void printChangeRestanceAskForID() {
    System.out.print("""
        
        CHANGE MEMBER RESISTANCE
        select member     -> ID of member
        abort at any time -> Enter
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
  
  public static String stringMemberWithNoRestance(Member member) {
    return String.format(Color.TEXT_GREEN + "%4s | %-15s , res:%8.2f ,\n" + Color.TEXT_RESET,
        member.getID(),
        member.getName(),
        member.getContingent());
  }
  
  public static String stringMemberWithRestance(Member member) {
    return String.format(Color.TEXT_RED + "%4s | %-15s , res:%8.2f ,\n" + Color.TEXT_RESET,
        member.getID(),
        member.getName(),
        member.getRestance());
  }
  
  public static void printStringBuilderList(StringBuilder restanceMembers) {
    System.out.print(restanceMembers);
  }
  
  public static void printExpectedEarnings(double expectedEarnings) {
    String color;
    if (0 < expectedEarnings) color = Color.TEXT_GREEN;
    else color = Color.TEXT_RED;
    
    System.out.printf("EXPECTED EARNINGS: %s%18.2f%s\n",
        color,
        expectedEarnings,
        Color.TEXT_RESET);
  }
  
  public static void printChangedRestance(Member member) {
    System.out.println("RESTANCE CHANGED");
    UI.printMemberHeader();
    UI.printMember(member);
  }
  
  
  public static void printMemberRestanceHeader() {
    System.out.printf("%s%4s | %-15s | %-12s |%s\n",
        Color.TEXT_RED,
        "ID", "Name", "RESTANCE",
        Color.TEXT_RESET);
  }
}
