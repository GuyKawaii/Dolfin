package record;

import enums.AgeGroup;

import java.time.LocalDate;

public class RecordTraining extends Record implements Comparable<RecordTraining> {
  
  
  public RecordTraining(String name, AgeGroup ageGroup, int timeInSeconds, LocalDate date) {
    super(name, ageGroup, timeInSeconds, date);
  }


  @Override
  public int compareTo(RecordTraining other) {
    return this.getTimeInSeconds() - other.getTimeInSeconds();
  }

  @Override
  public boolean equals(Object obj) {
    RecordTraining other = (RecordTraining) obj;

    return this.getName().equals(other.getName()) &&
           this.getAgeGroup() == other.getAgeGroup() &&
           this.getTimeInSeconds() == other.getTimeInSeconds() &&
           this.getDate().equals(other.getDate());
  }
  
}

