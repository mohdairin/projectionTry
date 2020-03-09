package cordova.plugin.projection.action;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.content.Context;
import cordova.plugin.projection.dto.PlanMortalityTableDto;
import cordova.plugin.projection.dto.PlanSvTableDto;
import cordova.plugin.projection.dto.PremAllocTableDto;
import cordova.plugin.projection.dto.ProjectionDto;
import cordova.plugin.projection.pojo.PlanMortalityTable;
import cordova.plugin.projection.pojo.PlanSvTable;
import cordova.plugin.projection.pojo.PremAllocTable;
import cordova.plugin.projection.pojo.oProjection;

public class ProjectionAction {
  private Context context;

  public ProjectionAction(Context _context) {
    this.context = _context;
  }

  public List<oProjection> bfnCalcProjection(
    String strPlanCode, int intTerm, BigDecimal intAge, int intFreq,
    BigDecimal bigDecPrem, BigDecimal bigDecBaseSA) {

    ProjectionDto proj = new ProjectionDto(this.context);
    List<oProjection> projectionList = new ArrayList<oProjection>();
    int counter = 1;
    int counterMonth = 1;
    int iValue = 1;
    BigDecimal perValue = new BigDecimal(0);
    BigDecimal qx = new BigDecimal(0);
    BigDecimal factor = new BigDecimal(0);
    BigDecimal prevIAgeInsured = BigDecimal.valueOf(0);
    BigDecimal dblPAdminCharge = BigDecimal.valueOf(200);
    BigDecimal dblPAdminChargeInflation = BigDecimal.valueOf(0.10);
    BigDecimal tempDblPrem = new BigDecimal(0);
    double dblIncrPerc = 0;
    BigDecimal dblFundvalue_Start_6 = new BigDecimal(0);
    BigDecimal dblFundvalue_Start_8 = new BigDecimal(0);
    BigDecimal dblFundvalue_Start_10 = new BigDecimal(0);
    BigDecimal dblFundvalue_End_6 = new BigDecimal(0);
    BigDecimal dblFundvalue_End_8 = new BigDecimal(0);
    BigDecimal dblFundvalue_End_10 = new BigDecimal(0);
    BigDecimal dblMortalityCharge_6 = new BigDecimal(0);
    BigDecimal dblMortalityCharge_8 = new BigDecimal(0);
    BigDecimal dblMortalityCharge_10 = new BigDecimal(0);
    BigDecimal dblTotalCharge_6 = new BigDecimal(0);
    BigDecimal dblTotalCharge_8 = new BigDecimal(0);
    BigDecimal dblTotalCharge_10 = new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_6 = new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_8 = new BigDecimal(0);
    BigDecimal dblNetAmtaddedtoFund_10 = new BigDecimal(0);
    BigDecimal dblSurrenderValue_6 = new BigDecimal(0);
    BigDecimal dblSurrenderValue_8 = new BigDecimal(0);
    BigDecimal dblSurrenderValue_10 = new BigDecimal(0);
    BigDecimal dblFundManagementCharge_6 = new BigDecimal(0);
    BigDecimal dblFundManagementCharge_8 = new BigDecimal(0);
    BigDecimal dblFundManagementCharge_10 = new BigDecimal(0);
    BigDecimal iAgeInsured = new BigDecimal(0);
    BigDecimal dblTempBaseSA = new BigDecimal(0);
    int iPolicyYear = 1;
    BigDecimal dblPAdminChargeValue;
    BigDecimal dblFundValueforSAR_6;
    BigDecimal dblFundValueforSAR_8;
    BigDecimal dblFundValueforSAR_10;
    int tempIPolicyYear = 1;
    int tempTerm = 0;
    BigDecimal bIntAge = intAge;
    BigDecimal dblProbability_qx;
    Calendar DatePac = Calendar.getInstance();
//    int yearPac=DatePac.get(Calendar.YEAR);

    try {
      prevIAgeInsured = bIntAge;
      tempDblPrem = bigDecPrem;
      dblTempBaseSA = bigDecBaseSA;

      proj.deleteManager(strPlanCode);

      if(intTerm>=10){
        tempTerm = 10;
      }else{
        tempTerm = intTerm;
      }

      PremAllocTableDto premalctbl = new PremAllocTableDto(this.context);
      PlanMortalityTableDto planMortDto = new PlanMortalityTableDto(this.context);
      PlanSvTableDto planSvTblDto = new PlanSvTableDto(this.context);

      List<PremAllocTable> premAllocList = premalctbl.select(iPolicyYear,tempTerm);
      if (premAllocList.size() > 0) {
        perValue = premAllocList.get(0).getPerValue();
      }

      if (perValue == null) {
        perValue = new BigDecimal(0);
      }

      BigDecimal dblPAC = perValue.multiply(tempDblPrem);

      while (counter <= intTerm * 12) {
        iAgeInsured = intAge.add(BigDecimal.valueOf(counter/12.0)).setScale(2, BigDecimal.ROUND_HALF_DOWN);

        List<PlanMortalityTable> planMortList = planMortDto.select(prevIAgeInsured, strPlanCode);

        if (planMortList.size() == 1) {
          qx = planMortList.get(0).getQx();
        }

        if (qx == null) {
          qx = new BigDecimal(0);
        }
        dblProbability_qx = qx;

        dblPAdminChargeValue = dblPAdminCharge.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN).multiply(
          BigDecimal.valueOf(Math.pow(dblPAdminChargeInflation.add(BigDecimal.ONE).doubleValue(),
            (new BigDecimal(DatePac.get(Calendar.YEAR)).subtract(BigDecimal.valueOf(2013)).doubleValue()))));

        dblFundManagementCharge_6=(dblFundvalue_End_6.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));
        dblFundManagementCharge_8=(dblFundvalue_End_8.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));
        dblFundManagementCharge_10=(dblFundvalue_End_10.add(tempDblPrem).subtract(dblPAC).multiply(BigDecimal.valueOf(0.0075/12)));

