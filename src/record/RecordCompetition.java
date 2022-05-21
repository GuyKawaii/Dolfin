package record;

import enums.AgeGroup;

import java.time.LocalDate;

public class RecordCompetition {
  
  
  private String name;
  private AgeGroup ageGroup;
  private int timeInSeconds;
  private LocalDate date;
  private int placement;
  private String convention;
  
  // String name, int timeInSeconds, LocalDate date, int placement, String convention
  
  // String name, AgeGroup, int timeInSeconds, LocalDate date, int placement, String convention
  public RecordCompetition(String name, AgeGroup ageGroup, int timeInSeconds, LocalDate date, int placement, String convention) {
   setName(name);
   setAgeGroup(ageGroup);
   setTimeInSeconds(timeInSeconds);
   setDate(date);
   setPlacement(placement);
   setConvention(convention);
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setAgeGroup(AgeGroup ageGroup) {
    this.ageGroup = ageGroup;
  }
  
  public AgeGroup getAgeGroup() {
    return ageGroup;
  }
  
  public int getTimeInSeconds() {
    return timeInSeconds;
  }
  
  public void setTimeInSeconds(int timeInSeconds) {
    this.timeInSeconds = timeInSeconds;
  }
  
  public LocalDate getDate() {
    return date;
  }
  
  public void setDate(LocalDate date) {
    this.date = date;
  }
  
  public String getConvention() {
    return convention;
  }
  
  public void setConvention(String convention) {
    this.convention = convention;
  }
  
  public int getPlacement() {
    return placement;
  }
  
  public void setPlacement(int placement) {
    this.placement = placement;
  }
}
