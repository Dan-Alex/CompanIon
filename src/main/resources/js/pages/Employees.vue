<template>
    <div>
        <br/>
        <router-link v-if="getRoles.includes('ROLE_ADMIN')" to="/registration">Зарегистрировать</router-link>
        <label for="find">Поиск:</label> <find-user name="find"></find-user>

        <h3>Список всех работников</h3>
        <ul>
            <li v-for="user in getUsers"><User :user="user"></User></li>
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

</style>