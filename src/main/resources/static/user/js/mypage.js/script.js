
/* section */
$(function(){
    $.ajax({
        type:"GET",
        url : "/user/section",
        datatype : "html/text",
        success: function(result){
            $(".contents").html(result).trigger("create");            
        }
    });
})
/* sidebar1 */
$(function(){
    $.ajax({
        type:"GET",
        url : "/user/sidebar1",
        datatype : "html/text",
        success: function(result){
            $(".main_leftaside1").html(result).trigger("create");            
        }
    });
})
/* sidebar2 */
$(function(){
    $.ajax({
        type:"GET",
        url : "/user/sidebar2",
        datatype : "html/text",
        success: function(result){
            $(".main_leftaside2").html(result).trigger("create");            
        }
    });
})
/* sidebar3 */
$(function(){
    $.ajax({
        type:"GET",
        url : "/user/sidebar3",
        datatype : "html/text",
        success: function(result){
            $(".main_leftaside3").html(result).trigger("create");
        }
    });
})
/* orderlist_box > orderlist1 */
$(function(){
    $(".contents_category li:nth-child(1)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/orderlist1",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_box").html(result).trigger("create");                
            }
        });
    })
})
/* orderlist_box > orderlist2 */
$(function(){
    $(".contents_category li:nth-child(2)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/orderlist2",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_box").html(result).trigger("create");                
            }
        });
    })
})
/* orderlist_box > orderlist3 */
$(function(){
    $(".contents_category li:nth-child(3)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/orderlist3",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_box").html(result).trigger("create");                
            }
        });
    })
})
/* orderlist_box > orderlist4 */
$(function(){
    $(".contents_category li:nth-child(4)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/orderlist4",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_box").html(result).trigger("create");                
            }
        });
    })
})
$(function(){
    /* ??????????????? ?????? ????????? html ????????? ???????????? 2/2 */
    $(".center_faq_paging .next_page > a").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/faq7_2",
            datatype : "html/text",
            success: function(result){
                $(".faq_contents").html(result).trigger("create");                
            }
        });
    })
    /* ??????????????? ?????? ????????? html ????????? ???????????? 1/2 */
    $(".center_faq_paging .prev_page > a").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/faq7_1",
            datatype : "html/text",
            success: function(result){
                $(".faq_contents").html(result).trigger("create");                
            }
        });
    })
    /* T??????????????? ?????? ????????? html ????????? */
    $(".faq_button8").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/faq8",
            datatype : "html/text",
            success: function(result){
                $(".faq_contents").html(result).trigger("create");                
            }
        });
    })
})
/* ???????????? - ??????????????? ?????? */
$(function(){
    $("#post_filter_available").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/available_review",
            datatype : "html/text",
            success: (result) => {
                $(".review_contents_box").html(result).trigger("create");                
            }
        });
    })
})


