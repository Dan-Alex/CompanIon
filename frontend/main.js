import Vue from 'vue'
import '@babel/polyfill'
import './resource/resource'
import store from './store/store'
import router from './route/router'
import App from './pages/App.vue'

new Vue({
    el:"#app",
    store,
    router,
    render: a => a(App)
})