const employeeForm = document.getElementById("employee");
const employeeFirstName = document.getElementById("firstName");
const employeeLastName = document.getElementById("lastName");
const employeeEmail = document.getElementById("employeeEmail");
const programmerManagerSelect = document.getElementById("programmerManagerSelect");
const testerManagerSelect = document.getElementById("testerManagerSelect");
const programmer = document.getElementById("programmer");
const tester = document.getElementById("tester");
const programmerManager = document.getElementById("programmerManager");
const testerManager = document.getElementById("testerManager");
const employeeGenderMale = document.getElementById("employeeGenderMale");
const employeeGenderFemale = document.getElementById("employeeGenderFemale");


employeeFirstName.addEventListener("change", function () {
    if (!namePattern.test(employeeFirstName.value)) {
        removeClass(employeeFirstName, "uk-form-success");
        addAnotherClass(employeeFirstName, "uk-form-danger");
    } else {
        removeClass(employeeFirstName, "uk-form-danger");
        addAnotherClass(employeeFirstName, "uk-form-success")
    }
})
employeeLastName.addEventListener("change", function () {
    if (!namePattern.test(employeeLastName.value)) {
        removeClass(employeeLastName, "uk-form-success");
        addAnotherClass(employeeLastName, "uk-form-danger");
    } else {
        removeClass(employeeLastName, "uk-form-danger");
        addAnotherClass(employeeLastName, "uk-form-success")
    }
})


employeeEmail.addEventListener("change", function () {
    if (!emailPattern.test(employeeEmail.value)) {
        removeClass(employeeEmail, "uk-form-success");
        addAnotherClass(employeeEmail, "uk-form-danger");
    } else {
        removeClass(employeeEmail, "uk-form-danger");
        addAnotherClass(employeeEmail, "uk-form-success");
    }
})


submitButton.addEventListener("click", function () {
    if (validateemployee()) {
        removeClass(submitButton, "uk-animation-shake");
        removeClass(submitButton, "uk-button-danger");
        addAnotherClass(submitButton, "uk-button-primary");
        employeeForm.submit();
        setTimeout(function () {
            makeIconVisible(checkIcon);
            employeeForm.reset();
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

function validateemployee() {
    return (
        validateName(employeeFirstName.name)
        &&
        validateName(employeeLastName.name)
        &&
        validateEmail(employeeEmail.value)
        &&
        validateRadios(employeeGenderMale, employeeGenderFemale)
        &&
        validateRadios(programmerManagerSelect, testerManagerSelect)
        &&
        validateRadios(programmer, tester, programmerManager, testerManager)
    );
}