import Controller.Controller;
import other.Team;

public class Main {
  public static void main(String[] args) {
    new Main().go();
  }
  
  public void go() {
    new Controller().go(); // todo remove class Main after people see THIS message as we can start with main inside controller also

//    LocalDate date = now();
//    MemberMotionist m1 = new member.MemberMotionist("Daniel", now().minusYears(25), ACTIVE);
//    MemberMotionist m2 = new member.MemberMotionist("Kasper", now().minusYears(22), ACTIVE);
//    MemberMotionist m3 = new member.MemberMotionist("William", now().minusYears(17), ACTIVE);
//    MemberMotionist m4 = new member.MemberMotionist("Thomas", now().minusYears(19), PASSIVE);
//
//    MemberCompetitive m5 = new member.MemberCompetitive("Mike", now().minusYears(21), ACTIVE);
//    MemberCompetitive m6 = new member.MemberCompetitive("Lotte", now().minusYears(24), ACTIVE);
//    MemberCompetitive m7 = new member.MemberCompetitive("Veronica", now().minusYears(26), ACTIVE);
//    MemberCompetitive m8 = new member.MemberCompetitive("Grethe", now().minusYears(23), PASSIVE);
//
//    member.MemberList memberList = new member.MemberList();
//    memberList.addMotionist(m1);
//    memberList.addMotionist(m2);
//    memberList.addMotionist(m3);
//    memberList.addMotionist(m4);
//    memberList.addCompetitive(m5);
//    memberList.addCompetitive(m6);
//    memberList.addCompetitive(m7);
//    memberList.addCompetitive(m8);
//
//    System.out.println(memberList);
//    // test valid member
//    System.out.println(memberList.getMember("Daniel"));
//    System.out.println(memberList.getMotionist("Daniel"));
//    System.out.println(memberList.getCompetitive("Daniel"));
//
//    // test remove member
//    // memberList.removeMember("Daniel");
//
//    System.out.println(memberList.getMember("Daniel"));
//    System.out.println(memberList.getMotionist("Daniel"));
//    System.out.println(memberList.getCompetitive("Daniel"));
//
//    System.out.println(memberList.removeMotionist("Daniel"));
//    System.out.println(memberList.removeCompetitive("Daniel"));
//
//    System.out.println(memberList.getMemberAmount());
//    System.out.println(memberList.getMotionistAmount());
//    System.out.println(memberList.getCompetitiveAmount());
//
//    System.out.println(memberList.getMembers());
  }
}
