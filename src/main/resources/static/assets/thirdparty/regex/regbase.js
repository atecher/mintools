/*
 XRegExp 0.2.5
 (c)Steven Levithan 
 MIT license

 Provides an augmented, cross-browser implementation of regular
 expressions, including support for additional flags.
 */
(function () {
    if (window.XRegExp) return;
    var real = {
        RegExp: RegExp,
        exec: RegExp.prototype.exec,
        match: String.prototype.match,
        replace: String.prototype.replace
    };
    var re = {
        extended: /(?:[^[#\s\\]+|\\(?:[\S\s]|$)|\[\^?]?(?:[^\\\]]+|\\(?:[\S\s]|$))*]?)+|(\s*#[^\n\r\u2028\u2029]*\s*|\s+)([?*+]|{[0-9]+(?:,[0-9]*)?})?/g,
        singleLine: /(?:[^[\\.]+|\\(?:[\S\s]|$)|\[\^?]?(?:[^\\\]]+|\\(?:[\S\s]|$))*]?)+|\./g,
        characterClass: /(?:[^\\[]+|\\(?:[\S\s]|$))+|\[\^?(]?)(?:[^\\\]]+|\\(?:[\S\s]|$))*]?/g,
        capturingGroup: /(?:[^[(\\]+|\\(?:[\S\s]|$)|\[\^?]?(?:[^\\\]]+|\\(?:[\S\s]|$))*]?|\((?=\?))+|(\()(?:<([$\w]+)>)?/g,
        namedBackreference: /(?:[^\\[]+|\\(?:[^k]|$)|\[\^?]?(?:[^\\\]]+|\\(?:[\S\s]|$))*]?|\\k(?!<[$\w]+>))+|\\k<([$\w]+)>([0-9]?)/g,
        replacementVariable: /(?:[^$]+|\$(?![1-9$&`']|{[$\w]+}))+|\$(?:([1-9]\d*|[$&`'])|{([$\w]+)})/g
    };
    XRegExp = function (pattern, flags) {
        flags = flags || "";
        if (flags.indexOf("x") > -1) {
            pattern = real.replace.call(pattern, re.extended, function ($0, $1, $2) {
                return $1 ? ($2 || "(?:)") : $0
            })
        }
        ;
        var hasNamedCapture = false;
        if (flags.indexOf("k") > -1) {
            var captureNames = [];
            pattern = real.replace.call(pattern, re.capturingGroup, function ($0, $1, $2) {
                if ($1) {
                    if ($2) hasNamedCapture = true;
                    captureNames.push($2 || null);
                    return "("
                } else {
                    return $0
                }
            });
            if (hasNamedCapture) {
                pattern = real.replace.call(pattern, re.namedBackreference, function ($0, $1, $2) {
                    var index = $1 ? captureNames.indexOf($1) : -1;
                    return index > -1 ? "\\" + (index + 1) + ($2 ? "(?:)" + $2 : "") : $0
                })
            }
        }
        ;
        pattern = real.replace.call(pattern, re.characterClass, function ($0, $1) {
            return $1 ? real.replace.call($0, "]", "\\]") : $0
        });
        if (flags.indexOf("s") > -1) {
            pattern = real.replace.call(pattern, re.singleLine, function ($0) {
                return $0 === "." ? "[\\S\\s]" : $0
            })
        }
        ;
        var regex = real.RegExp(pattern, real.replace.call(flags, /[sxk]+/g, ""));
        if (hasNamedCapture) regex._captureNames = captureNames;
        return regex
    };
    RegExp.prototype.addFlags = function (flags) {
        flags = (flags || "") + (this.global ? "g" : "") + (this.ignoreCase ? "i" : "") + (this.multiline ? "m" : "");
        var regex = new XRegExp(this.source, flags);
        if (!regex._captureNames && this._captureNames) regex._captureNames = this._captureNames.slice(0);
        return regex
    };
    RegExp.prototype.exec = function (str) {
        var result = real.exec.call(this, str);
        if (!(this._captureNames && result && result.length > 1)) return result;
        for (var i = 1; i < result.length; i++) {
            var name = this._captureNames[i - 1];
            if (name) result[name] = result[i]
        }
        ;
        return result
    };
    String.prototype.match = function (regex) {
        if (!regex._captureNames || regex.global) return real.match.call(this, regex);
        return regex.exec(this)
    };
    String.prototype.replace = function (search, replacement) {
        if (!(search instanceof real.RegExp && search._captureNames)) return real.replace.apply(this, arguments);
        if (typeof replacement === "function") {
            return real.replace.call(this, search, function () {
                arguments[0] = new String(arguments[0]);
                for (var i = 0; i < search._captureNames.length; i++) {
                    if (search._captureNames[i]) arguments[0][search._captureNames[i]] = arguments[i + 1]
                }
                ;
                return replacement.apply(window, arguments)
            })
        } else {
            return real.replace.call(this, search, function () {
                var args = arguments;
                return real.replace.call(replacement, re.replacementVariable, function ($0, $1, $2) {
                    if ($1) {
                        switch ($1) {
                            case "$":
                                return "$";
                            case "&":
                                return args[0];
                            case "`":
                                return args[args.length - 1].slice(0, args[args.length - 2]);
                            case "'":
                                return args[args.length - 1].slice(args[args.length - 2] + args[0].length);
                            default:
                                var literalNumbers = "";
                                $1 = +$1;
                                while ($1 > search._captureNames.length) {
                                    literalNumbers = $1.split("").pop() + literalNumbers;
                                    $1 = Math.floor($1 / 10)
                                }
                                ;
                                return ($1 ? args[$1] : "$") + literalNumbers
                        }
                    } else if ($2) {
                        var index = search._captureNames.indexOf($2);
                        return index > -1 ? args[index + 1] : $0
                    } else {
                        return $0
                    }
                })
            })
        }
    }
})();
XRegExp.cache = function (pattern, flags) {
    var key = "/" + pattern + "/" + (flags || "");
    return XRegExp.cache[key] || (XRegExp.cache[key] = new XRegExp(pattern, flags))
};
XRegExp.overrideNative = function () {
    RegExp = XRegExp
};
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (item, from) {
        var len = this.length;
        for (var i = (from < 0) ? Math.max(0, len + from) : from || 0; i < len; i++) {
            if (this[i] === item) return i
        }
        ;
        return -1
    }
}