/* ???????????? ????????? ???????????? ????????? ?????? */
$(function(){
    $(".contents_category li").click(function(event){
        $(this).add(".contents_category li").addClass("orderlist_click")
        $(".contents_category > li").not($(this)).removeClass("orderlist_click")
        event.stopPropagation();
    })
})
/* 1:1?????? ?????? ????????? */
// $(function(){
//     $(".mantoman_list_box").on("click", () => {
//         $(".mantoman_contents_qna").toggle();
//     })
// })
/* faq ???????????? ?????? ????????? ????????? */
// $(function(){
//     $(".center_faq_move").on("click", () => {
//         $(".center_faq_box").toggle();
//     })
// })
/* ??????????????? ?????? / ????????? ?????? ???????????? */
$(function(){
    $(".contents_post_box > span").click(function(event){
        $(this).add(".contents_post_box > span").addClass("post_filter_available2")
        $(".contents_post_box > span").not($(this)).removeClass("post_filter_available2")
        event.stopPropagation();
    })
})
/* ?????? ?????? ?????? ??? ?????? ?????? ?????? */
$(function(){
    $(".edit_shipping_allow").on("click", () => {
        $(".enrollpay_allow_list").toggle();
    })
})
$(document).ready(function(){
    // ?????? ?????????
    $(".ask_detail_radio_1").click(function(){
        $(".select_detail_radio_1").show();
        $(".ask_detail_radio_2_msg").hide();
        $(".ask_detail_radio_3_msg").hide();
        $(".post_box").hide();
        $(".insert_location").hide();
        $(".post_box_background > div").hide();
        $(".ask_addr_detail > li:nth-child(4) > div").hide();
    });
    $("#chk_door").click(() => {
        $(".ask_addr_detail li div > div:nth-child(3)").hide();
    })
    // ????????? ?????????
    $(".ask_detail_radio_2").click(function(){
        $(".select_detail_radio_1").hide();
        $(".post_box").hide();
        $(".insert_location").hide();
        $(".ask_detail_radio_2_msg").show();
        $(".ask_detail_radio_3_msg").hide();
        $(".ask_addr_detail li div > div:nth-child(3)").hide();
        $(".post_box_background > div").hide();
        $(".ask_addr_detail > li:nth-child(4) > div").hide();
    });
// ????????? ?????????
    $(".ask_detail_radio_3").click(function(){
        $(".select_detail_radio_1").hide();
        $(".post_box").show();
        $(".insert_location").hide();
        $(".ask_detail_radio_2_msg").hide();
        $(".ask_detail_radio_3_msg").show();
        $(".ask_addr_detail li div > div:nth-child(3)").hide();
        $(".ask_addr_detail > li:nth-child(4) > div").hide();
    });
// ???????????? ?????????
    $(".ask_detail_radio_4").click(function(){
        $(".select_detail_radio_1").hide();
        $(".post_box").hide();
        $(".insert_location").show();
        $(".ask_detail_radio_2_msg").hide();
        $(".ask_detail_radio_3_msg").hide();
        $(".ask_addr_detail li div > div:nth-child(3)").hide();
        $(".post_box_background > div").hide();
    });
    $("#no_pwd_enter").click(function(){
        $(".insert_door_pwd").hide();
        $(".ask_addr_detail li div > div:nth-child(3)").hide();
        $(".post_box_background > div").hide();
        $(".ask_addr_detail > li:nth-child(4) > div").hide();
    });
    $("#entrance_pwd").click(function(){
        $(".insert_door_pwd").show();
        $(".ask_addr_detail li div > div:nth-child(3)").hide();
        $(".post_box_background > div").hide();
        $(".ask_addr_detail > li:nth-child(4) > div").hide();
    });
});

