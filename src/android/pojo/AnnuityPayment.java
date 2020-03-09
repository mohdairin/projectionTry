package cordova.plugin.projection.pojo;
import java.math.BigDecimal;

public class AnnuityPayment {
	private int payoutmonth;
	private BigDecimal annuityMonth6;
	private BigDecimal annuityMonth8;
	private BigDecimal annuityMonth10;
	private int tempValue;
	
	public int getTempValue() {
		return tempValue;
	}
	public void setTempValue(int tempValue) {
		this.tempValue = tempValue;
	}
	public int getPayoutmonth() {
		return payoutmonth;
	}
	public void setPayoutmonth(int payoutmonth) {
		this.payoutmonth = payoutmonth;
	}
	public BigDecimal getAnnuityMonth6() {
		return annuityMonth6;
	}
	public void setAnnuityMonth6(BigDecimal annuityMonth6) {
		this.annuityMonth6 = annuityMonth6;
	}
	public BigDecimal getAnnuityMonth8() {
		return annuityMonth8;
	}
	public void setAnnuityMonth8(BigDecimal annuityMonth8) {
		this.annuityMonth8 = annuityMonth8;
	}
	public BigDecimal getAnnuityMonth10() {
		return annuityMonth10;
	}
	public void setAnnuityMonth10(BigDecimal annuityMonth10) {
		this.annuityMonth10 = annuityMonth10;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		sb.append(">payoutmonth:"+payoutmonth);
		sb.append(">tempValue:"+tempValue);
		sb.append(">annuityMonth6:"+annuityMonth6);
		sb.append(">annuityMonth8:"+annuityMonth8);
		sb.append(">annuityMonth10:"+annuityMonth10);
		return sb.toString();
	}
}
