
  function adjustFormLayout() {
    const screenWidth = window.innerWidth;

    const grid = document.querySelector(".grid");
    const formGroups = document.querySelectorAll(".form-group");

    if (screenWidth <= 768) {
      // For smaller screens, adjust the grid layout
      grid.style.gridTemplateColumns = "1fr";
      formGroups.forEach(formGroup => {
        formGroup.style.flexBasis = "100%";
      });
    } else if (screenWidth <= 1024) {
      // For medium-sized screens, adjust the grid layout further
      grid.style.gridTemplateColumns = "1fr 1fr";
      formGroups.forEach(formGroup => {
        formGroup.style.flexBasis = "calc(50% - 10px)";
      });
    } else {
      // For larger screens, revert back to the original grid layout
      grid.style.gridTemplateColumns = "1fr 1fr";
      formGroups.forEach(formGroup => {
        formGroup.style.flexBasis = "calc(50% - 10px)";
      });
    }
  }

  // Call the adjustFormLayout function on page load and whenever the window is resized
  window.addEventListener("load", adjustFormLayout);
  window.addEventListener("resize", adjustFormLayout);

  function validateForm() {
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const mobileNumber = document.getElementById("mobile-number").value;
    const gender = document.querySelector('input[name="gender"]:checked');

    if (username.trim() === "") {
      alert("Username must be filled out");
      return false;
    }

    if (email.trim() === "") {
      alert("Email must be filled out");
      return false;
    }

    if (password === "") {
      alert("Password must be filled out");
      return false;
    }

    if (confirmPassword === "") {
      alert("Confirm password must be filled out");
      return false;
    }

    if (password !== confirmPassword) {
      alert("Passwords do not match");
      return false;
    }

    if (mobileNumber.trim() === "") {
      alert("Mobile number must be filled out");
      return false;
    }

    if (!gender) {
      alert("Gender must be selected");
      return false;
    }
  }

