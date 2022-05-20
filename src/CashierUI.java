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

}
