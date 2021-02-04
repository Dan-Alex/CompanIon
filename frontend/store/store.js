import Vue from 'vue'
import Vuex from 'vuex'

import Users from './modules/users'
import Tasks from './modules/tasks'
import Profile from './modules/profile'
import Departments from './modules/departments'
import Company from './modules/company'


Vue.use(Vuex)

export default new Vuex.Store({

    modules: {
        Users,
        Tasks,
        Profile,
        Departments,
        Company
    }
})

