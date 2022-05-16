package record;

import java.time.LocalDateTime;

public class RecordCompetition {
  private String name;
  private LocalDateTime contestantAge; // todo maybe or use ENUM for age bracket
  private int timeInSeconds;
  private LocalDateTime date;
  private String convention;
  
  public RecordCompetition(String name, LocalDateTime contestantAge, int timeInSeconds, LocalDateTime date) {
    setName(name);
    setContestantAge(contestantAge);
    setTimeInSeconds(timeInSeconds);
    setDate(date);
    setConvention("NONE");
  }
  
  public RecordCompetition(String name, LocalDateTime contestantAge, int timeInSeconds, LocalDateTime date, String convention) {
    setName(name);
    setContestantAge(contestantAge);
    setTimeInSeconds(timeInSeconds);
    setDate(date);
    setConvention(convention);
  }
  
  public void setDate(LocalDateTime date) {
    this.date = date;
  }
  
  public LocalDateTime getDate() {
    return date;
  }
  
  public void setContestantAge(LocalDateTime contestantAge) {
    this.contestantAge = contestantAge;
  }
  
  public LocalDateTime getContestantAge() {
    return contestantAge;
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
  
  public void setConvention(String convention) {
    this.convention = convention;
  }
  
  public String getConvention() {
    return convention;
  }
}
