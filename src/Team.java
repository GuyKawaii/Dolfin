//import not_implemented

import record.RecordCompetition;
import record.RecordTraining;
import enums.AgeGroup;

import java.util.ArrayList;

public class Team {
  private AgeGroup ageGroup;
  private ArrayList<RecordTraining> crawlTraining;
  private ArrayList<RecordCompetition> crawlCompetition;
  private ArrayList<RecordTraining> backCrawlTraining;
  private ArrayList<RecordCompetition> backCrawlCompetition;
  private ArrayList<RecordTraining> breastStrokeTraining;
  private ArrayList<RecordCompetition> breastStrokeCompetition;
  private ArrayList<RecordTraining> butterflyTraining;
  private ArrayList<RecordCompetition> butterflyCompetition;
  
  public Team(AgeGroup ageGroup) {
    this.ageGroup = ageGroup;
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
}
