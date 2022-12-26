<template>
	<button @click="goView()">back</button>
	<h1>View/Edit Pet</h1>
	<label for="name">Name: </label>
	<input id="name" name="name" v-model="petName"/>
	<br/>
	<label>isSick: </label>
	<label for="is1">True</label>
	<input id="is1" name="isSick" type="radio" value="true" v-model="isSick"/>
	<label for="is2">False</label>
	<input id="is2" name="isSick" type="radio" value="false" v-model="isSick"/>
	<br/>
	<label for="animal">Animal: {{animalName}}</label>
	<br/>
	<button @click="upPet">update</button>
</template>

<script>
	export default {
		data() {
			return {
				id: this.$route.query.pet,
				petName: "petName",
				isSick: "false",
				animalName: this.$route.query.animal
			}
		},
		methods:{
			upPet(){
				updatePet(this.animalName, this);
				alert("update sucessfully");
			},
			goView(){
				this.$router.push({
						path: "/animals/view", 
						query: {
							animal: this.animalName
						}
					});
			},
		},
		mounted: function(){
			fetchAndDisplayPet(this);
		}
	};
	
	function displayPet(pet, obj) {
		obj.petName = pet.name;
		obj.isSick = pet.isSick?"true":"false";
		obj.animalName = pet.animal;
	}
	
	function fetchAndDisplayPet(obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            displayPet(JSON.parse(this.responseText), obj);
				console.log(JSON.parse(this.responseText));
	        }
	    };
	    xhttp.open("GET", 'http://localhost:4030/api/animals/' + obj.animalName + '/pets/' + obj.id, true);
	    xhttp.send();
	}
	
	function updatePet(pet, obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            fetchAndDisplayAnimal(obj);
	        }
	    };
	    xhttp.open("PUT", 'http://localhost:4030/api/animals/' + obj.animalName + '/pets/' + obj.id, true);
	
	    const request = {
	        'name': obj.petName,
	        'is_sick': obj.isSick == "true"? true : false,
	    };
	
	    xhttp.setRequestHeader('Content-Type', 'application/json');
	
	    xhttp.send(JSON.stringify(request));
	}
</script>

<style>

</style>