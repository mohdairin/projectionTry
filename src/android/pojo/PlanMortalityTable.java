package cordova.plugin.projection.pojo;
import java.math.BigDecimal;

public class PlanMortalityTable {
	BigDecimal prevIAgeInsured;
	BigDecimal qx;
	String strPlanCode;
	public BigDecimal getPrevIAgeInsured() {
		return prevIAgeInsured;
	}
	public void setPrevIAgeInsured(BigDecimal prevIAgeInsured) {
		this.prevIAgeInsured = prevIAgeInsured;
	}
	public String getStrPlanCode() {
		return strPlanCode;
	}
	public void setStrPlanCode(String strPlanCode) {
		this.strPlanCode = strPlanCode;
	}
	public BigDecimal getQx() {
		return qx;
	}
	public void setQx(BigDecimal qx) {
		this.qx = qx;
	}
}
