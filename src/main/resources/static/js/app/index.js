
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
        $('#btn-update').on('click', function(){
            _this.update();
        });
        $('#btn-delete').on('click', function(){
            _this.delete();
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
            window.location.href = '/'; // redirection
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update : function(){
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href = '/'; // redirection
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },
    delete : function(){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href = '/'; // redirection
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();