var app = {
    moment: {}
};

(function (P) {
    var _this = null;
    _this = P.moment = {
        param: {
        	uid : 1,
            start: 1,
            limit: 20
        },
        
        init: function () {
            _this.initEvent();
            _this.search();
        },
        
        initEvent: function () {
            $('#prev').on('click', function () {
                _this.prevPage();
            });

            $('#next').on('click', function () {
                _this.nextPage();
            });
        },

        render: function (result) {
            var list = result.data;
            var html = "";
            for (var i = 0; i < list.length; i++) {
                var obj = list[i];
                html += "<div class=\"element\">";
                html += _this.convertList(obj);
                html += "</div>";
                html += "<hr style=\"height: 5px;width: 100%;\"/>";
            }
            $("#content").html(html);
        },


        search: function (uid) {
            var params = '?uid=1&start=1&limit=20';
            $.ajax({
                type: 'get',
                contentType: 'application/json',
                url: '/moment/list'+params,
                dataType: "json",
                success: function (result) {
                    if (result.meta.code == 1) {
                        _this.render(result)
                    } else {
                        alert(result.meta.msg);
                    }
                }
            });
        },

        prevPage: function () {
        },

        nextPage: function () {
        },

        showPage: function () {
        },

        convertList: function (obj) {
        	var id = obj['id']
            var title = obj['title'];
            var type = obj['type'];
            var content = obj['content'];
            var uid = obj['uid'];
            var uname = obj['uname'];
            var headImage = obj['headImage'];
            var publishTime = obj['publishTime'];
            
            if (1 == type) {
                type = "此刻";
            }
            var element = '';
            element += '<div">'+ id + '</div>';
            element += '<div">'+ type + '</div>';
            element += '<div">'+ content + '</div>';
            element += '<div">'+ uid + '</div>';
            element += '<div">'+ headImage + '</div>';
            element += '<div">'+ uname + '</div>';
            element += '<div">'+ publishTime + '</div>';
            
            return element;
        },
    };
})(app);