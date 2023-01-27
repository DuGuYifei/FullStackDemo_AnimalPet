import {
	createRouter,
	createWebHashHistory,
	createWebHistory
} from "vue-router"

const routes = [
	{
		path: '',
		name: 'rootP',
		component: () => import('../views/animals/animal_list.vue')
	},
	{
		path: '/animals',
		name: 'Animals',
		component: () => import('../views/animals/animal_list.vue')
	},
	{
		path: '/animals/view',
		name: 'AnimalView',
		component: () => import ('../views/animals/animal_view.vue')
	},
	{
		path: '/animals/add',
		name: 'AnimalAdd',
		component: () => import ('../views/animals/animal_add.vue')
	},
	{
		path:'/pet',
		name:'PetView',
		component: () => import('../views/pets/pet_view.vue')
	},
	{
		path:'/pet/add',
		name:'PetAdd',
		component: () => import('../views/pets/pet_add.vue')
	},
	{
		path:'/file',
		name:'FileAdd',
		component: () => import('../views/files/file.vue')
	},
	{
		path:'/file/list',
		name:'FileList',
		component: () => import('../views/files/file_list.vue')
	},
]

const router = createRouter({
	history: createWebHistory(),
	routes
})

export default router