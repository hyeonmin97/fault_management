var agencyX = agencyDto.point.x;
var agencyY = agencyDto.point.y;
//지도 생성
var agencyPosition = new naver.maps.LatLng(agencyY, agencyX);
var mapOptions = {
    center: agencyPosition,
    zoom: 13
};
var map = new naver.maps.Map('map', mapOptions);

//대리점 마커 생성
var agencyMarker = new naver.maps.Marker({
    position: agencyPosition,
    map: map,
    title: agencyDto.name,
    icon: {
        url: '/img/blue.png',
        size: new naver.maps.Size(20, 20),
        scaledSize: new naver.maps.Size(20, 20)
    }
})

//점포 마커 추가
var markers = []; //점포 마커들을 저장할 배열
var infoWindows = []; //마커의 정보창을 저장할 배열
for (var i in agencyDto.storeCoordinateList) {//i는 인덱스
    var store = agencyDto.storeCoordinateList[i];
    if (store.point) {
        //마커 생성
        var storePoint = new naver.maps.LatLng(store.point.y, store.point.x);
        var marker = new naver.maps.Marker({
            position: storePoint,
            map: map,
            icon: {
                url: '/img/red_circle.png',
                size: new naver.maps.Size(15, 15),
                scaledSize: new naver.maps.Size(15, 15)
            }
        });
        markers.push(marker);

        var infoWindowContent = getInfoWindowContent(store)

        //정보창
        var infoWindow = new naver.maps.InfoWindow({
            content: infoWindowContent,
            borderWidth: 2,

        })
        infoWindows.push(infoWindow);
    }
}

function getInfoWindowContent(store) {
    return [
        '<div style="text-align:center;padding:10px;font-size: 15px">',
        '   <h4>' + store.name + '</h4>',
        '   <p>',
        '      매장 전화번호 : ' + store.telephone + '<br>',
        '      매장 코드 : ' + store.storeCode + '<br>',
        '      매니저 이름 : ' + store.managerName + '<br>',
        '      매니저 전화번호 : ' + store.managerPhone + '<br>',
        '      매장 상태 : ' + store.storeAgencyStatus + '<br>',
        '   </p>',
        '</div>'
    ].join('');
}

function getClickHandler(seq) {
    return function (e) {
        var marker = markers[seq];
        var infoWindow = infoWindows[seq];
        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
            infoWindow.open(map, marker);
        }
    }
}

//리스너 등록
for (let i = 0; i < markers.length; i++) {
    naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
}

//차트 표시
const year = 2023;
const searchYear = 5;
let yearStrArr = [];

//year - 4 부터 저장
for (let i = 0; i < searchYear; i++) {
    yearStrArr[i] = year - (searchYear - 1 - i);
}


const engineerChart = document.getElementById('engineerChart');
const engineerChartData = {
    labels: [
        '재직',
        '퇴사'
    ],
    datasets: [{
        data: [agencyDto.incumbentCount, agencyDto.retireeCount],
    }]
};
new Chart(engineerChart, {
    type: 'pie',
    data: engineerChartData,
    options: {
        plugins: {
            title: {
                display: true,
                font: {
                    size: 20,
                    weight: 'bold'
                },
                position: 'top',
                align: 'center',
                text: "재직 현황"
            }
        }
    }

});

//입사자 수
const joinCountChart = document.getElementById('joinCountChart');
const joinCountData = {
    labels: yearStrArr,
    datasets: [{
        data: agencyDto.joinCountArray
    }]
};
new Chart(joinCountChart, {
    type: 'line',
    data: joinCountData,
    options: {
        plugins: {
            title: {
                display: true,
                fullSize: false,
                position: 'top',
                align: 'center',
                text: "입사자 현황",
                font: {
                    size: 20,
                    weight: 'bold'
                }
            },
            legend: {
                display: false
            }
        },
        scales: {
            y: {
                suggestedMin: 0,
            }
        }
    }
});

//퇴사자 수
const resignationCountChart = document.getElementById('resignationCountChart');
const resignationCountData = {
    labels: yearStrArr,
    datasets: [{
        data: agencyDto.resignationCountArray
    }]
};
new Chart(resignationCountChart, {
    type: 'line',
    data: resignationCountData,
    options: {
        plugins: {
            title: {
                display: true,
                position: 'top',
                align: 'center',
                text: "퇴사자 현황",
                font: {
                    size: 20,
                    weight: 'bold'
                }
            },
            legend: {
                display: false
            }
        },
        scales: {
            y: {
                suggestedMin: 0,
            }
        }
    }
});
