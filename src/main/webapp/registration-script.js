
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
    function generateRegistrationID() {
        const prefix = 'FEST';
        const randomNum = Math.floor(Math.random() * 100000000); // 8-digit random number
        const registrationID = `${prefix}-${String(randomNum).padStart(8, '0')}`;
        return registrationID;
    }
    function validateRegistrationForm() {
        let isValid = true;

        document.querySelectorAll('.error-msg').forEach(msg => msg.textContent = '');

        const fullName = document.getElementById('full-name').value.trim();
        const nameError = document.getElementById('name-error');
        if (fullName.length < 3) {
            nameError.textContent = '❌ Name must be at least 3 characters long';
            isValid = false;
        } else if (!/^[a-zA-Z\s]+$/.test(fullName)) {
            nameError.textContent = '❌ Name can only contain letters and spaces';
            isValid = false;
        }
        const email = document.getElementById('email').value.trim();
        const emailError = document.getElementById('email-error');
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            emailError.textContent = '❌ Please enter a valid email address';
            isValid = false;
        }
        const phone = document.getElementById('phone').value.trim();
        const phoneError = document.getElementById('phone-error');
        if (!/^\d{10}$/.test(phone)) {
            phoneError.textContent = '❌ Phone must be exactly 10 digits (numbers only)';
            isValid = false;
        }
        const age = parseInt(document.getElementById('age').value);
        const ageError = document.getElementById('age-error');
        if (isNaN(age) || age < 1 || age > 150) {
            ageError.textContent = '❌ Age must be a valid number between 1 and 150';
            isValid = false;
        }
        const festival = document.getElementById('favorite-festival').value;
        const festivalError = document.getElementById('festival-error');
        if (festival === '') {
            festivalError.textContent = '❌ Please select your favorite festival';
            isValid = false;
        }
        const agreeTerms = document.getElementById('agree-terms').checked;
        const termsError = document.getElementById('terms-error');
        if (!agreeTerms) {
            termsError.textContent = '❌ You must agree to receive updates';
            isValid = false;
        }
        return isValid;
    }
    const registrationForm = document.getElementById('registration-form');
    const regIdInput = document.getElementById('reg-id');
    const successMessage = document.getElementById('success-message');
    if (registrationForm) {
        window.addEventListener('load', () => {
            regIdInput.value = generateRegistrationID();
        });
        registrationForm.addEventListener('submit', (e) => {
            e.preventDefault();
            if (validateRegistrationForm()) {
                const registrationID = regIdInput.value;
                document.getElementById('display-reg-id').textContent = registrationID;
                successMessage.style.display = 'block';               
                const formData = {
                    regId: registrationID,
                    fullName: document.getElementById('full-name').value,
                    email: document.getElementById('email').value,
                    phone: document.getElementById('phone').value,
                    age: document.getElementById('age').value,
                    favoriteFestival: document.getElementById('favorite-festival').value,
                    timestamp: new Date().toLocaleString()
                };               
                setCookie('festival_reg_id', formData.regId, 7);
                setCookie('festival_full_name', formData.fullName, 7);
                setCookie('festival_email', formData.email, 7);
                setCookie('festival_phone', formData.phone, 7);
                setCookie('festival_age', formData.age, 7);
                setCookie('festival_favorite_festival', formData.favoriteFestival, 7);
                setCookie('festival_timestamp', formData.timestamp, 7);                
                console.log('Registration successful! Cookies saved:', formData);
                setTimeout(() => {
                    successMessage.scrollIntoView({ behavior: 'smooth', block: 'center' });
                }, 300);
            } else {
                successMessage.style.display = 'none';
            }
        });
        const resetBtn = registrationForm.querySelector('button[type="reset"]');
        resetBtn.addEventListener('click', () => {
            document.querySelectorAll('.error-msg').forEach(msg => msg.textContent = '');
            successMessage.style.display = 'none';
        });
    }

});
