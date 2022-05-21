//import record.RecordTraining;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Locale;
//import java.util.Scanner;
//
//public class Disciplin_LEGACY {
//  private ArrayList<RecordTraining> entries;
//  private String name;
//
//  public Disciplin_LEGACY(String name) {
//    entries = new ArrayList<>();
//    this.name = name;
//
//    loadDataBase();
//  }
//
//  public void loadDataBase() {
//    try {
//      // open file
//      Scanner fileScanner = new Scanner(new File(name + ".csv"));
//
//      // clear in-use data
//      entries.clear();
//
//      while (fileScanner.hasNextLine()) {
//        String line = fileScanner.nextLine();
//        Scanner token = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
//
//        //csv columns
//        String name = token.next();
//        LocalDateTime contestantAge = LocalDateTime.parse(token.next());
//        int timeInSeconds = token.nextInt();
//        LocalDate date = LocalDateTime.parse(token.next());
//        String convention = token.next();
//        entries.add(new RecordTraining(name, contestantAge, timeInSeconds, date, convention));
//      }
//      // release file
//      fileScanner.close();
//
//    } catch (FileNotFoundException FNF) {
//      System.err.println(String.format("COLD NOT FIND \"%s.csv\" FILE - has now been created", name)); // todo throw up higher maybe
//
//      // create empty file
//      try {
//        File file = new File(name + ".csv");
//        file.createNewFile();
//      } catch (IOException IO) {
//        System.err.println("CANT CREATE FILE");
//      }
//
//    }
//  }
//
//  public void saveDataBase() {
//    // overwrite old database-file
//    try {
//      PrintStream file = new PrintStream(name + ".csv");
//
//      for (RecordTraining entryTraning : entries) {
//        file.printf("%s;%s;%s;%s;%s\n",
//            entryTraning.getName(),
//            entryTraning.getContestantAge(),
//            entryTraning.getTimeInSeconds(),
//            entryTraning.getDate(),
//            entryTraning.getConvention());
//      }
//      // release file
//      file.close();
//
//    } catch (FileNotFoundException e) {
//      System.err.println(e);
//    }
//
//  }
//
//
//}
