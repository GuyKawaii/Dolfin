import org.junit.jupiter.api.Test;
import other.TeamRecords;
import other.Trainer;
import record.RecordTraining;

import java.time.LocalDate;
import java.util.ArrayList;

import static enums.AgeGroup.*;
import static enums.Discipline.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TeamRecordsTest {
  
  @Test
  void replaceTrainingRecord() {
    TeamRecords teamRecords = new TeamRecords(JUNIOR, new Trainer("Bubba"));
    
    // reference record fastest
    RecordTraining ref = new RecordTraining(1, "1", JUNIOR, 1, LocalDate.now());
    ArrayList<RecordTraining> reference = new ArrayList<>();
    reference.add(ref);
    
    // consecutive record creation
    teamRecords.createTrainingRecord(CRAWL, 1, "1", 2, LocalDate.now());
    teamRecords.createTrainingRecord(CRAWL, 1, "1", 1, LocalDate.now());
    
    assertEquals(reference, teamRecords.getCrawlTraining());
  }
  
  @Test
  void createTrainingRecord() {
    TeamRecords teamRecords = new TeamRecords(JUNIOR, new Trainer("Bubba"));
    
    // reference times
    RecordTraining ref = new RecordTraining(1, "1", JUNIOR, 1, LocalDate.now());
    ArrayList<RecordTraining> reference = new ArrayList<>();
    reference.add(ref);
    
    // add and overwrite old time new times
    teamRecords.createTrainingRecord(BACK_CRAWL, 1, "1", 10, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 1, "1", 5, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 1, "1", 1, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 1, "1", 5, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 1, "1", 10, LocalDate.now());
    
    assertEquals(reference, teamRecords.getBackCrawlTraining());
  }
  
  @Test
  void createCompetitiveRecord() {
    TeamRecords teamRecords = new TeamRecords(SENIOR, new Trainer("Michael"));
    teamRecords.createCompetitiveRecord(CRAWL, 1, "2", 3, LocalDate.now(), 4, "5");
    
    // assert for each parameter
    assertEquals(teamRecords.getCrawlCompetition().get(0).getID(), 1);
    assertEquals(teamRecords.getCrawlCompetition().get(0).getName(), "2");
    assertEquals(teamRecords.getCrawlCompetition().get(0).getDate(), LocalDate.now());
    assertEquals(teamRecords.getCrawlCompetition().get(0).getPlacement(), 4);
    assertEquals(teamRecords.getCrawlCompetition().get(0).getConvention(), "5");
  }
  
  @Test
  void findTopFive() {
    TeamRecords teamRecords = new TeamRecords(JUNIOR, new Trainer("Bubba"));
    
    // create reference top five
    ArrayList<RecordTraining> referenceTopFive = new ArrayList<>();
    RecordTraining nr1 = new RecordTraining(1, "1", JUNIOR, 1, LocalDate.now());
    RecordTraining nr2 = new RecordTraining(2, "2", JUNIOR, 2, LocalDate.now());
    RecordTraining nr3 = new RecordTraining(3, "3", JUNIOR, 3, LocalDate.now());
    RecordTraining nr4 = new RecordTraining(4, "4", JUNIOR, 4, LocalDate.now());
    RecordTraining nr5 = new RecordTraining(5, "5", JUNIOR, 5, LocalDate.now());
    referenceTopFive.add(nr1);
    referenceTopFive.add(nr2);
    referenceTopFive.add(nr3);
    referenceTopFive.add(nr4);
    referenceTopFive.add(nr5);
    
    // actual top five
    teamRecords.createTrainingRecord(BACK_CRAWL, 7, "7", 7, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 6, "6", 6, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 5, "5", 5, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 4, "4", 4, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 3, "3", 3, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 2, "2", 2, LocalDate.now());
    teamRecords.createTrainingRecord(BACK_CRAWL, 1, "1", 1, LocalDate.now());
    
    assertEquals(referenceTopFive, teamRecords.topFiveForDiscipline(BACK_CRAWL));
  }
}