        dblFundValueforSAR_6 = dblFundvalue_End_6.add(tempDblPrem).subtract(dblPAC)
          .subtract(dblPAdminChargeValue).subtract(dblFundManagementCharge_6)
          .setScale(16, RoundingMode.HALF_DOWN);
        dblFundValueforSAR_8 = dblFundvalue_End_8.add(tempDblPrem).subtract(dblPAC)
          .subtract(dblPAdminChargeValue).subtract(dblFundManagementCharge_8)
          .setScale(14, RoundingMode.HALF_DOWN);
        dblFundValueforSAR_10 = dblFundvalue_End_10.add(tempDblPrem).subtract(dblPAC)
          .subtract(dblPAdminChargeValue).subtract(dblFundManagementCharge_10)
          .setScale(14, RoundingMode.HALF_DOWN);
        dblMortalityCharge_6 = bigDecBaseSA.subtract(dblFundValueforSAR_6).multiply(dblProbability_qx).setScale(0, RoundingMode.UP);
        dblMortalityCharge_8 = bigDecBaseSA.subtract(dblFundValueforSAR_8).multiply(dblProbability_qx).setScale(0, RoundingMode.UP);
        dblMortalityCharge_10 = bigDecBaseSA.subtract(dblFundValueforSAR_10).multiply(dblProbability_qx).setScale(0, RoundingMode.UP);
        if (dblFundValueforSAR_6.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_6 = BigDecimal.ZERO;
        }
        if (dblFundValueforSAR_8.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_8 = BigDecimal.ZERO;
        }
        if (dblFundValueforSAR_10.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_10 = BigDecimal.ZERO;
        }
        if (dblMortalityCharge_6.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_6 = BigDecimal.ZERO;
        }
        if (dblMortalityCharge_8.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_8 = BigDecimal.ZERO;
        }
        if (dblMortalityCharge_10.compareTo(BigDecimal.ZERO) < 0) {
          dblMortalityCharge_10 = BigDecimal.ZERO;
        }
        dblTotalCharge_6 = dblPAC.add(dblPAdminChargeValue.add(dblMortalityCharge_6)
          .add(dblFundManagementCharge_6));
        dblTotalCharge_8 = dblPAC.add(dblPAdminChargeValue.add(dblMortalityCharge_8)
          .add(dblFundManagementCharge_8));

