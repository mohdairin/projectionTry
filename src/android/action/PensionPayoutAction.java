package cordova.plugin.projection.action;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import cordova.plugin.projection.dto.ProjectionDto;
import cordova.plugin.projection.pojo.AnnuityPayment;

public class PensionPayoutAction {

	private Context context;

	public PensionPayoutAction(Context _context) {
	 this.context = _context;
	}

	public List<AnnuityPayment> generateAnnuityPayment(
		BigDecimal bigDecFundEnd_6, BigDecimal bigDecFundEnd_8, BigDecimal bigDecFundEnd_10,
		int intDrawdownterm, int intPensionPayFreq, int intProposerAge, int intBasePlanTerm){
		
		ProjectionDto projectionDto = new ProjectionDto(this.context);
		List<AnnuityPayment> annuityPaymentList = new ArrayList<AnnuityPayment>();
		List<AnnuityPayment> annuityPaymentInsertionList = new ArrayList<AnnuityPayment>();

    int stepyear = 1;
    BigDecimal am_6 = BigDecimal.ZERO;
    BigDecimal am_8 = BigDecimal.ZERO;
    BigDecimal am_10 = BigDecimal.ZERO;

    try{
			projectionDto.deleteManager("ANNUITYPAYMENT");	

			while(stepyear <= intDrawdownterm){
				if(stepyear == 1){
					am_6=bigDecFundEnd_6.divide(new BigDecimal(intDrawdownterm*intPensionPayFreq),10,RoundingMode.FLOOR);
					am_8=bigDecFundEnd_8.divide(new BigDecimal(intDrawdownterm*intPensionPayFreq),10,RoundingMode.FLOOR);
					am_10=bigDecFundEnd_10.divide(new BigDecimal(intDrawdownterm*intPensionPayFreq),10,RoundingMode.FLOOR);
				}else{
					am_6=am_6.multiply(new BigDecimal(1.06));
					am_8=am_8.multiply(new BigDecimal(1.08));
					am_10=am_10.multiply(new BigDecimal(1.10));
				}

				AnnuityPayment eachAnnuityPayment = new AnnuityPayment();
				eachAnnuityPayment.setPayoutmonth(stepyear);
				eachAnnuityPayment.setAnnuityMonth6(am_6.setScale(19, RoundingMode.FLOOR));
				eachAnnuityPayment.setAnnuityMonth8(am_8.setScale(19, RoundingMode.FLOOR));
				eachAnnuityPayment.setAnnuityMonth10(am_10.setScale(19, RoundingMode.FLOOR));
				eachAnnuityPayment.setTempValue((stepyear+intBasePlanTerm+intProposerAge)-1);
				annuityPaymentInsertionList.add(eachAnnuityPayment);
				
				if(intDrawdownterm ==5){
					if(stepyear==1||stepyear==3||stepyear==5){
						annuityPaymentList.add(eachAnnuityPayment);
					}
				}else if(intDrawdownterm ==10){
					if(stepyear==1||stepyear==3||stepyear==5||stepyear==7||stepyear==10){
						annuityPaymentList.add(eachAnnuityPayment);
					}				
				}else if(intDrawdownterm ==15){
					if(stepyear==1||stepyear==3||stepyear==5||stepyear==7||stepyear==9||stepyear==11||stepyear==13||stepyear==15){
						annuityPaymentList.add(eachAnnuityPayment);
					}				
				}else if(intDrawdownterm ==20){
					if(stepyear==1||stepyear==3||stepyear==5||stepyear==7||stepyear==9||stepyear==11||stepyear==13||stepyear==15||stepyear==17||stepyear==20){
						annuityPaymentList.add(eachAnnuityPayment);
					}				
				}
				stepyear = stepyear + 1;
				
			}
			//Inserting all records
			if(annuityPaymentInsertionList!=null){
				if(annuityPaymentInsertionList.size()>0){
					projectionDto.insertAnnuityPayments(annuityPaymentInsertionList);
				}
			}
      return annuityPaymentList;

		}catch (Exception e) {
			e.printStackTrace();
		}
    return null;
	}
}
