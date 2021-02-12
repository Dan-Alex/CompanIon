<template>
    <div class="main-app">
        <div class="header">

            <h1>DOCFLOW</h1>
            <a v-if="!getProfile" href="/login">Войти</a>
            <div  class="menu" v-if="getProfile != null">
                <router-link class="menu_item" to="/my_tasks">Мои задачи</router-link>
                <router-link class="menu_item" to="/my_documents">Мои документы</router-link>
                <router-link class="menu_item" to="/employees">Работники</router-link>
                <router-link class="menu_item" to="/departments_list">Отделы</router-link>
                <router-link class="menu_item" to="/about_company">О предприятии</router-link>
                <router-link class="menu_item" to="/profile">{{getProfile.name}} {{getProfile.surname}}</router-link>
                <a class="menu_item" href="/logout">Выйти</a>
            </div>
        </div>

        <div class="content">
            <div></div>
            <router-view ></router-view>
            <div></div>
        </div>
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
        font-size: 20pt;
    }

     .header {
         display: grid;
         grid-template-columns: repeat(1, 1fr);
         background-color: khaki;
         text-align: center;
         padding: 1em;
         border-radius: 15px;
     }

    .menu {
        display: grid;
        grid-template-columns: repeat(7, 1fr);

        grid-gap: 1em;
    }

    .menu_item {
        background-color: coral;
        padding: 1em;
        text-align: center;
        border-radius: 15px;
    }

    .content {
        display: grid;
        grid-template-columns: 1fr 4fr 1fr;
        justify-items: stretch;
    }
</style>