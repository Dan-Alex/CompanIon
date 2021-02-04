<template>
    <div class="main-app">
        <h1>DOCFLOW</h1>
        <a v-if="!getProfile" href="/login">Войти</a>
        <div v-if="getProfile != null">
            <router-link to="/mytasks">Мои задачи</router-link>
            <router-link to="/employees">Работники</router-link>
            <router-link to="/departments_list">Отделы</router-link>
            <router-link to="/about_company">О предприятии</router-link>
            <router-link to="/profile">{{getProfile.name}} {{getProfile.surname}}</router-link>
            <a href="/logout">Выйти</a>
        </div>

        <router-view></router-view>
    </div>
</template>

<script>
    import {mapGetters, mapActions} from 'vuex'


    export default {

        computed:{
            ...mapGetters(['getProfile'])
        },

        methods: {
            ...mapActions(['getAllUsersAction', 'getCompanyAction', 'getAllDepartmentsAction'])
        },

        created(){
            this.getAllUsersAction();
            this.getCompanyAction();
            this.getAllDepartmentsAction()
        }

    }

</script>

<style scoped>
    .main-app {
        color: brown;
        font-size: 18pt;
    }
</style>