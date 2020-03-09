package cordova.plugin.projection.action;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.content.Context;
import cordova.plugin.projection.dto.ProjectionDto;
import cordova.plugin.projection.dto.PremAllocTableDto;
import cordova.plugin.projection.dto.PlanMortalityTableDto;
import cordova.plugin.projection.pojo.ProductCashFlow;

public class ProductCashFlowAction {

	private Context context;

	public ProductCashFlowAction(Context _context) {
	 this.context = _context;
	}

	public List<ProductCashFlow> generateProductCashFlow(
		String strPlanCode, int intTerm, BigDecimal intAge,int intFreq,BigDecimal bigDecPrem,
		BigDecimal bigDecBaseSA, String strGender, BigDecimal bigDecInflation_Guard){

		List<ProductCashFlow> cashFlowList = new ArrayList<ProductCashFlow>();
		int counter = 1;
		int counterMonth = 1;
		int ivalue = 1;
		int ipolicyYear = 1;
		int tempIPolicyYear = 1;
		BigDecimal dblProbability_qx = new BigDecimal(0);
		BigDecimal premiumIncrease = new BigDecimal(0);
		BigDecimal premiumIncome = new BigDecimal(0);
		BigDecimal dblPAC = new BigDecimal(0);
		BigDecimal dblFMC = new BigDecimal(0.0075);
		BigDecimal dblPolicyAdminChg = new BigDecimal(200);
		BigDecimal dblPolicyAdminChg_Inflation = new BigDecimal(0.10);
		BigDecimal sumPremium = new BigDecimal(0);
		BigDecimal SABASE = new BigDecimal(1.25);
		BigDecimal fundValueEnd_6 = new BigDecimal(0);
		BigDecimal fundValueEnd_8 = new BigDecimal(0);
		BigDecimal fundValueEnd_10 = new BigDecimal(0);
		BigDecimal fundvalueSAR_6 = new BigDecimal(0);
		BigDecimal fundvalueSAR_8 = new BigDecimal(0);
		BigDecimal fundvalueSAR_10 = new BigDecimal(0);
		BigDecimal fundvalueStart_6 = new BigDecimal(0);
		BigDecimal fundvalueStart_8 = new BigDecimal(0);
		BigDecimal fundvalueStart_10 = new BigDecimal(0);
		BigDecimal mc_6 = new BigDecimal(0);
		BigDecimal mc_8 = new BigDecimal(0);
		BigDecimal mc_10 = new BigDecimal(0);
		BigDecimal fmc_6 = new BigDecimal(0);
		BigDecimal fmc_8 = new BigDecimal(0);
		BigDecimal fmc_10 = new BigDecimal(0);
		BigDecimal netAmtAddedtoFund_6 = new BigDecimal(0);
		BigDecimal netAmtAddedtoFund_8 = new BigDecimal(0);
		BigDecimal netAmtAddedtoFund_10 = new BigDecimal(0);
		BigDecimal deathSA = new BigDecimal(0);
		BigDecimal totalCharges_6 = new BigDecimal(0);
		BigDecimal totalCharges_8 = new BigDecimal(0);
		BigDecimal totalCharges_10 = new BigDecimal(0);
		BigDecimal dividendRate_6 =new BigDecimal(0.00486755056534305);
		BigDecimal dividendRate_8 =new BigDecimal(0.00643403011000343);
		BigDecimal dividendRate_10 =new BigDecimal(0.00797414042890376);
		BigDecimal deathBenefit_6;
		BigDecimal deathBenefit_8;
		BigDecimal deathBenefit_10;
		BigDecimal deathSAR_6;
		BigDecimal deathSAR_8;
		BigDecimal deathSAR_10;
		BigDecimal perValue = new BigDecimal(0);
		BigDecimal iAgeInsured =  new BigDecimal(0);
		BigDecimal prevIAgeInsured =  new BigDecimal(0);
		prevIAgeInsured = intAge;

    ProjectionDto projectionDto=new ProjectionDto(this.context);
    PremAllocTableDto premAllocTableDto = new PremAllocTableDto(this.context);
    PlanMortalityTableDto planMortalityTableDto = new PlanMortalityTableDto(this.context);

		try {
      projectionDto.deleteManager(strPlanCode);

			premiumIncome=bigDecPrem.multiply(BigDecimal.valueOf(Math.pow(bigDecInflation_Guard.add(BigDecimal.ONE).add(premiumIncrease).doubleValue(),(new BigDecimal(ipolicyYear).subtract(BigDecimal.ONE).doubleValue()))));
			sumPremium=premiumIncome;

      perValue = premAllocTableDto.select_pervalue(ipolicyYear,strPlanCode);
      if (perValue == null) {
				perValue = new BigDecimal(0);
			}

			dblPAC = perValue.multiply(bigDecPrem);

			while (counter <= intTerm * 12) {
				ProductCashFlow eachCashFlow=new ProductCashFlow();
				iAgeInsured = intAge.add(new BigDecimal(counter).divide(new BigDecimal(12),0,RoundingMode.HALF_UP));

				dblProbability_qx = planMortalityTableDto.fetchRate(strPlanCode, strGender, prevIAgeInsured);

				deathSA=SABASE.multiply(sumPremium);

				fmc_6=(fundValueEnd_6.add(premiumIncome).subtract(dblPAC)).multiply(new BigDecimal(0.0075 / 12));
				fmc_8=(fundValueEnd_8.add(premiumIncome).subtract(dblPAC)).multiply((new BigDecimal(0.0075 / 12)));
				fmc_10=(fundValueEnd_10.add(premiumIncome).subtract(dblPAC)).multiply((new BigDecimal(0.0075 / 12)));

				Calendar today=Calendar.getInstance();
				today.set(Calendar.MONTH, today.get(Calendar.MONTH)+counter-1);
				int year=today.get(Calendar.YEAR);

				fundvalueSAR_6=fundValueEnd_6.add(premiumIncome).subtract(dblPAC).subtract(fmc_6).
				subtract(dblPolicyAdminChg.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(Math.pow((dblPolicyAdminChg_Inflation.add(BigDecimal.ONE).doubleValue()),  year-2013))));
				fundvalueSAR_8=fundValueEnd_8.add(premiumIncome).subtract(dblPAC).subtract(fmc_8).
				subtract(dblPolicyAdminChg.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(Math.pow((dblPolicyAdminChg_Inflation.add(BigDecimal.ONE).doubleValue()),  year-2013))));
				fundvalueSAR_10=fundValueEnd_10.add(premiumIncome).subtract(dblPAC).subtract(fmc_10).
				subtract(dblPolicyAdminChg.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(Math.pow((dblPolicyAdminChg_Inflation.add(BigDecimal.ONE).doubleValue()),  year-2013))));

