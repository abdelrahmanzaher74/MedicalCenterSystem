const ctx = document.getElementById('myChart');

new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
        datasets: [{
            label: 'Patients',
            data: [10, 20, 30, 25, 40],
            borderWidth: 2
        }]
    },
});