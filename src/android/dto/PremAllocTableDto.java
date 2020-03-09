package cordova.plugin.projection.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Log;
import cordova.plugin.projection.sqlite.DBManager;
import cordova.plugin.projection.pojo.PremAllocTable;

public class PremAllocTableDto {
 private Context context;

 public PremAllocTableDto(Context _context) {
  this.context = _context;
 }


  public List <PremAllocTable> select(int iPolicyYear,int term) throws Exception {
    List <PremAllocTable> premAllocEach = new ArrayList<PremAllocTable>();
    DBManager dbManager = new DBManager(this.context);

    PremAllocTable premAllocTable = new PremAllocTable();
    String sql = "SELECT pervalue FROM prem_alloc_table WHERE YEAR = " + iPolicyYear + " AND TERM= "+term;

    premAllocTable.setPerValue(dbManager.queueAll_new(sql));
    premAllocEach.add(premAllocTable);

    Log.i("Alloc_select", sql);
    return premAllocEach;
  }

  public List <PremAllocTable> selectWithPlanCode(int iPolicyYear,int term,String plancode) throws Exception {
    List <PremAllocTable> premAllocEach = new ArrayList<PremAllocTable>();
    DBManager dbManager = new DBManager(this.context);

    PremAllocTable premAllocTable = new PremAllocTable();
    String sql = "SELECT pervalue FROM prem_alloc_table WHERE YEAR = " + iPolicyYear + " AND TERM= "+term+" AND PLANCODE = '"+plancode+"'";

    premAllocTable.setPerValue(dbManager.queueAll_new(sql));
    premAllocEach.add(premAllocTable);

    Log.i("Alloc_selectWiPlanCode", sql);
    return premAllocEach;
  }

  public BigDecimal select_pervalue(int iPolicyYear, String planCode){
    BigDecimal perValue;
    DBManager dbManager = new DBManager(this.context);

    String sql = "select pervalue from prem_alloc_table where YEAR = "+iPolicyYear+" and Plancode='"+planCode+"'";
    perValue = dbManager.queueAll_new(sql);

    return perValue;
  }

}
