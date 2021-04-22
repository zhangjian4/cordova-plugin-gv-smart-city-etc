var exec = require("cordova/exec");

exports.printer = {
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
