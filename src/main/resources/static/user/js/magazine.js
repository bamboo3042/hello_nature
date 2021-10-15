/* 레시피 미리보기 */
let recPrePrev = document.querySelector('#rec_pre_btn_prev');
let recPreNext = document.querySelector('#rec_pre_btn_next');
let recPreBox = document.querySelector('#rec_pre_box');
let recPre = document.querySelectorAll('#rec_pre');
let recPreCnt = recPre.length;
let recPreIdx = 0;
let recPreWidth = 330;
let recPreMargin = 30;

recPreBox.style.width = (recPreWidth + recPreMargin) * recPreCnt - recPreMargin + 'px';

function moveRecPre(num) {
    recPreBox.style.left = -num * (recPreWidth + recPreMargin) + 'px';
    recPreIdx = num;
}
recPreNext.addEventListener('click', function() {
    if (recPreIdx < recPreCnt - 3) {
        moveRecPre(recPreIdx + 1);
        console.log(recPreIdx);
    } else {
        moveRecPre(0);
    }
});
recPrePrev.addEventListener('click', function() {
    if (recPreIdx > 0) {
        moveRecPre(recPreIdx - 1);
        console.log(recPreIdx);
    } else {
        moveRecPre(recPreCnt - 3);
    }
});


/* 탐험노트 미리보기 */
let advPrePrev = document.querySelector('#adv_pre_btn_prev');
let advPreNext = document.querySelector('#adv_pre_btn_next');
let advPreBox = document.querySelector('#adv_pre_box');
let advPre = document.querySelectorAll('#adv_pre');
let advPreCnt = advPre.length;
let advPreIdx = 0;
let advPreWidth = 330;
let advPreMargin = 30;

advPreBox.style.width = (advPreWidth + advPreMargin) * advPreCnt - advPreMargin + 'px';

function moveAdvPre(num) {
    advPreBox.style.left = -num * (advPreWidth + advPreMargin) + 'px';
    advPreIdx = num;
}
advPreNext.addEventListener('click', function() {
    if (advPreIdx < advPreCnt - 3) {
        moveAdvPre(advPreIdx + 1);
        console.log(advPreIdx);
    } else {
        moveAdvPre(0);
    }
});
advPrePrev.addEventListener('click', function() {
    if (advPreIdx > 0) {
        moveAdvPre(advPreIdx - 1);
        console.log(advPreIdx);
    } else {
        moveAdvPre(advPreCnt - 3);
    }
});


/* 라이프스타일 미리보기 */
let lifePrePrev = document.querySelector('#life_pre_btn_prev');
let lifePreNext = document.querySelector('#life_pre_btn_next');
let lifePreBox = document.querySelector('#life_pre_box');
let lifePre = document.querySelectorAll('#life_pre');
let lifePreCnt = lifePre.length;
let lifePreIdx = 0;
let lifePreWidth = 330;
let lifePreMargin = 30;

lifePreBox.style.width = (lifePreWidth + lifePreMargin) * lifePreCnt - lifePreMargin + 'px';

function moveLifePre(num) {
    lifePreBox.style.left = -num * (lifePreWidth + lifePreMargin) + 'px';
    lifePreIdx = num;
}
lifePreNext.addEventListener('click', function() {
    if (lifePreIdx < lifePreCnt - 3) {
        moveLifePre(lifePreIdx + 1);
        console.log(lifePreIdx);
    } else {
        moveLifePre(0);
    }
});
lifePrePrev.addEventListener('click', function() {
    if (lifePreIdx > 0) {
        moveLifePre(lifePreIdx - 1);
        console.log(lifePreIdx);
    } else {
        moveLifePre(lifePreCnt - 3);
    }
});

/* 키친가이드 미리보기 */
let kitPrePrev = document.querySelector('#kit_pre_btn_prev');
let kitPreNext = document.querySelector('#kit_pre_btn_next');
let kitPreBox = document.querySelector('#kit_pre_box');
let kitPre = document.querySelectorAll('#kit_pre');
let kitPreCnt = kitPre.length;
let kitPreIdx = 0;
let kitPreWidth = 330;
let kitPreMargin = 30;

kitPreBox.style.width = (kitPreWidth + kitPreMargin) * kitPreCnt - kitPreMargin + 'px';

function moveKitPre(num) {
    kitPreBox.style.left = -num * (kitPreWidth + kitPreMargin) + 'px';
    kitPreIdx = num;
}
kitPreNext.addEventListener('click', function() {
    if (kitPreIdx < kitPreCnt - 3) {
        moveKitPre(kitPreIdx + 1);
        console.log(kitPreIdx);
    } else {
        moveKitPre(0);
    }
});
kitPrePrev.addEventListener('click', function() {
    if (kitPreIdx > 0) {
        moveKitPre(kitPreIdx - 1);
        console.log(kitPreIdx);
    } else {
        moveKitPre(kitPreCnt - 3);
    }
});