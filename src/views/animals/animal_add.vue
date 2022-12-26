<template>
	<p>Name: <input id="name" name="name" v-model="animalName"/></p>
	FoodType:
	<br/>
	<label for="fd1">carnivore</label>
	<input id="fd1" name="fd" type="radio" value="carnivore" v-model="foodType"/>
	<br/>
	<label for="fd2">herbivore</label>
	<input id="fd2" name="fd" type="radio" value="herbivore" v-model="foodType"/>
	<br/>
	<label for="fd3">omnivore</label>
	<input id="fd3" name="fd" type="radio" value="omnivore" v-model="foodType"/>
	<br/>
	<button @click="create">submit</button>
	<button @click="goList()">cancel/back</button>
</template>

<script>
	export default {
		data() {
			return {
				animalName: "animalName",
				foodType: "omnivore",
			}
		},
		methods:{
			create()
			{
				createAnimal(this.animalName, this.foodType);
				setTimeout(() => {
					this.$router.push("/animals")
				}, 1000);
			},
			goList(){
				this.$router.push({
					path: "/animals", 
				});
			},
		}
	};
	
	function createAnimal(name, foodType) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            console.log("Animal created successfully");
	        }
	    };
	    xhttp.open("POST", 'http://localhost:4030/api/animals/', true);
	
	    const request = {
	        'name': name,
	        'food_type': foodType,
	    };
	
	    xhttp.setRequestHeader('Content-Type', 'application/json');
	
	    xhttp.send(JSON.stringify(request));
	}
</script>

<style>
</style>