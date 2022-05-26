package filehandling;

import enums.AgeGroup;
import enums.Discipline;
import other.*;
import record.*;

import java.io.File;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static enums.AgeGroup.*;


public class FileHandlingTeam {
  private final String databaseFolder = "database/";
  private final String trainerFile = "trainer.csv";
  // record-files
  private final String crawlTrainingFile = "crawlTraining.csv";
  private final String crawlCompetitionFile = "crawlCompetition.csv";
  private final String backCrawlTrainingFile = "backCrawlTraining.csv";
  private final String backCrawlCompetitionFile = "backCrawlCompetition.csv";
  private final String breastStrokeTrainingFile = "breastStrokeTrainingFile.csv";
  private final String breastStrokeCompetitionFile = "breastStrokeCompetition.csv";
  private final String butterflyTrainingFile = "butterflyTrainingFile.csv";
  private final String butterflyCompetitionFile = "butterflyCompetitionFile.csv";
  
  // whole team // todo not implemented or used remove later?
  public void saveTeam(Team team) {
    
    // Tager en bestemt arrayliste af en discplin - den skal ligge den ind i filen som passer til den (16 gange)
    //
    // save team.getCrawlTraining() file crawltraning team.getAgeGrup
    try {
      PrintStream file = new PrintStream(databaseFolder + team.getAgeGroup() + crawlTrainingFile);
      for (RecordTraining recordTraining : team.getCrawlTraining()) {
        file.printf("%s;%s;%s;%s;%s\n",
            team.getAgeGroup(),
            team.getTrainer(),
            recordTraining.getName(),
            recordTraining.getTimeInSeconds(),
            recordTraining.getDate());
      }
      file.close();
    } catch (Exception e) {
      System.err.println(e);
    }
    // file naming -> ageGroup + training discipline
    // from array -> file
  }
  
  // training records
  public void loadTrainingRecords(TeamRecords teamRecords) {
    // load all disciplines
    AgeGroup ageGroup = teamRecords.getAgeGroup();
    teamRecords.setCrawlTraining(loadRecordTrainingFile(databaseFolder + ageGroup + crawlTrainingFile));
    teamRecords.setBackCrawlTraining(loadRecordTrainingFile(databaseFolder + ageGroup + backCrawlTrainingFile));
    teamRecords.setBreastStrokeTraining(loadRecordTrainingFile(databaseFolder + ageGroup + breastStrokeTrainingFile));
    teamRecords.setButterflyTraining(loadRecordTrainingFile(databaseFolder + ageGroup + butterflyTrainingFile));
  }
  
  public void saveRecordTraining(TeamRecords teamRecords, Discipline discipline) {
    switch (discipline) {
      case CRAWL -> writeToFileTraining(teamRecords.getCrawlTraining(), (teamRecords.getAgeGroup() + crawlTrainingFile));
      case BACK_CRAWL -> writeToFileTraining(teamRecords.getBackCrawlTraining(), (teamRecords.getAgeGroup() + backCrawlTrainingFile));
      case BREAST_STROKE ->
          writeToFileTraining(teamRecords.getBreastStrokeTraining(), (teamRecords.getAgeGroup() + breastStrokeTrainingFile));
      case BUTTERFLY -> writeToFileTraining(teamRecords.getButterflyTraining(), (teamRecords.getAgeGroup() + butterflyTrainingFile));
    }
  }
  
  public ArrayList<RecordTraining> loadRecordTrainingFile(String filePath) {
    ArrayList<RecordTraining> recordTrainings = new ArrayList<>();
    
    try {
      // open file
      Scanner fileScanner = new Scanner(new File(filePath));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
        
        // parameters
        int ID = Integer.parseInt(token.next());
        String name = token.next();
        AgeGroup ageGroup = setAgeGroup(token.next());
        int timeInSeconds = token.nextInt();
        LocalDate date = LocalDate.parse(token.next());
        
        // create
        recordTrainings.add(new RecordTraining(ID, name, ageGroup, timeInSeconds, date));
      }
      
      // release file
      fileScanner.close();
      
    } catch (Exception e) {
      // create empty file if not found
      createEmptyFile(filePath);
      
      // empty
      return recordTrainings;
    }
    
