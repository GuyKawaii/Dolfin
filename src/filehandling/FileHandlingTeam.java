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
  public void loadTrainingRecords(Team team) {
    // load all disciplines
    AgeGroup ageGroup = team.getAgeGroup();
    team.setCrawlTraining(loadRecordTrainingFile(databaseFolder + ageGroup + crawlTrainingFile));
    team.setBackCrawlTraining(loadRecordTrainingFile(databaseFolder + ageGroup + backCrawlTrainingFile));
    team.setBreastStrokeTraining(loadRecordTrainingFile(databaseFolder + ageGroup + breastStrokeTrainingFile));
    team.setButterflyTraining(loadRecordTrainingFile(databaseFolder + ageGroup + butterflyTrainingFile));
  }
  
  public void saveRecordTraining(Team team, Discipline discipline) {
    switch (discipline) {
      case CRAWL -> writeToFileTraining(team.getCrawlTraining(), (team.getAgeGroup() + crawlTrainingFile));
      case BACK_CRAWL -> writeToFileTraining(team.getBackCrawlTraining(), (team.getAgeGroup() + backCrawlTrainingFile));
      case BREAST_STROKE ->
          writeToFileTraining(team.getBreastStrokeTraining(), (team.getAgeGroup() + breastStrokeTrainingFile));
      case BUTTERFLY -> writeToFileTraining(team.getButterflyTraining(), (team.getAgeGroup() + butterflyTrainingFile));
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
        String name = token.next();
        AgeGroup ageGroup = setAgeGroup(token.next()); // todo gives error when reading file
        int timeInSeconds = token.nextInt();
        LocalDate date = LocalDate.parse(token.next());
        
        // create
        recordTrainings.add(new RecordTraining(name, ageGroup, timeInSeconds, date));
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
        write.printf("%s;%s;%s;%s\n",
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
  public void loadCompetitiveRecords(Team team) {
    // load all disciplines
    AgeGroup ageGroup = team.getAgeGroup();
    team.setCrawlCompetition(loadRecordCompetitiveFile(databaseFolder + ageGroup + crawlCompetitionFile));
    team.setBackCrawlCompetition(loadRecordCompetitiveFile(databaseFolder + ageGroup + backCrawlCompetitionFile));
    team.setBreastStrokeCompetition(loadRecordCompetitiveFile(databaseFolder + ageGroup + breastStrokeCompetitionFile));
    team.setButterflyCompetition(loadRecordCompetitiveFile(databaseFolder + ageGroup + butterflyCompetitionFile));
  }
  
  public void saveRecordCompetition(Team team, Discipline discipline) {
    switch (discipline) {
      case CRAWL -> writeToFileCompetition(team.getCrawlCompetition(), (team.getAgeGroup() + crawlCompetitionFile));
      case BACK_CRAWL ->
          writeToFileCompetition(team.getBackCrawlCompetition(), (team.getAgeGroup() + backCrawlCompetitionFile));
      case BREAST_STROKE ->
          writeToFileCompetition(team.getBreastStrokeCompetition(), (team.getAgeGroup() + breastStrokeCompetitionFile));
      case BUTTERFLY ->
          writeToFileCompetition(team.getButterflyCompetition(), (team.getAgeGroup() + butterflyCompetitionFile));
      // TODO: 23/05/2022 add default?
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
        String name = token.next();
        AgeGroup ageGroup = setAgeGroup(token.next()); // todo gives error when reading file
        int timeInSeconds = token.nextInt();
        LocalDate date = LocalDate.parse(token.next());
        int placement = Integer.parseInt(token.next());
        String convention = token.next();
        
        // create
        competitiveRecords.add(new RecordCompetition(name, ageGroup, timeInSeconds, date, placement, convention));
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
        write.printf("%s;%s;%s;%s;%s;%s\n",
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
  public boolean saveTrainer(Team team) {
    String filePath = (databaseFolder + team.getAgeGroup() + trainerFile);
    
    try {
      PrintStream write = new PrintStream(filePath);
      
      write.printf("%s\n",
          team.getTrainer().getName());
      
      write.close();
      return true;
      
    } catch (Exception e) {
      System.err.println(e);
      return false;
    }
    
  }
  
  public void loadTrainerFile(Team team) {
    String filePath = (databaseFolder + team.getAgeGroup() + trainerFile);
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
    }
    
    team.setTrainer(trainer);
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
  
  public void createEmptyFile(String filePath) {
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
