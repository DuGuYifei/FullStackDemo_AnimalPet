<template>
	<h1>Files</h1>
	<tr>
		<th>id</th>
		<th>name</th>
		<th>author</th>
		<th>description</th>
	</tr>
	<tr v-for="file in fileArr">
		<td>{{file.id}}</td>
		<td>{{file.name}}</td>
		<td>{{file.author}}</td>
		<td>{{file.description}}</td>
	</tr>
	<button @click="goAdd">add</button>
</template>

<script>
	export default {
		data() {
			return {
				fileArr: []
			}
		},
		methods: {
			delAnimal(animal){
				deleteAnimal(animal, this)
			},
			goAdd(){
				this.$router.push("/file");
			}
		},
		mounted:function () { 
			fetchAndDisplayFiles(this);
		},
	};
	
	
	function displayFiles(files, obj) {
		obj.animalArr = [];
		files.files.forEach(file => {
			console.log(file);
			obj.fileArr.push(file);
		});
	}
	
	function fetchAndDisplayFiles(obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            displayFiles(JSON.parse(this.responseText), obj);
	        }
	    };
	    xhttp.open("GET", 'http://localhost:4030/api/files', true);
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