

$(document).ready(function(){
    for(var i =0; i<$(".hidden_data_tag").length;i++){
        var tag = $(".hidden_data_tag")[i].innerText;
        if(tag=="헬스"){
            console.log($(".hidden_data_tag")[i].closest("div"));
            $(".icon_border_pink").attr('class','icon_boarder_blue');
            console.log($(".hidden_data_tag")[i].parent("div"));
            console.log(document.getElementsByClassName("icon_boarder_pink"));
            // div.attr('class','icon_boarder_blue')


        }
    }
});