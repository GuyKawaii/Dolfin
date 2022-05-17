package member;

import enums.AgeGroup;
import org.junit.jupiter.api.Test;

import static enums.AgeGroup.*;
import static enums.MembershipStatus.*;
import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
  
  @Test
  public void testContingentPassive() {
    MemberMotionist motionistJunior = new member.MemberMotionist("Junior1", now().minusYears(17), PASSIVE);
    MemberMotionist motionistSenior = new member.MemberMotionist("Senior1", now().minusYears(18), PASSIVE);
    MemberMotionist motionistSeniorSixtyPlus = new member.MemberMotionist("Senior Plus1", now().minusYears(61), PASSIVE);
    
    MemberCompetitive competitiveJunior = new member.MemberCompetitive("Junior2", now().minusYears(17), PASSIVE);
    MemberCompetitive competitiveSenior = new member.MemberCompetitive("Senior2", now().minusYears(18), PASSIVE);
    MemberCompetitive competitiveSeniorSixtyPuls = new member.MemberCompetitive("Senior Plus2", now().minusYears(61), PASSIVE);
    
    // motionist
    assertEquals(500, motionistJunior.getContingent());
    assertEquals(500, motionistSenior.getContingent());
    assertEquals(500, motionistSeniorSixtyPlus.getContingent());
    
    // competitive
    assertEquals(500, competitiveJunior.getContingent());
    assertEquals(500, competitiveSenior.getContingent());
    assertEquals(500, competitiveSeniorSixtyPuls.getContingent());
  }
  
  @Test
  public void testContingentActiveJunior() {
    // motionist
    MemberMotionist motionistJunior = new member.MemberMotionist("Junior1", now().minusYears(17), ACTIVE);
    assertEquals(1000, motionistJunior.getContingent());
    
    // competitive
    MemberCompetitive competitiveJunior = new member.MemberCompetitive("Junior2", now().minusYears(17), ACTIVE);
    assertEquals(1000, competitiveJunior.getContingent());
  }
  
  @Test
  public void testContingentActiveSenior() {
    // motionist
    MemberMotionist motionistJunior = new member.MemberMotionist("Senior test1", now().minusYears(23), ACTIVE);
    assertEquals(1600, motionistJunior.getContingent());
    
    // competitive
    MemberCompetitive competitiveJunior = new member.MemberCompetitive("Senior test2", now().minusYears(24), ACTIVE);
    assertEquals(1600, competitiveJunior.getContingent());
  }
  
  @Test
  public void testContingentActiveSeniorSixtyPlus() {
    // motionist
    MemberMotionist motionistSeniorSixtyPlus = new member.MemberMotionist("Senior nr.1 60+", now().minusYears(61), ACTIVE);
    assertEquals(1600 * (1 - 0.25), motionistSeniorSixtyPlus.getContingent());
    
    // competitive
    MemberCompetitive competitiveSeniorSixtyPlus = new member.MemberCompetitive("Senior nr.2 60+", now().minusYears(61), ACTIVE);
    assertEquals(1600 * (1 - 0.25), competitiveSeniorSixtyPlus.getContingent());
  }
  
  @Test
  public void testAgeGroupJunior() {
    // motionist
    MemberMotionist motionistJunior1 = new member.MemberMotionist("Junior test nr.1", now().minusYears(17), PASSIVE);
    MemberMotionist motionistJunior2 = new member.MemberMotionist("Junior test nr.2", now().minusYears(17), ACTIVE);
    assertEquals(JUNIOR, motionistJunior1.getAgeGroup());
    assertEquals(JUNIOR, motionistJunior2.getAgeGroup());
    
    // competitive
    MemberCompetitive competitiveJunior1 = new member.MemberCompetitive("Junior test nr.1", now().minusYears(17), PASSIVE);
    MemberCompetitive competitiveJunior2 = new member.MemberCompetitive("Junior test nr.2", now().minusYears(17), ACTIVE);
    assertEquals(JUNIOR, competitiveJunior1.getAgeGroup());
    assertEquals(JUNIOR, competitiveJunior2.getAgeGroup());
  }
  
  @Test
  public void testAgeGroupSenior() {
    // motionist
    MemberMotionist motionistSenior1 = new member.MemberMotionist("Senior test nr.1", now().minusYears(26), PASSIVE);
    MemberMotionist motionistSenior2 = new member.MemberMotionist("Senior test nr.2", now().minusYears(30), ACTIVE);
    assertEquals(SENIOR, motionistSenior1.getAgeGroup());
    assertEquals(SENIOR, motionistSenior2.getAgeGroup());
    
    //competitive
    MemberCompetitive competitiveSenior1 = new member.MemberCompetitive("Senior test nr.1", now().minusYears(26), PASSIVE);
    MemberCompetitive competitiveSenior2 = new member.MemberCompetitive("Senior test nr. 2", now().minusYears(30), ACTIVE);
    assertEquals(SENIOR, competitiveSenior1.getAgeGroup());
    assertEquals(SENIOR, competitiveSenior2.getAgeGroup());
  }
  
  @Test
  public void testAgeGroupSeniorPlus() {
    // motionist
    MemberMotionist motionistSeniorPlus1 = new member.MemberMotionist("Senior plus test nr.1", now().minusYears(60), PASSIVE);
    MemberMotionist motionistSeniorPlus2 = new member.MemberMotionist("Senior plus test nr.2", now().minusYears(61), ACTIVE);
    assertEquals(SENIOR, motionistSeniorPlus1.getAgeGroup());
    assertEquals(SENIOR, motionistSeniorPlus2.getAgeGroup());
    
    //competitive
    MemberCompetitive competitiveSeniorPlus1 = new member.MemberCompetitive("Senior plus test nr.1", now().minusYears(60), PASSIVE);
    MemberCompetitive competitiveSeniorPlus2 = new member.MemberCompetitive("Senior plus test nr. 2", now().minusYears(61), ACTIVE);
    assertEquals(SENIOR, competitiveSeniorPlus1.getAgeGroup());
    assertEquals(SENIOR, competitiveSeniorPlus2.getAgeGroup());
  }
  
  
  @Test
  public void testAge() {
    // ages
    int age1= 16;
    int age2= 18;
    int age3= 60;
    
    // motionist
    MemberMotionist motionist1 = new member.MemberMotionist("nr1", now().minusYears(age1), ACTIVE);
    MemberMotionist motionist2 = new member.MemberMotionist("nr2", now().minusYears(age2), ACTIVE);
    MemberMotionist motionist3 = new member.MemberMotionist("nr3", now().minusYears(age3), ACTIVE);
    assertEquals(age1, motionist1.getAge());
    assertEquals(age2, motionist2.getAge());
    assertEquals(age3, motionist3.getAge());
  
    // competitive
    MemberCompetitive competitive1 = new member.MemberCompetitive("nr1", now().minusYears(age1), ACTIVE);
    MemberCompetitive competitive2 = new member.MemberCompetitive("nr2", now().minusYears(age2), ACTIVE);
    MemberCompetitive competitive3 = new member.MemberCompetitive("nr3", now().minusYears(age3), ACTIVE);
    assertEquals(age1, competitive1.getAge());
    assertEquals(age2, competitive2.getAge());
    assertEquals(age3, competitive3.getAge());
  }
  
  
}