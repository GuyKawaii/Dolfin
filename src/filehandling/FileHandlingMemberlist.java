package filehandling;

import enums.Discipline;
import enums.MembershipStatus;
import member.Competitive;
import member.Motionist;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static enums.Discipline.*;
import static enums.MembershipStatus.*;


public class FileHandlingMemberlist {
    private ArrayList<Motionist> motionists = new ArrayList<>();
    private ArrayList<Competitive> competitors = new ArrayList<>();

    public void setMotionists(Motionist motionist) {
        motionists.add(motionist);
    }

    public void setCompetitors(Competitive competitor) {
        competitors.add(competitor);
    }

    public ArrayList<Motionist> getMotionists() {
        return motionists;
    }

    public ArrayList<Competitive> getCompetitors() {
        return competitors;
    }

    public void addMotionistsToFile(Motionist motionist) throws FileNotFoundException {
        PrintStream output = new PrintStream("motionister.cvs");
        writeMotionist(output, motionist);
    }

    public void writeMotionist(PrintStream out, Motionist motionist) {
        out.print(motionist.getName());
        out.print(";");
        out.print(motionist.getBirthday());
        out.print(";");
        out.print(motionist.getAge());
        out.print(";");
        out.print(motionist.getAgeGroup());
        out.print(";");
        out.print(motionist.getMembershipStatus());
        out.print(";");
        out.print(motionist.getContingent());
        out.print(";");
        out.print(motionist.getRestance());
        out.print("\n");
    }

    public void addCompetitorsToFile(Competitive competitor) throws FileNotFoundException {
        PrintStream out = new PrintStream("konkurrenceudøvere.csv");
        writeCompetitorsToFile(out, competitor);
    }

    public void writeCompetitorsToFile(PrintStream out, Competitive competitor) {
        out.print(competitor.getName());
        out.print(";");
        out.print(competitor.getBirthday());
        out.print(";");
        out.print(competitor.getAge());
        out.print(";");
        out.print(competitor.getAgeGroup());
        out.print(";");
        out.print(competitor.getMembershipStatus());
        out.print(";");
        out.print(competitor.getContingent());
        out.print(";");
        out.print(competitor.getRestance());
        out.print(";");
        out.print(competitor.getDisciplines());
        out.print("\n");
    }

    public void loadMotionistFile() {
        Scanner fileInput = new Scanner("motionister.csv");
        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            Scanner tokenScanner = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
            String name = tokenScanner.next();
            LocalDate birthday = LocalDate.parse(tokenScanner.next());
            String membershipStatusText = tokenScanner.next();
            MembershipStatus membershipStatus = setMembershipStatus(membershipStatusText);
            double restance = tokenScanner.nextDouble();

            transferMotionistsToArraylist(name, birthday, membershipStatus, restance);
        }
        fileInput.close();
    }

    public void transferMotionistsToArraylist(String name, LocalDate birthday,
                                              MembershipStatus membershipStatus, double restance) {
        Motionist motionist = new Motionist(name, birthday, membershipStatus);
        motionist.setRestance(restance);
        motionists.add(motionist);
    }

    public MembershipStatus setMembershipStatus(String membershipStatusText) {
        if (membershipStatusText.equals("ACTIVE"))
            return ACTIVE;
        else if (membershipStatusText.equals("PASSIVE"))
            return PASSIVE;
        else
            return null;
    }

    public void loadCompetitorFile(){
        Scanner fileInput = new Scanner("konkurrenceudøvere.cvs");
        while (fileInput.hasNextLine()){
            String line = fileInput.nextLine();
            Scanner tokenScanner = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);
            String name = tokenScanner.next();
            LocalDate birthday = LocalDate.parse(tokenScanner.next());
            String membershipStatusText = tokenScanner.next();
            MembershipStatus membershipStatus = setMembershipStatus(membershipStatusText);
            double restance = tokenScanner.nextDouble();

            Discipline disciplines[] = new Discipline[4];
            for (int i = 0; i < disciplines.length; i++) {
                String disciplineText = tokenScanner.next();
                disciplines[i] = setDisciplines(disciplineText);
            } // TODO: 17/05/2022 virker det? tager scanner array som token?

            transferCompetitorsToArraylist(name, birthday, membershipStatus, restance, disciplines);

        }
        fileInput.close();
    }

    public Discipline setDisciplines(String disciplineText){
        return switch (disciplineText) {
            case "CRAWL" -> CRAWL;
            case "BACK_CRAWL" -> BACK_CRAWL;
            case "BREAST_STROKE" -> BREAST_STROKE;
            case "BUTTERFLY" -> BUTTERFLY;
            default -> null;
        };
    }

    public void transferCompetitorsToArraylist(String name, LocalDate birthday,
                                               MembershipStatus membershipStatus, double restance, Discipline[] disciplines){
        Competitive competitor = new Competitive(name, birthday, membershipStatus, disciplines);
        competitor.setRestance(restance);
        competitors.add(competitor);
    }


}
