package cordova.plugin.projection.action;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.content.Context;
import cordova.plugin.projection.dto.PlanMortalityTableDto;
import cordova.plugin.projection.dto.PremAllocTableDto;
import cordova.plugin.projection.dto.ProjectionDto;
import cordova.plugin.projection.pojo.PlanMortalityTable;
import cordova.plugin.projection.pojo.Projection14_15;

public class ProjectionAction_14_15 {
  private Context context;

  public ProjectionAction_14_15(Context _context) {
    this.context = _context;
  }

  public List<Projection14_15> generateProjections(
    String strPlanCode, int intTerm, BigDecimal intAge, int intFreq,
    BigDecimal bigDecPrem, BigDecimal bigDecBaseSA, BigDecimal bigDecLTRAnnualPrem, int intMultiplier) {

    int counter = 1;
    int counterMonth = 1;
    int iPolicyYear = 1;
    BigDecimal iAgeInsured = new BigDecimal(0);
    BigDecimal prevIAgeInsured = new BigDecimal(0);
    BigDecimal dblProbability_qx = new BigDecimal(0);
    BigDecimal dbltpdProbability_qx = new BigDecimal(0);
    BigDecimal tempDblPrem = new BigDecimal(0);
    int ivalue = 1;
    BigDecimal dblPAC = new BigDecimal(0);
    BigDecimal dblPAdminCharge = new BigDecimal(200);
    BigDecimal dblPAdminChargeInflation = new BigDecimal(0.10);
    BigDecimal dblPAdminChargeValue = new BigDecimal(0);
    BigDecimal dblIncr_Perc;
    BigDecimal dblTempBaseSA;
    BigDecimal dblDiscfuturePremiums = new BigDecimal(0);
    BigDecimal dblFundValueforSAR_6 = new BigDecimal(0);
    BigDecimal dblMortalityCharge_6 = new BigDecimal(0);
    BigDecimal dblTotalCharge_6 = new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_6 = new BigDecimal(0);
    BigDecimal dblFundvalue_Start_6 = new BigDecimal(0);
    BigDecimal dblFundvalue_End_6 = new BigDecimal(0);
    BigDecimal dblSurrenderValue_6 = new BigDecimal(0);
    BigDecimal dblFundValueforSAR_8 = new BigDecimal(0);
    BigDecimal dblMortalityCharge_8 = new BigDecimal(0);
    BigDecimal dblTotalCharge_8 = new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_8 = new BigDecimal(0);
    BigDecimal dblFundvalue_Start_8 = new BigDecimal(0);
    BigDecimal dblFundvalue_End_8 = new BigDecimal(0);
    BigDecimal dblSurrenderValue_8 = new BigDecimal(0);
    BigDecimal dblFundValueforSAR_10 = new BigDecimal(0);
    BigDecimal dblMortalityCharge_10 = new BigDecimal(0);
    BigDecimal dblTotalCharge_10 = new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_10 = new BigDecimal(0);
    BigDecimal dblFundvalue_Start_10 = new BigDecimal(0);
    BigDecimal dblFundvalue_End_10 = new BigDecimal(0);
    BigDecimal dblSurrenderValue_10 = new BigDecimal(0);
    BigDecimal dblFundManagementCharge_6 = new BigDecimal(0);
    BigDecimal dblFundManagementCharge_8 = new BigDecimal(0);
    BigDecimal dblFundManagementCharge_10 = new BigDecimal(0);
    int tempIPolicyYear = 1;
    int intNPVFreq = 0;
    int intNPVCounter = 1;
    BigDecimal fgemv = new BigDecimal(0);
    BigDecimal fdvgemv = new BigDecimal(0);
    BigDecimal fmcgemv = new BigDecimal(0);
    BigDecimal prem = new BigDecimal(0);

    BigDecimal DblpremWithRisk = new BigDecimal(0);
    BigDecimal tempDblpremWithRisk = new BigDecimal(0);
    BigDecimal DblDiscfuturepremiumsWithRisk = new BigDecimal(0);

    Calendar DatePac = Calendar.getInstance();
    int yearPac=DatePac.get(Calendar.YEAR);

    BigDecimal tempPrem = new BigDecimal(0);
    tempPrem = bigDecPrem;

    ProjectionDto projectionDto = new ProjectionDto(this.context);
    PremAllocTableDto premAllocTableDto = new PremAllocTableDto(this.context);
    PlanMortalityTableDto planMortalityDto = new PlanMortalityTableDto(this.context);

    List<Projection14_15> projectionList = new ArrayList<Projection14_15>();
    try {
      prevIAgeInsured = intAge;

      if (strPlanCode.equals("PRODUCT14")) {
        if (intFreq ==1) {
          DblpremWithRisk = bigDecPrem.multiply(new BigDecimal(12).divide(new BigDecimal(12), 10, RoundingMode.FLOOR));
          tempDblPrem=(bigDecPrem.multiply(new BigDecimal(12)).subtract(bigDecLTRAnnualPrem)).divide(new BigDecimal(12), 10, RoundingMode.FLOOR);
        }
        if (intFreq ==3) {
          DblpremWithRisk = bigDecPrem.multiply(new BigDecimal(4).divide(new BigDecimal(4), 10, RoundingMode.FLOOR));
          tempDblPrem=(bigDecPrem.multiply(new BigDecimal(4)).subtract(bigDecLTRAnnualPrem)).divide(new BigDecimal(4), 10, RoundingMode.FLOOR);
        }
        if (intFreq ==6) {
          DblpremWithRisk = bigDecPrem.multiply(new BigDecimal(2).divide(new BigDecimal(2), 10, RoundingMode.FLOOR));
          tempDblPrem=(bigDecPrem.multiply(new BigDecimal(2)).subtract(bigDecLTRAnnualPrem)).divide(new BigDecimal(2), 10, RoundingMode.FLOOR);
        }
        if (intFreq == 12) {
          DblpremWithRisk = bigDecPrem.multiply(new BigDecimal(1).divide(new BigDecimal(1), 10, RoundingMode.FLOOR));
          tempDblPrem=(bigDecPrem.multiply(new BigDecimal(1)).subtract(bigDecLTRAnnualPrem)).divide(new BigDecimal(1), 10, RoundingMode.FLOOR);
        }
      }

      if (strPlanCode.equals("PRODUCT15")) {
        bigDecLTRAnnualPrem = projectionDto.fetchAnnualPremium(bigDecBaseSA, intTerm, intAge);
        if (intFreq == 1) {
          prem = (bigDecPrem.multiply(new BigDecimal(12))).multiply(new BigDecimal(intTerm)).multiply(new BigDecimal(intMultiplier));
          DblpremWithRisk = bigDecPrem.multiply(new BigDecimal(12).divide(new BigDecimal(12), 10, RoundingMode.FLOOR));
         
          tempDblPrem = ((bigDecPrem.multiply(new BigDecimal(12)))
            .subtract((bigDecLTRAnnualPrem.multiply(new BigDecimal(0.09).multiply(new BigDecimal(12))))))
            .divide(new BigDecimal(12));
        }
        if (intFreq == 3) {
          prem = (bigDecPrem.multiply(new BigDecimal(4))).multiply(new BigDecimal(intTerm)).multiply(new BigDecimal(intMultiplier));
          DblpremWithRisk = bigDecPrem.multiply(new BigDecimal(4).divide(new BigDecimal(4), 10, RoundingMode.FLOOR));

          tempDblPrem = ((bigDecPrem.multiply(new BigDecimal(4)))
            .subtract((bigDecLTRAnnualPrem.multiply(new BigDecimal(0.26).multiply(new BigDecimal(4))))))
            .divide(new BigDecimal(4));
        }
        if (intFreq == 6) {
          prem = (bigDecPrem.multiply(new BigDecimal(2))).multiply(new BigDecimal(intTerm)).multiply(new BigDecimal(intMultiplier));
          DblpremWithRisk = bigDecPrem.multiply(new BigDecimal(2).divide(new BigDecimal(2), 10, RoundingMode.FLOOR));

          tempDblPrem = ((bigDecPrem.multiply(new BigDecimal(2)))
            .subtract((bigDecLTRAnnualPrem.multiply(new BigDecimal(0.52).multiply(new BigDecimal(2))))))
            .divide(new BigDecimal(2));
        }
        if (intFreq == 12) {
          prem = (bigDecPrem.multiply(new BigDecimal(1))).multiply(new BigDecimal(intTerm)).multiply(new BigDecimal(intMultiplier));
          DblpremWithRisk = bigDecPrem.multiply(new BigDecimal(1).divide(new BigDecimal(1), 10, RoundingMode.FLOOR));

          tempDblPrem =  bigDecPrem.subtract(bigDecLTRAnnualPrem).multiply(BigDecimal.ONE).multiply(BigDecimal.ONE);
        }
      }

      bigDecPrem = tempDblPrem;
      tempDblpremWithRisk = DblpremWithRisk;

      projectionDto.deleteManager(strPlanCode);

      BigDecimal perValue =  premAllocTableDto.select_pervalue(iPolicyYear,strPlanCode);
      if (perValue == null) {
        perValue = new BigDecimal(0);
      }

      dblPAC = perValue.multiply(tempDblPrem).setScale(2, BigDecimal.ROUND_HALF_DOWN);
      intNPVFreq=intFreq;
      
      while (counter <= intTerm * 12) {
        iAgeInsured = intAge.add(BigDecimal.valueOf(counter/12.0)).setScale(2, BigDecimal.ROUND_HALF_DOWN);

        List<PlanMortalityTable> planMortList = planMortalityDto.select(prevIAgeInsured, strPlanCode);
        BigDecimal qx = new BigDecimal(0);

        List<PlanMortalityTable> tpdPlanMortList = planMortalityDto.selectTpd(prevIAgeInsured, strPlanCode);
        BigDecimal tpdQx = new BigDecimal(0);

        if (planMortList.size() >= 1)
          qx = planMortList.get(0).getQx();
        if (qx == null)
          qx = new BigDecimal(0);

        dblProbability_qx = qx;


        if (tpdPlanMortList.size() >= 1)
          tpdQx = tpdPlanMortList.get(0).getQx();
        if (tpdQx == null)
          tpdQx = new BigDecimal(0);

        dbltpdProbability_qx = tpdQx;
        dblDiscfuturePremiums = fetchNpv(bigDecPrem, new BigDecimal(0.00407412378364835),
          (intTerm * 12) - intNPVCounter, intNPVFreq, intFreq);
        DblDiscfuturepremiumsWithRisk = (fetchNpv(DblpremWithRisk, new BigDecimal(0.00407412378364835),
          (intTerm * 12) - intNPVCounter, intNPVFreq, intFreq)).multiply(new BigDecimal(1+0.00407412378364835));

        if (intNPVFreq == 1) {
          intNPVFreq = intFreq;
        } else {
          intNPVFreq = intNPVFreq - 1;
        }

        intNPVCounter = intNPVCounter + 1;
        if (strPlanCode.equals("PRODUCT15")) {
          fdvgemv = prem.divide(
            BigDecimal.valueOf(Math.pow(1 + 0.00407412378364835, (intTerm * 12) - (counter - 1))), 10,
            RoundingMode.FLOOR);
          fmcgemv= fdvgemv.multiply(qx);
        }

        dblPAdminChargeValue = dblPAdminCharge.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN)
          .multiply(BigDecimal.valueOf(Math
            .pow((dblPAdminChargeInflation.add(BigDecimal.ONE).doubleValue()),
              (new BigDecimal(DatePac.get(Calendar.YEAR)).subtract(BigDecimal.valueOf(2013)).doubleValue()))));

        dblMortalityCharge_6 = bigDecBaseSA.subtract(dblFundValueforSAR_6).multiply(dblProbability_qx);
        dblMortalityCharge_8 = bigDecBaseSA.subtract(dblFundValueforSAR_8).multiply(dblProbability_qx);
        dblMortalityCharge_10 = bigDecBaseSA.subtract(dblFundValueforSAR_10).multiply(dblProbability_qx);

        if (dblMortalityCharge_6.compareTo(BigDecimal.ZERO) < 0)
          dblMortalityCharge_6 = BigDecimal.ZERO;
        if (dblMortalityCharge_8.compareTo(BigDecimal.ZERO) < 0)
          dblMortalityCharge_8 = BigDecimal.ZERO;
        if (dblMortalityCharge_10.compareTo(BigDecimal.ZERO) < 0)
          dblMortalityCharge_10 = BigDecimal.ZERO;


        dblFundManagementCharge_6=(dblFundvalue_End_6.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));
        dblFundManagementCharge_8=(dblFundvalue_End_8.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));
        dblFundManagementCharge_10=(dblFundvalue_End_10.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));


        dblTotalCharge_6 = dblPAC.add(dblPAdminChargeValue).add(dblFundManagementCharge_6)
          .add((dblDiscfuturePremiums.multiply(dblProbability_qx)))
          .add((DblDiscfuturepremiumsWithRisk.multiply(dbltpdProbability_qx)));

        dblTotalCharge_8 = dblPAC.add(dblPAdminChargeValue).add(dblFundManagementCharge_8)
          .add((dblDiscfuturePremiums.multiply(dblProbability_qx)))
          .add((DblDiscfuturepremiumsWithRisk.multiply(dbltpdProbability_qx)));
        dblTotalCharge_10 = dblPAC.add(dblPAdminChargeValue).add(dblFundManagementCharge_10)
          .add((dblDiscfuturePremiums.multiply(dblProbability_qx)))
          .add((DblDiscfuturepremiumsWithRisk.multiply(dbltpdProbability_qx)));

        dblNetAmtaddedtoFund_6 = tempDblPrem.subtract(dblTotalCharge_6);
        dblNetAmtaddedtoFund_8 = tempDblPrem.subtract(dblTotalCharge_8);
        dblNetAmtaddedtoFund_10 = tempDblPrem.subtract(dblTotalCharge_10);

        dblFundvalue_End_6 = dblFundvalue_End_6.add(dblNetAmtaddedtoFund_6)
          .multiply(new BigDecimal(1 + 0.00486755056534305)).setScale(19, RoundingMode.HALF_DOWN);
        dblFundvalue_End_8 = dblFundvalue_End_8.add(dblNetAmtaddedtoFund_8)
          .multiply(new BigDecimal(1 + 0.00643403011000343)).setScale(19, RoundingMode.HALF_DOWN);
        dblFundvalue_End_10 = dblFundvalue_End_10.add(dblNetAmtaddedtoFund_10)
          .multiply(new BigDecimal(1 + 0.00797414042890376)).setScale(19, RoundingMode.HALF_DOWN);

        if (counter == intTerm * 12)
          fgemv = prem;

        Projection14_15 projection = new Projection14_15();
        projection.setStep(counter);
        projection.setMonth(counterMonth);
        projection.setDurationMonth(counter);
        projection.setPolicyYear(iPolicyYear);
        projection.setAgeInsured(iAgeInsured);
        projection.setProbabilityqx(dblProbability_qx.setScale(19, RoundingMode.FLOOR));
        projection.setTpdChargerateqx(dbltpdProbability_qx.setScale(19, RoundingMode.FLOOR));
        projection.setPremiumIncome(tempDblPrem.setScale(19, RoundingMode.FLOOR));
        projection.setPremiumAllocationCharge(dblPAC.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundManagementCharge_6(dblFundManagementCharge_6.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundManagementCharge_8(dblFundManagementCharge_8.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundManagementCharge_10(dblFundManagementCharge_10.setScale(19, RoundingMode.FLOOR));
        projection.setPolicyAdministrationCharge(dblPAdminChargeValue.setScale(19, RoundingMode.FLOOR));
        projection.setDiscfuturePremiums(dblDiscfuturePremiums.setScale(19, RoundingMode.FLOOR));
        projection.setPremiumIncomeRisk(tempDblpremWithRisk.setScale(19, RoundingMode.FLOOR));
        projection.setDiscfuturePremiumsRisk(DblDiscfuturepremiumsWithRisk.setScale(19, RoundingMode.FLOOR));

        projection.setWopChargedeath(
          dblDiscfuturePremiums.multiply(dblProbability_qx).setScale(19, RoundingMode.FLOOR));
        projection.setWopChargetpd(
          dblDiscfuturePremiums.multiply(dbltpdProbability_qx).setScale(19, RoundingMode.FLOOR));
        projection.setWopChargeTotal((dblDiscfuturePremiums.multiply(dblProbability_qx))
          .add((dblDiscfuturePremiums.multiply(dbltpdProbability_qx))).setScale(19, RoundingMode.FLOOR));
        projection.setTotalCharge6(dblTotalCharge_6.setScale(19, RoundingMode.FLOOR));
        projection.setNetAmtaddedtoFund6(dblNetAmtaddedtoFund_6.setScale(19, RoundingMode.FLOOR));
        projection.setFundvalueStart6(dblFundvalue_Start_6.setScale(19, RoundingMode.FLOOR));
        projection.setFundvalueEnd6(dblFundvalue_End_6.setScale(19, RoundingMode.FLOOR));
        projection.setSurrenderValue6(dblSurrenderValue_6.setScale(19, RoundingMode.FLOOR));
        projection.setTotalCharge8(dblTotalCharge_8.setScale(19, RoundingMode.FLOOR));
        projection.setNetAmtaddedtoFund8(dblNetAmtaddedtoFund_8.setScale(19, RoundingMode.FLOOR));
        projection.setFundvalueStart8(dblFundvalue_Start_8.setScale(19, RoundingMode.FLOOR));
        projection.setFundvalueEnd8(dblFundvalue_End_8.setScale(19, RoundingMode.FLOOR));
        projection.setSurrenderValue8(dblSurrenderValue_8.setScale(19, RoundingMode.FLOOR));
        projection.setTotalCharge10(dblTotalCharge_10.setScale(19, RoundingMode.FLOOR));
        projection.setNetAmtAddedtoFund10(dblNetAmtaddedtoFund_10.setScale(19, RoundingMode.FLOOR));
        projection.setFundvalueStart10(dblFundvalue_Start_10.setScale(19, RoundingMode.FLOOR));
        projection.setFundvalueend10(dblFundvalue_End_10.setScale(19, RoundingMode.FLOOR));
        projection.setSurrenderValue10(dblSurrenderValue_10.setScale(19, RoundingMode.FLOOR));
        projection.setGemv(fgemv.setScale(19, RoundingMode.FLOOR));
        projection.setDvgemv(fdvgemv.setScale(19, RoundingMode.FLOOR).setScale(19, RoundingMode.FLOOR));
        projection.setMcgemv(fmcgemv.setScale(19, RoundingMode.FLOOR));
        projectionList.add(projection);

        counter = counter + 1;
        counterMonth = counterMonth + 1;
        DatePac.add(Calendar.MONTH, 1);
        if (counterMonth == 12)
          tempIPolicyYear = tempIPolicyYear + 1;

        if (counterMonth > 12) {
          counterMonth = 1;
          iPolicyYear = iPolicyYear + 1;
        }

        ivalue = ivalue + 1;
        dblFundvalue_Start_6 = dblFundvalue_End_6;
        dblFundvalue_Start_8 = dblFundvalue_End_8;
        dblFundvalue_Start_10 = dblFundvalue_End_10;
        
        if (intFreq == 3 || intFreq == 6 || intFreq == 12) {
          if (ivalue == (intFreq + 1)) {
            ivalue = 1;
            
            if (strPlanCode.equals("PRODUCT15")) {
              if (intFreq == 3) {
                DblpremWithRisk = tempPrem.multiply(new BigDecimal(4).divide(new BigDecimal(4), 10, RoundingMode.FLOOR));

                tempDblPrem = ((tempPrem.multiply(new BigDecimal(4)))
                  .subtract((bigDecLTRAnnualPrem.multiply(new BigDecimal(0.26).multiply(new BigDecimal(4))))))
                  .divide(new BigDecimal(4));
              }
              if (intFreq == 6) {
                DblpremWithRisk = tempPrem.multiply(new BigDecimal(2).divide(new BigDecimal(2), 10, RoundingMode.FLOOR));

                tempDblPrem = ((tempPrem.multiply(new BigDecimal(2)))
                  .subtract((bigDecLTRAnnualPrem.multiply(new BigDecimal(0.52)
                    .multiply(new BigDecimal(2))))))
                  .divide(new BigDecimal(2));
              }
              if (intFreq == 12) {
                DblpremWithRisk = tempPrem.multiply(new BigDecimal(1).divide(new BigDecimal(1), 10, RoundingMode.FLOOR));

                tempDblPrem = (tempPrem.multiply(new BigDecimal(1)).subtract(bigDecLTRAnnualPrem).
                  multiply(new BigDecimal(1)).multiply(new BigDecimal(1)))
                  .divide(new BigDecimal(1), 10, RoundingMode.FLOOR);
              }
            }
            else if (strPlanCode.equals("PRODUCT14")) {
              if (intFreq == 3) {
                DblpremWithRisk = tempPrem.multiply(new BigDecimal(4).divide(new BigDecimal(4), 10, RoundingMode.FLOOR));

                tempDblPrem = ((tempPrem.multiply(new BigDecimal(4)))
                  .subtract((bigDecLTRAnnualPrem)))
                  .divide(new BigDecimal(4));
              }
              if (intFreq == 6) {
                DblpremWithRisk = tempPrem.multiply(new BigDecimal(2).divide(new BigDecimal(2), 10, RoundingMode.FLOOR));

                tempDblPrem = ((tempPrem.multiply(new BigDecimal(2)))
                  .subtract((bigDecLTRAnnualPrem)))
                  .divide(new BigDecimal(2));
              }
              if (intFreq == 12) {
                DblpremWithRisk = tempPrem.multiply(new BigDecimal(1).divide(new BigDecimal(1), 10, RoundingMode.FLOOR));

                tempDblPrem = (tempPrem.multiply(new BigDecimal(1)).subtract(bigDecLTRAnnualPrem).
                  multiply(new BigDecimal(1)).multiply(new BigDecimal(1)))
                  .divide(new BigDecimal(1), 10, RoundingMode.FLOOR);
              }
            }

            perValue = premAllocTableDto.select_pervalue(iPolicyYear,strPlanCode);
            if (perValue == null) {
              perValue = new BigDecimal(0);
            }

            dblPAC = perValue.multiply(tempDblPrem);

          } else {
            perValue = new BigDecimal(0);
            dblPAC = perValue.multiply(tempDblPrem);
            tempDblPrem = new BigDecimal(0);
          }
        } else {
          perValue = premAllocTableDto.select_pervalue(iPolicyYear,strPlanCode);
          if (perValue == null) {
            perValue = new BigDecimal(0);
          }
          dblPAC = perValue.multiply(tempDblPrem);

        }
        prevIAgeInsured = iAgeInsured;

      }
      projectionDto.insertAllProjection(projectionList);
      return projectionList;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public BigDecimal fetchNpv(
    BigDecimal prem, BigDecimal rate, int noOfYear, int freq, int userFreq) {

    BigDecimal npv = new BigDecimal(0);
    try {
      BigDecimal pv = new BigDecimal(0);
      int step;
      BigDecimal tempPrem = new BigDecimal(0);
      String resultStr = "";
      step = 1;
      if (userFreq == 1) {
        step = step + userFreq - 1;
      } else {
        step = step + freq;
        step = step - 1;
      }
      npv = BigDecimal.ZERO;
      tempPrem = prem;
      while (step <= noOfYear) {
        pv = tempPrem.divide(
          BigDecimal.valueOf(Math.pow((new BigDecimal( 1).add(rate)).doubleValue(), step)), 10,
          RoundingMode.FLOOR);
        resultStr = resultStr + step + ";" + tempPrem + "|" + pv + "~";
        npv = npv.add(pv);
        step = step + userFreq;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return npv;
  }

}
