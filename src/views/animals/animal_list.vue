<template>
	<h1>Animals</h1>
	<tr>
		<th>name</th>
		<th>view/edit</th>
		<th>delete</th>
	</tr>
	<tr v-for="name in animalArr">
		<td>{{name}}</td>
		<td>
			<button @click="goView(name)">View/Edit</button>
		</td>
		<td>
			<button @click="delAnimal(name)">Delete</button>
		</td>
	</tr>
	<button @click="goAdd">add</button>
	<br>
	<button @click="goFile">file</button>
</template>

<script>
	export default {
		data() {
			return {
				animalArr: []
			}
		},
		methods: {
			delAnimal(animal){
				deleteAnimal(animal, this)
			},
			goAdd(){
				this.$router.push("/animals/add");
			},
			goView(animal){
				this.$router.push(
					{
						path: "/animals/view", 
						query: {
							animal: animal
						}
					}
				);
			},
			goFile(){
				this.$router.push(
					{
						path: "/file", 
					}
				);
			}
		},
		mounted:function () { 
			fetchAndDisplayAnimals(this);
		},
	};


	function displayAnimals(animals, obj) {
		obj.animalArr = [];
		animals.animals.forEach(animal => {
			console.log(animal);
			obj.animalArr.push(animal.name);
		});
	}

	function fetchAndDisplayAnimals(obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            displayAnimals(JSON.parse(this.responseText), obj);
	        }
	    };
	    xhttp.open("GET", 'http://localhost:4030/api/animals', true);
	    xhttp.send();
	}
	
	function deleteAnimal(animal, obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 202) {
	            fetchAndDisplayAnimals(obj);
	        }
	    };
	    xhttp.open("DELETE", 'http://localhost:4030/api/animals/' + animal, true);
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