var app = {
    article: {}
};

(function (P) {
    var _this = null;
    _this = P.article = {
        init : function () {
            _this.initEvent();
            _this.search();
        },
        initEvent: function () {
            $('#publish').on('click', function () {
                _this.publish();
            });

            $('#cannel').on('click', function () {
                alert("取消");
            });
            $('#save').on('click', function () {
            	alert("保存");
            });
        },
        /**
         * 发布
         */
        publish : function() {
        	var title = $("#title").val();
    		var desc = $("#desc").val();
    		var content = $("#content").val();
    		
    		var data = {
    			"title":title,
    			"content":content,
    			"desc":desc
    		};
    		
    		$.ajax({
    			type: "POST",
  			  	url: ctx + "/article/add",
  			  	data: data,
  			  	dataType: "json",
  			  	success: function(result) {
  			  		if(result.meta.code == 1000) {
  			  			alert("添加成功");
  			  		} else {
  			  			alert("添加失败");
  			  		}
  			  	}
			});
        }
    };
})(app);