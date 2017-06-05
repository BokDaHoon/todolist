StringBuilderEx = function () {
    this._buffer = new Array();
}
StringBuilderEx.prototype =
    {
        // This method appends the string into an array 
        append: function (text) {
            this._buffer[this._buffer.length] = text;
        },

        // This method does concatenation using JavaScript built-in function
        toString: function () {
            return this._buffer.join("");
        }
    };
//StringBuffer 배열 선언
function StringBuffer() {
    this.__strings__ = [];
}

//StringBuffer에 append 속성부여
StringBuffer.prototype.append = function (str) {
    this.__strings__.push(str);
}

//StringBuffer에 toString 송성 부여
StringBuffer.prototype.toString = function (delimiter) {
    return this.__strings__.join(delimiter);
}