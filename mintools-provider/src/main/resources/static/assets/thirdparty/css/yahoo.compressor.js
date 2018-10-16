function cssbeautify(style, opt) {
    'use strict';
    var options, index = 0, length = style.length, blocks, formatted = '', ch, ch2, str, state, State, depth, quote,
        comment, openbracesuffix = true, trimRight;
    options = arguments.length > 1 ? opt : {};
    if (typeof options.indent === 'undefined') {
        options.indent = '    ';
    }
    if (typeof options.openbrace === 'string') {
        openbracesuffix = (options.openbrace === 'end-of-line');
    }

    function isWhitespace(c) {
        return (c === ' ') || (c === '\n') || (c === '\t') || (c === '\r') || (c === '\f');
    }

    function isQuote(c) {
        return (c === '\'') || (c === '"');
    }

    function isName(c) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || '-_*.:#'.indexOf(c) >= 0;
    }

    function appendIndent() {
        var i;
        for (i = depth; i > 0; i -= 1) {
            formatted += options.indent;
        }
    }

    function openBlock() {
        formatted = trimRight(formatted);
        if (openbracesuffix) {
            formatted += ' {';
        } else {
            formatted += '\n';
            appendIndent();
            formatted += '{';
        }
        if (ch2 !== '\n') {
            formatted += '\n';
        }
        depth += 1;
    }

    function closeBlock() {
        depth -= 1;
        formatted = trimRight(formatted);
        formatted += '\n';
        appendIndent();
        formatted += '}';
        blocks.push(formatted);
        formatted = '';
    }

    if (String.prototype.trimRight) {
        trimRight = function (s) {
            return s.trimRight();
        };
    } else {
        trimRight = function (s) {
            return s.replace(/\s+$/, '');
        };
    }
    State = {Start: 0, AtRule: 1, Block: 2, Selector: 3, Ruleset: 4, Property: 5, Separator: 6, Expression: 7};
    depth = 0;
    state = State.Start;
    comment = false;
    blocks = [];
    style = style.replace(/\r\n/g, '\n');
    while (index < length) {
        ch = style.charAt(index);
        ch2 = style.charAt(index + 1);
        index += 1;
        if (isQuote(quote)) {
            formatted += ch;
            if (ch === quote) {
                quote = null;
            }
            if (ch === '\\' && ch2 === quote) {
                formatted += ch2;
                index += 1;
            }
            continue;
        }
        if (isQuote(ch)) {
            formatted += ch;
            quote = ch;
            continue;
        }
        if (comment) {
            formatted += ch;
            if (ch === '*' && ch2 === '/') {
                comment = false;
                formatted += ch2;
                index += 1;
            }
            continue;
        } else {
            if (ch === '/' && ch2 === '*') {
                comment = true;
                formatted += ch;
                formatted += ch2;
                index += 1;
                continue;
            }
        }
        if (state === State.Start) {
            if (blocks.length === 0) {
                if (isWhitespace(ch) && formatted.length === 0) {
                    continue;
                }
            }
            if (ch <= ' ' || ch.charCodeAt(0) >= 128) {
                state = State.Start;
                formatted += ch;
                continue;
            }
            if (isName(ch) || (ch === '@')) {
                str = trimRight(formatted);
                if (str.length === 0) {
                    if (blocks.length > 0) {
                        formatted = '\n\n';
                    }
                } else {
                    if (str.charAt(str.length - 1) === '}' || str.charAt(str.length - 1) === ';') {
                        formatted = str + '\n\n';
                    } else {
                        while (true) {
                            ch2 = formatted.charAt(formatted.length - 1);
                            if (ch2 !== ' ' && ch2.charCodeAt(0) !== 9) {
                                break;
                            }
                            formatted = formatted.substr(0, formatted.length - 1);
                        }
                    }
                }
                formatted += ch;
                state = (ch === '@') ? State.AtRule : State.Selector;
                continue;
            }
        }
        if (state === State.AtRule) {
            if (ch === ';') {
                formatted += ch;
                state = State.Start;
                continue;
            }
            if (ch === '{') {
                openBlock();
                state = State.Block;
                continue;
            }
            formatted += ch;
            continue;
        }
        if (state === State.Block) {
            if (isName(ch)) {
                str = trimRight(formatted);
                if (str.length === 0) {
                    if (blocks.length > 0) {
                        formatted = '\n\n';
                    }
                } else {
                    if (str.charAt(str.length - 1) === '}') {
                        formatted = str + '\n\n';
                    } else {
                        while (true) {
                            ch2 = formatted.charAt(formatted.length - 1);
                            if (ch2 !== ' ' && ch2.charCodeAt(0) !== 9) {
                                break;
                            }
                            formatted = formatted.substr(0, formatted.length - 1);
                        }
                    }
                }
                appendIndent();
                formatted += ch;
                state = State.Selector;
                continue;
            }
            if (ch === '}') {
                closeBlock();
                state = State.Start;
                continue;
            }
            formatted += ch;
            continue;
        }
        if (state === State.Selector) {
            if (ch === '{') {
                openBlock();
                state = State.Ruleset;
                continue;
            }
            if (ch === '}') {
                closeBlock();
                state = State.Start;
                continue;
            }
            formatted += ch;
            continue;
        }
        if (state === State.Ruleset) {
            if (ch === '}') {
                closeBlock();
                state = State.Start;
                if (depth > 0) {
                    state = State.Block;
                }
                continue;
            }
            if (ch === '\n') {
                formatted = trimRight(formatted);
                formatted += '\n';
                continue;
            }
            if (!isWhitespace(ch)) {
                formatted = trimRight(formatted);
                formatted += '\n';
                appendIndent();
                formatted += ch;
                state = State.Property;
                continue;
            }
            formatted += ch;
            continue;
        }
        if (state === State.Property) {
            if (ch === ':') {
                formatted = trimRight(formatted);
                formatted += ': ';
                state = State.Expression;
                if (isWhitespace(ch2)) {
                    state = State.Separator;
                }
                continue;
            }
            if (ch === '}') {
                closeBlock();
                state = State.Start;
                if (depth > 0) {
                    state = State.Block;
                }
                continue;
            }
            formatted += ch;
            continue;
        }
        if (state === State.Separator) {
            if (!isWhitespace(ch)) {
                formatted += ch;
                state = State.Expression;
                continue;
            }
            if (isQuote(ch2)) {
                state = State.Expression;
            }
            continue;
        }
        if (state === State.Expression) {
            if (ch === '}') {
                closeBlock();
                state = State.Start;
                if (depth > 0) {
                    state = State.Block;
                }
                continue;
            }
            if (ch === ';') {
                formatted = trimRight(formatted);
                formatted += ';\n';
                state = State.Ruleset;
                continue;
            }
            formatted += ch;
            continue;
        }
        formatted += ch;
    }
    formatted = blocks.join('') + formatted;
    return formatted;
}

