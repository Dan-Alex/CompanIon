import Vue from 'vue'
import VueRouter from 'vue-router'

import Profile from '../pages/Profile.vue'
import RegistrationPage from "../pages/RegistrationPage.vue";
import Employees from '../pages/Employees.vue';
import Departments from '../pages/Departments.vue'
import Company from '../pages/Company.vue'

import Tasks from '../pages/tasks/Tasks.vue'
import New from '../pages/tasks/New.vue'
import Performed from '../pages/tasks/Performed.vue'
import Completed from '../pages/tasks/Completed.vue'
import Sent from '../pages/tasks/Sent.vue'
import Create from '../pages/tasks/Create.vue'
import MyFiles from "../pages/MyDocuments.vue";


Vue.use(VueRouter)

const routes = [
    {path: '/my_tasks', component: Tasks, children: [
                                            {path:'new', component: New, name: 'new'},
                                            {path:'performed', component: Performed, name: 'performed'},
                                            {path:'completed', component: Completed, name: 'completed'},
                                            {path:'sent', component: Sent, name: 'sent'},
                                            {path:'create', component: Create, name: 'create'},
                                        ]
    },
    {path: '/employees', component: Employees},
    {path: '/departments_list', component: Departments},
    {path:'/about_company', component: Company},
    {path: '/my_documents', component: MyDocuments},
    {path: '/profile', component: Profile},
    {path: '/registration', component: RegistrationPage,  name : 'registration', props:true},

]

export default new VueRouter({
    mode: 'history',
    routes
})