        dblTotalCharge_10 = dblPAC.add(dblPAdminChargeValue.add(dblMortalityCharge_10)
          .add(dblFundManagementCharge_10));
        dblNetAmtaddedtoFund_6 = tempDblPrem.subtract(dblTotalCharge_6);
        dblNetAmtaddedtoFund_8 = tempDblPrem.subtract(dblTotalCharge_8);
        dblNetAmtaddedtoFund_10 = tempDblPrem.subtract(dblTotalCharge_10);
        dblFundvalue_End_6 = dblFundvalue_End_6.add(dblNetAmtaddedtoFund_6)
          .multiply(new BigDecimal(1 + 0.00486755056534305)).setScale(19, RoundingMode.HALF_DOWN);// CHECK
        dblFundvalue_End_8 = dblFundvalue_End_8.add(dblNetAmtaddedtoFund_8)
          .multiply(new BigDecimal(1 + 0.00643403011000343)).setScale(19, RoundingMode.HALF_DOWN);// CHECK
        dblFundvalue_End_10 = dblFundvalue_End_10.add(dblNetAmtaddedtoFund_10)
          .multiply(new BigDecimal(1 + 0.00797414042890376)).setScale(19, RoundingMode.HALF_DOWN);// CHECK

        List<PlanSvTable> planSvList = planSvTblDto.select(strPlanCode, tempIPolicyYear);
        if (planSvList.size() == 1) {
          factor = planSvList.get(0).getFactor();
        }

