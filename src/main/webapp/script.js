

document.addEventListener('DOMContentLoaded', () => {
    function setCookie(name, value, days) {
        let expires = "";
        if (days) {
            const date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + encodeURIComponent(value || "") + expires + "; path=/; SameSite=Lax";
    }

    function getCookie(name) {
        const nameEQ = name + "=";
        const ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) === 0) return decodeURIComponent(c.substring(nameEQ.length, c.length));
        }
        return null;
    }

    function eraseCookie(name) {
        document.cookie = name + '=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    }


    const welcomeBanner = document.getElementById('welcome-banner');
    const welcomeText = document.getElementById('welcome-text');
    const resetProfileBtn = document.getElementById('reset-profile-btn');

    function checkUserProfile() {
        let username = getCookie('festival_user');
        if (!username) {
            username = "Festive Guest";
            setCookie('festival_user', username, 14);
        }
        welcomeText.textContent = `Namaste, ${username}! Welcome to your Indian Festivals Guide. 🪔`;
        welcomeBanner.style.display = 'flex';
    }

    resetProfileBtn.addEventListener('click', () => {
        const confirmReset = confirm("Are you sure you want to reset your profile? This will clear your stored name cookie.");
        if (confirmReset) {
            eraseCookie('festival_user');
            alert("Profile preferences cleared.");
            location.reload();
        }
    });

    checkUserProfile();


    const slides = document.querySelectorAll('.slide');
    const dots = document.querySelectorAll('.slider-dot');
    const prevBtn = document.getElementById('slider-prev');
    const nextBtn = document.getElementById('slider-next');
    const sliderContainer = document.querySelector('.slider-container');
    let currentSlide = 0;
    let autoplayInterval;

    function showSlide(index) {
        if (index >= slides.length) currentSlide = 0;
        else if (index < 0) currentSlide = slides.length - 1;
        else currentSlide = index;

        slides.forEach(slide => slide.classList.remove('active'));
        dots.forEach(dot => dot.classList.remove('active'));

        slides[currentSlide].classList.add('active');
        dots[currentSlide].classList.add('active');
    }

    function startAutoplay() {
        autoplayInterval = setInterval(() => {
            showSlide(currentSlide + 1);
        }, 5000); 
    }

    function stopAutoplay() {
        clearInterval(autoplayInterval);
    }

    prevBtn.addEventListener('click', () => {
        showSlide(currentSlide - 1);
    });

    nextBtn.addEventListener('click', () => {
        showSlide(currentSlide + 1);
    });

    dots.forEach((dot, idx) => {
        dot.addEventListener('click', () => {
            showSlide(idx);
        });
    });

    sliderContainer.addEventListener('mouseenter', stopAutoplay);
    sliderContainer.addEventListener('mouseleave', startAutoplay);

    showSlide(0);
    startAutoplay();


    const filterTabs = document.querySelectorAll('.filter-tab');
    const festivalCards = document.querySelectorAll('.card');

    filterTabs.forEach(tab => {
        tab.addEventListener('click', () => {
            filterTabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            const categoryFilter = tab.getAttribute('data-filter');

            festivalCards.forEach(card => {
                const cardCategory = card.getAttribute('data-category');
                
                if (categoryFilter === 'all' || cardCategory === categoryFilter) {
                    card.style.display = 'flex';
                    setTimeout(() => {
                        card.style.opacity = '1';
                        card.style.transform = 'scale(1)';
                    }, 50);
                } else {
                    card.style.opacity = '0';
                    card.style.transform = 'scale(0.95)';
                    setTimeout(() => {
                        card.style.display = 'none';
                    }, 300);
                }
            });
        });
    });
    const regIdCookie = getCookie('festival_reg_id');
    const fullNameCookie = getCookie('festival_full_name');
    
    if (regIdCookie && fullNameCookie) {
        welcomeText.textContent = `Namaste, ${fullNameCookie}! Welcome to your Indian Festivals Guide. 🪔`;
        welcomeBanner.style.display = 'flex';
    } else {
        checkUserProfile();
    }

});
