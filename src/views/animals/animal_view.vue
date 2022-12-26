<template>
	<button @click="goList()">back</button>
	<h1>{{animalName}}</h1>
	<h2>Details</h2>
	<p>Name: {{animalName}}</p>
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
	<button @click="upAnimal">update</button>
	<h2>Pet List</h2>
	<tr>
		<th>id</th>
		<th>name</th>
		<th>View/Edit</th>
		<th>Delete</th>
	</tr>
	<tr v-for="item in petArr">
		<td>{{item.id}}</td>
		<td>{{item.name}}</td>
		<td>
			<button @click="goView(item.id, animalName)">View/Edit</button>
		</td>
		<td>
			<button @click="delPet(item.id)">delete</button>
		</td>
	</tr>
	<button @click="goAdd(animalName)">add</button>
</template>

<script>
	export default {
		data() {
			return {
				foodType: "foodType",
				animalName: this.$route.query.animal,
				petArr: []
			}
		},
		methods:{
			upAnimal(){
				updateAnimal(this.animalName, this);
				alert("update sucessfully");
			},
			delPet(id){
				deletePet(id, this);
			},
			goView(pet, animal){
				this.$router.push(
					{
						path: "/pet", 
						query: {
							pet: pet,
							animal: animal
						}
					}
				);
			},
			goAdd(animal){
				this.$router.push({
						path: "/pet/add", 
						query: {
							animal: animal
						}
					});
			},
			goList(){
				this.$router.push({
						path: "/animals", 
					});
			},
		},
		mounted: function(){
			fetchAndDisplayAnimal(this);
			fetchAndDisplayPets(this)
		}
	};
	
	function fetchAndDisplayAnimal(obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            obj.foodType = JSON.parse(this.responseText).food_type;
				console.log(JSON.parse(this.responseText));
	        }
	    };
	    xhttp.open("GET", 'http://localhost:4030/api/animals/' + obj.$route.query.animal, true);
	    xhttp.send();
	}
	
	function updateAnimal(animal, obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            fetchAndDisplayAnimal(obj);
	        }
	    };
	    xhttp.open("PUT", 'http://localhost:4030/api/animals/' + animal, true);
	
	    const request = {
	        'name': obj.animalName,
	        'food_type': obj.foodType,
	    };
	
	    xhttp.setRequestHeader('Content-Type', 'application/json');
	
	    xhttp.send(JSON.stringify(request));
	}
	
	function displayPets(pets, obj) {
		obj.petArr = [];
		pets.pets.forEach(pet => {
			console.log(pet);
			obj.petArr.push(pet);
		});
	}
	
	function fetchAndDisplayPets(obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            displayPets(JSON.parse(this.responseText), obj);
	        }
	    };
	    xhttp.open("GET", 'http://localhost:4030/api/animals/' + obj.animalName + '/pets', true);
	    xhttp.send();
	}
	
	function deletePet(pet, obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 202) {
	            fetchAndDisplayPets(obj);
	        }
	    };
	    xhttp.open("DELETE", 'http://localhost:4030/api/animals/' + obj.animalName + '/pets/' + pet, true);
	    xhttp.send();
	}
</script>

<style>
	th{
		padding: 2px;
	}
	td{
		padding: 5px;
	}
</style>