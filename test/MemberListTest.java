import other.Team;
import other.Trainer;
import enums.Discipline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static enums.AgeGroup.JUNIOR;
import static org.junit.jupiter.api.Assertions.*;

class MemberListTest {
  
  @Test
  void topFiveUnderFiveNoTraineesTest() {
    Team team = new Team(JUNIOR, new Trainer("Bubba"));
    
    assertEquals(0, team.topFiveForDiscipline(Discipline.CRAWL).size());
  }
  
  @Test
  void topFiveUnderFiveTraineesTest() {
    Team team = new Team(JUNIOR, new Trainer("Bubba"));
    // 1
    team.createTrainingRecord(Discipline.CRAWL, "name1", 10, LocalDate.now());
    // 2
    team.createTrainingRecord(Discipline.BACK_CRAWL, "name1", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.BACK_CRAWL, "name2", 10, LocalDate.now());
    // 3
    team.createTrainingRecord(Discipline.BREAST_STROKE, "name2", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.BREAST_STROKE, "name2", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.BREAST_STROKE, "name2", 10, LocalDate.now());
    // 4
    team.createTrainingRecord(Discipline.BUTTERFLY, "name2", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.BUTTERFLY, "name2", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.BUTTERFLY, "name2", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.BUTTERFLY, "name2", 10, LocalDate.now());
    
    // top five less than 5 trainees
    assertEquals(1, team.topFiveForDiscipline(Discipline.CRAWL).size());
    assertEquals(2, team.topFiveForDiscipline(Discipline.BACK_CRAWL).size());
    assertEquals(3, team.topFiveForDiscipline(Discipline.BREAST_STROKE).size());
    assertEquals(4, team.topFiveForDiscipline(Discipline.BUTTERFLY).size());
  }
  
  @Test
    // get 5 exactly
  void fiveTraineesTest() {
    Team team = new Team(JUNIOR, new Trainer("Bubba"));
    team.createTrainingRecord(Discipline.CRAWL, "name", 10, LocalDate.now());
    
    // top five less than 5 trainees
    assertEquals(1, team.topFiveForDiscipline(Discipline.CRAWL).size());
  }
  
  @Test
  void aboveFiveTraineesTest() {
    Team team = new Team(JUNIOR, new Trainer("Bubba"));
    team.createTrainingRecord(Discipline.CRAWL, "name1", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.CRAWL, "name2", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.CRAWL, "name3", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.CRAWL, "name4", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.CRAWL, "name5", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.CRAWL, "name6", 10, LocalDate.now());
    team.createTrainingRecord(Discipline.CRAWL, "name7", 10, LocalDate.now());
    
    assertEquals(5, team.topFiveForDiscipline(Discipline.CRAWL).size());
  }
  
  
  void getMotionist() {
  }
  
  @Test
  void getCompetitive() {
  }
  
  @Test
  void getMember() {
  }
  
  @Test
  void addMotionist() {
  }
  
  @Test
  void addCompetitive() {
  }
  
  @Test
  void removeMotionist() {
  }
  
  @Test
  void removeCompetitive() {
  }
  
  @Test
  void removeMember() {
  }
  
  @Test
  void getMemberAmount() {
  }
  
  @Test
  void getMotionistAmount() {
  }
  
  @Test
  void getCompetitiveAmount() {
  }
  
  @Test
  void getCompetitors() {
  }
  
  @Test
  void getMotionists() {
  }
  
  @Test
  void getMembers() {
  }
  
  @Test
  void testToString() {
  }
}