package member;

import enums.AgeGroup;
import enums.Disciplines;
import enums.MembershipStatus;

import java.time.LocalDate;
import java.util.Arrays;

public class MemberCompetitive extends Member{
  private Disciplines[] disciplines;
  
  
  public MemberCompetitive(String name, LocalDate birthDay, MembershipStatus membershipStatus) {
    super(name, birthDay, membershipStatus);
    
    disciplines = new Disciplines[4];
  }
}