window.onload = () => {
    $(function(){
        const expAddrPopNameCheck = RegExp (/^[???-???]+$/);
        const expAddrPopHpCheck = RegExp (/^\d{3}-\d{3,4}-\d{4}$/);
        /* ???????????? */
        $("#addressName").keyup(() => {
            if($("#addressName").val() == ""){
                $("#addressName").focus();
                $(".shipping_form_list li:nth-child(1) > .caution_not_input").show();
                return false;
            } else if (!$("#addressName").val() == "") {
                $(".shipping_form_list li:nth-child(1) > .caution_not_input").hide();
            }
        })
        /* ?????? ?????? */
        $("#userName").keyup(() => {
            if ($("#userName").val() == "") {
                $('#userName').focus();
                $(".shipping_form_list li:nth-child(2) > .caution_not_input").show();
                return false;
            } else if (!$("#userName").val() == "") {
                $(".shipping_form_list li:nth-child(2) > .caution_not_input").hide();
            }
        })
        /* ???????????? */
        $("#userHp").keyup(() => {
            if ($("#userHp").val() == "") {
                $('#userHp').focus();
                $(".shipping_form_list li:nth-child(3) > .caution_not_input").show();
                return false;
            } else if (!$("#userHp").val() == "") {
                $(".shipping_form_list li:nth-child(3) > .caution_not_input").hide();
            }
        })
        /* ?????? */
        $("#sample6_address").keyup(() => {
            if ($("#sample6_address").val() == "") {
                $('#sample6_address').focus();
                $(".shipping_form_list li:nth-child(4) > div:nth-child(4)").show();
                return false;
            } else if (!$("#sample6_address").val() == "") {
                $(".shipping_form_list li:nth-child(4) > div:nth-child(4)").hide();
            }
        })
        /* ????????? ?????? */
        $("#sample6_detailAddress").keyup(() => {
            if ($("#sample6_detailAddress").val() == "") {
                $('#sample6_detailAddress').focus();
                $(".shipping_form_list li:nth-child(4) > div:nth-child(6)").show();
                return false;
            } else if (!$("#sample6_detailAddress").val() == "") {
                $(".shipping_form_list li:nth-child(4) > div:nth-child(6)").hide();
            }
        })
        /* ????????? ?????? */
        $(".insert_door_pwd").keyup(() => {
            if ($(".insert_door_pwd").val() == "") {
                $('.insert_door_pwd').focus();
                $(".ask_addr_detail li div > div:nth-child(3)").show();
                return false;
            } else if (!$(".insert_door_pwd").val() == "") {
                $(".ask_addr_detail li div > div:nth-child(3)").hide();
            }
        })
        /* ????????? ???????????? */
        $(".post_box").keyup(() => {
            if ($(".post_box").val() == "") {
                $('.post_box').focus();
                $(".post_box_background > div").show();
                return false;
            } else if (!$(".post_box").val() == "") {
                $(".post_box_background > div").hide();
            }
        })
        /* ???????????? */
        $(".insert_location").keyup(() => {
            if ($(".insert_location").val() == "") {
                $('.insert_location').focus();
                $(".ask_addr_detail > li:nth-child(4) > div").show();
                return false;
            } else if (!$(".insert_location").val() == "") {
                $(".ask_addr_detail > li:nth-child(4) > div").hide();
            }
        })
    });
    /* ???????????? ????????? ?????? ????????? (???????????????) */
    $(function(){
        $(".thegreen_checkbox").change(() => {
            if($(".thegreen_checkbox").is(":checked")){
                $(".enroll_hellopass_area2 > button:nth-child(2)").prop('disabled', false);
                $(".enroll_hellopass_area2 > button:nth-child(2)").css("background-color", "#5dab99");
                $(".enroll_hellopass_area2 > button:nth-child(2)").css( "color", "#fff");
            }else{
                $(".enroll_hellopass_area2 > button:nth-child(2)").prop('disabled', true);
                $(".enroll_hellopass_area2 > button:nth-child(2)").css("background-color", "#f6f6f6");
                $(".enroll_hellopass_area2 > button:nth-child(2)").css( "color", "#ddd");
            }
        })
    });
    $(function(){
        /* ??????????????? ???????????? ????????? ???????????? ????????? */
        $(".hellopass_link_payment").on("click", () => {
            $(".popup_payment_box").toggle();
        })
        $(".popup_close").on("click", () => {
            $(".popup_payment_box").css("display", "none");
        })
        /* ??????????????? ?????? ?????? ?????? ?????? ??? ?????? ?????? ????????? */
        $(".enrollpay_allow").on("click", () => {
            $(".enrollpay_allow_list2").toggle();
        })
    })
    /* ???????????? ???????????? css */
    $(function(){
        $(".choose_payment_box > div").click(function(event){
            $(this).add(".choose_payment_box > div").addClass("payment_box_clicked")
            $(".choose_payment_box > div").not($(this)).removeClass("payment_box_clicked")
            event.stopPropagation();
        })
    })
    /* ???????????? ???????????? ????????? */
    $(function(){
        $(".post_guide_box").on("click", () => {
            $(".post_guide_overview").toggle();
        })
    })
    /* ???????????? ?????? */
    $(() => {
        $("#productInqContents").on("click", () => {
            $("#productInqContents").focus();
            if($("#productInqContents").focus()){
                if($("#productInqContents").val() == ""){
                    $(".button_submit_productInq").prop("disabled", true);
                }else if($("#productInqContents").val().length > 0){
                    $(".button_submit_productInq").prop("disabled", false);
                }
                $(".button_submit_productInq").css("background", "#5dab99");
            }else{
                $(".button_submit_productInq").css("background", "#ccc")
            }
        })
    })
    /* ???????????? ???????????? ????????? ????????? */
    $(() => {
        $(".enroll_post_radiobox > li:nth-child(1)").click((e) => {
            $(this).add(".post_radio1_span").addClass("available_smile")
            $(this).add(".post_radio1_span").removeClass("post_radio1_span")
            $(".enroll_post_radiobox > li:nth-child(1)").not($(this)).removeClass("available_smile")
            e.stopPropagation();
        })
        $(".enroll_post_radiobox > li:nth-child(2)").click((e) => {
            if($("available_soso").hasClass("available_soso")){
                $(".enroll_post_radiobox > li:nth-child(2)").not($(this)).removeClass("available_soso")
                $(this).add(".available_soso").addClass(".post_radio2_span")
                $(this).add(".post_radio2_span").removeClass(".available_soso")
            }else{
                $(this).add(".post_radio2_span").addClass("available_soso")
                $(this).add(".post_radio2_span").removeClass("post_radio2_span")
            }
            e.stopPropagation();
        })
        $(".enroll_post_radiobox > li:nth-child(3)").click((e) => {
            $(this).add(".post_radio3_span").addClass("available_down")
            $(this).add(".post_radio3_span").removeClass("post_radio3_span")
            $(".enroll_post_radiobox > li:nth-child(3)").not($(this)).removeClass("available_down")
            e.stopPropagation();
        })
    })
    $(() => {
        $("#str_email01").keyup(() => {
            if($("#str_email01").val() == ""){
                $("#str_email01").focus();
                $(".email_caution").show();
                return false;
            }
        })
        $("#mantomanHp").keyup(() => {
            if($("#mantomanHp").val() == ""){
                $("#mantomanHp").focus();
                $(".hp_caution").show();
                return false;
            }
        })
        $("#mantomanCase").keyup(() => {
            if($("#mantomanCase").val() == ""){
                $("#mantomanCase").focus();
                $(".case_caution").show();
                return false;
            }
        })
        $("#mantomanTitle").keyup(() => {
            if($("#mantomanTitle").val() == ""){
                $("#mantomanTitle").focus();
                $(".title_caution").show();
                return false;
            }
        })
        $("#mantomanContents").keyup(() => {
            if($("#mantomanContents").val() == ""){
                $("#mantomanContents").focus();
                $(".contents_caution").show();
                return false;
            }
        })
    })
    $("#switch_label1").on("click", () => {
        $(".switch_label").addClass("slide_clicked")
    })
}

