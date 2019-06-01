//JavaScript代码区域

window.reloadOrder = function(){
    var formObject1={};
    var formArray1=$("#searchForm").serializeArray();
    $.each(formArray1,function(index,entity){
        formObject1[entity.name]=entity.value;
    });
    orderTable.reload({
        page:{ curr:1 },
        where: formObject1
    })
}

function edit(orderId, productId, userId) {
    layer.open({
        type: 2,
        title: "编辑订单",
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['80%', '90%'],
        content: "/background/order/edit/" + userId + '/' + orderId + '/' + productId
    })
}

function process(orderId, productId, userId) {
    $.ajax({
        url: '/api/order/process',
        method: 'post',
        dataType: 'json',
        data: {'orderId': orderId, 'productId': productId, 'userId': userId},
        success: function(data) {
            if(data.code == 200) {
                layer.alert(data.msg, {icon: 1});
                setTimeout(function () {
                    window.parent.location.reload();
                }, 1000);
            }
        },
        error: function (e) {
            var d = e.responseJSON;
            if(d) {
                layer.msg(d, {icon: 5, time: 1000});
            }
        }
    });
}

function remove(orderId, productId, userId) {
    $.ajax({
        url: '/api/order/delete',
        method: 'get',
        dataType: 'json',
        data: {'orderId': orderId, 'productId': productId, 'userId': userId},
        success: function(data) {
            if(data.code == 200) {
                layer.alert(data.msg, {icon: 1});
                setTimeout(function () {
                    window.parent.location.reload();
                }, 1000);
            }
        },
        error: function (e) {
            var d = e.responseJSON;
            if(d) {
                layer.msg(d, {icon: 5, time: 1000});
            }
        }
    });
}