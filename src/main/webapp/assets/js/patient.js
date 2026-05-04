console.log("🔥 JS RUNNING");

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
        }

        // SSN
        if (ssn && !/^\d{9}$/.test(ssn.value)) {
            document.getElementById("ssnError").innerText = "SSN must be exactly 9 digits";
            valid = false;
        }

        if (!valid) e.preventDefault();
    });
}

// ================= SEARCH =================
let searchInput = document.getElementById("searchInput");
let genderFilter = document.getElementById("genderFilter");

if (searchInput) {
    searchInput.addEventListener("keyup", filterPatients);
}

if (genderFilter) {
    genderFilter.addEventListener("change", filterPatients);
}

function filterPatients() {

    let search = searchInput.value.toLowerCase();
    let gender = genderFilter ? genderFilter.value : "";

    document.querySelectorAll("tbody tr").forEach(row => {

        let name = row.querySelector(".name")?.textContent.toLowerCase() || "";
        let g = row.querySelector(".gender")?.textContent || "";

        let show = name.includes(search) && (gender === "" || g === gender);

        row.style.display = show ? "" : "none";
    });
}

// ================= EDIT =================
document.querySelectorAll(".editBtn").forEach(btn => {

    btn.addEventListener("click", function () {

        console.log("✏️ EDIT CLICKED");

        let modal = document.getElementById("editModal");

        if (modal) {
            modal.style.display = "flex";
        }

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
    if (modal) {
        modal.style.display = "none";
    }
}