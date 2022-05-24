package filehandling;

import enums.AgeGroup;
import enums.Discipline;
import enums.MembershipStatus;
import member.Competitive;
import other.*;
import record.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import static enums.AgeGroup.*;
import static enums.Discipline.*;
import static enums.Discipline.BUTTERFLY;
import static enums.MembershipStatus.ACTIVE;


public class FileHandlingTeam {
  private final String databaseFolder = "database/";
  private final String crawlTrainingFile = "crawlTraining.csv";
  private final String crawlCompetitionFile = "crawlCompetition.csv";
  private final String backCrawlTrainingFile = "backCrawlTraining.csv";
  private final String backCrawlCompetitionFile = "backCrawlCompetition.cvs";
  private final String breastStrokeTrainingFile = "breastStrokeTrainingFile.csv";
  private final String breastStrokeCompetitionFile = "breastStrokeCompetition.csv";
  private final String butterflyTrainingFile = "butterflyTrainingFile.csv";
  private final String butterflyCompetitionFile = "butterflyCompetitionFile.csv";


  //har fjernet trainer for nu
  public void saveRecordTraining(Team team, Discipline discipline) {
    //Discipline discipline = recordTrainings(0).getDisciple;
    // save given array to given filepath given py parameter;
    //tag disciplin fra træner input når han laver record
    //læg disciplin i record ved træner input?
    switch (discipline) {
      case CRAWL -> writeToFileTraining(team.getCrawlTraining(), (team.getAgeGroup() + crawlTrainingFile));
      case BACK_CRAWL -> writeToFileTraining(team.getBackCrawlTraining(), (team.getAgeGroup() + backCrawlTrainingFile));
      case BREAST_STROKE -> writeToFileTraining(team.getBreastStrokeTraining(), (team.getAgeGroup() + breastStrokeTrainingFile));
      case BUTTERFLY -> writeToFileTraining(team.getButterflyTraining(), (team.getAgeGroup() + butterflyTrainingFile));
      // TODO: 23/05/2022 add default?
    }
  }

  public void saveRecordCompetition(Team team, Discipline discipline) {
    switch (discipline) {
      case CRAWL -> writeToFileCompetition(team.getCrawlCompetition(), (team.getAgeGroup() + crawlCompetitionFile));
      case BACK_CRAWL -> writeToFileCompetition(team.getBackCrawlCompetition(), (team.getAgeGroup() + backCrawlCompetitionFile));
      case BREAST_STROKE -> writeToFileCompetition(team.getBreastStrokeCompetition(), (team.getAgeGroup() + breastStrokeCompetitionFile));
      case BUTTERFLY -> writeToFileCompetition(team.getButterflyCompetition(), (team.getAgeGroup() + butterflyCompetitionFile));
      // TODO: 23/05/2022 add default?
    }
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

  // todo maybe add folder for junior senior instead of filename
  // todo maybe add trainer as a parameter for each member added to the record
  public void writeToFileCompetition(ArrayList<RecordCompetition> recordCompetitions, String discipline) {
    try {
      PrintStream write = new PrintStream(databaseFolder + discipline);
      for (RecordCompetition recordCompetition : recordCompetitions) {
        write.printf("%s;%s;%s;%s\n",
            recordCompetition.getName(),
            recordCompetition.getAgeGroup(),
            recordCompetition.getTimeInSeconds(),
            recordCompetition.getDate());
      }
      write.close();

    } catch (Exception e) {
      System.err.println(e);
    }
  }


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

  public ArrayList<RecordTraining> loadRecordTrainings(String fileName) {
    ArrayList<RecordTraining> recordTrainings = new ArrayList<>();
    RecordTraining recordTraining;


    // RecordTraining(String name, AgeGroup ageGroup, int timeInSeconds, LocalDate date)
    try {
      // open file
      Scanner fileScanner = new Scanner(databaseFolder + fileName);

      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

        // parameters
        String name = token.next();
        AgeGroup ageGroup = setAgeGroup(token.next()); // todo gives error when reading file
        int timeInSeconds = token.nextInt();
        LocalDate date = LocalDate.parse(token.next());

        // create
        recordTraining = new RecordTraining(name, ageGroup, timeInSeconds, date);
        recordTrainings.add(recordTraining);
      }

      // release file
      fileScanner.close();

    } catch (Exception e) {
      System.err.println(e);
      System.out.println(recordTrainings); // <---
      System.out.println(databaseFolder + fileName);

      // create empty file
      try {
        File file = new File(databaseFolder + fileName);
        file.createNewFile();
        System.out.println(file);
      } catch(Exception ee) {
        ee.printStackTrace();
      }

      return recordTrainings;
    }

    return recordTrainings;
  }

