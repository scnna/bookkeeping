/**
 * Created by limeng on 2017/7/12.
 */
var RCDLayerUtil = (function ($) {
    var rcdLayer = layer;

    var generateNativeOption = function (option) {
        var nativeOption = {};
        if (!option.type) {
            nativeOption.type = 1;
        } else {
            nativeOption.type = option.type;
        }

        if (option.maxmin === false) {
            nativeOption.maxmin = false;
        } else {
            nativeOption.maxmin = true;
        }

        if (option.area) {
            nativeOption.area = option.area;
        } else {
            nativeOption.area = ['auto'];
        }
        if (option.offset) {
            nativeOption.offset = option.offset;
        }
        if (option.end) {
            nativeOption.end = option.end;
        }
        nativeOption.title = option.title;

        if (nativeOption.type === 1) {
            nativeOption.content = $(option.content);
        } else {
            nativeOption.content = option.content;
        }

        return nativeOption;
    };

    return {
        /**
         * 打开一个Layer窗口
         * @param option
         * {
         *
         * }
         */
        open: function (option) {
            var index = rcdLayer.open(generateNativeOption(option));
            if (option.fullScreen !== false) {
                rcdLayer.full(index);
            }
            return index;
        },

        close: function (index) {
            rcdLayer.close(index);
        }
    }
}(jQuery));