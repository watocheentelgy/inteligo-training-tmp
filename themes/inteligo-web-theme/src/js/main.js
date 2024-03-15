AUI().ready(function () {
    swiperLoops();
});
function swiperLoops() {
    if (document.querySelector(".testimonials-tpl-swiper")) {
      swiperTestimonials = new Swiper(".testimonials-tpl-swiper .mySwiper", {
        navigation: {
          nextEl: ".testimonials-tpl-swiper .swiper .swiper-button-next",
          prevEl: ".testimonials-tpl-swiper .swiper .swiper-button-prev",
        }
      });
    }
}





Liferay.Portlet.ready(function (_portletId, _node) {});

/*
 * This function gets loaded when everything, including the portlets, is on
 * the page.
 */
Liferay.on('allPortletsReady', function () {});
