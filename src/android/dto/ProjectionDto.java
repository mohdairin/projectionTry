package cordova.plugin.projection.dto;

import java.math.BigDecimal;
import java.util.List;
import android.content.Context;
import cordova.plugin.projection.pojo.oProjection;
import cordova.plugin.projection.pojo.Projection14_15;
import cordova.plugin.projection.pojo.Projection24;
import cordova.plugin.projection.pojo.AnnuityPayment;
import cordova.plugin.projection.sqlite.DBManager;

public class ProjectionDto {
	private Context context;

	public ProjectionDto(Context _context) {
		this.context = _context;
	}

	public void deleteManager(String plancode) {
    DBManager dbManager = new DBManager(this.context);
		try {
			dbManager.open();
			dbManager.delete(plancode);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
      dbManager.close();
    }
	}

	public void insertAll(List<oProjection> projectionList) {
    DBManager dbManager = new DBManager(this.context);
		try {
      dbManager.open();
			for (oProjection projection : projectionList) {
          dbManager.insertProjection(projection.getStep(),
          projection.getMonth(),
          projection.getDurationMonth(),
          projection.getPolicyYear(),
          projection.getiAgeInsured(),
          projection.getDblProbability_qx(),
          projection.getTempDblPrem(),
          projection.getDblPAC(),
          projection.getDblPAdminChargeValue(),
          projection.getDblFundManagementCharge_6(),
          projection.getDblFundManagementCharge_8(),
          projection.getDblFundManagementCharge_10(),
          projection.getDblMortalityCharge_6(),
          projection.getDblMortalityCharge_8(),
          projection.getDblMortalityCharge_10(),
          projection.getDblBaseSA(),
          projection.getDblTotalCharge_6(),
          projection.getDblNetAmtaddedtoFund_6(),
          projection.getDblFundValueforSAR_6(),
          projection.getDblFundvalue_Start_6(),
          projection.getDblFundvalue_End_6(),
          projection.getDblSurrenderValue_6(),
          projection.getDblTotalCharge_8(),
          projection.getDblNetAmtaddedtoFund_8(),
          projection.getDblFundValueforSAR_8(),
          projection.getDblFundvalue_Start_8(),
          projection.getDblFundvalue_End_8(),
          projection.getDblSurrenderValue_8(),
          projection.getDblTotalCharge_10(),
          projection.getDblNetAmtaddedtoFund_10(),
          projection.getDblFundValueforSAR_10(),
          projection.getDblFundvalue_Start_10(),
          projection.getDblFundvalue_End_10(),
          projection.getDblSurrenderValue_10());
			}
		} catch (Exception  e) {
			e.printStackTrace();
		} finally {
      dbManager.close();
    }
	}

