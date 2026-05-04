<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Appointments</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<!-- ✅ SIDEBAR -->
<jsp:include page="sidebar.jsp" />

<!-- ✅ WRAPPER -->
<div style="margin-left:230px; padding:20px;">

<div class="container mt-4">

<h2 class="text-center mb-4">Appointments</h2>

<!-- ALERTS -->
<c:if test="${not empty success}">
<div class="alert alert-success text-center">${success}</div>
</c:if>

<c:if test="${not empty error}">
<div class="alert alert-danger text-center">${error}</div>
</c:if>

<%
session.removeAttribute("success");
session.removeAttribute("error");
%>

<!-- DASHBOARD -->
<div class="row mb-4 text-center">

<div class="col-md-4">
<div class="card bg-primary text-white">
<div class="card-body">
<h5>Total</h5>
<h3>${totalAppointments}</h3>
</div>
</div>
</div>

<div class="col-md-4">
<div class="card bg-success text-white">
<div class="card-body">
<h5>With Service</h5>
<h3>${withService}</h3>
</div>
</div>
</div>

<div class="col-md-4">
<div class="card bg-warning text-dark">
<div class="card-body">
<h5>Without Service</h5>
<h3>${withoutService}</h3>
</div>
</div>
</div>

</div>

<!-- ================= ADD ================= -->
<form id="appointmentForm" action="appointments" method="post" class="row g-3">

<div class="col-md-2">
<input type="number" name="id" class="form-control" placeholder="ID" required>
</div>

<div class="col-md-3">
<input type="datetime-local" name="date" class="form-control" required>
</div>

<div class="col-md-2">
<input type="text" name="patient" id="patientSSN" class="form-control" placeholder="Patient SSN" required>
<small class="text-danger" id="ssnError"></small>
</div>

<div class="col-md-2">
<select name="doctor" class="form-select" required>
<option value="">Doctor</option>
<c:forEach var="d" items="${doctors}">
<option value="${d.doctorSSN}">${d.doctorsName}</option>
</c:forEach>
</select>
</div>

<div class="col-md-2">
<select name="service" class="form-select">
<option value="">No Service</option>
<c:forEach var="s" items="${services}">
<option value="${s.serviceid}">${s.serviceName}</option>
</c:forEach>
</select>
</div>

<div class="col-md-2">
<select name="method" class="form-select" required>
<option value="">Payment</option>
<c:forEach var="m" items="${methods}">
<option value="${m.methodid}">${m.methodName}</option>
</c:forEach>
</select>
</div>

<div class="col-md-12">
<button class="btn btn-success w-100">Add Appointment</button>
</div>

</form>

<hr>

<input type="text" id="searchInput" class="form-control mb-3" placeholder="Search appointments...">

<!-- ================= TABLE ================= -->
<table class="table table-striped text-center">

<thead class="table-dark">
<tr>
<th>ID</th>
<th>Date</th>
<th>Patient</th>
<th>Doctor</th>
<th>Total 💰</th>
<th>Actions</th>
</tr>
</thead>

<tbody>

<c:forEach var="a" items="${appointments}">
<tr>

<td>${a.appointmentid}</td>
<td>${a.appointmentDate}</td>
<td>${a.patientSSN.patientName}</td>
<td>${a.doctorSSN.doctorsName}</td>

<td>
${ (a.doctorSSN.priceid.priceValue)
 + (a.serviceid != null ? a.serviceid.priceid.priceValue : 0) }
</td>

<td>

<a href="deleteAppointment?id=${a.appointmentid}"
class="btn btn-danger btn-sm"
onclick="return confirm('Delete?')">
Delete
</a>

<button class="btn btn-primary btn-sm editBtn"
data-id="${a.appointmentid}"
data-date="${a.appointmentDate}"
data-ssn="${a.patientSSN.patientssn}"
data-doctor="${a.doctorSSN.doctorSSN}"
data-service="${a.serviceid != null ? a.serviceid.serviceid : ''}"
data-method="${a.methodid.methodid}">
Edit
</button>

</td>

</tr>
</c:forEach>

</tbody>
</table>

</div>

</div> <!-- wrapper -->

<!-- ================= MODAL ================= -->
<div id="editModal" style="display:none; position:fixed; top:0; width:100%; height:100%; background:rgba(0,0,0,0.5);">

<div style="background:white; width:400px; margin:100px auto; padding:20px; border-radius:10px;">

<form action="updateAppointment" method="post">

<label>ID</label>
<input id="edit_id" name="id" class="form-control mb-2" readonly>

<label>Date & Time</label>
<input type="datetime-local" id="edit_date" name="date" class="form-control mb-2" required>

<label>Patient SSN</label>
<input id="edit_ssn" name="patient" class="form-control mb-2">

<label>Doctor</label>
<select id="edit_doctor" name="doctor" class="form-select mb-2">
<c:forEach var="d" items="${doctors}">
<option value="${d.doctorSSN}">${d.doctorsName}</option>
</c:forEach>
</select>

<label>Service</label>
<select id="edit_service" name="service" class="form-select mb-2">
<option value="">No Service</option>
<c:forEach var="s" items="${services}">
<option value="${s.serviceid}">${s.serviceName}</option>
</c:forEach>
</select>

<label>Payment</label>
<select id="edit_method" name="method" class="form-select mb-3">
<c:forEach var="m" items="${methods}">
<option value="${m.methodid}">${m.methodName}</option>
</c:forEach>
</select>

<button class="btn btn-primary w-100">Update</button>
<button type="button" class="btn btn-secondary w-100 mt-2" onclick="closeModal()">Cancel</button>

</form>

</div>
</div>

<script>

// VALIDATION
document.getElementById("appointmentForm").addEventListener("submit", function(e){
let ssn = document.getElementById("patientSSN");
let error = document.getElementById("ssnError");

error.innerText = "";

if (!/^\d{9}$/.test(ssn.value)) {
error.innerText = "SSN must be 9 digits";
e.preventDefault();
}
});

// EDIT
document.querySelectorAll(".editBtn").forEach(btn => {

btn.addEventListener("click", function () {

document.getElementById("editModal").style.display = "block";

document.getElementById("edit_id").value = this.dataset.id;

let date = this.dataset.date;

if (date.includes(" ")) {
    date = date.replace(" ", "T").substring(0,16);
} else {
    date = date + "T00:00";
}

document.getElementById("edit_date").value = date;

document.getElementById("edit_ssn").value = this.dataset.ssn;
document.getElementById("edit_doctor").value = this.dataset.doctor;
document.getElementById("edit_service").value = this.dataset.service;
document.getElementById("edit_method").value = this.dataset.method;

});

});

// SEARCH
document.getElementById("searchInput").addEventListener("keyup", function(){
let value = this.value.toLowerCase();
document.querySelectorAll("table tbody tr").forEach(row=>{
row.style.display = row.innerText.toLowerCase().includes(value) ? "" : "none";
});
});

function closeModal(){
document.getElementById("editModal").style.display = "none";
}

</script>

</body>
</html>