function validateForm() {
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const mobileNumber = document.getElementById("mobile-number").value;
    const gender = document.querySelector('input[name="gender"]:checked');
  
    if (username === "") {
      alert("Username must be filled out");
      return false;
    }
  
    if (email === "") {
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
  
    if (mobileNumber === "") {
      alert("Mobile number must be filled out");
      return false;
    }
  
    if (!gender) {
      alert("Gender must be selected");
      return false;
    }
  }
  