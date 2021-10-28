//그래프


window.onload = function () {

    let myChart = document.getElementById('myChart').getContext('2d');
    gradient = myChart.createLinearGradient(0, 0, 0, 450);

    gradient.addColorStop(0.5, '#e9fae9');
    gradient.addColorStop(0.35, '#BFFFC7');
    gradient.addColorStop(0.15, '#18A5A7');

    let linechart = new Chart(myChart, {
        type: 'line', // 차트의 형태
        data: {
            labels: [
                //x 축
                '1', '2', '3', '4', '5' , '6', '7'
            ],
            datasets: [
                { //데이터
                    fill: true,
                    data: [
                        '300', '550', '1020', '220', '260', '500', '600'
                    ],
                    backgroundColor: gradient,
                    borderColor: [
                        '#266266',
                    ],
                    borderWidth: 2.8,//경계선 굵기
                    pointBorderColor: '#266266',
                    pointBackgroundColor: '#266266'
                }
            ]
        },
        options: {
            maintainAspectRatio: false,
            title: {
                display: false
            },
            legend: {
                display: false
            },
            responsive: true,
            scales: {
                yAxes: [
                    {
                        ticks: {
                            beginAtZero: true,
                            max: 1200,
                        },
                        gridLines: {
                            display: true,
                            drawOnChartArea: false,
                        }
                    }
                ]
            }
        }
    });


    let myChart2 = document.getElementById('myChart2').getContext('2d');
    gradient2 = myChart2.createLinearGradient(0, 0, 0, 450);
    gradient2.addColorStop(0, '#04363a');
    gradient2.addColorStop(0.5, '#266266');
    gradient2.addColorStop(1, '#e9fae9');

    let hover_1 = document.getElementById("Am_c1_product")
    let hover_2 = document.getElementById("Am_c1_order")
    let hover_3 = document.getElementById("Am_c1_claim")
    let hover_4 = document.getElementById("Am_c1_delay")
    let hover_5 = document.getElementById("Am_c1_review")
    let hover_6 = document.getElementById("Am_c1_question")

    let label_1 = document.getElementById("label_1").innerText
    let label_2 = document.getElementById("label_2").innerText
    let label_3 = document.getElementById("label_3").innerText

    mybar = new Chart(myChart2, {
        type: 'bar', // 차트의 형태
        data: {
            labels: [
                //x 축
                '판매중', '판매중지', '품절'
            ],
            datasets: [
                { //데이터
                    fill: true,
                    fillColor: '#04363a',
                    borderDashOffset: 1.0,
                    data: ["11", "22", "33"],
                    backgroundColor: gradient2,
                    borderColor: [
                        '#e9fae9',
                    ],
                    borderWidth: 1,
                    pointBorderColor: '#266266',
                    pointBackgroundColor: '#266266'
                }
            ]
        },
        options: {
            responsive: false,
            tooltips: {
                enabled: false
            },
            animation: {
                duration: 1,
                onComplete: function () {
                    var chartInstance = this.chart,
                        ctx = chartInstance.ctx;
                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                    ctx.fillStyle = '';
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'bottom';

                    this.data.datasets.forEach(function (dataset, i) {
                        var meta = chartInstance.controller.getDatasetMeta(i);
                        meta.data.forEach(function (bar, index) {
                            var data = dataset.data[index];
                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                        });
                    });
                }
            },
            maintainAspectRatio: false,
            title: {
                display: false
            },
            legend: {
                display: false
            },
            responsive: true,
            scales: {
                yAxes: [
                    {
                        ticks: {
                            display: false,
                            beginAtZero: true,
                            max: 2000,
                        },
                        gridLines: {
                            display: true,
                            drawOnChartArea: false,
                        }
                    }
                ],
                xAxes: [{
                    barPercentage: 0.3,
                    gridLines: {
                        display: true,
                        drawOnChartArea: false,
                    }
                }]
            }
        }
    });
    setTimeout(function () {
        hover_1.addEventListener("click", function () {
            $(".chartjs-hidden-iframe").remove();
            mybar = new Chart(myChart2, {
                type: 'bar', // 차트의 형태
                data: {
                    labels: [
                        //x 축
                        '판매중', '판매중지', '품절'
                    ],
                    datasets: [
                        { //데이터
                            fill: true,
                            fillColor: '#04363a',
                            borderDashOffset: 1.0,
                            data: [$("#label_1").text(), $("#label_2").text(), $("#label_3").text()],
                            backgroundColor: gradient2,
                            borderColor: [
                                '#e9fae9',
                            ],
                            borderWidth: 1,
                            pointBorderColor: '#266266',
                            pointBackgroundColor: '#266266'
                        }
                    ]
                },
                options: {
                    responsive: false,
                    tooltips: {
                        enabled: false
                    },
                    animation: {
                        duration: 1,
                        onComplete: function () {
                            var chartInstance = this.chart,
                                ctx = chartInstance.ctx;
                            ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                            ctx.fillStyle = '';
                            ctx.textAlign = 'center';
                            ctx.textBaseline = 'bottom';

                            this.data.datasets.forEach(function (dataset, i) {
                                var meta = chartInstance.controller.getDatasetMeta(i);
                                meta.data.forEach(function (bar, index) {
                                    var data = dataset.data[index];
                                    ctx.fillText(data, bar._model.x, bar._model.y - 5);
                                });
                            });
                        }
                    },
                    maintainAspectRatio: false,
                    title: {
                        display: false
                    },
                    legend: {
                        display: false
                    },
                    responsive: true,
                    scales: {
                        yAxes: [
                            {
                                ticks: {
                                    display: false,
                                    beginAtZero: true,
                                    max: 2000,
                                },
                                gridLines: {
                                    display: true,
                                    drawOnChartArea: false,
                                }
                            }
                        ],
                        xAxes: [{
                            barPercentage: 0.3,
                            gridLines: {
                                display: true,
                                drawOnChartArea: false,
                            }
                        }]
                    }
                }
            });
        });
    }, 200);

    setTimeout(function () {
        hover_2.addEventListener("click", function () {
            setTimeout(function () {
                $(".chartjs-hidden-iframe").remove();
                let mybar2 = new Chart(myChart2, {
                    type: 'bar', // 차트의 형태
                    data: {
                        labels: [
                            //x 축
                            '신규입점', '입점중지'
                        ],
                        datasets: [
                            { //데이터
                                fill: true,
                                fillColor: '#04363a',
                                borderDashOffset: 1.0,
                                data: [$("#label_4").text(), $("#label_5").text()],
                                backgroundColor: gradient2,
                                borderColor: [
                                    '#e9fae9',
                                ],
                                borderWidth: 1,
                                pointBorderColor: '#266266',
                                pointBackgroundColor: '#266266'
                            }
                        ]
                    },
                    options: {
                        responsive: false,
                        tooltips: {
                            enabled: false
                        },
                        animation: {
                            duration: 1,
                            onComplete: function () {
                                var chartInstance = this.chart,
                                    ctx = chartInstance.ctx;
                                ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                                ctx.fillStyle = '';
                                ctx.textAlign = 'center';
                                ctx.textBaseline = 'bottom';

                                this.data.datasets.forEach(function (dataset, i) {
                                    var meta = chartInstance.controller.getDatasetMeta(i);
                                    meta.data.forEach(function (bar, index) {
                                        var data = dataset.data[index];
                                        ctx.fillText(data, bar._model.x, bar._model.y - 5);
                                    });
                                });
                            }
                        },
                        maintainAspectRatio: false,
                        title: {
                            display: false
                        },
                        legend: {
                            display: false
                        },
                        responsive: true,
                        scales: {
                            yAxes: [
                                {
                                    ticks: {
                                        display: false,
                                        beginAtZero: true,
                                        max: 100,
                                    },
                                    gridLines: {
                                        display: true,
                                        drawOnChartArea: false,
                                    }
                                }
                            ],
                            xAxes: [{
                                barPercentage: 0.3,
                                gridLines: {
                                    display: true,
                                    drawOnChartArea: false,
                                }
                            }]
                        }
                    }
                });
            }, 200);
        });

        setTimeout(function () {
            hover_3.addEventListener("click", function () {
                $(".chartjs-hidden-iframe").remove();
                let mybar3 = new Chart(myChart2, {
                    type: 'bar', // 차트의 형태
                    data: {
                        labels: [
                            //x 축
                            '미답변 문의', '미답변 후기'
                        ],
                        datasets: [
                            { //데이터
                                fill: true,
                                fillColor: '#04363a',
                                borderDashOffset: 1.0,
                                data: [$("#label_6").text(), $("#label_7").text(), $("#label_8").text()],
                                backgroundColor: gradient2,
                                borderColor: [
                                    '#e9fae9',
                                ],
                                borderWidth: 1,
                                pointBorderColor: '#266266',
                                pointBackgroundColor: '#266266'
                            }
                        ]
                    },
                    options: {
                        responsive: false,
                        tooltips: {
                            enabled: false
                        },
                        animation: {
                            duration: 1,
                            onComplete: function () {
                                var chartInstance = this.chart,
                                    ctx = chartInstance.ctx;
                                ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                                ctx.fillStyle = '';
                                ctx.textAlign = 'center';
                                ctx.textBaseline = 'bottom';

                                this.data.datasets.forEach(function (dataset, i) {
                                    var meta = chartInstance.controller.getDatasetMeta(i);
                                    meta.data.forEach(function (bar, index) {
                                        var data = dataset.data[index];
                                        ctx.fillText(data, bar._model.x, bar._model.y - 5);
                                    });
                                });
                            }
                        },
                        maintainAspectRatio: false,
                        title: {
                            display: false
                        },
                        legend: {
                            display: false
                        },
                        responsive: true,
                        scales: {
                            yAxes: [
                                {
                                    ticks: {
                                        display: false,
                                        beginAtZero: true,
                                        max: 100,
                                    },
                                    gridLines: {
                                        display: true,
                                        drawOnChartArea: false,
                                    }
                                }
                            ],
                            xAxes: [{
                                barPercentage: 0.3,
                                gridLines: {
                                    display: true,
                                    drawOnChartArea: false,
                                }
                            }]
                        }
                    }
                });
            }, 200);

            setTimeout(function () {
                hover_4.addEventListener("click", function () {
                    $(".chartjs-hidden-iframe").remove();
                    let mybar4 = new Chart(myChart2, {
                        type: 'bar', // 차트의 형태
                        data: {
                            labels: [
                                //x 축
                                '미답변 문의'
                            ],
                            datasets: [
                                { //데이터
                                    fill: true,
                                    fillColor: '#04363a',
                                    borderDashOffset: 1.0,
                                    data: [$("#label_9").text()],
                                    backgroundColor: gradient2,
                                    borderColor: [
                                        '#e9fae9',
                                    ],
                                    borderWidth: 1,
                                    pointBorderColor: '#266266',
                                    pointBackgroundColor: '#266266'
                                }
                            ]
                        },
                        options: {
                            responsive: false,
                            tooltips: {
                                enabled: false
                            },
                            animation: {
                                duration: 1,
                                onComplete: function () {
                                    var chartInstance = this.chart,
                                        ctx = chartInstance.ctx;
                                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                                    ctx.fillStyle = '';
                                    ctx.textAlign = 'center';
                                    ctx.textBaseline = 'bottom';

                                    this.data.datasets.forEach(function (dataset, i) {
                                        var meta = chartInstance.controller.getDatasetMeta(i);
                                        meta.data.forEach(function (bar, index) {
                                            var data = dataset.data[index];
                                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                                        });
                                    });
                                }
                            },
                            maintainAspectRatio: false,
                            title: {
                                display: false
                            },
                            legend: {
                                display: false
                            },
                            responsive: true,
                            scales: {
                                yAxes: [
                                    {
                                        ticks: {
                                            display: false,
                                            beginAtZero: true,
                                            max: 100,
                                        },
                                        gridLines: {
                                            display: true,
                                            drawOnChartArea: false,
                                        }
                                    }
                                ],
                                xAxes: [{
                                    barPercentage: 0.3,
                                    gridLines: {
                                        display: true,
                                        drawOnChartArea: false,
                                    }
                                }]
                            }
                        }
                    });
                });
            }, 200);

            setTimeout(function () {
                hover_5.addEventListener("click", function () {
                    $(".chartjs-hidden-iframe").remove();
                    let mybar5 = new Chart(myChart2, {
                        type: 'bar', // 차트의 형태
                        data: {
                            labels: [
                                //x 축
                                '신규매거진', '전시중지 매거진'
                            ],
                            datasets: [
                                { //데이터
                                    fill: true,
                                    fillColor: '#04363a',
                                    borderDashOffset: 1.0,
                                    data: [$("#label_9").text(), $("#label_10").text()],
                                    backgroundColor: gradient2,
                                    borderColor: [
                                        '#e9fae9',
                                    ],
                                    borderWidth: 1,
                                    pointBorderColor: '#266266',
                                    pointBackgroundColor: '#266266'
                                }
                            ]
                        },
                        options: {
                            responsive: false,
                            tooltips: {
                                enabled: false
                            },
                            animation: {
                                duration: 1,
                                onComplete: function () {
                                    var chartInstance = this.chart,
                                        ctx = chartInstance.ctx;
                                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                                    ctx.fillStyle = '';
                                    ctx.textAlign = 'center';
                                    ctx.textBaseline = 'bottom';

                                    this.data.datasets.forEach(function (dataset, i) {
                                        var meta = chartInstance.controller.getDatasetMeta(i);
                                        meta.data.forEach(function (bar, index) {
                                            var data = dataset.data[index];
                                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                                        });
                                    });
                                }
                            },
                            maintainAspectRatio: false,
                            title: {
                                display: false
                            },
                            legend: {
                                display: false
                            },
                            responsive: true,
                            scales: {
                                yAxes: [
                                    {
                                        ticks: {
                                            display: false,
                                            beginAtZero: true,
                                            max: 200,
                                        },
                                        gridLines: {
                                            display: true,
                                            drawOnChartArea: false,
                                        }
                                    }
                                ],
                                xAxes: [{
                                    barPercentage: 0.3,
                                    gridLines: {
                                        display: true,
                                        drawOnChartArea: false,
                                    }
                                }]
                            }
                        }
                    });
                });
            }, 200);

            setTimeout(function () {
                hover_6.addEventListener("click", function () {
                    $(".chartjs-hidden-iframe").remove();
                    let mybar6 = new Chart(myChart2, {
                        type: 'bar', // 차트의 형태
                        data: {
                            labels: [
                                //x 축
                                '신규주문', '입금완료', '배송완료', '취소,환불,반품'
                            ],
                            datasets: [
                                { //데이터
                                    fill: true,
                                    fillColor: '#04363a',
                                    borderDashOffset: 1.0,
                                    data: [$("#label_11").text(), $("#label_12").text(), $("#label_13").text(), $("#label_14").text()],
                                    backgroundColor: gradient2,
                                    borderColor: [
                                        '#e9fae9',
                                    ],
                                    borderWidth: 1,
                                    pointBorderColor: '#266266',
                                    pointBackgroundColor: '#266266'
                                }
                            ]
                        },
                        options: {
                            responsive: false,
                            tooltips: {
                                enabled: false
                            },
                            animation: {
                                duration: 1,
                                onComplete: function () {
                                    var chartInstance = this.chart,
                                        ctx = chartInstance.ctx;
                                    ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                                    ctx.fillStyle = '';
                                    ctx.textAlign = 'center';
                                    ctx.textBaseline = 'bottom';

                                    this.data.datasets.forEach(function (dataset, i) {
                                        var meta = chartInstance.controller.getDatasetMeta(i);
                                        meta.data.forEach(function (bar, index) {
                                            var data = dataset.data[index];
                                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                                        });
                                    });
                                }
                            },
                            maintainAspectRatio: false,
                            title: {
                                display: false
                            },
                            legend: {
                                display: false
                            },
                            responsive: true,
                            scales: {
                                yAxes: [
                                    {
                                        ticks: {
                                            display: false,
                                            beginAtZero: true,
                                            max: 2000,
                                        },
                                        gridLines: {
                                            display: true,
                                            drawOnChartArea: false,
                                        }
                                    }
                                ],
                                xAxes: [{
                                    barPercentage: 0.3,
                                    gridLines: {
                                        display: true,
                                        drawOnChartArea: false,
                                    }
                                }]
                            }
                        }
                    });
                })
            });
        }, 200);
    })

//클릭시 색깔 변경
    $(function () {
        $("#Am_c2_chart_1").click(function (event) {
            if ($(this).hasClass("strong")) {
                $(this).add("#Am_c2_chart_1").removeClass("strong");
            } else {
                $(this).add("#Am_c2_chart_1").addClass("strong")
                $(".Am_c2 > ul > li > a").not($(this)).removeClass("strong")
                event.stopPropagation();
            }
        })
    })

    $(function () {
        $("#Am_c2_chart_2").click(function (event) {
            if ($(this).hasClass("strong")) {
                $(this).add("#Am_c2_chart_2").removeClass("strong");
            } else {
                $(this).add("#Am_c2_chart_2").addClass("strong")
                $(".Am_c2 > ul > li > a").not($(this)).removeClass("strong")
                event.stopPropagation();
            }
        })
    })

    $(function () {
        $("#Am_c2_chart_3").click(function (event) {
            if ($(this).hasClass("strong")) {
                $(this).add("#Am_c2_chart_3").removeClass("strong");
            } else {
                $(this).add("#Am_c2_chart_3").addClass("strong")
                $(".Am_c2 > ul > li > a").not($(this)).removeClass("strong")
                event.stopPropagation();
            }
        })
    })
}
///////////////////////////////////////////////////////////////////////////////
// $(function(){
//     $(".click_cate").click(function(event){
//         if($(this).hasClass("on")){
//             $(this).add(".click_cate").removeClass("on");
//         }else{
//             $(this).add(".click_cate").addClass("on")
//             $(".click_cate").not($(this)).removeClass("on")
//             event.stopPropagation();
//         }
//     })
// })