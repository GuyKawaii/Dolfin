package UserInterface;

import enums.Discipline;

import java.util.ArrayList;

// todo maybe take printing elements into this class from controller part (kinda arbitrary split within a text based setup tbh hear patrick or tine again again maybe)
public class FormandUI {

  public static void printFormandMenu() {
    System.out.print("""
                    
          FORMAND:
          - See all members     -> 1
          - Register members    -> 2
          - Delete members      -> 3
          - Return to main menu -> Enter
          SELECT:\040""");
  }

  public static void printInputDisciplineInstructions() {
    System.out.println("""
                    
          INPUT DISCIPLINES
          crawl -> c | back crawl -> bc | breast stroke -> bs | butterfly -> b | retry -> r
          INPUT:\40""");
  }

  public static void printDisciplines(ArrayList<Discipline> disciplines) {
    System.out.println(disciplines);
  }

  public static void printRegisteringMembers() {
    System.out.print("""
                
        REGISTERING MEMBERS
        abort at any time -> Enter
        """);
  }

  public static void printInputNameInstructions() {
    System.out.print("""
                    
          INPUT name:\40""");
  }

  public static void printInputBirthdayInstructions() {
    System.out.print("\nINPUT birthday (yyyy-mm-dd): ");
  }

  public static void printInputMembershipStatusInstructions() {
    System.out.print("""
                    
          MEMBERSHIP STATUS
          active  -> 1
          passive -> 2
          abort   -> Enter
          SELECT:\40""");
  }

  public static void printInputMemberTypeInstructions() {
    System.out.print("""
          
          MEMBER TYPE
          motionist   -> 1
          competitive -> 2
          SELECT:\40""");
  }

  public static void printMemberCreatedConfirmation() {
    System.out.println("MEMBER CREATED");
  }

  public static void printInputDeleteMemberInstructions() {
    System.out.print("""
                    
          DELETE MEMBER
          - delete member -> ID of given member
          - abort         -> [Enter]
          SELECT:\040""");
  }

  public static void printMemberRemovedConfirmation() {
    System.out.println("\nREMOVED");
  }

  public static void printMemberNotFoundError(Integer ID) {
    System.out.printf(Color.TEXT_RED + "%s - MEMBER NOT FOUND" + Color.TEXT_RESET + "\n", ID);
  }

}
