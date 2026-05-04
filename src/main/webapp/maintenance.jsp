<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Maintenance</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<!-- ✅ SIDEBAR (ADD ONLY) -->
<jsp:include page="sidebar.jsp" />

<!-- ✅ WRAPPER (ADD ONLY) -->
<div style="margin-left:230px; padding:20px;">

<div class="container mt-4">

<h2 class="text-center mb-4">Maintenance</h2>

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
<div class="card bg-primary text-white"><div class="card-body">
<h5>Total</h5><h3>${total}</h3>
</div></div></div>

<div class="col-md-4">
<div class="card bg-warning"><div class="card-body">
<h5>Pending</h5><h3>${pending}</h3>
</div></div></div>

<div class="col-md-4">
<div class="card bg-success text-white"><div class="card-body">
<h5>Completed</h5><h3>${completed}</h3>
</div></div></div>

</div>

<!-- ADD -->
<form action="maintenance" method="post" class="row g-3">

<input name="id" type="number" class="form-control" placeholder="ID" required>
<input name="date" type="date" class="form-control" required>

<select name="equipment_id" class="form-select" required>
<option value="">Equipment</option>
<c:forEach var="e" items="${equipments}">
<option value="${e.equipmentid}">${e.equipmentName}</option>
</c:forEach>
</select>

<select name="company_id" class="form-select" required>
<option value="">Company</option>
<c:forEach var="c" items="${companies}">
<option value="${c.companyid}">${c.companyname}</option>
</c:forEach>
</select>

<input name="type" placeholder="Type" required>
<input name="cost" type="number" placeholder="Cost" required>

<select name="status">
<option>Pending</option>
<option>Completed</option>
<option>In Progress</option>
</select>

<button class="btn btn-success">Add</button>

</form>

<hr>

<input id="searchInput" class="form-control mb-3" placeholder="Search...">

<table class="table table-striped text-center">
<tr>
<th>ID</th><th>Date</th><th>Equipment</th><th>Company</th><th>Type</th><th>Status</th><th>Cost</th><th>Actions</th>
</tr>

<c:forEach var="m" items="${maintenance}">
<tr>
<td>${m.maintenanceID}</td>
<td>${m.maintenanceDate}</td>
<td>${m.equipmentid.equipmentName}</td>
<td>${m.companyid.companyname}</td>
<td>${m.maintenanceType}</td>
<td>${m.maintenanceStatus}</td>
<td>${m.maintenanceCost}</td>

<td>
<a href="deleteMaintenance?id=${m.maintenanceID}" class="btn btn-danger btn-sm">Delete</a>

<button class="btn btn-primary btn-sm editBtn"
data-id="${m.maintenanceID}"
data-date="${m.maintenanceDate}"
data-equipment="${m.equipmentid.equipmentid}"
data-company="${m.companyid.companyid}"
data-type="${m.maintenanceType}"
data-status="${m.maintenanceStatus}"
data-cost="${m.maintenanceCost}">
Edit
</button>
</td>

</tr>
</c:forEach>

</table>

</div>

</div> <!-- wrapper close -->

<!-- MODAL -->
<div id="editModal" style="display:none; position:fixed; top:0; width:100%; height:100%; background:rgba(0,0,0,0.5);">

<div style="background:white; width:400px; margin:100px auto; padding:20px; border-radius:10px;">

<form action="updateMaintenance" method="post">

<input id="edit_id" name="id" readonly>
<input type="date" id="edit_date" name="date">

<select id="edit_equipment" name="equipment_id">
<c:forEach var="e" items="${equipments}">
<option value="${e.equipmentid}">${e.equipmentName}</option>
</c:forEach>
</select>

<select id="edit_company" name="company_id">
<c:forEach var="c" items="${companies}">
<option value="${c.companyid}">${c.companyname}</option>
</c:forEach>
</select>

<input id="edit_type" name="type">
<input id="edit_cost" name="cost">

<select id="edit_status" name="status">
<option>Pending</option>
<option>Completed</option>
<option>In Progress</option>
</select>

<button class="btn btn-primary w-100">Update</button>
<button type="button" onclick="closeModal()" class="btn btn-secondary w-100 mt-2">Cancel</button>

</form>

</div>
</div>

<script>

document.getElementById("searchInput").addEventListener("keyup", function(){
let v=this.value.toLowerCase();
document.querySelectorAll("table tr").forEach(r=>{
r.style.display=r.innerText.toLowerCase().includes(v)?"":"none";
});
});

function closeModal(){editModal.style.display="none";}

document.querySelectorAll(".editBtn").forEach(b=>{
b.onclick=function(){
editModal.style.display="block";
edit_id.value=this.dataset.id;
let d=this.dataset.date;
if(d.includes(" ")) d=d.split(" ")[0];
edit_date.value=d;
edit_equipment.value=this.dataset.equipment;
edit_company.value=this.dataset.company;
edit_type.value=this.dataset.type;
edit_status.value=this.dataset.status;
edit_cost.value=this.dataset.cost;
}
});

</script>

</body>
</html>