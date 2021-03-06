var app = {
    feed: {}
};

(function (P) {
    var _this = null;
    _this = P.feed.list = {
        param: {
            skip: 0,
            limit: 20,
            currentPage: 1,
            hasmore: 0,
            changed: 0, //0 stands for no,1 stands for yes,  sequence changed or not
            uid: 0,

        },
        init: function () {
            _this.initEvent();
        },
        initEvent: function () {
            $('#search').on('click', function () {
                var sequence = $('#sequence').val();
                if (parseInt(sequence) > 0) {
                    console.log(parseInt(sequence));
                    _this.search(parseInt(sequence));
                }

            });

            $('#sequence').on('change', function () {
                _this.param.uid = 0;
                _this.param.skip = 0;
                _this.param.limit = 20;
                _this.param.currentPage = 1;
                console.log('changed');
            });

            $('#prev').on('click', function () {
                _this.prevPage();
            });

            $('#next').on('click', function () {
                _this.nextPage();
            });


        },

        initSearch: function () {
            _this.param.skip = 0;
            _this.param.limit = 20;
        },

        search: function (val) {
            if (_this.param.uid == 0) {
                console.log('search');
                $.ajax({
                    type: 'GET',
                    url: '/admin/userInfo?sequence=' + val,
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.success == 1) {
                            _this.param.uid = data.uid;
                            _this.searchData(data.uid);
                        } else {
                            alert(data.msg);
                            return;
                        }
                    }
                });
            } else {
                _this.searchData(_this.param.uid);
            }

        },

        render: function (data) {
            var hasmore = data.hasmore;//if there is any more feed after this request 1stands for yes

            var list = data.data;

            var html = "";

            for (var i = 0; i < list.length; i++) {
                var obj = list[i];
                html += "<div class=\"element\">";
                html += _this.convertTitle(obj);
                html += _this.convertDetail(obj);
                html += "</div>";
                html += "<hr style=\"height: 5px;width: 100%;\"/>";
            }
            $("#content").html(html);
            _this.showPage();
            _this.param.hasmore = hasmore;

        },


        searchData: function (uid) {
            console.log('searchData');
            var postData = {
                "uid": uid,
                "limit": _this.param.limit,
                "skip": _this.param.skip,
                "latlng": "0,0",
                "apptoken": "dmaitoken01",
                "client_info": {
                    "channel": "9|demaiwebsite",
                    "type": "android",
                    "app": 11,
                    "version": "4.8",
                    "device_id": "7A0C057F-0944-4EA9-B193-D1ACB439F607",
                    "network": "2g",
                    "isp": "中国移动",
                    "ip": "119.57.35.166",
                    "ssid": ""
                }
            };
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '/feed/allFeed',
                dataType: "json",
                data: JSON.stringify(postData),
                success: function (data) {
                    if (data.success == 1) {
                        _this.render(data)
                    } else {
                        alert(data.msg.message);
                    }
                }
            });
        },

        convertPage: function () {
            if (_this.param.hasmore == 1) {//如果有更多

            }
        },


        prevPage: function () {
            if (_this.param.skip == 0) {
                return;
            } else {
                _this.param.skip = _this.param.skip - _this.param.limit;
                _this.param.currentPage = _this.param.currentPage - 1;
                _this.search($('#sequence').val());
            }

        },

        nextPage: function () {
            if (_this.param.hasmore == 1) {
                _this.param.skip = _this.param.skip + _this.param.limit;
                _this.param.currentPage = _this.param.currentPage + 1;
                _this.search($('#sequence').val());
            } else {
                return;
            }

        },

        showPage: function () {
            $('#go_pg_num').html(_this.param.currentPage);
        },

        convertTitle: function (obj) {
            var title = obj["title"];
            var type = obj["type"];
            var name = obj["name"];
            var uid = obj ["uid"];
            if (20001 == type) {
                type = "动态";
            } else if (20002 == type) {
                type = "活动";
            } else if (20003 == type) {
                type = "转发";
            }

            return "<div class=\"title\">" + title + "<div class=\"time\">" + name + "[" + uid + "]   " + type + "</div></div>";
        },

        convertDetail: function (obj) {
            var type = obj["type"];
            console.log(type);
            var html = "";
            if (type == 20002) {
                var picHtml = "<div class=\"img\">";
                var pic = obj["pic"];
                console.log(pic);
                if ("" != pic) {
                    picHtml += "<img src=" + pic + " />";
                }
                picHtml += "</div>";
                html += picHtml;
            } else {
                var content = obj["content"];
                var picHtml = "<div class=\"img\">";
                var hasPic = false;
                var hasText = false;
                var textHtml = "<div class=\"detail\">";
                for (var i in content) {
                    var con = content[i];
                    if ("pic" == con["type"]) {
                        hasPic = true;
                        var url = con["value"];

                        picHtml += "<img src=" + url + " />";
                    } else {
                        hasText = true;
                        textHtml += con["value"];
                    }
                }
                if (hasText) {
                    textHtml += "</div>";
                    html += textHtml;
                }
                if (hasPic) {
                    picHtml += "</div>";
                    html += picHtml;
                }
            }


            return html;
        }
        ,


    };
})(app);