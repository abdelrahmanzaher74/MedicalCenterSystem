<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>

<html>
<head>
<title>Reports</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body class="bg-light">

<jsp:include page="sidebar.jsp" />

<div style="margin-left:230px; padding:20px;">

<h2>📊 Advanced Reports</h2>

<div class="row mt-3">

<div class="col-md-4">
<div class="card p-3 bg-primary text-white">
Patients: <h3><%= request.getAttribute("patients") %></h3>
</div>
</div>

<div class="col-md-4">
<div class="card p-3 bg-success text-white">
Doctors: <h3><%= request.getAttribute("doctors") %></h3>
</div>
</div>

<div class="col-md-4">
<div class="card p-3 bg-warning text-white">
Appointments: <h3><%= request.getAttribute("appointments") %></h3>
</div>
</div>

</div>

<hr>

<!-- 📅 Chart -->
<canvas id="appointmentsChart" style="height:300px;"></canvas>

<hr>

<!-- 👨‍⚕️ Doctor Chart -->
<canvas id="doctorChart" style="height:300px;"></canvas>

<hr>

<!-- 🦠 Top Diseases -->
<h4>Top Diseases</h4>

<ul>
<%
List<Object[]> diseases = (List<Object[]>) request.getAttribute("topDiseases");

if(diseases != null){
for(Object[] d : diseases){
%>
<li><%= d[0] %> (<%= d[1] %>)</li>
<%
}
}
%>
</ul>

</div>

<%
/* ================= BUILD DATA SAFELY ================= */

List<Object[]> perDay = (List<Object[]>) request.getAttribute("perDay");
List<Object[]> perDoctor = (List<Object[]>) request.getAttribute("perDoctor");

StringBuilder labelsStr = new StringBuilder();
StringBuilder dataStr = new StringBuilder();

if(perDay != null){
for(int i=0; i<perDay.size(); i++){
Object[] r = perDay.get(i);

String date = new java.text.SimpleDateFormat("yyyy-MM-dd")
        .format((java.util.Date)r[0]);

labelsStr.append("\"").append(date).append("\"");
dataStr.append(r[1]);

if(i < perDay.size()-1){
labelsStr.append(",");
dataStr.append(",");
}
}
}

StringBuilder docLabelsStr = new StringBuilder();
StringBuilder docDataStr = new StringBuilder();

if(perDoctor != null){
for(int i=0; i<perDoctor.size(); i++){
Object[] r = perDoctor.get(i);

// 🔥 FIX quotes problem
String name = r[0] != null ? r[0].toString().replace("\"","").replace("'","") : "";

docLabelsStr.append("\"").append(name).append("\"");
docDataStr.append(r[1]);

if(i < perDoctor.size()-1){
docLabelsStr.append(",");
docDataStr.append(",");
}
}
}
%>

<script>

// 📅 Appointments per day
new Chart(document.getElementById('appointmentsChart'), {
    type: 'line',
    data: {
        labels: [<%= labelsStr.toString() %>],
        datasets: [{
            label: 'Appointments per Day',
            data: [<%= dataStr.toString() %>],
            borderWidth: 2
        }]
    }
});

// 👨‍⚕️ Appointments per doctor
new Chart(document.getElementById('doctorChart'), {
    type: 'bar',
    data: {
        labels: [<%= docLabelsStr.toString() %>],
        datasets: [{
            label: 'Appointments per Doctor',
            data: [<%= docDataStr.toString() %>],
            borderWidth: 1
        }]
    }
});

</script>

</body>
</html>