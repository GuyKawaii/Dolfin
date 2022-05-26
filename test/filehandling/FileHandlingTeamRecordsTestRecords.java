package filehandling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import record.RecordTraining;

import java.util.ArrayList;

class FileHandlingTeamRecordsTestRecords {
  FileHandlingTeam fileHandlingTeam;
  
  @BeforeEach
  public void setup() {
    fileHandlingTeam = new  FileHandlingTeam();
  }
  
  
  @Test
  void readRecordTesting() {
    final String databaseFolder = "database/";
    final String crawlTrainingFile = "crawlTraining.csv";
    final String ageGroup = "UNIOR";
    
    
    ArrayList<RecordTraining> recordTrainings = new ArrayList<>();
    recordTrainings = fileHandlingTeam.loadRecordTrainingFile(databaseFolder + ageGroup + crawlTrainingFile);
  
    System.out.println(recordTrainings);
  }
  
}