    return recordTrainings;
  }
  
  public void writeToFileTraining(ArrayList<RecordTraining> recordTrainings, String discipline) {
    try {
      PrintStream write = new PrintStream(databaseFolder + discipline);
      for (RecordTraining recordTraining : recordTrainings) {
        write.printf("%s;%s;%s;%s;%s\n",
            recordTraining.getID(),
            recordTraining.getName(),
            recordTraining.getAgeGroup(),
            recordTraining.getTimeInSeconds(),
            recordTraining.getDate());
      }
      write.close();
      
    } catch (Exception e) {
      System.err.println(e);
    }
  }
  
  // competitive records
  public void loadCompetitiveRecords(TeamRecords teamRecords) {
    // load all disciplines
    AgeGroup ageGroup = teamRecords.getAgeGroup();
    teamRecords.setCrawlCompetition(loadRecordCompetitiveFile(databaseFolder + ageGroup + crawlCompetitionFile));
    teamRecords.setBackCrawlCompetition(loadRecordCompetitiveFile(databaseFolder + ageGroup + backCrawlCompetitionFile));
    teamRecords.setBreastStrokeCompetition(loadRecordCompetitiveFile(databaseFolder + ageGroup + breastStrokeCompetitionFile));
    teamRecords.setButterflyCompetition(loadRecordCompetitiveFile(databaseFolder + ageGroup + butterflyCompetitionFile));
  }
  
  public void saveRecordCompetition(TeamRecords teamRecords, Discipline discipline) {
    switch (discipline) {
      case CRAWL -> writeToFileCompetition(teamRecords.getCrawlCompetition(), (teamRecords.getAgeGroup() + crawlCompetitionFile));
      case BACK_CRAWL ->
          writeToFileCompetition(teamRecords.getBackCrawlCompetition(), (teamRecords.getAgeGroup() + backCrawlCompetitionFile));
      case BREAST_STROKE ->
          writeToFileCompetition(teamRecords.getBreastStrokeCompetition(), (teamRecords.getAgeGroup() + breastStrokeCompetitionFile));
      case BUTTERFLY ->
          writeToFileCompetition(teamRecords.getButterflyCompetition(), (teamRecords.getAgeGroup() + butterflyCompetitionFile));
    }
  }
  
  public ArrayList<RecordCompetition> loadRecordCompetitiveFile(String filePath) {
    ArrayList<RecordCompetition> competitiveRecords = new ArrayList<>();
    
    try {
      // open file
      Scanner fileScanner = new Scanner(new File(filePath));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
        
        // parameters
        int ID = Integer.parseInt(token.next());
        String name = token.next();
        AgeGroup ageGroup = setAgeGroup(token.next()); // todo gives error when reading file
        int timeInSeconds = token.nextInt();
        LocalDate date = LocalDate.parse(token.next());
        int placement = Integer.parseInt(token.next());
        String convention = token.next();
        
        // create
        competitiveRecords.add(new RecordCompetition(ID, name, ageGroup, timeInSeconds, date, placement, convention));
      }
      
      // release file
      fileScanner.close();
      
    } catch (Exception e) {
      // create empty file if not found
      createEmptyFile(filePath);
      
      // empty
      return competitiveRecords;
    }
    
    return competitiveRecords;
  }
  
  public void writeToFileCompetition(ArrayList<RecordCompetition> recordCompetitions, String discipline) {
    try {
      PrintStream write = new PrintStream(databaseFolder + discipline);
      for (RecordCompetition recordCompetition : recordCompetitions) {
        write.printf("%s;%s;%s;%s;%s;%s;%s\n",
            recordCompetition.getID(),
            recordCompetition.getName(),
            recordCompetition.getAgeGroup(),
            recordCompetition.getTimeInSeconds(),
            recordCompetition.getDate(),
            recordCompetition.getPlacement(),
            recordCompetition.getConvention());
      }
      write.close();
      
    } catch (Exception e) {
      System.err.println(e);
    }
  }
  
  // trainer
  public boolean saveTrainer(TeamRecords teamRecords) {
    String filePath = (databaseFolder + teamRecords.getAgeGroup() + trainerFile);
    
    try {
      PrintStream write = new PrintStream(filePath);
      
      write.printf("%s\n",
          teamRecords.getTrainer().getName());
      
      write.close();
      return true;
      
    } catch (Exception e) {
      System.err.println(e);
      return false;
    }
    
  }
  
  public void loadTrainerFile(TeamRecords teamRecords) {
    String filePath = (databaseFolder + teamRecords.getAgeGroup() + trainerFile);
    Trainer trainer = null;
    
    try {
      // open file
      Scanner fileScanner = new Scanner(new File(filePath));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
        
        // parameters
        String name = token.next();
        
        // create
        trainer = new Trainer(name);
      }
      
      // release file
      fileScanner.close();
      
    } catch (Exception e) {
      // create empty file if not found
      createEmptyFile(filePath);
      // default
      teamRecords.setTrainer(new Trainer(String.format("%s TRAINER", teamRecords.getAgeGroup())));
    }
    
    teamRecords.setTrainer(trainer);
  }
  
  // helper methods
  private AgeGroup setAgeGroup(String ageGroupText) {
    if (ageGroupText.equals("JUNIOR"))
      return JUNIOR;
    else if (ageGroupText.equals("SENIOR"))
      return SENIOR;
    else
      return null;
  }
  
  public void createEmptyFile(String filePath) { // todo make it write TRAINER for TRAINER LOAD
    try {
      File newFile = new File(filePath);
      if (newFile.createNewFile()) System.out.println("File created: " + newFile.getName());
      else System.out.println("File already exists.");
    } catch (Exception ee) {
      System.out.println("An error occurred.");
      ee.printStackTrace();
    }
  }
  
}
