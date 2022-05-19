package member;

import enums.Discipline;
import enums.MembershipStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class Competitive extends Member{
  private ArrayList<Discipline> disciplines;
  
  
  public Competitive(String name, LocalDate birthDay, MembershipStatus membershipStatus) {
    super(name, birthDay, membershipStatus);
  
    disciplines = new ArrayList<>();
  }
  
  public Competitive(String name, LocalDate birthDay, MembershipStatus membershipStatus, ArrayList<Discipline> disciplines) {
    super(name, birthDay, membershipStatus);
    
    this.disciplines = disciplines;
  }
  
  public ArrayList<Discipline> getDisciplines() {
    return disciplines;
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
}
