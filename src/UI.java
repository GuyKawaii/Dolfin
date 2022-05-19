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
  
  public static void printCompetitors(ArrayList<Competitive> competitors) {
    for (Competitive competitive : competitors) {
      printCompetitive(competitive);
    }
  }
  
  public static void printCompetitive(Competitive competitive) {
    System.out.printf("%-15s %s, con:%7s, res:%7s, %7s, %s, ",
        competitive.getName(),
        competitive.getBirthday(),
        competitive.getContingent(),
        competitive.getRestance(),
        competitive.getMembershipStatus(),
        competitive.getAgeGroup());
    System.out.println("COMPETITIVE " + ((Competitive) competitive).getDisciplines());
  }
  
  public static void printMotionist(Motionist motionist) {
    System.out.printf("%-15s %s, con:%7s, res:%7s, %7s, %s\n",
        motionist.getName(),
        motionist.getBirthday(),
        motionist.getContingent(),
        motionist.getRestance(),
        motionist.getMembershipStatus(),
        motionist.getAgeGroup());
  }
  
  public static void printMotionists(ArrayList<Motionist> motionists) {
    for (Motionist motionist : motionists) {
      printMotionist(motionist);
    }
  }
}