function checkMantoman(){
    const expHpCheck = RegExp (/^\d{3}-\d{3,4}-\d{4}$/);
    const expEmailInput = RegExp (/^[a-zA-Z0-9\.\-]+@[a-zA-Z0-9\.\-]+\.[a-zA-Z0-9]+$/);
    if (!expHpCheck.test($('#mantomanHp').val())) {
        alert('??????????????? ????????? ?????? ???????????????.');
        $('#mantomanHp').val('');
        $('#mantomanHp').focus();
        return false;
    }
}



/* ???????????? / ?????????????????? ?????? Ajax */
$(function(){
    $(".filter_outline div:nth-child(1)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/filter1",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter").html(result).trigger("create");
            }
        });
    })
    $(".filter_outline div:nth-child(2)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/filter2",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter").html(result).trigger("create");
            }
        });
    })
    $(".filter_outline div:nth-child(3)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/filter3",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter").html(result).trigger("create");
            }
        });
    })
    $(".filter_outline div:nth-child(4)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/filter4",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter").html(result).trigger("create");
            }
        });
    })
})

/* ?????? ?????? ?????? ?????? Ajax */
$(function(){
    $(".filter_outline2 div:nth-child(1)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/favorite_filter1",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter2").html(result).trigger("create");
            }
        });
    })
    $(".filter_outline2 div:nth-child(2)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/favorite_filter2",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter2").html(result).trigger("create");
            }
        });
    })
    $(".filter_outline2 div:nth-child(3)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/favorite_filter3",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter2").html(result).trigger("create");
            }
        });
    })
    $(".filter_outline2 div:nth-child(4)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/favorite_filter4",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter2").html(result).trigger("create");
            }
        });
    })
})


/* ????????? ?????? Ajax */
$(function(){
    $(".filter_outline3 div:nth-child(1)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/like_filter1",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter3").html(result).trigger("create");
            }
        });
    })
    $(".filter_outline3 div:nth-child(2)").click(function(){
        $.ajax({
            type:"GET",
            url : "/user/like_filter2",
            datatype : "html/text",
            success: function(result){
                $(".orderlist_filter3").html(result).trigger("create");
            }
        });
    })
})

/* ????????????/ ?????????????????? / ??????????????????/ ????????? ?????? ????????? css ?????? */
$(function(){
    $(".filter_outline > div").click(function(event){
        $(this).add(".filter_outline > div").addClass("filter_outline_on")
        $(".filter_outline > div").not($(this)).removeClass("filter_outline_on")
        event.stopPropagation();
    })
    $(".filter_outline2 > div").click(function(event){
        $(this).add(".filter_outline2 > div").addClass("filter_outline_on")
        $(".filter_outline2 > div").not($(this)).removeClass("filter_outline_on")
        event.stopPropagation();
    })
    $(".filter_outline3 > div").click(function(event){
        $(this).add(".filter_outline3 > div").addClass("filter_outline_on")
        $(".filter_outline3 > div").not($(this)).removeClass("filter_outline_on")
        event.stopPropagation();
    })
})







        



