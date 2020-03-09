package cordova.plugin.projection.sqlite;

import java.math.*;
import java.lang.Math;
import java.util.*;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.math.BigDecimal;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import cordova.plugin.projection.sqlite.SQLiteHelper;

public class DBManager{
    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public DBManager(Context c) {
        this.context = c;
    }

    public DBManager open() throws SQLException {
        this.dbHelper = new SQLiteHelper(this.context);
        this.database = this.dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.database.close();
    }

    public void insertProjection(
        int Step, int Month,int DurationMonth,int PolicyYear,BigDecimal AgeInsured,
        BigDecimal Probability_qx, BigDecimal PremiumIncome,BigDecimal PremiumAllocationCharge,
        BigDecimal PolicyAdministrationCharge,BigDecimal FundManagementCharge_6,
        BigDecimal FundManagementCharge_8,BigDecimal FundManagementCharge_10,
        BigDecimal MortalityCharge_6, BigDecimal MortalityCharge_8,
        BigDecimal MortalityCharge_10,BigDecimal DeathBenefit,
        BigDecimal TotalCharge_6,BigDecimal NetAmtaddedtoFund_6, BigDecimal FundvalueSAR_6,
        BigDecimal Fundvalue_Start_6,BigDecimal Fundvalue_End_6,BigDecimal SurrenderValue_6,
        BigDecimal TotalCharge_8, BigDecimal NetAmtaddedtoFund_8,BigDecimal FundValueSAR_8,
        BigDecimal Fundvalue_Start_8,BigDecimal Fundvalue_End_8,BigDecimal SurrenderValue_8,
        BigDecimal TotalCharge_10,BigDecimal NetAmtAddedtoFund_10,BigDecimal FundValueSAR_10,
        BigDecimal Fundvalue_Start_10,BigDecimal Fundvalue_end_10,BigDecimal SurrenderValue_10) {
         ContentValues contentValue = new ContentValues();
         contentValue.put(SQLiteHelper.Step, Step);
         contentValue.put(SQLiteHelper.Month, Month);
         contentValue.put(SQLiteHelper.DurationMonth, DurationMonth);
         contentValue.put(SQLiteHelper.PolicyYear, PolicyYear);
         contentValue.put(SQLiteHelper.AgeInsured, AgeInsured.doubleValue());
         contentValue.put(SQLiteHelper.Probability_qx, Probability_qx.doubleValue());
         contentValue.put(SQLiteHelper.PremiumIncome, PremiumIncome.doubleValue());
         contentValue.put(SQLiteHelper.PremiumAllocationCharge, PremiumAllocationCharge.doubleValue());
         contentValue.put(SQLiteHelper.PolicyAdministrationCharge, PolicyAdministrationCharge.doubleValue());
         contentValue.put(SQLiteHelper.FundManagementCharge_6, FundManagementCharge_6.doubleValue());
         contentValue.put(SQLiteHelper.FundManagementCharge_8, FundManagementCharge_8.doubleValue());
         contentValue.put(SQLiteHelper.FundManagementCharge_10, FundManagementCharge_10.doubleValue());
         contentValue.put(SQLiteHelper.MortalityCharge_6, MortalityCharge_6.doubleValue());
         contentValue.put(SQLiteHelper.MortalityCharge_8, MortalityCharge_8.doubleValue());
         contentValue.put(SQLiteHelper.MortalityCharge_10, MortalityCharge_10.doubleValue());
         contentValue.put(SQLiteHelper.DeathBenefit, DeathBenefit.doubleValue());
         contentValue.put(SQLiteHelper.TotalCharge_6, TotalCharge_6.doubleValue());
         contentValue.put(SQLiteHelper.NetAmtaddedtoFund_6, NetAmtaddedtoFund_6.doubleValue());
         contentValue.put(SQLiteHelper.FundvalueSAR_6, FundvalueSAR_6.doubleValue());
         contentValue.put(SQLiteHelper.Fundvalue_Start_6, Fundvalue_Start_6.doubleValue());
         contentValue.put(SQLiteHelper.Fundvalue_End_6, Fundvalue_End_6.doubleValue());
         contentValue.put(SQLiteHelper.SurrenderValue_6, SurrenderValue_6.doubleValue());
         contentValue.put(SQLiteHelper.TotalCharge_8, TotalCharge_8.doubleValue());
         contentValue.put(SQLiteHelper.NetAmtaddedtoFund_8, NetAmtaddedtoFund_8.doubleValue());
         contentValue.put(SQLiteHelper.FundValueSAR_8, FundValueSAR_8.doubleValue());
         contentValue.put(SQLiteHelper.Fundvalue_Start_8, Fundvalue_Start_8.doubleValue());
         contentValue.put(SQLiteHelper.Fundvalue_End_8, Fundvalue_End_8.doubleValue());
         contentValue.put(SQLiteHelper.SurrenderValue_8, SurrenderValue_8.doubleValue());
         contentValue.put(SQLiteHelper.TotalCharge_10, TotalCharge_10.doubleValue());
         contentValue.put(SQLiteHelper.NetAmtAddedtoFund_10, NetAmtAddedtoFund_10.doubleValue());
         contentValue.put(SQLiteHelper.FundValueSAR_10, FundValueSAR_10.doubleValue());
         contentValue.put(SQLiteHelper.Fundvalue_Start_10, Fundvalue_Start_10.doubleValue());
         contentValue.put(SQLiteHelper.Fundvalue_end_10, Fundvalue_end_10.doubleValue());
         contentValue.put(SQLiteHelper.SurrenderValue_10, SurrenderValue_10.doubleValue());

        this.database.insert(SQLiteHelper.TABLE_NAME_PROJECTION, null, contentValue);

    }

