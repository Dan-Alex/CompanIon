import Vue from 'vue'
import VueRouter from 'vue-router'
import Employees from '../pages/Employees.vue';
import Profile from '../pages/Profile.vue'

Vue.use(VueRouter)

const routes = [
    {path: '/employees', component: Employees},
    {path: '/profile', component: Profile}
]

export default new VueRouter({
    mode: 'history',
    routes
})