package filehandling;

import enums.MembershipStatus;
import member.Motionist;

import java.io.File;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import member.Competitive;
import record.RecordTraining;

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
