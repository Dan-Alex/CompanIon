import Vue from 'vue'
import Vuex from 'vuex'
import Users from 'store/users'
import Tasks from 'store/tasks'
import Profile from 'store/profile'


Vue.use(Vuex)

export default new Vuex.Store({

    modules: {
        Users,
        Tasks,
        Profile
    }
})

