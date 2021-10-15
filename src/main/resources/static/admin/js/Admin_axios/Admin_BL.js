$(function () {
    let page = $('.page').val()
    axios.get('/api/brand/list?startPage='+ page, {}).then(function (response) {
        let tb = $('<tbody>');
        for (let i in response.data.data) {
            let $name = response.data.data[i].name;
            let $proCount = response.data.data[i].proCount;
            let $dateStart = response.data.data[i].dateStart;
            let $dateEnd = response.data.data[i].dateEnd;
            let $state = response.data.data[i].state;
            if ($state == 1) {
                $state = '입점대기'
            } else if ($state == 2) {
                $state = '입점중'
            } else if ($state == 3) {
                $state = '입점중지'
            } else if ($state == 4) {
                $state = '입점종료'
            }else{
                $state = ""
            }

            let row = $('<tr>').append(
                '<td><input type="checkbox" value="cou_list" id="BL_check_' + i + '" name="brand" onclick="BL_checkSelectAll()"><label for="BL_check_' + i + '"></label></td>' +
                ' <td><a onclick="BL_location_3()">' + $name + '</a></td>' +
                '<td>' + $proCount + '</td>' +
                '<td>' + $dateStart + ' ~ ' + $dateEnd + '</td>' +
                '<td>' + $state + '</td>'
            )
            tb.append(row);
        }
        $('.thead').after(tb);
        for (let i = response.data.pagination.totalPages; i > 0; i--) {
            let pages = $('<li class="page_num" style="cursor: pointer" value="' + (i - 1) + '">').append(
                i + '</li>'
            )
            $('#prev').after(pages);
        }
        $('.page_num').click(function (e) {

            $('tbody').empty();

            $(".page").val($(this).val())
            let page = $('.page').val()
            axios.get('/api/brand/list?startPage='+page, {}).then(function (response) {
                let tb = $('<tbody>');
                for (let i in response.data.data) {
                    let $name = response.data.data[i].name;
                    let $proCount = response.data.data[i].proCount;
                    let $dateStart = response.data.data[i].dateStart;
                    let $dateEnd = response.data.data[i].dateEnd;
                    let $state = response.data.data[i].state;
                    if ($state == 1) {
                        $state = '입점대기'
                    } else if ($state == 2) {
                        $state = '입점중'
                    } else if ($state == 3) {
                        $state = '입점중지'
                    } else if ($state == 4) {
                        $state = '입점종료'
                    }else{
                        $state = ""
                    }
                    let row = $('<tr>').append(
                        '<td><input type="checkbox" value="cou_list" id="BL_check_' + i + '" name="brand" onclick="BL_checkSelectAll()"><label for="BL_check_' + i + '"></label></td>' +
                        ' <td><a onclick="BL_location_3()">' + $name + '</a></td>' +
                        '<td>' + $proCount + '</td>' +
                        '<td>' + $dateStart + ' ~ ' + $dateEnd + '</td>' +
                        '<td>' + $state + '</td>'
                    )
                    tb.append(row);
                }
                $('.thead').after(tb);
            })
        })

    }).catch(function (err) {
        console.log(err);
    })
})

