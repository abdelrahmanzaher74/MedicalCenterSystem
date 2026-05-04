<%@ page import="java.util.*" %>
<%@ page import="com.medical.model.entities.Patient" %>

<html>
<head>
    <title>Patients Management</title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .modal {
            position: fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            display:none;
            justify-content:center;
            align-items:center;
            background: rgba(0,0,0,0.6);
            z-index:9999;
        }

        .modal-box {
            background:white;
            padding:20px;
            border-radius:10px;
            width:400px;
        }
    </style>
</head>

<body class="bg-light">

<jsp:include page="sidebar.jsp" />

<div style="margin-left:230px; padding:20px;">
<div class="container mt-4">

<%
String error = (String) request.getAttribute("error");
if (error != null) {
%>
<div class="alert alert-danger text-center">
<%= error %>
</div>
<%
}
%>

<!-- SEARCH + FILTER -->
<div class="row mb-3">
    <div class="col-md-6">
        <input type="text" id="searchInput" class="form-control" placeholder="Search by name...">
    </div>

    <div class="col-md-6">
        <select id="genderFilter" class="form-select">
            <option value="">All Gender</option>
            <option value="M">Male</option>
            <option value="F">Female</option>
        </select>
    </div>
</div>

<!-- ADD -->
<div class="card p-4 mb-4 shadow">
<h4>Add Patient</h4>

<form id="patientForm" action="patients" method="post" class="row g-3">

<div class="col-md-3">
<input type="text" name="ssn" id="ssn" class="form-control" placeholder="SSN">
<small id="ssnError" class="text-danger"></small>
</div>

<div class="col-md-3">
<select name="blood_id" id="blood" class="form-select">
<option value="">Blood</option>

<%
List<com.medical.model.entities.Bloodtype> bloodList =
    (List<com.medical.model.entities.Bloodtype>) request.getAttribute("bloodList");

if (bloodList != null) {
    for (com.medical.model.entities.Bloodtype b : bloodList) {
%>

<option value="<%= b.getBloodId() %>">
    <%= b.getBloodname() %>
</option>

<%
    }
}
%>

</select>
<small id="bloodError" class="text-danger"></small>
</div>

<div class="col-md-3">
<input type="text" name="name" id="name" class="form-control" placeholder="Name">
<small id="nameError" class="text-danger"></small>
</div>

<div class="col-md-3">
<input type="email" name="email" class="form-control" placeholder="Email">
</div>

<div class="col-md-3">
<select name="gender" class="form-select">
<option value="">Gender</option>
<option value="M">Male</option>
<option value="F">Female</option>
</select>
</div>

<div class="col-md-3">
<input type="text" name="status" class="form-control" placeholder="Status">
</div>

<div class="col-md-3">
<input type="date" name="birth_date" class="form-control">
</div>

<div class="col-md-3">
<input type="text" name="address" class="form-control" placeholder="Address">
</div>

<div class="col-md-3">
<input type="text" name="phone" id="phone" class="form-control" placeholder="Phone">
<small id="phoneError" class="text-danger"></small>
</div>

<div class="col-12">
<button class="btn btn-success">Add Patient</button>
</div>

</form>
</div>

<!-- TABLE -->
<table class="table table-striped">

<thead>
<tr>
<th>SSN</th>
<th>Name</th>
<th>Email</th>
<th>Gender</th>
<th>Status</th>
<th>Address</th>
<th>Blood</th>
<th>Phone</th>
<th>Age</th>
<th>Actions</th>
</tr>
</thead>

<tbody>

<%
List<Patient> list = (List<Patient>) request.getAttribute("patients");
Map<String,String> phoneMap = (Map<String,String>) request.getAttribute("phoneMap");