        if (factor == null) {
          factor = new BigDecimal(0);
        }
        dblSurrenderValue_6 = factor.multiply(dblFundvalue_End_6).setScale(19, RoundingMode.HALF_DOWN);
        dblSurrenderValue_8 = factor.multiply(dblFundvalue_End_8).setScale(19, RoundingMode.HALF_DOWN);
        dblSurrenderValue_10 = factor.multiply(dblFundvalue_End_10).setScale(19, RoundingMode.HALF_DOWN);
        oProjection projection = new oProjection();
        projection.setStep(counter);
        projection.setMonth(counterMonth);
        projection.setDurationMonth(counter);
        projection.setPolicyYear(iPolicyYear);
        projection.setiAgeInsured(iAgeInsured);
        projection.setDblProbability_qx(dblProbability_qx.setScale(19, RoundingMode.FLOOR));
        projection.setTempDblPrem(tempDblPrem.setScale(2, RoundingMode.HALF_DOWN));
        projection.setDblPAC(dblPAC.setScale(2, RoundingMode.HALF_DOWN));
        projection.setDblFundManagementCharge_6(dblFundManagementCharge_6.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundManagementCharge_8(dblFundManagementCharge_8.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundManagementCharge_10(dblFundManagementCharge_10.setScale(19, RoundingMode.FLOOR));
        projection.setDblPAdminChargeValue(dblPAdminChargeValue.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundValueforSAR_6(dblFundValueforSAR_6.setScale(19, RoundingMode.FLOOR));
        if (dblMortalityCharge_6.compareTo(BigDecimal.ZERO) == 1) {
          projection.setDblMortalityCharge_6(dblMortalityCharge_6.setScale(19, RoundingMode.FLOOR));
        } else {
          projection.setDblMortalityCharge_6(BigDecimal.ZERO);
        }
        projection.setDblBaseSA(bigDecBaseSA.setScale(19, RoundingMode.FLOOR));
        projection.setDblTotalCharge_6(dblTotalCharge_6.setScale(19, RoundingMode.FLOOR));
        projection.setDblNetAmtaddedtoFund_6(dblNetAmtaddedtoFund_6.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundvalue_Start_6(dblFundvalue_Start_6.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundvalue_End_6(dblFundvalue_End_6.setScale(19, RoundingMode.FLOOR));
        projection.setDblSurrenderValue_6(dblSurrenderValue_6.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundValueforSAR_8(dblFundValueforSAR_8.setScale(19, RoundingMode.FLOOR));
        if (dblMortalityCharge_8.compareTo(BigDecimal.ZERO) == 1) {
          projection.setDblMortalityCharge_8(dblMortalityCharge_8.setScale(19, RoundingMode.FLOOR));
        } else {
          projection.setDblMortalityCharge_8(BigDecimal.ZERO);
        }
        projection.setDblTotalCharge_8(dblTotalCharge_8.setScale(19, RoundingMode.FLOOR));
        projection.setDblNetAmtaddedtoFund_8(dblNetAmtaddedtoFund_8.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundvalue_Start_8(dblFundvalue_Start_8.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundvalue_End_8(dblFundvalue_End_8.setScale(19, RoundingMode.FLOOR));
        projection.setDblSurrenderValue_8(dblSurrenderValue_8.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundValueforSAR_10(dblFundValueforSAR_10.setScale(19, RoundingMode.FLOOR));
        if (dblMortalityCharge_10.compareTo(BigDecimal.ZERO) == 1) {
          projection.setDblMortalityCharge_10(dblMortalityCharge_10.setScale(19, RoundingMode.FLOOR));
        } else {
          projection.setDblMortalityCharge_10(BigDecimal.ZERO);
        }
        projection.setDblTotalCharge_10(dblTotalCharge_10.setScale(19, RoundingMode.FLOOR));
        projection.setDblNetAmtaddedtoFund_10(dblNetAmtaddedtoFund_10.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundvalue_Start_10(dblFundvalue_Start_10.setScale(19, RoundingMode.FLOOR));
        projection.setDblFundvalue_End_10(dblFundvalue_End_10.setScale(19, RoundingMode.FLOOR));
        projection.setDblSurrenderValue_10(dblSurrenderValue_10.setScale(19, RoundingMode.FLOOR));

        projectionList.add(projection);

        counter = counter + 1;
        counterMonth = counterMonth + 1;
        DatePac.add(Calendar.MONTH, 1);
        if (counterMonth == 12) {
          tempIPolicyYear = tempIPolicyYear + 1;
        }
        if (counterMonth > 12) {
          counterMonth = 1;
          iPolicyYear = iPolicyYear + 1;
          if (iPolicyYear > 1 && strPlanCode.equals("PRODUCT13")) {
            dblIncrPerc = 0.10 * (iPolicyYear - 1);
            bigDecBaseSA = (dblTempBaseSA.multiply(new BigDecimal(dblIncrPerc)).add(dblTempBaseSA));
          }
        }
        iValue = iValue + 1;
        dblFundvalue_Start_6 = dblFundvalue_End_6;
        dblFundvalue_Start_8 = dblFundvalue_End_8;
        dblFundvalue_Start_10 = dblFundvalue_End_10;

        if (intFreq == 3 || intFreq == 6 || intFreq == 12) {
          if (iValue == (intFreq + 1)) {
            iValue = 1;
            tempDblPrem = bigDecPrem;

            if(iPolicyYear<4)
            {
              List<PremAllocTable> premAllocListTemp = premalctbl.selectWithPlanCode(iPolicyYear,tempTerm,strPlanCode);
              perValue = premAllocListTemp.get(0).getPerValue();
              dblPAC = perValue.multiply(tempDblPrem);
            }else{
              dblPAC = new BigDecimal(0);
            }
          } else {
            perValue = new BigDecimal(0);
            dblPAC = perValue.multiply(tempDblPrem);
            tempDblPrem = new BigDecimal(0);
          }
        } else {
          if(iPolicyYear<4)
          {
            List<PremAllocTable> premAllocListTemp = premalctbl.selectWithPlanCode(iPolicyYear,tempTerm,strPlanCode);
            perValue = premAllocListTemp.get(0).getPerValue();
            dblPAC = perValue.multiply(tempDblPrem);
          }else{
            dblPAC = new BigDecimal(0);
          }
        }
        prevIAgeInsured = iAgeInsured;
      }
      if (projectionList != null) {
        if (projectionList.size() > 0) {
          proj.insertAll(projectionList);

          return projectionList;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