    public void insertProjection14_15(
        int Step, int Month, int DurationMonth, int PolicyYear, BigDecimal AgeInsured,
        BigDecimal Probability_qx, BigDecimal TPD_charge_rate_qx, BigDecimal PremiumIncome,
        BigDecimal PremiumAllocationCharge, BigDecimal FundManagementCharge_6,
        BigDecimal FundManagementCharge_8,BigDecimal FundManagementCharge_10, BigDecimal PolicyAdministrationCharge,
        BigDecimal Discfuturepremiums,BigDecimal PremiumIncomeRisk,
        BigDecimal DiscfuturePremiumsRisk, BigDecimal WoP_charge_death, BigDecimal WoP_charge_tpd,
        BigDecimal WoP_charge_total, BigDecimal TotalCharge_6, BigDecimal NetAmtaddedtoFund_6,
        BigDecimal Fundvalue_Start_6, BigDecimal Fundvalue_End_6, BigDecimal SurrenderValue_6,
        BigDecimal TotalCharge_8, BigDecimal NetAmtaddedtoFund_8, BigDecimal Fundvalue_Start_8,
        BigDecimal Fundvalue_End_8, BigDecimal SurrenderValue_8, BigDecimal TotalCharge_10,
        BigDecimal NetAmtAddedtoFund_10, BigDecimal Fundvalue_Start_10, BigDecimal Fundvalue_end_10,
        BigDecimal SurrenderValue_10, BigDecimal GEMV, BigDecimal DVGEMV,  BigDecimal MCGEMV) {
             ContentValues contentValue = new ContentValues();
             contentValue.put(SQLiteHelper.Step, Step);
             contentValue.put(SQLiteHelper.Month, Month);
             contentValue.put(SQLiteHelper.DurationMonth, DurationMonth);
             contentValue.put(SQLiteHelper.PolicyYear, PolicyYear);
             contentValue.put(SQLiteHelper.AgeInsured, AgeInsured.doubleValue());
             contentValue.put(SQLiteHelper.Probability_qx, Probability_qx.doubleValue());
             contentValue.put(SQLiteHelper.TPD_charge_rate_qx, TPD_charge_rate_qx.doubleValue());
             contentValue.put(SQLiteHelper.PremiumIncome, PremiumIncome.doubleValue());
             contentValue.put(SQLiteHelper.PremiumAllocationCharge, PremiumAllocationCharge.doubleValue());
             contentValue.put(SQLiteHelper.FundManagementCharge_6, FundManagementCharge_6.doubleValue());
             contentValue.put(SQLiteHelper.FundManagementCharge_8, FundManagementCharge_8.doubleValue());
             contentValue.put(SQLiteHelper.FundManagementCharge_10, FundManagementCharge_10.doubleValue());
             contentValue.put(SQLiteHelper.PolicyAdministrationCharge, PolicyAdministrationCharge.doubleValue());
             contentValue.put(SQLiteHelper.Discfuturepremiums, Discfuturepremiums.doubleValue());
             contentValue.put(SQLiteHelper.PremiumIncomeWithRisk, PremiumIncomeRisk.doubleValue());
             contentValue.put(SQLiteHelper.DiscfuturepremiumsWithRisk, DiscfuturePremiumsRisk.doubleValue());
             contentValue.put(SQLiteHelper.WoP_charge_death, WoP_charge_death.doubleValue());
             contentValue.put(SQLiteHelper.WoP_charge_tpd, WoP_charge_tpd.doubleValue());
             contentValue.put(SQLiteHelper.WoP_charge_total, WoP_charge_total.doubleValue());
             contentValue.put(SQLiteHelper.TotalCharge_6, TotalCharge_6.doubleValue());
             contentValue.put(SQLiteHelper.NetAmtaddedtoFund_6, NetAmtaddedtoFund_6.doubleValue());

             contentValue.put(SQLiteHelper.Fundvalue_Start_6, Fundvalue_Start_6.doubleValue());
             contentValue.put(SQLiteHelper.Fundvalue_End_6, Fundvalue_End_6.doubleValue());
             contentValue.put(SQLiteHelper.SurrenderValue_6, SurrenderValue_6.doubleValue());

             contentValue.put(SQLiteHelper.TotalCharge_8, TotalCharge_8.doubleValue());
             contentValue.put(SQLiteHelper.NetAmtaddedtoFund_8, NetAmtaddedtoFund_8.doubleValue());
             contentValue.put(SQLiteHelper.Fundvalue_Start_8, Fundvalue_Start_8.doubleValue());
             contentValue.put(SQLiteHelper.Fundvalue_End_8, Fundvalue_End_8.doubleValue());

             contentValue.put(SQLiteHelper.SurrenderValue_8, SurrenderValue_8.doubleValue());
             contentValue.put(SQLiteHelper.TotalCharge_10, TotalCharge_10.doubleValue());
             contentValue.put(SQLiteHelper.NetAmtAddedtoFund_10, NetAmtAddedtoFund_10.doubleValue());

             contentValue.put(SQLiteHelper.Fundvalue_Start_10, Fundvalue_Start_10.doubleValue());
             contentValue.put(SQLiteHelper.Fundvalue_end_10, Fundvalue_end_10.doubleValue());
             contentValue.put(SQLiteHelper.SurrenderValue_10, SurrenderValue_10.doubleValue());

             contentValue.put(SQLiteHelper.GEMV, GEMV.doubleValue());
             contentValue.put(SQLiteHelper.DVGEMV, DVGEMV.doubleValue());
             contentValue.put(SQLiteHelper.MCGEMV, MCGEMV.doubleValue());

//      try {
//        open();
        this.database.insert(SQLiteHelper.TABLE_NAME_PROJECTION14_15, null, contentValue);
//        this.database.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
    }

