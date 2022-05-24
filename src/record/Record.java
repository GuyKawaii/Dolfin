package record;

import enums.AgeGroup;

import java.time.LocalDate;

  public class Record {
    private String name;
    private AgeGroup ageGroup;
    private int timeInSeconds;
    private LocalDate date;
    
    public Record(String name, AgeGroup ageGroup, int timeInSeconds, LocalDate date) {
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
    public String toString() {
      return String.format("%s, %s, time in seconds: %s, %s",
          name,
          ageGroup.getString(),
          timeInSeconds,
          date);
    }
}
