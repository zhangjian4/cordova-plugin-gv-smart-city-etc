# cordova-plugin-gv-smart-city-etc
金溢ETC手持机SDK—Cordova插件

## 安装
`cordova plugin add https://github.com/zhangjian4/cordova-plugin-gv-smart-city-etc.git`

## 打印机接口
1. 连接打印机
```javascript
GvSmartCityEtc.printer.openConnection(successCallback,errorCallback);
```
### Example
```javascript
GvSmartCityEtc.printer.openConnection(
    function(result){
        console.log(result);
    },
    function(error){
        console.error(error);
    });
```
