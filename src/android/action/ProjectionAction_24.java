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
import cordova.plugin.projection.pojo.Projection24;

public class ProjectionAction_24 {
  private Context context;

  public ProjectionAction_24(Context _context) {
    this.context = _context;
  }

  public List<Projection24> generateProjections(
    String strPlanCode,int intTerm, BigDecimal intAge, int intFreq, BigDecimal bigDecPrem, BigDecimal bigDecBaseSA,
    BigDecimal bigDecLTRAnnualPrem, int intMultiplier,BigDecimal bigDecInflation_Guard){

    int counter = 1;
    int counterMonth = 1;
    int iPolicyYear = 1;
    BigDecimal iAgeInsured =  new BigDecimal(0);
    BigDecimal prevIAgeInsured =  new BigDecimal(0);
    BigDecimal dblProbability_qx =  new BigDecimal(0);
    BigDecimal dbltpdProbability_qx =  new BigDecimal(0);
    BigDecimal tempDblPrem =  new BigDecimal(0);
    BigDecimal tempDblPremWithoutRisk =  new BigDecimal(0);
    int ivalue = 1;
    BigDecimal dblPAC =  new BigDecimal(0);
    BigDecimal dblPAdminCharge = new BigDecimal(200);
    BigDecimal dblPAdminChargeInflation = new BigDecimal(0.1);
    BigDecimal dblPAdminChargeValue =  new BigDecimal(0);
    BigDecimal dblincr_perc =  new BigDecimal(0);
    BigDecimal dblTempBaseSA =  new BigDecimal(0);
    BigDecimal dblDiscfuturepremiums = new BigDecimal(0);
    BigDecimal dblDiscfutureWithoutRiskpremiums = new BigDecimal(0);
    BigDecimal premium_Increase =  new BigDecimal(0);
    BigDecimal premiumIncome = new BigDecimal(0);
    BigDecimal premiumIncomeWithOutRisk = new BigDecimal(0);
    BigDecimal dblFundValueforSAR_6 =  new BigDecimal(0);
    BigDecimal dblMortalityCharge_6 =  new BigDecimal(0);
    BigDecimal dblTotalCharge_6 =  new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_6 =  new BigDecimal(0);
    BigDecimal dblFundvalue_Start_6 =  new BigDecimal(0);
    BigDecimal dblFundvalue_End_6 =  new BigDecimal(0);
    BigDecimal dblSurrenderValue_6 =  new BigDecimal(0);
    BigDecimal dblFundValueforSAR_8 =  new BigDecimal(0);
    BigDecimal dblMortalityCharge_8 =  new BigDecimal(0);
    BigDecimal dblTotalCharge_8 =  new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_8 =  new BigDecimal(0);
    BigDecimal dblFundvalue_Start_8 = new BigDecimal(0);
    BigDecimal dblFundvalue_End_8 =  new BigDecimal(0);
    BigDecimal dblSurrenderValue_8 =  new BigDecimal(0);
    BigDecimal dblFundValueforSAR_10 =  new BigDecimal(0);
    BigDecimal dblMortalityCharge_10 =  new BigDecimal(0);
    BigDecimal dblTotalCharge_10 =  new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_10 =  new BigDecimal(0);
    BigDecimal dblFundvalue_Start_10 = new BigDecimal(0);
    BigDecimal dblFundvalue_End_10 =  new BigDecimal(0);
    BigDecimal dblSurrenderValue_10 =  new BigDecimal(0);
    
    BigDecimal F6 = new BigDecimal(0);
    BigDecimal F8 = new BigDecimal(0);
    BigDecimal F10 = new BigDecimal(0);

    int tempIPolicyYear = 1;
    int intNPVFreq = 0;
    int intNPVCounter = 1;
    BigDecimal fgemv =  new BigDecimal(0);
    BigDecimal fdvgemv =  new BigDecimal(0);
    BigDecimal fmcgemv =  new BigDecimal(0);
    BigDecimal prem =  new BigDecimal(0);
    BigDecimal userEnteredPremium = new BigDecimal(0);
    List<Projection24> projectionList = new ArrayList<Projection24>();

    ProjectionDto projectionDto = new ProjectionDto(this.context);
    PlanMortalityTableDto planMortalityDto = new PlanMortalityTableDto(this.context);
    PremAllocTableDto premAllocTableDto = new PremAllocTableDto(this.context);

    try {
      userEnteredPremium = bigDecPrem;
      prevIAgeInsured = intAge;
      bigDecLTRAnnualPrem = projectionDto.fetchAnnualRatePremium(bigDecBaseSA, intAge,intTerm);

      if(intFreq == 1){
        prem=(bigDecPrem.multiply(new BigDecimal(12))).multiply(new BigDecimal(intTerm)).multiply(new BigDecimal(intMultiplier));
        premiumIncome = (((bigDecPrem.multiply(new BigDecimal(12))).subtract(bigDecLTRAnnualPrem.multiply(new BigDecimal(0.09)).multiply(new BigDecimal(12)))).divide(new BigDecimal(12))).multiply(new BigDecimal(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear-1)));
        premiumIncomeWithOutRisk= ((bigDecPrem.multiply(new BigDecimal(12))).divide(new BigDecimal(12))).multiply(new BigDecimal(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear-1)));
      }
      if(intFreq==3){
        prem=(bigDecPrem.multiply(new BigDecimal(4))).multiply(new BigDecimal(intTerm)).multiply(new BigDecimal(intMultiplier));
        premiumIncome = (((bigDecPrem.multiply(new BigDecimal(4))).subtract(bigDecLTRAnnualPrem.multiply(new BigDecimal(0.26)).multiply(new BigDecimal(4)))).divide(new BigDecimal(4))).multiply(new BigDecimal(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear-1)));
        premiumIncomeWithOutRisk= ((bigDecPrem.multiply(new BigDecimal(4))).divide(new BigDecimal(4))).multiply(new BigDecimal(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear-1)));
      }
      if(intFreq==6){
        prem=(bigDecPrem.multiply(new BigDecimal(2))).multiply(new BigDecimal(intTerm)).multiply(new BigDecimal(intMultiplier));
        premiumIncome = (((bigDecPrem.multiply(new BigDecimal(2))).subtract(bigDecLTRAnnualPrem.multiply(new BigDecimal(0.52)).multiply(new BigDecimal(2)))).divide(new BigDecimal(2))).multiply(new BigDecimal(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear-1)));
        premiumIncomeWithOutRisk= ((bigDecPrem.multiply(new BigDecimal(2))).divide(new BigDecimal(2))).multiply(new BigDecimal(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear-1)));
      }
      if(intFreq==12){
        prem=(bigDecPrem.multiply(new BigDecimal(1))).multiply(new BigDecimal(intTerm)).multiply(new BigDecimal(intMultiplier));
        premiumIncome = (((bigDecPrem.multiply(new BigDecimal(1))).subtract(bigDecLTRAnnualPrem.multiply(new BigDecimal(0.52)).multiply(new BigDecimal(2)))).divide(new BigDecimal(2))).multiply(new BigDecimal(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear-1)));
        premiumIncomeWithOutRisk= ((bigDecPrem.multiply(new BigDecimal(1))).divide(new BigDecimal(1))).multiply(new BigDecimal(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear-1)));
      }

      BigDecimal perValue = premAllocTableDto.select_pervalue(iPolicyYear,strPlanCode);
      if (perValue == null) {
        perValue = new BigDecimal(0);
      }

      dblPAC = perValue.multiply(premiumIncome);

      tempDblPrem=premiumIncome;
      tempDblPremWithoutRisk=premiumIncomeWithOutRisk;
      dblTempBaseSA=bigDecBaseSA;

      //Deleting Existing records
      projectionDto.deleteManager(strPlanCode);

      intNPVFreq=intFreq;
      while(counter<=intTerm*12){
        iAgeInsured = intAge.add(new BigDecimal(counter).divide(new BigDecimal(12),10,RoundingMode.FLOOR)).setScale(10, BigDecimal.ROUND_FLOOR);

        BigDecimal qx = new BigDecimal(0);
        qx = planMortalityDto.fetchQx_24_new(prevIAgeInsured,strPlanCode);
        if (qx == null) {
          qx = new BigDecimal(0);
        }
        dblProbability_qx = qx;

        BigDecimal tpdQx = new BigDecimal(0);
        tpdQx = planMortalityDto.fetchQx_24_new(prevIAgeInsured,"TPD");
        if (tpdQx == null) {
          tpdQx = new BigDecimal(0);
        }
        dbltpdProbability_qx=tpdQx;

        dblDiscfuturepremiums = fetchDiscFuturePremium(premiumIncome, new BigDecimal(0.00407412378364835), (intTerm*12)-1, intFreq, intFreq, bigDecBaseSA, bigDecInflation_Guard, intTerm, intAge, "N",counter).multiply(new BigDecimal(1+0.00407412378364835));
        dblDiscfutureWithoutRiskpremiums = fetchDiscFuturePremium(userEnteredPremium, new BigDecimal(0.00407412378364835), (intTerm*12)-1, intFreq, intFreq, bigDecBaseSA, bigDecInflation_Guard, intTerm, intAge, "N",counter).multiply(new BigDecimal(1+0.00407412378364835));
        if(intNPVFreq==1){
          intNPVFreq=intFreq;
        }else{
          intNPVFreq=intNPVFreq-1;
        }
        intNPVCounter=intNPVCounter+1;
        fdvgemv=prem.divide(BigDecimal.valueOf(Math.pow(1+0.00407412378364835, (intTerm*12)-(counter-1))),10,RoundingMode.HALF_DOWN);
        fmcgemv=fdvgemv.multiply(dblProbability_qx);
                
        Calendar today=Calendar.getInstance();
        today.set(Calendar.MONTH, today.get(Calendar.MONTH)+counter-1);
        int year=today.get(Calendar.YEAR);

        dblPAdminChargeValue=(dblPAdminCharge.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN)).multiply(BigDecimal.valueOf(Math.pow((dblPAdminChargeInflation.add(BigDecimal.ONE).doubleValue()), year-2013)));

        F6=(dblFundvalue_End_6.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));
        F8=(dblFundvalue_End_8.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));
        F10=(dblFundvalue_End_10.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));

        dblFundValueforSAR_6 = dblFundvalue_End_6.add(tempDblPrem).subtract(dblPAC).subtract(dblPAdminChargeValue).subtract(dblFundvalue_End_6.multiply(F6));
        dblFundValueforSAR_8 = dblFundvalue_End_8.add(tempDblPrem).subtract(dblPAC).subtract(dblPAdminChargeValue).subtract(dblFundvalue_End_8.multiply(F8));
        dblFundValueforSAR_10 = dblFundvalue_End_10.add(tempDblPrem).subtract(dblPAC).subtract(dblPAdminChargeValue).subtract(dblFundvalue_End_10.multiply(F10));
        dblMortalityCharge_6 = (bigDecBaseSA.subtract(dblFundValueforSAR_6)).multiply(dblProbability_qx);
        dblMortalityCharge_8 = (bigDecBaseSA.subtract(dblFundValueforSAR_8)).multiply(dblProbability_qx);
        dblMortalityCharge_10 = (bigDecBaseSA.subtract(dblFundValueforSAR_10)).multiply(dblProbability_qx);
        if (dblMortalityCharge_6.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_6 = BigDecimal.ZERO;
        }
        if (dblMortalityCharge_8.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_8 = BigDecimal.ZERO;
        }
        if (dblMortalityCharge_10.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_10 = BigDecimal.ZERO;
        }

        dblTotalCharge_6 = dblPAC.add(F6).add(dblPAdminChargeValue).add(dblDiscfuturepremiums.multiply(dblProbability_qx)).add(dblDiscfutureWithoutRiskpremiums.multiply(dbltpdProbability_qx)).add(fmcgemv);
        dblTotalCharge_8=dblPAC.add(F8).add(dblPAdminChargeValue).add(dblDiscfuturepremiums.multiply(dblProbability_qx)).add(dblDiscfutureWithoutRiskpremiums.multiply(dbltpdProbability_qx)).add(fmcgemv);
        dblTotalCharge_10=dblPAC.add(F10).add(dblPAdminChargeValue).add(dblDiscfuturepremiums.multiply(dblProbability_qx)).add(dblDiscfutureWithoutRiskpremiums.multiply(dbltpdProbability_qx)).add(fmcgemv);

        dblNetAmtaddedtoFund_6=tempDblPrem.subtract(dblTotalCharge_6);
        dblNetAmtaddedtoFund_8=tempDblPrem.subtract(dblTotalCharge_8);
        dblNetAmtaddedtoFund_10=tempDblPrem.subtract(dblTotalCharge_10);

        dblFundvalue_End_6=(dblFundvalue_End_6.add(dblNetAmtaddedtoFund_6)).multiply(new BigDecimal(1+ 0.00486755056534305)).setScale(19, RoundingMode.HALF_DOWN);
        dblFundvalue_End_8=(dblFundvalue_End_8.add(dblNetAmtaddedtoFund_8)).multiply(new BigDecimal(1+ 0.00643403011000343)).setScale(19, RoundingMode.HALF_DOWN);
        dblFundvalue_End_10=(dblFundvalue_End_10.add(dblNetAmtaddedtoFund_10)).multiply(new BigDecimal(1+ 0.00797414042890376)).setScale(19, RoundingMode.HALF_DOWN);

        if(counter==intTerm*12){
          fgemv=prem;
        }

        Projection24 projection=new Projection24();
        projection.setStep(counter);
        projection.setMonth(counterMonth);
        projection.setDurationMonth(counter);
        projection.setPolicyYear(iPolicyYear);
        projection.setAgeInsured(iAgeInsured.setScale(2, RoundingMode.HALF_UP));
        projection.setProbabilityqx(dblProbability_qx.setScale(19, RoundingMode.FLOOR));
        projection.setTpdChargerateqx(dbltpdProbability_qx.setScale(19, RoundingMode.FLOOR));
        projection.setPremiumIncome(tempDblPrem.setScale(19, RoundingMode.FLOOR));
        projection.setPremiumAllocationCharge(dblPAC.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundManagementCharge_6(F6.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundManagementCharge_8(F8.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundManagementCharge_10(F10.setScale(19, RoundingMode.FLOOR));
        projection.setPolicyAdministrationCharge(dblPAdminChargeValue.setScale(19, RoundingMode.FLOOR));
        projection.setDiscfuturePremiumsIncludingRisk(dblDiscfutureWithoutRiskpremiums.setScale(19, RoundingMode.FLOOR));
        projection.setPremiumIncomeIncludingRisk(tempDblPremWithoutRisk.setScale(19, RoundingMode.FLOOR));

        projection.setDiscfuturePremiums(dblDiscfuturepremiums.setScale(19, RoundingMode.FLOOR));
        projection.setWopChargedeath((dblDiscfuturepremiums.multiply(dblProbability_qx)).setScale(19, RoundingMode.FLOOR));
        projection.setWopChargetpd((dblDiscfutureWithoutRiskpremiums.multiply(dbltpdProbability_qx)).setScale(19, RoundingMode.FLOOR));
        projection.setWopChargeTotal((dblDiscfuturepremiums.multiply(dblProbability_qx)).add((dblDiscfutureWithoutRiskpremiums.multiply(dbltpdProbability_qx))).setScale(19, RoundingMode.FLOOR));
        projection.setTotalCharge6(dblTotalCharge_6.setScale(19, RoundingMode.FLOOR));
        projection.setNetAmtaddedtoFund6(dblNetAmtaddedtoFund_6.setScale(19, RoundingMode.FLOOR));
        projection.setFundvalueStart6( dblFundvalue_Start_6.setScale(19, RoundingMode.FLOOR));
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
        projection.setDvgemv(fdvgemv.setScale(19, RoundingMode.FLOOR));
        projection.setMcgemv(fmcgemv.setScale(19, RoundingMode.FLOOR));
        projectionList.add(projection);

        counter=counter+1;
        counterMonth=counterMonth+1;
        if(counterMonth==12){
          tempIPolicyYear=tempIPolicyYear+1;
        }
        if(counterMonth>12){
          counterMonth=1;
          iPolicyYear=iPolicyYear+1;
          if(iPolicyYear>1&strPlanCode=="PRODUCT13"){
            dblincr_perc=new BigDecimal(0.10+(iPolicyYear-1));
            bigDecBaseSA=dblTempBaseSA.add(dblTempBaseSA.multiply(dblincr_perc));
          }
        }
        ivalue=ivalue+1;
        dblFundvalue_Start_6=dblFundvalue_End_6;
        dblFundvalue_Start_8=dblFundvalue_End_8;
        dblFundvalue_Start_10=dblFundvalue_End_10;
        
        BigDecimal perValueTemp = new BigDecimal(0);
        if(intFreq==3||intFreq==6||intFreq==12){
          if(ivalue==intFreq+1){
            ivalue=1;
            if(intFreq==3){
              tempDblPrem = (((bigDecPrem.multiply(new BigDecimal(4)).subtract(bigDecLTRAnnualPrem.multiply(new BigDecimal(0.26).multiply(new BigDecimal(4)))).divide(new BigDecimal(4))))).multiply(BigDecimal.valueOf(Math.pow(premium_Increase.add(bigDecInflation_Guard).add(BigDecimal.ONE).doubleValue(),iPolicyYear-1)));
              tempDblPremWithoutRisk = ((bigDecPrem.multiply(new BigDecimal(4))).divide(new BigDecimal(4))).multiply(BigDecimal.valueOf(Math.pow(premium_Increase.add(bigDecInflation_Guard).add(BigDecimal.ONE).doubleValue(),iPolicyYear-1)));
            }
            if(intFreq==6){
              tempDblPrem = (((bigDecPrem.multiply(new BigDecimal(2)).subtract(bigDecLTRAnnualPrem.multiply(new BigDecimal(0.52).multiply(new BigDecimal(2)))).divide(new BigDecimal(2))))).multiply(BigDecimal.valueOf(Math.pow(premium_Increase.add(bigDecInflation_Guard).add(BigDecimal.ONE).doubleValue(),iPolicyYear-1)));
              tempDblPremWithoutRisk = ((bigDecPrem.multiply(new BigDecimal(2))).divide(new BigDecimal(2))).multiply(BigDecimal.valueOf(Math.pow(premium_Increase.add(bigDecInflation_Guard).add(BigDecimal.ONE).doubleValue(),iPolicyYear-1)));
            }
            if(intFreq==12){
              tempDblPrem = (((bigDecPrem.multiply(new BigDecimal(1)).subtract(bigDecLTRAnnualPrem.multiply(new BigDecimal(1).multiply(new BigDecimal(1)))).divide(new BigDecimal(1))))).multiply(BigDecimal.valueOf(Math.pow(premium_Increase.add(bigDecInflation_Guard).add(BigDecimal.ONE).doubleValue(),iPolicyYear-1)));
              tempDblPremWithoutRisk = ((bigDecPrem.multiply(new BigDecimal(1))).divide(new BigDecimal(1))).multiply(BigDecimal.valueOf(Math.pow(premium_Increase.add(bigDecInflation_Guard).add(BigDecimal.ONE).doubleValue(),iPolicyYear-1)));
            }

            perValueTemp = premAllocTableDto.select_pervalue(iPolicyYear,strPlanCode);
            if (perValueTemp == null) {
              perValueTemp = new BigDecimal(0);
            }

            dblPAC = perValueTemp.multiply(tempDblPrem);
          }else{
            tempDblPrem=BigDecimal.ZERO;
            dblPAC=BigDecimal.ZERO;
            tempDblPremWithoutRisk=BigDecimal.ZERO;
          }
        }else{
          tempDblPrem = (((bigDecPrem.multiply(new BigDecimal(12)).subtract(bigDecLTRAnnualPrem.multiply(new BigDecimal(0.09).multiply(new BigDecimal(12)))))).divide(new BigDecimal(12))).multiply(BigDecimal.valueOf(Math.pow(premium_Increase.add(bigDecInflation_Guard).add(BigDecimal.ONE).doubleValue(),iPolicyYear-1)));
          tempDblPremWithoutRisk = ((bigDecPrem.multiply(new BigDecimal(12))).divide(new BigDecimal(12))).multiply(BigDecimal.valueOf(Math.pow(premium_Increase.add(bigDecInflation_Guard).add(BigDecimal.ONE).doubleValue(),iPolicyYear-1)));

          perValueTemp = premAllocTableDto.select_pervalue(iPolicyYear,strPlanCode);
          if (perValueTemp == null) {
            perValueTemp = new BigDecimal(0);
          }

          dblPAC = perValueTemp.multiply(tempDblPrem);
        }
        prevIAgeInsured = iAgeInsured;
      }
      
      projectionDto.insertAllProjection24(projectionList);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return projectionList;
  }

  public BigDecimal fetchDiscFuturePremium(
    BigDecimal userPrem, BigDecimal rate, int noOfYear, int freq, int userFreq,
    BigDecimal basicSA, BigDecimal inflation_Guard, int intTerm, BigDecimal intAge,
    String withDeath, int npvCounter) {

    String flag = "Y";
    BigDecimal npv = BigDecimal.ZERO;
    BigDecimal pv = BigDecimal.ZERO;
    int step;
    int istep = 0;
    BigDecimal premium_Increase = BigDecimal.ZERO;
    BigDecimal premiumIncome = BigDecimal.ZERO;
    int iPolicyYear = 1;
    BigDecimal annualPremium = BigDecimal.ZERO;
    BigDecimal premiumRate = BigDecimal.ZERO;
    BigDecimal modelFactor = BigDecimal.ZERO;
    BigDecimal prem_death = BigDecimal.ZERO;
    BigDecimal premFund = BigDecimal.ZERO;
    int counterMonth = 1;
    int tempFreq;
    annualPremium = userPrem;
    modelFactor = new BigDecimal(1.00 * 1);
    tempFreq = 1;
    if (userFreq == 1) {
      annualPremium = userPrem.multiply(new BigDecimal(12));
      modelFactor = new BigDecimal(0.09 * 12);
      tempFreq = 12;
    }
    if (userFreq == 6) {
      annualPremium = userPrem.multiply(new BigDecimal(2));
      modelFactor = new BigDecimal(0.52 * 2);
      tempFreq = 2;
    }
    if (userFreq == 3) {
      annualPremium = userPrem.multiply(new BigDecimal(4));
      modelFactor = new BigDecimal(0.26 * 4);
      tempFreq = 4;
    }

    ProjectionDto projectionDto = new ProjectionDto(this.context);
    premiumRate = projectionDto.fetchPremiumRate(intTerm, intAge.intValue());
    prem_death = premiumRate.multiply(basicSA).divide(new BigDecimal(1000));

    if (withDeath == "Y") {
      premFund = annualPremium.subtract(prem_death.multiply(modelFactor));
    }
    if (withDeath == "N") {
      premFund = annualPremium;
    }
    step = npvCounter;
    if (userFreq == 1) {
      istep = 1;
      step = step + userFreq - 1;
    } else {
      if (step <= userFreq) {
        step = userFreq;
      } else {
        if (step > userFreq) {
          while (step % userFreq != 0) {
            step = step + 1;
          }
          step = step + 1;
        }
      }
      if ((npvCounter % userFreq) == 1) {
        istep = userFreq;
      }
      if ((npvCounter % userFreq) == 2) {
        istep = userFreq - 1;
      }
      if ((npvCounter % userFreq) == 3) {
        istep = userFreq - 2;
      }
      if ((npvCounter % userFreq) == 4) {
        istep = userFreq - 3;
      }
      if ((npvCounter % userFreq) == 5) {
        istep = userFreq - 4;
      }
      if ((npvCounter % userFreq) == 6) {
        istep = userFreq - 5;
      }
      if ((npvCounter % userFreq) == 7) {
        istep = userFreq - 6;
      }
      if ((npvCounter % userFreq) == 8) {
        istep = userFreq - 7;
      }
      if ((npvCounter % userFreq) == 9) {
        istep = userFreq - 8;
      }
      if ((npvCounter % userFreq) == 10) {
        istep = userFreq - 9;
      }
      if ((npvCounter % userFreq) == 11) {
        istep = userFreq - 10;
      }
      if ((npvCounter % userFreq) == 0) {
        istep = 1;
      }
    }
    npv = BigDecimal.ZERO;
    counterMonth = step;
    iPolicyYear = ((npvCounter + userFreq) - 1) / 12;
    while (step <= noOfYear) {
      premiumIncome = (premFund.divide(new BigDecimal(tempFreq), 10, RoundingMode.FLOOR)).multiply(new BigDecimal(
        Math.pow(inflation_Guard.add(BigDecimal.ONE).add(premium_Increase).doubleValue(), iPolicyYear)));
      ;
      if (iPolicyYear > 0 & flag == "Y") {
        counterMonth = step % 12;
      }
      pv = premiumIncome.divide(BigDecimal.valueOf(Math.pow(rate.add(BigDecimal.ONE).doubleValue(), istep)), 10,
        RoundingMode.FLOOR);
      npv = npv.add(pv);
      step = step + userFreq;
      counterMonth = counterMonth + userFreq;
      if (counterMonth >= 12) {
        counterMonth = 0;
        iPolicyYear = iPolicyYear + 1;
      }
      flag = "N";
      istep = istep + userFreq;
    }
    return npv;
  }

}
