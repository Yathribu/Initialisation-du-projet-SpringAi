// ===============================
// HTML ELEMENTS
// ===============================

// Generate button
const generateBtn = document.getElementById("generateBtn");

// Prompt textarea
const promptInput = document.getElementById("promptInput");

// Loading div
const loading = document.getElementById("loading");

// Responses container
const responsesContainer = document.getElementById("responsesContainer");

// Backend endpoint
const API_URL = "http://localhost:8080/home/askai";


// ===============================
// BUTTON EVENT
// ===============================

generateBtn.addEventListener("click", async () => {

    // Clear previous responses
    responsesContainer.innerHTML = "";

    // Get prompt value
    const prompt = promptInput.value.trim();

    // Get selected providers
    const selectedProviders = Array.from(
        document.querySelectorAll('input[type="checkbox"]:checked')
    ).map(cb => cb.value);

    // Validation
    if (!prompt) {
        alert("Veuillez entrer une question");
        return;
    }

    // Validation
    if (selectedProviders.length === 0) {
        alert("Veuillez sélectionner au moins une IA");
        return;
    }

    // Show loading
    loading.classList.remove("hidden");

    try {

        // HTTP POST request
        const response = await fetch(API_URL, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({

                userRequest: prompt,

                aiProvider: selectedProviders

            })

        });

        // API error
        if (!response.ok) {
            throw new Error("Erreur API");
        }

        // Convert JSON response
        const data = await response.json();

        // Display responses
        displayResponses(data);

    } catch (error) {

        console.error(error);

        responsesContainer.innerHTML = `
        
            <div class="response-card">

                <div class="response-title">
                    Erreur
                </div>

                <div class="response-content">
                    Une erreur est survenue.
                </div>

            </div>
        `;

    } finally {

        // Hide loading
        loading.classList.add("hidden");

    }

});


// ===============================
// DISPLAY RESPONSES
// ===============================

function displayResponses(data) {

    // If backend returns:
    // {
    //   responses: [...]
    // }

    const responses = data.responses;

    responses.forEach((response, index) => {

        // Create card
        const card = document.createElement("div");

        card.className = "response-card";

        // HTML inside card
        card.innerHTML = `

            <div class="response-title">
                IA ${index + 1}
            </div>

            <div class="response-content">
                ${response}
            </div>
        `;

        // Add card to container
        responsesContainer.appendChild(card);

    });

}