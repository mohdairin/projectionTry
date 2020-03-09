package cordova.plugin.projection;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import cordova.plugin.projection.action.ProjectionAction;
import cordova.plugin.projection.action.ProjectionAction_14_15;
import cordova.plugin.projection.action.ProjectionAction_24;
import cordova.plugin.projection.action.PensionPayoutAction;
import cordova.plugin.projection.action.ProductCashFlowAction;
import cordova.plugin.projection.pojo.oProjection;
import cordova.plugin.projection.pojo.Projection14_15;
import cordova.plugin.projection.pojo.Projection24;
import cordova.plugin.projection.pojo.AnnuityPayment;
import cordova.plugin.projection.pojo.ProductCashFlow;

public class Projection extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        else if (action.equals("add")) {
            this.add(args, callbackContext);
            return true;
        }
        else if (action.equals("calcProjection")) {
            this.calcProjection(args, callbackContext);
            return true;
        }
        else if (action.equals("calcProjection14_15")) {
            this.calcProjection14_15(args, callbackContext);
            return true;
        }
        else if (action.equals("calcAnnuityPayment")) {
            this.calcAnnuityPayment(args, callbackContext);
            return true;
        }
        else if (action.equals("calcCashFlow")) {
            this.calcCashFlow(args, callbackContext);
            return true;
        }
        else if (action.equals("calcProjection24")) {
            this.calcProjection24(args, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void calcProjection(JSONArray args, CallbackContext callbackContext) {
        if(args != null){
            try{
                String sPlanCode = args.getJSONObject(0).getString("plancode");
                int iTerm = Integer.parseInt(args.getJSONObject(0).getString("term"));
                BigDecimal iAge =  new BigDecimal(args.getJSONObject(0).getString("age"));
                int iFreq  = Integer.parseInt(args.getJSONObject(0).getString("frequency"));
                BigDecimal bPrem  = new BigDecimal(args.getJSONObject(0).getString("premium"));
                BigDecimal bBaseSA = new BigDecimal(args.getJSONObject(0).getString("baseSA"));

                ProjectionAction projectionAction = new ProjectionAction(this.cordova.getActivity().getApplicationContext());
                List<oProjection> projectionList =  new ArrayList<oProjection>();
                projectionList = projectionAction.bfnCalcProjection(sPlanCode, iTerm, iAge, iFreq, bPrem, bBaseSA);


                callbackContext.success("callBack => list Size: "+ projectionList.size() +" Plancode:" + sPlanCode + " Term:"+ iTerm +
                                        " Age:"+ iAge + " Frequency:" + iFreq + " Premium:" + bPrem +
                                        " BaseSA:" +bBaseSA);

            }catch(Exception ex){
                callbackContext.error("Projection:- " + ex);
            }
        }
        else{
            callbackContext.error("Null Value!");
        }
    }
    private void calcProjection14_15(JSONArray args, CallbackContext callbackContext) {
        if(args != null){
            try{
                String sPlanCode = args.getJSONObject(0).getString("plancode");
                int iTerm = Integer.parseInt(args.getJSONObject(0).getString("term"));
                BigDecimal iAge = new BigDecimal(args.getJSONObject(0).getString("age"));
                int iFreq  = Integer.parseInt(args.getJSONObject(0).getString("frequency"));
                BigDecimal bPrem  = new BigDecimal(args.getJSONObject(0).getString("premium"));
                BigDecimal bBaseSA = new BigDecimal(args.getJSONObject(0).getString("baseSA"));
                BigDecimal bigDecLTRAnnualPrem = new BigDecimal(args.getJSONObject(0).getString("bigDecLTRAnnualPrem"));
                int intMultiplier =Integer.parseInt(args.getJSONObject(0).getString("intMultiplier"));

                ProjectionAction_14_15 projectionAction = new ProjectionAction_14_15(this.cordova.getActivity().getApplicationContext());


                List<Projection14_15> projectionList14_15=projectionAction.generateProjections(sPlanCode, iTerm, iAge, iFreq,bPrem, bBaseSA,bigDecLTRAnnualPrem, intMultiplier);
                System.out.println(projectionList14_15.size());


                callbackContext.success("Projection 14_15callBack => List Size 14_15====>"+projectionList14_15.size()+" Plancode:" + sPlanCode + " Term:"+ iTerm +
                                        " Age:"+ iAge + " Frequency:" + iFreq + " Premium:" + bPrem +
                                        " BaseSA:" +bBaseSA +" bigDecLTRAnnualPrem:"+ bigDecLTRAnnualPrem+" intMultiplier:"+intMultiplier);

            }catch(Exception ex){
                callbackContext.error("Projection:- " + ex);
            }
        }
        else{
            callbackContext.error("Null Value!");
        }
    }
    private void calcAnnuityPayment(JSONArray args, CallbackContext callbackContext) {
        if(args != null){
            try{
                BigDecimal FundEnd_6 = new BigDecimal(args.getJSONObject(0).getString("FundEnd_6"));
                BigDecimal FundEnd_8 = new BigDecimal(args.getJSONObject(0).getString("FundEnd_8"));
                BigDecimal FundEnd_10 = new BigDecimal(args.getJSONObject(0).getString("FundEnd_10"));
                int PensionPayTerm = Integer.parseInt(args.getJSONObject(0).getString("PensionPayTerm"));
                int PensionPayFreq = Integer.parseInt(args.getJSONObject(0).getString("PensionPayFreq"));
                int ProposerAge = Integer.parseInt(args.getJSONObject(0).getString("ProposerAge"));
                int BasePlanTerm = Integer.parseInt(args.getJSONObject(0).getString("BasePlanTerm"));

                PensionPayoutAction pensionPayoutAction=new PensionPayoutAction(this.cordova.getActivity().getApplicationContext());
                List<AnnuityPayment> pensionPayoutList=pensionPayoutAction.generateAnnuityPayment(FundEnd_6, FundEnd_8, FundEnd_10, PensionPayTerm, PensionPayFreq, ProposerAge, BasePlanTerm);
                // System.out.println(pensionPayoutList.size());

                callbackContext.success("\nAnnuity Payment====>"+pensionPayoutList.size());

            }catch(Exception ex){
                callbackContext.error("Annuity Payment:- " + ex);
            }
        }
        else{
            callbackContext.error("Null Value!");
        }
    }
    private void calcCashFlow(JSONArray args, CallbackContext callbackContext) {
        if(args != null){
            try{
                String sPlanCode = args.getJSONObject(0).getString("plancode");
                int iTerm = Integer.parseInt(args.getJSONObject(0).getString("term"));
                BigDecimal iAge = new BigDecimal(args.getJSONObject(0).getString("age"));
                int iFreq  = Integer.parseInt(args.getJSONObject(0).getString("frequency"));
                BigDecimal bPrem  = new BigDecimal(args.getJSONObject(0).getString("premium"));
                BigDecimal bBaseSA = new BigDecimal(args.getJSONObject(0).getString("baseSA"));
                String gender = args.getJSONObject(0).getString("gender");
                BigDecimal bigDecInflation_Guard =  new BigDecimal(args.getJSONObject(0).getString("inflationGuard"));

                ProductCashFlowAction productCashFlowAction=new ProductCashFlowAction(this.cordova.getActivity().getApplicationContext());
                List<ProductCashFlow> cashFlowList = productCashFlowAction.generateProductCashFlow(sPlanCode, iTerm, iAge,iFreq, bPrem, bBaseSA, gender, bigDecInflation_Guard);

                // List<ProductCashFlow> cashFlowList=productCashFlowAction.generateProductCashFlow("PRODUCT23", 12, 40,50000,new BigDecimal(20000),new BigDecimal(110000), "F", new BigDecimal(1));
                // callbackContext.success("\nCash Flow====>"+cashFlowList.size());

                // productCashFlowDto.fetchRate(sPlanCode, 'M', iAge, counter);

                callbackContext.success("callBack => list Size: "+ cashFlowList.size() +" Plancode:" + sPlanCode + " Term:"+ iTerm +
                " Age:"+ iAge + " Frequency:" + iFreq + " Premium:" + bPrem +
                " BaseSA:" +bBaseSA);

            }catch(Exception ex){
                callbackContext.error("Cash Flow:- " + ex);
            }
        }
        else{
            callbackContext.error("Null Value!");
        }
    }
    private void calcProjection24(JSONArray args, CallbackContext callbackContext) {
        if(args != null){
            try{
                String sPlanCode = args.getJSONObject(0).getString("plancode");
                int iTerm = Integer.parseInt(args.getJSONObject(0).getString("term"));
                BigDecimal iAge =  new BigDecimal(args.getJSONObject(0).getString("age"));
                int iFreq  = Integer.parseInt(args.getJSONObject(0).getString("frequency"));
                BigDecimal bPrem  = new BigDecimal(args.getJSONObject(0).getString("premium"));
                BigDecimal bBaseSA = new BigDecimal(args.getJSONObject(0).getString("baseSA"));
                BigDecimal bigDecLTRAnnualPrem = new BigDecimal(args.getJSONObject(0).getString("bigDecLTRAnnualPrem"));
                int intMultiplier =Integer.parseInt(args.getJSONObject(0).getString("intMultiplier"));
                BigDecimal bigDecInflation_Guard =  new BigDecimal(args.getJSONObject(0).getString("bigDecInflation_Guard"));

                ProjectionAction_24 projectionAction = new ProjectionAction_24(this.cordova.getActivity().getApplicationContext());

              //  List<Projection14_15> projectionList14_15=projectionAction.generateProjections("PRODUCT24", 10, 50, 1,new BigDecimal(6000), new BigDecimal(4000), new BigDecimal(2000), 1, new BigDecimal(1));
                List<Projection24> Projection24=projectionAction.generateProjections(sPlanCode,iTerm, iAge, iFreq,bPrem, bBaseSA, bigDecLTRAnnualPrem, intMultiplier, bigDecInflation_Guard);


                callbackContext.success("Projection 24 => list Size: "+Projection24.size() +"\nList Size 24====>"+Projection24.size()+" Plancode:" + sPlanCode + " Term:"+ iTerm +
                                        " Age:"+ iAge + " Frequency:" + iFreq + " Premium:" + bPrem +
                                        " BaseSA:" +bBaseSA +" bigDecLTRAnnualPrem:"+ bigDecLTRAnnualPrem+" intMultiplier:"+intMultiplier+" Inflation Guard:"+bigDecInflation_Guard);

            }catch(Exception ex){
                callbackContext.error("Projection:- " + ex);
            }
        }
        else{
            callbackContext.error("Null Value!");
        }
    }
    private void add(JSONArray args, CallbackContext callbackContext) {
        if(args != null){
            try{
                int p1 = Integer.parseInt(args.getJSONObject(0).getString("param1"));
                int p2 = Integer.parseInt(args.getJSONObject(0).getString("param2"));

                callbackContext.success("" + (p1+p2));

            }catch(Exception ex){
                callbackContext.error("Add:- "+ ex);
            }
        }
        else{
            callbackContext.error("Null Value!");
        }
    }
}
