package member;

import org.junit.jupiter.api.Test;

import static enums.AgeGroup.*;
import static enums.MembershipStatus.*;
import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
  
  @Test
  public void testContingentPassive() {
    Motionist motionistJunior = new Motionist(1, "Junior1", now().minusYears(17), PASSIVE);
    Motionist motionistSenior = new Motionist(2, "Senior1", now().minusYears(18), PASSIVE);
    Motionist motionistSeniorSixtyPlus = new Motionist(3,"Senior Plus1", now().minusYears(61), PASSIVE);
    
    Competitive competitiveJunior = new Competitive(3, "Junior2", now().minusYears(17), PASSIVE);
    Competitive competitiveSenior = new Competitive(4, "Senior2", now().minusYears(18), PASSIVE);
    Competitive competitiveSeniorSixtyPuls = new Competitive(5, "Senior Plus2", now().minusYears(61), PASSIVE);
    
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
    Motionist motionistJunior = new Motionist(1, "Junior1", now().minusYears(17), ACTIVE);
    assertEquals(1000, motionistJunior.getContingent());
    
    // competitive
    Competitive competitiveJunior = new Competitive(2, "Junior2", now().minusYears(17), ACTIVE);
    assertEquals(1000, competitiveJunior.getContingent());
  }
  
  @Test
  public void testContingentActiveSenior() {
    // motionist
    Motionist motionistJunior = new Motionist(1, "Senior test1", now().minusYears(23), ACTIVE);
    assertEquals(1600, motionistJunior.getContingent());
    
    // competitive
    Competitive competitiveJunior = new Competitive(2, "Senior test2", now().minusYears(24), ACTIVE);
    assertEquals(1600, competitiveJunior.getContingent());
  }
  
  @Test
  public void testContingentActiveSeniorSixtyPlus() {
    // motionist
    Motionist motionistSeniorSixtyPlus = new Motionist(1, "Senior nr.1 60+", now().minusYears(61), ACTIVE);
    assertEquals(1600 * (1 - 0.25), motionistSeniorSixtyPlus.getContingent());
    
    // competitive
    Competitive competitiveSeniorSixtyPlus = new Competitive(2, "Senior nr.2 60+", now().minusYears(61), ACTIVE);
    assertEquals(1600 * (1 - 0.25), competitiveSeniorSixtyPlus.getContingent());
  }
  
  @Test
  public void testAgeGroupJunior() {
    // motionist
    Motionist motionistJunior1 = new Motionist(1, "Junior test nr.1", now().minusYears(17), PASSIVE);
    Motionist motionistJunior2 = new Motionist(2, "Junior test nr.2", now().minusYears(17), ACTIVE);
    assertEquals(JUNIOR, motionistJunior1.getAgeGroup());
    assertEquals(JUNIOR, motionistJunior2.getAgeGroup());
    
    // competitive
    Competitive competitiveJunior1 = new Competitive(3, "Junior test nr.1", now().minusYears(17), PASSIVE);
    Competitive competitiveJunior2 = new Competitive(4, "Junior test nr.2", now().minusYears(17), ACTIVE);
    assertEquals(JUNIOR, competitiveJunior1.getAgeGroup());
    assertEquals(JUNIOR, competitiveJunior2.getAgeGroup());
  }
  
  @Test
  public void testAgeGroupSenior() {
    // motionist
    Motionist motionistSenior1 = new Motionist(1,"Senior test nr.1", now().minusYears(26), PASSIVE);
    Motionist motionistSenior2 = new Motionist(2,"Senior test nr.2", now().minusYears(30), ACTIVE);
    assertEquals(SENIOR, motionistSenior1.getAgeGroup());
    assertEquals(SENIOR, motionistSenior2.getAgeGroup());
    
    //competitive
    Competitive competitiveSenior1 = new Competitive(3,"Senior test nr.1", now().minusYears(26), PASSIVE);
    Competitive competitiveSenior2 = new Competitive(4,"Senior test nr. 2", now().minusYears(30), ACTIVE);
    assertEquals(SENIOR, competitiveSenior1.getAgeGroup());
    assertEquals(SENIOR, competitiveSenior2.getAgeGroup());
  }
  
  @Test
  public void testAgeGroupSeniorPlus() {
    // motionist
    Motionist motionistSeniorPlus1 = new Motionist(1,"Senior plus test nr.1", now().minusYears(60), PASSIVE);
    Motionist motionistSeniorPlus2 = new Motionist(2,"Senior plus test nr.2", now().minusYears(61), ACTIVE);
    assertEquals(SENIOR, motionistSeniorPlus1.getAgeGroup());
    assertEquals(SENIOR, motionistSeniorPlus2.getAgeGroup());
    
    //competitive
    Competitive competitiveSeniorPlus1 = new Competitive(1,"Senior plus test nr.1", now().minusYears(60), PASSIVE);
    Competitive competitiveSeniorPlus2 = new Competitive(2,"Senior plus test nr. 2", now().minusYears(61), ACTIVE);
    assertEquals(SENIOR, competitiveSeniorPlus1.getAgeGroup());
    assertEquals(SENIOR, competitiveSeniorPlus2.getAgeGroup());
  }
  
  
  @Test
  public void testAge() {
    // ages
    int age1 = 16;
    int age2 = 18;
    int age3 = 60;
    
    // motionist
    Motionist motionist1 = new Motionist(1,"nr1", now().minusYears(age1), ACTIVE);
    Motionist motionist2 = new Motionist(2,"nr2", now().minusYears(age2), ACTIVE);
    Motionist motionist3 = new Motionist(3,"nr3", now().minusYears(age3), ACTIVE);
    assertEquals(age1, motionist1.getAge());
    assertEquals(age2, motionist2.getAge());
    assertEquals(age3, motionist3.getAge());
    
    // competitive
    Competitive competitive1 = new Competitive(1,"nr1", now().minusYears(age1), ACTIVE);
    Competitive competitive2 = new Competitive(2,"nr2", now().minusYears(age2), ACTIVE);
    Competitive competitive3 = new Competitive(3,"nr3", now().minusYears(age3), ACTIVE);
    assertEquals(age1, competitive1.getAge());
    assertEquals(age2, competitive2.getAge());
    assertEquals(age3, competitive3.getAge());
  }
  
  
}