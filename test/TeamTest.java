import enums.AgeGroup;
import enums.Discipline;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import record.RecordTraining;

import java.time.LocalDate;
import java.util.ArrayList;

import static enums.AgeGroup.*;
import static enums.Discipline.*;
import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

//  @BeforeAll
  
  @Test
  void replaceTrainingRecord() {
  
  
  
  
  
  }
  
  @Test
  void createTrainingRecord() {
    Team team = new Team(JUNIOR);
    
    // reference times
    RecordTraining nr1 = new RecordTraining("1", JUNIOR, 1, LocalDate.now());
    ArrayList<RecordTraining> reference = new ArrayList<>();
    reference.add(nr1);
    
    
    // add and overwrite old time new times
    team.createTrainingRecord(BACK_CRAWL, "1", 10, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "1", 5, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "1", 1, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "1", 5, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "1", 10, LocalDate.now());
    
    assertEquals(reference,team.getBackCrawlTraining());
  }
  
  @Test
  void createCompetitiveRecord() {
  }
  
  @Test
  void topFiveForDiscipline() {
  }
  
  @Test
  void findTopFive() {
    Team team = new Team(JUNIOR);
    
    // create reference top five
    ArrayList<RecordTraining> referenceTopFive = new ArrayList<>();
    RecordTraining nr1 = new RecordTraining("1", JUNIOR, 1, LocalDate.now());
    RecordTraining nr2 = new RecordTraining("2", JUNIOR, 2, LocalDate.now());
    RecordTraining nr3 = new RecordTraining("3", JUNIOR, 3, LocalDate.now());
    RecordTraining nr4 = new RecordTraining("4", JUNIOR, 4, LocalDate.now());
    RecordTraining nr5 = new RecordTraining("5", JUNIOR, 5, LocalDate.now());
    referenceTopFive.add(nr1);
    referenceTopFive.add(nr2);
    referenceTopFive.add(nr3);
    referenceTopFive.add(nr4);
    referenceTopFive.add(nr5);
    
    // actual top five
    team.createTrainingRecord(BACK_CRAWL, "7", 7, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "6", 6, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "5", 5, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "4", 4, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "3", 3, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "2", 2, LocalDate.now());
    team.createTrainingRecord(BACK_CRAWL, "1", 1, LocalDate.now());
    
    assertEquals(referenceTopFive,team.topFiveForDiscipline(BACK_CRAWL));
  }
}