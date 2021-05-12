package com.benbenjun.cordova.gv;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.genvict.printer.sdk.PrinterInstance;

/**
 * This class echoes a string called from JavaScript.
 */
public class GvSmartCityEtc extends CordovaPlugin {

    private PrinterInstance mPrinter;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        synchronized (this){
            if(this.mPrinter==null){
                this.mPrinter=PrinterInstance.getInstance(this.cordova.getActivity());
            }
        }
        // 连接打印机
        if (action.equals("openConnection")) {
            int result = mPrinter.openConnection();
            callbackContext.success(result);
            return true;
        }
        // 关闭打印机
        if (action.equals("closeConnection")) {
            mPrinter.closeConnection();
            callbackContext.success();
            return true;
        }
        // 初始化打印机
        if (action.equals("init")) {
            int result = mPrinter.init();
            callbackContext.success(result);
            return true;
        }
        // 设置打印机
        if (action.equals("setPrinter")) {
            int command = args.getInt(0);
            int value = args.getInt(1);
            int result = mPrinter.setPrinter(command,value);
            callbackContext.success(result);
            return true;
        }
        // 设置文本编码
        if (action.equals("setEncoding")) {
            String charsetName = args.getString(0);
            mPrinter.setEncoding(charsetName);
            callbackContext.success();
            return true;
        }
        // 设置字符放大倍数
        if (action.equals("setCharacterMultiple")) {
            int x = args.getInt(0);
            int y = args.getInt(1);
            int result = mPrinter.setCharacterMultiple(x,y);
            callbackContext.success(result);
            return true;
        }
        // 设置打印区域左边距
        if (action.equals("setLeftMargin")) {
            int nOrgx = args.getInt(0);
            int result = mPrinter.setLeftMargin(nOrgx);
            callbackContext.success(result);
            return true;
        }
        // 设置打印模式
        if (action.equals("setPrintModel")) {
            boolean isBold = args.getBoolean(0);
            boolean isDoubleHeight = args.getBoolean(1);
            boolean isDoubleWidth = args.getBoolean(2);
            boolean isUnderLine = args.getBoolean(3);
            int result = mPrinter.setPrintModel(isBold,isDoubleHeight,isDoubleWidth,isUnderLine);
            callbackContext.success(result);
            return true;
        }
        // 走纸N行
        if (action.equals("feedLine")) {
            int line = args.getInt(0);
            int result = mPrinter.feedLine(line);
            callbackContext.success(result);
            return true;
        }
        // 设置对齐方式
        if (action.equals("setAlign")) {
            int align = args.getInt(0);
            int result = mPrinter.setAlign(align);
            callbackContext.success(result);
            return true;
        }
        // 打印文本
        if (action.equals("printText")) {
            String content = args.getString(0);
            if(args.length()==1){
                int result = mPrinter.printText(content);
                callbackContext.success(result);
            }else{
                int nOrgx=args.getInt(1);
                int nWidthTimes=args.getInt(2);
                int nHeightTimes=args.getInt(3);
                int nFontType=args.getInt(4);
                int nFontStyle=args.getInt(5);
                int result = mPrinter.printText(content, nOrgx, nWidthTimes, nHeightTimes, nFontType, nFontStyle);
                callbackContext.success(result);
            }
            return true;
        }
        // 打印二维码
        if (action.equals("printQRCode")) {
            String url = args.getString(0);
            int width = args.getInt(1);
            int height = args.getInt(2);
            int result = mPrinter.printQRCode(url,width,height);
            callbackContext.success(result);
            return true;
        }
        // 打印条形码
        if (action.equals("printBarCode")) {
            String strCodedata = args.getString(0);
            int nOrgx = args.getInt(1);
            byte nType = (byte)args.getInt(2);
            int nWidthX = args.getInt(3);
            int nHeight = args.getInt(4);
            int nHriFontPosition = args.getInt(5);
            int result = mPrinter.printBarCode(strCodedata,nOrgx,nType,nWidthX,nHeight,nHriFontPosition);
            callbackContext.success(result);
            return true;
        }
        return false;
    }

}