	public void insertAllProjection(List<Projection14_15> projectionList) {
    DBManager dbManager = new DBManager(this.context);
		try {
      dbManager.open();
			for (Projection14_15 eachProjection : projectionList) {
				dbManager.insertProjection14_15(eachProjection.getStep(),
				eachProjection.getMonth(),
				eachProjection.getDurationMonth(),
				eachProjection.getPolicyYear(),
				eachProjection.getAgeInsured(),
				eachProjection.getProbabilityqx(),
				eachProjection.getTpdChargerateqx(),
				eachProjection.getPremiumIncome(),
				eachProjection.getPremiumAllocationCharge(),
				eachProjection.getDblFundManagementCharge_6(),
				eachProjection.getDblFundManagementCharge_8(),
				eachProjection.getDblFundManagementCharge_10(),
				eachProjection.getPolicyAdministrationCharge(),
				eachProjection.getDiscfuturePremiums(),
				eachProjection.getPremiumIncomeRisk(),
				eachProjection.getDiscfuturePremiumsRisk(),
				eachProjection.getWopChargedeath(),
				eachProjection.getWopChargetpd(),
				eachProjection.getWopChargeTotal(),
				eachProjection.getTotalCharge6(),
				eachProjection.getNetAmtaddedtoFund6(),
				eachProjection.getFundvalueStart6(),
				eachProjection.getFundvalueEnd6(),
				eachProjection.getSurrenderValue6(),
				eachProjection.getTotalCharge8(),
				eachProjection.getNetAmtaddedtoFund8(),
				eachProjection.getFundvalueStart8(),
				eachProjection.getFundvalueEnd8(),
				eachProjection.getSurrenderValue8(),
				eachProjection.getTotalCharge10(),
				eachProjection.getNetAmtAddedtoFund10(),
				eachProjection.getFundvalueStart10(),
				eachProjection.getFundvalueend10(),
				eachProjection.getSurrenderValue10(),
				eachProjection.getGemv(),
				eachProjection.getDvgemv(),
				eachProjection.getMcgemv());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
      dbManager.close();
    }
	}

	public void insertAnnuityPayments(List<AnnuityPayment> annuityPaymentList){
    DBManager dbManager = new DBManager(this.context);
		try{
      dbManager.open();
			for (AnnuityPayment eachAnnuityPayment : annuityPaymentList) {
				dbManager.insertAnnuityPayment(eachAnnuityPayment.getPayoutmonth(),
				eachAnnuityPayment.getAnnuityMonth6(),
				eachAnnuityPayment.getAnnuityMonth8(),
				eachAnnuityPayment.getAnnuityMonth10());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
      dbManager.close();
    }
	}

	public void insertAllProjection24(List<Projection24> projectionList){
    DBManager dbManager = new DBManager(this.context);
		try{
      dbManager.open();
			for (Projection24 eachProjection : projectionList) {
				dbManager.insertProjection24(
				eachProjection.getStep(),
				eachProjection.getMonth(),
				eachProjection.getDurationMonth(),
				eachProjection.getPolicyYear(),
				eachProjection.getAgeInsured(),
				eachProjection.getProbabilityqx(),
				eachProjection.getTpdChargerateqx(),
				eachProjection.getPremiumIncome(),
				eachProjection.getPremiumAllocationCharge(),
				eachProjection.getDblFundManagementCharge_6(),
				eachProjection.getDblFundManagementCharge_8(),
				eachProjection.getDblFundManagementCharge_10(),
				eachProjection.getPolicyAdministrationCharge(),
				eachProjection.getPremiumIncomeRisk(),
				eachProjection.getDiscfuturePremiumsRisk(),
				eachProjection.getDiscfuturePremiums(),
				eachProjection.getWopChargedeath(),
				eachProjection.getWopChargetpd(),
				eachProjection.getWopChargeTotal(),
				eachProjection.getTotalCharge6(),
				eachProjection.getNetAmtaddedtoFund6(),
				eachProjection.getFundvalueStart6(),
				eachProjection.getFundvalueEnd6(),
				eachProjection.getSurrenderValue6(),
				eachProjection.getTotalCharge8(),
				eachProjection.getNetAmtaddedtoFund8(),
				eachProjection.getFundvalueStart8(),
				eachProjection.getFundvalueEnd8(),
				eachProjection.getSurrenderValue8(),
				eachProjection.getTotalCharge10(),
				eachProjection.getNetAmtAddedtoFund10(),
				eachProjection.getFundvalueStart10(),
				eachProjection.getFundvalueend10(),
				eachProjection.getSurrenderValue10(),
				eachProjection.getGemv(),
				eachProjection.getDvgemv(),
				eachProjection.getMcgemv());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
      dbManager.close();
    }
	}

  public void insertAllCashFlow(List<cordova.plugin.projection.pojo.ProductCashFlow> cashFlowList)  {
    DBManager dbManager = new DBManager(this.context);
    try {
      dbManager.open();
      for (cordova.plugin.projection.pojo.ProductCashFlow eachCashFlow : cashFlowList) {

        java.sql.Date sqlDate = new java.sql.Date(eachCashFlow.getDateBegOfmonth().getTime());

        dbManager.insertProduct23_cashflow(
          eachCashFlow.getStep(),
          eachCashFlow.getMonth(),
          eachCashFlow.getStep(),
          eachCashFlow.getPolicyYear(),
          sqlDate.toString(),
          eachCashFlow.getYearBegOfmonth(),
          eachCashFlow.getAgeInsured(),
          eachCashFlow.getIndProbDeathQx(),
          eachCashFlow.getPremiumIncome(),
          eachCashFlow.getPremiumAllocationCharge(),
          eachCashFlow.getFmc6(),
          eachCashFlow.getFmc8(),
          eachCashFlow.getFmc10(),
          eachCashFlow.getPac(),
          eachCashFlow.getMc6(),
          eachCashFlow.getMc8(),
          eachCashFlow.getMc10(),
          eachCashFlow.getDeathSa(),
          eachCashFlow.getTotalCharges6(),
          eachCashFlow.getNetAmtAddedtoFund6(),
          eachCashFlow.getFundvalueSAR6(),
          eachCashFlow.getFundvalueStart6(),
          eachCashFlow.getFundvalueEnd6(),
          eachCashFlow.getTotalCharges8(),
          eachCashFlow.getNetAmtAddedtoFund8(),
          eachCashFlow.getFundvalueSAR8(),
          eachCashFlow.getFundvalueStart8(),
          eachCashFlow.getFundvalueEnd8(),
          eachCashFlow.getTotalCharges10(),
          eachCashFlow.getNetAmtAddedtoFund10(),
          eachCashFlow.getFundvalueSAR10(),
          eachCashFlow.getFundvalueStart10(),
          eachCashFlow.getFundvalueEnd10(),
          eachCashFlow.getDeathBenefit6(),
          eachCashFlow.getDeathBenefit8(),
          eachCashFlow.getDeathBenefit10(),
          eachCashFlow.getDeathSAR6(),
          eachCashFlow.getDeathSAR8(),
          eachCashFlow.getDeathSAR10());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      dbManager.close();
    }
  }

  public BigDecimal fetchPremiumRate(int intTerm,int intAge){
    BigDecimal annualRate;
    DBManager dbManager = new DBManager(this.context);

    String sql = "select PremRate from gnmm_premium where Plancode = 'RLTR024' and "+intAge+" between StartAge and EndAge and  "+intTerm+" between PlanTermRangeStart and PlanTermRangeEnd";
    annualRate = dbManager.queueAll_new(sql);

    return annualRate;
  }

  public BigDecimal fetchAnnualPremium(BigDecimal dblBaseSA,int intTerm,BigDecimal intAge){
    BigDecimal annualRate;
    DBManager dbManager = new DBManager(this.context);

    String sql = "select rate*"+dblBaseSA+"/1000 as AnnualRate  from annualpremiumrate where "+intTerm+" between fromterm and toterm and (ROUND("+intAge+",0)+1) between fromage and toage";
    annualRate = dbManager.queueAll_new(sql);

    return annualRate;
  }

  public BigDecimal fetchAnnualRatePremium(BigDecimal dblBaseSA,BigDecimal intAge,int intTerm){
    BigDecimal annualRate;
    DBManager dbManager = new DBManager(this.context);

    String sql =  "select PremRate*"+dblBaseSA+"/1000 as AnnualRatePremium  from gnmm_premium where Plancode = 'RLTR024' and ROUND("+intAge+") between StartAge and EndAge and  "+intTerm+" between PlanTermRangeStart and PlanTermRangeEnd";
    annualRate = dbManager.queueAll_new(sql);

    return annualRate;
  }
}
