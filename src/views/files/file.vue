<template>
	<input id="file" type="file" name="file" ref='ipt'>
	<br />
	<input id="author" name="author" v-model="author"/>
	<br />
	<input id="description" name="description" v-model="description"/>
	<br />
	<button @click="create">upload</button>
	<br />
	<br />
	<input id="dl_id" name="dl_id" v-model="dl_id"/>
	<br />
	<button @click="download">download</button>
	<br>
	<button @click="goList">file</button>
</template>


<script>

	export default {
		data() {
			return {
				author: "unknown author",
				description: "no description",
				dl_id: "input id",
			}
		},
		methods:{
			create()
			{
				const xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function () {
				    if (this.readyState === 4 && this.status === 200) {
				        console.log("Pet created successfully");
				    }
				};
				xhttp.open("POST", 'http://localhost:4030/api/files', true);
				let file = this.$refs.ipt.files[0];
				let formData = new FormData();
				formData.append("name", file.name);
				formData.append("author", this.author);
				formData.append("description", this.description);
				formData.append("file", file);
					
				//xhttp.setRequestHeader('Content-Type', 'multipart/form-data');
				xhttp.send(formData);
				
				// $.ajax({
				//     type: "POST",
				//     url: "http://localhost:4030/api/files",
				//     data: formData,
				//     processData: false,
				//     contentType: "application/json",
				//     success: function (msg) {
				//         alert("sucess");
				//     }
				// })
			},
			download(){
				const xhttp = new XMLHttpRequest();
				let dlid = this.dl_id;
				xhttp.onreadystatechange = function () {
				    if (this.readyState === 4 && this.status === 200) {
						window.open("http://localhost:4030/api/files/" + dlid);
				    }
				};
				xhttp.open("GET", 'http://localhost:4030/api/files/' + this.dl_id, true);
				xhttp.send();
			},
			goList(){
				this.$router.push(
					{
						path: "/file/list", 
					}
				);
			}
		}
	};
	
</script>

<style>
</style>