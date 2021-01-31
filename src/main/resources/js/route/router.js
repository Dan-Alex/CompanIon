import Vue from 'vue'
import VueRouter from 'vue-router'

import Employees from '../pages/Employees.vue';
import Profile from '../pages/Profile.vue'
import RegistrationPage from "../pages/RegistrationPage.vue";

import Tasks from '../pages/Tasks.vue'
import New from '../pages/tasks/New.vue'
import Performed from '../pages/tasks/Performed.vue'
import Completed from '../pages/tasks/Completed.vue'
import Sent from '../pages/tasks/Sent.vue'
import Create from '../pages/tasks/Create.vue'

Vue.use(VueRouter)

const routes = [
    {path: '/employees', component: Employees},
    {path: '/profile', component: Profile},
    {path: '/registration', component: RegistrationPage,  name : 'registration', props:true},
    {path: '/mytasks', component: Tasks, children: [
                                            {path:'new', component: New, name: 'new'},
                                            {path:'performed', component: Performed, name: 'performed'},
                                            {path:'completed', component: Completed, name: 'completed'},
                                            {path:'sent', component: Sent, name: 'sent'},
                                            {path:'create', component: Create, name: 'create'},
                                                    ]
    }
]

export default new VueRouter({
    mode: 'history',
    routes
})