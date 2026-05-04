<%@ page import="java.util.*" %>
<%@ page import="com.medical.model.entities.Patient" %>
<%@ page import="com.medical.model.entities.Diseases" %>

<html>
<head>
<title>Patient Profile</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

</head>

<body class="bg-light">

<jsp:include page="sidebar.jsp" />

<div style="margin-left:230px; padding:20px;">
<div class="container mt-4">

<h3>Patient Profile</h3>

<!-- SEARCH -->
<form method="get" action="patientProfile" class="mb-4">
<input type="text" name="ssn" class="form-control" placeholder="Enter SSN">
<button class="btn btn-primary mt-2">Search</button>
</form>

<%
Patient p = (Patient) request.getAttribute("selectedPatient");
List<String> phones = (List<String>) request.getAttribute("phones");
List<Object[]> diseases = (List<Object[]>) request.getAttribute("diseases");
List<Diseases> allDiseases = (List<Diseases>) request.getAttribute("allDiseases");
List<Object[]> appointments = (List<Object[]>) request.getAttribute("appointments");

if (p != null) {
%>

<div class="card p-4 shadow">

<h4><%= p.getPatientName() %></h4>
<hr>

<p><b>SSN:</b> <%= p.getPatientssn() %></p>
<p><b>Email:</b> <%= p.getPatientEmail() %></p>
<p><b>Gender:</b> <%= p.getPatientGender() %></p>
<p><b>Status:</b> <%= p.getPatientMaritalstatus() %></p>
<p><b>Address:</b> <%= p.getPatientAdress() %></p>

<p><b>Blood:</b>
<%= p.getBloodId() != null ? p.getBloodId().getBloodname() : "" %>
</p>

<!-- AGE -->
<p><b>Age:</b>
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
</p>

<hr>

<h5> Phone Numbers</h5>
<ul>
<%
if(phones != null){
for(String ph : phones){
%>
<li><%= ph %></li>
<%
}
}
%>
</ul>

<hr>

<h5>Diseases</h5>
<ul>
<%
if(diseases != null && !diseases.isEmpty()){
for(Object[] d : diseases){
%>
<li>
<%= d[1] %>

<form method="post" action="patientProfile" style="display:inline;">
<input type="hidden" name="action" value="delete">
<input type="hidden" name="ssn" value="<%= p.getPatientssn() %>">
<input type="hidden" name="diseaseId" value="<%= d[0] %>">
<button class="btn btn-danger btn-sm">X</button>
</form>

</li>
<%
}
}else{
%>
<li>No diseases</li>
<%
}
%>
</ul>

<hr>

<h5>Add Disease</h5>

<form method="post" action="patientProfile">
<input type="hidden" name="action" value="add">
<input type="hidden" name="ssn" value="<%= p.getPatientssn() %>">

<select name="diseaseIds" id="diseaseSelect" class="form-control" multiple>
<%
for(Diseases d : allDiseases){
%>
<option value="<%= d.getDiseaseID() %>"><%= d.getDiseaseName() %></option>
<%
}
%>
</select>

<button class="btn btn-primary mt-2">Add</button>
</form>

<hr>

<h5> Appointments</h5>

<table class="table table-bordered mt-2">
<tr>
<th>ID</th>
<th>Date</th>
<th>Doctor</th>
</tr>

<%
if(appointments != null && !appointments.isEmpty()){
for(Object[] a : appointments){
%>
<tr>
<td><%= a[0] %></td>
<td><%= a[1] %></td>
<td><%= a[2] %></td>
</tr>
<%
}
}else{
%>
<tr><td colspan="3">No appointments</td></tr>
<%
}
%>

</table>

</div>

<%
}
%>

</div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<script>
$(document).ready(function() {
    $('#diseaseSelect').select2({
        placeholder: "Search diseases",
        width: '100%'
    });
});
</script>

</body>
</html>