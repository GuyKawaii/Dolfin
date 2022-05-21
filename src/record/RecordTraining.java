package record;

import enums.AgeGroup;

import java.time.LocalDate;

public class RecordTraining implements Comparable<RecordTraining> {
  private String name;
  private AgeGroup ageGroup;
  private int timeInSeconds;
  private LocalDate date;
  
  // String name, int timeInSeconds, LocalDate date, int placement, String convention
  public RecordTraining(String name, AgeGroup ageGroup, int timeInSeconds, LocalDate date) {
    setName(name);
    setAgeGroup(ageGroup);
    setTimeInSeconds(timeInSeconds);
    setDate(date);
  }
  
  public void setAgeGroup(AgeGroup ageGroup) {
    this.ageGroup = ageGroup;
  }
  
  public AgeGroup getAgeGroup() {
    return ageGroup;
  }
  
  public void setDate(LocalDate date) {
    this.date = date;
  }
  
  public LocalDate getDate() {
    return date;
  }
  
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public void setTimeInSeconds(int timeInSeconds) {
    this.timeInSeconds = timeInSeconds;
  }
  
  public int getTimeInSeconds() {
    return timeInSeconds;
  }
  
  @Override
  public boolean equals(Object obj) {
    RecordTraining other = (RecordTraining) obj;
    
    return this.name.equals(other.name) &&
           this.ageGroup == other.ageGroup &&
           this.timeInSeconds == other.timeInSeconds &&
           this.date.equals(other.date);
  }
  
  @Override
  public int compareTo(RecordTraining other) {
    return this.timeInSeconds - other.timeInSeconds;
  }
  
  @Override
  public String toString() {
    return String.format("%s %s %s %s",
        name,
        ageGroup.getAgeBracket(),
        timeInSeconds,
        date);
  }
}

