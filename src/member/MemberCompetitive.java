package member;

import enums.Disciplines;
import enums.MembershipStatus;

import java.time.LocalDate;

public class MemberCompetitive extends Member{
  private Disciplines[] disciplines;
  
  
  public MemberCompetitive(String name, LocalDate birthDay, MembershipStatus membershipStatus) {
    super(name, birthDay, membershipStatus);
    
    disciplines = new Disciplines[4];
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
}
