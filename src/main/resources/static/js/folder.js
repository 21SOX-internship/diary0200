


function savefolder() {
    var folder = [];
    $("input[name='checkfolder']:checked").each(function (i) {
        folder.push($(this).val());
    });
    $.ajax({
        type: "POST",
        url: "../makefolder.do",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "folderName": $(".set_folder_name").val(),
            "folder": "[" + folder.toString() + "]"
        },
        success: function (response) {
            if (response == 0) {
                alert("폴더가 생성되었습니다.");
                window.location.href = "/mypage/main";
            } else {
                alert("뭔가이상하다!");
            }

        },
        error: function (jqXHR, status, error) {
            alert("알 수 없는 에러 [" + error + "]")
        }
    });
}


function gofoloder(folderName){
    window.location.href = "/mypage/folder?folderName="+folderName;
}