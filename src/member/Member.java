package member;

import enums.AgeGroup;
import static enums.AgeGroup.*;

import enums.MembershipStatus;
import static enums.MembershipStatus.*;

import java.time.LocalDate; // (yyyy-MM-dd)
import java.time.Period;

public abstract class Member {
  private String name;
  private LocalDate birthday;
  private int age;
  private AgeGroup ageGroup;
  private MembershipStatus membershipStatus;
  private double contingent;
  private double restance;
  
  public Member(String name, LocalDate birthday, MembershipStatus membershipStatus) {
    setName(name);
    setBirthday(birthday);
    setMembershipStatus(membershipStatus);
    setRestance(0);
    
    // calculate after all setters (based on setter values)
    calculateAgeAndAgeGroup();  // 1
    calculateContingent();      // 2
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }
  
  public LocalDate getBirthday() {
    return birthday;
  }
  
  public void calculateAgeAndAgeGroup() {
    // calculates age and assign ageGroup
    LocalDate today = LocalDate.now();
    age = Period.between(birthday, today).getYears();
    
    // assign
    if (age < 18)
      ageGroup = JUNIOR;
    else
      ageGroup = SENIOR;
  }
  
  public AgeGroup getAgeGroup() {
    return ageGroup;
  }
  
  public int getAge() {
    return age;
  }
  
  public void setMembershipStatus(MembershipStatus membershipStatus) {
    this.membershipStatus = membershipStatus;
  }
  
  public MembershipStatus getMembershipStatus() {
    return membershipStatus;
  }
  
  public void calculateContingent() {
    if (membershipStatus == PASSIVE) contingent = 500;
    else if (ageGroup == JUNIOR) contingent = 1000;
    else if (ageGroup == SENIOR && age < 60) contingent = 1600;
    else if (ageGroup == SENIOR && age >= 60) contingent = 1600 * (1 - 0.25);
    else contingent = 0; // todo maybe throw exception?
  }
  
  public double getContingent() {
    return contingent;
  }
  
  public void setRestance(double restance) {
    this.restance = restance;
  }
  
  public double getRestance() {
    return restance;
  }
}