    public void insertProjection24(
        int Step,
        int Month,
        int DurationMonth,
        int PolicyYear,
        BigDecimal AgeInsured,
        BigDecimal Probability_qx,
        BigDecimal TPD_charge_rate_qx,
        BigDecimal PremiumIncome,
        BigDecimal PremiumAllocationCharge,
        BigDecimal FundManagementCharge_6,
        BigDecimal FundManagementCharge_8,
        BigDecimal FundManagementCharge_10,
		BigDecimal PolicyAdministrationCharge,
		BigDecimal PremiumIncomeWithOutRisk,
		BigDecimal DiscfuturePremiumsWithOutRisk,
        BigDecimal Discfuturepremiums,
		BigDecimal WoP_charge_death,
		BigDecimal WoP_charge_tpd,
        BigDecimal WoP_charge_total,
		BigDecimal TotalCharge_6,
		BigDecimal NetAmtaddedtoFund_6,
        BigDecimal Fundvalue_Start_6,
		BigDecimal Fundvalue_End_6,
		BigDecimal SurrenderValue_6,
        BigDecimal TotalCharge_8,
		BigDecimal NetAmtaddedtoFund_8,
		BigDecimal Fundvalue_Start_8,
        BigDecimal Fundvalue_End_8,
		BigDecimal SurrenderValue_8,
		BigDecimal TotalCharge_10,
        BigDecimal NetAmtAddedtoFund_10,
		BigDecimal Fundvalue_Start_10,
		BigDecimal Fundvalue_end_10,
        BigDecimal SurrenderValue_10,
		BigDecimal GEMV,
		BigDecimal DVGEMV,
		BigDecimal MCGEMV) {
             ContentValues contentValue = new ContentValues();
             contentValue.put("Step", Step);
             contentValue.put("Month", Month);
             contentValue.put("DurationMonth", DurationMonth);
             contentValue.put("PolicyYear", PolicyYear);
             contentValue.put("AgeInsured", AgeInsured.doubleValue());
             contentValue.put("Probability_qx", Probability_qx.doubleValue());
             contentValue.put("TPD_charge_rate_qx", TPD_charge_rate_qx.doubleValue());
             contentValue.put("PremiumIncome", PremiumIncome.doubleValue());
             contentValue.put("PremiumAllocationCharge", PremiumAllocationCharge.doubleValue());
              contentValue.put("FundManagementCharge_6", FundManagementCharge_6.doubleValue());
              contentValue.put("FundManagementCharge_8", FundManagementCharge_8.doubleValue());
              contentValue.put("FundManagementCharge_10", FundManagementCharge_10.doubleValue());
             contentValue.put("PolicyAdministrationCharge", PolicyAdministrationCharge.doubleValue());
             contentValue.put("Discfuturepremiums", Discfuturepremiums.doubleValue());
             contentValue.put(SQLiteHelper.WoP_charge_death, WoP_charge_death.doubleValue());
             contentValue.put(SQLiteHelper.WoP_charge_tpd, WoP_charge_tpd.doubleValue());
             contentValue.put(SQLiteHelper.WoP_charge_total, WoP_charge_total.doubleValue());
             contentValue.put(SQLiteHelper.TotalCharge_6, TotalCharge_6.doubleValue());
             contentValue.put("NetAmtaddedtoFund_6", NetAmtaddedtoFund_6.doubleValue());

             contentValue.put(SQLiteHelper.Fundvalue_Start_6, Fundvalue_Start_6.doubleValue());
             contentValue.put("Fundvalue_End_6", Fundvalue_End_6.doubleValue());
             contentValue.put(SQLiteHelper.SurrenderValue_6, SurrenderValue_6.doubleValue());

             contentValue.put(SQLiteHelper.TotalCharge_8, TotalCharge_8.doubleValue());
             contentValue.put("NetAmtaddedtoFund_8", NetAmtaddedtoFund_8.doubleValue());
             contentValue.put(SQLiteHelper.Fundvalue_Start_8, Fundvalue_Start_8.doubleValue());
             contentValue.put("Fundvalue_End_8", Fundvalue_End_8.doubleValue());

             contentValue.put(SQLiteHelper.SurrenderValue_8, SurrenderValue_8.doubleValue());
             contentValue.put(SQLiteHelper.TotalCharge_10, TotalCharge_10.doubleValue());
             contentValue.put("NetAmtAddedtoFund_10", NetAmtAddedtoFund_10.doubleValue());

             contentValue.put(SQLiteHelper.Fundvalue_Start_10, Fundvalue_Start_10.doubleValue());
             contentValue.put("Fundvalue_end_10", Fundvalue_end_10.doubleValue());
             contentValue.put(SQLiteHelper.SurrenderValue_10, SurrenderValue_10.doubleValue());

             contentValue.put(SQLiteHelper.GEMV, GEMV.doubleValue());
             contentValue.put(SQLiteHelper.DVGEMV, DVGEMV.doubleValue());

             contentValue.put(SQLiteHelper.MCGEMV, MCGEMV.doubleValue());

             contentValue.put("PremiumIncomeIncludingRisk", PremiumIncomeWithOutRisk.doubleValue());
             contentValue.put("DiscfuturepremiumsIncludingRisk", DiscfuturePremiumsWithOutRisk.doubleValue());


//      try {
//        open();
        this.database.insert(SQLiteHelper.TABLE_NAME_PROJECTION_24, null, contentValue);
//        this.database.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }


    }

