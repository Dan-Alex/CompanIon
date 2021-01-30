import Vue from 'vue'
import VueRouter from 'vue-router'
import Employees from '../pages/Employees.vue';
import Profile from '../pages/Profile.vue'
import RegistrationPage from "../pages/RegistrationPage.vue";

Vue.use(VueRouter)

const routes = [
    {path: '/employees', component: Employees},
    {path: '/profile', component: Profile},
    {path: '/registration', component: RegistrationPage,  name : 'registration', props:true}
]

export default new VueRouter({
    mode: 'history',
    routes
})