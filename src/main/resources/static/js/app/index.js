
var main = {
    // 굳이 변수의 속성으로 function을 선언한 이유는 다른 js 파일에 같은 func 이름이 있으면
    // 브라우저 스코프는 공용공간이므로, 나중에 불러와진 func 으로 덮어쓰여진다.
    // 이를 막기 위해 var main 과 같이 변수에 function을 넣어 문제를 해결한다.
    init : function() {
        console.log('init function');
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
    },
    save : function(){
        var data = {
            title:$('#title').val(),
            author:$('#author').val(),
            content:$('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();