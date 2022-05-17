package member;

import enums.AgeGroup;
import enums.MembershipStatus;

import java.time.LocalDate;

public class MemberMotionist extends Member {
  
  public MemberMotionist(String name, LocalDate birthday, MembershipStatus membershipStatus) {
    super(name, birthday, membershipStatus);
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
}
