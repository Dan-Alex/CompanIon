import Vue from 'vue'
import VueRouter from 'vue-router'
import Employees from '../pages/Employees.vue';

Vue.use(VueRouter)

const routes = [
    {path: '/employees', component: Employees}
]

export default new VueRouter({
    mode: 'history',
    routes
})