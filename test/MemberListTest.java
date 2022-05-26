import org.junit.jupiter.api.BeforeEach;
import other.TeamRecords;
import other.Trainer;
import enums.Discipline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static enums.AgeGroup.JUNIOR;
import static org.junit.jupiter.api.Assertions.*;

class MemberListTest {
  
  @Test
  void topFiveUnderFiveNoTraineesTest() {
    TeamRecords teamRecords = new TeamRecords(JUNIOR, new Trainer("Bubba"));
    assertEquals(0, teamRecords.topFiveForDiscipline(Discipline.CRAWL).size());
  }
  
  @Test
  void topFiveUnderFiveTraineesTest() {
    TeamRecords teamRecords = new TeamRecords(JUNIOR, new Trainer("Bubba"));
    // 1
    teamRecords.createTrainingRecord(Discipline.CRAWL, 1, "1", 10, LocalDate.now());
    // 2
    teamRecords.createTrainingRecord(Discipline.BACK_CRAWL, 1, "1", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.BACK_CRAWL, 2, "2", 10, LocalDate.now());
    // 3
    teamRecords.createTrainingRecord(Discipline.BREAST_STROKE, 1, "1", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.BREAST_STROKE, 2, "2", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.BREAST_STROKE, 3, "3", 10, LocalDate.now());
    // 4
    teamRecords.createTrainingRecord(Discipline.BUTTERFLY, 1, "1", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.BUTTERFLY, 2, "2", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.BUTTERFLY, 3, "3", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.BUTTERFLY, 4, "4", 10, LocalDate.now());
    
    // top five less than 5 trainees
    assertEquals(1, teamRecords.topFiveForDiscipline(Discipline.CRAWL).size());
    assertEquals(2, teamRecords.topFiveForDiscipline(Discipline.BACK_CRAWL).size());
    assertEquals(3, teamRecords.topFiveForDiscipline(Discipline.BREAST_STROKE).size());
    assertEquals(4, teamRecords.topFiveForDiscipline(Discipline.BUTTERFLY).size());
  }
  
  @Test
  void topFiveTraineesTest() {
    TeamRecords teamRecords = new TeamRecords(JUNIOR, new Trainer("Bubba"));
    teamRecords.createTrainingRecord(Discipline.CRAWL, 1, "1", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 2, "2", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 3, "3", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 4, "4", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 5, "5", 10, LocalDate.now());
    
    // get 5 exactly
    assertEquals(5, teamRecords.topFiveForDiscipline(Discipline.CRAWL).size());
  }
  
  @Test
  void aboveFiveTraineesTest() {
    TeamRecords teamRecords = new TeamRecords(JUNIOR, new Trainer("Bubba"));
    teamRecords.createTrainingRecord(Discipline.CRAWL, 1, "1", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 2, "2", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 3, "3", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 4, "4", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 5, "5", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 6, "6", 10, LocalDate.now());
    teamRecords.createTrainingRecord(Discipline.CRAWL, 7, "7", 10, LocalDate.now());
    
    assertEquals(5, teamRecords.topFiveForDiscipline(Discipline.CRAWL).size());
  }
  
}