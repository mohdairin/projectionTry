package cordova.plugin.projection.util;

import java.math.BigDecimal;

public class Query {
    public static String FETCH_PER_VALUE_Airin = "select pervalue from prem_alloc_table where Plancode = 'PRODUCT12' and YEAR = ?";

	public static String FETCH_PER_VALUE = "select pervalue from prem_alloc_table where YEAR = ?";
	public static String FETCH_FACTOR = "select factor from plan_sv_table where plancode=? AND YEAR=?";
	public static String FETCH_PROJECTIONS = "select * from projection";
	public static String DELETE_PROJECTIONS = "delete from projection";
	public static String INSERT_PROJECTION = "INSERT INTO projection(Step,MONTH,DurationMonth,PolicyYear,AgeInsured,Probability_qx,PremiumIncome, "
			+ " PremiumAllocationCharge,FundManagementCharge,PolicyAdministrationCharge,FundvalueSAR_6, "
			+ " MortalityCharge_6,TotalCharge_6,NetAmtaddedtoFund_6,Fundvalue_Start_6,Fundvalue_End_6,SurrenderValue_6, "
			+ " FundValueSAR_8,MortalityCharge_8,TotalCharge_8,NetAmtaddedtoFund_8,Fundvalue_Start_8,Fundvalue_End_8,SurrenderValue_8, "
			+ " FundValueSAR_10,MortalityCharge_10,TotalCharge_10,NetAmtAddedtoFund_10,Fundvalue_Start_10,Fundvalue_end_10,SurrenderValue_10 "
			+ " ) VALUES  " + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	public static String INSERT_PROJECTIONS = "INSERT INTO projection(Step,MONTH,DurationMonth,PolicyYear,AgeInsured,Probability_qx,PremiumIncome, "
			+ " PremiumAllocationCharge,FundManagementCharge,PolicyAdministrationCharge,DeathBenefit,FundvalueSAR_6, "
			+ " MortalityCharge_6,TotalCharge_6,NetAmtaddedtoFund_6,Fundvalue_Start_6,Fundvalue_End_6,SurrenderValue_6, "
			+ " FundValueSAR_8,MortalityCharge_8,TotalCharge_8,NetAmtaddedtoFund_8,Fundvalue_Start_8,Fundvalue_End_8,SurrenderValue_8, "
			+ " FundValueSAR_10,MortalityCharge_10,TotalCharge_10,NetAmtAddedtoFund_10,Fundvalue_Start_10,Fundvalue_end_10,SurrenderValue_10 "
			+ " ) VALUES ";
	public static String FETCH_QX = "select ROUND(1-POWer(1-qx,0.08333333333333),19) as qx from planmortalitytable Where age=? and Plancode=?";
	public static String INSERT_PROJECTIONS_TEMP = "INSERT INTO projectiontemp(Step,MONTH,DurationMonth,PolicyYear,AgeInsured,Probability_qx,PremiumIncome, "
			+ " PremiumAllocationCharge,FundManagementCharge,PolicyAdministrationCharge,DeathBenefit,FundvalueSAR_6, "
			+ " MortalityCharge_6,TotalCharge_6,NetAmtaddedtoFund_6,Fundvalue_Start_6,Fundvalue_End_6,SurrenderValue_6, "
			+ " FundValueSAR_8,MortalityCharge_8,TotalCharge_8,NetAmtaddedtoFund_8,Fundvalue_Start_8,Fundvalue_End_8,SurrenderValue_8, "
			+ " FundValueSAR_10,MortalityCharge_10,TotalCharge_10,NetAmtAddedtoFund_10,Fundvalue_Start_10,Fundvalue_end_10,SurrenderValue_10 "
			+ " ) VALUES ";
	public static String DELETE_PRODUCT_CASH_FLOW = "delete from Product23_cashflow";
	public static String FETCH_PER_VALUE1 = "select pervalue from prem_alloc_table where YEAR = ? and Plancode=?";
	public static String FETCH_RATE = "select rate/12/1000 from planmortalitytable where plancode=?  and gender=? and age=(?+(?/12))+1 ";
	public static String INSERT_PRODUCT_CASH_FLOW = "INSERT INTO Product23_cashflow(Step,Month,DurationMonth,PolicyYear,Date_BegofMonth,Year_BegofMonth,AgeInsured,Ind_Prob_Death_qx,Premium_Income,Premium_allocation_charge,FMC_6,FMC_8,FMC_10,PAC,MC_6,MC_8,MC_10,Death_SA,Total_Charges_6,NetAmtAddedtoFund_6,FundvalueSAR_6,FundvalueStart_6,FundvalueEnd_6,Total_Charges_8,NetAmtAddedtoFund_8,FundvalueSAR_8,FundvalueStart_8,FundvalueEnd_8,Total_Charges_10,NetAmtAddedtoFund_10,FundvalueSAR_10,FundvalueStart_10,FundvalueEnd_10,DeathBenefit_6,DeathBenefit_8,DeathBenefit_10,DeathSAR_6,DeathSAR_8,DeathSAR_10) VALUES ";
	public static String FETCH_ANNUAL_RATE = "select rate*?/1000 as AnnualRate  from annualpremiumrate where ? between fromterm and toterm and ? between fromage and toage";
	public static String DELETE_PROJECTIONS14_15 = "delete from projection14_15";
	public static String INSERT_PROJECTIONS14_15 = "INSERT INTO projection14_15(Step,Month,DurationMonth,PolicyYear,AgeInsured,Probability_qx,TPD_charge_rate_qx,PremiumIncome,PremiumAllocationCharge,FundManagementCharge,PolicyAdministrationCharge,Discfuturepremiums,WoP_charge_death,WoP_charge_tpd,WoP_charge_total,TotalCharge_6,NetAmtaddedtoFund_6,Fundvalue_Start_6,Fundvalue_End_6,SurrenderValue_6,TotalCharge_8,NetAmtaddedtoFund_8,Fundvalue_Start_8,Fundvalue_End_8,SurrenderValue_8,TotalCharge_10,NetAmtAddedtoFund_10,Fundvalue_Start_10,Fundvalue_end_10,SurrenderValue_10,GEMV,DVGEMV,MCGEMV) VALUES ";
	public static String INSERT_PROJECTIONS14 = "INSERT INTO projection14_15(Step,Month,DurationMonth,PolicyYear,AgeInsured,Probability_qx,TPD_charge_rate_qx,PremiumIncome,PremiumAllocationCharge,FundManagementCharge,PolicyAdministrationCharge,Discfuturepremiums,WoP_charge_death,WoP_charge_tpd,WoP_charge_total,TotalCharge_6,NetAmtaddedtoFund_6,Fundvalue_Start_6,Fundvalue_End_6,SurrenderValue_6,TotalCharge_8,NetAmtaddedtoFund_8,Fundvalue_Start_8,Fundvalue_End_8,SurrenderValue_8,TotalCharge_10,NetAmtAddedtoFund_10,Fundvalue_Start_10,Fundvalue_end_10,SurrenderValue_10,GEMV,DVGEMV,MCGEMV,PremiumIncomeWithOutRisk,DiscfuturepremiumsWithOutRisk) VALUES ";
	public static String FETCH_PV_VALUE = "select ?/POWER(1+0.00407412378364835,?) as pv";
	public static String FETCH_PV_VALUE(BigDecimal prem, int step) {
		String query = "select " + prem + "/POWer(1+0.00407412378364835," + step + ") as pv";
		return query;
	}
	public static String DELETE_ANNUITY_PAYMENTS = "delete from AnnuityPayment";
	public static String INSERT_ANNUITY_PAYMENT = "INSERT INTO AnnuityPayment(payoutmonth,AnnuityMonth_6,AnnuityMonth_8,AnnuityMonth_10) VALUES ";
	public static String INSERT_ANNUITY_PAYMENTS = "INSERT INTO AnnuityPayment(payoutmonth,AnnuityMonth_6,AnnuityMonth_8,AnnuityMonth_10) VALUES ";
	public static String FETCH_ANNUAL_RATE_PREMIUM = "select PremRate*?/1000 as AnnualRatePremium  from gnmm_premium where Plancode = 'RLTR024' and ? between StartAge and EndAge and  ? between PlanTermRangeStart and PlanTermRangeEnd";
	public static String FETCH_QX1 = "select ROUND(1-POWer(1-qx,0.08333333333333),19) as qx from planmortalitytable Where age=(ROUND(?,0)) and Plancode=?";
	public static String FETCH_PREMIUM_RATE = "select PremRate  from gnmm_premium where Plancode = 'RLTR024' and ? between StartAge and EndAge and  ? between PlanTermRangeStart and PlanTermRangeEnd";
}
