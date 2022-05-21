package record;

import enums.AgeGroup;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RecordTrainingTest {
  
  @Test
  void comparableSmallFirst() {
    RecordTraining recordTraining1 = new RecordTraining("r1", AgeGroup.JUNIOR, 1, LocalDate.now());
    RecordTraining recordTraining2 = new RecordTraining("r2", AgeGroup.JUNIOR, 2, LocalDate.now());
    RecordTraining recordTraining3 = new RecordTraining("r2", AgeGroup.JUNIOR, 3, LocalDate.now());
    RecordTraining recordTraining4 = new RecordTraining("r2", AgeGroup.JUNIOR, 4, LocalDate.now());
    
    // small number first
    ArrayList<RecordTraining> trainingsSmallTOBig = new ArrayList<>();
    trainingsSmallTOBig.add(recordTraining1);
    trainingsSmallTOBig.add(recordTraining2);
    trainingsSmallTOBig.add(recordTraining3);
    trainingsSmallTOBig.add(recordTraining4);
    // sort
    Collections.sort(trainingsSmallTOBig);
    assertEquals(1,trainingsSmallTOBig.get(0).getTimeInSeconds());
  
    // big number first
    ArrayList<RecordTraining> trainingsBigToSmall = new ArrayList<>();
    trainingsBigToSmall.add(recordTraining4);
    trainingsBigToSmall.add(recordTraining3);
    trainingsBigToSmall.add(recordTraining2);
    trainingsBigToSmall.add(recordTraining1);
    // sort
    Collections.sort(trainingsBigToSmall);
    assertEquals(1,trainingsBigToSmall.get(0).getTimeInSeconds());
  }
  
}