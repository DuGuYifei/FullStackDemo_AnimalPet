<template>
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
	<button @click="create">submit</button>
	<button @click="goView()">cancel/back</button>
</template>

<script>
	export default {
		data() {
			return {
				petName: "pet name",
				isSick: "",
				animalName: this.$route.query.animal
			}
		},
		methods:{
			create()
			{
				createPet(this)
				setTimeout(() => {
					this.$router.push(
						{
							path: "/animals/view", 
							query: {
								animal: this.animalName
							}
						}
					);
				}, 1000);
			},
			goView(){
				this.$router.push({
						path: "/animals/view", 
						query: {
							animal: this.animalName
						}
					});
			},
		}
	};
	
	function createPet(obj) {
	    const xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function () {
	        if (this.readyState === 4 && this.status === 200) {
	            console.log("Pet created successfully");
	        }
	    };
	    xhttp.open("POST", 'http://localhost:4030/api/pets', true);
	
	    const request = {
	          "name": obj.petName,
	          "is_sick": obj.isSick == "true"? true:false,
	          "animal": obj.animalName
	    };
	
	    xhttp.setRequestHeader('Content-Type', 'application/json');
	
	    xhttp.send(JSON.stringify(request));
	}
</script>

<style>
</style>