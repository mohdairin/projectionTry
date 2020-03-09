package cordova.plugin.projection.sqlite;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.os.Build;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String CREATE_TABLE_STUDENT = "create table STUDENTS ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL , age TEXT );";
    private static final String DB_NAME = "agentbuddylife.db";
    private static final int DB_VERSION = 3;
    public static final String NAME = "name";
    public static final String TABLE_NAME_ANNUAL = "annualpremiumrate";
    public static final String TABLE_NAME_PREM = "prem_alloc_table";
    public static final String TABLE_NAME_PROJECTION = "projection";
    public static final String TABLE_NAME_PROJECTION14_15="projection14_15";
    public static final String TABLE_NAME_PROJECTION_24 = "projection24";
    public static final String TABLE_NAME_ANNUITYPAYMENT="AnnuityPayment";
    public static final String TABLE_NAME_PRODUCT23="Product23_cashflow";


    public static final String PerValue = "PerValue";
    public static final String _ID = "_id";
    public static final String FromTerm = "Fromterm";
    public static final String ToTerm = "toTerm";
    public static final String PlanCode = "plancode";
    public static final String Rate = "rate";

    //Projection Column
    public static final String Step = "Step";
    public static final String Month = "Month";
    public static final String DurationMonth = "DurationMonth";
    public static final String PolicyYear = "PolicyYear";
    public static final String AgeInsured = "AgeInsured";
    public static final String Probability_qx = "Probability_qx";
    public static final String PremiumIncome = "PremiumIncome";
    public static final String PremiumAllocationCharge = "PremiumAllocationCharge";
    public static final String FundManagementCharge = "FundManagementCharge";
    public static final String FundManagementCharge_6 = "FundManagementCharge_6";
    public static final String FundManagementCharge_8 = "FundManagementCharge_8";
    public static final String FundManagementCharge_10 = "FundManagementCharge_10";
    public static final String PolicyAdministrationCharge = "PolicyAdministrationCharge";
    public static final String MortalityCharge_6 = "MortalityCharge_6";
    public static final String MortalityCharge_8 = "MortalityCharge_8";
    public static final String MortalityCharge_10 = "MortalityCharge_10";
    public static final String DeathBenefit = "DeathBenefit";
    public static final String TotalCharge_6 = "TotalCharge_6";
    public static final String NetAmtaddedtoFund_6 = "NetAmtaddedtoFund_6";
    public static final String FundvalueSAR_6 = "FundvalueSAR_6";
    public static final String Fundvalue_Start_6 = "Fundvalue_Start_6";
    public static final String Fundvalue_End_6 = "Fundvalue_End_6";
    public static final String SurrenderValue_6 = "SurrenderValue_6";
    public static final String TotalCharge_8 = "TotalCharge_8";
    public static final String NetAmtaddedtoFund_8 = "NetAmtaddedtoFund_8";
    public static final String FundValueSAR_8 = "FundValueSAR_8";
    public static final String Fundvalue_Start_8 = "Fundvalue_Start_8";
    public static final String Fundvalue_End_8 = "Fundvalue_End_8";
    public static final String SurrenderValue_8 = "SurrenderValue_8";
    public static final String TotalCharge_10 = "TotalCharge_10";
    public static final String NetAmtAddedtoFund_10 = "NetAmtAddedtoFund_10";
    public static final String FundValueSAR_10 = "FundValueSAR_10";
    public static final String Fundvalue_Start_10 = "Fundvalue_Start_10";
    public static final String Fundvalue_end_10 = "Fundvalue_end_10";
    public static final String SurrenderValue_10 = "SurrenderValue_10";
    public static final String CheckCol = "CheckCol";

     //Projection14_15 Column
    public static final String TPD_charge_rate_qx = "TPD_charge_rate_qx";
    public static final String Discfuturepremiums = "Discfuturepremiums";
    public static final String WoP_charge_death = "WoP_charge_death";
    public static final String WoP_charge_tpd = "WoP_charge_tpd";
    public static final String WoP_charge_total = "WoP_charge_total";
    public static final String GEMV = "GEMV";
    public static final String DVGEMV = "DVGEMV";
    public static final String MCGEMV = "MCGEMV";
    public static final String PremiumIncomeWithRisk = "PremiumIncomeWithRisk";
    public static final String DiscfuturepremiumsWithRisk = "DiscfuturepremiumsWithRisk";

    //AnnuityPayment Column
    public static final String payoutmonth = "payoutmonth";
    public static final String AnnuityMonth_6 = "AnnuityMonth_6";
    public static final String AnnuityMonth_8 = "AnnuityMonth_8";
    public static final String AnnuityMonth_10 = "AnnuityMonth_10";

    //Product23 Column
    public static final String _23_Date_BegofMonth="Date_BegofMonth";
    public static final String _23_Year_BegofMonth="Year_BegofMonth";
    public static final String _23_Ind_Prob_Death_qx="Ind_Prob_Death_qx";
    public static final String _23_Premium_Income= "Premium_Income";
    public static final String _23_Premium_allocation_charge= "Premium_allocation_charge";
    public static final String _23_FMC_6= "FMC_6";
    public static final String _23_FMC_8= "FMC_8";
    public static final String _23_FMC_10= "FMC_10";
    public static final String _23_PAC= "PAC";
    public static final String _23_MC_6= "MC_6";
    public static final String _23_MC_8= "MC_8";
    public static final String _23_MC_10= "MC_10";
    public static final String _23_Death_SA= "Death_SA";
    public static final String _23_Total_Charges_6= "Total_Charges_6";
    public static final String _23_NetAmtAddedtoFund_6= "NetAmtAddedtoFund_6";

    public static final String _23_FundvalueSAR_6= "FundvalueSAR_6";
    public static final String _23_FundvalueStart_6= "FundvalueStart_6";
    public static final String _23_FundvalueEnd_6= "FundvalueEnd_6";
    public static final String _23_Total_Charges_8= "Total_Charges_8";
    public static final String _23_NetAmtAddedtoFund_8= "NetAmtAddedtoFund_8";
    public static final String _23_FundvalueSAR_8= "FundvalueSAR_8";

    public static final String _23_FundvalueStart_8= "FundvalueStart_8";
    public static final String _23_FundvalueEnd_8 = "FundvalueEnd_8";
    public static final String _23_Total_Charges_10 = "Total_Charges_10";
    public static final String _23_NetAmtAddedtoFund_10 = "NetAmtAddedtoFund_10";
    public static final String _23_FundvalueSAR_10 = "FundvalueSAR_10";
    public static final String _23_FundvalueStart_10 = "FundvalueStart_10";
    public static final String _23_FundvalueEnd_10= "FundvalueEnd_10";
    public static final String _23_DeathBenefit_6 = "DeathBenefit_6";
    public static final String _23_DeathBenefit_8 = "DeathBenefit_8";

    public static final String _23_DeathBenefit_10 = "DeathBenefit_10";
    public static final String _23_DeathSAR_6 = "DeathSAR_6";
    public static final String _23_DeathSAR_8 = "DeathSAR_8";
    public static final String _23_DeathSAR_10 = "DeathSAR_10";
    public static final String _23_SUMPremium = "SUMPremium";
    public static final String _23_ivalue = "SUMPremium";


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
      if(Build.VERSION.SDK_INT == 28){
        db.disableWriteAheadLogging();
    }
    //db.disableWriteAheadLogging();
    super.onOpen(db);
    //    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    //      db.disableWriteAheadLogging();
    //    }
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


}