$(function () {

    let now = new Date();
    let now_result = now.toISOString().substring(0, 10);

    //일주일, 일개월, 삼개월버튼
    document.getElementById("weekbtn").addEventListener("click", function () {
        let week = new Date();
        week.setDate(week.getDate() - 7);
        var week_result = week.toISOString().substring(0, 10);

        document.getElementById("date_start").value = week_result;
        document.getElementById("date_end").value = now_result;
    })

    document.getElementById("monthbtn").addEventListener("click", function () {

        let month = new Date();
        month.setMonth(month.getMonth() - 1);
        var month_result = month.toISOString().substring(0, 10);


        document.getElementById("date_start").value = month_result;
        document.getElementById("date_end").value = now_result;
    })

    document.getElementById("three_monthbtn").addEventListener("click", function () {

        let three_month = new Date();
        three_month.setMonth(three_month.getMonth() - 3);
        var three_month_result = three_month.toISOString().substring(0, 10);


        document.getElementById("date_start").value = three_month_result;
        document.getElementById("date_end").value = now_result;
    })

    $('#search').click(function (e) {
        let name = $('#name').val();
        let state = $('input:checkbox[name="enter_state"]:checked').val();

        if (state == '입점대기') {
            state = 1
        } else if (state == '입점중') {
            state = 2
        } else if (state == '입점중지') {
            state = 3
        } else if (state == '입점종료') {
            state = 4
        } else{
            state = ""
        }

        let dateStart = $('#date_start').val();
        let dateEnd = $('#date_end').val();
        let page = $('.page').val();
        let url = '/api/brand/list?name=' + name;

        if(name == ""){
            url = '/api/brand/list?startPage=' + page;
        }else if(name != ""){
            url = '/api/brand/list?startPage=' + page + '&name=' + name;
        }
        console.log(state);

        if(state == ""){
            url = url;
        }else if(state != ""){
            url = url + '&state=' + state;
        }

        if(dateStart == "" && dateEnd == ""){
            url = url;
        }else if(dateStart == "" && dateEnd != ""){
            url = url + '&dateEnd=' + dateEnd;
        }else if(dateStart != "" && dateEnd == ""){
            url = url + '&dateStart' + dateStart;
        }else if(dateStart != "" && dateEnd != ""){
            url = '/api/brand/list?startPage=' + page + '&name=' + name + '&dateStart=' + dateStart + '&dateEnd=' + dateEnd;
        }else if(dateStart != "" && dateEnd != ""){
            url = '/api/brand/list?startPage=' + page + '&dateStart=' + dateStart + '&dateEnd=' + dateEnd;
        }
        console.log(url);

        alert('눌럿다')
        $('tbody').empty();

        axios.get(url, {}).then(function (response) {

            let tb = $('<tbody>');
            for (let i in response.data.data) {
                let $name = response.data.data[i].name;
                let $proCount = response.data.data[i].proCount;
                let $dateStart = response.data.data[i].dateStart;
                let $dateEnd = response.data.data[i].dateEnd;
                let $state = response.data.data[i].state;
                if ($state == 1) {
                    $state = '입점대기'
                } else if ($state == 2) {
                    $state = '입점중'
                } else if ($state == 3) {
                    $state = '입점중지'
                } else if ($state == 4) {
                    $state = '입점종료'
                }else{
                    $state = ""
                }

                let row = $('<tr>').append(
                    '<td><input type="checkbox" value="cou_list" id="BL_check_' + i + '" name="brand" onclick="BL_checkSelectAll()"><label for="BL_check_' + i + '"></label></td>' +
                    ' <td><a onclick="BL_location_3()">' + $name + '</a></td>' +
                    '<td>' + $proCount + '</td>' +
                    '<td>' + $dateStart + ' - ' + $dateEnd + '</td>' +
                    '<td>' + $state + '</td>'
                )
                tb.append(row);
            }
            $('.thead').after(tb);
            $('.page_num').remove();
            for (let i = response.data.pagination.totalPages; i > 0; i--) {
                let pages = $('<li class="page_num" style="cursor:pointer" value="'+ (i - 1) +'">').append(
                    i + '</li>'
                )
                $('#prev').after(pages);
            }
            $('.page_num').click(function (e) {

                $('tbody').empty();

                $(".page").val($(this).val())
                let page = $('.page').val()
                let url = '/api/brand/list?startPage=' + page;
                // if(state == ""){
                //     url = '/api/brand/list?&name='+ name +'&dateStart='+ dateStart + '&dateEnd='+ dateEnd;
                //     console.log(url)


                axios.get('/api/brand/list?startPage='+ page + '&name=' + name, {}).then(function (response) {
                    let tb = $('<tbody>');
                    for (let i in response.data.data) {
                        let $name = response.data.data[i].name;
                        let $proCount = response.data.data[i].proCount;
                        let $dateStart = response.data.data[i].dateStart;
                        let $dateEnd = response.data.data[i].dateEnd;
                        let $state = response.data.data[i].state;
                        if ($state == 1) {
                            $state = '입점대기'
                        } else if ($state == 2) {
                            $state = '입점중'
                        } else if ($state == 3) {
                            $state = '입점중지'
                        } else if ($state == 4) {
                            $state = '입점종료'
                        }else{
                            state = ""
                        }

                        let row = $('<tr>').append(
                            '<td><input type="checkbox" value="cou_list" id="BL_check_' + i + '" name="brand" onclick="BL_checkSelectAll()"><label for="BL_check_' + i + '"></label></td>' +
                            ' <td><a onclick="BL_location_3()">' + $name + '</a></td>' +
                            '<td>' + $proCount + '</td>' +
                            '<td>' + $dateStart + ' ~ ' + $dateEnd + '</td>' +
                            '<td>' + $state + '</td>'
                        )
                        tb.append(row);
                    }
                    $('.thead').after(tb);
                })
            })

        }).catch(function (err) {
            console.log(err);
            alert("검색 결과가 없습니다.")
        })
    })
})