import Vue from 'vue'
import Vuex from 'vuex'
import Users from 'store/users'
import Tasks from 'store/tasks'


Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        Users,
        Tasks
    }
})

