package cordova.plugin.projection.dto;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Log;
import cordova.plugin.projection.sqlite.DBManager;
import cordova.plugin.projection.pojo.PlanSvTable;

public class PlanSvTableDto {
 private Context context;

 public PlanSvTableDto(Context _context) {
  this.context = _context;
 }

  public List <PlanSvTable> select(String strPlanCode, int tempIPolicyYear) {
    List <PlanSvTable> PlanSvTableEach = new ArrayList<PlanSvTable> ();
    DBManager dbManager = new DBManager(this.context);

    PlanSvTable planSvTable = new PlanSvTable();
    String sql = "SELECT factor FROM plan_sv_table WHERE UPPER(plancode)= UPPER('" + strPlanCode + "') AND YEAR=" + tempIPolicyYear;

    planSvTable.setFactor(dbManager.queueAll_new(sql));
    PlanSvTableEach.add(planSvTable);

    Log.i("SvTable_select", sql);
    return PlanSvTableEach;
  }
}
