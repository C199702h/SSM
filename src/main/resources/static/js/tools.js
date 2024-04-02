// 获取当前 url 中某个参数的方法
function getURLParam(key) {
    var params = location.search;
    if (params.indexOf("?") >= 0) {
        params = params.substring(params.indexOf("?") + 1);
        var paramArr = params.split('&');
        for (var i = 0; i < paramArr.length; i++) {
            var namevalues = paramArr[i].split("=");
            if (namevalues[0] == key) {
                return namevalues[1];
            }
        }
    } else {
        return "";
    }
}
var descLength = 80; // 简介的长度

// 字符串截取，将文章正文截取成简介
function mySubstr(content) {

    if (content.length > descLength) {
        return content.substr(0, descLength);
    }
    return content;
}