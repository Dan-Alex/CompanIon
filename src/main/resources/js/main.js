import Vue from 'vue'
import '@babel/polyfill'
import App from 'pages/App.vue'
import 'api/resource'
import store from 'store/store'
import router from 'route/router'


new Vue({
    el:"#app",
    store,
    router,
    render: a => a(App)
})