    public void insertProduct23_cashflow(
        int Step,int Month,int DurationMonth,int PolicyYear,String sqlDate,int YearBegOfmonth,
						BigDecimal AgeInsured,BigDecimal IndProbDeathQx,BigDecimal PremiumIncome,
						BigDecimal PremiumAllocationCharge,
						BigDecimal Fmc6,BigDecimal Fmc8,BigDecimal Fmc10,BigDecimal Pac,
						BigDecimal Mc6,BigDecimal Mc8,BigDecimal Mc10,BigDecimal DeathSa,
						BigDecimal TotalCharges6,BigDecimal NetAmtAddedtoFund6,BigDecimal FundvalueSAR6,
						BigDecimal FundvalueStart6,BigDecimal FundvalueEnd6,BigDecimal TotalCharges8,
						BigDecimal NetAmtAddedtoFund8,BigDecimal FundvalueSAR8,
						BigDecimal FundvalueStart8,BigDecimal FundvalueEnd8,BigDecimal TotalCharges10,
						BigDecimal NetAmtAddedtoFund10,BigDecimal FundvalueSAR10,BigDecimal FundvalueStart10,
						BigDecimal FundvalueEnd10,BigDecimal DeathBenefit6,BigDecimal DeathBenefit8,BigDecimal DeathBenefit10,
						BigDecimal DeathSAR6,BigDecimal DeathSAR8,BigDecimal DeathSAR10) {
             ContentValues contentValue = new ContentValues();
             contentValue.put(SQLiteHelper.Step, Step);
             contentValue.put(SQLiteHelper.Month, Month);
             contentValue.put(SQLiteHelper.DurationMonth, Step);
             contentValue.put(SQLiteHelper.PolicyYear, PolicyYear);
             contentValue.put(SQLiteHelper._23_Date_BegofMonth, sqlDate);
             contentValue.put(SQLiteHelper._23_Year_BegofMonth, YearBegOfmonth);
             contentValue.put(SQLiteHelper.AgeInsured, AgeInsured.doubleValue());
             contentValue.put(SQLiteHelper._23_Ind_Prob_Death_qx, IndProbDeathQx.doubleValue());
             contentValue.put(SQLiteHelper._23_Premium_Income, PremiumIncome.doubleValue());
             contentValue.put(SQLiteHelper._23_Premium_allocation_charge, PremiumAllocationCharge.doubleValue());

             contentValue.put(SQLiteHelper._23_FMC_6, Fmc6.doubleValue());
             contentValue.put(SQLiteHelper._23_FMC_8, Fmc8.doubleValue());
             contentValue.put(SQLiteHelper._23_FMC_10, Fmc10.doubleValue());

             contentValue.put(SQLiteHelper._23_PAC, Pac.doubleValue());

             contentValue.put(SQLiteHelper._23_MC_6, Mc6.doubleValue());
             contentValue.put(SQLiteHelper._23_MC_8, Mc8.doubleValue());
             contentValue.put(SQLiteHelper._23_MC_10, Mc10.doubleValue());

             contentValue.put(SQLiteHelper._23_Death_SA, DeathSa.doubleValue());
             contentValue.put(SQLiteHelper._23_Total_Charges_6, TotalCharges6.doubleValue());
             contentValue.put(SQLiteHelper._23_NetAmtAddedtoFund_6, NetAmtAddedtoFund6.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueSAR_6, FundvalueSAR6.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueStart_6, FundvalueStart6.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueEnd_6, FundvalueEnd6.doubleValue());
             contentValue.put(SQLiteHelper._23_Total_Charges_8, TotalCharges8.doubleValue());
             contentValue.put(SQLiteHelper._23_NetAmtAddedtoFund_8, NetAmtAddedtoFund8.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueSAR_8, FundvalueSAR8.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueStart_8, FundvalueStart8.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueEnd_8, FundvalueEnd8.doubleValue());
             contentValue.put(SQLiteHelper._23_Total_Charges_10, TotalCharges10.doubleValue());
             contentValue.put(SQLiteHelper._23_NetAmtAddedtoFund_10, NetAmtAddedtoFund10.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueSAR_10, FundvalueSAR10.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueStart_10, FundvalueStart10.doubleValue());
             contentValue.put(SQLiteHelper._23_FundvalueEnd_10, FundvalueEnd10.doubleValue());
             contentValue.put(SQLiteHelper._23_DeathBenefit_6, DeathBenefit6.doubleValue());


             contentValue.put(SQLiteHelper._23_DeathBenefit_8, DeathBenefit8.doubleValue());
             contentValue.put(SQLiteHelper._23_DeathBenefit_10, DeathBenefit10.doubleValue());
             contentValue.put(SQLiteHelper._23_DeathSAR_6, DeathSAR6.doubleValue());
             contentValue.put(SQLiteHelper._23_DeathSAR_8, DeathSAR8.doubleValue());
             contentValue.put(SQLiteHelper._23_DeathSAR_10, DeathSAR10.doubleValue());
            //  contentValue.put(SQLiteHelper._23_SUMPremium, PremiumAllocationCharge.doubleValue());
            //  contentValue.put(SQLiteHelper._23_ivalue, PremiumAllocationCharge.doubleValue());


//      try {
//        open();
        this.database.insert(SQLiteHelper.TABLE_NAME_PRODUCT23, null, contentValue);
//        this.database.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
    }

