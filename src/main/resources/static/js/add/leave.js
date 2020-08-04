const leaveForm = document.getElementById("leave");
const leaveSubject = document.getElementById("leaveSubject");
const dailyLeaveType = document.getElementById("dailyLeaveType");
const hourlyLeaveType = document.getElementById("hourlyLeaveType");


leaveSubject.addEventListener("change", function () {
    if (!namePattern.test(leaveSubject.value)) {
        removeClass(leaveSubject, "uk-form-success");
        addAnotherClass(leaveSubject, "uk-form-danger");
    } else {
        removeClass(leaveSubject, "uk-form-danger");
        addAnotherClass(leaveSubject, "uk-form-success")
    }
})


submitButton.addEventListener("click", function () {
    if (validateleave()) {
        removeClass(submitButton, "uk-animation-shake");
        removeClass(submitButton, "uk-button-danger");
        addAnotherClass(submitButton, "uk-button-primary");
        leaveForm.submit();
        setTimeout(function () {
            makeIconVisible(checkIcon);
            leaveForm.reset();
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

function validateleave() {
    return (
        validateName(leaveSubject.name)
        &&
        validateRadios(dailyLeaveType, hourlyLeaveType)
    );
}