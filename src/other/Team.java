package other;

import enums.Discipline;
import record.Record;
import record.RecordCompetition;
import record.RecordTraining;
import enums.AgeGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Team { //TODO maybe change name to TeamRecords?
  private AgeGroup ageGroup;
  private Trainer trainer;

  private ArrayList<Record> allRecords;
  private ArrayList<RecordTraining> crawlTraining;
  private ArrayList<RecordCompetition> crawlCompetition;
  private ArrayList<RecordTraining> backCrawlTraining;
  private ArrayList<RecordCompetition> backCrawlCompetition;
  private ArrayList<RecordTraining> breastStrokeTraining;
  private ArrayList<RecordCompetition> breastStrokeCompetition;
  private ArrayList<RecordTraining> butterflyTraining;
  private ArrayList<RecordCompetition> butterflyCompetition;
  
  public Team(AgeGroup ageGroup, Trainer trainer) {
    this.ageGroup = ageGroup;
    this.trainer = trainer;
    
    // discipline lists
    crawlTraining = new ArrayList();
    crawlCompetition = new ArrayList();
    backCrawlTraining = new ArrayList();
    backCrawlCompetition = new ArrayList();
    breastStrokeTraining = new ArrayList();
    breastStrokeCompetition = new ArrayList();
    butterflyTraining = new ArrayList();
    butterflyCompetition = new ArrayList();
  }
  

  
  public AgeGroup getAgeGroup() {
    return ageGroup;
  }

  public Trainer getTrainer() {return trainer;}
  
  public boolean createTrainingRecord(Discipline discipline, String name, int timeInSeconds, LocalDate date) {
    // only create new if time is better
    switch (discipline) {
      case CRAWL -> {
        return replaceTrainingRecord(crawlTraining, name, timeInSeconds, date);
      }
      case BACK_CRAWL -> {
        return replaceTrainingRecord(backCrawlTraining, name, timeInSeconds, date);
      }
      case BREAST_STROKE -> {
        return replaceTrainingRecord(breastStrokeTraining, name, timeInSeconds, date);
      }
      case BUTTERFLY -> {
        return replaceTrainingRecord(butterflyTraining, name, timeInSeconds, date);
      }
      default -> {
        return false;// todo maybe add boolean for success
      }
    }
  }
  
  public void createCompetitiveRecord(Discipline discipline, String name, int timeInSeconds, LocalDate date, int placement, String convention) {
    switch (discipline) {
      case CRAWL ->
          crawlCompetition.add(new RecordCompetition(name, ageGroup, timeInSeconds, date, placement, convention));

      case BACK_CRAWL ->
          backCrawlCompetition.add(new RecordCompetition(name, ageGroup, timeInSeconds, date, placement, convention));
      case BREAST_STROKE ->
          breastStrokeCompetition.add(new RecordCompetition(name, ageGroup, timeInSeconds, date, placement, convention));
      case BUTTERFLY ->
          butterflyCompetition.add(new RecordCompetition(name, ageGroup, timeInSeconds, date, placement, convention));
      default -> {
        // todo maybe add boolean for success
      }
    }
  }
  
  public ArrayList<RecordTraining> topFiveForDiscipline(Discipline discipline) {
    switch (discipline) {
      case CRAWL -> {
        return findTopFive(crawlTraining);
      }
      case BACK_CRAWL -> {
        return findTopFive(backCrawlTraining);
      }
      case BREAST_STROKE -> {
        return findTopFive(breastStrokeTraining);
      }
      case BUTTERFLY -> {
        return findTopFive(butterflyTraining);
      }
      default -> {
        return null;
      }
    }
  }
  
  // helper methods
  private ArrayList<RecordTraining> findTopFive(ArrayList<RecordTraining> disciplines) {
    Collections.sort(disciplines);
    int records = disciplines.size();
    
    if (records <= 5) return disciplines;
    else return new ArrayList<>(disciplines.subList(0, 5));
  }
  
  private boolean replaceTrainingRecord(ArrayList<RecordTraining> trainingRecords, String name, int timeInSeconds, LocalDate date) {
    // only create new if time is better
    RecordTraining oldRecord = null;
    
    // find
    for (RecordTraining recordTraining : trainingRecords) {
      if (recordTraining.getName() == name) {
        oldRecord = recordTraining;
      }
    }
    
    if (oldRecord == null) {
      trainingRecords.add(new RecordTraining(name, ageGroup, timeInSeconds, date));
      return true;
      
    } else if (oldRecord.getTimeInSeconds() > timeInSeconds) {
      // removeIf is used to remove an object from a list while iterating through it
      trainingRecords.removeIf(n -> (n.getName().equals(name) && n.getTimeInSeconds() > timeInSeconds));
      
      trainingRecords.add(new RecordTraining(name, ageGroup, timeInSeconds, date));
      return true;
    }
    
    return false;
  }
  
  public ArrayList<RecordTraining> getCrawlTraining() {
    return crawlTraining;
  }

  public void setCrawlTraining(ArrayList<RecordTraining> crawlTraining) {
    this.crawlTraining = crawlTraining;
  }

  public ArrayList<RecordCompetition> getCrawlCompetition() {
    return crawlCompetition;
  }

  public void setCrawlCompetition(ArrayList<RecordCompetition> crawlCompetition) {
    this.crawlCompetition = crawlCompetition;
  }

  public ArrayList<RecordTraining> getBackCrawlTraining() {
    return backCrawlTraining;
  }

  public void setBackCrawlTraining(ArrayList<RecordTraining> backCrawlTraining) {
    this.backCrawlTraining = backCrawlTraining;
  }
  
  public ArrayList<RecordCompetition> getBackCrawlCompetition() {
    return backCrawlCompetition;
  }
  
  public ArrayList<RecordTraining> getBreastStrokeTraining() {
    return breastStrokeTraining;
  }
  
  public ArrayList<RecordCompetition> getBreastStrokeCompetition() {
    return breastStrokeCompetition;
  }
  
  public ArrayList<RecordTraining> getButterflyTraining() {
    return butterflyTraining;
  }

  public void setButterflyTraining(ArrayList<RecordTraining> butterflyTraining) {
    this.butterflyTraining = butterflyTraining;
  }

  public ArrayList<RecordCompetition> getButterflyCompetition() {
    return butterflyCompetition;
  }

  public void setButterflyCompetition(ArrayList<RecordCompetition> butterflyCompetition) {
    this.butterflyCompetition = butterflyCompetition;
  }


}
