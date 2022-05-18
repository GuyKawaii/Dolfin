import member.Competitive;
import member.Member;
import member.Motionist;

import java.util.ArrayList;

public class UI {
  
  public static void printMembers(ArrayList<Member> members) {
    // William 2005-05-18 con:1000,00 res:0,00 COMPETITIVE crawl,
    // William 2007-05-18 con:1000,00 res:0,00 MOTIONIST
    for (Member member : members) {
      printMember(member);
    }
  }
  
  public static void printMember(Member member) {
      System.out.printf("%-15s %s, con:%7s, res:%7s, %7s, %s, ",
          member.getName(),
          member.getBirthday(),
          member.getContingent(),
          member.getRestance(),
          member.getMembershipStatus(),
          member.getAgeGroup());
      if (member instanceof Motionist) System.out.println("MOTIONIST");
      if (member instanceof Competitive) System.out.println("COMPETITIVE " + ((Competitive) member).getDisciplines());
    }
    
}
