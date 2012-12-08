(function($) {

	//fixed col

	var scrollTofixed = function() {

		var el = $('.side-bar');
		if (el[0]) {
			var height = el.height(), elTop = el.offset().top, windowHeight = document.documentElement.clientHeight;

			return function() {
				var top = $(window).scrollTop();
				if (top > elTop && height < windowHeight) {
					if (el.css('position') == 'fixed') {
						return;
					}
					el.css({
						'top' : 0,
						'position' : 'fixed',
						'right' : 20
					});
				} else {
					el.css({
						'position' : 'static'
					});
				}
			}
		}
	}();

	// $(window).scroll(function(){
	     // scrollTofixed();
	// });

	//nav menu event
	var navMouseTimer;
	$('.nav-menu>li').mouseenter(function() {
		var $this = $(this);
		navMouseTimer = setTimeout(function() {
			$this.addClass('open');
			$this.find('.children,.sub-menu').slideDown('fast');
		}, 200);

	}).mouseleave(function() {
		clearTimeout(navMouseTimer);
		var $this = $(this);

		$this.removeClass('open');
		$this.find('.children,.sub-menu').hide();

	});

	//scrolltop
	function setScrollTop() {
		var sel = $(".scroll-top");
		var scrollTop = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
		if (scrollTop > 0) {
			sel.fadeIn();
		} else {
			sel.fadeOut();
		}
		sel.unbind().click(function() {
			$(document.body).animate({
				scrollTop : 0
			}, 500);
			$(document.documentElement).animate({
				scrollTop : 0
			}, 500)
		});
		if ($.browser.msie && $.browser.version == "6.0") {
			sel.css({
				top : document.documentElement.clientHeight + scrollTop - 170
			});
		}
	}
	$(window).scroll(setScrollTop).resize(setScrollTop);

	var UserData = {
		userData : null,
		name : location.hostname,

		init : function() {
			if (!UserData.userData) {
				try {
					UserData.userData = document.createElement('INPUT');
					UserData.userData.type = "hidden";
					UserData.userData.style.display = "none";
					UserData.userData.addBehavior("#default#userData");
					document.body.appendChild(UserData.userData);
					var expires = new Date();
					expires.setMinutes(30);
					UserData.userData.expires = expires.toUTCString();
				} catch(e) {
					return false;
				}
			}
			return true;
		},

		setItem : function(key, value) {

			if (UserData.init()) {
				UserData.userData.load(UserData.name);
				UserData.userData.setAttribute(key, value);
				UserData.userData.save(UserData.name);
			}
		},

		getItem : function(key) {
			if (UserData.init()) {
				UserData.userData.load(UserData.name);
				return UserData.userData.getAttribute(key)
			}
		},

		remove : function(key) {
			if (UserData.init()) {
				UserData.userData.load(UserData.name);
				UserData.userData.removeAttribute(key);
				UserData.userData.save(UserData.name);
			}

		}
	};
	$.local = {};
	$.local.set = function(key, value) {
		if (!window.localStorage) {
			if (value) {
				UserData.setItem(key, value);
			} else {
				UserData.remove(key);
			}
		} else {
			if (value) {
				window.localStorage.setItem(key, value);
			} else {
				window.localStorage.removeItem(key);
			}
		}
	}
	$.local.get = function(key) {
		if (!window.localStorage) {
			return UserData.getItem(key);
		} else {
			return window.localStorage.getItem(key);
		}
	}
	//custom cols
	var setCol = function() {
		var col = $.local.get('col');
		if (col == 2) {
			$('body').addClass('col2');
			$('.side-bar-category').fadeOut();
		} else {

			$('body').removeClass('col2');
			$('.side-bar-category').fadeIn();
		}
	}
	// setCol();
	$('.navbar-col a').click(function() {
		var rel = $(this).attr('rel');
		if (rel == "two") {
			$('body').addClass('col2');
			$.local.set('col', 2);
			$('.side-bar-category').fadeOut();
		} else {
			$('body').removeClass('col2');
			$.local.set('col', 3);
			$('.side-bar-category').fadeIn();
		}
	});

	//random theme
	//var randomNum = parseInt(Math.random()*(5-1)+1);
	//$(document.body).addClass('theme'+randomNum);

})(jQuery); 