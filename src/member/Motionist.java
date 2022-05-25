package member;

import enums.MembershipStatus;

import java.time.LocalDate;

public class Motionist extends Member {
  
  // ### constructors###
  // constructor with ID
  public Motionist(long ID, String name, LocalDate birthday, MembershipStatus membershipStatus) {
    super(ID, name, birthday, membershipStatus);
  }
  // constructor without ID
  public Motionist(String name, LocalDate birthday, MembershipStatus membershipStatus) {
    super(name, birthday, membershipStatus);
  }
  // ### constructors###
  
  
  @Override
  public String toString() {
    return super.toString();
  }
}
