package member;

import enums.Discipline;
import enums.MembershipStatus;

import java.time.LocalDate;

public class MemberCompetitive extends Member{
  private Discipline[] disciplines;
  
  
  public MemberCompetitive(String name, LocalDate birthDay, MembershipStatus membershipStatus) {
    super(name, birthDay, membershipStatus);
    
    disciplines = new Discipline[4];
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
}
