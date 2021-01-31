<template>
    <div>
        <p>Список работников</p>

        <router-link v-if="getRoles.includes('ROLE_ADMIN')" to="/registration">Зарегистрировать</router-link>
        <ul>
            <li v-for="user in getUsers"><User :user="user"></User></li>
        </ul>

    </div>
</template>

<script>

    import {mapActions, mapGetters} from 'vuex'
    import User from "../components/User.vue";

    export default {
        components: {
            User
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