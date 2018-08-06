var li = $("#ul_box li");
var num=0;
var len=li.length;
// var iCount=setInterval(function(){
//     switchImg();
// },3000);
function switchImg() {
    $("#span_box span").css("background-color","#2dbb55");
    $("#span_box span").eq(num).css("background-color","#fff")
    li.css("display","none");
    num=++num==len?0:num;
    li[num].style.display="inline-block";
}
$("#span_box span").mouseenter(function () {
    num = $(this).attr("id").split("_")[1];
    switchImg();
    //clearInterval(iCount);
})
// $("#span_box span").mouseleave(function () {
//     iCount=setInterval(function(){
//         switchImg();
//     },3000);
// })
