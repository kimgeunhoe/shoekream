const pno = document.getElementById('pno').innerText;

document.querySelector('.sizeBtn').addEventListener('click', () => {
    // if(document.getElementById('modal').innerText==0) {
    if(document.querySelector('.select_list').innerText==0) {
        buyPrice(pno).then(result => {
            spreadModal(result);
        });
    }
    document.querySelector('.layer_detail_size_select').style.display = "block";
});

function spreadModal(result) {
    let html = '';
    // '<div data-v-1f7c6d3f="" class="layer_header"><h2 data-v-1f7c6d3f="" class="title"><span data-v-1f7c6d3f="">사이즈</span></h2></div><div data-v-1f7c6d3f="" class="layer_content">';
    // html += '<div data-v-02c63ee6="" class="select_area lg" showlayer="true" optiontitle="사이즈" data-v-1f7c6d3f=""><ul data-v-02c63ee6="" class="select_list">';
    html += '<li data-v-02c63ee6="" class="select_item active" data-size="0"><button data-v-02c63ee6="" role="button" aria-selected="" class="select_link buyPrice">';
    html += '<div data-v-02c63ee6="" class="link_inner"><span data-v-02c63ee6="" class="size"> 모든 사이즈 </span>';
    html += `<span data-v-02c63ee6="" class="price">${document.getElementById('buyPrice').innerText }</span>원</div></button></li>`;
    for (const ivo of result) {
        html += `<li data-v-02c63ee6="" class="select_item" data-size="${ivo.sizeVO.sizeId }"><button data-v-02c63ee6="" role="button" aria-selected="false" class="select_link buy">`;
        html += `<div data-v-02c63ee6="" class="link_inner"><span data-v-02c63ee6="" class="size">${ivo.sizeVO.sizeValue }</span>`;
        html += `<span data-v-02c63ee6="" class="price">${ivo.price!=null ? ivo.price : "구매입찰"}</span></div></button></li>`;
    }
	// html += '</ul></div></div><button data-v-1f7c6d3f="" class="btn_layer_close close"><i class="fa fa-close"></i></button>';							

	document.querySelector('.select_list').innerHTML = html;
	// document.getElementById('modal').innerHTML = html;
}

// document.querySelector('.close').addEventListener('click', () => {
//     document.querySelector('.layer_detail_size_select').style.display = "none";
// });

document.addEventListener('click', (e) => {
    if(e.target.classList.contains('select_link') || e.target.classList.contains('size') || e.target.classList.contains('price')){
        e.target.closest('ul').querySelector('.active').classList.remove('active');
        e.target.closest('li').classList.add('active');
        document.querySelector('.sizeValue').innerText = e.target.closest('button').querySelector('.size').innerText;
        let buyPrice = e.target.closest('button').querySelector('.price').innerText;
        document.querySelector('.buy').querySelector('.num').innerText = buyPrice=='구매입찰' ? '-\u00a0': buyPrice;
        recentPriceSellPrice(pno, e.target.closest('li').dataset.size).then(result => {
            document.getElementById('recentPriceValue').innerText = result.recentPrice!=null ? result.recentPrice : '-\u00a0';
            document.querySelector('.sell').querySelector('.num').innerText = result.maxSellPrice!=null ? result.maxSellPrice : '-\u00a0';
        });
        document.querySelector('.layer_detail_size_select').style.display = "none";
    }
    if(e.target.classList.contains('fa-close')) {
        document.querySelector('.layer_detail_size_select').style.display = "none";
    }
});

async function buyPrice(pno) {
    try {
      const resp = await fetch('/items/' + pno);
      const rp = await resp.json();
      return await rp;
    } catch (error) {
      console.log(error);
    }
}

async function recentPriceSellPrice(pno, size) {
    try {
      const resp = await fetch('/items/' + pno + '/' + size);
      const rp = await resp.json();
      return await rp;
    } catch (error) {
      console.log(error);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    if(document.getElementById('email').innerText=='') {
        document.querySelectorAll('.btn_division').forEach(a => {
            a.href = '/member/login';
        });
    }
});