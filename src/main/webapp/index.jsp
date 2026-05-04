<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

    <!-- Chart -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        body {
            background: #f4f6f9;
        }

        .card-custom {
            border-radius: 20px;
            transition: 0.3s;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        }

        .card-custom:hover {
            transform: scale(1.05);
        }

        .icon-box {
            font-size: 35px;
            opacity: 0.8;
        }

        .bg-primary {
            background: linear-gradient(45deg, #0d6efd, #3d8bfd);
        }

        .bg-success {
            background: linear-gradient(45deg, #198754, #3ddc97);
        }

        .bg-warning {
            background: linear-gradient(45deg, #ffc107, #ffd966);
        }

        .bg-danger {
            background: linear-gradient(45deg, #dc3545, #ff6b6b);
        }
    </style>
</head>

<body>

<!-- ✅ SIDEBAR -->
<jsp:include page="sidebar.jsp" />

<!-- ✅ WRAPPER -->
<div style="margin-left:230px; padding:20px;">

    <!-- TOP BAR -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Dashboard</h2>
        <span class="text-muted"><%
com.medical.model.entities.User user =
    (com.medical.model.entities.User) session.getAttribute("user");
%>

Welcome, <%= user.getUsername() %></span>
    </div>

    <!-- 🔥 STATS -->
    <div class="row g-4 mb-4">

        <div class="col-md-3">
            <div class="card card-custom bg-primary text-white p-3">
                <div class="d-flex justify-content-between">
                    <div>
                        <h4>${patientsCount}</h4>
                        <p>Patients</p>
                    </div>
                    <i class="fas fa-user-injured icon-box"></i>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card card-custom bg-success text-white p-3">
                <div class="d-flex justify-content-between">
                    <div>
                        <h4>${staffCount}</h4>
                        <p>Staff</p>
                    </div>
                    <i class="fas fa-user-md icon-box"></i>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card card-custom bg-warning text-dark p-3">
                <div class="d-flex justify-content-between">
                    <div>
                        <h4>${appointmentsCount}</h4>
                        <p>Appointments</p>
                    </div>
                    <i class="fas fa-calendar-check icon-box"></i>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card card-custom bg-danger text-white p-3">
                <div class="d-flex justify-content-between">
                    <div>
                        <h4>${equipmentCount}</h4>
                        <p>Equipment</p>
                    </div>
                    <i class="fas fa-tools icon-box"></i>
                </div>
            </div>
        </div>

    </div>

    <!-- 📊 CHART -->
    <div class="card card-custom p-4">
        <h4 class="mb-3">Patients Growth</h4>
        <canvas id="myChart"></canvas>
    </div>

</div>

<script>

const ctx = document.getElementById('myChart');

new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Jan','Feb','Mar','Apr','May','Jun'],
        datasets: [{
            label: 'Patients Growth',
            data: [5, 15, 25, 40, 60, 80],
            borderWidth: 3,
            tension: 0.4,
            fill: true
        }]
    },
    options: {
        responsive: true
    }
});

</script>

</body>
</html>