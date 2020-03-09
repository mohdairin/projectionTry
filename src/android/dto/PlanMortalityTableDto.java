package cordova.plugin.projection.dto;
import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.math.*;
import java.lang.Math;
//import android.app.Application;
import android.content.Context;
import android.util.Log;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
import cordova.plugin.projection.pojo.PlanMortalityTable;
import cordova.plugin.projection.sqlite.DBManager;

public class PlanMortalityTableDto {
  private Context context;

  public PlanMortalityTableDto(Context _context) {
      this.context = _context;
  }

  public List<PlanMortalityTable> select(BigDecimal prevIAgeInsured, String strPlanCode) {
    List <PlanMortalityTable> PlanMortalityEach = new ArrayList<PlanMortalityTable> ();
    DBManager dbManager = new DBManager(this.context);

    PlanMortalityTable planMortalityTable = new PlanMortalityTable();
    String strSql = "SELECT qx FROM planmortalitytable WHERE age=(ROUND("+ prevIAgeInsured +",0)) AND Plancode='" + strPlanCode + "'";

    Double tempValue = new Double(dbManager.queueAll_new(strSql).toString());
    BigDecimal tempqx =  new BigDecimal(tempValue/12);
    planMortalityTable.setQx(tempqx);
    PlanMortalityEach.add(planMortalityTable);

    Log.i("Mortality_select", strSql);
    return PlanMortalityEach;
  }

  public List<PlanMortalityTable> selectTpd(BigDecimal prevIAgeInsured, String strPlanCode) {
    List <PlanMortalityTable> PlanMortalityEach = new ArrayList<PlanMortalityTable> ();
    DBManager dbManager = new DBManager(this.context);

    PlanMortalityTable planMortalityTable = new PlanMortalityTable();
    String strSql = "SELECT qx FROM plantpdchargetable WHERE age=(ROUND("+ prevIAgeInsured +",0)) AND Plancode='" + strPlanCode + "'";

    Double tempValue = new Double(dbManager.queueAll_new(strSql).toString());
    BigDecimal tempqx =  new BigDecimal(tempValue/12);
    planMortalityTable.setQx(tempqx);
    PlanMortalityEach.add(planMortalityTable);

    Log.i("Mortality_selectTpd", strSql);

    return PlanMortalityEach;
  }

  public List<PlanMortalityTable> fetchQx(BigDecimal prevIAgeInsured, String strPlanCode) {
    List<PlanMortalityTable> PlanMortalityEach = new ArrayList<PlanMortalityTable>();
    DBManager dbManager = new DBManager(this.context);

    PlanMortalityTable planMortalityTable = new PlanMortalityTable();
    String sql =  "SELECT qx FROM planmortalitytable WHERE age=(ROUND("+ prevIAgeInsured +",0)) AND Plancode='" + strPlanCode + "'";

    Double tempValue = new Double(dbManager.queueAll_new(sql).toString());
    BigDecimal tempqx =  new BigDecimal(1-Math.pow(1-tempValue, 0.08333333333333));
    planMortalityTable.setQx(tempqx);
    PlanMortalityEach.add(planMortalityTable);

    Log.i("Mortality_fetchQx", sql);
    return PlanMortalityEach;
  }

  public List<PlanMortalityTable> fetchQx_24(BigDecimal prevIAgeInsured, String strPlanCode) {
    List<PlanMortalityTable> PlanMortalityEach = new ArrayList<PlanMortalityTable>();
    DBManager dbManager = new DBManager(this.context);

    PlanMortalityTable planMortalityTable = new PlanMortalityTable();
    String sql =  "SELECT qx/12 FROM planmortalitytable WHERE age=(ROUND("+ prevIAgeInsured +",0)) AND Plancode='" + strPlanCode + "'";

    BigDecimal tempqx =  new BigDecimal(dbManager.queueAll_new(sql).toString());
    planMortalityTable.setQx(tempqx);
    PlanMortalityEach.add(planMortalityTable);

    Log.i("Mortality_fetchQx_24", sql);
    return PlanMortalityEach;
  }

  public BigDecimal fetchQx_24_new(BigDecimal prevIAgeInsured, String strPlanCode)  {
    BigDecimal tempqx;
    DBManager dbManager = new DBManager(this.context);

    String sql =  "SELECT qx/12 FROM planmortalitytable WHERE age=(ROUND("+ prevIAgeInsured +",0)) AND Plancode='" + strPlanCode + "'";
    tempqx = dbManager.queueAll_new(sql);

    return tempqx;
  }

  public BigDecimal fetchRate(String planCode,String gender,BigDecimal age){
    BigDecimal rate;
    DBManager dbManager = new DBManager(this.context);

    String sql = "select (rate/12/1000) rate from planmortalitytable where plancode='"+planCode+"'  and gender='"+gender+"' and age=ROUND("+age+",0)";
    rate = dbManager.queueAll_new(sql);

    return rate;
  }


}
