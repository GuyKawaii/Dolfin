package filehandling;

import enums.Discipline;
import other.*;
import record.*;

import java.io.PrintStream;
import java.util.ArrayList;

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
      case BREAST_STROKE ->
          writeToFileTraining(team.getBreastStrokeTraining(), (team.getAgeGroup() + breastStrokeTrainingFile));
      case BUTTERFLY -> writeToFileTraining(team.getButterflyTraining(), (team.getAgeGroup() + butterflyTrainingFile));
      // TODO: 23/05/2022 add default?
    }
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
      PrintStream file = new PrintStream(databaseFolder + crawlTrainingFile);
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


  
 /*
  public ArrayList<Motionist> loadMotionists() {
    ArrayList<Motionist> motionists = new ArrayList<>();
    Motionist motionist;
    // todo add return or mutate directly?
    // todo make controller catch and deal with exception
    
    try {
      Scanner fileScanner = new Scanner(new File(databaseFolder + motionistFile));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
        
        // all parameters for motionist
        String name = token.next();
        LocalDate birthday = LocalDate.parse(token.next());
        Double restance = Double.valueOf(token.next());
        MembershipStatus membershipStatus = setMembershipStatus(token.next());
        
        // create Motionist
        motionist = new Motionist(name, birthday, membershipStatus);
        motionist.setRestance(restance);
        // add motionist
        motionists.add(motionist);
      }
      
    } catch (Exception e) {
      System.err.println(e);
      return motionists;
    }
    
    return motionists;
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
