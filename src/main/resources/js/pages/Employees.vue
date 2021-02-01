<template>
    <div>
        <router-link v-if="getRoles.includes('ROLE_ADMIN')" to="/registration">Зарегистрировать</router-link>
        <label for="find">Поиск:</label> <find-user name="find"></find-user>

        <p>Список всех работников</p>
        <ul>
            <li v-for="user in getUsers"><User :user="user"></User></li>
        </ul>
    </div>
</template>

<script>

    import {mapActions, mapGetters} from 'vuex'
    import User from "../components/User.vue";
    import FindUser from "../components/findUser.vue"

    export default {

        components: {
            User,
            FindUser
        },

        computed: {
            ...mapGetters(['getUsers', 'getRoles'])
        },

        methods: {
            ...mapActions(['getAllUsersAction']),
        },

        created() {
            this.getAllUsersAction()
        }
    }
</script>

<style>

</style>