<template>
    <div id="app" class="ceiling-fan">
        <img src="/assets/ceiling-fan.png" alt="My Image" class="ceiling-fan-image" />
        <div>
            <button @click="increaseSpeed" >Speed</button>
            <button @click="toggleDirection">Direction</button>
        </div>
        <div>
            <p><strong>Direction:</strong> {{ direction }}</p>
            <p><strong>Speed:</strong> {{ speed }}</p>
        </div>

    </div>
</template>

<script>
export default {
    data() {
        return {
            speed: 0,
            direction: "clockwise",  // Initial direction
            fanId: 1,                // Primary key of the ceiling fan,
            mode: "insert"
        };

    },
    mounted() {
        // Fetch the initial values for the ceiling fan when the component mounts
        this.fetchCeilingFan();
    },
    computed: {
        requestMethod() {
            return this.mode == 'insert' ? 'POST' : 'PUT';
        },
        requestUrl() {
            return this.mode == 'insert' ? 'http://localhost:8080/api/fans' : 'http://localhost:8080/api/fans/1';
        }
    },
    methods: {
        async fetchCeilingFan() {
            fetch('http://localhost:8080/api/fans')
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    if (data.length > 0) {
                        //Use the first fan, we should only have one,
                        //future iterations could assign fans to people and pull their fan
                        this.speed = data[0].speed;
                        this.direction = data[0].direction;
                        this.mode = "update";
                    }
                })
                .catch(error => {
                    console.log('Error fetching fan:', error);
                });

        },
        // Method to increase the fan speed
        async increaseSpeed() {
            const newSpeed = this.speed + 1 > 3 ? 0 : this.speed + 1;
            const data = {
                speed: newSpeed,
                direction: this.direction
            };

            await this.updateCeilingFan(data);
            this.speed = newSpeed;  // Update the local speed state
        },

        // Method to toggle the direction of the fan
        async toggleDirection() {
            const newDirection = this.direction === "clockwise" ? "counter-clockwise" : "clockwise";
            const data = {
                speed: this.speed,
                direction: newDirection
            };
            await this.updateCeilingFan(data);
            this.direction = newDirection;  // Update the local direction state
        },

        // Helper method to update the ceiling fan in the backend
        async updateCeilingFan(data) {
            fetch(this.requestUrl, {  // Assuming the fan's ID is 1
                method: this.requestMethod,  // Specifies the request method
                headers: {
                    'Content-Type': 'application/json', // The type of content you're sending (JSON)
                },
                body: JSON.stringify(data),  // The updated data you're sending as JSON
            }).then(response => response.json())
                .then(data => {
                    console.log(data);
                    if (data) {
                        //Use the first fan, we should only have one,
                        //future iterations could assign fans to people and pull their fan
                        this.speed = data.speed;
                        this.direction = data.direction;
                        this.mode = "update";
                    }
                })
                .catch(error => {
                    console.log('Error fetching fan:', error);
                });
        },

    }
};
</script>

<style scoped>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}

.ceiling-fan {
    text-align: center;
    margin-top: 20px;;
}

button {
    margin: 10px;
    padding: 10px;
    font-size: 16px;
    cursor: pointer;
}

button:disabled {
    cursor: not-allowed;
    background-color: #ccc;
}

.ceiling-fan-image {
    width: 50%;
    height: auto;
    border-radius: 8px;
}
</style>
