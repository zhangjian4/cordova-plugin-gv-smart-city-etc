package com.benbenjun.cordova.gv;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.genvict.etc.sdk.api.parking.EtcParkingChargeAPI;
import com.genvict.etc.sdk.api.parking.IEtcParkingChargeAPI;
import com.genvict.etc.sdk.api.parking.Result;
import com.genvict.etc.sdk.api.parking.local.PsamConfig;
import com.genvict.etc.sdk.core.SdkConstant;
import com.genvict.etc.sdk.core.entity.EtcInfo;
import com.genvict.etc.sdk.core.entity.KeyChannel;
import com.genvict.etc.sdk.core.entity.ObuVehInfo;
import com.genvict.etc.sdk.core.entity.TransInfo;
import com.genvict.etc.sdk.core.utils.GvConvertUtils;
import com.google.gson.Gson;

/**
 * This class echoes a string called from JavaScript.
 */
public class ETC extends CordovaPlugin {

    private IEtcParkingChargeAPI etcParkingChargeAPI;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        synchronized (this){
            if (this.etcParkingChargeAPI == null) {
                this.etcParkingChargeAPI = EtcParkingChargeAPI.getInstance(this.cordova.getContext());
            }
        }
        // 获取OBU个体信息+用户卡信息
        if (action.equals("getEtcInfo")) {
            Result<EtcInfo> result = this.etcParkingChargeAPI.getEtcInfo();
            resultCallback(result, callbackContext);
            return true;
        }
        //获取ETC车辆信息(明文)
        if (action.equals("getObuVehInfo")) {
            KeyChannel keyChannel = getKeyChannel(args.getString(0));
            Result<ObuVehInfo> result = this.etcParkingChargeAPI.getObuVehInfo(keyChannel);
            resultCallback(result, callbackContext);
            return true;
        }
        //获取ETC车辆信息(密文)
        if (action.equals("getObuVehCiphertext")) {
            Result<String> result = this.etcParkingChargeAPI.getObuVehCiphertext();
            resultCallback(result, callbackContext);
            return true;
        }
        //执行ETC扣费指令接口
        if (action.equals("consume")) {
            KeyChannel keyChannel = getKeyChannel(args.getString(0));
            String vehiclePlateNumber = args.getString(1);
            int payMoney = args.getInt(2);
            Result<TransInfo> result = this.etcParkingChargeAPI.consume(keyChannel, vehiclePlateNumber, payMoney);
            resultCallback(result, callbackContext);
            return true;
        }
        //启用调试日志
        if (action.equals("enableDebugLogger")) {
            boolean enableLogger = args.getBoolean(0);
            this.etcParkingChargeAPI.enableDebugLogger(enableLogger);
            callbackContext.success();
            return true;
        }
        //设置PSAM配置
        if (action.equals("setPsamConfig")) {
            JSONObject configJSON = args.getJSONObject(0);
            PsamConfig config = new Gson().fromJson(configJSON.toString(), PsamConfig.class);
            this.etcParkingChargeAPI.setPsamConfig(config);
            callbackContext.success();
            return true;
        }
        //是否启用校验OBU
        if (action.equals("isVerifyObu")) {
            boolean status = args.getBoolean(0);
            this.etcParkingChargeAPI.isVerifyObu(status);
            callbackContext.success();
            return true;
        }
        return false;
    }

    private <T> void resultCallback(Result<T> result, CallbackContext callbackContext) throws JSONException {
        if (result.getCode() == 0) {
            if (result.getData() instanceof String) {
                callbackContext.success((String) result.getData());
            } else {
                JSONObject json = new JSONObject(new Gson().toJson(result.getData()));
                callbackContext.success(json);
            }
        } else {
            callbackContext.error(result.getMsg());
        }
    }

    private KeyChannel getKeyChannel(String str) {
        return "cloud".equalsIgnoreCase(str) ? KeyChannel.CLOUD : KeyChannel.LOCAL;
    }

}