  public void loadTrainingRecords(Team team) {
    AgeGroup ageGroup = team.getAgeGroup();
    team.setCrawlTraining(loadRecordTrainings(ageGroup + crawlTrainingFile));
    team.setCrawlTraining(loadRecordTrainings(ageGroup + backCrawlTrainingFile));
    team.setCrawlTraining(loadRecordTrainings(ageGroup + breastStrokeTrainingFile));
    team.setCrawlTraining(loadRecordTrainings(ageGroup + butterflyTrainingFile));


    /*
    // team splitter team arrays
    ArrayList<RecordTraining> recordTrainings = new ArrayList<>();
    RecordTraining recordTraining;
    AgeGroup ageGroup;
    try {
      for (int i = 0; i < 3; i++) {
        switch (i) {
          case 0 -> {
            Scanner fileScanner = new Scanner(databaseFolder + crawlTrainingFile);
            while (fileScanner.hasNextLine()) {
              String line = fileScanner.nextLine();
              Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

              String name = token.next();
              ageGroup = setAgeGroup(token.next());
              int timeInSeconds = token.nextInt();
              LocalDate date = LocalDate.parse(token.next());


              recordTraining = new RecordTraining(name, ageGroup, timeInSeconds, date);
              recordTrainings.add(recordTraining);
            }
          }
          case 1 -> {
            Scanner fileScanner = new Scanner(databaseFolder + backCrawlTrainingFile);
            while (fileScanner.hasNextLine()) {
              String line = fileScanner.nextLine();
              Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

              String name = token.next();
              ageGroup = setAgeGroup(token.next());
              int timeInSeconds = token.nextInt();
              LocalDate date = LocalDate.parse(token.next());


              recordTraining = new RecordTraining(name, ageGroup, timeInSeconds, date);
              recordTrainings.add(recordTraining);
            }
          }
          case 2 -> {
            Scanner fileScanner = new Scanner(databaseFolder + breastStrokeTrainingFile);
            while (fileScanner.hasNextLine()) {
              String line = fileScanner.nextLine();
              Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

              String name = token.next();
              ageGroup = setAgeGroup(token.next());
              int timeInSeconds = token.nextInt();
              LocalDate date = LocalDate.parse(token.next());


              recordTraining = new RecordTraining(name, ageGroup, timeInSeconds, date);
              recordTrainings.add(recordTraining);
            }
          }
          case 3 -> {
            Scanner fileScanner = new Scanner(databaseFolder + butterflyTrainingFile);
            while (fileScanner.hasNextLine()) {
              String line = fileScanner.nextLine();
              Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

              String name = token.next();
              ageGroup = setAgeGroup(token.next());
              int timeInSeconds = token.nextInt();
              LocalDate date = LocalDate.parse(token.next());


              recordTraining = new RecordTraining(name, ageGroup, timeInSeconds, date);
              recordTrainings.add(recordTraining);
            }
          }
        }
      }
    } catch (Exception e) {
      System.err.println(e);
      return recordTrainings;
    }
    return recordTrainings;*/

  }

  public AgeGroup setAgeGroup(String ageGroupText) {
    if (ageGroupText.equals("JUNIOR"))
      return JUNIOR;
    else if (ageGroupText.equals("SENIOR"))
      return SENIOR;
    else
      return null;
  }

  /*
  public ArrayList<Competitive> loadCompetitors() {
    ArrayList<Competitive> competitors = new ArrayList<>();
    Competitive competitive;
    // todo add return or mutate directly?
    // todo make controller catch and deal with exception
    ArrayList<Discipline> disciplines = new ArrayList<>();

    try {
      Scanner fileScanner = new Scanner(new File(databaseFolder + competitiveFile));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

        // all parameters for competitive
        String name = token.next();
        LocalDate birthday = LocalDate.parse(token.next());
        Double restance = Double.valueOf(token.next());
        MembershipStatus membershipStatus = setMembershipStatus(token.next());
        disciplines = setDisciplines(token.next());


        // create competitive
        competitive = new Competitive(name, birthday, membershipStatus, disciplines);
        competitive.setRestance(restance);
        // add competitive
        competitors.add(competitive);
      }

    } catch (Exception e) {
      System.err.println(e);
      return competitors;
    }

    return competitors;
  }
  */


//  public void saveTrainingRecord (ArrayList<RecordTraining>) {
//
//  }

 /*
  public boolean saveMotionists(ArrayList<Motionist> motionists) {
    // overwrite old database-file
    try {
      PrintStream file = new PrintStream(databaseFolder + motionistFile);
      for (Motionist motionist : motionists) {
        file.printf("%s;%s;%s;%s\n",
            motionist.getName(),
            motionist.getBirthday(),
            motionist.getRestance(),
            motionist.getMembershipStatus());
        
      }
      // release file
      file.close();
      return true;
    } catch (Exception e) {
      System.err.println(e);
      return false;
    }
  }
*/
}
