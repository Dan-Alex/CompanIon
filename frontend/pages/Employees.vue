<template>
    <div>
        <br/>
        <router-link v-if="getRoles.includes('ROLE_ADMIN')" to="/registration">Зарегистрировать<br/></router-link><br/>

        <div class="find_user">
            <label for="find">Поиск:</label>
            <find-user name="find"></find-user>
        </div>

        <h3>Список всех работников</h3>
        <ul class="users_ul">
            <li class="users_li" v-for="user in getUsers"><User :user="user"></User></li>
        </ul>
    </div>
</template>

<script>

    import {mapGetters} from 'vuex'
    import User from "../components/users/User.vue";
    import FindUser from "../components/users/findUser.vue"

    export default {

        components: {
            User,
            FindUser
        },

        computed: {
            ...mapGetters(['getUsers', 'getRoles'])
        }
    }
</script>

<style>

    h3 {
        text-align: center;
    }

    .users_ul {
        margin-left: 0;
        padding-left: 0;
    }

    .users_li {
        padding: 1em;
        list-style-type: none;
    }

    .find_user {
        display: grid;
        grid-template-columns: 8% 92%;
    }
</style>