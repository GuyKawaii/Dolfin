package member;

import enums.MembershipStatus;

import java.time.LocalDate;

public class Motionist extends Member {
  
  public Motionist(String name, LocalDate birthday, MembershipStatus membershipStatus) {
    super(name, birthday, membershipStatus);
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
}