        mc_6=new BigDecimal(Math.ceil((deathSA.subtract(fundvalueSAR_6)).multiply(dblProbability_qx).doubleValue()));
				if(mc_6.compareTo(BigDecimal.ZERO)<0){
					mc_6=BigDecimal.ZERO;
				}
				mc_8=new BigDecimal(Math.ceil((deathSA.subtract(fundvalueSAR_8)).multiply(dblProbability_qx).doubleValue()));
				if(mc_8.compareTo(BigDecimal.ZERO)<0){
					mc_8=BigDecimal.ZERO;
				}
				mc_10=new BigDecimal(Math.ceil((deathSA.subtract(fundvalueSAR_10)).multiply(dblProbability_qx).doubleValue()));
				if(mc_10.compareTo(BigDecimal.ZERO)<0){
					mc_10=BigDecimal.ZERO;
				}

				fundvalueStart_6=fundValueEnd_6;
				fundvalueStart_8=fundValueEnd_8;
				fundvalueStart_10=fundValueEnd_10;

				Date beggingOfDate=new Date();
				beggingOfDate.setMonth(beggingOfDate.getMonth()+counter-1);
				BigDecimal policyAdminChages=dblPolicyAdminChg.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(Math.pow((dblPolicyAdminChg_Inflation.add(BigDecimal.ONE).doubleValue()),  year-2013)));

				totalCharges_6=dblPAC.add((fundValueEnd_6.add(premiumIncome).subtract(dblPAC)).multiply(dblFMC.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN))).add(policyAdminChages).add(mc_6);
				totalCharges_8=dblPAC.add((fundValueEnd_8.add(premiumIncome).subtract(dblPAC)).multiply(dblFMC.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN))).add(policyAdminChages).add(mc_8);
				totalCharges_10=dblPAC.add((fundValueEnd_10.add(premiumIncome).subtract(dblPAC)).multiply(dblFMC.divide(new BigDecimal(12), 14, RoundingMode.HALF_DOWN))).add(policyAdminChages).add(mc_10);

				netAmtAddedtoFund_6=premiumIncome.subtract(totalCharges_6);
				netAmtAddedtoFund_8=premiumIncome.subtract(totalCharges_8);
				netAmtAddedtoFund_10=premiumIncome.subtract(totalCharges_10);

				fundValueEnd_6=(fundValueEnd_6.add(netAmtAddedtoFund_6)).multiply(BigDecimal.ONE.add(dividendRate_6));
				fundValueEnd_8=(fundValueEnd_8.add(netAmtAddedtoFund_8)).multiply(BigDecimal.ONE.add(dividendRate_8));
				fundValueEnd_10=(fundValueEnd_10.add(netAmtAddedtoFund_10)).multiply(BigDecimal.ONE.add(dividendRate_10));

				deathBenefit_6=deathSA;
				deathBenefit_8=deathSA;
				deathBenefit_10=deathSA;

				if(deathBenefit_6.compareTo(fundValueEnd_6)<0){
					deathBenefit_6=fundValueEnd_6;
				}
				if(deathBenefit_8.compareTo(fundValueEnd_8)<0){
					deathBenefit_8=fundValueEnd_8;
				}
				if(deathBenefit_10.compareTo(fundValueEnd_10)<0){
					deathBenefit_10=fundValueEnd_10;
				}

				deathSAR_6=deathBenefit_6.subtract(fundValueEnd_6);
				deathSAR_8=deathBenefit_8.subtract(fundValueEnd_8);
				deathSAR_10=deathBenefit_10.subtract(fundValueEnd_10);
				if(deathSAR_6.compareTo(BigDecimal.ZERO)<=0){
					deathSAR_6=BigDecimal.ZERO;
				}
				if(deathSAR_8.compareTo(BigDecimal.ZERO)<=0){
					deathSAR_8=BigDecimal.ZERO;
				}
				if(deathSAR_10.compareTo(BigDecimal.ZERO)<=0){
					deathSAR_10=BigDecimal.ZERO;
				}

				eachCashFlow.setStep(counter);//Step
				eachCashFlow.setMonth(counterMonth);//Month
				eachCashFlow.setDurationMonth(counter);//DurationMonth
				eachCashFlow.setPolicyYear(ipolicyYear);//PolicyYear  ???
				eachCashFlow.setDateBegOfmonth(beggingOfDate);//Date_BegofMonth
				eachCashFlow.setYearBegOfmonth(year);//Year_BegofMonth ???
				eachCashFlow.setAgeInsured(iAgeInsured);//AgeInsured
				eachCashFlow.setIndProbDeathQx(dblProbability_qx.setScale(19, RoundingMode.FLOOR));//Ind_Prob_Death_qx
				eachCashFlow.setPremiumIncome(premiumIncome.setScale(19, RoundingMode.FLOOR));//Premium_Income
				eachCashFlow.setPremiumAllocationCharge(dblPAC.setScale(19, RoundingMode.FLOOR));//Premium_allocation_charge
				eachCashFlow.setFmc6(fmc_6.setScale(19, RoundingMode.FLOOR));//FMC_6
				eachCashFlow.setFmc8(fmc_8.setScale(19, RoundingMode.FLOOR));//FMC_8
				eachCashFlow.setFmc10(fmc_10.setScale(19, RoundingMode.FLOOR));//FMC_10
				eachCashFlow.setPac(policyAdminChages.setScale(19, RoundingMode.FLOOR));//PAC
				eachCashFlow.setMc6(mc_6.setScale(19, RoundingMode.FLOOR));//MC_6
				eachCashFlow.setMc8(mc_8.setScale(19, RoundingMode.FLOOR));//MC_8
				eachCashFlow.setMc10(mc_10.setScale(19, RoundingMode.FLOOR));//MC_10
				eachCashFlow.setDeathSa(deathSA.setScale(19, RoundingMode.FLOOR));//Death_SA
				eachCashFlow.setTotalCharges6(totalCharges_6.setScale(19, RoundingMode.FLOOR));//Total_Charges_6
				eachCashFlow.setNetAmtAddedtoFund6(netAmtAddedtoFund_6.setScale(19, RoundingMode.FLOOR));//NetAmtAddedtoFund_6
				eachCashFlow.setFundvalueSAR6(fundvalueSAR_6.setScale(19, RoundingMode.FLOOR));//FundvalueSAR_6
				eachCashFlow.setFundvalueStart6(fundvalueStart_6.setScale(19, RoundingMode.FLOOR));//FundvalueStart_6
				eachCashFlow.setFundvalueEnd6(fundValueEnd_6.setScale(19, RoundingMode.FLOOR));//FundvalueEnd_6
				eachCashFlow.setTotalCharges8(totalCharges_8.setScale(19, RoundingMode.FLOOR));//Total_Charges_8
				eachCashFlow.setNetAmtAddedtoFund8(netAmtAddedtoFund_8.setScale(19, RoundingMode.FLOOR));//NetAmtAddedtoFund_8
				eachCashFlow.setFundvalueSAR8(fundvalueSAR_8.setScale(19, RoundingMode.FLOOR));//FundvalueSAR_8
				eachCashFlow.setFundvalueStart8(fundvalueStart_8.setScale(19, RoundingMode.FLOOR));//FundvalueStart_8
				eachCashFlow.setFundvalueEnd8(fundValueEnd_8.setScale(19, RoundingMode.FLOOR));//FundvalueEnd_8
				eachCashFlow.setTotalCharges10(totalCharges_10.setScale(19, RoundingMode.FLOOR));//Total_Charges_10
				eachCashFlow.setNetAmtAddedtoFund10(netAmtAddedtoFund_10.setScale(19, RoundingMode.FLOOR));//NetAmtAddedtoFund_10
				eachCashFlow.setFundvalueSAR10(fundvalueSAR_10.setScale(19, RoundingMode.FLOOR));//FundvalueSAR_10
				eachCashFlow.setFundvalueStart10(fundvalueStart_10.setScale(19, RoundingMode.FLOOR));//FundvalueStart_10
				eachCashFlow.setFundvalueEnd10(fundValueEnd_10.setScale(19, RoundingMode.FLOOR));//FundvalueEnd_10
				eachCashFlow.setDeathBenefit6(deathBenefit_6.setScale(19, RoundingMode.FLOOR));//DeathBenefit_6
				eachCashFlow.setDeathBenefit8(deathBenefit_8.setScale(19, RoundingMode.FLOOR));//DeathBenefit_8
				eachCashFlow.setDeathBenefit10(deathBenefit_10.setScale(19, RoundingMode.FLOOR));//DeathBenefit_10
				eachCashFlow.setDeathSAR6(deathSAR_6.setScale(19, RoundingMode.FLOOR));//DeathSAR_6
				eachCashFlow.setDeathSAR8(deathSAR_8.setScale(19, RoundingMode.FLOOR));//DeathSAR_8
				eachCashFlow.setDeathSAR10(deathSAR_10.setScale(19, RoundingMode.FLOOR));//DeathSAR_10
				cashFlowList.add(eachCashFlow);

				counter=counter+1;
				counterMonth=counterMonth+1;
				if(counterMonth==12){
					tempIPolicyYear=tempIPolicyYear+1;
				}
				if(counterMonth>12){
					counterMonth=1;
					ipolicyYear=ipolicyYear+1;
				}
				ivalue=ivalue+1;
				if(intFreq==3||intFreq==6||intFreq==12){
					if(ivalue==intFreq+1){
						ivalue=1;
						premiumIncome=bigDecPrem.multiply(BigDecimal.valueOf(Math.pow(bigDecInflation_Guard.add(premiumIncrease).add(BigDecimal.ONE).doubleValue(),ipolicyYear-1)));
						BigDecimal dblPACTemp = premAllocTableDto.select_pervalue(ipolicyYear,strPlanCode);
						sumPremium=sumPremium.add(premiumIncome);
						if (dblPACTemp.compareTo(BigDecimal.ZERO)<1) {
							dblPAC=BigDecimal.ZERO;
						}else{
							dblPAC=dblPACTemp.multiply(premiumIncome);
						}
					}else{
						premiumIncome=BigDecimal.ZERO;
						dblPAC=BigDecimal.ZERO;
					}
				}
				if(intFreq==1){
					premiumIncome=bigDecPrem.multiply(BigDecimal.valueOf(Math.pow(bigDecInflation_Guard.add(premiumIncrease).add(BigDecimal.ONE).doubleValue(),ipolicyYear-1)));
					BigDecimal dblPACTemp = premAllocTableDto.select_pervalue(ipolicyYear,strPlanCode);
					if (dblPACTemp.compareTo(BigDecimal.ZERO)<1) {
						dblPAC=BigDecimal.ZERO;
					}else{
						dblPAC=dblPACTemp.multiply(premiumIncome);
					}
					sumPremium=sumPremium.add(premiumIncome);
				}

				prevIAgeInsured = iAgeInsured;
			}
			//Inserting all records
      projectionDto.insertAllCashFlow(cashFlowList);

		} catch(Exception e){
			e.printStackTrace();
		}

    return cashFlowList;
	}
}
