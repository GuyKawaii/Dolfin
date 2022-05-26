package member;

import enums.Discipline;
import enums.MembershipStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class Competitive extends Member {
  private ArrayList<Discipline> disciplines;
  
  // ### constructors ###
  // constructors with ID
  public Competitive(int ID, String name, LocalDate birthDay, MembershipStatus membershipStatus, ArrayList<Discipline> disciplines) {
    super(ID, name, birthDay, membershipStatus);
    
    setDisciplines(disciplines);
  }
  
  public Competitive(int ID, String name, LocalDate birthDay, MembershipStatus membershipStatus) {
    super(ID, name, birthDay, membershipStatus);
    
    disciplines = new ArrayList<>();
  }
  
//  // constructors without ID
//  public Competitive(String name, LocalDate birthDay, MembershipStatus membershipStatus, ArrayList<Discipline> disciplines) {
//    super(name, birthDay, membershipStatus);
//
//    setDisciplines(disciplines);
//  }
//
//  public Competitive(String name, LocalDate birthDay, MembershipStatus membershipStatus) {
//    super(name, birthDay, membershipStatus);
//
//    disciplines = new ArrayList<>();
//  }
  // ### constructors ###
  
  
  public ArrayList<Discipline> getDisciplines() {
    return disciplines;
  }
  
  public void setDisciplines(ArrayList<Discipline> disciplines) {
    this.disciplines = disciplines;
  }
  
  public boolean hasDiscipline(Discipline discipline) {
    // checks if the desired competitive discipline
    return disciplines.contains(discipline);
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
}