if (list != null) {
for (Patient p : list) {
%>

<tr>

<td><%= p.getPatientssn() %></td>
<td class="name"><%= p.getPatientName() %></td>
<td><%= p.getPatientEmail() %></td>
<td class="gender"><%= p.getPatientGender() %></td>
<td><%= p.getPatientMaritalstatus() %></td>
<td><%= p.getPatientAdress() %></td>

<td>
<%= p.getBloodId() != null ? p.getBloodId().getBloodname() : "" %>
</td>

<td>
<%= (phoneMap != null && phoneMap.get(p.getPatientssn()) != null)
    ? phoneMap.get(p.getPatientssn())
    : "" %>
</td>

<td>
<%
int age = 0;
if(p.getPatientBirthDate() != null){
    Calendar birth = Calendar.getInstance();
    birth.setTime(p.getPatientBirthDate());

    Calendar today = Calendar.getInstance();
    age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
}
%>
<%= age %>
</td>

<td>

<button class="btn btn-warning btn-sm editBtn"
data-ssn="<%= p.getPatientssn() %>"
data-name="<%= p.getPatientName() %>"
data-email="<%= p.getPatientEmail() %>"
data-gender="<%= p.getPatientGender() %>"
data-status="<%= p.getPatientMaritalstatus() %>"
data-address="<%= p.getPatientAdress() %>"
data-blood="<%= p.getBloodId() != null ? p.getBloodId().getBloodId() : "" %>">
Edit
</button>

<a href="deletePatient?id=<%= p.getPatientssn() %>" class="btn btn-danger btn-sm">
Delete
</a>

</td>

</tr>

<%
}
}
%>

</tbody>
</table>

</div>
</div>

<!-- ===== EDIT MODAL ===== -->
<div id="editModal" class="modal">

<div class="modal-box">

<h3>Edit Patient</h3>

<form action="updatePatient" method="post">

<input type="hidden" name="ssn" id="edit_ssn">

<input type="text" name="name" id="edit_name" class="form-control mb-2">
<input type="email" name="email" id="edit_email" class="form-control mb-2">

<select name="gender" id="edit_gender" class="form-control mb-2">
<option value="M">Male</option>
<option value="F">Female</option>
</select>

<input type="text" name="status" id="edit_status" class="form-control mb-2">
<input type="text" name="address" id="edit_address" class="form-control mb-2">

<select name="blood_id" id="edit_blood" class="form-control">
<option value="">Select Blood</option>

<%
if (bloodList != null) {
    for (com.medical.model.entities.Bloodtype b : bloodList) {
%>
<option value="<%= b.getBloodId() %>">
    <%= b.getBloodname() %>
</option>
<%
    }
}
%>

</select>

<button class="btn btn-success">Save</button>
</form>

<button onclick="closeModal()" class="btn btn-danger mt-2">Cancel</button>

</div>
</div>
        
<script>
console.log("? JS WORKING");

// ================= VALIDATION =================
let form = document.getElementById("patientForm");
let phone = document.getElementById("phone");
let ssn = document.getElementById("ssn");

if (form) {
    form.addEventListener("submit", function(e){

        let valid = true;

        // Phone
        if (phone && !/^\d{11}$/.test(phone.value)) {
            document.getElementById("phoneError").innerText = "Phone must be 11 digits";
            valid = false;
        } else {
            document.getElementById("phoneError").innerText = "";
        }

        // SSN
        if (ssn && !/^\d{9}$/.test(ssn.value)) {
            document.getElementById("ssnError").innerText = "SSN must be 9 digits";
            valid = false;
        } else {
            document.getElementById("ssnError").innerText = "";
        }

        if (!valid) e.preventDefault();
    });
}

// ================= SEARCH =================
let searchInput = document.getElementById("searchInput");
let genderFilter = document.getElementById("genderFilter");

if (searchInput) searchInput.addEventListener("keyup", filterPatients);
if (genderFilter) genderFilter.addEventListener("change", filterPatients);

function filterPatients() {

    let search = searchInput.value.toLowerCase();
    let gender = genderFilter.value;

    document.querySelectorAll("tbody tr").forEach(row => {

        let name = row.querySelector(".name")?.textContent.toLowerCase() || "";
        let g = row.querySelector(".gender")?.textContent || "";

        let matchName = name.includes(search);
        let matchGender = (gender === "" || g === gender);

        row.style.display = (matchName && matchGender) ? "" : "none";
    });
}

// ================= EDIT =================
document.querySelectorAll(".editBtn").forEach(btn => {

    btn.addEventListener("click", function () {

        let modal = document.getElementById("editModal");
        if (modal) modal.style.display = "flex";

        document.getElementById("edit_ssn").value = this.dataset.ssn;
        document.getElementById("edit_name").value = this.dataset.name;
        document.getElementById("edit_email").value = this.dataset.email;
        document.getElementById("edit_gender").value = this.dataset.gender;
        document.getElementById("edit_status").value = this.dataset.status;
        document.getElementById("edit_address").value = this.dataset.address;
        document.getElementById("edit_blood").value = this.dataset.blood;
    });

});

// ================= CLOSE MODAL =================
function closeModal() {
    let modal = document.getElementById("editModal");
    if (modal) modal.style.display = "none";
}
</script>
</body>
</html>