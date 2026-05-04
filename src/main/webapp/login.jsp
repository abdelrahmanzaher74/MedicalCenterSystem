<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Login</title>

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
    width: 350px;
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
    margin-bottom: 25px;
}

/* ===== INPUT ===== */
.input-group {
    position: relative;
    margin-bottom: 15px;
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

/* ===== ERROR ===== */
.error {
    color: #ff6b6b;
    margin-bottom: 10px;
}

small {
    color: #ff6b6b;
    display: block;
    text-align: left;
}

</style>

</head>

<body>

<div class="auth-container">

    <div class="logo">
        <i class="fa-solid fa-hospital"></i>
    </div>

    <h2>Medical Center</h2>

<%
if(request.getParameter("error") != null){
%>
<p class="error">Wrong username or password</p>
<%
}
%>

<form id="loginForm" action="login" method="post">

    <div class="input-group">
        <span><i class="fa-solid fa-user"></i></span>
        <input type="text" name="username" placeholder="Username" required>
    </div>

    <div class="input-group">
        <span><i class="fa-solid fa-lock"></i></span>
        <input type="password" id="password" name="password" placeholder="Password" required>

        <i class="fa-solid fa-eye toggle-pass" onclick="togglePassword()"></i>

        <small id="passError"></small>
    </div>

    <button type="submit">Login</button>

</form>

<a href="signup.jsp">Create Account</a>

</div>

<script>

// ===== SHOW / HIDE PASSWORD =====
function togglePassword(){
    let pass = document.getElementById("password");
    pass.type = pass.type === "password" ? "text" : "password";
}

// ===== VALIDATION =====
document.getElementById("loginForm").addEventListener("submit", function(e){

    let pass = document.getElementById("password").value;
    let error = document.getElementById("passError");

    let regex = /^(?=.*[A-Z])(?=.*\d).{6,}$/;

    if(!regex.test(pass)){
        error.innerText = "Password must contain 1 capital letter, 1 number and be at least 6 characters";
        e.preventDefault();
    } else {
        error.innerText = "";
    }

});

</script>

</body>
</html>