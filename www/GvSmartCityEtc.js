var exec = require("cordova/exec");

const printer = {
  // 连接打印机
  openConnection: function (success, error) {
    exec(success, error, "GvSmartCityEtc", "openConnection", []);
  },
  // 关闭打印机
  closeConnection: function (success, error) {
    exec(success, error, "GvSmartCityEtc", "openConnection", []);
  },
  // 初始化打印机
  init: function (success, error) {
    exec(success, error, "GvSmartCityEtc", "init", []);
  },
  // 设置打印机
  setPrinter: function (command, value, success, error) {
    exec(success, error, "GvSmartCityEtc", "setPrinter", [command, value]);
  },
  // 设置文本编码
  setEncoding: function (charsetName, success, error) {
    exec(success, error, "GvSmartCityEtc", "setEncoding", [charsetName]);
  },
  // 设置字符放大倍数
  setCharacterMultiple: function (x, y, success, error) {
    exec(success, error, "GvSmartCityEtc", "setCharacterMultiple", [x, y]);
  },
  // 设置打印区域左边距
  setLeftMargin: function (nOrgx, success, error) {
    exec(success, error, "GvSmartCityEtc", "setLeftMargin", [nOrgx]);
  },
  // 设置打印模式
  setPrintModel: function (
    isBold,
    isDoubleHeight,
    isDoubleWidth,
    isUnderLine,
    success,
    error
  ) {
    exec(success, error, "GvSmartCityEtc", "setPrintModel", [
      isBold,
      isDoubleHeight,
      isDoubleWidth,
      isUnderLine,
    ]);
  },
  // 走纸N行
  feedLine: function (line, success, error) {
    exec(success, error, "GvSmartCityEtc", "feedLine", [line]);
  },
  // 设置对齐方式
  setAlign: function (align, success, error) {
    exec(success, error, "GvSmartCityEtc", "setAlign", [align]);
  },
  // 打印文本
  printText: function (
    content,
    nOrgx,
    nWidthTimes,
    nHeightTimes,
    nFontType,
    nFontStyle,
    success,
    error
  ) {
    if (nHeightTimes === undefined) {
      exec(nOrgx, nWidthTimes, "GvSmartCityEtc", "printText", [content]);
    } else {
      exec(success, error, "GvSmartCityEtc", "printText", [
        content,
        nOrgx,
        nWidthTimes,
        nHeightTimes,
        nFontType,
        nFontStyle,
      ]);
    }
  },
  // 打印二维码
  printQRCode: function (url, width, height, success, error) {
    exec(success, error, "GvSmartCityEtc", "printQRCode", [url, width, height]);
  },
  // 打印条形码
  printBarCode: function (
    strCodedata,
    nOrgx,
    nType,
    nWidthX,
    nHeight,
    nHriFontPosition,
    success,
    error
  ) {
    exec(success, error, "GvSmartCityEtc", "printBarCode", [
      strCodedata,
      nOrgx,
      nType,
      nWidthX,
      nHeight,
      nHriFontPosition,
    ]);
  },
};

const etc = {
  // 获取OBU个体信息+用户卡信息
  getEtcInfo: function (success, error) {
    exec(success, error, "ETC", "getEtcInfo", []);
  },
  //获取ETC车辆信息(明文)
  getObuVehInfo: function (keyChannel, success, error) {
    exec(success, error, "ETC", "getObuVehInfo", [keyChannel]);
  },
  //获取ETC车辆信息(密文)
  getObuVehCiphertext: function (success, error) {
    exec(success, error, "ETC", "getObuVehCiphertext", []);
  },
  //执行ETC扣费指令接口
  consume: function (keyChannel, vehiclePlateNumber, payMoney, success, error) {
    exec(success, error, "ETC", "consume", [
      keyChannel,
      vehiclePlateNumber,
      payMoney,
    ]);
  },
  //启用调试日志
  enableDebugLogger: function (enableLogger, success, error) {
    exec(success, error, "ETC", "enableDebugLogger", [enableLogger]);
  },
  //设置PSAM配置
  setPsamConfig: function (config, success, error) {
    exec(success, error, "ETC", "setPsamConfig", [config]);
  },
  //是否启用校验OBU
  isVerifyObu: function (status, success, error) {
    exec(success, error, "ETC", "isVerifyObu", [status]);
  },
};

exports.printer = printer;
exports.etc = etc;
