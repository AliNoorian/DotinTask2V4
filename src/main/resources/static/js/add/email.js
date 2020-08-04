const emailForm = document.getElementById("email");
const subject = document.getElementById("subject");
const fileSelect = document.getElementById("fileSelect");
fileElem = document.getElementById("fileElem");
const obj_url = URL.createObjectURL(blob);
fileSelect.src = obj_url;
URL.revokeObjectURL(obj_url);

function blob() {

    fileSelect.addEventListener("click", function () {
        if (fileElem) {
            fileElem.click();
        }
    }, false);
}


subject.addEventListener("change", function () {
    if (!namePattern.test(subject.value)) {
        removeClass(subject, "uk-form-success");
        addAnotherClass(subject, "uk-form-danger");
    } else {
        removeClass(subject, "uk-form-danger");
        addAnotherClass(subject, "uk-form-success")
    }
})


submitButton.addEventListener("click", function () {
    if (validateemail()) {
        removeClass(submitButton, "uk-animation-shake");
        removeClass(submitButton, "uk-button-danger");
        addAnotherClass(submitButton, "uk-button-primary");
        emailForm.submit();
        setTimeout(function () {
            makeIconVisible(checkIcon);
            emailForm.reset();
        }, 3000);
    } else {
        makeIconInvisible(checkIcon);
        addAnotherClass(submitButton, "uk-animation-shake");
        addAnotherClass(submitButton, "uk-button-danger");
        setTimeout(function () {
            removeClass(submitButton, "uk-animation-shake");
            removeClass(submitButton, "uk-button-danger");
            addAnotherClass(submitButton, "uk-button-primary");
        }, 1000);
    }

})

function validateemail() {
    return (
        validateName(subject.name)
    );
}