var YAHOO = YAHOO || {};
YAHOO.compressor = YAHOO.compressor || {};
YAHOO.compressor._extractDataUrls = function (css, preservedTokens) {
    var maxIndex = css.length - 1, appendIndex = 0, startIndex, endIndex, terminator, foundTerminator, sb = [], m,
        preserver, token, pattern = /url\(\s*(["']?)data\:/g;
    while ((m = pattern.exec(css)) !== null) {
        startIndex = m.index + 4;
        terminator = m[1];
        if (terminator.length === 0) {
            terminator = ")";
        }
        foundTerminator = false;
        endIndex = pattern.lastIndex - 1;
        while (foundTerminator === false && endIndex + 1 <= maxIndex) {
            endIndex = css.indexOf(terminator, endIndex + 1);
            if ((endIndex > 0) && (css.charAt(endIndex - 1) !== '\\')) {
                foundTerminator = true;
                if (")" != terminator) {
                    endIndex = css.indexOf(")", endIndex);
                }
            }
        }
        sb.push(css.substring(appendIndex, m.index));
        if (foundTerminator) {
            token = css.substring(startIndex, endIndex);
            token = token.replace(/\s+/g, "");
            preservedTokens.push(token);
            preserver = "url(___YUICSSMIN_PRESERVED_TOKEN_" + (preservedTokens.length - 1) + "___)";
            sb.push(preserver);
            appendIndex = endIndex + 1;
        } else {
            sb.push(css.substring(m.index, pattern.lastIndex));
            appendIndex = pattern.lastIndex;
        }
    }
    sb.push(css.substring(appendIndex));
    return sb.join("");
};
YAHOO.compressor._compressHexColors = function (css) {
    var pattern = /(\=\s*?["']?)?#([0-9a-f])([0-9a-f])([0-9a-f])([0-9a-f])([0-9a-f])([0-9a-f])(\}|[^0-9a-f{][^{]*?\})/gi,
        m, index = 0, isFilter, sb = [];
    while ((m = pattern.exec(css)) !== null) {
        sb.push(css.substring(index, m.index));
        isFilter = m[1];
        if (isFilter) {
            sb.push(m[1] + "#" + (m[2] + m[3] + m[4] + m[5] + m[6] + m[7]));
        } else {
            if (m[2].toLowerCase() == m[3].toLowerCase() && m[4].toLowerCase() == m[5].toLowerCase() && m[6].toLowerCase() == m[7].toLowerCase()) {
                sb.push("#" + (m[3] + m[5] + m[7]).toLowerCase());
            } else {
                sb.push("#" + (m[2] + m[3] + m[4] + m[5] + m[6] + m[7]).toLowerCase());
            }
        }
        index = pattern.lastIndex = pattern.lastIndex - m[8].length;
    }
    sb.push(css.substring(index));
    return sb.join("");
};
YAHOO.compressor.cssmin = function (css, linebreakpos) {
    var startIndex = 0, endIndex = 0, i = 0, max = 0, preservedTokens = [], comments = [], token = '',
        totallen = css.length, placeholder = '';
    css = this._extractDataUrls(css, preservedTokens);
    while ((startIndex = css.indexOf("/*", startIndex)) >= 0) {
        endIndex = css.indexOf("*/", startIndex + 2);
        if (endIndex < 0) {
            endIndex = totallen;
        }
        token = css.slice(startIndex + 2, endIndex);
        comments.push(token);
        css = css.slice(0, startIndex + 2) + "___YUICSSMIN_PRESERVE_CANDIDATE_COMMENT_" + (comments.length - 1) + "___" + css.slice(endIndex);
        startIndex += 2;
    }
    css = css.replace(/("([^\\"]|\\.|\\)*")|('([^\\']|\\.|\\)*')/g, function (match) {
        var i, max, quote = match.substring(0, 1);
        match = match.slice(1, -1);
        if (match.indexOf("___YUICSSMIN_PRESERVE_CANDIDATE_COMMENT_") >= 0) {
            for (i = 0, max = comments.length; i < max; i = i + 1) {
                match = match.replace("___YUICSSMIN_PRESERVE_CANDIDATE_COMMENT_" + i + "___", comments[i]);
            }
        }
        match = match.replace(/progid:DXImageTransform\.Microsoft\.Alpha\(Opacity=/gi, "alpha(opacity=");
        preservedTokens.push(match);
        return quote + "___YUICSSMIN_PRESERVED_TOKEN_" + (preservedTokens.length - 1) + "___" + quote;
    });
    for (i = 0, max = comments.length; i < max; i = i + 1) {
        token = comments[i];
        placeholder = "___YUICSSMIN_PRESERVE_CANDIDATE_COMMENT_" + i + "___";
        if (token.charAt(0) === "!") {
            preservedTokens.push(token);
            css = css.replace(placeholder, "___YUICSSMIN_PRESERVED_TOKEN_" + (preservedTokens.length - 1) + "___");
            continue;
        }
        if (token.charAt(token.length - 1) === "\\") {
            preservedTokens.push("\\");
            css = css.replace(placeholder, "___YUICSSMIN_PRESERVED_TOKEN_" + (preservedTokens.length - 1) + "___");
            i = i + 1;
            preservedTokens.push("");
            css = css.replace("___YUICSSMIN_PRESERVE_CANDIDATE_COMMENT_" + i + "___", "___YUICSSMIN_PRESERVED_TOKEN_" + (preservedTokens.length - 1) + "___");
            continue;
        }
        if (token.length === 0) {
            startIndex = css.indexOf(placeholder);
            if (startIndex > 2) {
                if (css.charAt(startIndex - 3) === '>') {
                    preservedTokens.push("");
                    css = css.replace(placeholder, "___YUICSSMIN_PRESERVED_TOKEN_" + (preservedTokens.length - 1) + "___");
                }
            }
        }
        css = css.replace("/*" + placeholder + "*/", "");
    }
    css = css.replace(/\s+/g, " ");
    css = css.replace(/(^|\})(([^\{:])+:)+([^\{]*\{)/g, function (m) {
        return m.replace(":", "___YUICSSMIN_PSEUDOCLASSCOLON___");
    });
    css = css.replace(/\s+([!{};:>+\(\)\],])/g, '$1');
    css = css.replace(/___YUICSSMIN_PSEUDOCLASSCOLON___/g, ":");
    css = css.replace(/:first-(line|letter)(\{|,)/g, ":first-$1 $2");
    css = css.replace(/\*\/ /g, '*/');
    css = css.replace(/^(.*)(@charset "[^"]*";)/gi, '$2$1');
    css = css.replace(/^(\s*@charset [^;]+;\s*)+/gi, '$1');
    css = css.replace(/\band\(/gi, "and (");
    css = css.replace(/([!{}:;>+\(\[,])\s+/g, '$1');
    css = css.replace(/;+\}/g, "}");
    css = css.replace(/([\s:])(0)(px|em|%|in|cm|mm|pc|pt|ex)/gi, "$1$2");
    css = css.replace(/:0 0 0 0(;|\})/g, ":0$1");
    css = css.replace(/:0 0 0(;|\})/g, ":0$1");
    css = css.replace(/:0 0(;|\})/g, ":0$1");
    css = css.replace(/(background-position|transform-origin|webkit-transform-origin|moz-transform-origin|o-transform-origin|ms-transform-origin):0(;|\})/gi, function (all, prop, tail) {
        return prop.toLowerCase() + ":0 0" + tail;
    });
    css = css.replace(/(:|\s)0+\.(\d+)/g, "$1.$2");
    css = css.replace(/rgb\s*\(\s*([0-9,\s]+)\s*\)/gi, function () {
        var i, rgbcolors = arguments[1].split(',');
        for (i = 0; i < rgbcolors.length; i = i + 1) {
            rgbcolors[i] = parseInt(rgbcolors[i], 10).toString(16);
            if (rgbcolors[i].length === 1) {
                rgbcolors[i] = '0' + rgbcolors[i];
            }
        }
        return '#' + rgbcolors.join('');
    });
    css = this._compressHexColors(css);
    css = css.replace(/(border|border-top|border-right|border-bottom|border-right|outline|background):none(;|\})/gi, function (all, prop, tail) {
        return prop.toLowerCase() + ":0" + tail;
    });
    css = css.replace(/progid:DXImageTransform\.Microsoft\.Alpha\(Opacity=/gi, "alpha(opacity=");
    css = css.replace(/[^\};\{\/]+\{\}/g, "");
    if (linebreakpos >= 0) {
        startIndex = 0;
        i = 0;
        while (i < css.length) {
            i = i + 1;
            if (css[i - 1] === '}' && i - startIndex > linebreakpos) {
                css = css.slice(0, i) + '\n' + css.slice(i);
                startIndex = i;
            }
        }
    }
    css = css.replace(/;;+/g, ";");
    for (i = 0, max = preservedTokens.length; i < max; i = i + 1) {
        css = css.replace("___YUICSSMIN_PRESERVED_TOKEN_" + i + "___", preservedTokens[i]);
    }
    css = css.replace(/^\s+|\s+$/g, "");
    return css;
};
