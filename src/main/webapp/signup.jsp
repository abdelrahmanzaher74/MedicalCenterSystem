<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Sign Up</title>

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

<style>

/* ===== BACKGROUND ===== */
body {
    margin: 0;
    height: 100vh;
    font-family: 'Segoe UI', sans-serif;

    background: linear-gradient(135deg, #1e3c72, #2a5298);

    display: flex;
    justify-content: center;
    align-items: center;
}

/* ===== CARD ===== */
.auth-container {
    background: rgba(255,255,255,0.15);
    backdrop-filter: blur(15px);
    padding: 40px;
    border-radius: 20px;
    width: 370px;
    text-align: center;
    box-shadow: 0 20px 50px rgba(0,0,0,0.4);
}

/* ===== LOGO ===== */
.logo {
    font-size: 40px;
    color: #4fc3f7;
    margin-bottom: 10px;
}

h2 {
    color: #e3f2fd;
    margin-bottom: 20px;
}

/* ===== INPUT ===== */
.input-group {
    position: relative;
    margin-bottom: 12px;
}

.input-group span {
    position: absolute;
    left: 10px;
    top: 50%;
    transform: translateY(-50%);
    color: #4fc3f7;
}

.input-group input {
    width: 100%;
    padding: 10px 40px 10px 35px;
    border-radius: 10px;
    border: none;
    outline: none;
    background: rgba(255,255,255,0.15);
    color: #e3f2fd;
}

.input-group input::placeholder {
    color: #bbdefb;
}

/* eye icon */
.toggle-pass {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
    color: #fff;
}

/* ===== BUTTON ===== */
button {
    width: 100%;
    padding: 10px;
    border: none;
    border-radius: 10px;
    background: linear-gradient(90deg, #2196f3, #21cbf3);
    color: #fff;
    font-weight: bold;
    cursor: pointer;
    transition: 0.3s;
}

button:hover {
    transform: scale(1.05);
}

/* ===== LINK ===== */
a {
    display: block;
    margin-top: 15px;
    color: #81d4fa;
    text-decoration: none;
}

/* ===== ERRORS ===== */
small {
    color: #ff6b6b;
    display: block;
    text-align: left;
    font-size: 12px;
}

</style>

</head>

<body>

<div class="auth-container">

    <div class="logo">
        <i class="fa-solid fa-user-plus"></i>
    </div>

    <h2>Create Account</h2>

<form id="signupForm" action="signup" method="post">

    <!-- Username -->
    <div class="input-group">
        <span><i class="fa-solid fa-user"></i></span>
        <input type="text" name="username" placeholder="Username" required>
    </div>

    <!-- Password -->
    <div class="input-group">
        <span><i class="fa-solid fa-lock"></i></span>
        <input type="password" id="password" name="password" placeholder="Password" required>
        <i class="fa-solid fa-eye toggle-pass" onclick="togglePassword('password')"></i>
        <small id="passError"></small>
    </div>

    <!-- Confirm Password -->
    <div class="input-group">
        <span><i class="fa-solid fa-lock"></i></span>
        <input type="password" id="confirmPassword" placeholder="Confirm Password" required>
        <i class="fa-solid fa-eye toggle-pass" onclick="togglePassword('confirmPassword')"></i>
        <small id="confirmError"></small>
    </div>

    <button type="submit">Sign Up</button>

</form>

<a href="login.jsp">Already have account? Login</a>

</div>

<script>

// ===== SHOW / HIDE PASSWORD =====
function togglePassword(id){
    let input = document.getElementById(id);
    input.type = input.type === "password" ? "text" : "password";
}

// ===== VALIDATION =====
document.getElementById("signupForm").addEventListener("submit", function(e){

    let pass = document.getElementById("password").value;
    let confirm = document.getElementById("confirmPassword").value;

    let passError = document.getElementById("passError");
    let confirmError = document.getElementById("confirmError");

    let valid = true;

    // Password rules
    let regex = /^(?=.*[A-Z])(?=.*\d).{6,}$/;

    if(!regex.test(pass)){
        passError.innerText = "Password must contain capital letter, number, min 6 chars";
        valid = false;
    } else {
        passError.innerText = "";
    }

    // Confirm match
    if(pass !== confirm){
        confirmError.innerText = "Passwords do not match";
        valid = false;
    } else {
        confirmError.innerText = "";
    }

    if(!valid) e.preventDefault();

});

</script>

</body>
</html>