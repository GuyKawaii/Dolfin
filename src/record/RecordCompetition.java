package record;

import enums.AgeGroup;

import java.time.LocalDate;

public class RecordCompetition extends Record implements Comparable<RecordCompetition> {
  private int placement;
  private String convention;
  
  public RecordCompetition(String name, AgeGroup ageGroup, int timeInSeconds, LocalDate date, int placement, String convention) {
    super(name, ageGroup, timeInSeconds, date);
    setPlacement(placement);
    setConvention(convention);
  }
  
  public String getConvention() {
    return convention;
  }
  
  public void setConvention(String convention) {
    this.convention = convention;
  }
  
  public int getPlacement() {
    return placement; // TODO: 22/05/2022 til sidst så fjern alle ubrugte gettere og settere
  }
  
  public void setPlacement(int placement) {
    this.placement = placement;
  }
  
  @Override
  public String toString() {
    return String.format("%s, placement: %s, %s", super.toString(), placement, convention);
  }
  
  @Override
  public int compareTo(RecordCompetition other) {
    return this.placement - other.getPlacement();
  }
}
