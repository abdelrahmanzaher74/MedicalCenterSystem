<%@ page import="java.util.*, com.medical.model.entities.Bloodtype" %>

<html>
<head>
<title>Blood Types</title>

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.modal {
    position: fixed;
    top:0; left:0;
    width:100%; height:100%;
    display:none;
    justify-content:center;
    align-items:center;
    background: rgba(0,0,0,0.6);
}
.modal-box {
    background:white;
    padding:20px;
    border-radius:10px;
    width:350px;
}
</style>

</head>

<body class="bg-light">

<jsp:include page="sidebar.jsp" />

<div style="margin-left:230px; padding:20px;">
<div class="container mt-4">

<h2>Blood Types</h2>

<!-- ADD -->
<div class="card p-4 mb-4 shadow">
<h4>Add Blood Type</h4>

<form action="blood" method="post" class="row g-3">

<input type="hidden" name="action" value="add">

<div class="col-md-3">
<input type="number" name="id" class="form-control" placeholder="ID" required>
</div>

<div class="col-md-5">
<input type="text" name="name" class="form-control" placeholder="A+, O-, AB+" required>
</div>

<div class="col-md-4">
<button class="btn btn-success w-100">Add</button>
</div>

</form>
</div>

<!-- TABLE -->
<table class="table table-striped shadow">

<thead>
<tr>
<th>ID</th>
<th>Name</th>
<th>Actions</th>
</tr>
</thead>

<tbody>

<%
List<Bloodtype> list = (List<Bloodtype>) request.getAttribute("bloodList");

if (list != null && !list.isEmpty()) {
    for (Bloodtype b : list) {
%>

<tr>
<td><%= b.getBloodId() %></td>
<td><%= b.getBloodname() %></td>

<td>

<button class="btn btn-warning btn-sm editBtn"
data-id="<%= b.getBloodId() %>"
data-name="<%= b.getBloodname() %>">
Edit
</button>

<form action="blood" method="post" style="display:inline;">
<input type="hidden" name="action" value="delete">
<input type="hidden" name="id" value="<%= b.getBloodId() %>">
<button class="btn btn-danger btn-sm">Delete</button>
</form>

</td>
</tr>

<%
    }
} else {
%>
<tr>
<td colspan="3" class="text-center text-muted">No Blood Types Found</td>
</tr>
<%
}
%>

</tbody>
</table>

</div>
</div>

<!-- EDIT MODAL -->
<div id="editModal" class="modal">
<div class="modal-box">

<h4>Edit Blood</h4>

<form action="blood" method="post">
<input type="hidden" name="action" value="update">
<input type="hidden" name="id" id="edit_id">

<input type="text" name="name" id="edit_name" class="form-control mb-2">

<button class="btn btn-success w-100">Save</button>
</form>

<button onclick="closeModal()" class="btn btn-danger mt-2 w-100">Cancel</button>

</div>
</div>

<script>
// EDIT
document.querySelectorAll(".editBtn").forEach(btn => {
    btn.addEventListener("click", function () {

        let modal = document.getElementById("editModal");
        modal.style.display = "flex";

        document.getElementById("edit_id").value = this.dataset.id;
        document.getElementById("edit_name").value = this.dataset.name;
    });
});

function closeModal(){
    document.getElementById("editModal").style.display = "none";
}
</script>

</body>
</html>