    public void insertAnnuityPayment(
        int Payoutmonth, BigDecimal AnnuityMonth6, BigDecimal AnnuityMonth8, BigDecimal AnnuityMonth10){

        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLiteHelper.payoutmonth, Payoutmonth);
        contentValue.put(SQLiteHelper.AnnuityMonth_6, AnnuityMonth6.doubleValue());
        contentValue.put(SQLiteHelper.AnnuityMonth_8, AnnuityMonth8.doubleValue());
        contentValue.put(SQLiteHelper.AnnuityMonth_10, AnnuityMonth10.doubleValue());



//      try {
//        open();
        this.database.insert(SQLiteHelper.TABLE_NAME_ANNUITYPAYMENT, null, contentValue);
//        this.database.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
    }

    public Cursor fetch() {
        Cursor cursor = this.database.query(SQLiteHelper.TABLE_NAME_PREM, new String[]{SQLiteHelper.PerValue}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor query() {
        Cursor cursor = this.database.rawQuery("select * from table",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor queueAll(String query) {
//        String selectQuery=query;
//        Cursor cursor = this.database.rawQuery(selectQuery, null);
//        return cursor;
      BigDecimal value;
      this.dbHelper = new SQLiteHelper(this.context);
      this.database = this.dbHelper.getWritableDatabase();
      Cursor cursor = this.database.rawQuery(query, null);



      this.database.close();
      return cursor;

    }

  public BigDecimal queueAll_new(String query) {
    BigDecimal value;

    this.dbHelper = new SQLiteHelper(this.context);
    this.database = this.dbHelper.getWritableDatabase();

    try{
      Cursor cursor = this.database.rawQuery(query, null);
      if (cursor != null) {
        cursor.moveToFirst();
      }
      value = new BigDecimal(cursor.getString(0));
    }
    catch (Exception e) {
      e.printStackTrace();
      value = new BigDecimal(0);
    } finally {
      this.database.close();
    }

    return value;
  }


    public void queueAllInsert(String query) {
        this.database.rawQuery(query, null);
    }

    public void delete(String plancode) {
      switch(plancode){
            case "PRODUCT12":
            case "PRODUCT13":
                this.database.delete(SQLiteHelper.TABLE_NAME_PROJECTION,null, null);
                break;
            case "PRODUCT14":
            case "PRODUCT15":
                this.database.delete(SQLiteHelper.TABLE_NAME_PROJECTION14_15,null, null);
                break;
            case "PRODUCT24":
                this.database.delete(SQLiteHelper.TABLE_NAME_PROJECTION_24,null, null);
                break;
            case "PRODUCT23":
                this.database.delete(SQLiteHelper.TABLE_NAME_PRODUCT23,null, null);
                break;
            case "ANNUITYPAYMENT":
                this.database.delete(SQLiteHelper.TABLE_NAME_ANNUITYPAYMENT,null, null);
                break;
            default:
            break;
        }
    }
}
