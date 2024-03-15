AUI().ready(function () {
    showMenuHamburguer();
    window.addEventListener("resize", widthChangeCallback);
    widthChangeCallback();
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

//Funcion Hamburger Show and Hide Menu Navegación Mobile 
function showMenuHamburguer() {
  const btnNav = document.querySelector("#btnNav");
  const navMain = document.querySelector(".navigation--menu");
  const navigation = document.querySelector(".nav__menu#navigation");
  btnNav.addEventListener("click", (event) => {
    if (document.querySelector(".accordion")) {
      event.preventDefault();
      btnNav.classList.toggle("open");
      navMain.classList.toggle("show");
      if (navMain.classList.contains("show")) {
        document.body.style.overflow = "hidden";
        navMain.insertAdjacentElement("beforeEnd", navigation);
        document.querySelector(".navigation--menu .nav__menu").style.visibility =
          "visible";
        const visibleNav = Array.from(
          document.querySelectorAll(".nav__menu")
        ).filter(
          (s) => window.getComputedStyle(s).getPropertyValue("display") !== "none"
        )[0];
        const visibleNavLinks = Array.from(
          visibleNav.getElementsByTagName("li")
        ).filter(
          (s) => window.getComputedStyle(s).getPropertyValue("display") !== "none"
        );
        // if (visibleNavLinks.length > 0) {
        //   const firstLink = visibleNavLinks[0].querySelector("a");
        //   firstLink.focus();
        // }
      } else {
        document.querySelector(".navigation--menu .nav__menu").style.visibility =
          "hidden";
        document.body.removeAttribute("style");
      }
    }   
  });

  document.querySelectorAll('.menu--list .menu--item').forEach((element) => {
    if (element.childNodes[3]) {
      let childrenMenu = element.children[1].querySelectorAll('.submenu--item');
      childrenMenu.forEach(el => {
        el.querySelector('.sub-link').addEventListener('click', () => {
          document.querySelector('body').removeAttribute('style');
        });
      });
    }
  });
}
function widthChangeCallback() {
  const navigation = document.querySelector(".nav__menu#navigation");
  if (window.innerWidth > 768) {
    document
      .querySelector(".navigation .navigation--column-1")
      .insertAdjacentElement("beforeEnd", navigation);
  }
  if (window.innerWidth < 992) {
    document.querySelector(".nav__menu").classList.remove("nav__accessible");
    document.querySelector(".navigation--menu").insertAdjacentElement("beforeEnd", navigation);
    document.querySelector(".nav__menu .menu--list").classList.add("accordion");
    if (document.querySelector(".nav__menu .menu--list").classList.contains('accordion')) {      
      accordionToggle('accordion');
    }
  } else {
    document.querySelector(".nav__menu").classList.add("nav__accessible");
    document.querySelector(".nav__menu").classList.remove("accordion");
    document.querySelector(".navigation--menu").classList.remove("show");
    document.querySelector(".navigation--column-3 .btn--menu").classList.remove("open");
    document.querySelector(".nav__menu").removeAttribute("style");
    document.querySelector(".nav__menu .menu--list").classList.remove("accordion");
    if (!document.querySelector(".nav__menu .menu--list").classList.contains('accordion')) {
      document.querySelectorAll(".menu--list .has__child").forEach((element) => {
        element.querySelector('.link').classList.remove('accordion__tab');
        element.querySelector('.submenu--list').classList.remove('accordion__panel');
        element.querySelector('.link').removeAttribute('id');
        element.querySelector('.link').removeAttribute('aria-controls');
        element.querySelector('.submenu--list').removeAttribute('id');
        element.querySelector('.submenu--list').removeAttribute('aria-labelledby');
        element.querySelector('.submenu--list').removeAttribute('style');
        element.querySelector('.submenu--list').removeAttribute('data-height');
      });
    }
  }
}
function accordionToggle(element) {
  if (document.querySelectorAll(element)) {
    const randomId = function (length = 6) {
      return Math.random().toString(36).substring(2, length + 2);
    };
    let components = document.getElementsByClassName(element);
    if (components) {
      let component;
      for (let a = components.length - 1; a >= 0; a--) {
        component = components[a];
        let tabs = component.getElementsByClassName("accordion__tab");
        let tab;
        for (let b = tabs.length - 1; b >= 0; b--) {
          tab = tabs[b];
          tab.id = "accordionTab" + randomId();
          // tab.setAttribute("aria-expanded", false);
          let button = tab;
          button.addEventListener("click", toggle);
          let panel = tab.nextElementSibling;
          if (panel) {
            panel.id = "accordionPanel" + randomId();
            panel.dataset.height = getHeight(tab, panel);
            // -- Set Initial ARIA
            tab.setAttribute("aria-controls", panel.id);
            // tab.setAttribute("aria-expanded", false);
            panel.setAttribute("aria-labelledby", tab.id);
          }
        }
      }
    }
  }
}

Liferay.Portlet.ready(function (_portletId, _node) {});

Liferay.on('allPortletsReady', function () {});
