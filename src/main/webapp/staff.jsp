<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.medical.model.entities.*" %>

<html>
<head>
<title>Staff</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

</head>

<body class="bg-light">

<!-- SIDEBAR -->
<jsp:include page="sidebar.jsp" />

<div style="margin-left:230px; padding:20px;">

<h2 class="mb-4">Staff Management</h2>

<!-- ALERTS -->
<%
String success = (String) session.getAttribute("success");
String error = (String) session.getAttribute("error");

if(success != null){
%>
<div class="alert alert-success text-center"><%= success %></div>
<%
}
if(error != null){
%>
<div class="alert alert-danger text-center"><%= error %></div>
<%
}

session.removeAttribute("success");
session.removeAttribute("error");
%>

<!-- ADD -->
<form action="staff" method="post" class="row g-3 mb-4">

<div class="col-md-3">
<input name="ssn" class="form-control" placeholder="SSN" required>
</div>

<div class="col-md-3">
<input name="name" class="form-control" placeholder="Name" required>
</div>

<div class="col-md-3">
<input name="email" class="form-control" placeholder="Email">
</div>

<div class="col-md-3">
<select name="gender" class="form-select">
<option value="">Gender</option>
<option value="M">Male</option>
<option value="F">Female</option>
</select>
</div>

<div class="col-md-3">
<input name="salary" class="form-control" placeholder="Salary">
</div>

<div class="col-md-3">
<input type="date" name="start_date" class="form-control">
</div>

<div class="col-md-3">
<select name="department" class="form-select">
<option value="">Department</option>

<%
List<Departments> deps = (List<Departments>) request.getAttribute("departments");

if(deps != null){
for(Departments d : deps){
%>
<option value="<%= d.getDepartmentid() %>">
<%= d.getDepName() %>
</option>
<%
}}
%>

</select>
</div>

<div class="col-md-12">
<button class="btn btn-success w-100">Add Staff</button>
</div>

</form>

<hr>

<!-- SEARCH -->
<input id="searchInput" class="form-control mb-3" placeholder="Search...">

<!-- TABLE -->
<table class="table table-striped text-center">

<tr>
<th>SSN</th>
<th>Name</th>
<th>Email</th>
<th>Gender</th>
<th>Salary</th>
<th>Date</th>
<th>Department</th>
<th>Actions</th>
</tr>

<%
List<Staff> list = (List<Staff>) request.getAttribute("staffList");

if(list != null){
for(Staff s : list){
%>

<tr>
<td><%= s.getStaffSSN() %></td>
<td><%= s.getStName() %></td>
<td><%= s.getStEmail() %></td>
<td><%= s.getStGender() %></td>
<td><%= s.getStSalary() %></td>
<td><%= s.getStStartDate() %></td>

<td>
<%= s.getDepartmentid() != null ? s.getDepartmentid().getDepName() : "" %>
</td>

<td>

<a href="deleteStaff?id=<%= s.getStaffSSN() %>"
class="btn btn-danger btn-sm"
onclick="return confirm('Delete?')">
Delete
</a>

<button class="btn btn-primary btn-sm editBtn"
data-ssn="<%= s.getStaffSSN() %>"
data-name="<%= s.getStName() %>"
data-email="<%= s.getStEmail() %>"
data-gender="<%= s.getStGender() %>"
data-salary="<%= s.getStSalary() %>"
data-date="<%= s.getStStartDate() %>"
data-department="<%= s.getDepartmentid() != null ? s.getDepartmentid().getDepartmentid() : "" %>">
Edit
</button>

</td>

</tr>

<%
}}
%>

</table>

</div>

<!-- MODAL -->
<div id="editModal" style="display:none; position:fixed; top:0; width:100%; height:100%; background:rgba(0,0,0,0.5);">

<div style="background:white; width:400px; margin:100px auto; padding:20px; border-radius:10px;">

<form action="updateStaff" method="post">

<input name="ssn" id="edit_ssn" readonly class="form-control mb-2">
<input name="name" id="edit_name" class="form-control mb-2">
<input name="email" id="edit_email" class="form-control mb-2">
<input name="gender" id="edit_gender" class="form-control mb-2">
<input name="salary" id="edit_salary" class="form-control mb-2">
<input type="date" name="start_date" id="edit_date" class="form-control mb-2">
<input name="department" id="edit_department" class="form-control mb-2">

<button class="btn btn-primary w-100">Update</button>
<button type="button" class="btn btn-secondary w-100 mt-2" onclick="closeModal()">Cancel</button>

</form>

</div>
</div>

<script>

// SEARCH
document.getElementById("searchInput").addEventListener("keyup", function(){
let v=this.value.toLowerCase();
document.querySelectorAll("table tr").forEach(r=>{
r.style.display=r.innerText.toLowerCase().includes(v)?"":"none";
});
});

// EDIT
document.querySelectorAll(".editBtn").forEach(b=>{
b.onclick=function(){
editModal.style.display="block";

edit_ssn.value=this.dataset.ssn;
edit_name.value=this.dataset.name;
edit_email.value=this.dataset.email;
edit_gender.value=this.dataset.gender;
edit_salary.value=this.dataset.salary;

let d=this.dataset.date;
if(d && d.includes(" ")) d=d.split(" ")[0];
edit_date.value=d;

edit_department.value=this.dataset.department;
}
});

function closeModal(){
editModal.style.display="none";
}

</script>

</body>
</html>