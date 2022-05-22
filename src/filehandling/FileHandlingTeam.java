package filehandling;

import enums.AgeGroup;
import member.Competitive;
import other.*;
import record.RecordCompetition;
import record.*;

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

    public void saveRecord(Trainer trainer, ArrayList<record.Record> records) {

    }

    public void saveJuniorRecord(ArrayList<RecordTraining> records) {

    }

    public void saveTeam(Team team) {
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
